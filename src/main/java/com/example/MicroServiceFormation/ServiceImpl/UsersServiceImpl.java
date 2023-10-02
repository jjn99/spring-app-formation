package com.example.MicroServiceFormation.ServiceImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Dao.RolesDao;
import com.example.MicroServiceFormation.Dao.UserDao;
import com.example.MicroServiceFormation.JWT.CustomerUserDetailsService;
import com.example.MicroServiceFormation.JWT.JwtUtils;
import com.example.MicroServiceFormation.Model.AppRoles;
import com.example.MicroServiceFormation.Model.AppUsers;
import com.example.MicroServiceFormation.Service.RolesService;
import com.example.MicroServiceFormation.Service.UsersService;
import com.example.MicroServiceFormation.Utils.DfpUtils;
import com.example.MicroServiceFormation.Utils.EmailUtils;
import com.google.common.base.Strings;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UsersServiceImpl implements UsersService {

    private RolesDao rolesDao;
    private UserDao userDao;
    private PasswordEncoder passwodEncoder;
    private AuthenticationManager authentication;

    public UsersServiceImpl(RolesDao rolesDao, UserDao userDao,PasswordEncoder passwodEncoder,AuthenticationManager authentication) {
        this.rolesDao= rolesDao;
        this.userDao = userDao;
        this.passwodEncoder=passwodEncoder;
        this.authentication=authentication;
    }
    
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private EmailUtils emailUtils;
    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;
    
    //Validation
        private boolean validateUser(Map<String, String> requestMap,boolean validateId){
        
        if(requestMap.containsKey("username") &&requestMap.containsKey("password")){
            
              if(requestMap.containsKey("id") && validateId){
            return true;
            }
            else if(!validateId)
            {
             return true;
            }
        }
        return false;
    }
    //Mapping
    private AppUsers getUsersFromMap(Map<String, String> requestMap) {

        AppUsers user = new AppUsers();
        user.setUsername(requestMap.get("username"));
        user.setEmail(requestMap.get("email"));
        user.setMatricule(requestMap.get("matricule"));
        user.setNumeroTelephone(requestMap.get("numeroTelephone"));
        String pw= requestMap.get("password");
       user.setPassword(passwodEncoder.encode(pw));
       return user;
    }
    //updateUser
        private AppUsers updateUserFromMap(AppUsers user, Map<String, String > requestMap){
            user.setUsername(requestMap.get("username")== null? user.getUsername():requestMap.get("username"));
            user.setEmail(requestMap.get("email") == null ? user.getEmail(): requestMap.get("email"));
            user.setMatricule(requestMap.get("matricule") == null ? user.getMatricule(): requestMap.get("matricule"));
            user.setNumeroTelephone(requestMap.get("numeroTelephone") == null ? user.getNumeroTelephone(): requestMap.get("numeroTelephone"));       
        return user;
    }
    @Override
    public ResponseEntity<String> addNewUser(Map<String, String> requestMap) {
              try{
            
        if(validateUser(requestMap,false)){
            String email = requestMap.get("email");
            AppUsers user = userDao.findByEmail(email);
            String username = requestMap.get("username");
            AppUsers userUsername = userDao.findByUsername(username);
            if(user == null && userUsername == null){
                userDao.save(getUsersFromMap(requestMap));
                return DfpUtils.getResponseEntity(DfpConstantes.Created, HttpStatus.OK);
            }
            else{
                return DfpUtils.getResponseEntity(DfpConstantes.Existing, HttpStatus.OK);
            }
        }else{
            return DfpUtils.getResponseEntity(DfpConstantes.Invalid_Data, HttpStatus.BAD_REQUEST);
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Not_Created , HttpStatus.INTERNAL_SERVER_ERROR);
   
 
    }

    @Override
    public ResponseEntity<String> updateUser( Map<String, String> requestMap) {
              try{
                 
        if(requestMap.get("id") != null){
             Long id= Long.parseLong(requestMap.get("id"));
            AppUsers user = userDao.findByIdUser(id);
            if(user != null){
                userDao.save(updateUserFromMap(user,requestMap));
                return DfpUtils.getResponseEntity(DfpConstantes.Modified, HttpStatus.OK);
            }else{
                return DfpUtils.getResponseEntity(DfpConstantes.Not_Existing, HttpStatus.BAD_REQUEST);
            }
        }else{
            return DfpUtils.getResponseEntity(DfpConstantes.Invalid_Data, HttpStatus.BAD_REQUEST);
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Not_Modified , HttpStatus.INTERNAL_SERVER_ERROR);
 
 
    }

    @Override
    public ResponseEntity<AppUsers> findUserByUserName(Map<String, String> requestMap) {
        try{
            String username= requestMap.get("username");
            return new ResponseEntity<>( userDao.findByUsername(username), HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new AppUsers(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @Override
    public ResponseEntity<AppUsers> getUserByUserName(String username) {
        try{
            return new ResponseEntity<>( userDao.getByUsername(username), HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new AppUsers(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<AppUsers>> getAllUsers() {
             try{
            return new ResponseEntity<>( userDao.findAll(), HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
    }

    @Override
    public ResponseEntity<String> addRolesToUsers(Map<String, String> requestMap) {
        String username = requestMap.get("username");
        String roleName = requestMap.get("roleName");
        AppUsers appUser = userDao.findByUsername(username);
        if(appUser != null){
        AppRoles appRole = rolesDao.findByRoleName(roleName);
        appUser.getAppRoles().add(appRole);
        userDao.save(appUser);
        return DfpUtils.getResponseEntity(DfpConstantes.Add_Success, HttpStatus.OK);
        }
        else{
            log.info("objet null");
            return DfpUtils.getResponseEntity(DfpConstantes.OperationAddRoleEchec, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> removeRoleToUsers(Map<String, String> requestMap) {
        String username = requestMap.get("username");
        String roleName = requestMap.get("roleName");
         AppUsers appUser = userDao.findByUsername(username);
        if(appUser != null){
        AppRoles appRole = rolesDao.findByRoleName(roleName);
        appUser.getAppRoles().remove(appRole);
        userDao.save(appUser);
        return DfpUtils.getResponseEntity(DfpConstantes.Add_Success, HttpStatus.OK);
        }
        else{
            return DfpUtils.getResponseEntity(DfpConstantes.OperationAddRoleEchec, HttpStatus.BAD_REQUEST);
        } }

    @Override
    public ResponseEntity<String> deleteUserById(Map<String, String> requestMap) {
                               try{
            if(requestMap.get("id") != null){
                Long id = Long.valueOf(requestMap.get("id"));
               AppUsers user = userDao.findByIdUser(id);
                if(user != null){
                    userDao.deleteById(id);
                    return DfpUtils.getResponseEntity(DfpConstantes.Delete, HttpStatus.OK);
                }else{
                    DfpUtils.getResponseEntity(DfpConstantes.Not_Existing, HttpStatus.BAD_REQUEST);
                }
            }else{
                return DfpUtils.getResponseEntity(DfpConstantes.Invalid_Data, HttpStatus.BAD_REQUEST);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Not_Delete, HttpStatus.INTERNAL_SERVER_ERROR);


    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try{
            String username=requestMap.get("username");
            String password=requestMap.get("password");
            Authentication auth= authentication.authenticate(
                    new UsernamePasswordAuthenticationToken(username,password)
            );
            if(auth.isAuthenticated()){
                return new ResponseEntity<String>("{\"token\":\"" +
                        jwtUtils.generateToken(customerUserDetailsService.getUserdetails().getUsername()) +"\"}",HttpStatus.OK);
            }else{
                return DfpUtils.getResponseEntity(DfpConstantes.Authentication_Echec, HttpStatus.BAD_REQUEST);   
            }
        }catch(Exception ex){
            log.error("{}", ex);
        }
               return DfpUtils.getResponseEntity(DfpConstantes.Not_Connected, HttpStatus.BAD_REQUEST);        
    }

    @Override
    public ResponseEntity<String> checkToken() {
         log.info("true");
        return DfpUtils.getResponseEntity("true", HttpStatus.OK);
       
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try{
            String username=requestMap.get("username");
            AppUsers user = userDao.findByUsername(username);
            if( user != null){
                if(user.getPassword().equals(requestMap.get("ancienPassword"))){
                    user.setPassword(requestMap.get("newPassword"));
                    userDao.save(user);
                    return DfpUtils.getResponseEntity(DfpConstantes.Success_changePassword, HttpStatus.OK);
                }
            return DfpUtils.getResponseEntity(DfpConstantes.Error_AncienPassword, HttpStatus.BAD_REQUEST);
            }
            return DfpUtils.getResponseEntity(DfpConstantes.Not_Found, HttpStatus.BAD_REQUEST);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Error_ChangePassword, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
        try{
            String email= requestMap.get("email");
            AppUsers user = userDao.findByEmail(email);
            if(user !=null && !Strings.isNullOrEmpty(user.getEmail())){
                emailUtils.forgotPasswordMessage(user.getEmail(), "Modification de mot de pass(Mot de passe oublié)", user.getPassword());
            }
             return DfpUtils.getResponseEntity(DfpConstantes.Not_Found, HttpStatus.BAD_REQUEST);
  
        }catch(Exception ex){
            ex.printStackTrace();
        }
              return DfpUtils.getResponseEntity(DfpConstantes.Error_ChangePassword, HttpStatus.INTERNAL_SERVER_ERROR);
  
    }
    
}
