package com.example.MicroServiceFormation.Controller;

import com.example.MicroServiceFormation.Model.Lieu;
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

@RequestMapping("/Lieu")
public interface LieuRest {
    
              
    @PostMapping("/addLieu")
    public ResponseEntity<String> addLieu(@RequestBody Map<String,String> requestMap);
    
    @PostMapping("/updateLieu")
    public ResponseEntity<String> updateLieu( @RequestBody Map<String,String> requestMap);
    
    @GetMapping("/getAllLieu")
    public ResponseEntity<List<Lieu>> getAllLieu();
 
    @GetMapping("/getLieuById")
    public ResponseEntity<Lieu> getLieuById(@RequestBody Map<String, String> requestMap);
 
    @DeleteMapping("/deleteLieu/{idlieu}")
    public ResponseEntity<String> deleteLieu(@PathVariable Integer idLieu);
    
}
