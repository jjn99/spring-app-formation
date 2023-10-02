package com.example.MicroServiceFormation.Dao;

import com.example.MicroServiceFormation.Model.SousEntite;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SousEntiteDao extends JpaRepository<SousEntite, Integer>{

    public SousEntite getSousEntiteById(Integer id);

    public SousEntite getSousEntiteByCode(String codeimputation);

    public SousEntite getSousEntiteByNom(String nom);

    public List<SousEntite> getSousEntiteByIdEntite(Integer id);
    
}
