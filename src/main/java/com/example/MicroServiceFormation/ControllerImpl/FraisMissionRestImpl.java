package com.example.MicroServiceFormation.ControllerImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Controller.FraisMissionRest;
import com.example.MicroServiceFormation.Model.FraisMission;
import com.example.MicroServiceFormation.Service.FraisMissionService;
import com.example.MicroServiceFormation.Utils.DfpUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FraisMissionRestImpl implements FraisMissionRest{

    @Autowired
    FraisMissionService fraisService;
   
    
    @Override
    public ResponseEntity<String> addFrais(Map<String, String> requestMap) {
             try{
          return fraisService.addFrais(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  
    }

    @Override
    public ResponseEntity<String> updateFrais(Map<String, String> requestMap) {
               try{
          return fraisService.updateFrais(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  
    }

    @Override
    public ResponseEntity<List<FraisMission>> getAllFrais() {
         try{
          return fraisService.getAllFrais();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<FraisMission> getFraisMissionById(Map<String, String> requestMap) {
               try{
          return fraisService.getFraisMissionById(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new FraisMission(), HttpStatus.INTERNAL_SERVER_ERROR);
  
    }

    @Override
    public ResponseEntity<String> deleteFrais(Integer idFraisMission) {
  
           try{
          return fraisService.deleteFrais(idFraisMission);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
