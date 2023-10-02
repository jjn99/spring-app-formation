package com.example.MicroServiceFormation.Service;

import com.example.MicroServiceFormation.Model.Salle;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface SalleService {
    
    public ResponseEntity<String> addSalle(@RequestBody Map<String,String> requestMap);
    public ResponseEntity<String> updateSalle(@RequestBody Map<String,String> requestMap);
    public ResponseEntity<List<Salle>> getAllSalle();
    public ResponseEntity<Salle> getSalleById(Map<String, String> requestMap);
    public ResponseEntity<String> deleteSalle(Integer idSalle);
    
}
