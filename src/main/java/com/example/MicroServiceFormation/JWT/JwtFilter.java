package com.example.MicroServiceFormation.JWT;

import io.jsonwebtoken.Claims;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired 
    JwtUtils jwtUtils;
    
    @Autowired
    CustomerUserDetailsService service;
     
    private Claims claims = null;
    private String username=null;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().matches("/Utilisateur/login|/Utilisateur/forgotPassword|/Stage/addPostulant|/Stage/addDemande|/Utilisateur/addNewUser")){
            filterChain.doFilter(request, response);
        }else{
            String authorizationHeader = request.getHeader("Authorization");
            String token = null;
            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
                token=authorizationHeader.substring(7);
                username= jwtUtils.extractUsername(token);
                claims = jwtUtils.extractAllClaims(token);
            }
            if(username != null && SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails userDetails = service.loadUserByUsername(username);
                if(jwtUtils.validateToken(token, userDetails)){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
             filterChain.doFilter(request, response);
        }
    }
    
    
    public String getCurrentUser(){
        return username;
    }
}
