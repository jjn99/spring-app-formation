package com.example.MicroServiceFormation.Controller;

import com.example.MicroServiceFormation.Model.Agents;
import com.example.MicroServiceFormation.Model.Formation;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/Personnel")
public interface PersonnelRest {
    
    @PostMapping("/addPersonnel")
    public ResponseEntity<String> addPersonnel(@RequestBody Map<String,String> requestMap);
    
    @PostMapping("/updatePersonnel")
    public ResponseEntity<String> updatePersonnel(@RequestBody Map<String,String> requestMap);
    
    @GetMapping("/getAllPersonnel")
    public ResponseEntity<List<Agents>> getAllPersonnel();
    
    @GetMapping("/getPersonnelByMatricule/{matricule}")
    public ResponseEntity<Agents> getPersonnelByMatricule(@PathVariable String matricule);
    
    @GetMapping("/getPersonnelById/{idPersonnel}")
    public ResponseEntity<Agents> getPersonnelById(@PathVariable Integer idPersonnel);
    
    @DeleteMapping("/deletePersonnel/{idPersonnel}")
    public ResponseEntity<String> deletePersonnel(@PathVariable Integer idPersonnel);
    
    
}
