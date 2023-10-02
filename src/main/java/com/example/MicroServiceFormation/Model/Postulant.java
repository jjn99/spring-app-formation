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
import javax.print.Doc;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@NamedQuery(name="Postulant.getPostulantByStatus",query=" select s from Postulant s where s.statut=:statut" )
@NamedQuery(name="Postulant.getPostulantByMail",query=" select s from Postulant s where s.mail=:mail" )
@NamedQuery(name="Postulant.getPostulantById",query=" select s from Postulant s where s.idPostulant=:idPostulant" )

@Data
@Entity
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Postulant")
public class Postulant {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPostulant")
    private Integer idPostulant;
     
     @Column(name="nom")
     private String nom;
     
     @Column(name="prenom")
     private String prenom;
     
     @Column(name="age")
     private Integer age;
     
     @Column(name="sexe")
     private String sexe;
     
     @Column(name="cnib", unique=true)
     private String cnib;
     
     @Column(name="phone")
     private String phone;
     
     @Column(name="mail",unique=true)
     private String mail;
     
     @Column(name="ville")
     private String ville;
     
     @Column(name="statut")
     private String statut;
     
     @Column(name="niveau")
     private String niveau;
     
     @Column(name="diplome")
     private String diplome;
    
    @JsonIgnore
    @OneToMany(mappedBy="postulant")
    private List<Demande> demandes;
    
     @JsonIgnore
    @OneToMany(mappedBy="postulant")
    private List<Stage> stages;
    
             
}