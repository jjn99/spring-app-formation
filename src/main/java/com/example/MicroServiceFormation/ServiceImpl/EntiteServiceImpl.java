package com.example.MicroServiceFormation.ServiceImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Dao.BudgetDao;
import com.example.MicroServiceFormation.Dao.EntiteDao;
import com.example.MicroServiceFormation.Dao.FormationDao;
import com.example.MicroServiceFormation.Dao.SousEntiteDao;
import com.example.MicroServiceFormation.Model.Budget;
import com.example.MicroServiceFormation.Model.Entite;
import com.example.MicroServiceFormation.Model.Formation;
import com.example.MicroServiceFormation.Model.SousEntite;
import com.example.MicroServiceFormation.Service.BudgetService;
import com.example.MicroServiceFormation.Service.EntiteService;
import com.example.MicroServiceFormation.Utils.DfpUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EntiteServiceImpl implements EntiteService{

    @Autowired
    EntiteDao entiteDao;
    
    @Autowired
    BudgetService budgetService;
    
    @Autowired
    BudgetDao budgetDao;
    
        @Autowired
    FormationDao formationDao;
        
        @Autowired
    SousEntiteDao sousDao;
    
           
    private boolean validateEntite(Map<String, String> requestMap,boolean validateId){
        if(requestMap.containsKey("codeinputation")
                && requestMap.containsKey("nom")){
              if(requestMap.containsKey("idEntite") && validateId){
            return true;
            }
            else if(!validateId)
            {
             return true;
            }
        
        }
        return false;
    }
    
    private Entite getEntiteFromMap(Map<String ,String> requestMap){
  
        Entite entite = new Entite();
        entite.setCodeinputation(requestMap.get("codeinputation"));
        entite.setNom(requestMap.get("nom"));
        return entite;
    }
    
    private Entite updateEntiteFromMap(Entite entite ,Map<String, String > requestMap){
       
        entite.setCodeinputation(requestMap.get("codeinputation") == null 
                ? entite.getCodeinputation() : requestMap.get("codeinputation"));
        entite.setNom(requestMap.get("nom") == null ? entite.getNom() : requestMap.get("nom"));
        return entite;
    }
 
    
    @Override
    public ResponseEntity<String> addEntite(Map<String, String> requestMap) {
        try{
            
        if(validateEntite(requestMap,false)){
            Entite entite = entiteDao.getEntiteByCode(requestMap.get("codeinputation"));
            Entite entite2 = entiteDao.getEntiteByNom(requestMap.get("nom"));
            if(entite == null && entite2 == null){
                
                entiteDao.save(getEntiteFromMap(requestMap));
            
                return DfpUtils.getResponseEntity(DfpConstantes.Created, HttpStatus.OK);
        
            }else{
            return DfpUtils.getResponseEntity(DfpConstantes.Invalid_Data, HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<String> updateEntite(Map<String, String> requestMap) {
        try{
            
        if(requestMap.get("idEntite") != null){
            Integer id= Integer.valueOf(requestMap.get("idEntite"));
            Entite entite = entiteDao.getEntiteById(id);
            if(entite != null){
                entiteDao.save(updateEntiteFromMap(entite,requestMap));
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
    public ResponseEntity<List<Entite>> getAllEntite() {
                    try{
            return new ResponseEntity<>(entiteDao.findAll(),HttpStatus.OK);
        }catch(Exception ex){
            
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
    }

    @Override
    public ResponseEntity<Entite> getEntiteById(Integer idEntite) {
                    try{
                return new ResponseEntity<>( entiteDao.getEntiteById(idEntite), HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Entite(), HttpStatus.INTERNAL_SERVER_ERROR);
 

    }
//
//    @Override
//    public ResponseEntity<Entite> getEntiteByCode(String code) {
//                    try{
//                return new ResponseEntity<>( entiteDao.getEntiteByCode(code), HttpStatus.OK);
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return new ResponseEntity<>(new Entite(), HttpStatus.INTERNAL_SERVER_ERROR);
// 
//
//    }
//
//    @Override
//    public ResponseEntity<Entite> getEntiteByNom(String nom) {
//                    try{
//                return new ResponseEntity<>( entiteDao.getEntiteByNom(nom), HttpStatus.OK);
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return new ResponseEntity<>(new Entite(), HttpStatus.INTERNAL_SERVER_ERROR);
// 
//
//    }

    @Override
    public ResponseEntity<String> deleteEntite(Integer idEntite) {
                    try{
               Entite entite = entiteDao.getEntiteById(idEntite);
                if(entite != null){
                    entiteDao.deleteById(idEntite);
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
    public ResponseEntity<List<Formation>> getAllEntiteFormation(Integer idEntite) {
        Entite entite = entiteDao.getEntiteById(idEntite);
        List<Formation> formationList = formationDao.findAll();
        List<Formation> formationEntite = new ArrayList<>();
        
        int i;
        for(i=0;i<formationList.size();i++){
            Formation formation = formationList.get(i);
            if(formation.getListDirection().contains(entite)){
                formationEntite.add(formation);
            }
        }
        return new ResponseEntity<>(formationEntite, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Collection<Formation>> getAllFormationEntite(Integer idEntite) {
          Entite entite = entiteDao.getEntiteById(idEntite);
          List<SousEntite> sousEntite = sousDao.findAll();
        List<Formation> formationList = formationDao.findAll();
        Collection<Formation> formationEntite = new ArrayList<>();
         List<SousEntite> sousEntiteList = new ArrayList<>();
        int j;
        for(j=0;j<sousEntite.size();j++){
            SousEntite sousentite = sousEntite.get(j);
            if(sousentite.getIdEntite().equals(idEntite)){
                sousEntiteList.add(sousentite);
            }
        }
        
        int i;
        for(i=0;i<formationList.size();i++){
            Formation formation = formationList.get(i);
            if(formation.getListDirection().contains(entite)){
                formationEntite.add(formation);
            }
            int t;
            for(t=0;t<sousEntiteList.size();t++){
                if(formation.getListDepartement().contains(sousEntiteList.get(t))){
                    formationEntite.add(formation);
                }
            }
        }
        return new ResponseEntity<>(formationEntite, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> ActivezBudget(Map<String, String> requestMap) {
        
        try{
            Integer idBudget = Integer.parseInt(requestMap.get("idBudget"));
            Budget budget = budgetDao.getBudgetById(idBudget);
       if(budget != null){  
          String statut = "Debuter";
            List<Budget> budgetList = budgetDao.getBudgetByStatut(statut);
            if(!budgetList.isEmpty()){
             int i;
            for(i=0;i<budgetList.size();i++){
                Budget budgetV = budgetList.get(i);
                if(budgetV.getIdEntite().equals(budget.getIdEntite())){
                    budgetV.setStatut("Terminer");
                    budget.setStatut("Debuter");
                    budgetService.updateBudget((Map<String, String>) budgetV);
                    budgetService.updateBudget((Map<String, String>) budget);
                    return DfpUtils.getResponseEntity("Activation Reussit !", HttpStatus.OK);
                }
            }
            }else{
                  budget.setStatut("Debuter");
                    budgetService.updateBudget((Map<String, String>) budget);
                    return DfpUtils.getResponseEntity("Activation Reussit !", HttpStatus.OK);
            }
           
       }else{
              return DfpUtils.getResponseEntity(DfpConstantes.Invalid_Data, HttpStatus.BAD_REQUEST);
       }
               
        }catch(Exception ex){
            ex.printStackTrace();
        }
     return DfpUtils.getResponseEntity(DfpConstantes.Not_Modified, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}