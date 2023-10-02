package com.example.MicroServiceFormation.Service;

import com.example.MicroServiceFormation.Model.AppUsers;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;

public interface UsersService {

    public ResponseEntity<String> addNewUser(Map<String, String> requestMap);

    public ResponseEntity<String> updateUser( Map<String, String> requestMap);

    public ResponseEntity<AppUsers> findUserByUserName(Map<String, String> requestMap);

    public ResponseEntity<AppUsers> getUserByUserName(String username);
    
    public ResponseEntity<List<AppUsers>> getAllUsers();

    public ResponseEntity<String> addRolesToUsers(Map<String, String> requestMap);

    public ResponseEntity<String> removeRoleToUsers(Map<String, String> requestMap);

    public ResponseEntity<String> deleteUserById(Map<String, String> requestMap);

    public ResponseEntity<String> login(Map<String, String> requestMap);

    public ResponseEntity<String> checkToken();

    public ResponseEntity<String> changePassword(Map<String, String> requestMap);

    public ResponseEntity<String> forgotPassword(Map<String, String> requestMap);
    
}
