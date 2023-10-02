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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@NamedQuery(name="FraisMission.getFraisByCategorie",query=" select  f from FraisMission f where f.categorie=:categorie" )

@NamedQuery(name="FraisMission.getFraisByLibelle",query=" select  f from FraisMission f where f.libelle=:libelle" )

@NamedQuery(name="FraisMission.getFraisByDure",query=" select  f from FraisMission f where f.dure=:dure" )

@NamedQuery(name="FraisMission.getFraisByLocalisation",query=" select  f from FraisMission f where f.localisation=:localisation " )

@NamedQuery(name="FraisMission.getFraisMissionById",query=" select  f from FraisMission f where f.idFraisMission=:id" )

@Data
@Entity
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FraisMission")
public class FraisMission {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFraisMission")
    private Integer idFraisMission;
     
    @Column(name = "libelle")
    private String libelle;
     
    @Column(name = "categorie")
    private String categorie;
     
    @Column(name = "dure")
    private Integer dure;
     
    @Column(name = "prix")
    private Integer prix;
    
    @Column(name="localisation")
    private String localisation;
    

}
