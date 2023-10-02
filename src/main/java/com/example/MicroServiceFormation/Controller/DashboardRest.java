package com.example.MicroServiceFormation.Controller;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/Dashboard")
public interface DashboardRest {
    
    @GetMapping("/getDetails")
    ResponseEntity<Map<String,Object>> getCount();
    
    @GetMapping("/getStageDetails")
    ResponseEntity<Map<String,Object>> getStageCount();
    
}
