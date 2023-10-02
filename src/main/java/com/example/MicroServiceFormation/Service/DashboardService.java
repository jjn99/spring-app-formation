package com.example.MicroServiceFormation.Service;

import java.util.Map;
import org.springframework.http.ResponseEntity;

public interface DashboardService {

    public ResponseEntity<Map<String, Object>> getCount();

    public ResponseEntity<Map<String, Object>> getStageCount();
    
}
