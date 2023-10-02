package com.example.MicroServiceFormation.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@NamedQuery(name="Entite.getEntiteByCode",query=" select e from Entite e where e.codeinputation=:codeimputation" )

@NamedQuery(name="Entite.getEntiteByNom",query=" select e from Entite e where e.nom=:nom" )

@NamedQuery(name="Entite.getEntiteById",query=" select e from Entite e where e.idEntite=:id" )

@Data
@Entity
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Entite")
public class Entite {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEntite")
    private Integer idEntite;
     
    @Column(name="codeinputation")
    private String codeinputation;
    
    @Column(name="nom")
    private String nom;
      
    @JsonIgnore
    @OneToMany(mappedBy="entite")
    private List<Budget> budget;
    
    @JsonIgnore
    @OneToMany(mappedBy="entite")
    private List<SousEntite> sousEntites;
    
    @JsonIgnore
    @OneToMany(mappedBy="entite")
    private List<Stage> stages;
}
