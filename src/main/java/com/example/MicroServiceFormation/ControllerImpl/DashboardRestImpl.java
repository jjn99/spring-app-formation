package com.example.MicroServiceFormation.ControllerImpl;

import com.example.MicroServiceFormation.Controller.DashboardRest;
import com.example.MicroServiceFormation.Service.DashboardService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardRestImpl implements DashboardRest {

    @Autowired 
    DashboardService dashboard;
    
    @Override
    public ResponseEntity<Map<String, Object>> getCount() {
        return dashboard.getCount();
    }

    @Override
    public ResponseEntity<Map<String, Object>> getStageCount() {
        return dashboard.getStageCount();
    }
    
}
