package com.example.MicroServiceFormation.ServiceImpl;

import com.example.MicroServiceFormation.Dao.DemandeDao;
import com.example.MicroServiceFormation.Dao.EntiteDao;
import com.example.MicroServiceFormation.Dao.FormationDao;
import com.example.MicroServiceFormation.Dao.OrganismeDao;
import com.example.MicroServiceFormation.Dao.PersonnelDao;
import com.example.MicroServiceFormation.Dao.PlannifierDao;
import com.example.MicroServiceFormation.Dao.PostulantDao;
import com.example.MicroServiceFormation.Dao.SalleDao;
import com.example.MicroServiceFormation.Dao.SousEntiteDao;
import com.example.MicroServiceFormation.Dao.UserDao;
import com.example.MicroServiceFormation.Model.Demande;
import com.example.MicroServiceFormation.Model.Stage;
import com.example.MicroServiceFormation.Service.DashboardService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    FormationDao formation;
    
    @Autowired
    PersonnelDao agent;
    
    @Autowired
    EntiteDao entite;
    
    @Autowired
    SousEntiteDao sousEntite;
    
    @Autowired
    OrganismeDao organisme;
    
    @Autowired
    SalleDao salle;
    
    @Autowired
    PlannifierDao plannifier;
    
    @Autowired
    UserDao user;
    
    @Autowired
    PostulantDao postulant;
    
    @Autowired
    DemandeDao demande;
    
    @Override
    public ResponseEntity<Map<String, Object>> getCount() {
        Map<String,Object> map = new HashMap<>();
        map.put("formation",formation.count());
        map.put("personnel", agent.count());
        map.put("entite", entite.count());
        map.put("sousEntite", sousEntite.count());
        map.put("organisme", organisme.count());
        map.put("salle", salle.count());
        map.put("planning",plannifier.count());
        map.put("user",user.count());
        map.put("postulant", postulant.count());
        map.put("demande", demande.count());
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getStageCount() {
        Map<String,Object> map = new HashMap<>();
        List<Demande> stages = demande.findAll();
        
        for(int i =0; i < stages.size() ; i++){
            
        }
        
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
    
}
