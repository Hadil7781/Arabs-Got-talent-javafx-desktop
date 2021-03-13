/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Khawla
 */
public class Participationn {
    private int id;
    private int evanement_id;
    private String etat;
    private String prenom;
    private String Email;
    private int Cin;
    private int numTel;
    private int User_id;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    

    public Participationn(String nom, String username, String titre) {
        this.etat = nom;
        this.username = username;
        this.titre = titre;
    }

    private String titre;

    public Participationn(String nom, String prenom, String Email, int Cin, int numTel, int User_id) {
        this.etat = nom;
        this.prenom = prenom;
        this.Email = Email;
        this.Cin = Cin;
        this.numTel = numTel;
        this.User_id = User_id;
    }

    public int getEvanement_id() {
        return evanement_id;
    }

    public void setEvanement_id(int evanement_id) {
        this.evanement_id = evanement_id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    public Participationn(int id, int evanement_id, String nom, String prenom, String Email, int Cin, int numTel, int User_id) {
        this.id = id;
        this.evanement_id = evanement_id;
        this.etat = nom;
        this.prenom = prenom;
        this.Email = Email;
        this.Cin = Cin;
        this.numTel = numTel;
        this.User_id = User_id;
    }

    public Participationn(int evanement_id, String nom, int User_id, String username) {
        this.evanement_id = evanement_id;
        this.etat = etat;
        this.User_id = User_id;
        this.username = username;
    }
    
    

    public Participationn(int id, int evanement_id, int User_id, String username) {
        this.id = id;
        this.evanement_id = evanement_id;
        this.User_id = User_id;
        this.username = username;
    }
    

    public Participationn(int evanement_id, String etat, int User_id) {
        this.evanement_id = evanement_id;
        this.etat = etat;
        this.User_id = User_id;
    }
    
    
    public Participationn() {
        
    }

    @Override
    public String toString() {
        return "Participation{" + "id=" + id + ", etat=" + etat + ", username=" + username + ", titre=" + titre + '}';
    }

 
    public Participationn(int evanement_id, String nom, String prenom, String Email, int Cin, int numTel, int User_id) {
        this.evanement_id = evanement_id;
        this.etat = nom;
        this.prenom = prenom;
        this.Email = Email;
        this.Cin = Cin;
        this.numTel = numTel;
        this.User_id = User_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  
    public String getEtat() {
        return etat;
    }

    public void setEtat(String nom) {
        this.etat = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getCin() {
        return Cin;
    }

    public void setCin(int Cin) {
        this.Cin = Cin;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int User_id) {
        this.User_id = User_id;
    }

    
    
    
}
