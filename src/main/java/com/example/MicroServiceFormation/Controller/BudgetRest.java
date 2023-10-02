package com.example.MicroServiceFormation.Controller;

import com.example.MicroServiceFormation.Model.Budget;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/Budget")
public interface BudgetRest {
    
    @PostMapping("/addBudget")
    public ResponseEntity<String> addBudget(@RequestBody Map<String, String> requestMap);
    
    @PostMapping("/updateBudget")
    public ResponseEntity<String> updateBudget(@RequestBody Map<String, String> requestMap);
    
        @PostMapping("/ajoutGlobal")
    public ResponseEntity<String> ajoutGlobal(@RequestBody Map<String, String> requestMap);
    
        @PostMapping("/retraitGlobal")
    public ResponseEntity<String> retraitGlobal(@RequestBody Map<String, String> requestMap);
    
        @PostMapping("/calcul")
    public ResponseEntity<String> calcul(@RequestBody Map<String, String> requestMap);
    
    
    @GetMapping("/getAllBudget")
    public ResponseEntity<List<Budget>> getAllBudget();
    
    @GetMapping("/getBudgetById/{idBudget}")
    public ResponseEntity<Budget> getBudgetById(@PathVariable Integer idBudget);
    
    @GetMapping("/getBudgetEntiteById/{idEntite}")
    public ResponseEntity<List<Budget>> getBudgetEntiteById(@PathVariable Integer idEntite);
    
    @GetMapping("/getBudgetStatut/{idEntite}")
    public ResponseEntity<Budget> getBudgetByStatut(@PathVariable Integer idEntite);
    
    @DeleteMapping("/deleteBudget/{idBudget}")
    public ResponseEntity<String> deleteBudget(@PathVariable Integer idBudget);
    
     @PostMapping("/activerBudget")
    public ResponseEntity<String> activerBudget(@RequestBody Map<String, String> requestMap);
    
    
}
