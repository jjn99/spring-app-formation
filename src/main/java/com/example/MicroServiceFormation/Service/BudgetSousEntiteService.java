package com.example.MicroServiceFormation.Service;

import com.example.MicroServiceFormation.Model.BudgetSousEntite;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface BudgetSousEntiteService {
    
    public ResponseEntity<String> addBudget(@RequestBody Map<String,String> requestMap);
    public ResponseEntity<String> updateBudget(@RequestBody Map<String,String> requestMap);
    public ResponseEntity<List<BudgetSousEntite>> getAllBudget();
    public ResponseEntity<BudgetSousEntite> getBudgetById(Integer idBudget);
    public ResponseEntity<BudgetSousEntite> getBudgetByStatut(Integer idSousEntite);
    public ResponseEntity<String> deleteBudget(Integer idBudgetSousEntite);

    public ResponseEntity<List<BudgetSousEntite>> getBudgetSousEntiteById(Integer idSousEntite);

    public ResponseEntity<String> activerBudget(Map<String, String> requestMap);

    public ResponseEntity<String> calcul(Map<String, String> requestMap);
  
    
}
