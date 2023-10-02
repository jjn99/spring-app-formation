package com.example.MicroServiceFormation.Service;

import com.example.MicroServiceFormation.Model.Cout;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface CoutService {
    
          
    public ResponseEntity<String> addCout(@RequestBody Map<String, String> requestMap);
    public ResponseEntity<String> updateCout(@RequestBody Map<String, String> requestMap);
    public ResponseEntity<List<Cout>> getAllCout();
    public ResponseEntity<Cout> getCoutById(Integer idCout);
    public  ResponseEntity<List<Cout>> getEntiteByFormationId(Integer numeroInscription);
    public ResponseEntity<String> deleteCout(Integer idCout);
 
    
}
