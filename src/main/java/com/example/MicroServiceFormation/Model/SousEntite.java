package com.example.MicroServiceFormation.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@NamedQuery(name="SousEntite.getSousEntiteByCode",query=" select e from SousEntite e where e.codeinputation=:codeimputation" )

@NamedQuery(name="SousEntite.getSousEntiteByNom",query=" select e from SousEntite e where e.nom=:nom" )

@NamedQuery(name="SousEntite.getSousEntiteById",query=" select e from SousEntite e where e.idSousEntite=:id" )

@NamedQuery(name="SousEntite.getSousEntiteByIdEntite",query=" select e from SousEntite e where e.entite.idEntite=:id" )

@Data
@Entity
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SousEntite")

public class SousEntite {
    
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSousEntite")
    private Integer idSousEntite;
     
    @Column(name="codeinputation")
    private String codeinputation;
    
    @Column(name="nom")
    private String nom;
          
    @JsonIgnore
    @OneToMany(mappedBy="sousEntite")
    private List<BudgetSousEntite> sousEntite;
   
    @ManyToOne
    @JoinColumn(name="id_Entite", nullable = false)
    private Entite entite;
    @Transient
    private Integer idEntite;
    
}
