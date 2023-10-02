package com.example.MicroServiceFormation.ServiceImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Dao.BudgetDao;
import com.example.MicroServiceFormation.Dao.EntiteDao;
import com.example.MicroServiceFormation.Model.Budget;
import com.example.MicroServiceFormation.Model.Entite;
import com.example.MicroServiceFormation.Service.BudgetService;
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
public class BudgetServiceImpl implements BudgetService{

    @Autowired
    BudgetDao budgetDao;
    
    @Autowired
    EntiteDao entiteDao;
    
      
    private boolean validateBudgetEntite(Map<String, String> requestMap,boolean validateId){
        if(requestMap.containsKey("budgetalloue")){
             if(requestMap.containsKey("idBudget") && validateId){
            return true;
            }
            else if(!validateId)
            {
             return true;
            }
        }
        return false;
    }
    
    private Budget getBudgetFromMap(Map<String ,String> requestMap){
  
        Budget budget= new Budget();
        budget.setBudgetGlobaleAlloue(Integer.valueOf( requestMap.get("budgetalloue")));
        budget.setBudgetGlobaleRestant(Integer.valueOf( requestMap.get("budgetalloue")));
        budget.setBudgetalloue(Integer.valueOf( requestMap.get("budgetalloue")));
        budget.setBudgetrealisation(0);
        budget.setBudgetrestant(Integer.valueOf(requestMap.get("budgetalloue")));
        budget.setDatePlan(requestMap.get("datePlan"));
        budget.setStatut("Non-Debuter");
        budget.setIdEntite(Integer.valueOf(requestMap.get("idEntite")));
        return budget;
    }
    
    private Budget updateBudgetFromMap(Budget budget ,Map<String, String > requestMap){
       
        //test budget Alloué
        if(requestMap.get("budgetalloue") != null){
            budget.setBudgetalloue(Integer.parseInt(requestMap.get("budgetalloue")));
        }
        //test budget Restant
        if(requestMap.get("budgetrestant") != null) {
            budget.setBudgetrestant(budget.getBudgetrestant() - Integer.parseInt(requestMap.get("total")));
        }
        //test budget idEntite
        if(requestMap.get("idEntite") != null) {budget.setIdEntite(Integer.parseInt(requestMap.get("idEntite")));}
        //test budget Global alloué
        if(requestMap.get("budgetGlobaleAlloue")!= null)
        {
            budget.setBudgetGlobaleAlloue( Integer.parseInt(requestMap.get("budgetGlobaleAlloue"))
            );
        }
         
        budget.setDatePlan(requestMap.get("datePlan") == null ? budget.getDatePlan(): requestMap.get("datePlan"));
        budget.setStatut(requestMap.get("statut") == null ? budget.getStatut(): requestMap.get("statut"));
        return budget;
    }
  
    public Budget calculPrix(Budget budget){
        
        //test budget Réalisation
            budget.setBudgetrealisation(budget.getBudgetalloue()
                    - 
                    budget.getBudgetrestant());
         //test taux de réalisation budget
       budget.setTauxrealisation(budget.getBudgetrealisation().floatValue()/budget.getBudgetalloue());
       budget.setTauxRealisationGlobal((budget.getBudgetGlobaleAlloue().floatValue() - budget.getBudgetGlobaleRestant())
               /budget.getBudgetGlobaleAlloue().floatValue());
       return budget;
    }
    
    @Override
    public ResponseEntity<String> addBudget(Map<String, String> requestMap) {
       log.info("Dans ajout d'un Budget", requestMap);
        try{
            
        if(validateBudgetEntite(requestMap,false)){
            Entite entite = entiteDao.getEntiteById(Integer.valueOf(requestMap.get("idEntite")));
            if(entite != null){
                Budget budget = getBudgetFromMap(requestMap);
                budget.setEntite(entite);
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
        log.info("Dans Modification du budget", requestMap);
        try{
        if(requestMap.get("idBudget") != null){
            
            Integer id = Integer.parseInt(requestMap.get("idBudget"));
            Budget budget= budgetDao.getBudgetById(id);
            if(budget != null){
                Budget budget1 = updateBudgetFromMap(budget,requestMap);
                budget1.setEntite(entiteDao.getEntiteById(budget1.getIdEntite()));
                budgetDao.save(calculPrix(budget1));
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
    public ResponseEntity<List<Budget>> getAllBudget() {
        try{
            return new ResponseEntity<>(budgetDao.findAll(),HttpStatus.OK);
        }catch(Exception ex){
            
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Budget> getBudgetById(Integer id) {
        try{
                return new ResponseEntity<>( budgetDao.getBudgetById(id), HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Budget(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Budget>> getBudgetEntiteById(Integer id) {
        try{
            
            return new ResponseEntity<>(budgetDao.getBudgetEntiteById(id), HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Budget> getBudgetByStatut(Integer idEntite) {
        try{
            String statut = "En-Cours-Utilisation";
            List<Budget> budgetList = budgetDao.getBudgetByStatut(statut);
            int i;
            for(i=0;i<budgetList.size();i++){
                Budget budgetV = budgetList.get(i);
                if(budgetV.getIdEntite().equals(idEntite)){
                      return new ResponseEntity<>(budgetV,HttpStatus.OK);
                }
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Budget(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteBudget(Integer idBudget) {
        try{
                Budget budget = budgetDao.getBudgetById(idBudget);
                if(budget != null){
                    budgetDao.deleteById(idBudget);
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
    public ResponseEntity<String> activerBudget(Map<String, String> requestMap) {
       try{
            List<Budget> budgets = budgetDao.findAll();
        if(!budgets.isEmpty()){
            for(int i =0 ; i < budgets.size() ; i++){
                Budget budget = budgets.get(i);
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
    public ResponseEntity<String> ajoutGlobal(Map<String, String> requestMap) {
                              try{
            
        if(requestMap.get("idEntite") != null){
            Integer id = Integer.parseInt(requestMap.get("idEntite"));
            List<Budget> budget= budgetDao.getBudgetEntiteById(id);
            
            for(int i = 0 ; i < budget.size() ; i++){
                if(budget.get(i).getDatePlan().equalsIgnoreCase(requestMap.get("datePlan"))){
                         budget.get(i).setBudgetGlobaleAlloue(budget.get(i).getBudgetrestant() +
                                  Integer.parseInt(requestMap.get("budgetAlloue")));
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

    @Override
    public ResponseEntity<String> retraitGlobal(Map<String, String> requestMap) {
                         try{
            
        if(requestMap.get("idEntite") != null){
            Integer id = Integer.parseInt(requestMap.get("idEntite"));
            List<Budget> budget= budgetDao.getBudgetEntiteById(id);
            
            for(int i = 0 ; i < budget.size() ; i++){
                if(budget.get(i).getDatePlan().equalsIgnoreCase(requestMap.get("datePlan"))){
                         budget.get(i).setBudgetGlobaleAlloue(budget.get(i).getBudgetrestant() -
                                 Integer.parseInt(requestMap.get("ancien")) +
                                  Integer.parseInt(requestMap.get("nouveau")));
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

    @Override
    public ResponseEntity<String> calcul(Map<String, String> requestMap) {
                   try{
            
        if(requestMap.get("idEntite") != null){
            Integer id = Integer.parseInt(requestMap.get("idEntite"));
            List<Budget> budget= budgetDao.getBudgetEntiteById(id);
            
            for(int i = 0 ; i < budget.size() ; i++){
                if(budget.get(i).getDatePlan().equalsIgnoreCase(requestMap.get("datePlan"))){
                         budget.get(i).setBudgetrestant(budget.get(i).getBudgetrestant()- Integer.parseInt(requestMap.get("total")));
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
