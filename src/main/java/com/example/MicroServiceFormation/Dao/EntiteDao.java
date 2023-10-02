package com.example.MicroServiceFormation.Dao;

import com.example.MicroServiceFormation.Model.Entite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntiteDao extends JpaRepository<Entite, Integer>{

    public Entite getEntiteById(Integer id);

    public Entite getEntiteByCode(String codeimputation);

    public Entite getEntiteByNom(String nom);
    
}
