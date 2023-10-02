package com.example.MicroServiceFormation.ControllerImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Controller.EntiteRest;
import com.example.MicroServiceFormation.Model.Entite;
import com.example.MicroServiceFormation.Model.Formation;
import com.example.MicroServiceFormation.Service.EntiteService;
import com.example.MicroServiceFormation.Utils.DfpUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntiteRestImpl implements EntiteRest {

    @Autowired
    EntiteService entiteService;
   
    
    @Override
    public ResponseEntity<String> addEntite(Map<String, String> requestMap) {
                   try{
           return entiteService.addEntite(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  
  
    }

    @Override
    public ResponseEntity<String> updateEntite(Map<String, String> requestMap) {
                   try{
           return entiteService.updateEntite(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  
  
    }

    @Override
    public ResponseEntity<List<Entite>> getAllEntite() {
                   try{
           return entiteService.getAllEntite();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
    }

    @Override
    public ResponseEntity<Entite> getEntiteById(Integer idEntite) {
    try{
           return entiteService.getEntiteById(idEntite);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Entite(), HttpStatus.INTERNAL_SERVER_ERROR);
  
  
    }

    @Override
    public ResponseEntity<String> deleteEntite(Integer idEntite) {
    try{
           return entiteService.deleteEntite(idEntite);
         }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  
  
    }

    @Override
    public ResponseEntity<List<Formation>> getAllEntiteFormation(Integer idEntite) {
                          try{
           return entiteService.getAllEntiteFormation(idEntite);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
    }

    @Override
    public ResponseEntity<Collection<Formation>> getAllFormationEntite(Integer idEntite) {
                          try{
           return entiteService.getAllFormationEntite(idEntite);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
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
