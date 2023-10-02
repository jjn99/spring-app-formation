package com.example.MicroServiceFormation.Controller;

import com.example.MicroServiceFormation.Model.Postulant;
import com.example.MicroServiceFormation.Model.Salle;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/Postulant")
public interface PostulantRest {
    
    @PostMapping("/addPostulant")
    public ResponseEntity<String> addPostulant(@RequestBody Map<String,String> requestMap);
    
    @PostMapping("/updatePostulant")
    public ResponseEntity<String> updatePostulant( @RequestBody Map<String,String> requestMap);
    
    @GetMapping("/getAllPostulant")
    public ResponseEntity<List<Postulant>> getAllPostulant();
   
           @GetMapping("/getPostulantByMail/{mail}")
    public ResponseEntity<Postulant> getPostulantByMail(@PathVariable String mail);
    
    
}
