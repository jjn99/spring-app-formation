package com.example.MicroServiceFormation.ServiceImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Dao.PostulantDao;
import com.example.MicroServiceFormation.Model.Postulant;
import com.example.MicroServiceFormation.Service.PostulantService;
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
public class PostulantServiceImpl implements PostulantService {

    @Autowired
    PostulantDao  postulantDao;
    
    
           private boolean validatePostulant(Map<String, String> requestMap,boolean validateId){
        
        if(requestMap.containsKey("nom") && requestMap.containsKey("prenom") &&
                requestMap.containsKey("sexe") && requestMap.containsKey("cnib") &&
                requestMap.containsKey("phone") && requestMap.containsKey("mail")&&
                requestMap.containsKey("ville")&& requestMap.containsKey("niveau")&&
                requestMap.containsKey("diplome")){
            
              if(requestMap.containsKey("idPostulant") && validateId){
            return true;
            }
            else if(!validateId)
            {
             return true;
            }
        }
        return false;
    }
     
        private Postulant getPostulantFromMap(Map<String ,String> requestMap){
        Postulant postulant = new Postulant();
        postulant.setAge(Integer.parseInt(requestMap.get("age")));
        postulant.setCnib(requestMap.get("cnib"));
        postulant.setDiplome(requestMap.get("diplome"));
        postulant.setMail(requestMap.get("mail"));
        postulant.setNiveau(requestMap.get("niveau"));
        postulant.setNom(requestMap.get("nom"));
        postulant.setPhone(requestMap.get("phone"));
        postulant.setPrenom(requestMap.get("prenom"));
        postulant.setSexe(requestMap.get("sexe"));
        postulant.setVille(requestMap.get("ville"));
        postulant.setStatut("Postulant");
        return postulant;
    }
        
        
           private Postulant updatePostulantFromMap(Postulant postulant ,Map<String, String > requestMap){
        postulant.setCnib(requestMap.get("cnib") == null ? postulant.getCnib() : requestMap.get("cnib"));
        postulant.setDiplome(requestMap.get("diplome") == null ? postulant.getDiplome() : requestMap.get("diplome"));
        postulant.setMail(requestMap.get("mail") == null ? postulant.getMail() : requestMap.get("mail"));
        postulant.setNiveau(requestMap.get("niveau") == null ? postulant.getNiveau() : requestMap.get("niveau"));
        postulant.setNom(requestMap.get("nom") == null ? postulant.getNom() : requestMap.get("nom"));
        postulant.setPhone(requestMap.get("phone") == null ? postulant.getPhone() : requestMap.get("phone"));
        postulant.setPrenom(requestMap.get("prenom") == null ? postulant.getPrenom() : requestMap.get("prenom"));
        postulant.setSexe(requestMap.get("sexe") == null ? postulant.getSexe() : requestMap.get("sexe"));
        postulant.setVille(requestMap.get("ville") == null ? postulant.getVille() : requestMap.get("ville"));
        postulant.setStatut(requestMap.get("statut") == null ? postulant.getStatut(): requestMap.get("statut"));
        return postulant;
    }
 
    
    
    @Override
    public ResponseEntity<String> addPostulant(Map<String, String> requestMap) {
              try{
            
        if(validatePostulant(requestMap,false)){
            Postulant postulant = postulantDao.getPostulantByMail(requestMap.get("mail"));
            if(postulant == null){
                postulantDao.save(getPostulantFromMap(requestMap));
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
    public ResponseEntity<String> updatePostulant(Map<String, String> requestMap) {
               try{
            
        if(requestMap.get("idPostulant") != null){
            Integer id = Integer.valueOf(requestMap.get("idPostulant"));
            Postulant postulant = postulantDao.getPostulantById(id);
            if(postulant != null){
                postulantDao.save(updatePostulantFromMap(postulant,requestMap));
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
    public ResponseEntity<List<Postulant>> getAllPostulant() {
                                try{
            return new ResponseEntity<>(postulantDao.findAll(),HttpStatus.OK);
        }catch(Exception ex){
            
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
 
  
    }

//    @Override
//    public ResponseEntity<List<Postulant>> getPostulantByStatus(String statut) {
//                                try{
//            return new ResponseEntity<>(postulantDao.getPostulantByStatus(statut),HttpStatus.OK);
//        }catch(Exception ex){
//            
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
// 
//   
//    }

    @Override
    public ResponseEntity<Postulant> getPostulantByMail(String mail) {
      try{
            return new ResponseEntity<>(postulantDao.getPostulantByMail(mail),HttpStatus.OK);
        }catch(Exception ex){
            
        }
        return new ResponseEntity<>(new Postulant(), HttpStatus.INTERNAL_SERVER_ERROR);
 
    }
    
}
