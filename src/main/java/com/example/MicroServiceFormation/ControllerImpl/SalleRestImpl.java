package com.example.MicroServiceFormation.ControllerImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Controller.SalleRest;
import com.example.MicroServiceFormation.Model.Salle;
import com.example.MicroServiceFormation.Service.SalleService;
import com.example.MicroServiceFormation.Utils.DfpUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalleRestImpl implements SalleRest{

    @Autowired
    SalleService salleService;
    
    @Override
    public ResponseEntity<String> addSalle(Map<String, String> requestMap) {
        try{
           return salleService.addSalle(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateSalle(Map<String, String> requestMap) {
        try{
           return salleService.updateSalle(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Salle>> getAllSalle() {
        try{
            return salleService.getAllSalle();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<Salle> getSalleById(Map<String, String> requestMap) {
        try{
            return salleService.getSalleById(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Salle(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteSalle(Integer idSalle) {
        try{
            return salleService.deleteSalle(idSalle);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
