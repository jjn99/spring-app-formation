package com.example.MicroServiceFormation.ControllerImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Controller.PersonnelRest;
import com.example.MicroServiceFormation.Model.Agents;
import com.example.MicroServiceFormation.Model.Formation;
import com.example.MicroServiceFormation.Service.PersonnelService;
import com.example.MicroServiceFormation.Utils.DfpUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonnelRestImpl implements PersonnelRest{

    @Autowired
    PersonnelService agentService;
   
    
    @Override
    public ResponseEntity<String> addPersonnel(Map<String, String> requestMap) {
                             try{
             return agentService.addPersonnel(requestMap);
               }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  

    }

    @Override
    public ResponseEntity<String> updatePersonnel(Map<String, String> requestMap) {
                             try{
                                return agentService.updatePersonnel(requestMap);
                             }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  

    }

    @Override
    public ResponseEntity<List<Agents>> getAllPersonnel() {
                             try{
                                return agentService.getAllPersonnel();
                             }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  

    }

    @Override
    public ResponseEntity<Agents> getPersonnelByMatricule(String matricule) {
                             try{
                                return agentService.getPersonnelByMatricule(matricule);
                             }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Agents(), HttpStatus.INTERNAL_SERVER_ERROR);
  

    }

    @Override
    public ResponseEntity<Agents> getPersonnelById(Integer idPersonnel) {
                             try{
                                return agentService.getPersonnelById(idPersonnel);
                             }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity(new Agents(), HttpStatus.INTERNAL_SERVER_ERROR);
  

    }

    @Override
    public ResponseEntity<String> deletePersonnel(Integer idPersonnel) {
                             try{
                                return agentService.deletePersonnel(idPersonnel);
                             }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  

    }

}
