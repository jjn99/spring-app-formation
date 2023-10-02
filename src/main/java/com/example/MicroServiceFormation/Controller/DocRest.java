
package com.example.MicroServiceFormation.Controller;

import com.example.MicroServiceFormation.Model.Doc;
import java.util.List;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/Doc")
public interface DocRest {
    
     @PostMapping("/addDoc/{numeroDemande}")
     public ResponseEntity<String> addDoc(@PathVariable String numeroDemande,@RequestBody MultipartFile file);
    
    
     @GetMapping("/getAllDoc")
    public ResponseEntity<List<Doc>> getAllDoc();
    
     @GetMapping("/TelechargerFile/{idDoc}")
     public ResponseEntity<ByteArrayResource> getDocById(Integer idDoc);
     
     @GetMapping("/getFileByIdDemande/{idDemande}")
     public ResponseEntity<List<Doc>> getFileByIdDemande(@PathVariable Integer idDemande);
     
     
    
}
