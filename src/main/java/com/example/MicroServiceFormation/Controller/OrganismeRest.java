package com.example.MicroServiceFormation.Controller;

import com.example.MicroServiceFormation.Model.Organisme;
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

@RequestMapping("/Organisme")
public interface OrganismeRest {
    
    @PostMapping("/addOrganisme")
    public ResponseEntity<String> addOrganisme(@RequestBody Map<String,String> requestMap);
    
    @PostMapping("/updateOrganisme")
    public ResponseEntity<String> updateOrganisme(@RequestBody Map<String,String> requestMap);
    
    @GetMapping("/getAllOrganisme")
    public ResponseEntity<List<Organisme>> getAllOrganisme();
    
    @GetMapping("/getOrganismeById")
    public ResponseEntity<Organisme> getOrganismeById(@RequestBody Map<String, String> requestMap);
    
    @DeleteMapping("/deleteOrganisme/{idOrganisme}")
    public ResponseEntity<String> deleteOrganisme(@PathVariable Integer idOrganisme);
    
      
}
