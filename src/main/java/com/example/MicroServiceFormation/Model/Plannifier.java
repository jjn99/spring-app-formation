package com.example.MicroServiceFormation.Model;

import javax.persistence.Column;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

@NamedQuery(name="Plannifier.getPlannifierByEvenement",query=" select p from Plannifier p where p.evenement=:evenement" )

@NamedQuery(name="Plannifier.getPlannifierBySalle",query=" select p from Plannifier p where p.salle.idSalle=:id" )

@NamedQuery(name="Plannifier.getPlannifierByFormation",query=" select p from Plannifier p where p.formation.idFormation =:id" )

@NamedQuery(name="Plannifier.getPlannifierById",query=" select p from Plannifier p where p.idPlannifier=:id" )

@Data
@Entity
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Plannifier")
public class Plannifier {
    
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPlannifier")
    private Integer idPlannifier;
    
     @Column(name = "evenement")
     private String evenement;
     
     @Column(name = "datedebut")
     private String dateDebut;
     
     @Column(name=" color")
     private String color;
     
     @Column(name = "datefin")
     private String dateFin;
     
    @ManyToOne
    @JoinColumn(name="idFormation", nullable=true)
    private Formation formation;
    @Transient
    private Integer idFormation;

    @ManyToOne
    @JoinColumn(name="idsalle", nullable=false)
    private Salle salle;
    @Transient
    private Integer idsalle;

}
