package com.example.MicroServiceFormation.Controller;

import com.example.MicroServiceFormation.Model.Session;
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

@RequestMapping("/Session")
public interface SessionRest {
    
        
     @PostMapping("/addSession")
    public ResponseEntity<String> addSession(@RequestBody Map<String,String> requestMap);
    
    @PostMapping("/updateSession")
    public ResponseEntity<String> updateSession(@RequestBody Map<String,String> requestMap);
    
    @GetMapping("/getAllSession")
    public ResponseEntity<List<Session>> getAllSession();
    
//    @GetMapping("/findSessionByIdFormation/{idFormation}")
//    public ResponseEntity<List<Session>> findSessionByIdFormation(@PathVariable Integer idFormation);
//    
    @GetMapping("/getSessionById")
    public ResponseEntity<Session> getSessionById(@RequestBody Map<String, String> requestMap);
    
    @DeleteMapping("/deleteSession/{idSession}")
    public ResponseEntity<String> deleteSession(@PathVariable Integer idSession);
    
   
}
