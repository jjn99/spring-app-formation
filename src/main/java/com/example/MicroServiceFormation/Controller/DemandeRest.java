
package com.example.MicroServiceFormation.Controller;

import com.example.MicroServiceFormation.Model.Demande;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/Demande")
public interface DemandeRest {
    
    @PostMapping("/addDemande")
    public ResponseEntity<String> addDemande(@RequestBody Map<String,String> requestMap);
    
    @PostMapping("/updateDemande")
    public ResponseEntity<String> updateDemande( @RequestBody Map<String,String> requestMap);
    
    @GetMapping("/getAllDemande")
    public ResponseEntity<List<Demande>> getAllDemande();
    
    @GetMapping("/getDemandeByNumeroDemande/{numeroDemande}")
    public ResponseEntity<Demande> getDemandeByNumeroDemande(@PathVariable String numeroDemande);
    
    @GetMapping("/getDemandeByIdPostulant/{idPostulant}")
    public ResponseEntity<List<Demande>> getDemandeByIdPostulant(@PathVariable Integer idPostulant);
    
    @PostMapping("/sendSimpleMessage")
    public void sendSimpleMessage(@RequestBody Map<String,String> requestMap);
    
}
