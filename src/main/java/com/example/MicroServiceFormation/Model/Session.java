package com.example.MicroServiceFormation.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
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

@NamedQuery(name="Session.getSessionByIdFormation",query=" select s from Session s where s.formation.numeroInscription=:id" )

@NamedQuery(name="Session.getSessionById",query=" select s from Session s where s.idSession=:id" )

@Data
@Entity
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Session")
public class Session {
    
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSession")
    private Integer idSession;
    
     @JsonFormat(pattern = "yyyy-MM-dd" , shape = Shape.STRING)
     @Column(name="dateSessiondebut")
     private String dateSessionDebut;
     
     @JsonFormat(pattern = "yyyy-MM-dd" , shape = Shape.STRING)
     @Column(name="dateSessionfin")
     private String dateSessionFin;
     
     @JsonFormat(pattern = "yyyy-MM-dd" , shape = Shape.STRING)
     @Column(name="dateDepart")
     private String dateDepart;
     
     @JsonFormat(pattern = "yyyy-MM-dd" , shape = Shape.STRING)
     @Column(name="dateRetour")
     private String dateRetour;
     
     @ManyToOne
     @JsonIgnore
     @JoinColumn(name="idFormation",nullable =false)
     private Formation formation;

}
