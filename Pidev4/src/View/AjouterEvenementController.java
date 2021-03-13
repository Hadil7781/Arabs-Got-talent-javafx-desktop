/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Entity.Evenement;
import Service.EvenementService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker.State;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import netscape.javascript.JSObject;

/**
 * FXML Controller class
 *
 * @author Khawla
 */
public class AjouterEvenementController implements Initializable {

    @FXML
    private Button btn_annuler;
    @FXML
    private JFXDatePicker date_fin;
    private JFXTextField txt_localisation;
    @FXML
    private ComboBox<String> cb_categories;
    @FXML
    private JFXTextField txt_titre;
    @FXML
    private JFXTextField txt_description;
    @FXML
    private JFXDatePicker date_debut;
    private JFXTextField txt_etablissement;
    @FXML
    private JFXTextField nb_min_participant;
    private JFXTextField nb_actuel;
    @FXML
    private JFXTextField nb_max_participant;
    @FXML
    private JFXButton btn_image;
    LocalDate dateDebut,dateFin;
    String titre ;
    String local;
    String description,etabl;
    String nbMinPart,nbMaxPart,nbAct;
    Format formatter;
    String image;
    String isPublic ;
    String localisation ;
    int nbMin ,nbMax,nbActt;
    int prix;
    Date date = new Date();
    private EvenementService e = new EvenementService();  
    @FXML
    private JFXTextField txt_prix;
    @FXML
    private Button btn_ajouter;
    // AfficheEvenementController aec = new AfficheEvenementController();
    @FXML
    private ImageView immg;
    @FXML
    private Label description_label;
    @FXML
    private Label date_label;
    @FXML
    private Label titre_label;
    @FXML
    private Label categorie_label;
    @FXML
    private Label nb_min_label;
    @FXML
    private Label max_label;
    @FXML
    private Label prix_label;
    @FXML
    private Label label_image;
    @FXML
    private Hyperlink btn_map;
    @FXML
    private Label label;
    @FXML
    private Pane pnlEvenement;
    @FXML
    private Circle btnClose;
    /**
     * Initializes the controller class.
     */
    
    
    
 public    String photoEvenement = null;
    @FXML
    private Button btnEvenement;
    @FXML
    private Button btnPublication;
    @FXML
    private Button btnFormation;
    @FXML
    private Button btnSpoonsoring;
    @FXML
    private Button btnQuiz;
    @FXML
    private Button btnCommentaire;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> options = FXCollections.observableArrayList(
        "musique",
        "peinture",
        "dessin"
    );
             // create web engine and view
         WebEngine webEngine = new WebEngine(
                getClass().getResource("/Outils/googlemap.html").toString());
         WebView webView = new WebView();
         webView.getEngine().load("file:///C:/Users/Khawla/Documents/NetBeansProjects/Pidev4/src/Outils/googlemap.html");
        // create map type buttons

         cb_categories.setItems(options);
         // cb_categories.getItems().add("");
         
         if(fullName == null) {
             System.out.println("find null first of all");
         }
     

       
    } 
    /***************************************************************************************/
  
     Evenement evenement;
   //Control saisie des input Number 
    public void controleNumber() {
        try {
                  nbMin = Integer.parseInt(nbMinPart);
                  nbMax = Integer.parseInt(nbMaxPart);

                
            }catch(Exception e) {
               // alert.setContentText("Nbr min/Nbr max doivent etre non texte");
            }
    }

    
    ObservableList<Evenement>o =FXCollections.observableArrayList();

  /***************************************************************************************/  
    @FXML
    private void handleMouseEvent(MouseEvent event) throws IOException {
         //System.exit(0);
          o.clear();
          o.add(evenement);
          e.afficherEvenements();
              Parent root = FXMLLoader.load(getClass().getResource("DashboardAdmin.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
       
       // AjouterEvenementController ajoutEv = new AjouterEvenementController().;
       stage.show();
    }

    
    
    
    @FXML
    private void handleButtonAnnulerAction(ActionEvent event) {
        
        clear();
    }

    String fullName;
    @FXML
    private void handleButtonAddEvnementAction(ActionEvent event) throws ParseException {
        
         String pattern = "dd/MM/yyyy";
        titre = txt_titre.getText().toString().toLowerCase();
        description = txt_description.getText().toString().toLowerCase();
    localisation = label.getText();
                 System.out.println("tittre ="+titre);
                 System.out.println("loc ="+localisation);

      
        nbMaxPart = nb_max_participant.getText().toLowerCase();
        //nbAct = nb_actuel.getText();
    //    nbActt = Integer.parseInt(nbAct);
        image = imageUrl;
        cb_categories.setEditable(false);
        //DateFormat df = new SimpleDateFormat(LocalDate.parse(dateDebut));
        dateDebut = date_debut.getValue();
        dateFin = date_fin.getValue();
       // Alert alert =new Alert(AlertType.INFORMATION);
       // alert.setTitle(" Controle des champs");
      
       
    
         for(int i =0; i<txt_prix.getText().length();i++) {
             if(txt_prix.getText().charAt(i)>='a' && txt_prix.getText().charAt(i)<='z' && txt_prix.getText().equals(" ")) {
                    prix_label.setText("Verifier prix ");
                    return ;
             }
             else {
                 prix_label.setText(" ");
                 prix =Integer.parseInt(txt_prix.getText().toString());

             }
             }
            
            //Catch exception for Number 
            
            //
            
              int nbMaxx = Integer.valueOf(nb_max_participant.getText());
      int nbMinn = Integer.valueOf(nb_min_participant.getText());

      evenement = new Evenement(titre,description,localisation,cb_categories.getValue()
                  ,dateDebut.toString(),dateFin.toString(),nbMinn,nbMaxx,photoEvenement,prix);
       if(!ControleEvenement.controleTitre(evenement) || titre.equals("")) {
          titre_label.setText("le titre doit etre non erroné ou bien il est vide");
          return ;
      }
       if(ControleEvenement.controleTitre(evenement)) {
           titre_label.setText(" ");
       }
       
        if(!ControleEvenement.controleDescription(evenement) || description.equals("")) {
          description_label.setText("le description doit etre non erroné");
          return ;  
      }
        if(ControleEvenement.controleDescription(evenement)) {
                 description_label.setText(" ");
             }
        
      
      if(!ControleEvenement.controlePrix(evenement) || prix== 0) {
         prix_label.setText("Verifier prix ou bien il est 0 ");
          return ;
         }
        if(ControleEvenement.controlePrix(evenement)) {
                 prix_label.setText(" ");
             }
      if(!ControleEvenement.controleDate(evenement) || date_debut.equals(null) || date_fin.equals(null)) {
           date_label.setText("Verifier les dates ou bien le date nulls ");
          return ;

      }
      if(ControleEvenement.controleDate(evenement)) {
                 date_label.setText(" ");
             }
      
      if(!ControleEvenement.controleNbMax(evenement)){
          max_label.setText("nbr max doit contenir que  nombre");
          return ;

      }
 
      if(ControleEvenement.controleNbMax(evenement)){
          max_label.setText("");

      } 
       if(!ControleEvenement.controleNbMin(evenement)){
          nb_min_label.setText("nbr min doit contenir que  nombre");
          return ;

      }
 
      if(ControleEvenement.controleNbMin(evenement)){
          nb_min_label.setText("");

      }
      
      
      
      try{
    
      
    
      
               if(nbMaxx<nbMinn) {
              max_label.setText("Nombre max doit etre superieur");
              return;

               } 
               if(nbMaxx>nbMinn) {
                   nb_min_label.setText("");
                   
               }
               
              

               if(nbMinn<10) {
                  nb_min_label.setText("<10");
                  return;
                  
               }
               if(nbMinn>=10) {
                   nb_min_label.setText("");
               }
      }catch(Exception e) {
          nb_min_label.setText("min  doit etre non chaine");
          max_label.setText("max doit etre non chaine");
          return;
      }
               
               
                
                

        
      if(evenement != null) {
          System.out.println("hello add");
     
        e.ajoutEvenement(evenement);
          Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Ajout evenement");
        alert.setHeaderText("Ajout avec succées");
        alert.showAndWait();
      }
        
       
    }

    
    String imageUrl;
    @FXML
    private void HandleActionButtonImage(ActionEvent event) throws ParseException, IOException {
        FileChooser file = new FileChooser();
        file.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images","*.jpg","*.png", "*.bmp"));
        
        File selected_file = file.showOpenDialog((Stage)((Node) event.getSource()).getScene().getWindow());
        photoEvenement = selected_file.getAbsolutePath();
        BufferedImage bufferedImage = ImageIO.read(selected_file);
        WritableImage  imageW = SwingFXUtils.toFXImage(bufferedImage, null);
        immg.setImage(imageW);
        System.out.println("upload=" + imageW);
        
        
        nbMaxPart = nb_max_participant.getText().toLowerCase();
        //nbAct = nb_actuel.getText();
    //    nbActt = Integer.parseInt(nbAct);
    titre = txt_titre.getText();
    description = txt_description.getText();
        image = photoEvenement;
        cb_categories.setEditable(false);
        //DateFormat df = new SimpleDateFormat(LocalDate.parse(dateDebut));
        dateDebut = date_debut.getValue();
        dateFin = date_fin.getValue();
       // Alert alert =new Alert(AlertType.INFORMATION);
       // alert.setTitle(" Controle des champs");
      
       for(int i =0; i<txt_prix.getText().length();i++) {
             if(txt_prix.getText().charAt(i)>='a' && txt_prix.getText().charAt(i)<='z' && txt_prix.getText().equals(" ")) {
                    prix_label.setText("Verifier prix ");
                    return ;
             }
             else {
                 prix_label.setText(" ");
                             prix =Integer.parseInt(txt_prix.getText().toString());

             }
             }
            
            //Catch exception for Number 
            
            //
            System.out.println("file = "+image);
            
            
      int nbMaxx =Integer.valueOf(nb_max_participant.getText());
      int nbMinn = Integer.valueOf(nb_min_participant.getText());
      
      
      //error is down here
      
      evenement = new Evenement(titre,description,localisation,cb_categories.getValue()
                  ,dateDebut.toString(),dateFin.toString(),nbMinn,nbMaxx,image,prix);
        System.out.println("*******************"+evenement);
        
           if(!ControleEvenement.controleTitre(evenement) || titre.equals("")) {
          titre_label.setText("le titre doit etre non erroné ou bien il est vide");
          return ;
      }
       if(ControleEvenement.controleTitre(evenement)) {
           titre_label.setText(" ");
       }
       
        if(!ControleEvenement.controleDescription(evenement) || description.equals("")) {
          description_label.setText("le description doit etre non erroné");
          return ;  
      }
        if(ControleEvenement.controleDescription(evenement)) {
                 description_label.setText(" ");
             }
        
      
      if(!ControleEvenement.controlePrix(evenement) || prix== 0) {
         prix_label.setText("Verifier prix ou bien il est 0 ");
          return ;
         }
        if(ControleEvenement.controlePrix(evenement)) {
                 prix_label.setText(" ");
             }
      if(!ControleEvenement.controleDate(evenement) || date_debut.equals(null) || date_fin.equals(null)) {
           date_label.setText("Verifier les dates ou bien le date nulls ");
          return ;

      }
      if(ControleEvenement.controleDate(evenement)) {
                 date_label.setText(" ");
             }
      
      if(!ControleEvenement.controleNbMax(evenement)){
          max_label.setText("nbr max doit contenir que  nombre");
          return ;

      }
 
      if(ControleEvenement.controleNbMax(evenement)){
          max_label.setText("");

      } 
       if(!ControleEvenement.controleNbMin(evenement)){
          nb_min_label.setText("nbr min doit contenir que  nombre");
          return ;

      }
 
      if(ControleEvenement.controleNbMin(evenement)){
          nb_min_label.setText("");

      }
      
      
      
      try{
             if(nbMaxx<nbMinn) {
              max_label.setText("Nombre max doit etre superieur");
              return;

               } 
               if(nbMaxx>nbMinn) {
                   nb_min_label.setText("");
                   
               }
              if(nbMinn<10) {
                  nb_min_label.setText("<10");
                  return;
                  
               }
               if(nbMinn>=10) {
                   nb_min_label.setText("");
               }
      }catch(Exception e) {
          nb_min_label.setText("min  doit etre non chaine");
          max_label.setText("max doit etre non chaine");
          return;
      }
    
   }

    @FXML
    private void HandleActionButtonGoMap(ActionEvent event) throws IOException {
       
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     
        showMap(stage);
        
        
     
        
    }
    
    
   public void clear() {
        txt_titre.setText("");
        txt_description.setText("");
        cb_categories.getItems().clear();
        nb_max_participant.setText(null);
        nb_min_participant.setText(null);
        txt_prix.setText(null);
        btn_image.setText("");
        immg.setImage(null);
       
    }   
   
   public void transferMessage(String loc ) {
       
      // label.setText(fullName);
   }
   
   
   
   Stage stage1;
    FXMLLoader loader;
   public void showMap(Stage stage) {
       
            // create web engine and view
         WebEngine webEngine = new WebEngine(
                getClass().getResource("/Outils/googlemap.html").toString());
         WebView webView = new WebView();
         webView.getEngine().load("file:///C:/Users/Khawla/Documents/NetBeansProjects/Pidev4/src/Outils/googlemap.html");
        // create map type buttons
        
     
             stage = new Stage(StageStyle.UTILITY);
            stage.setScene(new Scene(webView));
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
                 System.out.println("close");
                 String fullName =webView.getEngine().executeScript("fullName;").toString();
                 System.out.println("loc = "+fullName);
                 label.setText(fullName);
             }
            
        });
       
        
     
        

   }
     private Timeline locationUpdateTimeline;

         
    private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }
 
    static { // use system proxy settings when standalone application
        System.setProperty("java.net.useSystemProxies", "true");
    }
   
}
