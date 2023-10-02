package com.example.MicroServiceFormation.Controller;

import com.example.MicroServiceFormation.Model.Cout;
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

@RequestMapping("/Cout")
public interface CoutRest {
    
          
    @PostMapping("/addCout")
    public ResponseEntity<String> addCout(@RequestBody Map<String, String> requestMap);
    
    @PostMapping("/updateCout")
    public ResponseEntity<String> updateCout( @RequestBody Map<String, String> requestMap);
    
    @GetMapping("/getAllCout")
    public ResponseEntity<List<Cout>> getAllCout();
 
    @GetMapping("/getCoutById/{idCout}")
    public ResponseEntity<Cout> getCoutById(@PathVariable Integer idCout);
    
    @GetMapping("/getEntiteByFormationId/{idFormation}")
    public  ResponseEntity<List<Cout>> getEntiteByFormationId(@PathVariable Integer idFormation);

    @DeleteMapping("/deleteCout/{idCout}")
    public ResponseEntity<String> deleteCout(@PathVariable Integer idCout);
    
   
    
}
