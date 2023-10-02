
package com.example.MicroServiceFormation.Dao;

import com.example.MicroServiceFormation.Model.Demande;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DemandeDao extends JpaRepository<Demande, Integer>{
    
    public List<Demande> getDemandeByStatus(String statut);

    public Demande getDemandeById(Integer idDemande);
    
     public List<Demande> getDemandeByIdPostulant(Integer idPostulant);

    public Demande getDemandeByNumeroDemande(String numeroDemande);
}
