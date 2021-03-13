/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Entity.Evenement;
import Entity.Publication;
import Outils.AutoCompleteComboBoxListener;
import Service.EvenementService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Khawla
 */
public class ConsulterEvenementController extends Application implements Initializable {

    @FXML
    private JFXTextField btn_filtrerEvents;
    
    ObservableList<Evenement> obList;
    EvenementService evenementService = new EvenementService();
    @FXML
    private Pane Pane;
    @FXML
    private AnchorPane anchor;
    @FXML
    private JFXListView<Evenement> listView;
    @FXML
    private Circle btnClose;
    @FXML
    private ImageView btnReturnAcls;
    @FXML
    private JFXComboBox<String> cb_cat;
    @FXML
    private void handleSearch(ActionEvent event) {
    filter();
   }

    @FXML
    private void filtrer(ActionEvent event) {
        filterByTitle();
    }

    /**
     * Initializes the controller class.
     */
    class XCell extends ListCell<Evenement> {
        Pane hbox = new Pane();
        //Label label = new Label("(empty)");
        Pane pane = new Pane();
        Button button = new Button("Detail");
        Rating rating = new Rating();
        //
        //
        Evenement lastItem;
        private ImageView imageView = new ImageView();
        public XCell() {
            super();
        //    Pane.setHgrow(imageView, Priority.ALWAYS);

        
             //rating de publication      
           rating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                   System.out.println("Rating is ==" +t1.toString());
                         
                   Evenement e = new Evenement(lastItem.getId(), t1.intValue()
);

                   evenementService.updateRating(e);
                   
                   
                   }});
             // msg.setText("Rating : "+ t1.toString());
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println(lastItem + " : " + event);
                    if(listView.getSelectionModel().getSelectedItem().getIsPublic()==1) {
                    loadSceneAndSendMessage(event);
                    Image goodImage = new Image("http://icons.iconarchive.com/icons/paomedia/small-n-flat/72/sign-check-icon.png", true);
                    Notifications notificationBuild = Notifications.create()
                                      .title("Confirmation de participation")
                                      .text("Votre demande a confirmé par par l'administrateur :) ")
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
             }
                    else {
                        loadSceneAndSendMessage(event);
                    }
   

        
          
             
                   
                    System.out.println("*******"+rating.getRating());
                   
                     Notifications notificationBuild = Notifications.create()
                                      .title("Avis")
                                      .text("Merci beaucoup de mon avis ")
                                      .graphic(null)
                                      .hideAfter(Duration.seconds(5))
                                      .position(Pos.CENTER)
                                     .onAction(new EventHandler<ActionEvent>() {
                                  @Override
                                  public void handle(ActionEvent event) {
                                      
                                      
       
                                  }
                                  
                              });
             
                              notificationBuild.darkStyle();
                              notificationBuild.show();
                                      
                      }});             
    
                   
        }
@Override
   protected void updateItem(Evenement item, boolean empty) {
   super.updateItem(item, empty);
   setText(null);  // No text in label of super class
   if (empty) {
                lastItem = null;
                setGraphic(null);
            } else {
           lastItem = item;
           Label Title = new Label("Titre : "+item.getTitle());
           Title.setLayoutX(120);
           Title.setLayoutY(50);
           Label Prix = new Label("\n Prix : "+String.valueOf(item.getPrix()));
         rating.setRating(item.getRating());
  
           Prix.setLayoutX(120);
           Prix.setLayoutY(50);
           
           
           rating.setLayoutX(100);
           rating.setLayoutY(50);
           rating.prefWidth(500);
           rating.setLayoutX(250);
           rating.setLayoutY(50);
           rating.setLayoutX(250);
           rating.setLayoutY(50);
           rating.setPrefWidth(201);

 
           button.setLayoutX(120);
           button.setLayoutY(50);
           button.prefHeight(500);
           button.prefWidth(500);
           button.setLayoutX(800);
           button.setLayoutY(50);
           button.setLayoutX(500);
           button.setLayoutY(50);
           button.setPrefHeight(50);
           button.setPrefWidth(201);
           button.setStyle("     -fx-background-radius:5;\n" +
"    -fx-background-color: #fff;\n" +
"        -fx-text-fill:  #E75480;\n" +
"        -fx-border-width:2px;\n" +
         "   -fx-border-color: #E75480 ;\n"+
"        -fx-border-style:solid;\n" +
"            -fx-border-radius:5px;"
                    );       
           if(item.getImagepath() == null ){
               System.out.println("null image");
               return ;
           }
          imageView.setImage(new Image("http://localhost:8080/pidev-integration/web/uploads/images/events/"+item.getImagepath(),120, 120, true, true));
          StackPane sp = new StackPane();
          Pane pane = new Pane();
          Pane pane2 = new Pane();
          pane.getChildren().add(imageView);
          pane2.getChildren().addAll(Title,Prix,button,rating);
          sp.getChildren().addAll(pane,pane2);
              setGraphic(sp);
            }
        }
    }
  public  void loadSceneAndSendMessage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailEvenement.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            DetailEvenementController  detailController = loader.getController();
            int idevenemet = listView.getSelectionModel().getSelectedItem().getId();
            int etat = listView.getSelectionModel().getSelectedItem().getIsPublic();
            String titre = listView.getSelectionModel().getSelectedItem().getTitle();
            String description = listView.getSelectionModel().getSelectedItem().getDescription();
            String datedebut = listView.getSelectionModel().getSelectedItem().getDatedebut();
            String datefin = listView.getSelectionModel().getSelectedItem().getDatefin();
            String etablissement = listView.getSelectionModel().getSelectedItem().getEtablissement();
            String localisation = listView.getSelectionModel().getSelectedItem().getLocalisation();
            String image= listView.getSelectionModel().getSelectedItem().getImagepath();
           //Pass whatever data you want. You can have multiple method calls here
                       int note = listView.getSelectionModel().getSelectedItem().getRating();

            detailController.transfertMessage(idevenemet,etat,titre, description, datedebut,datefin,etablissement,localisation,
                    
            "http://localhost:8080/pidev-integration/web/uploads/images/events/"+image,note);
            // FXMLLoader loader  =new FXMLLoader();
            // Parent root = loader.load();
            Scene s = new Scene(root);
            stage.setScene(s);
           //  stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        } catch (IOException ex) {
                                  System.err.println(ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    obList = evenementService.afficherEvenements();
    System.out.println("evenement utilisateur ="+obList);
    ObservableList <String> option = FXCollections.observableArrayList(
        "musique",
        "dessin",
        "peinture"
     );
      cb_cat.setItems(option);
     
      
      new AutoCompleteComboBoxListener<>(cb_cat);
        listView.setItems(obList);
        listView.setCellFactory(new Callback<ListView<Evenement>, ListCell<Evenement>>() {
            @Override
            public ListCell<Evenement> call(ListView<Evenement> param) {
                return new XCell();
            }
        });
       StackPane pane = new StackPane();
        //pane.getChildren().add(lv);
       //   Pane.getChildren().add(pane);
           
    }
 @FXML
    private void handleMouseAction(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("TunisianGotTalen.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    }
SortedList<Evenement> sortedData;
FilteredList<Evenement> filteredList ;
    //Metier Relation
    public void filter() {  
       filteredList = new FilteredList<>(obList,b->true);
       filteredList.setPredicate(evenement-> {
        if(cb_cat.getValue() == null || cb_cat.getValue().isEmpty()) {
                                
                         return true;
                }
                String lowerCaseFilter =cb_cat.getValue().toLowerCase();
                if(evenement.getCategories().toLowerCase().indexOf(lowerCaseFilter)!=-1 ) {
                  return true;
               }
               else{
                                        
                    return false;
                }

            });
       listView.setItems(filteredList);
      
    }
   public void filterByTitle() {  
        filteredList = new FilteredList<>(obList,b->true);
        btn_filtrerEvents.textProperty().addListener((observable,oldValue,newValue) ->{
        if(btn_filtrerEvents.getText().isEmpty()) {
        }
        filteredList.setPredicate(evenement-> {
        if(newValue == null || newValue.isEmpty()) {
                                
                         return true;
                }
      
                String lowerCaseFilter =newValue.toLowerCase();
                if(evenement.getTitle().toLowerCase().indexOf(lowerCaseFilter)!=-1 ) {
                    
                    return true;
                    
                }
            else{
                                        
                    return false;
                }

            });
            
        });
  listView.setItems(filteredList);
      
    }
   
   
  
   
  
    }
    



 //Metier Relation
         
  
    



    

   

