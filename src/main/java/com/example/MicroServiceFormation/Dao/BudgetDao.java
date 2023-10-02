package com.example.MicroServiceFormation.Dao;

import com.example.MicroServiceFormation.Model.Budget;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetDao extends JpaRepository<Budget, Integer>{

    public Budget getBudgetById(Integer id);

    public List<Budget> getBudgetByStatut(String statut);

    public List<Budget> getBudgetEntiteById(Integer id);
    
}
