package com.example.MicroServiceFormation.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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

@NamedQuery(name="Doc.getDocByIdDemande",query=" select s from Doc s where s.demande.idDemande=:idDemande" )
@NamedQuery(name="Doc.getDocById",query=" select s from Doc s where s.idDoc=:idDoc" )

@Data
@Entity
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Doc")
public class Doc {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDoc")
    private Integer idDoc;
        
     @Column(name="docName")
     private String docName;
     
     @Column(name="docType")
     private String docType;
     
     @Lob
      @Column(name="donne")
     private byte[] donne;
     
    @ManyToOne
    @JoinColumn(name="idDemande", nullable = false)
    private Demande demande;
    
}
