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

@NamedQuery(name="Demande.getDemandeByStatus",query=" select s from Demande s where s.statut=:statut" )
@NamedQuery(name="Demande.getDemandeById",query=" select s from Demande s where s.idDemande=:idDemande" )
@NamedQuery(name="Demande.getDemandeByIdPostulant",query=" select s from Demande s where s.postulant.idPostulant=:idPostulant" )
@NamedQuery(name="Demande.getDemandeByNumeroDemande",query=" select s from Demande s where s.numeroDemande=:numeroDemande" )



@Data
@Entity
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Demande")
public class Demande {
    
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDemande")
    private Integer idDemande;
        
     @Column(name="numeroDemande")
     private String numeroDemande;
     
     @Column(name="ecole")
     private String ecole;
        
     @Column(name="dateDebut")
     private String dateDebut;
     
      @Column(name="dateFin")
     private String dateFin;
    
     @Column(name="objet")
     private String objet;
       
     @Column(name="statut")
     private String statut;
        
     @Column(name="statutTraitement")
     private String statutTraitement;
       
     @Column(name="motif")
     private String motif;
        
     @ManyToOne
    @JoinColumn(name="idPostulant", nullable = false)
    private Postulant postulant;

    
    @JsonIgnore
    @OneToMany(mappedBy="demande")
    private List<Doc> docs;
    
}
