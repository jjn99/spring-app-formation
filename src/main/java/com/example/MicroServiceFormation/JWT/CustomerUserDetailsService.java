package com.example.MicroServiceFormation.JWT;

import com.example.MicroServiceFormation.Dao.UserDao;
import com.example.MicroServiceFormation.Model.AppUsers;
import java.util.ArrayList;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class CustomerUserDetailsService implements UserDetailsService{

    @Autowired
    private UserDao userDao;
    private AppUsers userDetail;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("JWT/CustomerUserDetailsService/loadUserByUsername {}", username);
         userDetail= userDao.findByUsername(username);
        if(!Objects.isNull(userDetail)){
            return new User(userDetail.getUsername(),userDetail.getPassword(),new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("Utilisateur non trouvé");
        }
    }
    
    public AppUsers getUserdetails(){
        return userDetail;
    }
    
}
