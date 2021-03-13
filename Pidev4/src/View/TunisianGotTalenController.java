/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Service.EvenementService;
import Service.PublicationService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.print.attribute.standard.MediaSize;

/**
 * FXML Controller class
 *
 * @author Khawla
 */
public class TunisianGotTalenController implements Initializable {

    
         // Max number of slider
    private static final byte NUMBER_OF_SLIDE = 3;
    
    private byte currentSlide = 1;
    @FXML
    private Circle btnClose;
    @FXML
    private JFXButton btnGoTosPublication;
    @FXML
    private StackPane stackpane;
    @FXML
    private Pane pnlHome;
    @FXML
    private ImageView slide1;
    @FXML
    private JFXButton btnPrevious;
    @FXML
    private Label lblPageNumber;
    @FXML
    private JFXButton btnNext;
    @FXML
    private ImageView slide2;
    @FXML
    private JFXButton btnGoTosPublibtnGoToEvenementClientcation1;
    @FXML
    private JFXButton btnModifierProfile;
    @FXML
    private JFXButton btnAjouterRec;
    @FXML
    private JFXButton btnDec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         btnPrevious.setOnAction(e-> {
            if(currentSlide == 1) {
                return;
            }
            previousSlide(--currentSlide);
        });
        
        btnNext.setOnAction(e-> {
            if(currentSlide == NUMBER_OF_SLIDE) {
                return;
            }
            nextSlide(++currentSlide);
        });
      
        // TODO
    }    

/***************************************************************************************/
    
    @FXML
    private void HandleActionDisplayPub(ActionEvent event) throws IOException {
        
        
       FXMLLoader loader  =new FXMLLoader();
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       loader.setLocation(getClass().getResource("AfficherPublication.fxml"));
       Parent root = loader.load();
       Scene s = new Scene(root);
       stage.setScene(s);
       //stage.initStyle(StageStyle.TRANSPARENT);
       stage.show();
        
        
    }
    
/***************************************************************************************/
    @FXML
    private void handleMouseEventModif(MouseEvent event) {
    }
    
/***************************************************************************************/
    
    private void HandleActionDisplayEvenement(ActionEvent event) throws IOException {
       FXMLLoader loader  =new FXMLLoader();
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       loader.setLocation(getClass().getResource("AfficheEvenement.fxml"));
       Parent root = loader.load();
       Scene s = new Scene(root);
       stage.setScene(s);
       stage.show();
        
    }
    
    
       private void nextSlide(byte indexOfSlide) {
        lblPageNumber.setText(indexOfSlide + lblPageNumber.getText().substring(1));
        switch (indexOfSlide) {
            case 1:
                slide1.setVisible(true);
                TranslateTransition tt1 = new TranslateTransition(Duration.millis(800));
                tt1.setNode(slide2);
                tt1.setFromX(0.0);
                tt1.setToX(-384.0);
                tt1.setAutoReverse(false);
                tt1.play();
                
                tt1.setOnFinished(e -> {
                    slide2.setVisible(false);
                });
                btnPrevious.setId("btn2");
                btnNext.setId("btn2");
                break;

                
        }
    }
    
    private void previousSlide(byte indexOfSlide) {
        lblPageNumber.setText(indexOfSlide + lblPageNumber.getText().substring(1));
        switch (indexOfSlide) {
            case 1:
                slide1.setVisible(true);
                TranslateTransition tt1 = new TranslateTransition(Duration.millis(800));
                tt1.setNode(slide1);
                tt1.setFromX(-384.0);
                tt1.setToX(0.0);
                tt1.setAutoReverse(false);
                tt1.play();
                
                tt1.setOnFinished(e -> {
                    slide2.setVisible(false);
                    btnPrevious.setId("btn1");
                    btnNext.setId("btn1");
                });
                break;
            case 2:
                slide2.setVisible(true);
                TranslateTransition tt2 = new TranslateTransition(Duration.millis(800));
                tt2.setNode(slide2);
                tt2.setFromX(-384.0);
                tt2.setToX(0.0);
                tt2.setAutoReverse(false);
                tt2.play();
                
                tt2.setOnFinished(e -> {
                    btnPrevious.setId("btn2");
                    btnNext.setId("btn2");
                });
                break;
        }
    }

    @FXML
    private void HandleActionDisplayEvenementCleint(ActionEvent event) throws IOException  {
          FXMLLoader loader  =new FXMLLoader();
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       loader.setLocation(getClass().getResource("ConsulterEvenement.fxml"));
       Parent root = loader.load();
       Scene s = new Scene(root);
       stage.setScene(s);
       //stage.initStyle(StageStyle.TRANSPARENT);
       stage.show();
    }

    private void GoHome(ActionEvent event) throws IOException {
        
         FXMLLoader loader  =new FXMLLoader();
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       loader.setLocation(getClass().getResource("TunisianGotTalen.fxml"));
       Parent root = loader.load();
       Scene s = new Scene(root);
       stage.setScene(s);
       //stage.initStyle(StageStyle.TRANSPARENT);
       stage.show();
    }

    private void GoPub(ActionEvent event) throws IOException {
         FXMLLoader loader  =new FXMLLoader();
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       loader.setLocation(getClass().getResource("AfficherPublication.fxml"));
       Parent root = loader.load();
       Scene s = new Scene(root);
       stage.setScene(s);
       //stage.initStyle(StageStyle.TRANSPARENT);
       stage.show();
    }

    private void GoEvent(ActionEvent event) throws IOException {
         FXMLLoader loader  =new FXMLLoader();
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       loader.setLocation(getClass().getResource("ConsulterEvenement.fxml"));
       Parent root = loader.load();
       Scene s = new Scene(root);
       stage.setScene(s);
       //stage.initStyle(StageStyle.TRANSPARENT);
       stage.show();
    }
}
    
    
    
    
    

