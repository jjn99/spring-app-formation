package com.example.MicroServiceFormation.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
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


@NamedQuery(name="Salle.getSalleByNom",query=" select s from Salle s where s.nom=:nom" )

@NamedQuery(name="Salle.getSalleByStatus",query=" select s from Salle s where s.statut=:statut" )

@NamedQuery(name="Salle.getSalleByNbrPlace",query=" select s from Salle s where s.nbrPlace=:nbrPlace" )

@NamedQuery(name="Salle.getSalleById",query=" select s from Salle s where s.idSalle=:id" )

@Data
@Entity
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Salle")
public class Salle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSalle")
    private Integer idSalle;
    
     @Column(name="nom")
     private String nom;
     
     @Column(name="nbrPlace")
     private Integer nbrPlace;
     
     @Column(name="description")
     private String description;
     
         @Column(name="motif")
     private String motif;
     
     @Column(name="statut")
     private String statut;
         
     @JsonIgnore
      @OneToMany(mappedBy="salle")
     private List<Plannifier> plannifiers;
     
}
