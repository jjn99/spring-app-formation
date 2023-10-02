package com.example.MicroServiceFormation.ServiceImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Dao.DemandeDao;
import com.example.MicroServiceFormation.Dao.PostulantDao;
import com.example.MicroServiceFormation.Model.Demande;
import com.example.MicroServiceFormation.Model.Postulant;
import com.example.MicroServiceFormation.Service.DemandeService;
import com.example.MicroServiceFormation.Utils.DfpUtils;
import com.example.MicroServiceFormation.Utils.EmailUtils;
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
public class DemandeServiceImpl implements DemandeService {

     @Autowired
    DemandeDao demandeDao;
     
      @Autowired
    PostulantDao postulantDao;
      
      @Autowired
      EmailUtils emailUtils;
     
       private boolean validateDemande(Map<String, String> requestMap,boolean validateId){
        
        if(requestMap.containsKey("numeroDemande") && requestMap.containsKey("dateDebut") &&
                requestMap.containsKey("dateFin") && requestMap.containsKey("objet") &&
                requestMap.containsKey("ecole")  && requestMap.containsKey("mail") ){
            
              if(requestMap.containsKey("idDemande") && validateId){
            return true;
            }
            else if(!validateId)
            {
             return true;
            }
        }
        return false;
    }
     
        private Demande getDemandeFromMap(Map<String ,String> requestMap){
        Demande demande = new Demande();
        demande.setDateDebut(requestMap.get("dateDebut"));
        demande.setDateFin(requestMap.get("dateFin"));
        demande.setEcole(requestMap.get("ecole"));
        demande.setNumeroDemande(requestMap.get("numeroDemande"));
        demande.setObjet(requestMap.get("objet"));
        demande.setStatut("Non-Traiter");
        return demande;
    }
       
        
        private Demande updateDemandeFromMap(Demande demande ,Map<String, String > requestMap){
        demande.setDateDebut(requestMap.get("dateDebut")  == null ? demande.getDateDebut() : requestMap.get("dateDebut"));
        demande.setDateFin(requestMap.get("dateFin") == null ? demande.getDateFin() : requestMap.get("dateFin"));
        demande.setObjet(requestMap.get("objet") == null ? demande.getObjet() : requestMap.get("objet"));
        demande.setStatut(requestMap.get("statut") == null ? demande.getStatut(): requestMap.get("statut"));
        demande.setStatutTraitement(requestMap.get("statutTraitement") == null ?
             demande.getStatutTraitement(): requestMap.get("statutTraitement"));
        demande.setMotif(requestMap.get("motif") == null ? demande.getMotif(): requestMap.get("motif"));
        return demande;
    }
        
    @Override
    public ResponseEntity<String> addDemande(Map<String, String> requestMap) {
  
       try{
            
        if(validateDemande(requestMap,false)){
            Demande demande1 = demandeDao.getDemandeByNumeroDemande(requestMap.get("numeroDemande"));
            if(demande1 == null){
            Demande demande = getDemandeFromMap(requestMap);
            Postulant postulant = postulantDao.getPostulantByMail(requestMap.get("mail"));
               demande.setPostulant(postulant);
                demandeDao.save(demande);
                return DfpUtils.getResponseEntity(DfpConstantes.Created, HttpStatus.OK);
            }else{
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
    public ResponseEntity<String> updateDemande(Map<String, String> requestMap) {
        
                try{
            
        if(requestMap.get("idDemande") != null){
            Integer id = Integer.valueOf(requestMap.get("idDemande"));
            Demande demande = demandeDao.getDemandeById(id);
            if(demande != null){
                Demande demande1 = updateDemandeFromMap(demande,requestMap);
                Postulant postulant = postulantDao.getPostulantByMail(requestMap.get("mail"));
                demande1.setPostulant(postulant);
                demandeDao.save(demande1);
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
    public ResponseEntity<List<Demande>> getAllDemande() {
                                  try{
            return new ResponseEntity<>(demandeDao.findAll(),HttpStatus.OK);
        }catch(Exception ex){
            
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
 
    
    }
//
//    @Override
//    public ResponseEntity<List<Demande>> geDemandeByStatus(String statut) {
//                                     try{
//            return new ResponseEntity<>(demandeDao.getDemandeByStatus(statut),HttpStatus.OK);
//        }catch(Exception ex){
//            
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
// 
//    
//    }

    @Override
    public ResponseEntity<List<Demande>> getDemandeByIdPostulant(Integer idPostulant) {
                                            try{
            return new ResponseEntity<>(demandeDao.getDemandeByIdPostulant(idPostulant),HttpStatus.OK);
        }catch(Exception ex){
            
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
 
    }

    @Override
    public void sendSimpleMessage(Map<String, String> requestMap) {
        try{
            String to = requestMap.get("to");
            String subject = requestMap.get("subject");
            String text = requestMap.get("text");
            emailUtils.sendSimpleMessage(to, subject, text);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public ResponseEntity<Demande> getDemandeByNumeroDemande(String numeroDemande) {
     try{
            return new ResponseEntity<>(demandeDao.getDemandeByNumeroDemande(numeroDemande),HttpStatus.OK);
        }catch(Exception ex){
            
        }
        return new ResponseEntity<>(new Demande(), HttpStatus.INTERNAL_SERVER_ERROR);
 
    }
    
}
