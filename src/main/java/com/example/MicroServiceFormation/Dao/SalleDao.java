package com.example.MicroServiceFormation.Dao;

import com.example.MicroServiceFormation.Model.Salle;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalleDao extends JpaRepository<Salle, Integer>{

    public Salle getSalleById(Integer id);

    public Salle getSalleByNom(String nom);

    public List<Salle> getSalleByStatus(String statut);

    public Salle getSalleByNbrPlace(Integer nbrPlace);
    
}
