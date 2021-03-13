/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Evenement;
import Entity.Publication;
import Outils.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Khawla
 */
public class PublicationService  implements IPublication{
      
    Connection con;
       String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());   
       ObservableList<Publication> obPublication = FXCollections.observableArrayList();
       ArrayList<Publication>publication = new ArrayList<>();

    public PublicationService() {
        con= DBConnect.getInstance().getConnection();
    }
    /*******************************************************************************************/
    
    @Override
    public void ajouterPublication(Publication p)
    {
        System.out.println("***********************ADD Publication**************************");
        PreparedStatement st;
        String requete ="INSERT INTO publication (evenement_id,titre,contenu,description,categorie,file,createdadd,is_valid,"
                + "isBlocked,nbLikeCoatch,nbLikeJurey,nbdislikeJurey,nbdislikeCoatch) "
                + "      VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
             st = con.prepareStatement(requete);
             st.setInt(1,p.getEvenement_id());
             st.setString(2, p.getTitre());
             st.setString(3, p.getContenu());
             st.setString(4, p.getDescription());
             st.setString(5,p.getCategorie());
             st.setString(6,p.getFile());
             st.setString(7, date);
             //String createdAt = nw SimpleDateFormat("yyyy-MM-dd").format(p.getCreatedadd());    
            // st.setString(7,createdAt  );
             st.setInt(8,p.getIs_valid());
             st.setInt(9,p.getIsBlocked());
             st.setInt(10,p.getNbLikeCoatch());
             st.setInt(11,p.getNbLikeJurey());
             st.setInt(12,p.getNbdislikeJurey());
             st.setInt(13,p.getNbdislikeCoatch());
             st.executeUpdate();
             System.out.println("added");
             
        }catch(Exception ex) {
                               Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
                             }
    }
     /*******************************************************************************************/
   @Override
    public void supprimerPublication(int id) {
      System.out.println("***********************Delete*****************************");
      String requete= "DELETE FROM publication WHERE id=?";
      try{
             PreparedStatement statement = con.prepareStatement(requete);
             statement.setInt(1,id);
             statement.executeUpdate();
             System.out.println("publication supprimée ");
          }catch (SQLException ex) {
                                     System.err.println(ex.getMessage());
                                   }
          
    }
     /*******************************************************************************************/
    @Override
    public void modifierPublication(Publication p) {
       System.out.println("******************************Update************************************");
       String requete ="UPDATE publication SET titre =? ,file=?  WHERE id=?"; 
        try{
        PreparedStatement st= con.prepareStatement(requete);
        //st.setInt(1, p.getEvenement_id());
        st.setString(1, p.getTitre());
        st.setString(2,p.getFile());
      
        st.setInt(3, p.getId());  
        st.executeUpdate();
        System.out.println("Publication  modifiée ");
        
        } catch (SQLException ex) {
            ex.printStackTrace();
                                   }
    }
     /*******************************************************************************************/

    
    
    
    @Override
    public ObservableList<Publication> afficherPublication() {
        System.out.println("******************************Affichage************************************");
        PreparedStatement rs;
        String sql  ="SELECT * FROM publication P "
                 + "JOIN fos_user F ON F.id = P.User_id "
                 + "JOIN evenement E ON E.id = P.evenement_id ";
        try{
            rs = con.prepareStatement(sql);
            ResultSet res =rs.executeQuery();
            while(res.next()) {
                int id =res.getInt(1);
                int evenement_id=res.getInt("evenement_id");
                String title= res.getString("titre");
                //String cont= res.getString("contenu");
                // String desc= res.getString("description");
                // String cat= res.getString("categorie");
                String file= res.getString("file");
                Date created= res.getDate("createdadd");
                String email = res.getString("email");
                String titreE = res.getString("title");
                Publication e =new Publication(id,title,file,new SimpleDateFormat("yyyy-MM-dd").format(created),email,titreE);
                System.out.println("email = "+email+"\n titre evenement ="+titreE);
                obPublication.add(e);
             }
            
               for(Publication publication : obPublication)
                    System.out.println("data ===>"+publication.toString());
                }catch(Exception ex) {
                                       ex.printStackTrace();
                                      }
    
                 return obPublication;
    
    }
     /*******************************************************************************************/

     public int getNbrPublication() {
        System.out.println("******************************Nombre publication************************************");
        String sql =" SELECT COUNT(*) FROM publication";
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
    public ArrayList<Publication> getEvenementByPublication() {
         PreparedStatement rs;
         String titreevenement;
        String sql  ="SELECT * FROM publication P "
                 + "JOIN evenement E ON E.id = P.evenement_id ";
        try{
            rs = con.prepareStatement(sql);
            ResultSet res =rs.executeQuery();
            while(res.next()) {
                
                String titreE = res.getString("title");
                int idPub  = res.getInt("id");
                int id= res.getInt("evenement_id");
                                Publication e =new Publication(titreE,id);
                                //titreevenement=e.getTitle();
                                publication.add(e);
                                //System.out.println("list de ev ="+publication.toString());

         }
        }catch(Exception e) {
                 e.printStackTrace();
                 }
            
                           return publication;

}
    

    
       @Override
    public void traiterPublication(Publication p) {
        
        String sql = "UPDATE publication SET isBlocked = ? WHERE id = ?";
        
        try {
            
            PreparedStatement pc = con.prepareStatement(sql);
            
            pc.setInt(1, p.getIsBlocked());
            pc.setInt(2, p.getId());
            pc.executeUpdate();
        }catch(Exception ex ) {
            ex.printStackTrace();
        }
    }

 
                 
    
}
    
    

    

