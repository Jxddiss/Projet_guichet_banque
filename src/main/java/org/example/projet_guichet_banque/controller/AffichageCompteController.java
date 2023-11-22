package org.example.projet_guichet_banque.controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.projet_guichet_banque.model.GestionnaireGuichetDAO;

import java.io.IOException;
import java.util.Objects;

public class AffichageCompteController {
    @FXML
    private Label prenomUtilisateurLbl;
    @FXML
    private Button deconnecterBtn;

    @FXML
    public void initialize(){
        String prenom = LoginController.gestionnaireGuichet.getClient().getPrenom();
        prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1);
        prenomUtilisateurLbl.setText("Bonjour, " + prenom);
    }

    @FXML
    public void deconnecterClick(ActionEvent actionEvent) throws IOException {
        LoginController.gestionnaireGuichet.setClient(null);
        GestionnaireGuichetDAO.save(LoginController.gestionnaireGuichet);
        Scene scene = null;
        Parent root = null;
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/login.fxml")));
        scene = deconnecterBtn.getScene();
        scene.setRoot(root);
        ((Stage)scene.getWindow()).setTitle("Login");
    }
}

