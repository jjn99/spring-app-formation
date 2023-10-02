package com.example.MicroServiceFormation.Controller;

import com.example.MicroServiceFormation.Model.Salle;
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


@RequestMapping("/Salle")
public interface SalleRest {
    
    @PostMapping("/addSalle")
    public ResponseEntity<String> addSalle(@RequestBody Map<String,String> requestMap);
    
    @PostMapping("/updateSalle")
    public ResponseEntity<String> updateSalle( @RequestBody Map<String,String> requestMap);
    
    @GetMapping("/getAllSalle")
    public ResponseEntity<List<Salle>> getAllSalle();
    
    @GetMapping("/getSalleById")
    public ResponseEntity<Salle> getSalleById(@RequestBody Map<String, String> requestMap);
   
    @DeleteMapping("/deleteSalle/{idSalle}")
    public ResponseEntity<String> deleteSalle(@PathVariable Integer idSalle);
    
    
}
