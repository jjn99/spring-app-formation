package com.example.MicroServiceFormation.Dao;

import com.example.MicroServiceFormation.Model.Lieu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LieuDao extends JpaRepository<Lieu, Integer>{

    public Lieu getLieuById(Integer id);

    public Lieu getLieuByPays(String pays);
    
    public Lieu getLieuByVille(String ville);
}
