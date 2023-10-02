package com.example.MicroServiceFormation.Controller;

import com.example.MicroServiceFormation.Model.AppUsers;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin("*")
@RequestMapping(path="/Utilisateur")
public interface UserRest {
    
    @PostMapping(path="/addNewUser")
    ResponseEntity<String> addNewUser(@RequestBody Map<String,String> requestMap);
    
    @PostMapping(path="/updateUser")
    ResponseEntity<String> updateUser(@RequestBody Map<String,String> requestMap);
    
    @PostMapping(path="/login")
    ResponseEntity<String> login(@RequestBody Map<String,String> requestMap);
    
    @GetMapping(path="/findUserByUserName")
    ResponseEntity<AppUsers> findUserByUserName(@RequestBody Map<String,String> requestMap);
    
    @GetMapping(path="/getUserByUserName/{username}")
    ResponseEntity<AppUsers> getUserByUserName(@PathVariable String username);
    
    
    @GetMapping(path="/getAllUsers")
    ResponseEntity<List<AppUsers>> getAllUsers();
    
    @PostMapping(path="/addRolesToUsers")
    ResponseEntity<String> addRolesToUsers(@RequestBody Map<String,String> requestMap);
    
    @PostMapping(path="/removeRoleToUsers")
    ResponseEntity<String> removeRoleToUsers(@RequestBody Map<String,String> requestMap);
    
    @DeleteMapping(path="/deleteUser")
    ResponseEntity<String> deleteUserById(@RequestBody Map<String, String> requestMap);
    
    @GetMapping(path="/checkToken")
    ResponseEntity<String> checkToken();
    
    @PostMapping(path="/changePassword")
    ResponseEntity<String> changePassword(@RequestBody Map<String,String> requestMap);
    
    @PostMapping(path="/forgotPassword")
    ResponseEntity<String> forgotPassword(@RequestBody Map<String,String> requestMap);
    
    
}
