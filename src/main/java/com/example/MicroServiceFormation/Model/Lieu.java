package com.example.MicroServiceFormation.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
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

@NamedQuery(name="Lieu.getLieuByPays",query=" select l from Lieu l where l.pays=:pays " )

@NamedQuery(name="Lieu.getLieuByLieu",query=" select l from Lieu l where l.ville=:ville " )

@NamedQuery(name="Lieu.getLieuById",query=" select l from Lieu l where l.idLieu=:id" )

@Data
@Entity
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Lieu")
public class Lieu implements Serializable {
    
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLieu")
    private Integer idLieu;
    
    @Column(name="pays")
    private String pays;
    
    @Column(name = "ville")    
    private String ville;
    

     
}
