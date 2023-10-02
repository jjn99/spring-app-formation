package com.example.MicroServiceFormation.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@NamedQuery(name="Cout.getEntiteByFormationId",query=" select c from Cout c where c.formation.idFormation=:id" )

@NamedQuery(name="Cout.getCoutById",query=" select c from Cout c where c.idCout=:id" )


@Data
@Entity
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Cout")
public class Cout {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCout")
    private Integer idCout;
     
    @Column(name = "designation") 
    private String designation;
    
    @Column(name = "coutUnitaire")
    private Integer coutUnitaire;
    
    @Column(name = "quantite")
    private Integer quantite;
    
    @Column(name = "nbrParticipant")
    private Integer nbrParticipant;
    
    @Column(name = "montant")
    private Integer montant;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="idFormation", nullable = false)
    private Formation formation;

   
    
    
}
