/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Entity.Evenement;
import Entity.Participationn;
import Service.ParticipationnService;
import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import netscape.javascript.JSObject;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Khawla
 */
public class DetailEvenementController implements Initializable{

    private GoogleMap map;
    @FXML
    private Circle btnClose;
    private ImageView ImageEvent;
    @FXML
    private Label labelTitre;
    private JFXButton btnDescr;
    @FXML
    private JFXButton btn_participer;
    @FXML
    private ImageView btnReturns;
    @FXML
    private Label labelDate;
    @FXML
    private Label labellocalisation;
    @FXML
    private Label labelDesc;
    @FXML
    private Pane pnlDeta;
    @FXML
    private Pane pnlMAp;
    private Hyperlink consMap;
    @FXML
    private JFXButton GoMap;
    @FXML
  
    private ImageView imgEvenet;
    @FXML
    private ImageView btnReturnsDet;
    @FXML
    private Rating note;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
    } 
   int etat ;
   int idEvent;
   String titrEvenement;
        public void transfertMessage(int id_evenement,int e,String titre, String description, String datedebut,String datefin,String etablissement,
                String localisation,String image,int noteE) {
            System.out.println("note = "+noteE);
        labelTitre.setText("Titre:"+titre);
        labelDesc.setText("Description:"+description);
        labelDate.setText("debut:"+datedebut+" Fin: "+datefin);
        //LabelEtablissement.setText("Etablissement :"+etablissement);
        labellocalisation.setText("Localisation :"+localisation);
        
      imgEvenet.setImage(new Image(
      image,120, 120, true, true));
      note.setRating(noteE);

        etat =e;
        idEvent = id_evenement;
        titrEvenement = titre;
   }

    @FXML
    private void handleActionButtonParticiper(ActionEvent event) {
      if(etat ==0) {
            
                    Image goodImage = new Image("http://icons.iconarchive.com/icons/paomedia/small-n-flat/72/sign-check-icon.png", true);

             Notifications notificationBuild = Notifications.create()
                                      .title("Demande de participation")
                                      .text("Votre demande envoyé avec success :) ")
                                      .graphic(new ImageView(goodImage))
                                      .hideAfter(Duration.seconds(5))
                                      .position(Pos.TOP_RIGHT)
                                      .onAction(new EventHandler<ActionEvent>() {
                                  @Override
                                  public void handle(ActionEvent event) {
                                      
                                      
                                  }
                                  
                              });
             
                              notificationBuild.darkStyle();
                              notificationBuild.show();
                              System.out.println("id evemenet = "+idEvent+"is public == "+etat);
                           Participationn participationn = new Participationn(idEvent,"en cours", 1);
                                   //lors de participation ispulibc  = 0 ba3d kif y confirmer admin tw1i 1 
                                   ParticipationnService participationnService = new ParticipationnService();
                                   participationnService.ajouterParticipation(participationn);
                                      
            
            
        }
        else 
        {
            //lahné twli is public  =1
            
                 Participationn participationn = new Participationn(idEvent,"confirmé", 1);
                                   //lors de participation ispulibc  = 0 ba3d kif y confirmer admin tw1i 1 
                                   ParticipationnService participationnService = new ParticipationnService();
                                   participationnService.updateEtat(participationn);
                                      
                 
          //  btnParticiper.se(Color.RED);
          Alert a = new Alert(Alert.AlertType.ERROR);
          a.setTitle("Evenement Confirmé");
          
          a.setContentText("Vous avez deja Participer ");
           a.showAndWait();
            
        
        }
        
        
        
    }

    @FXML
    private void handleMouseAction(MouseEvent event) throws IOException {
        
        
            if(event.getSource().equals(btnReturns)) {
             Parent root = FXMLLoader.load(getClass().getResource("ConsulterEvenement.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
            }
            else if(event.getSource().equals(btnReturnsDet)) {
                pnlMAp.setVisible(false);
                new FadeIn(pnlDeta).play();
                pnlDeta.toFront();
              
            }
            else 
                System.exit(0);
    }


    @FXML
    private void handleActionButtonGoMap(ActionEvent event) {
      
            
        if(event.getSource().equals(GoMap)) {
           System.out.println("click map");
          // mapView1.addMapInializedListener(this);
       
          // chooseCity(35.828829,10.640525);
          showMap(stage1);
          
        
        }
    }
    
  
   Stage stage1;
    FXMLLoader loader;
    
    public class JavaBridge {
 
    public void showLocation() {
        System.out.println("Show Time");
        labellocalisation.setText("here");
 
    }
}
    
 
   public void showMap(Stage stage) {
       
            // create web engine and view
         WebEngine webEngine1 = new WebEngine(
                getClass().getResource("/Outils/client.html").toString());
         WebView webView1 = new WebView();
        String loc = labellocalisation.getText();
         webView1.getEngine().load("file:///C:/Users/Khawla/Documents/NetBeansProjects/"
                 + "Pidev4/src/Outils/client.html");
        
    
 
     
             stage = new Stage(StageStyle.UTILITY);
            stage.setScene(new Scene(webView1));
            stage.show();
   
            
   
            
        final ToggleGroup mapTypeGroup = new ToggleGroup();
        final ToggleButton road = new ToggleButton("Road");
        road.setSelected(true);
        road.setToggleGroup(mapTypeGroup);
        final ToggleButton satellite = new ToggleButton("Retour");
        
        satellite.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                stage1 = new Stage(StageStyle.UTILITY);
                   loader= new FXMLLoader(getClass().getResource("AjouterEvenement.fxml"));
                    loader.setController(new AjouterEvenementController());
                 try {
                     stage1.setScene(new Scene(loader.load()));
                 } catch (IOException ex) {
                     Logger.getLogger(AjouterEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                 }
            stage1.show();
                 
             }
         });
    
        stage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>(){
             @Override
             public void handle(WindowEvent event) {
                
                // label.setText(fullName);
             }
            
        });
       
        
     
        
   }
   }