/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Entity.Evenement;
import Entity.Publication;
import Service.EvenementService;
import Service.ParticipationnService;
import Service.PublicationService;
import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Reflection;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Khawla
 */
public class DashboardAdminController implements Initializable{
        
@FXML
    private JFXButton pubBtn;
    @FXML
    private Pane pnlPub;
    @FXML
    private TableView<Publication> tableP;
    @FXML
    private TableColumn<Publication, String> col_titre;
    @FXML
    private TableColumn<Publication, String> col_date;
    private TableColumn<Publication, String> col_desc;

    @FXML
    private Circle btnClose;
    @FXML
    private Pane paneStat;
        private ObservableList<Publication>obList;
private ObservableList<Evenement>obListE;
    
    PublicationService ps = new PublicationService();
    @FXML
    private TableColumn<Publication, String> col_email;
    @FXML
    private TableColumn<Publication, String> col_titre_ev;
    @FXML
    private Label labelNbE;
    @FXML
    private Label labelNbPub;
    private Label labelNbPart;
    private Pane pnlProfile;
      @FXML
    private Pane pnlEvenement;
    @FXML
    private JFXTextField txt_filter;
    @FXML
    private JFXButton btn_modifier;
    @FXML
    private JFXButton btn_supprimer;
  @FXML
    private JFXButton btnProfile;
    @FXML
    private TableColumn<Evenement, String> col_titres;
    @FXML
    private Pane pnlEvenet;
    @FXML
    private JFXButton btnEvent;
    TableColumn<Evenement, Void>colTraiterBtn;
     TableColumn<Publication, Void>colDABtn;
    JFXToggleButton btnDA;
     JFXToggleButton btnTraiter;
     Evenement evenement;
     Publication publication;


  
        @FXML
    private TableView<Evenement> tableE;
    @FXML
    private TableColumn<Evenement, String> col_descriptionE;
    @FXML
    private TableColumn<Evenement, String> col_catE;
    @FXML
    private TableColumn<Evenement, String> col_date_debutE;
    @FXML
    private TableColumn<Evenement,  String> col_date_finE;
    EvenementService evenementService = new EvenementService();
    @FXML
    private JFXButton btnAjouterEvenement;
    @FXML
    private Label labelPart;
    @FXML
    private JFXButton btnDashb;
    Label label;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        obListE=evenementService.afficherEvenements();   
            for(Evenement e : obListE) {
            if(e.getIsPublic() == 1) {
                System.out.println("oui");
                label  = new Label("Confirmé");
                
            }
            else {
                System.out.println("non");
            }
        } 
        //  System.err.println(" data evenement ="+obList);
        col_titres.setCellValueFactory(new PropertyValueFactory<>(("title")));
        col_descriptionE.setCellValueFactory(new PropertyValueFactory<>(("description")));
        col_catE.setCellValueFactory(new PropertyValueFactory<>(("categories")));
        col_date_debutE.setCellValueFactory(new PropertyValueFactory<>(("datedebut")));
        col_date_finE.setCellValueFactory(new PropertyValueFactory<>(("datefin")));
      // col_etat.setCellValueFactory(new PropertyValueFactory<>(("isPublic")));
      colTraiterBtn = new TableColumn<>("Traiter");
              colDABtn = new TableColumn<>("Desactiver");

              
       tableE.getColumns().add(colTraiterBtn);
       
       tableP.getColumns().add(colDABtn);
        
        tableE.setItems(obListE);
  btnTraiter = new JFXToggleButton();

    Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>> cellFactory =
    new Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>>() {
    @Override
    public TableCell<Evenement, Void> call(final TableColumn<Evenement, Void> param) {
            btnTraiter = new JFXToggleButton();
            label = new Label("confirmé");
    final TableCell<Evenement, Void> cell = new TableCell<Evenement, Void>() {
    { 
            btnTraiter.selectedProperty().addListener(((observable, oldValue, newValue) -> {
                if(newValue==true){
                  evenement = tableE.getSelectionModel().getSelectedItem();
                  evenement.setIsPublic(1);
                  evenement.setNombreMaxParticipant(evenement.getNombreMaxParticipant()-1);
                   //Send notification
                  Notifications notificationBuild = Notifications.create()
                                      .title("Confirmation Evenement")
                                      .text("demande de l'evenement est envoyé au client avec succes")
                                      .graphic(null)
                                      .hideAfter(Duration.seconds(5))
                                      .position(Pos.CENTER)
                                      .onAction(new EventHandler<ActionEvent>() {
                                  @Override
                                  public void handle(ActionEvent event) {
                                      System.out.println("click here");
                                  }
                                  
                              });
                              notificationBuild.darkStyle();
                              notificationBuild.show();
                              evenementService.traiterEvenement(evenement);
                              System.out.println(evenement.getId());
                              
                            }
 }));
 }

    
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);  
                        int i=0;
                        int j=0;
                        if (empty) {
                            
                            setGraphic(null);
                        } else {
                                                           setGraphic(btnTraiter);   

                        }
                    }
                      
                  
                        
                };
                                           
                
                return cell;    
            }
        };

        colTraiterBtn.setCellFactory(cellFactory);

        tableE.refresh();
  tableE.addEventFilter(ScrollEvent.ANY, new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent scrollEvent) {
               System.out.println("Scrolled.");
               addButtonTraiterToTable();
            }
     });        
  
  
  
  
  
  //
  
      
  
  //
  
  
  obList =ps.afficherPublication();
        //  System.err.println(" data evenement ="+obList);
        col_titre.setCellValueFactory(new PropertyValueFactory<>(("titre")));
        col_date.setCellValueFactory(new PropertyValueFactory<>(("createdadd")));
        col_email.setCellValueFactory(new PropertyValueFactory<>(("email")));
        col_titre_ev.setCellValueFactory(new PropertyValueFactory<>(("title")));
        pnlPub.setVisible(false);
        pnlEvenet.setVisible(false);
        paneStat.setVisible(true);
        tableP.setItems(obList);
        EvenementService es = new EvenementService();
        int nbE= es.getNbrEvemenement();
        PublicationService ps = new PublicationService();
        int nbP = ps.getNbrPublication();
        ParticipationnService p= new ParticipationnService();
        int nbPart=p.getNbrParticipant();
       
        labelNbE.setText(""+Integer.valueOf(nbE));
        labelPart.setText(""+Integer.valueOf(nbPart));
        labelNbPub.setText(" "+Integer.valueOf(nbP));
        int total =nbE+nbP+nbPart;
        ObservableList<PieChart.Data> pieChartData =
        FXCollections.observableArrayList(
        new PieChart.Data("Evenements", (nbE*100)/total),
        new PieChart.Data("Publications", (nbP*100)/total),
        new PieChart.Data("Pariticpations", (nbPart*100)/total)
        );
        final PieChart chart = new PieChart(pieChartData);
        chart.setLayoutY(25);
        chart.setLayoutX(-50);
        chart.setTitle("Statistiques");
 Set<Node> items = chart.lookupAll("Label.chart-legend-item");
    int i = 0;
    // these colors came from caspian.css .default-color0..4.chart-pie
    Color[] colors = { Color.web("#f9d900"), Color.web("#a9e200"), Color.web("#22bad9"), Color.web("#0181e2"), Color.web("#2f357f") };
    for (Node item : items) {
      Label label = (Label) item;
      final Rectangle rectangle = new Rectangle(10, 10, colors[i]);
      final Glow niceEffect = new Glow();
      niceEffect.setInput(new Reflection());
      rectangle.setEffect(niceEffect);
      label.setGraphic(rectangle);
      i++;
    
    }    paneStat.getChildren().add(chart);
    
    
  
    
  }
        

    private void GoEvenement(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("AfficheEvenement.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }
    

    @FXML
    private void handlaActionButtonGoPublication(ActionEvent event) {
    
        if(event.getSource().equals(pubBtn )){
            addButtonActiverDescatierToTable();
            paneStat.setVisible(false);
            pnlEvenet.setVisible(false);
            pnlPub.setVisible(true);
            new FadeIn(pnlPub).play();
        }     pnlPub.toFront();
        }
@FXML
    private void handlaActionButtonGoEvenement(ActionEvent event) {
        filter();
    if(event.getSource().equals(btnEvent)) {
           paneStat
            .setVisible(false);
            pnlPub.setVisible(false);
            pnlEvenet.setVisible(true);
            new FadeIn(pnlEvenet).play();
                       }
  }
    
    //A: Recuperer les données de chaque ligne.
    private void loadSceneAndSendMessage(ActionEvent event) {
        try {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierEvenement.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ModifierEvenementController  modiferController = loader.getController();
            int id = tableE.getSelectionModel().getSelectedItem().getId();
            String titre = tableE.getSelectionModel().getSelectedItem().getTitle();
            String description =tableE.getSelectionModel().getSelectedItem().getDescription();
            LocalDate datedebut =LocalDate.parse(tableE.getSelectionModel().getSelectedItem().getDatedebut());
            LocalDate datefin = LocalDate.parse(tableE.getSelectionModel().getSelectedItem().getDatefin());
            String image = tableE.getSelectionModel().getSelectedItem().getImagepath();
            String cat = tableE.getSelectionModel().getSelectedItem().getCategories();
            //Pass whatever data you want. You can have multiple method calls here
            //Appler la fonction transfert message du controller modifier pour charger les données 
            //récuperer par A
            modiferController.transferMessage(id,titre,description,cat,datedebut,datefin,
                 "http://localhost:8080/pidev-integration/web/uploads/images/events/"+image);
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

    /*************************************************************************/
     public ObservableList<Evenement> AfficheEvenementControllerObservable() {
        return obListE;     
    }
  
    private void handleButtonAction(ActionEvent event) throws IOException {
        
        FXMLLoader loader  =new FXMLLoader();
        loader.setLocation(getClass().getResource("AjouterEvenement.fxml"));
        Parent root = loader.load();
        Scene s = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(s);
        stage.show();
        System.out.println("successfully");
        obList.clear();
        evenementService.afficherEvenements();
        tableE.refresh();
        tableE.setItems(obListE);
        
    }
    /***********************************************************************************/
    @FXML
    private void SupprimerEvenement(ActionEvent event) {
       Evenement e= tableE.getSelectionModel().getSelectedItem();
       int visibleIndex = tableE.getSelectionModel().getSelectedIndex();
       EvenementService evenementService  =new EvenementService();
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Voullez vous supprimer un evenement!!");
       alert.setTitle("Suppression d'un evenement");
       Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == null) {
      
        }
        else if (option.get() == ButtonType.OK) {
        evenementService.supprimerEvenement(e.getId());
        int sourceIndex = sortedData.getSourceIndexFor(obListE, visibleIndex);
        obListE.remove(sourceIndex);
        addButtonTraiterToTable();//taaed taayetlou besh ki tfakhou hheka e event e switch maytfsakhesh
       } else if (option.get() == ButtonType.CANCEL) {
       } else {
      }  
     //  if(option.get()== ButtonType.OK) {
 }
    /***********************************************************************************/
    @FXML
    private void HandleActionModifEvenement(ActionEvent event) throws IOException {
  
    loadSceneAndSendMessage(event);
        
    }
    /***********************************************************************************/

    private void handleMouseEventHome(MouseEvent event) throws IOException {
              Parent root = FXMLLoader.load(getClass().getResource("TunisianGotTalen.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }
   
    /***********************************************************************************/
    
    SortedList<Evenement> sortedData;
    //Metier Relation
    public void filter() {  
        FilteredList<Evenement> filteredList = new FilteredList<>(obListE,b->true);
        txt_filter.textProperty().addListener((observable,oldValue,newValue) ->{
        if(txt_filter.getText().isEmpty()) {
        //Reload button
        // addButtonToTable();
        }
        filteredList.setPredicate(evenement-> {
        if(newValue == null || newValue.isEmpty()) {
                                     btnTraiter=new JFXToggleButton();
                            return true;
                }
      
                String lowerCaseFilter =newValue.toLowerCase();
                if(evenement.getTitle().toLowerCase().indexOf(lowerCaseFilter)!=-1 ) {
                    
                    return true;
                    
                }
                else if(evenement.getCategories().toLowerCase().indexOf(lowerCaseFilter)!=-1) {

                    return true;
                    
                }
               
               
                else{
                       btnTraiter = new JFXToggleButton();
                    return false;
                }

            });
            
        });

        
        sortedData= new SortedList<>(filteredList);
        sortedData.comparatorProperty().bind(tableE.comparatorProperty());
        tableE.setItems(sortedData);
      
    }
    /********************************************************************************/
    public void addButtonTraiterToTable() {
    btnTraiter = new JFXToggleButton();

    
    Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>> cellFactory =
    new Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>>() {
    @Override
    public TableCell<Evenement, Void> call(final TableColumn<Evenement, Void> param) {
            btnTraiter = new JFXToggleButton();
    final TableCell<Evenement, Void> cell = new TableCell<Evenement, Void>() {
    { 
            btnTraiter.selectedProperty().addListener(((observable, oldValue, newValue) -> {
                if(newValue==true){
                  evenement = tableE.getSelectionModel().getSelectedItem();
                  evenement.setIsPublic(1);
                  
                  evenement.setNombreMaxParticipant(evenement.getNombreMaxParticipant()-1);

                   //Send notification
                  Notifications notificationBuild = Notifications.create()
                                      .title("Confirmation Evenement")
                                      .text("demande de l'evenement est envoyé au client avec succes")
                                      .graphic(null)
                                      .hideAfter(Duration.seconds(5))
                                      .position(Pos.CENTER)
                                      .onAction(new EventHandler<ActionEvent>() {
                                  @Override
                                  public void handle(ActionEvent event) {
                                      System.out.println("click here");
                                  }
                                  
                              });
                              notificationBuild.darkStyle();
                              notificationBuild.show();
                              evenementService.traiterEvenement(evenement);
                              System.out.println(evenement.getId());
                            }
 }));
 }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);  
                        if (empty) {
                            
                            setGraphic(null);
                        } else {
                            
                            setGraphic(btnTraiter);
                        }
                    }
                };
                                           
                
                return cell;    
            }
        };

        colTraiterBtn.setCellFactory(cellFactory);

      }

             
/************************************************************************************/             
    public void addButtonActiverDescatierToTable() {
         btnDA = new JFXToggleButton();
         Callback<TableColumn<Publication, Void>, TableCell<Publication, Void>> cellFactory =
         new Callback<TableColumn<Publication, Void>, TableCell<Publication, Void>>() {
         @Override
         public TableCell<Publication, Void> call(final TableColumn<Publication, Void> param) {
                btnDA = new JFXToggleButton();

         final TableCell<Publication, Void> cell = new TableCell<Publication, Void>() {


                    { 
                btnDA.selectedProperty().addListener(((observable, oldValue, newValue) -> {
                            if(newValue==true){
                              publication = tableP.getSelectionModel().getSelectedItem();
                              publication.setIsBlocked(1);
                              System.out.println("block = "+publication.getIsBlocked());
                              //    evenement.setNombreMaxParticipant(evenement.getNombreMaxParticipant()-1);
                              //Send notification
                              Notifications notificationBuild = Notifications.create()
                                      .title("Bloquage publication")
                                      .text("publication est bloquée avec success")
                                      .graphic(null)
                                      .hideAfter(Duration.seconds(5))
                                      .position(Pos.CENTER)
                                      .onAction(new EventHandler<ActionEvent>() {
                                  @Override
                                  public void handle(ActionEvent event) {
                                      System.out.println("click here");
                                  }
                                  
                              });
                              notificationBuild.darkStyle();
                              notificationBuild.show();
                                      
                              
                            ps.traiterPublication(publication);
                            System.out.println(publication.getId());        
                 
                            }
                                                   
                        
                            }));
  }

  @Override
    public void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);  
        if (empty) {
                     setGraphic(null);
        } 
        else {
                            
         setGraphic(btnDA);
              }
             }
};
                                           
                
                return cell;    
            }
        };

        colDABtn.setCellFactory(cellFactory);

      }
             
 @FXML
    private void handleActionButtonAjout(ActionEvent event) throws IOException {
        FXMLLoader loader  =new FXMLLoader();
        loader.setLocation(getClass().getResource("AjouterEvenement.fxml"));
        Parent root = loader.load();
        Scene s = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(s);
        stage.show();
        System.out.println("successfully");
        obList.clear();
        evenementService.afficherEvenements();
        tableE.refresh();
        tableE.setItems(obListE);
        
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        
        System.exit(0);
    }

    @FXML
    private void HandleActionGoStat(ActionEvent event) {
        
        pnlEvenet.setVisible(false);
        pnlPub.setVisible(false);
        paneStat.setVisible(true);
        new FadeIn(paneStat).play();
        paneStat.toFront();
    }

}
