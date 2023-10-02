package com.example.MicroServiceFormation.ServiceImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Dao.CoutDao;
import com.example.MicroServiceFormation.Dao.FormationDao;
import com.example.MicroServiceFormation.Model.Cout;
import com.example.MicroServiceFormation.Model.Formation;
import com.example.MicroServiceFormation.Service.CoutService;
import com.example.MicroServiceFormation.Service.FormationService;
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
public class CoutServiceImpl implements CoutService{

    @Autowired
    CoutDao coutDao;
    
    @Autowired
    FormationDao formationDao;
    
    @Autowired
    FormationService formationService;
    
        
    private boolean validateCout(Map<String, String> requestMap,boolean validateId){
        if(requestMap.containsKey("designation") && requestMap.containsKey("coutUnitaire") 
                && requestMap.containsKey("quantite")){
              if(requestMap.containsKey("idCout") && validateId){
            return true;
            }
            else if(!validateId)
            {
             return true;
            }
        }
        return false;
    }
    
    private Cout getCoutFromMap(Map<String ,String> requestMap){
  
        Cout cout = new Cout();
        cout.setCoutUnitaire(Integer.valueOf(requestMap.get("coutUnitaire")));
        cout.setDesignation(requestMap.get("designation"));
        cout.setQuantite(Integer.valueOf(requestMap.get("quantite")));
        return cout;
    }
    
    private Cout updateCoutFromMap(Cout cout ,Map<String, String > requestMap){
     
                if(requestMap.get("coutUnitaire") != null){
            cout.setCoutUnitaire(Integer.parseInt(requestMap.get("coutUnitaire")));
        }
               if(requestMap.get("quantite") != null){
            cout.setQuantite(Integer.parseInt(requestMap.get("quantite")));
        }
        cout.setDesignation(requestMap.get("designation") == null ? cout.getDesignation() : requestMap.get("designation"));
          return cout;
    }
  
    @Override
    public ResponseEntity<String> addCout(Map<String, String> requestMap) {
        try{
            
        if(validateCout(requestMap,false)){
            Formation formation = formationDao.getByIdFormation(Integer.parseInt(requestMap.get("idFormation")));
            if(formation != null){
                Cout cout = getCoutFromMap(requestMap);
                cout.setFormation(formation);
                cout.setNbrParticipant(
                formation.getListAgents().size()
                );
                cout.setMontant(
                cout.getCoutUnitaire() * cout.getNbrParticipant() * cout.getQuantite()
                );
                coutDao.save(cout);
                return DfpUtils.getResponseEntity(DfpConstantes.Created, HttpStatus.OK);
            }else{
                return DfpUtils.getResponseEntity(DfpConstantes.Bad_Identifiant_Formation, HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<String> updateCout(Map<String, String> requestMap) {
        try{
            
        if(requestMap.get("idCout") != null){
            Integer id = Integer.valueOf(requestMap.get("idCout"));
            Cout cout = coutDao.getCoutById(id);
            if(cout != null){
                Cout couta = updateCoutFromMap(cout,requestMap);
                Formation formation = formationDao.getByNumeroInscription(requestMap.get("numeroInscription"));
                couta.setFormation(formation);
                 couta.setNbrParticipant(
                formation.getListAgents().size()
                );
                couta.setMontant(
                couta.getCoutUnitaire() * cout.getNbrParticipant() * cout.getQuantite()
                );
                coutDao.save(couta);
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
    public ResponseEntity<List<Cout>> getAllCout() {
              try{
            return new ResponseEntity<>(coutDao.findAll(),HttpStatus.OK);
        }catch(Exception ex){
            
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
    }

    @Override
    public ResponseEntity<Cout> getCoutById(Integer idCout) {
              try{
                return new ResponseEntity<>( coutDao.getCoutById(idCout), HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Cout(), HttpStatus.INTERNAL_SERVER_ERROR);
 
    }

    @Override
    public  ResponseEntity<List<Cout>> getEntiteByFormationId(Integer numeroInscription) {
              try{
                return new ResponseEntity<>( coutDao.getEntiteByFormationId(numeroInscription), HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
 
    }

    @Override
    public ResponseEntity<String> deleteCout(Integer idCout) {
                try{
               Cout cout = coutDao.getCoutById(idCout);
                if(cout != null){
                    coutDao.deleteById(idCout);
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
