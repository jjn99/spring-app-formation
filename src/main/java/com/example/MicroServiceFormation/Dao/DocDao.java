package com.example.MicroServiceFormation.Dao;

import com.example.MicroServiceFormation.Model.Doc;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocDao extends JpaRepository<Doc, Integer>{
    
   public List<Doc> getDocByIdDemande(Integer idDemande);
    
   public Doc getDocById(Integer idDoc);
}
