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

@NamedQuery(name="Organisme.getOrganismeByNom",query=" select o from Organisme o where o.nom=:nom" )

@NamedQuery(name="Organisme.getOrganismeByDomaine",query=" select o from Organisme o where o.domaine=:domaine" )

@NamedQuery(name="Organisme.getOrganismeById",query=" select o from Organisme o where o.idOrganisme=:id" )

@NamedQuery(name="Organisme.getOrganismeByLieu", query ="select o from Organisme o where o.lieu=:lieu")

@Data
@Entity
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Organisme")
public class Organisme {
    
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOrganisme")
    private Integer idOrganisme;
   
     @Column(name = "nom")
     private String nom;
     
     @Column(name = "contact")
     private String contact;
     
     @Column(name = "domaine")
     private String domaine;
     
     @Column(name = "lieu")
     private String lieu;
    
    @JsonIgnore
     @OneToMany(mappedBy="organisme")
     private List<Formation> formations;
    
}
