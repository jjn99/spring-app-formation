package com.example.MicroServiceFormation.Service;

import com.example.MicroServiceFormation.Model.SousEntite;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface SousEntiteService {
    
    public ResponseEntity<String> addSousEntite(@RequestBody Map<String,String> requestMap);
    public ResponseEntity<String> updateSousEntite(@RequestBody Map<String,String> requestMap);
    public ResponseEntity<List<SousEntite>> getAllSousEntite();
    public ResponseEntity<SousEntite> getSousEntiteById(Map<String, String> requestMap);
    public ResponseEntity<List<SousEntite>> getSousEntiteByIdEntite(Map<String, String> requestMap);
    public ResponseEntity<String> deleteEntite(Integer idBudgetSousEntite);

    public ResponseEntity<String> ActivezBudget(@RequestBody Map<String,String> requestMap);
    
    
}
