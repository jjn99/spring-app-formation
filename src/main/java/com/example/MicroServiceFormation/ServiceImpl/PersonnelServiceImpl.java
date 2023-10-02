package com.example.MicroServiceFormation.ServiceImpl;

import com.example.MicroServiceFormation.Dao.PersonnelDao;
import com.example.MicroServiceFormation.Constantes.DfpConstantes;
import com.example.MicroServiceFormation.Dao.FormationDao;
import com.example.MicroServiceFormation.Model.Agents;
import com.example.MicroServiceFormation.Model.Formation;
import com.example.MicroServiceFormation.Service.PersonnelService;
import com.example.MicroServiceFormation.Utils.DfpUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PersonnelServiceImpl implements PersonnelService{

    @Autowired
    PersonnelDao agentDao;
     @Autowired
    FormationDao formationDao;
    
               
    private boolean validateAgent(Map<String, String> requestMap,boolean validateId){
        if(requestMap.containsKey("matricule") && requestMap.containsKey("nomprenom")
                && requestMap.containsKey("unite") && requestMap.containsKey("emploi")
                && requestMap.containsKey("fonction") && requestMap.containsKey("categorie")
                && requestMap.containsKey("echelon")){
             if(requestMap.containsKey("id") && validateId){
            return true;
            }
            else if(!validateId)
            {
             return true;
            }
        }
        return false;
    }
    
    private Agents getAgentFromMap(Map<String ,String> requestMap){
  
        Agents agent = new Agents();
        agent.setCategorie(requestMap.get("categorie"));
        agent.setDateEntreServ(requestMap.get("dateEntreServ"));
        agent.setDateNaissance(requestMap.get("dateNaissance"));
        agent.setEchelon(requestMap.get("echelon"));
        agent.setDateSortieServ(requestMap.get("dateSortieServ"));
        agent.setEmploi(requestMap.get("emploi"));
        agent.setFonction(requestMap.get("fonction"));
        agent.setGenre(requestMap.get("genre"));
        agent.setMatricule(requestMap.get("matricule"));
        agent.setNomprenom(requestMap.get("nomprenom"));
        agent.setStatut("En-Activite");
        agent.setUnite(requestMap.get("unite"));
        
        return agent;
    }
    
//    public Agents StatutAgent(Agents agent){
//        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
//        String toDay = date.format(new Date());
//        Date ToDays= Date.from(Instant.parse(toDay));
//        Agents agents = agent;
//            if(agents.getDateSortieServ() != null){
//            Date dateRetraite = Date.from(Instant.parse(agent.getDateSortieServ()));
//            if(dateRetraite.before(ToDays)){
//                agents.setStatut("Retraité(e)");
//                return agents;
//            }
//            else{
//            agents.setStatut("En-Activité");
//            return agents;
//            }
//           }
//            return agents;
//        }
//    
    
    private Agents updateAgentFromMap(Agents agent ,Map<String, String > requestMap){
        agent.setCategorie(requestMap.get("categorie") == null ? agent.getCategorie(): requestMap.get("categorie"));
        agent.setDateEntreServ(requestMap.get("dateEntreServ") == null ? 
                agent.getDateEntreServ(): requestMap.get("dateEntreServ") );
        agent.setDateNaissance(requestMap.get("dateNaissance") == null ? 
                agent.getDateNaissance() : requestMap.get("dateNaissance"));
        agent.setDateSortieServ(requestMap.get("dateSortieServ") == null ?
                agent.getDateSortieServ() : requestMap.get("dateSortieServ"));
        agent.setEchelon(requestMap.get("echelon") == null ? agent.getEchelon() : requestMap.get("echelon"));
        agent.setEmploi(requestMap.get("emploi") == null ? agent.getEmploi() : requestMap.get("emploi"));
        agent.setFonction(requestMap.get("fonction") == null ? agent.getFonction() : requestMap.get("fonction"));
        agent.setGenre(requestMap.get("genre") == null ? agent.getGenre() : requestMap.get("genre"));
         agent.setStatut(requestMap.get("statut") == null ? agent.getStatut() : requestMap.get("statut"));
        agent.setMatricule(requestMap.get("matricule") == null ? agent.getMatricule() : requestMap.get("matricule"));
        agent.setNomprenom(requestMap.get("nomprenom") == null ? agent.getNomprenom() : requestMap.get("nomprenom"));
        agent.setUnite(requestMap.get("unite") == null ? agent.getUnite() : requestMap.get("unite"));
        return agent;
    }
    
    @Override
    public ResponseEntity<String> addPersonnel(Map<String, String> requestMap) {
                         log.info("Dans ajout d'un Agent", requestMap);
        try{
            
        if(validateAgent(requestMap,false)){
            Agents agent = agentDao.getPersonnelByMatricule(requestMap.get("matricule"));
            if(agent == null){
                Agents agentSave = getAgentFromMap(requestMap);
                agentDao.save(agentSave);
                return DfpUtils.getResponseEntity(DfpConstantes.Created, HttpStatus.OK);
        
            }else{
            return DfpUtils.getResponseEntity(DfpConstantes.Invalid_Data, HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<String> updatePersonnel(Map<String, String> requestMap) {
                            log.info("Dans Modification d'un agent", requestMap);
        try{
            
        if(requestMap.get("idPersonnel") != null){
            Integer id = Integer.parseInt(requestMap.get("idPersonnel"));
            Agents agent = agentDao.getPersonnelById(id);
            if(agent != null){
                Agents agentSave= updateAgentFromMap(agent,requestMap);
                agentDao.save(agentSave);
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
    public ResponseEntity<List<Agents>> getAllPersonnel() {
                            try{
            return new ResponseEntity<>(agentDao.findAll(),HttpStatus.OK);
        }catch(Exception ex){
            
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<Agents> getPersonnelByMatricule(String matricule) {
                            try{
                return new ResponseEntity<>( agentDao.getPersonnelByMatricule(matricule), HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Agents(), HttpStatus.INTERNAL_SERVER_ERROR);
 

    }

    @Override
    public ResponseEntity<Agents> getPersonnelById(Integer idPersonnel) {
                            try{
                return new ResponseEntity<>( agentDao.getPersonnelById(idPersonnel), HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Agents(), HttpStatus.INTERNAL_SERVER_ERROR);
 

    }
//
//    @Override
//    public ResponseEntity<List<Agents>> getPersonnelByNomPrenom(Map<String,String> requestMap) {
//                            try{
//                                String nomprenom=requestMap.get("nomprenom");
//            return new ResponseEntity<>(agentDao.getPersonnelByNomPrenom(nomprenom),HttpStatus.OK);
//        }catch(Exception ex){
//            
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//
//    }
//
//    @Override
//    public ResponseEntity<List<Agents>> getPersonnelByUnite(String unite) {
//                            try{
//            return new ResponseEntity<>(agentDao.getPersonnelByUnite(unite),HttpStatus.OK);
//        }catch(Exception ex){
//            
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//
//    }
//
//    @Override
//    public ResponseEntity<List<Agents>> getPersonnelByStatut(String statut) {
//                            try{
//            return new ResponseEntity<>(agentDao.getPersonnelByStatut(statut),HttpStatus.OK);
//        }catch(Exception ex){
//            
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//
//    }
//
//    @Override
//    public ResponseEntity<List<Agents>> getPersonnelByEmploi(String emploi) {
//                            try{
//            return new ResponseEntity<>(agentDao.getPersonnelByEmploi(emploi),HttpStatus.OK);
//        }catch(Exception ex){
//            
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//
//    }
//
//    @Override
//    public ResponseEntity<List<Agents>> getPersonnelByFonction(String fonction) {
//                            try{
//            return new ResponseEntity<>(agentDao.getPersonnelByFonction(fonction),HttpStatus.OK);
//        }catch(Exception ex){
//            
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//
//    }
//
//    @Override
//    public ResponseEntity<List<Agents>> getPersonnelByCategorie(String categorie) {
//                            try{
//            return new ResponseEntity<>(agentDao.getPersonnelByCategorie(categorie),HttpStatus.OK);
//        }catch(Exception ex){
//            
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//
//    }
//
//    @Override
//    public ResponseEntity<List<Agents>> getPersonnelByEchelon(String echelon) {
//                            try{
//            return new ResponseEntity<>(agentDao.getPersonnelByEchelon(echelon),HttpStatus.OK);
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//
//    }

    @Override
    public ResponseEntity<String> deletePersonnel(Integer idPersonnel) {
                            try{
            
              Agents agent = agentDao.getPersonnelById(idPersonnel);
                if(agent != null){
                    agentDao.deleteById(idPersonnel);
                    return DfpUtils.getResponseEntity(DfpConstantes.Delete, HttpStatus.OK);
                }else{
                    DfpUtils.getResponseEntity(DfpConstantes.Not_Existing, HttpStatus.BAD_REQUEST);
                }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstantes.Not_Delete, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    
       @Override
    public ResponseEntity<List<Formation>> getFormationsByAgent(Integer idPersonnel) {
       try{
             List<Formation> formationList= new ArrayList<>();
             List<Formation> formationListAgent= new ArrayList<>();
             formationList = formationDao.findAll();
             int i;
             for(i=0; i< formationList.size(); i++){
             Formation formation = formationList.get(i);
             Agents agent = agentDao.getPersonnelById(idPersonnel);
              if(formation.getListAgents().contains(agent)){
              formationListAgent.add(formation);
                                    }
                                }
                                return new ResponseEntity<>(formationListAgent,HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);

    }
    
}
