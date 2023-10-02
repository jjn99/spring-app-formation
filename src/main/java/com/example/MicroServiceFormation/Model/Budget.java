package com.example.MicroServiceFormation.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@NamedQuery(name="Budget.getBudgetById",query=" select b from Budget b where b.idBudget=:id" )

@NamedQuery(name="Budget.getBudgetEntiteById",query=" select b from Budget b where b.entite.idEntite=:id " )

@NamedQuery(name="Budget.getBudgetByStatut",query=" select b from Budget b where b.statut=:statut" )


@Data
@Entity
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Budget")
public class Budget {
    
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBudget")
    private Integer idBudget;
    
     @Column(name="budgetGlobaleAlloue")
     private Integer budgetGlobaleAlloue;
     
     @Column(name="budgetGlobaleRestant")
     private Integer budgetGlobaleRestant;
     
     
     @Column(name="tauxRealisationGlobal")
     private Float tauxRealisationGlobal;
     
     @Column(name="budgetalloue")
     private Integer budgetalloue;
     
     @Column(name="budgetrealisation")
     private Integer budgetrealisation;
     
     @Column(name="budgetrestant")
     private Integer budgetrestant;
     
     @Column(name="tauxrealisation")
     private Float tauxrealisation;
     
     @Column(name="datePlan")
     private String datePlan;
     
    @Column(name="statut")
    private String statut;
     
    @ManyToOne
    @JoinColumn(name="id_Entite", nullable = false)
    private Entite entite;
     @Transient
    private Integer idEntite;
    
    
    
}
