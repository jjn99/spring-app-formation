package com.example.MicroServiceFormation.Service;

import com.example.MicroServiceFormation.Model.Plannifier;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface PlannifierService {
    
    public ResponseEntity<String> addPlannifier(@RequestBody Map<String,String> requestMap);
    public ResponseEntity<String> updatePlannifier(@RequestBody Map<String,String> requestMap);
    public ResponseEntity<List<Plannifier>> getAllPlannifier();
    public ResponseEntity<Plannifier> getPlannifierById(Map<String, String> requestMap);
    public ResponseEntity<List<Plannifier>> getPlannifierBySalle(Map<String, String> requestMap);
    public ResponseEntity<List<Plannifier>> getPlannifierByFormation(Map<String, String> requestMap);
    public ResponseEntity<String> deletePlannifier(Integer idPlanning);
    
}
