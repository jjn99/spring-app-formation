
package com.example.MicroServiceFormation.ControllerImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Controller.DemandeRest;
import com.example.MicroServiceFormation.Model.Demande;
import com.example.MicroServiceFormation.Service.DemandeService;
import com.example.MicroServiceFormation.Utils.DfpUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemandeRestImpl implements DemandeRest{

    
    @Autowired
    DemandeService demandeService;
    
    @Override
    public ResponseEntity<String> addDemande(Map<String, String> requestMap) {
              try{
           return demandeService.addDemande(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
    
    }

    @Override
    public ResponseEntity<String> updateDemande(Map<String, String> requestMap) {
              try{
           return demandeService.updateDemande(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
    
    }

    @Override
    public ResponseEntity<List<Demande>> getAllDemande() {
        try{
            return demandeService.getAllDemande();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
     }

    @Override
    public ResponseEntity<List<Demande>> getDemandeByIdPostulant(Integer idPostulant) {
               try{
            return demandeService.getDemandeByIdPostulant(idPostulant);
        }catch(Exception ex){
            ex.printStackTrace();
        }
       return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public void sendSimpleMessage(Map<String, String> requestMap) {
             try{
            demandeService.sendSimpleMessage(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public ResponseEntity<Demande> getDemandeByNumeroDemande(String numeroDemande) {
                     try{
            return demandeService.getDemandeByNumeroDemande(numeroDemande);
        }catch(Exception ex){
            ex.printStackTrace();
        }
       return new ResponseEntity<>(new Demande(), HttpStatus.INTERNAL_SERVER_ERROR);
  
    }

 
    
}
