/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Khawla
 */
public class Publication {
    private int id;
    private int evenement_id;
    private String titre;
    private String contenu;
    private String description;
    private String categorie;
    private String file;
    private String createdadd;
    private int is_valid;
    private int isBlocked;
    private int nbLikeCoatch;
    private int nbLikeJurey;
    private int nbdislikeJurey;
    private int nbdislikeCoatch;
    private int User_id;
    private String title;
    
    String email;

    public Publication( int evenement_id,String titre, String file,int id) {
        this.titre = titre;
        this.file = file;
        this.id=id;
        this.evenement_id=evenement_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    
    
    

     public Publication(String titre, String contenu, String description, String categorie, String file,String createdadd,int evenement_id) {
        this.titre = titre;
        this.contenu = contenu;
        this.description = description;
        this.title=title;
        this.categorie = categorie;
        this.title=title;
        this.file = file;
        this. evenement_id= evenement_id; 
        this.createdadd=createdadd;
    }

    

    public Publication(int evenement_id,String titre, String contenu, String description, String categorie, String file,String createdadd) {
        this.titre = titre;
        this.contenu = contenu;
        this.description = description;
        this.categorie = categorie;
        this.file = file;
        this. evenement_id= evenement_id; 
        this.createdadd=createdadd;
    }

    public String getEmail() {
        return email;
    }

    public Publication(String titre,int evenement_id) {
        this.title =titre;
        this.evenement_id = evenement_id;
        
    }
   
    public Publication(int id,String titre, String file, String createdadd,String email,String title) {
        this.titre = titre;
        this.file = file;
                this.id=id;
                this.email=email;
                this.createdadd = createdadd;
                this.title=title;
    }

   
    

    public Publication(int id,int evenement_id,String titre, String contenu, String description, String categorie, String file,String createdadd) {
        this.titre = titre;
                this.id = id;

        this.contenu = contenu;
        this.description = description;
        this.categorie = categorie;
        this.file = file;
        this. evenement_id= evenement_id; 
        this.createdadd=createdadd;
    }
    
    public Publication() {
        
    }

    
    
    

    

    public int getId() {
        return id;
    }

    public int getEvenement_id() {
        return evenement_id;
    }

    public String getTitre() {
        return titre;
    }

    public String getContenu() {
        return contenu;
    }

    

    public String getDescription() {
        return description;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getFile() {
        return file;
    }

    public String getCreatedadd() {
        return createdadd;
    }

    public int getIs_valid() {
        return is_valid;
    }

    public int getIsBlocked() {
        return isBlocked;
    }

    public int getNbLikeCoatch() {
        return nbLikeCoatch;
    }

    public int getNbLikeJurey() {
        return nbLikeJurey;
    }

    public int getNbdislikeJurey() {
        return nbdislikeJurey;
    }

    public int getNbdislikeCoatch() {
        return nbdislikeCoatch;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEvenement_id(int evenement_id) {
        this.evenement_id = evenement_id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setCreatedadd(String createdadd) {
        this.createdadd = createdadd;
    }

    public void setIs_valid(int is_valid) {
        this.is_valid = is_valid;
    }

    public void setIsBlocked(int isBlocked) {
        this.isBlocked = isBlocked;
    }

    public void setNbLikeCoatch(int nbLikeCoatch) {
        this.nbLikeCoatch = nbLikeCoatch;
    }

    public void setNbLikeJurey(int nbLikeJurey) {
        this.nbLikeJurey = nbLikeJurey;
    }

    public void setNbdislikeJurey(int nbdislikeJurey) {
        this.nbdislikeJurey = nbdislikeJurey;
    }

    public void setNbdislikeCoatch(int nbdislikeCoatch) {
        this.nbdislikeCoatch = nbdislikeCoatch;
    }

    @Override
    public String toString() {
        return "Publication{" + "id=" + id + ", evenement_id=" + evenement_id + ", titre=" + titre + ", contenu=" + contenu + ", description=" + description + ", categorie=" + categorie + ", file=" + file + ", createdadd=" + createdadd + ", is_valid=" + is_valid + ", isBlocked=" + isBlocked + ", nbLikeCoatch=" + nbLikeCoatch + ", nbLikeJurey=" + nbLikeJurey + ", nbdislikeJurey=" + nbdislikeJurey + ", nbdislikeCoatch=" + nbdislikeCoatch + '}';
    }
    

    
    
    
    
    //

    
    
  
    
    
    
    
    
    
}
