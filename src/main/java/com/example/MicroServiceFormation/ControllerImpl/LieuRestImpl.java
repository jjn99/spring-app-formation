package com.example.MicroServiceFormation.ControllerImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Controller.LieuRest;
import com.example.MicroServiceFormation.Model.Lieu;
import com.example.MicroServiceFormation.Service.LieuService;
import com.example.MicroServiceFormation.Utils.DfpUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LieuRestImpl implements LieuRest{

    @Autowired
    LieuService lieuService;
   
    
    @Override
    public ResponseEntity<String> addLieu(Map<String, String> requestMap) {
                      try{
              return lieuService.addLieu(requestMap);
               }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  
 
    }

    @Override
    public ResponseEntity<String> updateLieu(Map<String, String> requestMap) {
                      try{
                         return lieuService.updateLieu(requestMap);
                      }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  
 
    }

    @Override
    public ResponseEntity<List<Lieu>> getAllLieu() {
                      try{
                         return lieuService.getAllLieu();
                      }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
 
    }

    @Override
    public ResponseEntity<Lieu> getLieuById(Map<String, String> requestMap) {
                      try{
                         return lieuService.getLieuById(requestMap);
                      }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Lieu(), HttpStatus.INTERNAL_SERVER_ERROR);
  
 
    }

    @Override
    public ResponseEntity<String> deleteLieu(Integer idLieu) {
                      try{
                         return lieuService.deleteLieu(idLieu);
                      }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  
 
    }

}
