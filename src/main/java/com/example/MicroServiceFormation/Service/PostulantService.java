package com.example.MicroServiceFormation.Service;

import com.example.MicroServiceFormation.Model.Postulant;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;

public interface PostulantService {

    public ResponseEntity<String> addPostulant(Map<String, String> requestMap);

    public ResponseEntity<String> updatePostulant(Map<String, String> requestMap);

    public ResponseEntity<List<Postulant>> getAllPostulant();

    public ResponseEntity<Postulant> getPostulantByMail(String mail);
    
}
