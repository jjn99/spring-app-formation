package com.example.MicroServiceFormation.Dao;

import com.example.MicroServiceFormation.Model.Agents;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonnelDao extends JpaRepository<Agents, Integer>{

    public Agents getPersonnelById(Integer id);

    public Agents getPersonnelByMatricule(String matricule);

    public List<Agents> getPersonnelByNomPrenom(String nomprenom);

    public List<Agents> getPersonnelByUnite(String unite);

    public List<Agents> getPersonnelByStatut(String statut);

    public List<Agents> getPersonnelByEmploi(String emploi);

    public List<Agents> getPersonnelByFonction(String fonction);

    public List<Agents> getPersonnelByCategorie(String categorie);

    public List<Agents> getPersonnelByEchelon(String echelon);

    
}
