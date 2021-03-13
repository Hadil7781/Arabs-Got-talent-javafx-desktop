/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entity.Evenement;
import Outils.DBConnect;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author Khawla
 */
public class EvenementService implements IEvenement{
    ObservableList<Evenement> obEvenemnt = FXCollections.observableArrayList();
    Connection con;
    String fileNameServer;
    FileUploader fileUploader = new FileUploader("localhost:8080/pidev-integration/web/uploads/");
    public EvenementService() {
        con= DBConnect.getInstance().getConnection();
    }
@Override
 public void ajoutEvenement(Evenement e) {
           String pattern = "yyyy-MM-dd";

             System.out.println("***********************ADD EVENT**************************");
             PreparedStatement st;
             String sql = "INSERT INTO evenement (title,description,localisation,etablissement,categories,datedebut,datefin,"
                            + "nombreMinParticipants,isPublic,prix,nombreMaxParticipants,imagepath,rating,nbactuel ) "
                            + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
             try{
            
                  DateTimeFormatter outputDateFormat =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
                  DateTimeFormatter outputDateFormat1 =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
                  LocalDate dateTime = LocalDate.parse(e.getDatedebut(), outputDateFormat);
                  LocalDate dateTime1 = LocalDate.parse(e.getDatefin(), outputDateFormat);
                  st = con.prepareStatement(sql);
                  //  e.image type file 
                  //st.setInt(1,e.getId());
                  st.setString(1, e.getTitle());
                  st.setString(2, e.getDescription());
                  st.setString(3, e.getLocalisation());
                  st.setString(4,e.getEtablissement());
                  st.setString(5,e.getCategories());
                  st.setString(6, dateTime.toString());
                  st.setString(7,dateTime1.toString());
                  st.setInt(8, e.getNombreMinParticipant());
                  st.setInt(9, e.getIsPublic());
                  st.setInt(10,e.getPrix());
                  st.setInt(11, e.getNombreMaxParticipant());
                  st.setInt(13, e.getRating());
                  st.setInt(14, e.getNbactuel());
                  
                  //Upload Image
                  
                  
                  if(e.getImagepath() != null) {
                     fileNameServer = fileUploader.upload(e.getImagepath());

                  }
                  System.out.println("this is my file ="+fileNameServer);
                  e.setImagepath(fileNameServer);
                  
                  st.setString(12, e.getImagepath());
                  st.executeUpdate();
                  System.out.println("added");
                  }catch(Exception ex) 
                               {
                                  JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());

                               }
        
        }
 
 
  
@Override
public void supprimerEvenement(int id){
    System.out.println("***********************Delete EVENT*****************************");

    String requete= "DELETE FROM evenement WHERE id=?";
    try{
          PreparedStatement statement = con.prepareStatement(requete);
          statement.setInt(1,id);
          statement.executeUpdate();
          System.out.println("Evenement  supprimée ");
    }catch (SQLException ex) {
            System.err.println(ex.getMessage());
         }
          
       
    }

@Override
public void modifierEvenement (Evenement e)
{
    System.out.println("******************************Update************************************");
    //String requete ="UPDATE evenement set title =? ,description =?,localisation =?,etablissement =?,categories =?,datedebut =?,datefin=?,"
    //    + "nombreMinParticipants =?,isPublic =?,prix =?,nombreMaxParticipants =?,imagepath =?,rating =?,nbactuel =? WHERE id=?";  
    String requete ="UPDATE evenement set title =? ,description =?,categories =?,datedebut =?,datefin=?,imagepath=? WHERE id=?";  
     try{
            PreparedStatement st= con.prepareStatement(requete);
            st.setString(1, e.getTitle());
            st.setString(2, e.getDescription());
            //st.setString(3, e.getLocalisation());
            //st.setString(4,e.getEtablissement());
            st.setString(3,e.getCategories());
            st.setString(4,e.getDatedebut());
            st.setString(5,e.getDatefin());
            //st.setInt(8, e.getNombreMinParticipant());
            //st.setInt(9, e.getIsPublic());
            //st.setInt(10,e.getPrix());
            // st.setInt(11, e.getNombreMaxParticipant());
            //st.setInt(13, e.getRating());
            //st.setInt(14, e.getNbactuel());
            st.setInt(7,e.getId());
            
            
            try {
              if(e.getImagepath() != null) {
                     fileNameServer = fileUploader.upload(e.getImagepath());

                  }
                  System.out.println("this is my file ="+fileNameServer);
                  e.setImagepath(fileNameServer);
                  
            
            }catch(Exception exx) {
                
            }
            
            st.setString(6, e.getImagepath());
            
            st.executeUpdate();
            System.out.println("Evenement  modifiée ");
        
        
         } catch (SQLException ex)
         {
                        Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        
         }
}

     

    @Override
  public boolean rechercherEvenementParTitre(String titre) {
      System.out.println("******************************Search************************************");
      boolean res=false;
      try{
        String requete1 ="SELECT * FROM evenement WHERE (title = ?  )"; 
        PreparedStatement statement = con.prepareStatement(requete1);
        statement.setString(1,titre );
        ResultSet rs = statement.executeQuery();
        if(rs.next())
                 {
                    res=true; 
                  }
        }catch (SQLException ex)
        {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex); 
        }
      return res;
      
}

    @Override
    public ObservableList<Evenement> afficherEvenements() {
        System.out.println("******************************Affichage************************************");
        PreparedStatement rs;
        // ArrayList<Evenement>ev= new ArrayList<Evenement>();
        try{
            rs = con.prepareStatement("SELECT * FROM evenement");
            ResultSet res =rs.executeQuery();
            while(res.next()) {
                int id =res.getInt(1);
                String title= res.getString("title");
                String des= res.getString("description");
                String localisation= res.getString("localisation");
                String etablissement= res.getString("etablissement");
                String categories= res.getString("categories");
                Date datedebut= res.getDate("datedebut");
                Date datefin= res.getDate("datefin");
                
                
                
                int nombreMinParticipants= res.getInt("nombreMinParticipants");
                int isPublic= res.getInt("isPublic");
                int prix=res.getInt("prix");
                int nombreMaxParticipants= res.getInt("nombreMaxParticipants");
                String imagepath= res.getString("imagepath");
                int rating= res.getInt("rating");                 
                int nbactuel= res.getInt("nbactuel");
                Evenement e =new Evenement(id,title,des,localisation,etablissement,rating,categories,
                new SimpleDateFormat("yyyy-MM-dd").format(datedebut),new SimpleDateFormat("yyyy-MM-dd").format(datefin),nombreMinParticipants,nombreMaxParticipants, isPublic,prix, imagepath,nbactuel);
                obEvenemnt.add(e);
            
            }
            for(Evenement evenement : obEvenemnt)
                 System.out.println("data ===>"+evenement.toString());

            
             }catch(Exception ex) {
                                    ex.printStackTrace();
                                   }
             return obEvenemnt;
    
    }
      @Override
    public ObservableList<Evenement> afficheEvenementsClient() {
        System.out.println("******************************Affichage************************************");
        PreparedStatement rs;
        // ArrayList<Evenement>ev= new ArrayList<Evenement>();
        try{
            rs = con.prepareStatement("SELECT * FROM evenement");
            ResultSet res =rs.executeQuery();
            while(res.next()) {
               int id = res.getInt(1);
                String title= res.getString("title");
                String categories= res.getString("categories");
                int prix= res.getInt("prix");
                String imagepath= res.getString("imagepath");
                int rating= res.getInt("rating");                 

                Evenement e =new Evenement(id,title,rating,categories,imagepath,prix);
                obEvenemnt.add(e);
            
            }
            for(Evenement evenement : obEvenemnt)
                 System.out.println("data for user ===>"+evenement.toString());

            
             }catch(Exception ex) {
                                    ex.printStackTrace();
                                   }
             return obEvenemnt;
    
    }
    public int getNbrEvemenement() {
        System.out.println("******************************Nombre Event************************************");
        String sql =" SELECT COUNT(*) FROM evenement";
        int count =0;
        try {
            PreparedStatement prs =con.prepareStatement(sql);
            ResultSet rs = prs.executeQuery();
            while(rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }

    @Override
    public void traiterEvenement(Evenement e) {
        
        String sql = "UPDATE evenement SET isPublic = ? , NombreMaxParticipants =? WHERE id = ?";
        
        try {
            PreparedStatement pc = con.prepareStatement(sql);
            
            pc.setInt(1, e.getIsPublic());
            pc.setInt(2, e.getNombreMaxParticipant());
            pc.setInt(3, e.getId());
            pc.executeUpdate();
        }catch(Exception ex ) {
            ex.printStackTrace();
        }
    }

    
    
    public void updateRating(Evenement e) {
        
        String sql = "UPDATE evenement SET rating = ? WHERE id = ?";
        
        try {
            PreparedStatement pc = con.prepareStatement(sql);
            
    
    //
            pc.setInt(1, e.getRating());
            pc.setInt(2, e.getId());
            if(            pc.executeUpdate()>0) 
            System.out.println("success update rating");
        }catch(Exception ex ) {
            ex.printStackTrace();
        }
    }
    
        
    public int getIdEventByTitle(String title) {
            String req = "SELECT * FROM evenement WHERE title=?";
            int id = 0;
            
            try{    
            PreparedStatement pre = con.prepareStatement(req);
            pre.setString(1, title);
               ResultSet res = pre.executeQuery();
               
               while(res.next()) {
                                   id = res.getInt("id");

               }
                
            }catch(Exception e) {
                e.printStackTrace();
            }
            return id;
       
    }
    
    
    //otlbni bb chbik btit:p
           

    
    
    
    //
                 
}