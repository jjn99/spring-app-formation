package com.example.MicroServiceFormation.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NamedQuery(name="AppUsers.findByIdUser",query=" select p from AppUsers p where p.id=:id" )
@NamedQuery(name="AppUsers.getByUsername",query=" select p from AppUsers p where p.username=:username" )

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUsers {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
    
    private String username;
    
    private String matricule;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    
    private String email;
    
    private String numeroTelephone;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<AppRoles> appRoles = new ArrayList<>();
    
    
}
