
package com.example.MicroServiceFormation.Dao;

import com.example.MicroServiceFormation.Model.Stage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StageDao extends JpaRepository<Stage, Integer> {
    
     public List<Stage> getStageByStatus(String statut);

    public Stage getStageById(Integer idStage);
    
    public List<Stage> getStageByIdPostulant(Integer idPostulant);
    
    public List<Stage> getStageByIdEntite(Integer idEntite);
    
}
