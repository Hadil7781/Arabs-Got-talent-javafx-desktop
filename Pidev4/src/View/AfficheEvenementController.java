/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author Khawla
 */
public class AfficheEvenementController implements Initializable {

    @FXML
    private Pane pnlEvenement1;
    @FXML
    private Circle btnClose;
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
    @FXML
    private Pane pnlEvenement;
    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> col_titre;
    @FXML
    private TableColumn<?, ?> col_description;
    @FXML
    private TableColumn<?, ?> col_cat;
    @FXML
    private TableColumn<?, ?> col_date_debut;
    @FXML
    private TableColumn<?, ?> col_date_fin;
    @FXML
    private JFXTextField txt_filter;
    @FXML
    private JFXButton btnEnvoyerFeedback;
    @FXML
    private JFXButton btn_modifier;
    @FXML
    private JFXButton btn_supprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleMouseEventHome(MouseEvent event) {
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }

    @FXML
    private void HandleActionModif(ActionEvent event) {
    }

    @FXML
    private void SupprimerEvenement(ActionEvent event) {
    }
    
}
