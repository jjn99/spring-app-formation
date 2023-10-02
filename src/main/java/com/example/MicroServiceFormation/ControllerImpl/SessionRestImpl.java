package com.example.MicroServiceFormation.ControllerImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Controller.SessionRest;
import com.example.MicroServiceFormation.Model.Session;
import com.example.MicroServiceFormation.Service.SessionService;
import com.example.MicroServiceFormation.Utils.DfpUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionRestImpl implements SessionRest{

    @Autowired
    SessionService sessionService;
   
    
    @Override
    public ResponseEntity<String> addSession(Map<String, String> requestMap) {
                             try{
                                return sessionService.addSession(requestMap);
                             }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  
 
    }

    @Override
    public ResponseEntity<String> updateSession(Map<String, String> requestMap) {
                             try{
                                return sessionService.updateSession(requestMap);
                             }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  
 
    }

    @Override
    public ResponseEntity<List<Session>> getAllSession() {
                             try{
                                return sessionService.getAllSession();
                             }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
 
    }
//
//    @Override
//    public ResponseEntity<List<Session>>findSessionByIdFormation(Map<String, String> requestMap) {
//                             try{
//                                return sessionService.getSessionByIdFormation(requestMap);
//                             }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//  
// 
//    }

    @Override
    public ResponseEntity<Session> getSessionById(Map<String, String> requestMap) {
                             try{
                                return sessionService.getSessionById(requestMap);
                             }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Session(), HttpStatus.INTERNAL_SERVER_ERROR);
  
 
    }

    @Override
    public ResponseEntity<String> deleteSession(Integer idSession) {
                             try{
                                return sessionService.deleteSession(idSession);
                             }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  
 
    }

//    @Override
//    public ResponseEntity<> findSessionByIdFormation(Map<String, String> requestMap) {
//                                try{
//                                return sessionService.findSessionByIdFormation(requestMap);
//                             }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return new ResponseEntity<>
//  
//    }

//    @Override
//    public ResponseEntity<List<Session>> findSessionByIdFormation(Integer idFormation) {
//                                try{
//                              return sessionService.findSessionByIdFormation(idFormation);
//                             }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//  
//    }
//
}
