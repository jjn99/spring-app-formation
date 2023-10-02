package com.example.MicroServiceFormation.ControllerImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Controller.PlannifierRest;
import com.example.MicroServiceFormation.Model.Plannifier;
import com.example.MicroServiceFormation.Service.PlannifierService;
import com.example.MicroServiceFormation.Utils.DfpUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlannifierRestImpl implements PlannifierRest{

    @Autowired
    PlannifierService plannifierService;
   
    
    @Override
    public ResponseEntity<String> addPlannifier(Map<String, String> requestMap) {
                             try{
              return plannifierService.addPlannifier(requestMap);
               }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  

    }

    @Override
    public ResponseEntity<String> updatePlannifier( Map<String, String> requestMap) {
                             try{
                                return plannifierService.updatePlannifier(requestMap);
                             }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  

    }

    @Override
    public ResponseEntity<List<Plannifier>> getAllPlannifier() {
                             try{
                                return plannifierService.getAllPlannifier();
                             }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  

    }

    @Override
    public ResponseEntity<Plannifier> getPlannifierById(Map<String, String> requestMap) {
                             try{
                                return plannifierService.getPlannifierById(requestMap);
                             }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Plannifier(), HttpStatus.INTERNAL_SERVER_ERROR);
  

    }

    @Override
    public ResponseEntity<String> deletePlannifier(Integer idPlanning) {
                             try{
                                return plannifierService.deletePlannifier(idPlanning);
                             }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
  

    }

}
