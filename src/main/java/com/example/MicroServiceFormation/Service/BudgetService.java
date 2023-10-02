package com.example.MicroServiceFormation.Service;

import com.example.MicroServiceFormation.Model.Budget;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface BudgetService {
    
    public ResponseEntity<String> addBudget(@RequestBody Map<String, String> requestMap);
    public ResponseEntity<String> updateBudget(@RequestBody Map<String, String> requestMap);
    public ResponseEntity<List<Budget>> getAllBudget();
    public ResponseEntity<Budget> getBudgetById(Integer idBudget);
    public ResponseEntity<List<Budget>> getBudgetEntiteById(Integer idEntite);
    public ResponseEntity<Budget> getBudgetByStatut(Integer idEntite);
    public ResponseEntity<String> deleteBudget(Integer idBudget);

    public ResponseEntity<String> activerBudget(Map<String, String> requestMap);

    public ResponseEntity<String> ajoutGlobal(Map<String, String> requestMap);

    public ResponseEntity<String> retraitGlobal(Map<String, String> requestMap);

    public ResponseEntity<String> calcul(Map<String, String> requestMap);
    
    
}
