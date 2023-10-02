package com.example.MicroServiceFormation.Service;

import com.example.MicroServiceFormation.Model.Lieu;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface LieuService {
    
    public ResponseEntity<String> addLieu(@RequestBody Map<String,String> requestMap);
    public ResponseEntity<String> updateLieu( @RequestBody Map<String,String> requestMap);
    public ResponseEntity<List<Lieu>> getAllLieu();
    public ResponseEntity<Lieu> getLieuById(Map<String, String> requestMap);
    public ResponseEntity<String> deleteLieu(Integer idLieu);
 
    
    
}
