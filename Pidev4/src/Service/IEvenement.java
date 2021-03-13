/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Evenement;
import java.util.List;
import java.util.Observable;
import javafx.collections.ObservableList;

/**
 *
 * @author Khawla
 */

public interface IEvenement {
    public void ajoutEvenement(Evenement e);
    public void supprimerEvenement(int id);
    public boolean rechercherEvenementParTitre(String titre);
    public void modifierEvenement(Evenement e);
        public void traiterEvenement(Evenement e);

    public ObservableList<Evenement>afficheEvenementsClient();
        public ObservableList<Evenement>afficherEvenements();
            public void updateRating(Evenement e) ;
            
            public int getIdEventByTitle(String title);





}