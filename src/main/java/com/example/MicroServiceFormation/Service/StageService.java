package com.example.MicroServiceFormation.Service;

import com.example.MicroServiceFormation.Model.Stage;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;

public interface StageService {

    public ResponseEntity<String> addStage(Map<String, String> requestMap);

    public ResponseEntity<String> updateStage(Map<String, String> requestMap);

    public ResponseEntity<List<Stage>> getAllStage();
    
    public ResponseEntity<List<Stage>> getStageByIdPostulant(Integer idPostulant);

    public ResponseEntity<List<Stage>> getStageByIdEntite(Integer idEntite);
    
}
