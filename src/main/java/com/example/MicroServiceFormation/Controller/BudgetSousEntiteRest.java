package com.example.MicroServiceFormation.Controller;

import com.example.MicroServiceFormation.Model.BudgetSousEntite;
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

@RequestMapping("/BudgetSousEntite")
public interface BudgetSousEntiteRest {
    
    @PostMapping("/addBudget")
    public ResponseEntity<String> addBudget(@RequestBody Map<String,String> requestMap);
    
    @PostMapping("/updateBudget")
    public ResponseEntity<String> updateBudget(@RequestBody Map<String,String> requestMap);
    
            @PostMapping("/calcul")
    public ResponseEntity<String> calcul(@RequestBody Map<String, String> requestMap);
    
    
    @GetMapping("/getAllBudget")
    public ResponseEntity<List<BudgetSousEntite>> getAllBudget();
    
    @GetMapping("/getBudgetById/{idBudgetSousEntite}")
    public ResponseEntity<BudgetSousEntite> getBudgetById(@PathVariable Integer idBudgetSousEntite);
       
    @GetMapping("/getBudgetSousEntiteById/{idSousEntite}")
    public ResponseEntity<List<BudgetSousEntite>> getBudgetSousEntiteById(@PathVariable Integer idSousEntite);
    
    @GetMapping("/getBudgetStatut/{idSousEntite}")
    public ResponseEntity<BudgetSousEntite> getBudgetByStatut(@PathVariable Integer idSousEntite);
    
    @DeleteMapping("/deleteBudget/{idBudgetSousEntite}")
    public ResponseEntity<String> deleteBudget(@PathVariable Integer idBudgetSousEntite);
    
       @PostMapping("/activerBudget")
    public ResponseEntity<String> activerBudget(@RequestBody Map<String, String> requestMap);
    
    
}
