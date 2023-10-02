package com.example.MicroServiceFormation.ServiceImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Dao.FraisMissionDao;
import com.example.MicroServiceFormation.Model.FraisMission;
import com.example.MicroServiceFormation.Service.FraisMissionService;
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
public class FraisMissionServiceImpl implements FraisMissionService{

    @Autowired
    FraisMissionDao fraisDao;
           
    private boolean validateEntite(Map<String, String> requestMap,boolean validateId){
        if(requestMap.containsKey("libelle") && requestMap.containsKey("categorie")
                && requestMap.containsKey("localisation") && requestMap.containsKey("dure") 
                && requestMap.containsKey("prix")){
             if(requestMap.containsKey("idFraisMission") && validateId){
            return true;
            }
            else if(!validateId)
            {
             return true;
            }
        }
        return false;
    }
    
    private FraisMission getFraisFromMap(Map<String ,String> requestMap){
  
        FraisMission frais = new FraisMission();
        frais.setCategorie(requestMap.get("categorie"));
        frais.setDure(Integer.valueOf(requestMap.get("dure")));
        frais.setLibelle(requestMap.get("libelle"));
        frais.setPrix(Integer.valueOf(requestMap.get("prix")));
        frais.setLocalisation(requestMap.get("localisation"));
        return frais;
    }
    
    private FraisMission updateFraisFromMap(FraisMission frais ,Map<String, String > requestMap){
       frais.setCategorie(requestMap.get("categorie") == null ? frais.getCategorie() : requestMap.get("categorie"));
       if(requestMap.get("dure")!=null){frais.setDure(Integer.parseInt(requestMap.get("dure")));}
       frais.setLibelle(requestMap.get("libelle") == null ? frais.getLibelle() : requestMap.get("libelle"));
       frais.setLocalisation(requestMap.get("localisation") == null ? frais.getLocalisation() : requestMap.get("localisation"));
       if(requestMap.get("prix")!=null){frais.setPrix(Integer.parseInt(requestMap.get("prix")));}
        return frais;
    }
 
    
    @Override
    public ResponseEntity<String> addFrais(Map<String, String> requestMap) {
           log.info("Dans ajout de frais ", requestMap);
        try{
            
        if(validateEntite(requestMap,false)){
                fraisDao.save(getFraisFromMap(requestMap));
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
    public ResponseEntity<String> updateFrais(Map<String, String> requestMap) {
                            log.info("Dans Modification d'une direction", requestMap);
        try{
            
        if(requestMap.get("idFraisMission") != null){
            Integer id=Integer.valueOf(requestMap.get("idFraisMission"));
              FraisMission frais = fraisDao.getFraisMissionById(id);
            if(frais != null){
                fraisDao.save(updateFraisFromMap(frais,requestMap));
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
    public ResponseEntity<List<FraisMission>> getAllFrais() {
                     try{
            return new ResponseEntity<>(fraisDao.findAll(),HttpStatus.OK);
        }catch(Exception ex){
            
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
   }

//    @Override
//    public ResponseEntity<List<FraisMission>> getFraisByLocalisation(Map<String,String> requestMap) {
//                            try{
//                                String localisation =requestMap.get("localisation");
//            return new ResponseEntity<>(fraisDao.getFraisByLocalisation(localisation),HttpStatus.OK);
//        }catch(Exception ex){
//            
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//  
//    }

    @Override
    public ResponseEntity<FraisMission> getFraisMissionById(Map<String,String> requestMap) {
      try{
          Integer id = Integer.valueOf(requestMap.get("idFraisMission"));
                return new ResponseEntity<>( fraisDao.getFraisMissionById(id), HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new FraisMission(), HttpStatus.INTERNAL_SERVER_ERROR);


    }
//
//    @Override
//    public ResponseEntity<List<FraisMission>> getFraisByDure(Map<String,String> requestMap) {
//                              try{
//                                  Integer dure= Integer.valueOf(requestMap.get("dure"));
//            return new ResponseEntity<>(fraisDao.getFraisByDure(dure),HttpStatus.OK);
//        }catch(Exception ex){
//            
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//  
//    }
//
//    @Override
//    public ResponseEntity<List<FraisMission>> getFraisByLibelle(Map<String,String> requestMap) {
//                              try{
//                                  String libelle = requestMap.get("libelle");
//            return new ResponseEntity<>(fraisDao.getFraisByLibelle(libelle),HttpStatus.OK);
//        }catch(Exception ex){
//            
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//  
//    }
//
//    @Override
//    public ResponseEntity<List<FraisMission>> getFraisByCategorie(Map<String,String> requestMap) {
//                              try{
//                                  String categorie = requestMap.get("categorie");
//            return new ResponseEntity<>(fraisDao.getFraisByCategorie(categorie),HttpStatus.OK);
//        }catch(Exception ex){
//            
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//  
//    }

    @Override
    public ResponseEntity<String> deleteFrais(Integer idFraisMission) {
                            try{
               FraisMission frais = fraisDao.getFraisMissionById(idFraisMission);
                if(frais != null){
                    fraisDao.deleteById(idFraisMission);
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
