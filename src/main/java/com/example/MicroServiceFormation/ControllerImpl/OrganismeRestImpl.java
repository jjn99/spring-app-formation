package com.example.MicroServiceFormation.ControllerImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Controller.OrganismeRest;
import com.example.MicroServiceFormation.Model.Organisme;
import com.example.MicroServiceFormation.Service.OrganismeService;
import com.example.MicroServiceFormation.Utils.DfpUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrganismeRestImpl implements OrganismeRest{

    @Autowired
    OrganismeService organismeService;
    
    @Override
    public ResponseEntity<String> addOrganisme(Map<String, String> requestMap) {
      try{
          return organismeService.addOrganisme(requestMap);
      }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  

    }

    @Override
    public ResponseEntity<String> updateOrganisme(Map<String, String> requestMap) {
                             try{
     return organismeService.updateOrganisme(requestMap);
               }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  

    }

    @Override
    public ResponseEntity<List<Organisme>> getAllOrganisme() {
                             try{
                                return organismeService.getAllOrganisme();
                             }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  

    }

    @Override
    public ResponseEntity<Organisme> getOrganismeById(Map<String, String> requestMap) {
                             try{
            return organismeService.getOrganismeById(requestMap);
               }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Organisme(), HttpStatus.INTERNAL_SERVER_ERROR);
  

    }

    @Override
    public ResponseEntity<String> deleteOrganisme(Integer idOrganisme) {
                             try{
                                return organismeService.deleteOrganisme(idOrganisme);
                             }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  

    }

}
