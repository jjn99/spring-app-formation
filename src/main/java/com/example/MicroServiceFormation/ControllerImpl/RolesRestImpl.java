package com.example.MicroServiceFormation.ControllerImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Controller.RolesRest;
import com.example.MicroServiceFormation.Model.AppRoles;
import com.example.MicroServiceFormation.Service.RolesService;
import com.example.MicroServiceFormation.Utils.DfpUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RolesRestImpl implements RolesRest{
    
    private RolesService roles;

    public RolesRestImpl(RolesService roles) {
        this.roles = roles;
    }
    

    @Override
    public ResponseEntity<String> addRole(Map<String, String> requestMap) {
        try{
            return roles.addRole(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.OperationAddRoleEchec, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<AppRoles>> getAllRoles() {
        try{
            return roles.getAllRoles();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<AppRoles> findRoleByRoleName(Map<String,String> requestMap) {
        try{
            return roles.findRoleByRoleName(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new AppRoles(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
