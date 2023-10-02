package com.example.MicroServiceFormation.ServiceImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Dao.RolesDao;
import com.example.MicroServiceFormation.Model.AppRoles;
import com.example.MicroServiceFormation.Service.RolesService;
import com.example.MicroServiceFormation.Utils.DfpUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RolesServicesImpl implements RolesService{

    private RolesDao roleDao;

    public RolesServicesImpl(RolesDao roleDao) {
        this.roleDao = roleDao;
    }
    
    //Validation
    private boolean validateRole(Map<String, String> requestMap,boolean validateId){
             if(requestMap.containsKey("roleName")){
            
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
    
    //Pour l'ajout
    private AppRoles getRoleFromMap(Map<String ,String> requestMap){
        AppRoles roles = new AppRoles();
        roles.setRoleName(requestMap.get("roleName"));
        return roles;
    }
     
    @Override
    public ResponseEntity<String> addRole(Map<String, String> requestMap) {
             try{
            
        if(validateRole(requestMap,false)){
                roleDao.save(getRoleFromMap(requestMap));
                return DfpUtils.getResponseEntity(DfpConstantes.Created, HttpStatus.OK);
        }else{
            return DfpUtils.getResponseEntity(DfpConstantes.Invalid_Data, HttpStatus.BAD_REQUEST);
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Not_Created , HttpStatus.INTERNAL_SERVER_ERROR);
   
    }

    @Override
    public ResponseEntity<List<AppRoles>> getAllRoles() {
                        try{
            return new ResponseEntity<>(roleDao.findAll(),HttpStatus.OK);
        }catch(Exception ex){
            
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
   }

    @Override
    public ResponseEntity<AppRoles> findRoleByRoleName(Map<String,String> requestMap) {
                           try{
                               String roleName= requestMap.get("roleName");
                return new ResponseEntity<>( roleDao.findByRoleName(roleName), HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new AppRoles(), HttpStatus.INTERNAL_SERVER_ERROR);
 

    }
    
}
