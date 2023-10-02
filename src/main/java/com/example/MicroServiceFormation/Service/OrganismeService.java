package com.example.MicroServiceFormation.Service;

import com.example.MicroServiceFormation.Model.Organisme;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface OrganismeService {
    
    public ResponseEntity<String> addOrganisme(@RequestBody Map<String,String> requestMap);
    
    public ResponseEntity<String> updateOrganisme(@RequestBody Map<String,String> requestMap);
    public ResponseEntity<List<Organisme>> getAllOrganisme();
    public ResponseEntity<Organisme> getOrganismeById(Map<String, String> requestMap);
    public ResponseEntity<String> deleteOrganisme(Integer idOrganisme);
    
}
