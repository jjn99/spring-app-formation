package com.example.MicroServiceFormation.Dao;

import com.example.MicroServiceFormation.Model.Session;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionDao extends JpaRepository<Session, Integer>{

    public Session getSessionById(Integer id);

    public List<Session> getSessionByIdFormation(Integer id);
}
