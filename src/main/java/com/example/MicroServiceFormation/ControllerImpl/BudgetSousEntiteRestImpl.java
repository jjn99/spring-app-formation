package com.example.MicroServiceFormation.ControllerImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Controller.BudgetSousEntiteRest;
import com.example.MicroServiceFormation.Model.BudgetSousEntite;
import com.example.MicroServiceFormation.Service.BudgetSousEntiteService;
import com.example.MicroServiceFormation.Utils.DfpUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BudgetSousEntiteRestImpl implements BudgetSousEntiteRest{

    
    @Autowired
    BudgetSousEntiteService budgetService;
    
    
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
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);   
    }

    @Override
    public ResponseEntity<List<BudgetSousEntite>> getAllBudget() {
        try{
           return budgetService.getAllBudget();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);    }


    @Override
    public ResponseEntity<BudgetSousEntite> getBudgetById(Integer idBudgetSousEntite) {
         try{
           return budgetService.getBudgetById(idBudgetSousEntite);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new BudgetSousEntite(), HttpStatus.INTERNAL_SERVER_ERROR);    
  }

    @Override
    public ResponseEntity<BudgetSousEntite> getBudgetByStatut(Integer idBudgetSousEntite) {
  
          try{
           return budgetService.getBudgetByStatut(idBudgetSousEntite);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new BudgetSousEntite(), HttpStatus.INTERNAL_SERVER_ERROR);    
    }


    @Override
    public ResponseEntity<String> deleteBudget(Integer idBudgetSousEntite) {
         try{
           return budgetService.deleteBudget(idBudgetSousEntite);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);    }

    @Override
    public ResponseEntity<List<BudgetSousEntite>> getBudgetSousEntiteById(Integer idSousEntite) {
          try{
           return budgetService.getBudgetSousEntiteById(idSousEntite);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);    }

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
    public ResponseEntity<String> calcul(Map<String, String> requestMap) {
                try{
           return budgetService.calcul(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
   
    }

 
}
