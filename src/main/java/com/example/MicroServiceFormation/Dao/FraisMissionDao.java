package com.example.MicroServiceFormation.Dao;

import com.example.MicroServiceFormation.Model.FraisMission;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraisMissionDao extends JpaRepository<FraisMission, Integer>{

    public FraisMission getFraisMissionById(Integer id);

    public List<FraisMission> getFraisByDure(Integer dure);

    public List<FraisMission> getFraisByLibelle(String libelle);

    public List<FraisMission> getFraisByCategorie(String categorie);

    public List<FraisMission> getFraisByLocalisation(String localisation);
    
}
