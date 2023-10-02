package com.example.MicroServiceFormation.ServiceImpl;

import com.example.MicroServiceFormation.Service.FichierInscriptionService;
import com.example.MicroServiceFormation.Utils.DfpUtils;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Map;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/*@Slf4j
@Service*/
public class FichierInscriptionServiceImpl implements FichierInscriptionService {
/*
    @Autowired
    FichierInscriptionDao fichierInscriptionDao;
    
    @Override
    public ResponseEntity<String> generateInscription(Map<String, Object> requestMap) {
        log.info("Dans generateInscription");
        try{
            String fileName;
            if(validateRequestMap(requestMap)){
                if(requestMap.containsKey("isGenerate") && !(boolean) requestMap.get("isGenerate")){
                String file=(String) requestMap.get("numFiche");
                fileName="FICHE D'INSCRIPTION N°"+file ;
                }else{
                    String file=(String) requestMap.get("numFiche");
                fileName="FICHE D'INSCRIPTION N°"+file ;
                requestMap.put("numFiche", file);
                insertFichierInscription(requestMap);
                }
                
                String data1= "Libellé de la formation: " +requestMap.get("libelleFormation")+ "\n"+"Organisme de formation: "+requestMap.get("organismeDetails");
                String data2="But de la formation: Renforcement de capacités\n"+requestMap.get("objectif");
                
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(DfpConstants.Store_Location+"\\"+fileName+".pdf"));
                
                document.open();
                
                Paragraph p1= new Paragraph("SOCIETE NATIONALE D'ELECTRICITE ", getFont("Header"));
                p1.setAlignment(Element.ALIGN_LEFT);
                Paragraph p2= new Paragraph("DU BURKINA-SONABEL ", getFont("Header"));
                p2.setAlignment(Element.ALIGN_LEFT);
                Paragraph p3= new Paragraph("-------------------------", getFont("Header"));
                p3.setAlignment(Element.ALIGN_LEFT);
                Paragraph p4= new Paragraph("DIRECTION DES RESSOURCES HUMAINES", getFont("Header"));
                p4.setAlignment(Element.ALIGN_LEFT);
                Paragraph p5= new Paragraph("-------------------------", getFont("Header"));
                p5.setAlignment(Element.ALIGN_LEFT);
                Paragraph p6= new Paragraph("DEPARTEMENT FORMATION ET PERFECTIONNEMENT", getFont("Header"));
                p6.setAlignment(Element.ALIGN_LEFT);
                Date date=new Date();
                Paragraph p7= new Paragraph("Ouagadougou, le"+date);
                p7.setAlignment(Element.ALIGN_RIGHT);
                Paragraph p8= new Paragraph("FICHE D'INSCRIPTION N°"+fileName, getFont("Header"));
                p8.setAlignment(Element.ALIGN_CENTER);
                Paragraph p9= new Paragraph("I. A REMPLIR PAR LE SERVICE GESTION DE LA FORMATION ET DU PERFECTIONNEMENT", getFont("Header"));
                p9.setAlignment(Element.ALIGN_LEFT);
                Paragraph p10= new Paragraph("II. A REMPLIR PAR LE STAGIAIRE ET SES RESPONSABLES", getFont("Header"));
                p10.setAlignment(Element.ALIGN_LEFT);
                
                
                document.add(p1);
                document.add(p2);
                document.add(p3);
                document.add(p4);
                document.add(p5);
                document.add(p6);
                document.add(p7);
                document.add(p8);
                document.add(p9);
                
                Paragraph paragraph = new Paragraph(data1+"\n \n",getFont("data1"));
                document.add(paragraph);
                
                
                //Lieu
                PdfPTable table = new PdfPTable(1);
                table.setWidthPercentage(25);
                addTableHeader1(table);
                
                
                //Lieu
                JSONArray jsonArray= DfpUtils.getJsonArrayFromString((String) requestMap.get("lieuDetails"));
                for(int i=0;i<jsonArray.length();i++)
                {
                    addRowsLieu(table, DfpUtils.getMapFromJson(jsonArray.getString(i)));
                }
                document.add(table);
                
                //Session
                PdfPTable table2 = new PdfPTable(4);
                table2.setWidthPercentage(65);
                addTableHeader2(table2);
                
                
                  //Session
                JSONArray jsonArrayS= DfpUtils.getJsonArrayFromString((String) requestMap.get("sessionDetails"));
                for(int i=0;i<jsonArrayS.length();i++)
                {
                    addRowsSession(table2, DfpUtils.getMapFromJson(jsonArrayS.getString(i)));
                }
                document.add(table2);
            
                
                //Cout
                PdfPTable table3 = new PdfPTable(5);
                table3.setWidthPercentage(100);
                addTableHeader3(table3);
                
                
                  //Cout
                JSONArray jsonArrayC= DfpUtils.getJsonArrayFromString((String) requestMap.get("coutDetails"));
                for(int i=0;i<jsonArrayC.length();i++)
                {
                    addRowsCout(table3, DfpUtils.getMapFromJson(jsonArrayC.getString(i)));
                }
                document.add(table3);
            
                
                //Budget
                PdfPTable table4 = new PdfPTable(4);
                table4.setWidthPercentage(100);
                addTableHeader4(table4);
                
                  //Budget
                JSONArray jsonArrayB= DfpUtils.getJsonArrayFromString((String) requestMap.get("budgetDetails"));
                for(int i=0;i<jsonArrayB.length();i++)
                {
                    addRowsBudget(table4, DfpUtils.getMapFromJson(jsonArrayB.getString(i)));
                }
                document.add(table4);
            
                document.add(p10);
              
                //Agent
                PdfPTable table5 = new PdfPTable(7);
                table5.setWidthPercentage(100);
                addTableHeader5(table5);
                
                
                  //Agent
                JSONArray jsonArrayA= DfpUtils.getJsonArrayFromString((String) requestMap.get("AgentDetails"));
                for(int i=0;i<jsonArrayA.length();i++)
                {
                    addRowsAgent(table5, DfpUtils.getMapFromJson(jsonArrayA.getString(i)));
                }
                document.add(table5);
            
                Paragraph paragraph2 = new Paragraph(data2+"\n \n",getFont("data2"));
                document.add(paragraph2);
                
                Paragraph paD= new Paragraph("DECISION DU DIRECTEUT GENERAL ", getFont("Header"));
                paD.setAlignment(Element.ALIGN_RIGHT);
                document.add(paD);
                
                Paragraph paS= new Paragraph("AVIS DU SG", getFont("Header"));
                paS.setAlignment(Element.ALIGN_CENTER);
                document.add(paS);
                
                document.close();
                return new ResponseEntity<>("{\"nom\":\""+fileName+"\"}", HttpStatus.OK);
            }
            
            return DfpUtils.getResponseEntity(DfpConstants.Invalid_Data, HttpStatus.BAD_REQUEST);
    
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DfpUtils.getResponseEntity(DfpConstants.Not_Print, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateRequestMap(Map<String, Object> requestMap) {
      return requestMap.containsKey("numFiche");
    }

    private void insertFichierInscription(Map<String, Object> requestMap) {
        try{
            FichierInscription fichierInscription = new FichierInscription();
            fichierInscription.setNumFiche((String)requestMap.get("numFiche"));
            fichierInscription.setAgentDetails((String)requestMap.get("AgentDetails"));
            fichierInscription.setBudgetDetails((String)requestMap.get("budgetDetails"));
            fichierInscription.setCoutsDetails((String)requestMap.get("coutsDetails"));
            fichierInscription.setLibelleFormation((String)requestMap.get("libelleFormation"));
            fichierInscription.setLieuDetails((String)requestMap.get("lieuDetails"));
            fichierInscription.setObjectif((String)requestMap.get("objectif"));
            fichierInscription.setOrganismeDetails((String)requestMap.get("organismeDetails"));
            fichierInscription.setSessionDetails((String)requestMap.get("sessionDetails"));
            fichierInscriptionDao.save(fichierInscription);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private Font getFont(String type) {
        log.info("Dans getFont");
        switch(type){
            case"Header":
                Font headerFont = FontFactory.getFont(FontFactory.TIMES_BOLD,12,BaseColor.BLACK);
                headerFont.setStyle(Font.BOLD);
                return headerFont;
            case "Data1":
                Font dataFount= FontFactory.getFont(FontFactory.TIMES_ROMAN,11,BaseColor.BLACK);
                dataFount.setStyle(Font.BOLD);
                return dataFount;
            case "Data2":
                Font dataFount2= FontFactory.getFont(FontFactory.TIMES_ROMAN,11,BaseColor.BLACK);
                dataFount2.setStyle(Font.BOLD);
                return dataFount2;
            
            default:
                return new Font();
        }
    }

    private void addTableHeader1(PdfPTable table) {
        log.info("Dans addTableHeader");
        Stream.of("Lieux: ")
                .forEach(columnTitle ->{
                PdfPCell header1= new PdfPCell();
                header1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header1.setBorder(2);
                header1.setPhrase(new Phrase(columnTitle));
                header1.setHorizontalAlignment(Element.ALIGN_LEFT);
                header1.setVerticalAlignment(Element.ALIGN_CENTER);
                table.addCell(header1);
                });
    }

    
    private void addTableHeader2(PdfPTable table) {
        log.info("Dans addTableHeader2");
        Stream.of("Date de depart","Date de Retour"," Date de debut session", " Date de fin session")
                .forEach(columnTitle ->{
                PdfPCell header1= new PdfPCell();
                header1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header1.setBorder(2);
                header1.setPhrase(new Phrase(columnTitle));
                header1.setHorizontalAlignment(Element.ALIGN_CENTER);
                header1.setVerticalAlignment(Element.ALIGN_CENTER);
                table.addCell(header1);
                });
    }

    
    private void addTableHeader3(PdfPTable table) {
        log.info("Dans addTableHeader3");
        Stream.of("Désignation","Coût U.","Qté","Nre.Participant","Montant")
                .forEach(columnTitle ->{
                PdfPCell header1= new PdfPCell();
                header1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header1.setBorder(2);
                header1.setPhrase(new Phrase(columnTitle));
                header1.setHorizontalAlignment(Element.ALIGN_LEFT);
                header1.setVerticalAlignment(Element.ALIGN_CENTER);
                table.addCell(header1);
                });
    }

    
    private void addTableHeader4(PdfPTable table) {
        log.info("Dans addTableHeader4");
        Stream.of("Budget ","Total","Montant","Reliquat")
                .forEach(columnTitle ->{
                PdfPCell header1= new PdfPCell();
                header1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header1.setBorder(2);
                header1.setPhrase(new Phrase(columnTitle));
                header1.setHorizontalAlignment(Element.ALIGN_LEFT);
                header1.setVerticalAlignment(Element.ALIGN_CENTER);
                table.addCell(header1);
                });
    }

    
    private void addTableHeader5(PdfPTable table) {
        log.info("Dans addTableHeader5");
        Stream.of("Nom & Prenom","Matricule","Classement","Fonction","Direction","Unite","Date départ retraite")
                .forEach(columnTitle ->{
                PdfPCell header1= new PdfPCell();
                header1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header1.setBorder(2);
                header1.setPhrase(new Phrase(columnTitle));
                header1.setHorizontalAlignment(Element.ALIGN_LEFT);
                header1.setVerticalAlignment(Element.ALIGN_CENTER);
                table.addCell(header1);
                });
    }

    private void addRowsLieu(PdfPTable table, Map<String, Object> data) {
        log.info("Dans addRowsLieu");
        table.addCell((String) data.get("lieuDetails"));
    }

    private void addRowsAgent(PdfPTable table5, Map<String, Object> data) {
        log.info("Dans addAgent");
        table5.addCell((String) data.get("Nom & Prenom"));
        table5.addCell((String) data.get("Matricule"));
        table5.addCell((String) data.get("Classement"));
        table5.addCell((String) data.get("Fonction"));
        table5.addCell((String) data.get("Direction"));
        table5.addCell((String) data.get("Unite"));
        table5.addCell((String) data.get("Date départ retraite"));
    }

    private void addRowsBudget(PdfPTable table4, Map<String, Object> data) {
        log.info("Dans addRowsBudget");
        table4.addCell((String) data.get("Budget"));
        table4.addCell((String) data.get("Total"));
        table4.addCell((String) data.get("Montant"));
        table4.addCell((String) data.get("Reliquat"));
    }

    private void addRowsCout(PdfPTable table3, Map<String, Object> data) {
        log.info("Dans addRowsCout");
        table3.addCell((String) data.get("Désignation"));
        table3.addCell((String) data.get("Coût U."));
        table3.addCell((String) data.get("Qté"));
        table3.addCell((String) data.get("Nre.Participant"));
        table3.addCell((String) data.get("Montant"));
    }

    private void addRowsSession(PdfPTable table2, Map<String, Object> data) {
        log.info("Dans addRowsSession");
        table2.addCell((String) data.get("Date de depart"));
        table2.addCell((String) data.get("Date de Retour"));
        table2.addCell((String) data.get("Date de debut session"));
        table2.addCell((String) data.get("Date de fin session"));
    }
  */  
}
