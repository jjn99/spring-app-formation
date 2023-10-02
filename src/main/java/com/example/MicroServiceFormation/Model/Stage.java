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

@NamedQuery(name="Stage.getStageByStatus",query=" select s from Stage s where s.statut=:statut" )
@NamedQuery(name="Stage.getStageByIdPostulant",query=" select s from Stage s where s.postulant.idPostulant=:idPostulant" )
@NamedQuery(name="Stage.getStageByIdEntite",query=" select s from Stage s where s.entite.idEntite=:idEntite" )
@NamedQuery(name="Stage.getStageById",query=" select s from Stage s where s.idStage=:idStage" )



@Data
@Entity
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Stage")
public class Stage {
    
        
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idStage")
    private Integer idStage;
        
     @Column(name="dateDebut")
     private String dateDebut;
     
     @Column(name="dateFin")
     private String dateFin;
      
     @Column(name="statut")
     private String statut;
     
    @ManyToOne
    @JoinColumn(name="idPostulant", nullable = false)
    private Postulant postulant;
    
      @ManyToOne
    @JoinColumn(name="idEntite", nullable = false)
    private Entite entite;
    @Transient
    private Integer idEntite;
    
    
}
