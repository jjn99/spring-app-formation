
package com.example.MicroServiceFormation.ControllerImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Controller.PostulantRest;
import com.example.MicroServiceFormation.Model.Postulant;
import com.example.MicroServiceFormation.Model.Salle;
import com.example.MicroServiceFormation.Service.PostulantService;
import com.example.MicroServiceFormation.Utils.DfpUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostulantRestImpl implements PostulantRest{

        @Autowired
    PostulantService postulantSevice;
    
    @Override
    public ResponseEntity<String> addPostulant(Map<String, String> requestMap) {
        try{
           return postulantSevice.addPostulant(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
      }

    @Override
    public ResponseEntity<String> updatePostulant(Map<String, String> requestMap) {
              try{
           return postulantSevice.updatePostulant(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
    
    }

    @Override
    public ResponseEntity<List<Postulant>> getAllPostulant() {
        try{
            return postulantSevice.getAllPostulant();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
     }


    @Override
    public ResponseEntity<Postulant> getPostulantByMail(String mail) {
              try{
            return postulantSevice.getPostulantByMail(mail);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Postulant(), HttpStatus.INTERNAL_SERVER_ERROR);
   
    }
    
}
