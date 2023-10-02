package com.example.MicroServiceFormation.ControllerImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Controller.CoutRest;
import com.example.MicroServiceFormation.Model.Cout;
import com.example.MicroServiceFormation.Service.CoutService;
import com.example.MicroServiceFormation.Utils.DfpUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoutRestImpl implements CoutRest{

    @Autowired
    CoutService coutService;
   
    @Override
    public ResponseEntity<String> addCout(Map<String, String> requestMap) {
            try{
            return coutService.addCout(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  
    }

    @Override
    public ResponseEntity<String> updateCout(Map<String, String> requestMap) {
                  try{
           return coutService.updateCout(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  
    }

    @Override
    public ResponseEntity<List<Cout>> getAllCout() {
        try{
           return coutService.getAllCout();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
    }

    @Override
    public ResponseEntity<Cout> getCoutById(Integer idCout) {
        try{
           return coutService.getCoutById(idCout);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Cout(), HttpStatus.INTERNAL_SERVER_ERROR);
  
    }

    @Override
    public  ResponseEntity<List<Cout>> getEntiteByFormationId(Integer idFormation) {
        try{
           return coutService.getEntiteByFormationId(idFormation);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
    }

    @Override
    public ResponseEntity<String> deleteCout(Integer idCout) {
              try{
           return coutService.deleteCout(idCout);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  
    }

}
