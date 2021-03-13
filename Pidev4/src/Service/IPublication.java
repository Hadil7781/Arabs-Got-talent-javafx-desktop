/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Publication;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author Khawla
 */
public interface IPublication {
    public void ajouterPublication(Publication p);
    public void supprimerPublication(int id );
    public void modifierPublication(Publication p);
    public ObservableList<Publication>afficherPublication();
    public ArrayList<Publication>getEvenementByPublication();
        public void traiterPublication(Publication p) ;

}
