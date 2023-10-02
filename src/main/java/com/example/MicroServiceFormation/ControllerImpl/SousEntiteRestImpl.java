package com.example.MicroServiceFormation.ControllerImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Controller.SousEntiteRest;
import com.example.MicroServiceFormation.Model.SousEntite;
import com.example.MicroServiceFormation.Service.SousEntiteService;
import com.example.MicroServiceFormation.Utils.DfpUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SousEntiteRestImpl implements SousEntiteRest{

    @Autowired
    SousEntiteService entiteService;
   
    
    @Override
    public ResponseEntity<String> addSousEntite(Map<String, String> requestMap) {
                             try{
                                return entiteService.addSousEntite(requestMap);
                             }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  
 
    }

    @Override
    public ResponseEntity<String> updateSousEntite(Map<String, String> requestMap) {
                             try{
                                return entiteService.updateSousEntite(requestMap);
                             }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  
 
    }

    @Override
    public ResponseEntity<List<SousEntite>> getAllSousEntite() {
                             try{
                                return entiteService.getAllSousEntite();
                             }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
 
    }

    @Override
    public ResponseEntity<SousEntite> getSousEntiteById(Map<String, String> requestMap) {
                             try{
                                return entiteService.getSousEntiteById(requestMap);
                             }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new SousEntite(), HttpStatus.INTERNAL_SERVER_ERROR);
  
 
    }

    @Override
    public ResponseEntity<List<SousEntite>> getSousEntiteByIdEntite(Map<String, String> requestMap) {
                             try{
                                return entiteService.getSousEntiteByIdEntite(requestMap);
                             }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
 
    }

    @Override
    public ResponseEntity<String> deleteSousEntite(Integer idSousEntite) {
                             try{
                                return entiteService.deleteEntite(idSousEntite);
                             }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  
 
    }
    
       @Override
    public ResponseEntity<String> ActivezBudget(Map<String, String> requestMap) {
      try{
             return entiteService.ActivezBudget(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
           return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR); 
    
      }


 


}
