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

@NamedQuery(name="BudgetSousEntite.getBudgetById",query=" select b from BudgetSousEntite b where b.idBudgetSousEntite=:id " )

@NamedQuery(name="BudgetSousEntite.getBudgetByStatut",query=" select b from BudgetSousEntite b where b.statut=:statut " )

@NamedQuery(name="BudgetSousEntite.getBudgetSousEntiteById",query=" select b from BudgetSousEntite b where b.sousEntite.idSousEntite=:id " )


@Data
@Entity
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BudgetSousEntite")
public class BudgetSousEntite {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBudgetSousEntite")
    private Integer idBudgetSousEntite;
    
    @Column(name="budgetalloue")
    private Integer budgetAlloue;
     
    @Column(name="budgetrealisation")
    private Integer budgetRealisation;
     
    @Column(name="budgetrestant")
    private Integer budgetRestant;
     
    @Column(name="tauxrealisation")
    private Float tauxRealisation;
     
    @Column(name="datePlan")
    private String datePlan;
     
    @Column(name="statut")
    private String statut;
     
    @ManyToOne
    @JoinColumn(name="idSousEntite", nullable = false)
    private SousEntite sousEntite;
    @Transient
    private Integer idSousEntite;
}
