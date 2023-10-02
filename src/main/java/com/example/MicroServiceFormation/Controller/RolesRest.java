package com.example.MicroServiceFormation.Controller;

import com.example.MicroServiceFormation.Model.AppRoles;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
@CrossOrigin("*")
@RequestMapping(path="/Roles")
public interface RolesRest {
    
    @PostMapping(path="/addRole")
    ResponseEntity<String> addRole(@RequestBody Map<String,String> requestMap);
    
    @GetMapping(path="/listRoles")
    ResponseEntity<List<AppRoles>> getAllRoles();
    
    @GetMapping(path="/findRoleByRoleName")
    ResponseEntity<AppRoles> findRoleByRoleName(@RequestBody Map<String,String> requestMap);
}
