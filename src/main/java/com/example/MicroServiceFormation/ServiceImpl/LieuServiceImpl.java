package com.example.MicroServiceFormation.ServiceImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Dao.FraisMissionDao;
import com.example.MicroServiceFormation.Dao.LieuDao;
import com.example.MicroServiceFormation.Model.FraisMission;
import com.example.MicroServiceFormation.Model.Lieu;
import com.example.MicroServiceFormation.Service.LieuService;
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
public class LieuServiceImpl implements LieuService{

    @Autowired
    LieuDao lieuDao;
    
    @Autowired
    FraisMissionDao fraisDao;
    
               
    private boolean validateLieu(Map<String, String> requestMap,boolean validateId){
        if(requestMap.containsKey("pays") && requestMap.containsKey("Ville") ){
              if(requestMap.containsKey("idLieu") && validateId){
            return true;
            }
            else if(!validateId)
            {
             return true;
            }
        }
        return false;
    }
    
    private Lieu getLieuFromMap(Map<String ,String> requestMap){
  
        Lieu lieu = new Lieu();
        lieu.setPays(requestMap.get("pays") == null ? "Burkina Faso" : requestMap.get("pays"));
        lieu.setVille(requestMap.get("ville"));
        return lieu;
    }
    
    private Lieu updateLieuFromMap(Lieu lieu ,Map<String, String > requestMap){
        
        lieu.setPays(requestMap.get("pays") == null ? lieu.getPays(): requestMap.get("pays"));
        lieu.setVille(requestMap.get("ville") == null ? lieu.getVille() : requestMap.get("ville"));
         return lieu;
    }

    
    @Override
    public ResponseEntity<String> addLieu(Map<String, String> requestMap) {
                         log.info("Dans ajout d'une Direction", requestMap);
        try{
            
        if(validateLieu(requestMap,false)){
                Lieu lieu = getLieuFromMap(requestMap);
                lieuDao.save(lieu);
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
    public ResponseEntity<String> updateLieu( Map<String, String> requestMap) {
       log.info("Dans Modification d'un Lieu", requestMap);
        try{
            
        if(requestMap.get("idLieu") != null){
            Integer id = Integer.valueOf(requestMap.get("idLieu"));
            Lieu lieu = lieuDao.getLieuById(id);
            if(lieu != null){
                lieu = updateLieuFromMap(lieu,requestMap);
                 lieuDao.save(lieu);
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
    public ResponseEntity<List<Lieu>> getAllLieu() {
                            try{
            return new ResponseEntity<>(lieuDao.findAll(),HttpStatus.OK);
        }catch(Exception ex){
            
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<Lieu> getLieuById(Map<String,String> requestMap) {
                            try{
                                Integer id = Integer.valueOf(requestMap.get("idLieu"));
                return new ResponseEntity<>( lieuDao.getLieuById(id), HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Lieu(), HttpStatus.INTERNAL_SERVER_ERROR);

    }
//
//    @Override
//    public ResponseEntity<Lieu> getLieuByPays(Map<String,String> requestMap) {
//                            try{
//                                String pays = requestMap.get("pays");
//                return new ResponseEntity<>( lieuDao.getLieuByPays(pays), HttpStatus.OK);
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return new ResponseEntity<>(new Lieu(), HttpStatus.INTERNAL_SERVER_ERROR);
//
//    }
//
//     @Override
//    public ResponseEntity<Lieu> getLieuByVille(Map<String,String> requestMap) {
//                            try{
//                                String ville = requestMap.get("ville");
//                return new ResponseEntity<>( lieuDao.getLieuByVille(ville), HttpStatus.OK);
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return new ResponseEntity<>(new Lieu(), HttpStatus.INTERNAL_SERVER_ERROR);
//
//    }
    
    @Override
    public ResponseEntity<String> deleteLieu(Integer idLieu) {
                            try{
               Lieu lieu = lieuDao.getLieuById(idLieu);
                if(lieu != null){
                    lieuDao.deleteById(idLieu);
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
