package com.example.MicroServiceFormation.ControllerImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Controller.DocRest;
import com.example.MicroServiceFormation.Model.Doc;
import com.example.MicroServiceFormation.Service.DocService;
import com.example.MicroServiceFormation.Utils.DfpUtils;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class DocRestImpl implements DocRest{

    @Autowired
    DocService docService;
    
    @Override
    public ResponseEntity<List<Doc>> getAllDoc() {
               try{
            return docService.getAllDoc();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
   
    }

    @Override
    public ResponseEntity<ByteArrayResource> getDocById(Integer idDoc) {
        
           Doc doc = docService.getDocById(idDoc);
           return ResponseEntity.ok()
                   .contentType(MediaType.parseMediaType(doc.getDocType()))
                   .header(HttpHeaders.CONTENT_DISPOSITION,"attachement: fileName=\""+doc.getDocName()+"\"")
                   .body(new ByteArrayResource(doc.getDonne()));
       }

    @Override
    public ResponseEntity<List<Doc>> getFileByIdDemande(Integer idDemande) {
                     try{
            return  docService.getDocByIdDemande(idDemande);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
    }

    @Override
    public ResponseEntity<String> addDoc(String numeroDemande, MultipartFile file) {
             try{
            docService.addDoc(numeroDemande,file);
            return DfpUtils.getResponseEntity(DfpConstantes.Created, HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
   
    }
    
}
