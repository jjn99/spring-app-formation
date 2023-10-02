
package com.example.MicroServiceFormation.Dao;

import com.example.MicroServiceFormation.Model.Postulant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostulantDao extends JpaRepository<Postulant, Integer>{
 
     public List<Postulant> getPostulantByStatus(String statut);

    public Postulant getPostulantById(Integer idPostulant);

    public Postulant getPostulantByMail(String mail);
    
}
