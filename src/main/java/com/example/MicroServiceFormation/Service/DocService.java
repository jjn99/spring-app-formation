package com.example.MicroServiceFormation.Service;

import com.example.MicroServiceFormation.Model.Doc;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface DocService {
    
    public ResponseEntity<List<Doc>> getDocByIdDemande(Integer idDemande);
    
     public Doc getDocById(Integer idDoc);
     
     public ResponseEntity<List<Doc>> getAllDoc();
     
     public ResponseEntity<String> addDoc(String numeroDemande, MultipartFile file);
}
