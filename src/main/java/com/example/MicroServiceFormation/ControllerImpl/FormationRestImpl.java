package com.example.MicroServiceFormation.ControllerImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Controller.FormationRest;
import com.example.MicroServiceFormation.Model.Agents;
import com.example.MicroServiceFormation.Model.Formation;
import com.example.MicroServiceFormation.Service.FormationService;
import com.example.MicroServiceFormation.Utils.DfpUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FormationRestImpl implements FormationRest{

    @Autowired
    FormationService formationService;
   
    
    @Override
    public ResponseEntity<String> addFormation(Map<String, String> requestMap) {
         try{
          return formationService.addFormation(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  
    }

    @Override
    public ResponseEntity<String> updateFormation(Map<String, String> requestMap) {
             try{
          return formationService.updateFormation(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  
    }

    @Override
    public ResponseEntity<List<Formation>> getAllFormation() {
             try{
          return formationService.getAllFormation();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
    }


    @Override
    public ResponseEntity<Formation> getByNumeroInscription(String numeroInscription) {
             try{
          return formationService.getByNumeroInscription(numeroInscription);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Formation(), HttpStatus.INTERNAL_SERVER_ERROR);
  
    }

    @Override
    public ResponseEntity<String> deleteFormation(Integer idFormation) {
             try{
          return formationService.deleteFormation(idFormation);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  
    }

    @Override
    public ResponseEntity<String> addAgentToFormation(Map<String, String> requestMap) {
                   try{
          return formationService.addAgentToFormation(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  
  
    }

    @Override
    public ResponseEntity<String> removeAgentToFormation(Map<String, String> requestMap) {
              try{
          return formationService.removeAgentToFormation(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  
    }

    @Override
    public ResponseEntity<String> addEntiteToFormation(Map<String, String> requestMap) {
             try{
          return formationService.addEntiteToFormation(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  
    }

    @Override
    public ResponseEntity<String> removeEntiteToFormation(Map<String, String> requestMap) {
             try{
          return formationService.removeEntiteToFormation(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  
    }

    @Override
    public ResponseEntity<String> addSousEntiteToFormation(Map<String, String> requestMap) {
             try{
          return formationService.addSousEntiteToFormation(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  
    }

    @Override
    public ResponseEntity<String> removeSousEntiteToFormation(Map<String, String> requestMap) {
             try{
          return formationService.removeSousEntiteToFormation(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  
    }

    @Override
    public ResponseEntity<List<Agents>> getAgentDisponisble(Date dateDebut) {
         try{
          return formationService.getAgentDisponisble(dateDebut);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Formation>> getFormationByAgent(Integer idPersonnel) {
                  try{
          return formationService.getFormationByAgent(idPersonnel);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
   
    }

}
