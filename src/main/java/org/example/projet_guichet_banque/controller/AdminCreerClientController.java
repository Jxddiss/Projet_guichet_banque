package org.example.projet_guichet_banque.controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AdminCreerClientController {
    @FXML
    private Label prenomUtilisateurLbl;
    @FXML
    private Button quitterBtn;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField prenomTxtField;
    @FXML
    private TextField nomTxtField;
    @FXML
    private TextField telephoneTxtField;
    @FXML
    private TextField courielTxtField;
    @FXML
    private TextField nipTxtField;
    @FXML
    private Button confirmerBtn;

    @FXML
    public void initialize(){
        String prenom = LoginController.gestionnaireGuichet.getClient().getPrenom();
        prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1);
        prenomUtilisateurLbl.setText("Bonjour, " + prenom);

        confirmerBtn.setOnMouseEntered(event -> confirmerBtn.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: black; -fx-background-color: #448fbb; -fx-transition-duration: 0.9s;"));
        confirmerBtn.setOnMouseExited(event -> confirmerBtn.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: black; -fx-background-color:  #b1d1e3; -fx-transition-duration: 0.9s;"));

    }

    @FXML
    public void confirmerClick(ActionEvent actionEvent){
        if(!prenomTxtField.getText().isEmpty() && !nomTxtField.getText().isEmpty() && !telephoneTxtField.getText().isEmpty() && !courielTxtField.getText().isEmpty() && !nipTxtField.getText().isEmpty()){
            String prenom = prenomTxtField.getText();
            String nom = nomTxtField.getText();
            String telephone = telephoneTxtField.getText();
            String couriel = courielTxtField.getText();
            String nip = nipTxtField.getText();

            boolean statutCreation = LoginController.gestionnaireGuichet.creerClient(prenom, nom, telephone, couriel, nip);
            prenomTxtField.setText("");
            nomTxtField.setText("");
            telephoneTxtField.setText("");
            courielTxtField.setText("");
            nipTxtField.setText("");
            if(statutCreation){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("La création du compte a été réussi!");
                alert.show();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Il y a eu un problème avec la création du compte!");
                alert.show();
            }

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Aucun champ ne doit être laissé vide!");
            alert.show();
        }



    }

    @FXML
    public void quitterClick(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == quitterBtn){
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/affichageAdmin.fxml")));
            scene = quitterBtn.getScene();
            scene.setRoot(root);
            ((Stage)scene.getWindow()).setTitle("Panel Admin");
        }
    }
}
