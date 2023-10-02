package com.example.MicroServiceFormation.Dao;

import com.example.MicroServiceFormation.Model.AppRoles;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RolesDao extends JpaRepository<AppRoles,Long>{
    
    AppRoles findByRoleName(String roleName);
    
}
