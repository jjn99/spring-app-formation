package com.example.MicroServiceFormation.ServiceImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Dao.BudgetSousEntiteDao;
import com.example.MicroServiceFormation.Dao.EntiteDao;
import com.example.MicroServiceFormation.Dao.SousEntiteDao;
import com.example.MicroServiceFormation.Model.BudgetSousEntite;
import com.example.MicroServiceFormation.Model.Entite;
import com.example.MicroServiceFormation.Model.SousEntite;
import com.example.MicroServiceFormation.Service.BudgetSousEntiteService;
import com.example.MicroServiceFormation.Service.SousEntiteService;
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
public class SousEntiteServiceImpl implements SousEntiteService{

     @Autowired
    BudgetSousEntiteService entiteService;
    
    @Autowired
    SousEntiteDao sentiteDao;
    
    @Autowired
    EntiteDao entiteDao;
    
     @Autowired
    BudgetSousEntiteDao budgetDao;
    
            
    private boolean validateSEntite(Map<String, String> requestMap,boolean validateId){
        if(requestMap.containsKey("codeinputation") && requestMap.containsKey("nom")
                && requestMap.containsKey("idEntite")){
             if(requestMap.containsKey("idSousEntite") && validateId){
            return true;
            }
            else if(!validateId)
            {
             return true;
            }
        }
        return false;
    }
    
    private SousEntite getSEntiteFromMap(Map<String ,String> requestMap){
  
        SousEntite entite = new SousEntite();
        entite.setCodeinputation(requestMap.get("codeinputation"));
        entite.setNom(requestMap.get("nom"));
        entite.setIdEntite(Integer.valueOf(requestMap.get("idEntite")));
        return entite;
    }
    
    private SousEntite updateSEntiteFromMap(SousEntite entite ,Map<String, String > requestMap){
        if(requestMap.get("idEntite")!=null){entite.setIdEntite(Integer.parseInt(requestMap.get("idEntite")));}
        entite.setCodeinputation(requestMap.get("codeinputation") == null 
                ? entite.getCodeinputation() : requestMap.get("codeinputation"));
        entite.setNom(requestMap.get("nom") == null ? entite.getNom() : requestMap.get("nom"));
        return entite;
    }
 
   
    
    @Override
    public ResponseEntity<String> addSousEntite(Map<String, String> requestMap) {
            log.info("Dans ajout d'une SousEntite", requestMap);
        try{
            
        if(validateSEntite(requestMap,false)){
                Entite entite = entiteDao.getEntiteById(Integer.valueOf(requestMap.get("idEntite")));
                if(entite != null){
                    SousEntite sentite = getSEntiteFromMap(requestMap);
                    sentite.setEntite(entite);
                    sentiteDao.save(sentite);
                    return DfpUtils.getResponseEntity(DfpConstantes.Created, HttpStatus.OK);
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
    public ResponseEntity<String> updateSousEntite(Map<String, String> requestMap) {
                         log.info("Dans Modification d'une sous entite", requestMap);
        try{
            
        if(requestMap.get("idSousEntite") != null){
            Integer id= Integer.valueOf(requestMap.get("idSousEntite"));
            SousEntite sentite = sentiteDao.getSousEntiteById(id);
            if(sentite != null){
                sentite = updateSEntiteFromMap(sentite,requestMap);
                sentite.setEntite(entiteDao.getEntiteById(sentite.getIdEntite()));
                sentiteDao.save(sentite);
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
    public ResponseEntity<List<SousEntite>> getAllSousEntite() {
                         try{
            return new ResponseEntity<>(sentiteDao.findAll(),HttpStatus.OK);
        }catch(Exception ex){
            
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
   
    }

    @Override
    public ResponseEntity<SousEntite> getSousEntiteById(Map<String,String> requestMap) {
                         try{
                             Integer id = Integer.valueOf(requestMap.get("idSousEntite"));
                return new ResponseEntity<>( sentiteDao.getSousEntiteById(id), HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new SousEntite(), HttpStatus.INTERNAL_SERVER_ERROR);
 
    }

    @Override
    public ResponseEntity<List<SousEntite>> getSousEntiteByIdEntite(Map<String,String> requestMap) {
                                 try{
                                     Integer id= Integer.valueOf(requestMap.get("idEntite"));
            return new ResponseEntity<>(sentiteDao.getSousEntiteByIdEntite(id),HttpStatus.OK);
        }catch(Exception ex){
            
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);

    }
//
//    @Override
//    public ResponseEntity<SousEntite> getSousEntiteByCode(Map<String,String> requestMap) {
//                         try{
//                             String code = requestMap.get("codeinputation");
//                return new ResponseEntity<>( sentiteDao.getSousEntiteByCode(code), HttpStatus.OK);
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return new ResponseEntity<>(new SousEntite(), HttpStatus.INTERNAL_SERVER_ERROR);
// 
//    }
//
//    @Override
//    public ResponseEntity<SousEntite> getSousEntiteByNom(Map<String,String> requestMap) {
//                         try{ 
//                             String nom = requestMap.get("nom");
//                return new ResponseEntity<>( sentiteDao.getSousEntiteByNom(nom), HttpStatus.OK);
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return new ResponseEntity<>(new SousEntite(), HttpStatus.INTERNAL_SERVER_ERROR);
// 
//    }

    @Override
    public ResponseEntity<String> deleteEntite(Integer idBudgetSouEntite) {
                         try{
                
                  SousEntite sentite = sentiteDao.getSousEntiteById(idBudgetSouEntite);
                if(sentite != null){
                    sentiteDao.deleteById(idBudgetSouEntite);
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
    public ResponseEntity<String> ActivezBudget(Map<String, String> requestMap) {
        try{
            Integer idBudgetSousEntite = Integer.parseInt(requestMap.get("idBudgetSousEntite"));
                  BudgetSousEntite budget = budgetDao.getBudgetById(idBudgetSousEntite);
       if(budget != null){  
          String statut = "Debuter";
            List<BudgetSousEntite> budgetList = budgetDao.getBudgetByStatut(statut);
            if(budgetList != null){
             int i;
            for(i=0;i<budgetList.size();i++){
                BudgetSousEntite budgetV = budgetList.get(i);
                if(budgetV.getIdSousEntite().equals(budget.getIdSousEntite())){
                    budgetV.setStatut("Terminer");
                    budget.setStatut("Debuter");
                    entiteService.updateBudget((Map<String, String>) budgetV);
                    entiteService.updateBudget((Map<String, String>) budget);
                    return DfpUtils.getResponseEntity("Activation Reussit !", HttpStatus.OK);
                }
            }
            }else{
                  budget.setStatut("Debuter");
                    entiteService.updateBudget((Map<String, String>) budget);
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
