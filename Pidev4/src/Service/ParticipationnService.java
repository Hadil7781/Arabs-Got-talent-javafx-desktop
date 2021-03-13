/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Evenement;
import Entity.Participationn;
import Outils.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Khawla
 */

public class ParticipationnService implements IParticipationn {
     Connection con;
     public ParticipationnService() {
        con= DBConnect.getInstance().getConnection();
        
    }
      public void ajouterParticipation(Participationn p){
            PreparedStatement st;
            String requete= "INSERT INTO participationn (evenement_id,etat,prenom,Email,cin,numtel,user_id) VALUES (?,?,?,?,?,?,?)";
            
            
           try { 
               
             st=con.prepareStatement(requete);
            st.setInt(1, p.getEvanement_id());
            st.setString(2, p.getEtat());
            st.setString(3, p.getPrenom());
            st.setString(4, p.getEmail());
            st.setInt(5, p.getCin());
            st.setInt(6, p.getNumTel());
             st.setInt(7, p.getUser_id());
            
            st.executeUpdate();
            System.out.println("La participation est validée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      
      
          public int getNbrParticipant() {
        System.out.println("******************************Nombre Part************************************");
        String sql =" SELECT COUNT(*) FROM participationn";
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

        
    public void updateEtat(Participationn e) {
        
        String sql = "UPDATE participationn SET etat = ? WHERE id = ?";
        
        try {
            PreparedStatement pc = con.prepareStatement(sql);
           
    
    //
            pc.setString(1, e.getEtat());
            pc.setInt(2, e.getId());
            if(            pc.executeUpdate()>0) 
            System.out.println("success update eta");
        }catch(Exception ex ) {
            ex.printStackTrace();
        }
    }
}
