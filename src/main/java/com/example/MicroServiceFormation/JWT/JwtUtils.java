package com.example.MicroServiceFormation.JWT;

import com.example.MicroServiceFormation.Dao.UserDao;
import com.example.MicroServiceFormation.Model.AppUsers;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class JwtUtils {
    
    @Autowired 
    private UserDao userDao;
    
    private String secret = "SonabelDfp";
    
    public String extractUsername(String token){
        return extractClaims(token,Claims::getSubject);
    }
    
    public Date extractExpiration(String token){
        return extractClaims(token,Claims::getExpiration);
    }
    
    public <T> T extractClaims(String token, Function <Claims,T> claimsResolver){
        final Claims claims= extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    
    public Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    
    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    
    public String generateToken(String username){
        ArrayList<String> roles = new ArrayList<>();
         AppUsers user = userDao.findByUsername(username);
         user.getAppRoles().forEach(r->{
          roles.add(r.getRoleName());
         });
         Map<String,Object> claims= new HashMap<>();
         claims.put("roles", roles);
          return createToken(claims,username);
        
    }
    
    private String createToken(Map <String, Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000 *60 *60 *12))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }
    
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
