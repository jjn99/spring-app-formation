package com.example.MicroServiceFormation.Service;

import com.example.MicroServiceFormation.Model.Agents;
import com.example.MicroServiceFormation.Model.Formation;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface FormationService {
    
           
    public ResponseEntity<String> addFormation(@RequestBody Map<String,String> requestMap);
    
    public ResponseEntity<String> updateFormation(@RequestBody Map<String,String> requestMap);
    
    public ResponseEntity<List<Formation>> getAllFormation();
    
    public ResponseEntity<String> deleteFormation(Integer idFormation);

    public ResponseEntity<String> addAgentToFormation(@RequestBody Map<String, String> requestMap);

    public ResponseEntity<String> removeAgentToFormation(@RequestBody Map<String, String> requestMap);

    public ResponseEntity<String> addEntiteToFormation(@RequestBody Map<String, String> requestMap);

    public ResponseEntity<String> removeEntiteToFormation(@RequestBody Map<String, String> requestMap);

    public ResponseEntity<String> addSousEntiteToFormation(@RequestBody Map<String, String> requestMap);

    public ResponseEntity<String> removeSousEntiteToFormation(@RequestBody Map<String, String> requestMap);

    public ResponseEntity<List<Formation>> getFormationByIdEntite(Integer idEntite);

    public ResponseEntity<List<Formation>> getFormationByIdSousEntite(Integer idSousEntite);

    public ResponseEntity<List<Agents>> getAgentDisponisble(Date dateDebut);

    public ResponseEntity<Formation> getByNumeroInscription(String numeroInscription);
    
    public ResponseEntity<Formation> getByIdFormation(Integer idFormation);

    public ResponseEntity<List<Formation>> getFormationByOrganisme(Integer idOrganisme);

    public ResponseEntity<List<Formation>> getFormationByAgent(Integer idPersonnel);

    
}
