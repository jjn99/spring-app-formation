package com.example.MicroServiceFormation.Service;

import com.example.MicroServiceFormation.Model.FraisMission;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface FraisMissionService {
    
    public ResponseEntity<String> addFrais(@RequestBody Map<String,String> requestMap);
    public ResponseEntity<String> updateFrais(@RequestBody Map<String,String> requestMap);
    public ResponseEntity<List<FraisMission>> getAllFrais();
    public ResponseEntity<FraisMission> getFraisMissionById(Map<String, String> requestMap);
    public ResponseEntity<String> deleteFrais(Integer idFraisMission);
    
}
