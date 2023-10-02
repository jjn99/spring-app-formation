package com.example.MicroServiceFormation.ControllerImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Controller.BudgetRest;
import com.example.MicroServiceFormation.Model.Budget;
import com.example.MicroServiceFormation.Service.BudgetService;
import com.example.MicroServiceFormation.Utils.DfpUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BudgetRestImpl implements BudgetRest{

    @Autowired
    BudgetService budgetService;
    
    @Override
    public ResponseEntity<String> addBudget(Map<String, String> requestMap) {
        try{
            return budgetService.addBudget(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateBudget(Map<String, String> requestMap) {
        try{
            return budgetService.updateBudget(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);    }

    @Override
    public ResponseEntity<List<Budget>> getAllBudget() {
        try{
           return budgetService.getAllBudget();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);    }

    @Override
    public ResponseEntity<Budget> getBudgetById(Integer idBudget) {
        try{
           return budgetService.getBudgetById(idBudget);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>( new Budget(), HttpStatus.INTERNAL_SERVER_ERROR);    }

    @Override
    public ResponseEntity<List<Budget>> getBudgetEntiteById(Integer idEntite) {
        try{
            budgetService.getBudgetEntiteById(idEntite);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);    }

    @Override
    public ResponseEntity<Budget> getBudgetByStatut(Integer idEntite) {
        try{
           return budgetService.getBudgetByStatut(idEntite);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Budget(), HttpStatus.INTERNAL_SERVER_ERROR);    }

    @Override
    public ResponseEntity<String> deleteBudget(Integer idBudget) {
        try{
           return budgetService.deleteBudget(idBudget);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR); 
    }

    @Override
    public ResponseEntity<String> activerBudget(Map<String, String> requestMap) {
              try{
            return budgetService.activerBudget(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
   
    }

    @Override
    public ResponseEntity<String> ajoutGlobal(Map<String, String> requestMap) {
        try{
            return budgetService.ajoutGlobal(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
     }

    @Override
    public ResponseEntity<String> retraitGlobal(Map<String, String> requestMap) {
        try{
            return budgetService.retraitGlobal(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
     }

    @Override
    public ResponseEntity<String> calcul(Map<String, String> requestMap) {
       try{
            return budgetService.calcul(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
      }


    
}
