package com.example.MicroServiceFormation.Service;

import com.example.MicroServiceFormation.Model.Session;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface SessionService {
    
    public ResponseEntity<String> addSession(@RequestBody Map<String,String> requestMap);
    public ResponseEntity<String> updateSession(@RequestBody Map<String,String> requestMap);
    public ResponseEntity<List<Session>> getAllSession();
    public ResponseEntity<List<Session>> getSessionByIdFormation(Map<String, String> requestMap);
    public ResponseEntity<Session> getSessionById(Map<String, String> requestMap);
    public ResponseEntity<String> deleteSession(Integer idSession);

   // public ResponseEntity<List<Session>> findSessionByIdFormation(Integer idFormation);
    
}
