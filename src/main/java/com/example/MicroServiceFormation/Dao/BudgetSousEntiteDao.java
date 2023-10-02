package com.example.MicroServiceFormation.Dao;

import com.example.MicroServiceFormation.Model.BudgetSousEntite;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetSousEntiteDao extends JpaRepository<BudgetSousEntite, Integer>{

    public BudgetSousEntite getBudgetById(Integer id);

    public List<BudgetSousEntite> getBudgetByStatut(String statut);
    
    public List<BudgetSousEntite> getBudgetSousEntiteById(Integer id);
}
