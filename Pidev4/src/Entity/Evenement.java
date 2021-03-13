/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.File;
 
/**
 *
 * @author Khawla
 */
public class Evenement {
    
    private int id ;
    private String title;
    private String description;;
    private String localisation;
    private String etablissement;
    private int rating;
    private String categories;
    private String datedebut ;
    private String datefin;
    private int NombreMinParticipants;
    private int NombreMaxParticipants;
    private int isPublic;
    private String imagepath;
    private int nbactuel;
    //private File image;
      private int prix;

    public Evenement(int id,String title, int rating, String categories, String imagepath,int prix) {
        this.title = title;
        this.rating = rating;
        this.prix=prix   ;
        this.categories = categories;
        this.imagepath = imagepath;
        this.id=id;
    }

      
      
    public Evenement(int id, String title, String description, String localisation, String etablissement, int rating, String categories, String datedebut, String datefin, int NombreMinParticipants, int NombreMaxParticipants, int isPublic, int prix, String imagepath, int nbactuel) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.localisation = localisation;
        this.etablissement = etablissement;
        this.rating = rating;
        this.categories = categories;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.NombreMinParticipants = NombreMinParticipants;
        this.NombreMaxParticipants = NombreMaxParticipants;
        this.isPublic = isPublic;
        this.prix = prix;
        this.imagepath = imagepath;
        this.nbactuel = nbactuel;
    }

    public Evenement(String title, String description, String localisation,String categories, String datedebut, String datefin, int NombreMinParticipants, int NombreMaxParticipants, String imagepath, int prix) {
        
        this.title = title;
        this.description = description;
        this.categories = categories;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.NombreMinParticipants = NombreMinParticipants;
        this.NombreMaxParticipants = NombreMaxParticipants;
        this.imagepath = imagepath;
        this.prix = prix;
        this.localisation=localisation;    }
    
     

    
    public Evenement(String title, String description, String localisation, String etablissement, int rating, String categories, String datedebut, String datefin, int NombreMinParticipants, int NombreMaxParticipants, int isPublic, int prix, String imagepath, int nbactue) {
        this.title = title;
        this.description = description;
        this.localisation = localisation;
        this.etablissement = etablissement;
        this.rating = rating;
        this.categories = categories;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.NombreMinParticipants = NombreMinParticipants;
        this.NombreMaxParticipants = NombreMaxParticipants;
        this.isPublic = isPublic;
        this.prix = prix;
        this.imagepath = imagepath;
        this.nbactuel = nbactuel;
    }

   
    public Evenement(int id ,String title,String description,String categories,String datedebut,String datefin,String imagepath){
        this.id = id;
        this.title = title;
        this.description = description;
                this.imagepath = imagepath;
                       this.datedebut = datedebut;
             this.categories = categories;

        this.datefin = datefin;

    }
            
            

    public int getNombreMinParticipants() {
        return NombreMinParticipants;
    }

    public void setNombreMinParticipants(int NombreMinParticipants) {
        this.NombreMinParticipants = NombreMinParticipants;
    }

    public int getNombreMaxParticipants() {
        return NombreMaxParticipants;
    }

    public void setNombreMaxParticipants(int NombreMaxParticipants) {
        this.NombreMaxParticipants = NombreMaxParticipants;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

   
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    public String getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(String datedebut) {
        this.datedebut = datedebut;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    public int getNombreMinParticipant() {
        return NombreMinParticipants;
    }

    public void setNombreMinParticipant(int NombreMinParticipant) {
        this.NombreMinParticipants = NombreMinParticipant;
    }

    public int getNombreMaxParticipant() {
        return NombreMaxParticipants;
    }

    public void setNombreMaxParticipant(int NombreMxParticipant) {
        this.NombreMaxParticipants = NombreMxParticipant;
    }

    public int getIsPublic() {
        return isPublic;
        
        
    }

    public Evenement(int id, int rating) {
        this.id = id;
        this.rating = rating;
    }
    
    

    public Evenement() {
    }

    public Evenement(String title, String description, String datedebut) {
        this.title = title;
        this.description = description;
        this.datedebut = datedebut;
    }
    
    

    
    public void setIsPublic(int isPublic) {
        this.isPublic = isPublic;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public int getNbactuel() {
        return nbactuel;
    }

    public void setNbactuel(int nbactuel) {
        this.nbactuel = nbactuel;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", title=" + title + ", description=" + description + ", localisation=" + localisation + ", etablissement=" + etablissement + ", rating=" + rating + ", categories=" + categories + ", datedebut=" + datedebut + ", datefin=" + datefin + ", NombreMinParticipants=" + NombreMinParticipants + ", NombreMaxParticipants=" + NombreMaxParticipants + ", isPublic=" + isPublic + ", imagepath=" + imagepath + ", nbactuel=" + nbactuel + ", prix=" + prix + '}';
    }

    
    
    
    
            
            

        

    
    
    
}
