package com.example.MicroServiceFormation.ServiceImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Dao.EntiteDao;
import com.example.MicroServiceFormation.Dao.PostulantDao;
import com.example.MicroServiceFormation.Dao.StageDao;
import com.example.MicroServiceFormation.Model.Entite;
import com.example.MicroServiceFormation.Model.Postulant;
import com.example.MicroServiceFormation.Model.Stage;
import com.example.MicroServiceFormation.Service.StageService;
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
public class StageServiceImpl implements StageService{

    @Autowired
    StageDao  stageDao;
    
     @Autowired
    EntiteDao  entiteDao;
     
      @Autowired
    PostulantDao  postulantDao;
      
           private boolean validateStage(Map<String, String> requestMap,boolean validateId){
        
        if(requestMap.containsKey("dateDebut") && requestMap.containsKey("dateFin") &&
                requestMap.containsKey("idPostulant") && requestMap.containsKey("idEntite") ){
            
              if(requestMap.containsKey("idStage") && validateId){
            return true;
            }
            else if(!validateId)
            {
             return true;
            }
        }
        return false;
    }
    
    private Stage getStageFromMap(Map<String ,String> requestMap){
        Stage stage = new Stage();
        stage.setDateDebut(requestMap.get("dateDebut"));
        stage.setDateFin(requestMap.get("dateFin"));
        stage.setStatut("Non-Debuter");
        stage.setIdEntite(Integer.parseInt(requestMap.get("idEntite")));
        return stage;
    }
                 
    private Stage updateStageFromMap(Stage stage ,Map<String, String > requestMap){
        stage.setDateDebut(requestMap.get("dateDebut")  == null ? stage.getDateDebut() : requestMap.get("dateDebut"));
        stage.setDateFin(requestMap.get("dateFin") == null ? stage.getDateFin() : requestMap.get("dateFin"));
        stage.setStatut(requestMap.get("statut") == null ? stage.getStatut(): requestMap.get("statut"));
       if(requestMap.get("idEntite") != null){
            stage.setIdEntite(Integer.valueOf(requestMap.get("idEntite")));
        }
        return stage;
    }  
   
        
    @Override
    public ResponseEntity<String> addStage(Map<String, String> requestMap) {
                    try{
            
        if(validateStage(requestMap,false)){
            Stage stage = getStageFromMap(requestMap);
            Postulant postulant = postulantDao.getPostulantByMail(requestMap.get("mail"));
            
            Entite entite = entiteDao.getEntiteById(
                    Integer.parseInt(requestMap.get("idEntite")));
            if(postulant != null && entite != null){
                    stage.setPostulant(postulant);
                    stage.setEntite(entite);
                stageDao.save(stage);
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
    public ResponseEntity<String> updateStage(Map<String, String> requestMap) {
                      try{
            
        if(requestMap.get("idDemande") != null){
            Integer id = Integer.valueOf(requestMap.get("idDemande"));
            Stage stage = stageDao.getStageById(id);
            if(stage != null){
            Stage stage1 = updateStageFromMap(stage,requestMap);
            Postulant postulant =  postulantDao.getPostulantByMail(requestMap.get("mail"));
            Entite entite = entiteDao.getEntiteById(stage1.getIdEntite());
                    stage1.setPostulant(postulant);
                    stage1.setEntite(entite);
             stageDao.save(stage1);
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
    public ResponseEntity<List<Stage>> getAllStage() {
                                       try{
            return new ResponseEntity<>(stageDao.findAll(),HttpStatus.OK);
        }catch(Exception ex){
            
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
 
    }

//    @Override
//    public ResponseEntity<List<Stage>> getStageByStatus(String statut) {
//                                  try{
//            return new ResponseEntity<>(stageDao.getStageByStatus(statut),HttpStatus.OK);
//        }catch(Exception ex){
//            
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//  }

    @Override
    public ResponseEntity<List<Stage>> getStageByIdPostulant(Integer idPostulant) {
                                          try{
            return new ResponseEntity<>(stageDao.getStageByIdPostulant(idPostulant),HttpStatus.OK);
        }catch(Exception ex){
            
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<List<Stage>> getStageByIdEntite(Integer idEntite) {
                                          try{
            return new ResponseEntity<>(stageDao.getStageByIdEntite(idEntite),HttpStatus.OK);
        }catch(Exception ex){
            
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);

    }
    
}
