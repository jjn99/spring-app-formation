package com.example.MicroServiceFormation.Controller;

import com.example.MicroServiceFormation.Model.FraisMission;
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

@RequestMapping("/FraisMission")
public interface FraisMissionRest {
    
           
    @PostMapping("/addFrais")
    public ResponseEntity<String> addFrais(@RequestBody Map<String,String> requestMap);
    
    @PostMapping("/updateFrais")
    public ResponseEntity<String> updateFrais(@RequestBody Map<String,String> requestMap);
    
    @GetMapping("/getAllFrais")
    public ResponseEntity<List<FraisMission>> getAllFrais();
 
    @GetMapping("/getFraisById")
    public ResponseEntity<FraisMission> getFraisMissionById(@RequestBody Map<String, String> requestMap);
    
    @DeleteMapping("/deleteFrais/{idFraisMission}")
    public ResponseEntity<String> deleteFrais(@PathVariable Integer idFraisMission);
    
   
}
