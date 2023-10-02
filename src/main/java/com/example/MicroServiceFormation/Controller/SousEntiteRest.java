package com.example.MicroServiceFormation.Controller;

import com.example.MicroServiceFormation.Model.SousEntite;
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

@RequestMapping("/sousEntite")
public interface SousEntiteRest {
    
           
     @PostMapping("/addSousEntite")
    public ResponseEntity<String> addSousEntite(@RequestBody Map<String,String> requestMap);
    
    @PostMapping("/updateSousEntite")
    public ResponseEntity<String> updateSousEntite(@RequestBody Map<String,String> requestMap);
    
    @GetMapping("/getAllSousEntite")
    public ResponseEntity<List<SousEntite>> getAllSousEntite();
 
    @GetMapping("/getSousEntiteById")
    public ResponseEntity<SousEntite> getSousEntiteById(@RequestBody Map<String, String> requestMap);
    
    @GetMapping("/getSousEntiteByIdEntite")
    public ResponseEntity<List<SousEntite>> getSousEntiteByIdEntite(@RequestBody Map<String, String> requestMap);
    
    @DeleteMapping("/deleteSousEntite/{idSousEntite}")
    public ResponseEntity<String> deleteSousEntite(@PathVariable Integer idSousEntite);
    
   
        @PostMapping("/ActivezBudget")
    public ResponseEntity<String> ActivezBudget(@RequestBody Map<String,String> requestMap);
 
}
