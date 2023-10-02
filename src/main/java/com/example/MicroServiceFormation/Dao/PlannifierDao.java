package com.example.MicroServiceFormation.Dao;

import com.example.MicroServiceFormation.Model.Plannifier;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlannifierDao extends JpaRepository<Plannifier, Integer>{

    public Plannifier getPlannifierById(Integer id);
    
     public List<Plannifier> getPlannifierByFormation(Integer id);
     
      public List<Plannifier> getPlannifierBySalle(Integer id);
      
       public Plannifier getPlannifierByEvenement(String evenement);
    
}
