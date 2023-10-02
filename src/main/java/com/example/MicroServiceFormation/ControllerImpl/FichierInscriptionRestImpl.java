package com.example.MicroServiceFormation.ControllerImpl;

import com.example.MicroServiceFormation.Controller.FichierInscriptionRest;
import com.example.MicroServiceFormation.Service.FichierInscriptionService;
import com.example.MicroServiceFormation.Utils.DfpUtils;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class FichierInscriptionRestImpl /*implements FichierInscriptionRest */{
/*
    @Autowired
    FichierInscriptionService fichierInscriptionService;
    
    @Override
    public ResponseEntity<String> generateInscription(Map<String, Object> requestMap) {
        try{
            return fichierInscriptionService.generateInscription(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstants.Not_Print, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  */  
}
