/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;


import Entity.Publication;
import Service.PublicationService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Khawla
 */
public class ModifierPublicationController implements Initializable {

    @FXML
    private Circle btnClose;
    @FXML
    private JFXTextField txt_titre;
    @FXML
    private JFXButton btn_image;
    @FXML
    private JFXButton btnEditPublication;
    String cat,ov,o;
    private ComboBox<String> cb_categorie;
    PublicationService p = new PublicationService();
    @FXML
    private ImageView immg;
    @FXML
    private JFXButton btn_modifer_pub;
    @FXML
    private ImageView imgCurrent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> options = FXCollections.observableArrayList(
        "musique",
        "peinture",
        "dessin"
    );
        ObservableList<String> option = FXCollections.observableArrayList(
        "video",
        "image"
    );
    }    


      int idItem,evenement_idd;
     public void transferMessage(int id,int evenement_id,String titre,String file) {
       
       txt_titre .setText(titre);
      imgCurrent.setImage(new Image(file));
       idItem=id;
       evenement_idd=evenement_id;
   
    }
/***************************************************************************************/
    @FXML
    private void handleButtonEditAction(ActionEvent event) {
     String titre= txt_titre .getText().toString();
     String image = imageUrl; 
     int id = idItem;
     int id_evenement =evenement_idd;
    // cat = cb_categorie.getSelectionModel().getSelectedItem();
           

     System.out.println(id);
     Publication publication = new Publication(id_evenement,titre,image,id);
                System.out.println("data before update ="+         
                publication.toString());
                p.modifierPublication(publication);
                System.out.println("data after update ="+         
                publication.toString());
       

    }
    
/***************************************************************************************/
    
    @FXML
    private void handleMousePubModif(MouseEvent event) throws IOException {
         System.out.println("modif click");
             
     
        //System.exit(0);
           if(event.getSource().equals(btnClose)) {
              Parent root = FXMLLoader.load(getClass().getResource("AfficherPublication.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
      }
    }
/***************************************************************************************/
    String imageUrl;
    @FXML
    private void handleButtonEditImageAction(ActionEvent event) {
        FileChooser fileChooserr = new FileChooser();
        fileChooserr.setTitle("Select Image");
        fileChooserr.setInitialDirectory(new File("C:"));
        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooserr.getExtensionFilters().add(imageFilter);
        File file = fileChooserr.showOpenDialog(imgCurrent.getScene().getWindow());
        imageUrl=file.toURI().toString();
        Image image = new Image(imageUrl);
        imgCurrent .setImage(image);
        System.out.println("image path="+file.toURI().toString());
    }

   
    
    
}
