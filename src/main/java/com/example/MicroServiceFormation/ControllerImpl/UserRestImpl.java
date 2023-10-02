package com.example.MicroServiceFormation.ControllerImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Controller.UserRest;
import com.example.MicroServiceFormation.Dao.UserDao;
import com.example.MicroServiceFormation.Model.AppUsers;
import com.example.MicroServiceFormation.Service.UsersService;
import com.example.MicroServiceFormation.Utils.DfpUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestImpl implements UserRest{

    @Autowired
    private UsersService users;
    
    @Override
    public ResponseEntity<String> addNewUser(Map<String, String> requestMap) {
        try{
            return users.addNewUser(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.OperationAddUserEchec, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateUser(Map<String, String> requestMap) {
        try{
            return users.updateUser(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.OperationUpdateUserEchec, HttpStatus.INTERNAL_SERVER_ERROR);
   
    }

    @Override
    public ResponseEntity<AppUsers> findUserByUserName(Map<String, String> requestMap) {
        try{
            return users.findUserByUserName(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new AppUsers(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @Override
    public ResponseEntity<AppUsers> getUserByUserName(String username) {
        try{
            return users.getUserByUserName(username);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new AppUsers(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<AppUsers>> getAllUsers() {
               try{
            return users.getAllUsers();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
    }

    @Override
    public ResponseEntity<String> addRolesToUsers(Map<String, String> requestMap) {
                       try{
            return users.addRolesToUsers(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.OperationAddRolesToUsersEchec, HttpStatus.INTERNAL_SERVER_ERROR);
 
    }

    @Override
    public ResponseEntity<String> removeRoleToUsers(Map<String, String> requestMap) {
                             try{
            return users.removeRoleToUsers(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.OperationRemoveRolesToUsersEchec, HttpStatus.INTERNAL_SERVER_ERROR);
 
    }

    @Override
    public ResponseEntity<String> deleteUserById(Map<String, String> requestMap) {
        try{
            return users.deleteUserById(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.OperationDeleteUsersEchec, HttpStatus.INTERNAL_SERVER_ERROR);
 
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try{
            return users.login(requestMap);
        }catch(Exception e){
            e.printStackTrace();
        }
            return DfpUtils.getResponseEntity(DfpConstantes.Not_Connected, HttpStatus.INTERNAL_SERVER_ERROR);
 
    }

    @Override
    public ResponseEntity<String> checkToken() {
        try{
            return users.checkToken();
        }catch(Exception e){
            e.printStackTrace();
        }
                  return DfpUtils.getResponseEntity(DfpConstantes.Not_Connected, HttpStatus.INTERNAL_SERVER_ERROR);
 
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try{
            return users.changePassword(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Error_ChangePassword, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
              try{
            return users.forgotPassword(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Error_ChangePassword, HttpStatus.INTERNAL_SERVER_ERROR);
 
    }

    
}
