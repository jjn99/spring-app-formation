package com.example.MicroServiceFormation.Service;

import com.example.MicroServiceFormation.Model.AppRoles;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;

public interface RolesService {

    public ResponseEntity<String> addRole(Map<String, String> requestMap);

    public ResponseEntity<List<AppRoles>> getAllRoles();

    public ResponseEntity<AppRoles> findRoleByRoleName(Map<String,String> requestMap);
    
}
