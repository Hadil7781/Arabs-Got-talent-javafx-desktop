/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Entity.Evenement;
import Entity.Publication;
import Service.EvenementService;
import Service.PublicationService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.log.Log;
import java.io.ByteArrayOutputStream;
import javafx.scene.image.Image;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.Math.log;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import static java.rmi.server.LogStream.log;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;

/**
 * FXML Controller class
 *
 * @author Khawla
 */
public class AjouterPublicationController implements Initializable {

    @FXML
    private JFXTextField txt_titre;
    @FXML
    private JFXTextArea txt_description;
    @FXML
    private JFXComboBox<String> cb_categories;
    @FXML
    private JFXComboBox<String> cb_contenu;
    @FXML
    private JFXButton btn_image;
    @FXML
    private JFXButton btnAjouterPublication;
    @FXML
    private Circle btnClose;
    String titre;
    Format formatter;
    String description;
    Date date = new Date();
    LocalDate createdadd;
    String file;
    PublicationService ps = new PublicationService();
    @FXML
    private JFXButton btnAnnulerPublication;
    @FXML
    private ImageView immg;
    private ImageView btnReturn;
    @FXML
    private ImageView btnReturns;
    @FXML
    private JFXComboBox<String> cb_evenement;

    /**
     * Initializes the controller class.
     */
    ObservableList<Publication> obList;
    @FXML
    private Label labelCategorie;
    @FXML
    private Label labelTitre;
    @FXML
    private Label labelDescription;
    @FXML
    private Label labelContenu;
    @FXML
    private Label labelImage;
    @FXML
    private Label labelEvenement;
    @FXML
    private MediaView media;
    int idevenement;
    ArrayList<Publication>   idE =  ps.getEvenementByPublication();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    obList = ps.afficherPublication();
        // TODO
    ObservableList<String> options = FXCollections.observableArrayList(
        "musique",
        "peinture",
        "dessin"
    );
    ObservableList <String> option = FXCollections.observableArrayList(
        "Image",
        "Video"
     );
        EvenementService es = new EvenementService();
        ObservableList<Evenement> listEvenement = es.afficheEvenementsClient();
        
    String titres ="";
   for(Evenement p : listEvenement) {
        System.out.println("iddE = "+idE);
        System.out.println(p.getTitle());
        titres = titres +p.getTitle();
    
        
       
        idevenement = p.getId();
        cb_evenement.getItems().add(p.getTitle());
   }

   
   cb_evenement.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        
               //        int id=es.getIdEventByTitle(titre);
           // System.out.println("I find id ="+id);
      
            
        }
    });
    cb_categories.setItems(options);
    
    cb_contenu.setItems(option);
    
    }
    
Publication publication;
    @FXML
    private void handleButtonAddAction(ActionEvent event) throws IOException {
          Format formatter;
          Date date = new Date();
          String s,s1;
          formatter = new SimpleDateFormat("yyyy-MM-dd");
                      s1 = formatter.format(date);
          formatter = new SimpleDateFormat("yyyy-MM-dd");
          titre = txt_titre.getText().toLowerCase();
          description = txt_description.getText().toLowerCase();
          cb_contenu.setEditable(false);
         // createdadd = ft_date.getValue();
          cb_categories.setEditable(false);
          cb_evenement.setEditable(false);
          EvenementService es = new EvenementService();
           int id = es.getIdEventByTitle(cb_evenement.getSelectionModel().getSelectedItem());

          
          if(cb_contenu.getValue().equals("Image"))
          file =imageUrl;
          else 
              file = videoUrl;
          int idEveneemnetSelectd =0 ;
          for(Publication p1 : idE) {
          if(cb_evenement.getSelectionModel().getSelectedItem().equals(p1.getTitle())) {
              idEveneemnetSelectd = p1.getEvenement_id();
              System.out.println("id select ="+idEveneemnetSelectd);
          }
          } 
           publication= new  Publication(titre,cb_contenu.getValue(),description,cb_categories.getValue(),file,s1,id);
           
          if(!ControlePublication.controleTitre(publication) || description.equals("")) {
          labelTitre.setText("le titre doit etre non erroné");
          return ;  
      }
        if(ControlePublication.controleTitre(publication)) {
                 labelTitre.setText(" ");
             }
            if(!ControlePublication.controleDescription(publication) || description.equals("")) {
          labelDescription.setText("le description doit etre non erroné");
          return ;  
      }
        if(ControlePublication.controleDescription(publication)) {
                 labelDescription.setText(" ");
             }
       if(publication != null) {
        System.out.println("hello add");
        ps.ajouterPublication(publication);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Ajout Publication");
        alert.setHeaderText("Ajout avec succées");
        alert.showAndWait();
      }
    }
       Parent root;
   @FXML
    private void handleButtonAnnulerAction(ActionEvent event) {
        clear();
    }
   private static void showServerReply(FTPClient ftp) {
        String[] replies = ftp.getReplyStrings(); 
        if (replies != null && replies.length > 0) {
        for (String aReply : replies) {
            System.out.println("SERVER: " + aReply);
            }
        }
    }
    
    

     String imageUrl   ;
     String videoUrl;
 MediaPlayer mediaPlayer;
    /***************************************************************************************/
    @FXML
    private void handleButtonAddImageAction(ActionEvent event) throws FileNotFoundException, IOException {
        
        FileChooser fileChooserr = new FileChooser();
        fileChooserr.setTitle("Select Image or Video");
        fileChooserr.setInitialDirectory(new File("C:"));
        FileChooser.ExtensionFilter imageFilter
                    = new FileChooser.ExtensionFilter("Files", "*.jpg", "*.png","*.mp4","*.wav","*.rar","*.zip");
        fileChooserr.getExtensionFilters().add(imageFilter);
        
        File file = fileChooserr.showOpenDialog(immg.getScene().getWindow());
       String x =imageFilter.getExtensions().toString();
        
      
            
String fileName = file.getName();           
String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1, file.getName().length());
System.out.println(">> fileExtension" + fileExtension);
boolean b =false;
boolean a =false;
if( fileExtension.equals(new String("mp4"))) {
    b =true;
if(b) {
    //immg.setVisible(false);
            Media video = new Media(file.toURI().toString());
            videoUrl =file.toURI().toString();
            System.out.println("image url = "+videoUrl);
            mediaPlayer = new MediaPlayer(video);
            mediaPlayer.play();   
            media.setMediaPlayer(mediaPlayer);
}}
else {
a=true;
      
if(a) 
     { 
   // media.setVisible(false);
       imageUrl= file.toURI().toString();
       System.out.println("image url = "+imageUrl);
       Image image= new Image(imageUrl);  
       immg.setImage(image); 
       uploadFile(file, ftp, "anonymous", file.getName(), titre);
       
       
       
       
       //
} 
       
       
       
       
       
       
       
       //
}   
}
 boolean done ;
    private static void showServerReplys(FTPClient ftpClient) {
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                System.out.println("SERVER: " + aReply);
            }
        }
    }
     FTPClient ftp = null;

     
     
     
     public  boolean uploadFile(File f ,FTPClient ftpClient, String url, String username, String password) throws FileNotFoundException {
	boolean success = false;
	FTPClient ftp = new FTPClient();
                               int port = 21;
    FileInputStream fis = null;

   
            String filename =f.getPath();
            String firstRemoteFile = f.getName();
           fis = new FileInputStream(filename);
           
        String pass = "pass";
        String server ="speedtest.tele2.net";
	try {
		int reply;
	 
            ftp.connect(server, 21);
        ftp.login("anonymous", "anonymous");
            ftp.enterLocalPassiveMode();

            ftp.setFileType(FTP.BINARY_FILE_TYPE);
		reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
			return success;// echec
		}
                
              System.out.println("Debut d'upload fichier...");
              ftp.enterLocalPassiveMode();

            FileInputStream in = new FileInputStream(f);

            boolean result = ftp.storeFile("/upload/", in);

                in.close();
                
                Alert a = new Alert(AlertType.CONFIRMATION);
                a.setTitle("Upload fichier ");
                a.setContentText(filename+ " est enregsitrer dans le serveur "+server);
                a.showAndWait();
            ftp.logout();
            ftp.disconnect();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (ftp.isConnected()) {
			try {
				ftp.disconnect();
			} catch (IOException ioe) {
			}
		}
	}
	return success;
}
 

     
     

      
    

/***************************************************************************************/
    @FXML
    private void handleMouse(MouseEvent event) throws IOException  {
              System.out.println("hii");
              root = FXMLLoader.load(getClass().getResource("AfficherPublication.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
              
         
    }
       public void clear() {
        txt_titre.setText("");
        txt_description.setText("");
        cb_categories.getItems().clear();
        cb_contenu.getItems().clear();
        cb_evenement.getItems().clear();
        btn_image.setText("");
        immg.setImage(null);
       
    }
 
    
}

   




