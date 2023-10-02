package com.example.MicroServiceFormation.Dao;

import com.example.MicroServiceFormation.Model.Cout;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoutDao extends JpaRepository<Cout, Integer>{

    public Cout getCoutById(Integer id);

    public  List<Cout> getEntiteByFormationId(Integer id);
    
}
