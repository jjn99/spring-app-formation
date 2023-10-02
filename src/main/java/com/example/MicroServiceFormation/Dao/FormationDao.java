package com.example.MicroServiceFormation.Dao;

import com.example.MicroServiceFormation.Model.Formation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormationDao extends JpaRepository<Formation, Integer>{

    public Formation getByNumeroInscription(String numeroInscription);
    
     public Formation findByNumeroInscription(String numeroInscription);
    
    public Formation getByIdFormation(Integer id);
    
    Formation findByIdFormation(Integer idFormation);

    public List<Formation> getByTitre(String titre);

    public List<Formation> getByStatut(String statut);

    public List<Formation> getByType(String type);

    public List<Formation> getFormationByOrganisme(Integer id);

    public List<Formation> getFormationByAvis(String avis);

    public List<Formation> getFormationByDatePlan(String datePlan);
    
    public List<Formation> getFormationByCategorieFormation(String categorie);
      
}
