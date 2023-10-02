package com.example.MicroServiceFormation.ServiceImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Dao.FormationDao;
import com.example.MicroServiceFormation.Dao.PlannifierDao;
import com.example.MicroServiceFormation.Dao.SalleDao;
import com.example.MicroServiceFormation.Model.Formation;
import com.example.MicroServiceFormation.Model.Plannifier;
import com.example.MicroServiceFormation.Model.Salle;
import com.example.MicroServiceFormation.Service.PlannifierService;
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
public class PlannifierServiceImpl implements PlannifierService{

    @Autowired
    PlannifierDao plannifierDao;
    
    @Autowired
    FormationDao formationDao;
    
    @Autowired
    SalleDao salleDao;
    
               
    private boolean validatePlanning(Map<String, String> requestMap,boolean validateId){
        if(requestMap.containsKey("evenement") && requestMap.containsKey("idSalle")
                && requestMap.containsKey("dateDebut") && requestMap.containsKey("dateFin")){
              if(requestMap.containsKey("idPlannifier") && validateId){
            return true;
            }
            else if(!validateId)
            {
             return true;
            }
        }
        return false;
    }
    
    private Plannifier getPlanningFromMap(Map<String ,String> requestMap){
  
        Plannifier planning = new Plannifier();
        planning.setDateDebut(requestMap.get("dateDebut"));
        planning.setDateFin(requestMap.get("dateFin"));
        planning.setEvenement(requestMap.get("evenement"));
        planning.setColor(requestMap.get("color"));
        if(requestMap.get("idFormation") != null){
        planning.setIdFormation(Integer.valueOf(requestMap.get("idFormation")));
        }
        planning.setIdsalle(Integer.valueOf(requestMap.get("idSalle")));
        
        return planning;
    }
    
    private Plannifier updatePlannifierFromMap(Plannifier planning ,Map<String, String > requestMap){
        planning.setDateDebut(requestMap.get("dateDebut") == null 
                ? planning.getDateDebut() : requestMap.get("dateDebut"));
        planning.setDateFin(requestMap.get("dateDebut") == null 
                ? planning.getDateFin(): requestMap.get("dateDebut"));
        if(requestMap.get("numeroInscription")!=null){planning.setIdFormation(Integer.parseInt(requestMap.get("numeroInscription")));}
        if(requestMap.get("idSalle")!=null){planning.setIdsalle(Integer.parseInt(requestMap.get("idSalle")));}
        planning.setEvenement(requestMap.get("evenement") == null ? planning.getEvenement() : requestMap.get("evenement"));
        return planning;
    }
 

    
    @Override
    public ResponseEntity<String> addPlannifier(Map<String, String> requestMap) {
        try{
            
        if(validatePlanning(requestMap,false)){
            if(requestMap.get("idFormation") != null ){
                Formation formation = formationDao.getByIdFormation(Integer.valueOf(requestMap.get("idFormation")));
                Salle salle = salleDao.getSalleById(Integer.valueOf(requestMap.get("idSalle")));
                Plannifier planning = getPlanningFromMap(requestMap);
                planning.setFormation(formation);
                planning.setEvenement("Formation : "+formation.getTitre());
                planning.setSalle(salle);
                plannifierDao.save(planning);
                return DfpUtils.getResponseEntity(DfpConstantes.Created, HttpStatus.OK); 
            }
            else{
                    Salle salle = salleDao.getSalleById(Integer.valueOf(requestMap.get("idSalle")));
                    Plannifier planning = getPlanningFromMap(requestMap);
                    planning.setSalle(salle);
                plannifierDao.save(planning);
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
    public ResponseEntity<String> updatePlannifier(Map<String, String> requestMap) {
        try{
            
        if(requestMap.get("idPlannifier") != null){
            Integer id=Integer.valueOf(requestMap.get("idPlannifier"));
            Plannifier planning = plannifierDao.getPlannifierById(id);
            if(planning != null){
                planning = updatePlannifierFromMap(planning ,requestMap);
                if(planning.getIdFormation() == null){
                       Salle salle = salleDao.getSalleById(Integer.valueOf(requestMap.get("idSalle")));
                        planning.setSalle(salle);
                         plannifierDao.save(planning);
                 return DfpUtils.getResponseEntity(DfpConstantes.Modified, HttpStatus.OK);
                }
                else{
                     Formation formation = formationDao.getByIdFormation(Integer.parseInt(requestMap.get("idFormation")));
                     Salle salle = salleDao.getSalleById(Integer.valueOf(requestMap.get("idSalle")));
                     planning.setFormation(formation);
                     planning.setSalle(salle);
                     plannifierDao.save(planning);
                return DfpUtils.getResponseEntity(DfpConstantes.Modified, HttpStatus.OK);
                }
               
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
    public ResponseEntity<List<Plannifier>> getAllPlannifier() {
                            try{
            return new ResponseEntity<>(plannifierDao.findAll(),HttpStatus.OK);
        }catch(Exception ex){
            
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

//    @Override
//    public ResponseEntity<Plannifier> getPlannifierByEvenement(Map<String,String> requestMap) {
//                            try{
//                                String evenement = requestMap.get("evenement");
//                return new ResponseEntity<>( plannifierDao.getPlannifierByEvenement(evenement), HttpStatus.OK);
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return new ResponseEntity<>(new Plannifier(), HttpStatus.INTERNAL_SERVER_ERROR);
// 
//
//    }

    @Override
    public ResponseEntity<Plannifier> getPlannifierById(Map<String,String> requestMap) {
                            try{
                                Integer id = Integer.valueOf(requestMap.get("idPlannifier"));
                return new ResponseEntity<>( plannifierDao.getPlannifierById(id), HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Plannifier(), HttpStatus.INTERNAL_SERVER_ERROR);
 

    }

    @Override
    public ResponseEntity<List<Plannifier>> getPlannifierBySalle(Map<String,String> requestMap) {
                            try{
                                Integer id = Integer.valueOf(requestMap.get("idSalle"));
                return new ResponseEntity<>( plannifierDao.getPlannifierBySalle(id), HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
 

    }

    @Override
    public ResponseEntity<List<Plannifier>> getPlannifierByFormation(Map<String,String> requestMap) {
                            try{
                                Integer id = Integer.valueOf(requestMap.get("numeroInscription"));
                return new ResponseEntity<>( plannifierDao.getPlannifierByFormation(id), HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
 

    }

    @Override
    public ResponseEntity<String> deletePlannifier(Integer idPlannifier) {
                         try{
               Plannifier planning = plannifierDao.getPlannifierById(idPlannifier);
                if(planning != null){
                    plannifierDao.deleteById(idPlannifier);
                    return DfpUtils.getResponseEntity(DfpConstantes.Delete, HttpStatus.OK);
                }else{
                    DfpUtils.getResponseEntity(DfpConstantes.Not_Existing, HttpStatus.BAD_REQUEST);
                }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Not_Delete, HttpStatus.INTERNAL_SERVER_ERROR);

  
    }
    
}
