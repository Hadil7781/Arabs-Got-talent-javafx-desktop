    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev4;

import Entity.Evenement;
import Entity.Publication;
import Service.EvenementService;
import Service.PublicationService;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Khawla
 */
public class Pidev4 extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
          
        Parent root = FXMLLoader.load(getClass().getResource("/View/DashboardAdmin.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);

       // primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
  

   
     Format formatter;
     Date date = new Date();
     String s,s1;
          formatter = new SimpleDateFormat("yyyy-MM-dd");
            s = formatter.format(date);
                        s1 = formatter.format(date);

            

  System.out.println(s);
  

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  /******************************Ajout un evenement*********************/
        
        EvenementService evenementService  =new EvenementService();
        Evenement e= new Evenement("chadi","abcddd","dddd","ddsdss",4,
           "cat",s,s1,42,4,1,2,"xxxzx",1);
        Evenement e1= new Evenement();
        System.out.println("hello !! ");
        //evenementService.ajoutEvenement(e);
        System.out.println("donnez le titre" );
        Evenement e2= new Evenement("chadihadil","hhh","hhh","dddd",4,
                "catt",s,s1,15,5,7,3,"hhh",12);
       // evenementService.ajoutEvenement(e2);
        
        Evenement e4= new Evenement("ccccc","abcdef","ddd","zzz",6,
                "ddds",s,s1,5,4,7,2,"hhhxx",10);
        
               // evenementService.ajoutEvenement(e4);
               // evenementService.ajoutEvenement(e2);
                
             evenementService.modifierEvenement(e2);
             
             
             
            //**************Suppression Evenement***********
             evenementService.supprimerEvenement(20);

        
        
        //evenementService.ajoutEvenement(e3);
        
        /*Supprimer un evenement*/
       //evenementService.supprimerEvenement(2);
        //evenementService.supprimerEvenement(3);
        //System.out.println("evenement supprimer avec succes");
        
       /*****************Rechercher un evenement *******************************/
       
      boolean rst= evenementService.rechercherEvenementParTitre("houdouuu");
         System.out.println(rst);
         
        /********************* Modifier un evenement **************************/
      
     
     
      /************************** Affichage*************************************/
      

      
      
      
       
      
        
        
        /********* Ajouter un publication ****************/
          PublicationService publicaionService  =new PublicationService();
        
           Date  currentDate =  new Date(); 
            System.out.print("ddddddddddddddddddddddddddddddddddddddddddddddddddd");
            //Publication p= new Publication(6,"hadilchadi", "hohi", "dhdsds", "dsdsd", "didid", "ss", 1, 2, 3, 4, 5, 6);
            
            // publicaionService.ajouterPublication(p);
        
        
        
       /* Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        */
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
