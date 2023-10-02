package com.example.MicroServiceFormation.ServiceImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Dao.SalleDao;
import com.example.MicroServiceFormation.Model.Salle;
import com.example.MicroServiceFormation.Service.SalleService;
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
public class SalleServiceImpl implements SalleService{

    @Autowired
    SalleDao salleDao;
    
              
    private boolean validateSalle(Map<String, String> requestMap,boolean validateId){
        
        if(requestMap.containsKey("nom")){
            
              if(requestMap.containsKey("idSalle") && validateId){
                  log.info("true");
            return true;
            }
            else if(!validateId)
            {
                 log.info("true");
             return true;
            }
        }
         log.info("false");
        return false;
    }
    
    private Salle getSalleFromMap(Map<String ,String> requestMap){
         log.info("dans getSalleFromMap");
        Salle salle = new Salle();
        salle.setDescription(requestMap.get("description"));
        salle.setNbrPlace(Integer.valueOf(requestMap.get("nbrPlace")));
        salle.setNom(requestMap.get("nom"));
        salle.setStatut("Disponible");
        salle.setMotif("");
        return salle;
    }
    
    private Salle updateSalleFromMap(Salle salle ,Map<String, String > requestMap){
        salle.setMotif(requestMap.get("motif") == null ? salle.getMotif() : requestMap.get("motif"));
        salle.setNom(requestMap.get("nom") == null ? salle.getNom() : requestMap.get("nom"));
        salle.setDescription(requestMap.get("description") == null ? salle.getDescription() : requestMap.get("description"));
        salle.setNom(requestMap.get("nom") == null ? salle.getNom() : requestMap.get("nom"));
        salle.setStatut(requestMap.get("statut") == null ? salle.getStatut() : requestMap.get("statut"));
        if(requestMap.get("nbrPlace") != null){
            salle.setNbrPlace(Integer.valueOf(requestMap.get("nbrPlace")));
        }
        return salle;
    }
 
    
    @Override
    public ResponseEntity<String> addSalle(Map<String, String> requestMap) {
        try{
            
        if(validateSalle(requestMap,false)){
                salleDao.save(getSalleFromMap(requestMap));
                return DfpUtils.getResponseEntity(DfpConstantes.Created, HttpStatus.OK);
        }else{
            return DfpUtils.getResponseEntity(DfpConstantes.Invalid_Data, HttpStatus.BAD_REQUEST);
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Not_Created , HttpStatus.INTERNAL_SERVER_ERROR);
   
    }

    @Override
    public ResponseEntity<String> updateSalle(Map<String, String> requestMap) {
        try{
            
        if(requestMap.get("idSalle") != null){
            Integer id = Integer.valueOf(requestMap.get("idSalle"));
            Salle salle = salleDao.getSalleById(id);
            if(salle != null){
                salleDao.save(updateSalleFromMap(salle,requestMap));
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
    public ResponseEntity<List<Salle>> getAllSalle() {
                           try{
            return new ResponseEntity<>(salleDao.findAll(),HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
 
    }

//    @Override
//    public ResponseEntity<List<Salle>> getSalleByStatus(Map<String,String> requestMap) {
//                           try{
//                               String statut = requestMap.get("statut");
//            return new ResponseEntity<>(salleDao.getSalleByStatus(statut),HttpStatus.OK);
//        }catch(Exception ex){
//            
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
// 
//    }

    @Override
    public ResponseEntity<Salle> getSalleById(Map<String,String> requestMap) {
                           try{
                               Integer id = Integer.valueOf(requestMap.get("idSalle"));
                return new ResponseEntity<>( salleDao.getSalleById(id), HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Salle(), HttpStatus.INTERNAL_SERVER_ERROR);
 

    }
//
//    @Override
//    public ResponseEntity<Salle> getSalleByNom(Map<String,String> requestMap) {
//                           try{
//                               String nom = requestMap.get("nom");
//                return new ResponseEntity<>( salleDao.getSalleByNom(nom), HttpStatus.OK);
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return new ResponseEntity<>(new Salle(), HttpStatus.INTERNAL_SERVER_ERROR);
// 
//
//    }
//
//    @Override
//    public ResponseEntity<Salle> getSalleByNbrPlace(Map<String,String> requestMap) {
//                           try{
//                               Integer nbrPlace =Integer.valueOf(requestMap.get("nbrPlace"));
//                return new ResponseEntity<>( salleDao.getSalleByNbrPlace(nbrPlace), HttpStatus.OK);
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return new ResponseEntity<>(new Salle(), HttpStatus.INTERNAL_SERVER_ERROR);
// 
//
//    }

    @Override
    public ResponseEntity<String> deleteSalle(Integer idSalle) {
                           try{
               Salle salle = salleDao.getSalleById(idSalle);
                if(salle != null){
                    salleDao.deleteById(idSalle);
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
