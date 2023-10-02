
package com.example.MicroServiceFormation.ControllerImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Controller.StageRest;
import com.example.MicroServiceFormation.Model.Stage;
import com.example.MicroServiceFormation.Service.StageService;
import com.example.MicroServiceFormation.Utils.DfpUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StageRestImpl implements StageRest{

    @Autowired
    StageService stageService;
    
    @Override
    public ResponseEntity<String> addStage(Map<String, String> requestMap) {
         try{
           return stageService.addStage(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
     }

    @Override
    public ResponseEntity<String> updateStage(Map<String, String> requestMap) {
         try{
           return stageService.updateStage(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
     }

    @Override
    public ResponseEntity<List<Stage>> getAllStage() {
        try{
            return stageService.getAllStage();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
     }

    @Override
    public ResponseEntity<List<Stage>> getStageByIdPostulant(Integer idPostulant) {
       try{
            return stageService.getStageByIdPostulant(idPostulant);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
     }

    @Override
    public ResponseEntity<List<Stage>> getStageByIdEntite(Integer idEntite) {
        try{
            return stageService.getStageByIdEntite(idEntite);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
