package com.example.MicroServiceFormation.ServiceImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Dao.DemandeDao;
import com.example.MicroServiceFormation.Dao.DocDao;
import com.example.MicroServiceFormation.Model.Demande;
import com.example.MicroServiceFormation.Model.Doc;
import com.example.MicroServiceFormation.Service.DocService;
import com.example.MicroServiceFormation.Utils.DfpUtils;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class DocServiceImpl implements DocService{

    @Autowired
    DocDao docDao;
    
    @Autowired
    DemandeDao demandeDao;
    
    @Override
    public ResponseEntity<String> addDoc(String numeroDemande, MultipartFile file){
        try{
            String docName = file.getOriginalFilename();
            Demande demande = demandeDao.getDemandeByNumeroDemande(numeroDemande);
            Doc doc = new Doc();
            if(demande != null){
            doc.setDemande(demande);
            doc.setDocName(docName);
            doc.setDocType(file.getContentType());
            doc.setDonne(file.getBytes());
            docDao.save(doc);
            return DfpUtils.getResponseEntity(DfpConstantes.Created, HttpStatus.OK);
            }else{
            return DfpUtils.getResponseEntity(DfpConstantes.Invalid_Data, HttpStatus.BAD_REQUEST);
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
             return DfpUtils.getResponseEntity(DfpConstantes.Not_Created , HttpStatus.INTERNAL_SERVER_ERROR);
   
    }
    
    @Override
    public ResponseEntity<List<Doc>> getAllDoc() {
                           try{
            return new ResponseEntity<>(docDao.findAll(),HttpStatus.OK);
        }catch(Exception ex){
            
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
 
    }
    
    
    @Override
    public Doc getDocById(Integer idDoc) {
                           try{
                return  docDao.getDocById(idDoc);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;

    }
    
        @Override
        public ResponseEntity<List<Doc>> getDocByIdDemande(Integer idDemande) {
                           try{
                return new ResponseEntity<>( docDao.getDocByIdDemande(idDemande), HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
 

    }
    
}
