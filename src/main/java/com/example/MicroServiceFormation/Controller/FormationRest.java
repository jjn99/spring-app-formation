package com.example.MicroServiceFormation.Controller;

import com.example.MicroServiceFormation.Model.Agents;
import com.example.MicroServiceFormation.Model.Formation;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/Formation")
public interface FormationRest {
    
           
    @PostMapping("/addFormation")
    public ResponseEntity<String> addFormation(@RequestBody Map<String,String> requestMap);
    
    @PostMapping("/updateFormation")
    public ResponseEntity<String> updateFormation(@RequestBody Map<String,String> requestMap);
    
    @PostMapping(path="/addAgentToFormation")
    ResponseEntity<String> addAgentToFormation(@RequestBody Map<String,String> requestMap);
    
    @PostMapping(path="/removeAgentToFormation")
    ResponseEntity<String> removeAgentToFormation(@RequestBody Map<String,String> requestMap);
    
    @PostMapping(path="/addEntiteToFormation")
    ResponseEntity<String> addEntiteToFormation(@RequestBody Map<String,String> requestMap);
    
    @PostMapping(path="/removeEntiteToFormation")
    ResponseEntity<String> removeEntiteToFormation(@RequestBody Map<String,String> requestMap);
    
    @PostMapping(path="/addSousEntiteToFormation")
    ResponseEntity<String> addSousEntiteToFormation(@RequestBody Map<String,String> requestMap);
    
    @PostMapping(path="/removeSousEntiteToFormation")
    ResponseEntity<String> removeSousEntiteToFormation(@RequestBody Map<String,String> requestMap);
    
    @GetMapping("/getAllFormation")
    public ResponseEntity<List<Formation>> getAllFormation();
    
    @GetMapping("/getAgentDisponisble/{dateDebut}")
    public ResponseEntity<List<Agents>> getAgentDisponisble(@PathVariable @DateTimeFormat(pattern ="yyyy-MM-dd") Date dateDebut);
 
    @GetMapping("/getByNumeroInscription/{numeroInscription}")
    public ResponseEntity<Formation> getByNumeroInscription(@PathVariable String numeroInscription);
    
    @GetMapping("/getFormationByAgent/{idPersonnel}")
    public ResponseEntity<List<Formation>> getFormationByAgent(@PathVariable Integer idPersonnel);
    
    
    @DeleteMapping("/deleteFormation/{idFormation}")
    public ResponseEntity<String> deleteFormation(@PathVariable Integer idFormation);
    
     
    
}
