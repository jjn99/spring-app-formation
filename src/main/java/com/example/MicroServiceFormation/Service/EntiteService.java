package com.example.MicroServiceFormation.Service;

import com.example.MicroServiceFormation.Model.Entite;
import com.example.MicroServiceFormation.Model.Formation;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface EntiteService {
    
       
    public ResponseEntity<String> addEntite(@RequestBody Map<String,String> requestMap);
    public ResponseEntity<String> updateEntite(@RequestBody Map<String,String> requestMap);
    public ResponseEntity<List<Entite>> getAllEntite();
    public ResponseEntity<Entite> getEntiteById(Integer idEntite);
    public ResponseEntity<String> deleteEntite(Integer idEntite);

    public ResponseEntity<List<Formation>> getAllEntiteFormation(Integer idEntite);

    public ResponseEntity<Collection<Formation>> getAllFormationEntite(Integer idEntite);

    public ResponseEntity<String> ActivezBudget(@RequestBody Map<String,String> requestMap);
    
    
}
