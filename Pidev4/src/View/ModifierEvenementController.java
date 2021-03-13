/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Entity.Evenement;
import Service.EvenementService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class 
 * 
 *
 * @author Khawla
 */
public class ModifierEvenementController implements Initializable {
    ObservableList<String> options = FXCollections.observableArrayList(
        "musique",
        "peinture",
        "dessin"
    );
     public    String photoEvenement = null;

    @FXML
    private Pane pnlEvenement;
    @FXML
    private JFXTextField txt_titre;
    @FXML
    private JFXDatePicker date_fin;
    @FXML
    private JFXTextField txt_description;
    @FXML
    private JFXDatePicker date_debut;
    @FXML
    private Button btn_modifier;
    @FXML
    private Button btn_annuler;
    @FXML
    private JFXButton btn_image;
    @FXML
    private Pane pnl_modif;
    @FXML
    private Circle btnClose;
    private JFXTextField txt_id;
    String cat,ov;
    @FXML
    private ComboBox<String> cb_categorie;

    EvenementService e = new EvenementService();
    ObservableList<Evenement>o =FXCollections.observableArrayList();
    @FXML
    private Label titre_label;
    @FXML
    private Label description_label;
    @FXML
    private Label date_label;
    @FXML
    private Label categorie_label;
    @FXML
    private ImageView immg;
    @FXML
    private ImageView imageEventt;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Hello Modif");
     //   AfficheEvenementController aec = new AfficheEvenementController();
        cb_categorie.setItems(options);
          
        
    }    
    
    int idmodif;
    /***************************************************************************************/
    //ya5eth data mt3 kol ligne mel affichage wy7othom fi formulaire modifier 
    public void transferMessage(int id,String title,String description,String categorie,LocalDate dated,LocalDate datef ,String image) {
       
        
        System.out.println("image here = "+image);
       txt_titre .setText(title);
       txt_description .setText(description);
       date_debut.setValue(dated);
       date_fin.setValue(datef);
       cb_categorie.setValue(categorie);
     imageEventt.setImage(new Image(
              image,120, 120, true, true));
       idmodif = id;
   }

    //Refresh
    private void handleMouseEvent(MouseEvent event) throws IOException {
        o.clear();
        e.afficherEvenements();
                 
    }
    //Modification
    @FXML
    private void OnEditEvenement(ActionEvent event) throws ParseException, IOException {
         String titre= txt_titre .getText().toString();
      String desc= txt_description .getText().toString();
      String datedebut= date_debut.getValue().toString();
      String datefin = date_fin.getValue().toString();
      int  id =idmodif;
     ov = cb_categorie.getSelectionModel().getSelectedItem();
      Evenement evenement = new Evenement(id,titre,desc,ov,datedebut,datefin,imageUrl);
      if(!ControleEvenement.controleTitre(evenement) || titre.equals("")) {
          titre_label.setText("le titre doit etre non erroné ou bien il est vide");
          return ;
      }
       if(ControleEvenement.controleTitre(evenement)) {
           titre_label.setText(" ");
       }
       
        if(!ControleEvenement.controleDescription(evenement) || desc.equals("")) {
          description_label.setText("le description doit etre non erroné");
          return ;  
      }
        if(ControleEvenement.controleDescription(evenement)) {
                 description_label.setText(" ");
             }
        
      
    
      if(!ControleEvenement.controleDate(evenement) || date_debut.equals(null) || date_fin.equals(null)) {
           date_label.setText("Verifier les dates ou bien le date nulls ");
          return ;

      }
      if(ControleEvenement.controleDate(evenement)) {
                 date_label.setText(" ");
             }
   /*     if(ControleEvenement.controleNbrMinMax(evenement) || nbMax ==0 || nbMin==0) {
           nb_min_label.setText("Verifier nb min ");
           max_label.setText("Verifier nb max ");
          return ;

      }
                if(!ControleEvenement.controleNbrMinMax(evenement)) {
                   nb_min_label.setText(" ");
                    max_label.setText(" ");  
                }*/
if(evenement != null) {
    System.out.println("hello add");
    System.out.println("data before update ="+         
                          evenement.toString());
    e.modifierEvenement(evenement);
    System.out.println("data after update ="+         
                          evenement.toString());
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Modification evenement");
    alert.setHeaderText("Modification avec succées");
    alert.showAndWait();
      }
 
       //btn_image.setText(image);
  }
// kif tekliki yo5rj mel modifi wymchi lel affichage
@FXML
private void handleMouseEventModif(MouseEvent event) throws IOException {
System.out.println("modif click");
 //System.exit(0);
if(event.getSource().equals(btnClose)) {
              Parent root = FXMLLoader.load(getClass().getResource("DashboardAdmin.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
      }
 // AjouterEvenementController ajoutEv = new AjouterEvenementController().;
  }

@FXML
private void handleButtonAnnulerAction(ActionEvent event) {
    }
String imageUrl;
@FXML
private void handleImageEventModif(ActionEvent event) throws IOException {

     
      FileChooser file = new FileChooser();
        file.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images","*.jpg","*.png", "*.bmp"));
        
        File selected_file = file.showOpenDialog((Stage)((Node) event.getSource()).getScene().getWindow());
        photoEvenement = selected_file.getAbsolutePath();
        BufferedImage bufferedImage = ImageIO.read(selected_file);
        WritableImage  imageW = SwingFXUtils.toFXImage(bufferedImage, null);
        imageUrl = photoEvenement;
        imageEventt.setImage(imageW);
        
        
        
    }
    }


