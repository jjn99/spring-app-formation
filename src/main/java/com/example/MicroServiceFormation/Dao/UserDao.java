package com.example.MicroServiceFormation.Dao;

import com.example.MicroServiceFormation.Model.AppUsers;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<AppUsers,Long>{

    public AppUsers findByUsername(String username);

     public AppUsers getByUsername(String username);
    
    public AppUsers findByIdUser(Long id);
    
    public AppUsers findByEmail(String email);
    
}
