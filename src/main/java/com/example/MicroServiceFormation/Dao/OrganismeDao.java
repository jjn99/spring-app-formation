package com.example.MicroServiceFormation.Dao;

import com.example.MicroServiceFormation.Model.Organisme;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganismeDao extends JpaRepository<Organisme, Integer>{

    public Organisme getOrganismeById(Integer id);

    public List<Organisme> getOrganismeByDomaine(String domaine);

    public List<Organisme> getOrganismeByLieu(String lieu);

    public Organisme getOrganismeByNom(String nom);
    
}