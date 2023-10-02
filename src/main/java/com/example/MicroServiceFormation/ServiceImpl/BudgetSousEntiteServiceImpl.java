package com.example.MicroServiceFormation.ServiceImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Dao.BudgetSousEntiteDao;
import com.example.MicroServiceFormation.Dao.SousEntiteDao;
import com.example.MicroServiceFormation.Model.BudgetSousEntite;
import com.example.MicroServiceFormation.Model.SousEntite;
import com.example.MicroServiceFormation.Service.BudgetSousEntiteService;
import com.example.MicroServiceFormation.Utils.DfpUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BudgetSousEntiteServiceImpl implements BudgetSousEntiteService{

    @Autowired
    BudgetSousEntiteDao budgetDao;
    
    @Autowired
    SousEntiteDao entiteDao;
    
        private boolean validateBudgetEntite(Map<String, String> requestMap,boolean validateId){
            
        if(requestMap.containsKey("budgetalloue")){
             if(requestMap.containsKey("idBudgetSousEntite") && validateId){
            return true;
            }
            else if(!validateId)
            {
             return true;
            }
        }
        return false;
    }
        
           private BudgetSousEntite getBudgetFromMap(Map<String ,String> requestMap){
  
        BudgetSousEntite budget= new BudgetSousEntite();
        budget.setBudgetAlloue(Integer.valueOf(requestMap.get("budgetalloue")));
        budget.setBudgetRestant(Integer.valueOf(requestMap.get("budgetalloue")));
        budget.setBudgetRealisation(0);
        budget.setDatePlan(requestMap.get("datePlan"));
        budget.setStatut("Non-Debuter");
        budget.setIdSousEntite(Integer.valueOf(requestMap.get("idSousEntite")));
        return budget;
    }
    
              private BudgetSousEntite updateBudgetFromMap(BudgetSousEntite budget ,Map<String, String > requestMap){
        
    
        //test budget Alloué
        if(requestMap.get("budgetalloue") != null)
        { budget.setBudgetAlloue(Integer.parseInt(requestMap.get("budgetalloue")));}
        //test budget Restant
        if(requestMap.get("budgetrestant") != null) {budget.setBudgetRestant(Integer.parseInt(requestMap.get("budgetrestant")));}
        //test budget idEntite
        if(requestMap.get("idSousEntite") != null) {budget.setIdSousEntite(Integer.parseInt(requestMap.get("idSousEntite")));}

        budget.setDatePlan(requestMap.get("datePlan") == null ? budget.getDatePlan(): requestMap.get("datePlan"));
        budget.setStatut(requestMap.get("statut") == null ? budget.getStatut(): requestMap.get("statut"));
        return budget;
    }
              
                 public BudgetSousEntite calculPrix(BudgetSousEntite budget){
        
        //test budget Réalisation
            budget.setBudgetRealisation(budget.getBudgetAlloue()
                    - 
                    budget.getBudgetRestant());
         //test taux de réalisation budget
       budget.setTauxRealisation(budget.getBudgetRealisation().floatValue()/budget.getBudgetAlloue());
       return budget;
    }
           
    @Override
    public ResponseEntity<String> addBudget(Map<String, String> requestMap) {
           log.info("Dans ajout d'un Budget SousEntite", requestMap);
        try{
            
        if(validateBudgetEntite(requestMap,false)){
            SousEntite entite = entiteDao.getSousEntiteById(Integer.valueOf(requestMap.get("idSousEntite")));
            if(entite != null){
                BudgetSousEntite budget = getBudgetFromMap(requestMap);
                budget.setSousEntite(entite);
                budgetDao.save(calculPrix(budget));
                return DfpUtils.getResponseEntity(DfpConstantes.Created, HttpStatus.OK);
            }else{
                return DfpUtils.getResponseEntity(DfpConstantes.Bad_Identifiant_Entite, HttpStatus.BAD_REQUEST);
            }
        }else{
            return DfpUtils.getResponseEntity(DfpConstantes.Invalid_Data, HttpStatus.BAD_REQUEST);
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Not_Created , HttpStatus.INTERNAL_SERVER_ERROR);
                
   
    }

    @Override
    public ResponseEntity<String> updateBudget(Map<String, String> requestMap) {
            log.info("Dans Modification du budget SousEntite", requestMap);
        try{
            
        if(requestMap.get("idBudgetSousEntite") != null){
            Integer id = Integer.parseInt(requestMap.get("idBudgetSousEntite"));
            BudgetSousEntite budget= budgetDao.getBudgetById(id);
            if(budget != null){
                BudgetSousEntite budgeta = updateBudgetFromMap(budget,requestMap);
                budgeta.setSousEntite(entiteDao.getSousEntiteById(budgeta.getIdSousEntite()));
                budgetDao.save(calculPrix(budgeta));
                return DfpUtils.getResponseEntity(DfpConstantes.Modified, HttpStatus.OK);
            }else{
                return DfpUtils.getResponseEntity(DfpConstantes.Not_Existing, HttpStatus.BAD_REQUEST);
            }
        }else{
            return DfpUtils.getResponseEntity(DfpConstantes.Invalid_Data, HttpStatus.BAD_REQUEST);
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Not_Modified , HttpStatus.INTERNAL_SERVER_ERROR);
        
 
    }

    @Override
    public ResponseEntity<List<BudgetSousEntite>> getAllBudget() {
        try{
            return new ResponseEntity<>(budgetDao.findAll(),HttpStatus.OK);
        }catch(Exception ex){
            
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
    }

    @Override
    public ResponseEntity<BudgetSousEntite> getBudgetById(Integer idBudgetSousEntite) {
            try{
                return new ResponseEntity<>( budgetDao.getBudgetById(idBudgetSousEntite), HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new BudgetSousEntite(), HttpStatus.INTERNAL_SERVER_ERROR);
   
    }

    @Override
    public ResponseEntity<BudgetSousEntite> getBudgetByStatut(Integer idSousEntite) {
      try{
            String statut = "Debuter";
            List<BudgetSousEntite> budgetList = budgetDao.getBudgetByStatut(statut);
            int i;
            for(i=0;i<budgetList.size();i++){
                BudgetSousEntite budgetV = budgetList.get(i);
                if(budgetV.getIdSousEntite().equals(idSousEntite)){
                      return new ResponseEntity<>(budgetV,HttpStatus.OK);
                }
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new BudgetSousEntite(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteBudget(Integer idBudgetSousEntite) {
       try{
           BudgetSousEntite budget = budgetDao.getBudgetById(idBudgetSousEntite);
                if(budget != null){
                    budgetDao.deleteById(idBudgetSousEntite);
                    return DfpUtils.getResponseEntity(DfpConstantes.Delete, HttpStatus.OK);
                }else{
                    DfpUtils.getResponseEntity(DfpConstantes.Not_Existing, HttpStatus.BAD_REQUEST);
                }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Not_Delete, HttpStatus.INTERNAL_SERVER_ERROR);
    
    }

    @Override
    public ResponseEntity<List<BudgetSousEntite>> getBudgetSousEntiteById(Integer idSousEntite) {
         try{
            return new ResponseEntity<>(budgetDao.getBudgetSousEntiteById(idSousEntite), HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> activerBudget(Map<String, String> requestMap) {
         try{
                   List<BudgetSousEntite> budgets = budgetDao.findAll();
        if(!budgets.isEmpty()){
            for(int i =0 ; i < budgets.size() ; i++){
                BudgetSousEntite budget = budgets.get(i);
                if(budget.getStatut().equalsIgnoreCase("Debuter") &&
                        budget.getDatePlan().equalsIgnoreCase(requestMap.get("datePlan"))){
                    budget.setStatut("Terminer");
                    budgetDao.save(budget);
                }else if(budget.getStatut().equalsIgnoreCase("Non-Debuter") &&
                        budget.getDatePlan().equalsIgnoreCase(requestMap.get("datePlan"))){
                    budget.setStatut("Debuter");
                    budgetDao.save(budget);
                }
            }
             return DfpUtils.getResponseEntity(DfpConstantes.Modified, HttpStatus.OK);
               
        }
        else{
             return DfpUtils.getResponseEntity(DfpConstantes.Not_Modified, HttpStatus.INTERNAL_SERVER_ERROR);
  
        }
         }catch(Exception ex){
             ex.printStackTrace();
         }
     return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  
 
    }

    @Override
    public ResponseEntity<String> calcul(Map<String, String> requestMap) {
             try{
            
        if(requestMap.get("idSousEntite") != null){
            Integer id = Integer.parseInt(requestMap.get("idSousEntite"));
            List<BudgetSousEntite> budget= budgetDao.getBudgetSousEntiteById(id);
            
            for(int i = 0 ; i < budget.size() ; i++){
                if(budget.get(i).getDatePlan().equalsIgnoreCase(requestMap.get("datePlan"))){
                         budget.get(i).setBudgetRestant(budget.get(i).getBudgetRestant() - Integer.parseInt(requestMap.get("total")));
                budgetDao.save(calculPrix(budget.get(i)));
                return DfpUtils.getResponseEntity(DfpConstantes.Modified, HttpStatus.OK);
             }  else{
                return DfpUtils.getResponseEntity(DfpConstantes.Not_Existing, HttpStatus.BAD_REQUEST);
            }
        }
        }else{
            return DfpUtils.getResponseEntity(DfpConstantes.Invalid_Data, HttpStatus.BAD_REQUEST);
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Not_Modified , HttpStatus.INTERNAL_SERVER_ERROR);
      
    }
    
}
