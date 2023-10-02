package com.example.MicroServiceFormation.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;



@NamedQuery(name="Agents.getPersonnelByMatricule",query=" select p from Agents p where p.matricule=:matricule" )

@NamedQuery(name="Agents.getPersonnelById",query=" select p from Agents p where p.idPersonnel=:id" )

@NamedQuery(name="Agents.getPersonnelByNomPrenom",query="select p from Agents p where p.nomprenom=:nomprenom")

@NamedQuery(name="Agents.getPersonnelByUnite",query="select p from Agents p where p.unite=:unite")

@NamedQuery(name="Agents.getPersonnelByStatut",query="select p from Agents p where p.statut=:statut")

@NamedQuery(name="Agents.getPersonnelByEmploi",query="select p from Agents p where p.emploi=:emploi")

@NamedQuery(name="Agents.getPersonnelByFonction",query="select p from Agents p where p.fonction=:fonction")

@NamedQuery(name="Agents.getPersonnelByCategorie",query="select p from Agents p where p.categorie=:categorie")

@NamedQuery(name="Agents.getPersonnelByEchelon",query="select p from Agents p where p.echelon=:echelon")

@Data
@Entity
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Agents")

public class Agents {
    
    
    private static final Long serialVersionUID = 1L; //formatage
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPersonnel")
    private Integer idPersonnel;
    
    @Column(name = "matricule", nullable = false)
    private String matricule;
    
    @Column(name = "nomprenom")
    private String nomprenom;
    
    @Column(name = "genre")
    private String genre;
    
    @Column(name="statut")
    private String statut;
     
    @Column(name = "dateNaissance")
    private String dateNaissance;
    
    @Column(name = "unite")
    private String unite;
    
    @Column(name = "emploi")
    private String emploi;
    
    @Column(name = "fonction")
    private String fonction;
    
    @Column(name = "categorie")
    private String categorie;
    
    @Column(name = "echelon")
    private String echelon;
    
    @Column(name = "dateEntreServ")
    private String dateEntreServ;
    
    @Column(name = "dateSortieServ")
    private String dateSortieServ;
    
   
}
