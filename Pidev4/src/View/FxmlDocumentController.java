/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static javafx.scene.media.MediaPlayer.Status.PLAYING;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 *
 * @author Chedy
 */
public class FxmlDocumentController implements Initializable {

   
    public MediaPlayer mediaPlayer;
    public String file;
    
    @FXML
    private MediaView mediaView;
    @FXML
    private Slider slider;
    @FXML
    private Slider mediaSlider;
    
    private List<String> extensions;
    @FXML
    private Button openFile;
    @FXML
    private Button pause;
    @FXML
    private Button stop;
    @FXML
    private Button faster;
    @FXML
    private Button slower;
    @FXML
    private StackPane stackPane;
    @FXML
    private Label label;
    @FXML
    
    private void handleButtonAction(ActionEvent event){
      
            System.out.println("******************"+f);
        
 if(f !=null) {
     
 Media media = new Media(f);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            DoubleProperty width = mediaView.fitWidthProperty();
            DoubleProperty height = mediaView.fitHeightProperty();
                
                width.bind(Bindings.selectDouble(mediaView.sceneProperty(),"width"));
                height.bind(Bindings.selectDouble(mediaView.sceneProperty(),"height"));
                
                slider.setValue(mediaPlayer.getVolume()*100);
                
                
                
                slider.valueProperty().addListener(new InvalidationListener(){
                    @Override
                    public void invalidated(Observable obeservable){
                        mediaPlayer.setVolume(slider.getValue()/100);
                    } 
                });
                
                mediaPlayer.setOnReady(new Runnable(){
                @Override
                public void run() {
                    //System.out.println("Duration: "+ mediaPlayer.getTotalDuration().toSeconds());
                    mediaSlider.setMin(0.0);
                    mediaSlider.setValue(0.0);
                    mediaSlider.setMax(mediaPlayer.getTotalDuration().toSeconds());
                    
                    mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>(){
                    @Override
                    public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                        mediaSlider.setValue(newValue.toSeconds());
                    }
                    
                  });
                    
                mediaSlider.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                        mediaPlayer.seek(Duration.seconds(mediaSlider.getValue()));
                    
                }
                    
                });
                    
              }
            });
                
                
                
                
                
            mediaPlayer.play();
      
        
  
    }
    }
    
    @FXML
    private void pauseVideo(ActionEvent event){
        try{
            if(mediaPlayer.getStatus() == PLAYING){
                mediaPlayer.pause();
            }else{ 
                mediaPlayer.play();
              
            }
        }catch(Exception e){
            //System.out.println("Open up a file First");
            JOptionPane.showMessageDialog(null,"Open up a File First");
        }
        
    }
    
    @FXML
    private void stopVideo(ActionEvent event){
        try{
        mediaPlayer.stop();
        }catch(NullPointerException e ){
            //System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null,"Open up a File First");
        }
    }
    
    @FXML
    private void fastVideo(ActionEvent event){
        try{
            mediaPlayer.setRate(mediaPlayer.getRate()+0.5);
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null,"Open up a File First");
        }   
    }
    
    @FXML
    private void slowVideo(ActionEvent event){
        try{
            mediaPlayer.setRate(mediaPlayer.getRate()-0.5);
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null,"Open up a File First");
        }
        
    }
    
    @FXML
    private void closeVideo(ActionEvent event){
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

     
 } 
  
        
        
        
        
        // TODO

    @FXML
    private void pauseVideo(KeyEvent event) {
    }

    String f;
    public void get(String x) {
        f = x;
    }
        
    
}
