package com.example.MicroServiceFormation.Controller;

import com.example.MicroServiceFormation.Model.Entite;
import com.example.MicroServiceFormation.Model.Formation;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/Entite")
public interface EntiteRest {
    
       
    @PostMapping("/addEntite")
    public ResponseEntity<String> addEntite(@RequestBody Map<String,String> requestMap);
    
    @PostMapping("/updateEntite")
    public ResponseEntity<String> updateEntite(@RequestBody Map<String,String> requestMap);
    
    @GetMapping("/getAllEntite")
    public ResponseEntity<List<Entite>> getAllEntite();
 
    @GetMapping("/getEntiteById/{idEntite}")
    public ResponseEntity<Entite> getEntiteById(@PathVariable Integer idEntite);
    
    @DeleteMapping("/deleteEntite/{idEntite}")
    public ResponseEntity<String> deleteEntite(@PathVariable Integer idEntite);
    
    @PostMapping("/ActivezBudget")
    public ResponseEntity<String> ActivezBudget(@RequestBody Map<String,String> requestMap);
       
    
    @GetMapping("/getAllEntiteFormation/{idEntite}")
    public ResponseEntity<List<Formation>> getAllEntiteFormation(@PathVariable Integer idEntite);
    
    @GetMapping("/getAllFormationEntite/{idEntite}")
    public ResponseEntity<Collection<Formation>> getAllFormationEntite(@PathVariable Integer idEntite);
    
}
