package com.example.MicroServiceFormation.ServiceImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Dao.FormationDao;
import com.example.MicroServiceFormation.Dao.SessionDao;
import com.example.MicroServiceFormation.Model.Formation;
import com.example.MicroServiceFormation.Model.Session;
import com.example.MicroServiceFormation.Service.FormationService;
import com.example.MicroServiceFormation.Service.SessionService;
import com.example.MicroServiceFormation.Utils.DfpUtils;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SessionServiceImpl implements SessionService{

    @Autowired
    SessionDao sessionDao;
    
    @Autowired
    FormationDao formationDao;
    
    @Autowired
    FormationService formationService;
    
       private boolean validateSession(Map<String, String> requestMap,boolean validateId){
        if(requestMap.containsKey("dateSessionDebut") && requestMap.containsKey("dateSessionFin")
                && requestMap.containsKey("idFormation") ){
             if(requestMap.containsKey("idSession") && validateId){
            return true;
            }
            else if(!validateId)
            {
             return true;
            }
        }
        return false;
    }
 
    
    private Session getSessionFromMap(Map<String ,String> requestMap){
  
        Session session = new Session();
        session.setDateDepart(requestMap.get("dateDepart"));
        session.setDateRetour(requestMap.get("dateRetour"));
        session.setDateSessionDebut(requestMap.get("dateSessionDebut"));
        session.setDateSessionFin(requestMap.get("dateSessionFin"));
        return session;
    }
    
    private Session updateSessionFromMap(Session session ,Map<String, String > requestMap){
        session.setDateDepart(requestMap.get("dateDepart") == null 
                ? session.getDateDepart() : requestMap.get("dateDepart"));
        session.setDateRetour(requestMap.get("dateRetour") == null ?
                 session.getDateRetour() : requestMap.get("dateRetour"));
        session.setDateSessionDebut(requestMap.get("dateSessionDebut") == null ?
                session.getDateSessionDebut() : requestMap.get("dateSessionDebut"));
        session.setDateSessionFin(requestMap.get("dateSessionFin") == null ?
                session.getDateSessionFin() : requestMap.get("dateSessionFin"));
         return session;
    }
 
 
    
    @Override
    public ResponseEntity<String> addSession(Map<String, String> requestMap) {
        try{
            
        if(validateSession(requestMap,false)){
            Formation formation = formationDao.getByIdFormation(Integer.valueOf(requestMap.get("idFormation")));
            if(formation != null){
                Session session = getSessionFromMap(requestMap);
                session.setFormation(formation);
                  sessionDao.save(session);
                return DfpUtils.getResponseEntity(DfpConstantes.Created, HttpStatus.OK);
       
            }
            else{
                return DfpUtils.getResponseEntity(DfpConstantes.Bad_Identifiant, HttpStatus.MULTI_STATUS);
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
    public ResponseEntity<String> updateSession(Map<String, String> requestMap) {
        try{
        if(requestMap.get("idSession") != null){
            Integer id= Integer.valueOf(requestMap.get("idSession"));
            Session session = sessionDao.getSessionById(id);
            if(session != null){
                Formation formation = formationDao.getByIdFormation(Integer.valueOf(requestMap.get("idFormation")));
                if(formation != null){
                    session = updateSessionFromMap(session, requestMap);
                    session.setFormation(formation);
                    sessionDao.save(session);
                    return DfpUtils.getResponseEntity(DfpConstantes.Modified, HttpStatus.OK);
       }
            else{
                return DfpUtils.getResponseEntity(DfpConstantes.Bad_Identifiant, HttpStatus.MULTI_STATUS);
            } }else{
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
    public ResponseEntity<List<Session>> getAllSession() {
                           try{
            return new ResponseEntity<>(sessionDao.findAll(),HttpStatus.OK);
        }catch(Exception ex){
            
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
 
    }

    @Override
    public ResponseEntity<List<Session>> getSessionByIdFormation(Map<String,String> requestMap) {
                           try{
                               Integer id = Integer.valueOf(requestMap.get("numeroInscription"));
            return new ResponseEntity<>(sessionDao.getSessionByIdFormation(id),HttpStatus.OK);
        }catch(Exception ex){
            
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
 
    }

    @Override
    public ResponseEntity<Session> getSessionById(Map<String,String> requestMap) {
                           try{
                               Integer id = Integer.valueOf(requestMap.get("idSession"));
            return new ResponseEntity<>(sessionDao.getSessionById(id),HttpStatus.OK);
        }catch(Exception ex){
            
        }
        return new ResponseEntity<>(new Session(), HttpStatus.INTERNAL_SERVER_ERROR);
 
    }

    @Override
    public ResponseEntity<String> deleteSession(Integer idSession) {
                           try{
               Session session = sessionDao.getSessionById(idSession);
                if(session != null){
                    sessionDao.deleteById(idSession);
                    return DfpUtils.getResponseEntity(DfpConstantes.Delete, HttpStatus.OK);
                }else{
                    DfpUtils.getResponseEntity(DfpConstantes.Not_Existing, HttpStatus.BAD_REQUEST);
                }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Not_Delete, HttpStatus.INTERNAL_SERVER_ERROR);

 
    }

//    @Override
//    public ResponseEntity<List<Session>> findSessionByIdFormation(Integer idFormation) {
//             try{
//                 
//            return new ResponseEntity<>(sessionDao.findSessionByIdFormation(idFormation),HttpStatus.OK);
//        }catch(Exception ex){
//            
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
// 
//    }
//    
}
