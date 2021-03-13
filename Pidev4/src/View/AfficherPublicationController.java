/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Entity.Evenement;
import Entity.Publication;
import Service.PublicationService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 * FXML Controller class
 *
 * @author Khawla
 */
public class AfficherPublicationController  implements Initializable {
    @FXML
    private Circle btnClose;
    @FXML
    private Pane btnAjoutPublication;
    @FXML
    private JFXButton btnAjouterPublication;
    @FXML
    private JFXButton btnDeletePublication;
    PublicationService pub =new PublicationService();
    PublicationService publicationService = new PublicationService();
    @FXML
    private ImageView btnReturn;
    ObservableList<Publication> obList;
    @FXML
    private JFXButton btn_modifier;
    @FXML
    private JFXTextField txt_filterPub;
    @FXML
    private ImageView btnReturns;
    @FXML
    private JFXListView<Publication> listView;
  /***************************************************************************************/
    MediaView mediaView ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    obList=publicationService.afficherPublication(); 
    listView.setItems(obList);

    listView.setCellFactory(param -> new ListCell<Publication>() {
    private ImageView imageView = new ImageView();
    Pane pane = new Pane();
    private MediaView mv = new MediaView();
    MediaPlayer mediaPlayer;
    Image img;
        
    @Override
    public void updateItem(Publication name, boolean empty) {
        HBox vb = new HBox();
        super.updateItem(name, empty);
            if (empty) {
                        setText(null);
                        setGraphic(null);
                       } 
            else {
                         //display video  
                         String fileName = name.getFile();  
                         for( Publication p : obList) {
                         String fileExtension = p.getFile().substring(p.getFile().lastIndexOf(".") + 1, p.getFile().length());
                         System.out.println(fileExtension);
                         if(fileExtension.equals("mp4")) {
                                                           System.out.println("here video");
                                                           //File file = new File(p.getFile()); 
                                                            Media media = new Media(p.getFile()); 
                                                            mediaPlayer = new MediaPlayer(media);
                                                            mediaView = new MediaView(mediaPlayer);
                                                            mediaView.setFitWidth(20); 
                                                            mediaView.setFitHeight(20); 
           
                                                                listView.setOnMouseClicked(new EventHandler<MouseEvent>
                                                                () {
                                                               @Override
                                                               public void handle(MouseEvent event) {
                                                   
                                                                       loadVideo(event);

                                                                  

                                                               }
                                                           });

                                                                      
                      
                                                            mediaPlayer .pause();
                                                            setText(name.getTitre()+"\n"+name.getCreatedadd());
                                                           }    
                         else if(fileExtension.equals("jpg")) {
                                                            imageView.setImage(new Image(name.getFile(),120, 120, true, true));
                                                            setText(name.getTitre()+"\n"+name.getCreatedadd());
                                                               }
                                    }
                                    StackPane pane = new StackPane();
                                    Pane paneVideo = new Pane();
                                    Pane paneImage = new Pane();
                                    if(mediaView !=null) {
                                                          h.getChildren().add(mediaView);
                                                          paneVideo.getChildren().add(mediaView);
                                                          }
                                   paneImage.getChildren().add(imageView);
                                   pane.getChildren().addAll(paneVideo,paneImage);
                                   setGraphic(pane);
                                }
       
            }});
        filter();
    }
    HBox h = new HBox();
/*****************************************************************************************/
    
    
    private void handleButtonAddAction(ActionEvent event) throws IOException {
    }
    
/***************************************************************************************/
    @FXML
    private void handleButtonDeleteAction(ActionEvent event) {
    Publication p =  listView.getSelectionModel().getSelectedItem();
    int visibleIndex = listView.getSelectionModel().getSelectedIndex();
    PublicationService publicationService  =new PublicationService();
    Alert alert = new Alert(AlertType.CONFIRMATION,"Voullez vous supprimer une publication !!");
    alert.setTitle("Suppression d'une publication");
    Optional<ButtonType> option = alert.showAndWait();
    if (option.get() == null) {
      
        } 
    else if (option.get() == ButtonType.OK) {
            System.out.println(p.getId()); 
            publicationService.supprimerPublication(p.getId());
            if(p!=null){
                        int sourceIndex = filteredList.getSourceIndexFor(obList, visibleIndex);
                        filteredList.getSource().remove(p);
                        publicationService.afficherPublication();
            }
        } 
    else if (option.get() == ButtonType.CANCEL) {
      } 
    else {
      }    
    }

/***************************************************************************************/
        private void loadSceneAndSendMessage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierPublication.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            //   System.out.println("id of item ="+listView.getSelectionModel().getSelectedItem().getId());
            ModifierPublicationController  modiferController = loader.getController();
            int evenemnet_id = listView.getSelectionModel().getSelectedItem().getEvenement_id();
            int id = listView.getSelectionModel().getSelectedItem().getId();
            int id_evement = listView.getSelectionModel().getSelectedItem().getEvenement_id();
            String titre = listView.getSelectionModel().getSelectedItem().getTitre();
            String image =listView.getSelectionModel().getSelectedItem().getFile();
            //Pass whatever data you want. You can have multiple method calls here
            modiferController.transferMessage( id,id_evement,titre,image);
            // FXMLLoader loader  =new FXMLLoader();
           // Parent root = loader.load();
            Scene s = new Scene(root);
            stage.setScene(s);
          // stage.initStyle(StageStyle.TRANSPARENT);

        stage.show();
    
        } catch (IOException ex) {
                System.err.println(ex);
        }
    }
        
        
        private void loadVideo(MouseEvent event)  {
           
        try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("FxmlDocument.fxml"));
             root=loader.load(); 
                         String  filePath = listView.getSelectionModel().getSelectedItem().getFile();

               //The following both lines are the only addition we need to pass the arguments
            FxmlDocumentController fxmlDocumentController =  loader.getController();
        
                  fxmlDocumentController.get(filePath);

            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root, 450, 450));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow();
       
            
          } catch (IOException ex) {
            
            JOptionPane.showMessageDialog(null, "erreur = "+ex.getMessage());
        }
          

            
        }
  /***************************************************************************************/
   @FXML
    private void handleButtonEditAction(ActionEvent event) throws IOException {
        loadSceneAndSendMessage(event);
    }

    Parent root;
    @FXML
    private void handleMouseAction(MouseEvent event) throws IOException {  
        System.out.println("hii");
             root = FXMLLoader.load(getClass().getResource("TunisianGotTalen.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
       
    }
    SortedList<Publication> sortedData;
    FilteredList<Publication> filteredList ;
    //Metier Relation
    public void filter() {  
    filteredList = new FilteredList<>(obList,b->true);
    txt_filterPub.textProperty().addListener((observable,oldValue,newValue) ->{
    if(txt_filterPub.getText().isEmpty()) {
       
        }
    filteredList.setPredicate(publication-> {
    if(newValue == null || newValue.isEmpty()) {
                                return true;
                }
      
    String lowerCaseFilter =newValue.toLowerCase();
    if(publication.getCreatedadd().toLowerCase().indexOf(lowerCaseFilter)!=-1 ) {
                                 return true;
                }
    else{
                                        
                                 return false;
                }

            });
            
        });

    listView.setItems(filteredList);
      
    }
  
/***************************************************************************************/
    @FXML
    private void handleButtonOpenAddAction(ActionEvent event) throws IOException {
              Parent roots = FXMLLoader.load(getClass().getResource("AjouterPublication.fxml"));
              Scene scene = new Scene(roots);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }
/*****************************************************************************************/
    @FXML
    private void handleMouse(MouseEvent event) throws IOException {
      if(event.getSource().equals(btnReturns)){
          Parent roots = FXMLLoader.load(getClass().getResource("TunisianGotTalen.fxml"));
          Scene scene = new Scene(roots);
          Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          stage.setScene(scene);
          stage.show();
        }
        
        else {
            System.exit(0);
        }
    }

   
}
    
    
    

