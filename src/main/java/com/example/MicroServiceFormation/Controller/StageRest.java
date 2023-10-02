package com.example.MicroServiceFormation.Controller;

import com.example.MicroServiceFormation.Model.Salle;
import com.example.MicroServiceFormation.Model.Stage;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/Stage")
public interface StageRest {
    
    @PostMapping("/addStage")
    public ResponseEntity<String> addStage(@RequestBody Map<String,String> requestMap);
    
    @PostMapping("/updateStage")
    public ResponseEntity<String> updateStage( @RequestBody Map<String,String> requestMap);
    
    @GetMapping("/getAllStage")
    public ResponseEntity<List<Stage>> getAllStage();
    
    @GetMapping("/getStageByIdPostulant/{idPostulant}")
    public ResponseEntity<List<Stage>> getStageByIdPostulant(@PathVariable Integer idPostulant);
    
    @GetMapping("/getStageByIdEntite/{idEntite}")
    public ResponseEntity<List<Stage>> getStageByIdEntite(@PathVariable Integer idEntite);
}
