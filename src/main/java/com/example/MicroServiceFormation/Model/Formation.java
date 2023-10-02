package com.example.MicroServiceFormation.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@NamedQuery(name="Formation.getByNumeroInscription",query=" select f from Formation f where f.numeroInscription=:numeroInsciption" )
@NamedQuery(name="Formation.findByNumeroInscription",query=" select f from Formation f where f.numeroInscription=:numeroInsciption" )

@NamedQuery(name="Formation.getByIdFormation",query=" select f from Formation f where f.idFormation=:id" )

@NamedQuery(name="Formation.getFormationByOrganisme",query=" select f from Formation f where f.organisme.idOrganisme=:id" )

@NamedQuery(name="Formation.getFormationByDatePlan",query=" select f from Formation f where f.datePlan=:datePlan" )

@NamedQuery(name="Formation.getFormationByAvis",query=" select f from Formation f where f.avis=:avis" )

@NamedQuery(name="Formation.getFormationByCategorieFormation", query="select f from Formation f where f.categorieFormation=:categorie")




@Slf4j
@Data
@Entity
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Formation")
public class Formation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idFormation")
    private Integer idFormation;
    
    @Column(name="numeroInscription")
    private String numeroInscription;
     
    @Column(name = "titre")
    private String titre;

    @Column(name = "objectif")
    private String objectif;
     
    @Column(name="dateCreation")
    private String dateCreation;
     
    @Column(name="datePlan")
    private String datePlan;
     
    @Column(name="statut")
    private String statut;
     
    @Column(name="avis")
    private String avis;
    
    @Column(name="zone")
    private String zone;
    
    @Column(name="categorieFormation")
    private String categorieFormation;
    
    @Column(name="total")
    private Integer total;

    @Column(name = "type")
    private String type;

    @Column(name = "dure")
    private Integer dure;
     
    
    @ManyToMany
    private Collection<Entite> listDirection ;
    
    @ManyToOne
    @JoinColumn(name="idOrganisme", nullable = false)
    private Organisme organisme;
    @Transient
    private Integer idOrganisme;
    
   @Column(name="pays")
    private String pays;
    
    @Column(name = "ville")    
    private String ville;
    
    
    @OneToMany(mappedBy="formation")
    private List<Session> sessions;
    

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Agents> listAgents ;

    
    @OneToMany(mappedBy="formation")
    @JsonIgnore
    private List<Plannifier> plannifiers;
 
    
    @OneToMany(mappedBy="formation")
    private List<Cout> couts;
    
    @ManyToMany
    private Collection<SousEntite> listDepartement ;
  
}

