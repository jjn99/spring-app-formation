package com.example.MicroServiceFormation.ServiceImpl;

import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Dao.EntiteDao;
import com.example.MicroServiceFormation.Dao.FormationDao;
import com.example.MicroServiceFormation.Dao.LieuDao;
import com.example.MicroServiceFormation.Dao.OrganismeDao;
import com.example.MicroServiceFormation.Dao.PersonnelDao;
import com.example.MicroServiceFormation.Dao.SessionDao;
import com.example.MicroServiceFormation.Dao.SousEntiteDao;
import com.example.MicroServiceFormation.Model.Agents;
import com.example.MicroServiceFormation.Model.Entite;
import com.example.MicroServiceFormation.Model.Formation;
import com.example.MicroServiceFormation.Model.Organisme;
import com.example.MicroServiceFormation.Model.Session;
import com.example.MicroServiceFormation.Model.SousEntite;
import com.example.MicroServiceFormation.Service.FormationService;
import com.example.MicroServiceFormation.Utils.DfpUtils;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FormationServiceImpl implements FormationService{

    @Autowired
    PersonnelDao agentDao;
    
    @Autowired
    EntiteDao entiteDao;
    
    @Autowired
    SousEntiteDao sousEntiteDao;
    
    @Autowired
    FormationDao formationDao;
    
    @Autowired
    OrganismeDao organismeDao;
    
    @Autowired
    LieuDao lieuDao;
    
    @Autowired
    SessionDao sessionDao;
    
              
    private boolean validateFormation(Map<String, String> requestMap,boolean validateId){
        if(requestMap.containsKey("titre")
                && requestMap.containsKey("datePlan") && requestMap.containsKey("idOrganisme")
                && requestMap.containsKey("ville") && requestMap.containsKey("pays") ){
              if(requestMap.containsKey("idFormation") && validateId){
            return true;
            }
            else if(!validateId)
            {
             return true;
            }
        }
        return false;
    }
    
    private Formation getFormationFromMap(Map<String ,String> requestMap){
  
        Formation formation = new Formation();
        formation.setTotal(0);
        formation.setNumeroInscription(requestMap.get("numeroInscription"));
        formation.setType(requestMap.get("type"));
        formation.setTitre(requestMap.get("titre"));
        formation.setAvis(requestMap.get("avis"));
        formation.setZone(requestMap.get("zone"));
        formation.setDateCreation(requestMap.get("dateCreation"));
        formation.setDatePlan(requestMap.get("datePlan"));
        formation.setPays(requestMap.get("pays"));
        formation.setVille( requestMap.get("ville"));
        formation.setIdOrganisme(Integer.valueOf(requestMap.get("idOrganisme")));
        formation.setObjectif(requestMap.get("objectif"));
        formation.setStatut(requestMap.get("statut"));
        formation.setCategorieFormation(requestMap.get("categorieFormation"));
        return formation;
    }
    
    private Formation updateFormationFromMap(Formation formation ,Map<String, String > requestMap){
        formation.setNumeroInscription(requestMap.get("numeroInscription") == null ?
                formation.getNumeroInscription() : requestMap.get("numeroInscription"));
       formation.setAvis(requestMap.get("type") == null ? formation.getType() : requestMap.get("type"));
       formation.setAvis(requestMap.get("titre") == null ? formation.getTitre() : requestMap.get("titre"));
         formation.setAvis(requestMap.get("avis") == null ? formation.getAvis() : requestMap.get("avis"));
          formation.setZone(requestMap.get("zone") == null ? formation.getZone() : requestMap.get("zone"));
        formation.setDatePlan(requestMap.get("datePlan") == null ? formation.getDatePlan() : requestMap.get("datePlan"));
        if(requestMap.get("dure") != null){formation.setDure(Integer.parseInt(requestMap.get("dure")));}
        formation.setVille(requestMap.get("ville") == null ? formation.getVille() : requestMap.get("ville"));
        formation.setPays(requestMap.get("pays") == null ? formation.getPays() : requestMap.get("pays"));
        if(requestMap.get("idOrganisme") != null){formation.setIdOrganisme(Integer.parseInt(requestMap.get("idOrganisme")));}
        formation.setObjectif(requestMap.get("objectif") == null ? formation.getObjectif() : requestMap.get("objectif"));
        formation.setCategorieFormation(requestMap.get("categorieFormation") == null ? formation.getCategorieFormation() : requestMap.get("categorieFormation"));
        return formation;
    }
 
    @Override
    public ResponseEntity<String> addFormation(Map<String, String> requestMap) {
        try{
            
        if(validateFormation(requestMap,false)){
                Organisme organisme= organismeDao.getOrganismeById(Integer.valueOf(requestMap.get("idOrganisme")));
                if(organisme != null){
                    Formation formation = getFormationFromMap(requestMap);
                    formation.setOrganisme(organisme);
                    formationDao.save(formation);
                return DfpUtils.getResponseEntity(DfpConstantes.Created, HttpStatus.OK);
                }
                else{
                    return DfpUtils.getResponseEntity(DfpConstantes.Bad_Identifiant, HttpStatus.BAD_REQUEST);
                }
        }else{
            return DfpUtils.getResponseEntity(DfpConstantes.Invalid_Data, HttpStatus.BAD_REQUEST);
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Not_Created , HttpStatus.INTERNAL_SERVER_ERROR);
   
    }

    @Override
    public ResponseEntity<String> updateFormation(Map<String, String> requestMap) {
        try{
            
        if(requestMap.get("idFormation") != null){
            Integer id = Integer.valueOf(requestMap.get("idFormation"));
            Formation formation = formationDao.getByIdFormation(id);
            if(formation != null){
                 formation = updateFormationFromMap(formation,requestMap);
                 formation.setOrganisme(organismeDao.getOrganismeById(formation.getIdOrganisme()));
                 formationDao.save(formation);
                return DfpUtils.getResponseEntity(DfpConstantes.Modified, HttpStatus.OK);
            }else{
                return DfpUtils.getResponseEntity(DfpConstantes.Not_Existing, HttpStatus.BAD_REQUEST);
            }
        }else{
            return DfpUtils.getResponseEntity(DfpConstantes.Invalid_Data, HttpStatus.BAD_REQUEST);
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Not_Modified , HttpStatus.INTERNAL_SERVER_ERROR);
 
    }

    @Override
    public ResponseEntity<List<Formation>> getAllFormation() {
                         try{
            return new ResponseEntity<>(formationDao.findAll(),HttpStatus.OK);
        }catch(Exception ex){
            
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
    }

    @Override
    public ResponseEntity<Formation> getByNumeroInscription(String numeroInscription) {
                          try{
                return new ResponseEntity<>( formationDao.getByNumeroInscription(numeroInscription), HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Formation(), HttpStatus.INTERNAL_SERVER_ERROR);
 
    }
//
//    @Override
//    public ResponseEntity<List<Formation>> getByTitre(String titre) {
//                            try{
//            return new ResponseEntity<>(formationDao.getByTitre(titre),HttpStatus.OK);
//        }catch(Exception ex){
//            
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//  
//    }
//
//    @Override
//    public ResponseEntity<List<Formation>> getByStatut(String statut) {
//                       try{
//            return new ResponseEntity<>(formationDao.getByStatut(statut),HttpStatus.OK);
//        }catch(Exception ex){
//            
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//   }
//
//    @Override
//    public ResponseEntity<List<Formation>> getByType(String type) {
//                       try{
//            return new ResponseEntity<>(formationDao.getByType(type),HttpStatus.OK);
//        }catch(Exception ex){
//            
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//   }

    @Override
    public ResponseEntity<List<Formation>> getFormationByOrganisme(Integer idOrganisme) {
                       try{
            return new ResponseEntity<>(formationDao.getFormationByOrganisme(idOrganisme),HttpStatus.OK);
        }catch(Exception ex){
            
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
   }
//
//    @Override
//    public ResponseEntity<List<Formation>> getFormationByLieu(Integer idLieu) {
//                       try{
//            return new ResponseEntity<>(formationDao.getFormationByLieu(idLieu),HttpStatus.OK);
//        }catch(Exception ex){
//            
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @Override
//    public ResponseEntity<List<Formation>> getFormationByAvis(String avis) {
//                       try{
//            return new ResponseEntity<>(formationDao.getFormationByAvis(avis),HttpStatus.OK);
//        }catch(Exception ex){
//            
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//   }
//
//    @Override
//    public ResponseEntity<List<Formation>> getFormationByDatePlan(String datePlan) {
//                       try{
//            return new ResponseEntity<>(formationDao.getFormationByDatePlan(datePlan),HttpStatus.OK);
//        }catch(Exception ex){
//            
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//   }

    @Override
    public ResponseEntity<String> deleteFormation(Integer idFormation) {
                          try{               
                  Formation formation = formationDao.getByIdFormation(idFormation);
                if(formation != null){
                    formationDao.deleteById(idFormation);
                    return DfpUtils.getResponseEntity(DfpConstantes.Delete, HttpStatus.OK);
                }else{
                    DfpUtils.getResponseEntity(DfpConstantes.Not_Existing, HttpStatus.BAD_REQUEST);
                }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Not_Delete, HttpStatus.INTERNAL_SERVER_ERROR);

    }

//    @Override
//    public ResponseEntity<List<Formation>> getFormationByCategorieFormation(String categorie) {
//                      try{
//            return new ResponseEntity<>(formationDao.getFormationByCategorieFormation(categorie),HttpStatus.OK);
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//     }

    @Override
    public ResponseEntity<String> addAgentToFormation(Map<String, String> requestMap) {
        try{
            if(requestMap.get("idFormation") != null && requestMap.get("matricule") != null){
            Integer id = Integer.valueOf(requestMap.get("idFormation"));
            Formation formation = formationDao.getByIdFormation(id);
            String matricule = requestMap.get("matricule");
            if(formation != null){
                Agents agent = agentDao.getPersonnelByMatricule(matricule);
                if(!formation.getListAgents().contains(agent) == false){        
                formation.getListAgents().add(agent);
                formationDao.save(formation);
                return DfpUtils.getResponseEntity(DfpConstantes.OperationAjoutReussi, HttpStatus.OK);
            
                }else{
                 return DfpUtils.getResponseEntity(DfpConstantes.Existing, HttpStatus.OK);
            }
            }else{
                return DfpUtils.getResponseEntity(DfpConstantes.OperationAjoutEchoué, HttpStatus.BAD_REQUEST);
            }
            }else{
                return DfpUtils.getResponseEntity(DfpConstantes.Bad_Identifiant, HttpStatus.BAD_REQUEST);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> removeAgentToFormation(Map<String, String> requestMap) {
                try{
                     if(requestMap.get("idFormation") != null && requestMap.get("matricule") != null){
            Integer id = Integer.valueOf(requestMap.get("idFormation"));
            Formation formation = formationDao.getByIdFormation(id);
            String matricule = requestMap.get("matricule");
            if(formation != null){
                Agents agent = agentDao.getPersonnelByMatricule(matricule);
             if(formation.getListAgents().contains(agent) == true){        
                formation.getListAgents().remove(agent);
                formationDao.save(formation);
                return DfpUtils.getResponseEntity(DfpConstantes.OperationRemoveReussi, HttpStatus.OK);
            
                }else{
                 return DfpUtils.getResponseEntity(DfpConstantes.Not_Existing, HttpStatus.OK);
            }
            }else{
                return DfpUtils.getResponseEntity(DfpConstantes.OperationRemoveEchoué, HttpStatus.BAD_REQUEST);
            }
            }else{
                return DfpUtils.getResponseEntity(DfpConstantes.Bad_Identifiant, HttpStatus.BAD_REQUEST);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> addEntiteToFormation(Map<String, String> requestMap) {
                try{
                     if(requestMap.get("idFormation") != null && requestMap.get("idEntite") != null){
            Integer id = Integer.valueOf(requestMap.get("idFormation"));
            Formation formation = formationDao.getByIdFormation(id);
            String code = requestMap.get("idEntite");
            if(formation != null){
                Entite entite = entiteDao.getEntiteById(Integer.valueOf(code));
                if(formation.getListDirection().contains(entite) == false){
                   formation.getListDirection().add(entite);
                formationDao.save(formation);
                return DfpUtils.getResponseEntity(DfpConstantes.OperationAjoutReussi, HttpStatus.OK);
           }else{
                return DfpUtils.getResponseEntity(DfpConstantes.Existing, HttpStatus.OK);
            }
              }else{
                return DfpUtils.getResponseEntity(DfpConstantes.OperationAjoutEchoué, HttpStatus.BAD_REQUEST);
            }
            }else{
                return DfpUtils.getResponseEntity(DfpConstantes.Bad_Identifiant, HttpStatus.BAD_REQUEST);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> removeEntiteToFormation(Map<String, String> requestMap) {
        try{
           if(requestMap.get("idFormation") != null && requestMap.get("idEntite") != null){
            Integer id = Integer.valueOf(requestMap.get("idFormation"));
            Formation formation = formationDao.getByIdFormation(id);
            String code = requestMap.get("idEntite");
            if(formation != null){
                Entite entite = entiteDao.getEntiteById(Integer.valueOf(code));
                if(formation.getListDirection().contains(entite) == true){
                   formation.getListDirection().remove(entite);
                formationDao.save(formation);
                return DfpUtils.getResponseEntity(DfpConstantes.OperationRemoveReussi, HttpStatus.OK);
           }else{
                return DfpUtils.getResponseEntity(DfpConstantes.Not_Existing, HttpStatus.OK);
            }}else{
                return DfpUtils.getResponseEntity(DfpConstantes.OperationRemoveEchoué, HttpStatus.BAD_REQUEST);
            }}else{
                return DfpUtils.getResponseEntity(DfpConstantes.Bad_Identifiant, HttpStatus.BAD_REQUEST);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);

 
    }

    @Override
    public ResponseEntity<String> addSousEntiteToFormation(Map<String, String> requestMap) {
                        try{
             if(requestMap.get("idFormation") != null && requestMap.get("idSousEntite") != null){
            Integer id = Integer.valueOf(requestMap.get("idFormation"));
            Formation formation = formationDao.getByIdFormation(id);
            String code = requestMap.get("idSousEntite");
            if(formation != null){
                SousEntite entite = sousEntiteDao.getSousEntiteById(Integer.valueOf(code));
              if(!formation.getListDepartement().contains(entite) == false){
                   formation.getListDepartement().add(entite);
                formationDao.save(formation);
                return DfpUtils.getResponseEntity(DfpConstantes.OperationAjoutReussi, HttpStatus.OK);
           }else{
                return DfpUtils.getResponseEntity(DfpConstantes.Existing, HttpStatus.OK);
            }  }else{
                return DfpUtils.getResponseEntity(DfpConstantes.OperationAjoutEchoué, HttpStatus.BAD_REQUEST);
            }}else{
                return DfpUtils.getResponseEntity(DfpConstantes.Bad_Identifiant, HttpStatus.BAD_REQUEST);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> removeSousEntiteToFormation(Map<String, String> requestMap) {
                               try{
             if(requestMap.get("idFormation") != null && requestMap.get("idSousEntite") != null){
            Integer id = Integer.valueOf(requestMap.get("idFormation"));
            Formation formation = formationDao.getByIdFormation(id);
            String code = requestMap.get("idSousEntite");
            if(formation != null){
                SousEntite entite = sousEntiteDao.getSousEntiteById(Integer.valueOf(code));
               if(formation.getListDepartement().contains(entite) == true){
                   formation.getListDepartement().remove(entite);
                formationDao.save(formation);
                return DfpUtils.getResponseEntity(DfpConstantes.OperationRemoveReussi, HttpStatus.OK);
           }else{
                return DfpUtils.getResponseEntity(DfpConstantes.Not_Existing, HttpStatus.OK);
            }}else{
                return DfpUtils.getResponseEntity(DfpConstantes.OperationRemoveEchoué, HttpStatus.BAD_REQUEST);
            }}else{
                return DfpUtils.getResponseEntity(DfpConstantes.Bad_Identifiant, HttpStatus.BAD_REQUEST);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Operation, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<List<Formation>> getFormationByIdEntite(Integer idEntite) {
         try{
                  List<Formation> formationList = formationDao.findAll();
                  Entite entite = entiteDao.getEntiteById(idEntite);
                  List<Formation> formationListEntite = new ArrayList<>();
                  int i;
                  for(i=0;i<formationList.size();i++){
                      Formation formation = formationList.get(i);
                      if(formation.getListDirection().contains(entite)){
                          formationListEntite.add(formation);
                      }
                  }
                  return new ResponseEntity<>(formationListEntite,HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Formation>> getFormationByIdSousEntite(Integer idSousEntite) {
         try{
             
                  List<Formation> formationList = formationDao.findAll();
                  SousEntite entite = sousEntiteDao.getSousEntiteById(idSousEntite);
                  List<Formation> formationListEntite = new ArrayList<>();
                  int i;
                  for(i=0;i<formationList.size();i++){
                      Formation formation = formationList.get(i);
                      if(formation.getListDepartement().contains(entite)){
                          formationListEntite.add(formation);
                      }
                  }
                  return new ResponseEntity<>(formationListEntite,HttpStatus.OK);
         }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
    }

    @Override
    public ResponseEntity<List<Agents>> getAgentDisponisble(Date dateDebut) {
        List<Formation> formationList = formationDao.findAll();
        List<Agents> agents = agentDao.findAll();
        int i;
        for(i=0;i<formationList.size(); i++){
            Formation formation = formationList.get(i);
            List<Session> sessions = sessionDao.getSessionByIdFormation(formation.getIdFormation());
            int j;
            for(j=0;j<sessions.size();j++){
                Session session = sessions.get(j);
                if(session.getDateRetour() != null){
                    Date dateR = Date.from(Instant.parse(session.getDateRetour()));
                    log.info("", dateR);
                    if(dateDebut.before(dateR)){
                        Collection<Agents> agentsSupp = formation.getListAgents();
                        agents.removeAll(agentsSupp);
                    }
                }
            }
        }
        return new ResponseEntity<>(agents,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Formation> getByIdFormation(Integer idFormation) {
     try{
                return new ResponseEntity<>( formationDao.getByIdFormation(idFormation), HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Formation(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Formation>> getFormationByAgent(Integer idPersonnel) {
            try{
                List<Formation> formation = formationDao.findAll();
                List<Formation> formAgent = new ArrayList<>();
                Agents agent = agentDao.getPersonnelById(idPersonnel);
                if(agent == null){
                    return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST); 
                }
                else{
                    for(int i = 0; i < formation.size(); i++){
                        if(formation.get(i).getListAgents().contains(agent)){
                            formAgent.add(formation.get(i));
                        }
                    }
                    return new ResponseEntity<>(formAgent,HttpStatus.OK); 
                }
            }catch(Exception ex){
            ex.printStackTrace();
        }
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
