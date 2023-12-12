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
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AdminFermerGuichetController {
    @FXML
    private Label prenomUtilisateurLbl;
    @FXML
    private Button fermerBtn;
    @FXML
    private Button quitterBtn;
    @FXML
    private PasswordField nipPasswordField;
    private Scene scene;
    private Parent root;

    @FXML
    public void initialize(){
        String prenom = LoginController.gestionnaireGuichet.getClient().getPrenom();
        prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1);
        prenomUtilisateurLbl.setText("Bonjour, " + prenom);

    }

    @FXML
    public void fermerClick(ActionEvent actionEvent)throws IOException{
        if (actionEvent.getSource() == fermerBtn){
            String nip = nipPasswordField.getText();
            if (LoginController.gestionnaireGuichet.validerUtilisateur(0,nip) == 0){
                LoginController.gestionnaireGuichet.setStatut("fermer");
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/guichetFermer.fxml")));
                scene = fermerBtn.getScene();
                scene.setRoot(root);
                ((Stage)scene.getWindow()).setTitle("Panel Admin");
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Utilisateur ou nip Erronée");
                alert.show();
            }
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
