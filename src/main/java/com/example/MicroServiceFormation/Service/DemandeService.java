package com.example.MicroServiceFormation.Service;

import com.example.MicroServiceFormation.Model.Demande;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;

public interface DemandeService {

    public ResponseEntity<String> addDemande(Map<String, String> requestMap);

    public ResponseEntity<String> updateDemande(Map<String, String> requestMap);

    public ResponseEntity<List<Demande>> getAllDemande();

    public ResponseEntity<List<Demande>> getDemandeByIdPostulant(Integer idPostulant);

    public void sendSimpleMessage(Map<String, String> requestMap);

    public ResponseEntity<Demande> getDemandeByNumeroDemande(String numeroDemande);
    
}
