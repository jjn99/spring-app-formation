package com.example.MicroServiceFormation.ServiceImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Dao.LieuDao;
import com.example.MicroServiceFormation.Dao.OrganismeDao;
import com.example.MicroServiceFormation.Model.Lieu;
import com.example.MicroServiceFormation.Model.Organisme;
import com.example.MicroServiceFormation.Service.OrganismeService;
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
public class OrganismeServiceImpl implements OrganismeService{

    @Autowired
    OrganismeDao organismeDao;
    
    @Autowired
    LieuDao lieuDao;
               
    private boolean validateOrganisme(Map<String, String> requestMap,boolean validateId){
        log.info("dans validate ");
        if(requestMap.containsKey("nom") && requestMap.containsKey("domaine")){
              if(requestMap.containsKey("idOrganisme") && validateId){
            return true;
            }
            else if(!validateId)
            {
             return true;
            }
        }
        return false;
    }
    
    private Organisme getOrganismeFromMap(Map<String ,String> requestMap){
       log.info("getOrganismeFromMap");
        Organisme organisme = new Organisme();
        organisme.setContact(requestMap.get("contact"));
        organisme.setDomaine(requestMap.get("domaine"));
        organisme.setLieu(requestMap.get("lieu"));
        organisme.setNom(requestMap.get("nom"));
        return organisme;
    }
    
    private Organisme updateOrganismeFromMap(Organisme organisme ,Map<String, String > requestMap){
        log.info("update Organisme");
        organisme.setContact(requestMap.get("contact") == null ? organisme.getContact(): requestMap.get("contact"));
        organisme.setDomaine(requestMap.get("domaine") == null ? organisme.getDomaine() : requestMap.get("domaine"));
        if(requestMap.get("lieu")!=null){organisme.setLieu(requestMap.get("lieu"));}
        organisme.setNom(requestMap.get("nom") == null ? organisme.getNom() : requestMap.get("nom"));
        return organisme;
    }
 

    
    @Override
    public ResponseEntity<String> addOrganisme(Map<String, String> requestMap) {
                         log.info("Dans ajout d'un Organisme", requestMap);
        try{
            
        if(validateOrganisme(requestMap,false)){
            log.info("validate d'ajout Organisme");
                    Organisme organisme = getOrganismeFromMap(requestMap);
                    organismeDao.save(organisme);
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
    public ResponseEntity<String> updateOrganisme(Map<String, String> requestMap) {
            log.info("Dans Modification d'un Organisme", requestMap);
        try{
            
        if(requestMap.get("idOrganisme") != null){
            Integer id= Integer.valueOf(requestMap.get("idOrganisme"));
            Organisme organisme = organismeDao.getOrganismeById(id);
            if(organisme != null){
                organisme = updateOrganismeFromMap(organisme,requestMap);
                organismeDao.save(organisme);
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
    public ResponseEntity<List<Organisme>> getAllOrganisme() {
                            try{
            return new ResponseEntity<>(organismeDao.findAll(),HttpStatus.OK);
        }catch(Exception ex){
            
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
    }
//
//    @Override
//    public ResponseEntity<List<Organisme>> getOrganismeByDomaine(Map<String,String> requestMap) {
//                            try{
//                                String domaine = requestMap.get("domaine");
//            return new ResponseEntity<>(organismeDao.getOrganismeByDomaine(domaine),HttpStatus.OK);
//        }catch(Exception ex){
//            
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @Override
//    public ResponseEntity<List<Organisme>> getOrganismeByLieu(Map<String,String> requestMap) {
//                            try{
//                                String lieu= requestMap.get("lieu");
//            return new ResponseEntity<>(organismeDao.getOrganismeByLieu(lieu),HttpStatus.OK);
//        }catch(Exception ex){
//            
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//  
//
//    }

    @Override
    public ResponseEntity<Organisme> getOrganismeById(Map<String,String> requestMap) {
                            try{
                                Integer id= Integer.valueOf(requestMap.get("idOrganisme"));
                return new ResponseEntity<>( organismeDao.getOrganismeById(id), HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Organisme(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

//    @Override
//    public ResponseEntity<Organisme> getOrganismeByNom(Map<String,String> requestMap) {
//                            try{
//                                String nom = requestMap.get("nom");
//                return new ResponseEntity<>( organismeDao.getOrganismeByNom(nom), HttpStatus.OK);
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return new ResponseEntity<>(new Organisme(), HttpStatus.INTERNAL_SERVER_ERROR);
//
//    }

    @Override
    public ResponseEntity<String> deleteOrganisme(Integer idOrganisme) {
                            try{
               Organisme organisme = organismeDao.getOrganismeById(idOrganisme);
                if(organisme != null){
                    organismeDao.deleteById(idOrganisme);
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
