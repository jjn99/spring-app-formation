package com.example.MicroServiceFormation.Service;

import com.example.MicroServiceFormation.Model.Agents;
import com.example.MicroServiceFormation.Model.Formation;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface PersonnelService {
    
    public ResponseEntity<String> addPersonnel(@RequestBody Map<String,String> requestMap);
    public ResponseEntity<String> updatePersonnel( @RequestBody Map<String,String> requestMap);
    public ResponseEntity<List<Agents>> getAllPersonnel();
    public ResponseEntity<Agents> getPersonnelByMatricule(String matricule);
    public ResponseEntity<Agents> getPersonnelById(Integer idPersonnel);
    public ResponseEntity<String> deletePersonnel(Integer idPersonnel);
    public ResponseEntity<List<Formation>> getFormationsByAgent(Integer idPersonnel);

    
}
