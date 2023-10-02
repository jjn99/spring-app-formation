package com.example.MicroServiceFormation.Controller;

import com.example.MicroServiceFormation.Model.Plannifier;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/Plannifier")
public interface PlannifierRest {
    
    @PostMapping("/addPlannifier")
    public ResponseEntity<String> addPlannifier(@RequestBody Map<String,String> requestMap);
    
    @PostMapping("/updatePlannifier")
    public ResponseEntity<String> updatePlannifier(@RequestBody Map<String,String> requestMap);
    
    @GetMapping("/getAllPlannifier")
    public ResponseEntity<List<Plannifier>> getAllPlannifier();
   
    @GetMapping("/getPlannifierById")
    public ResponseEntity<Plannifier> getPlannifierById(@RequestBody Map<String, String> requestMap);
    
    @DeleteMapping("/deletePlannifier/{idPlanning}")
    public ResponseEntity<String> deletePlannifier(@PathVariable Integer idPlanning);
    
   
}
