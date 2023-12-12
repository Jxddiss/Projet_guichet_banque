package org.example.projet_guichet_banque.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.projet_guichet_banque.model.GestionnaireGuichetDAO;

import java.io.IOException;
import java.util.Objects;

public class GuichetFermerController {
    @FXML
    private Button deconnecterBtn;
    @FXML
    private Button ouvrirBtn;
    private Parent root;
    private Scene scene;

    @FXML
    public void initialize(){
        if (LoginController.gestionnaireGuichet.getClient().getCodeClient() != 0){
            ouvrirBtn.setVisible(false);
        }
    }
    @FXML
    public void ouvrirClick(ActionEvent actionEvent)throws IOException{
        if (actionEvent.getSource() == ouvrirBtn){
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/ouvrirGuichet.fxml")));
            scene = ouvrirBtn.getScene();
            scene.setRoot(root);
            ((Stage)scene.getWindow()).setTitle("Login");
        }
        LoginController.gestionnaireGuichet.setStatut("ouvert");
    }
    @FXML
    public void deconnecterClick(ActionEvent actionEvent) throws IOException {
        LoginController.gestionnaireGuichet.setClient(null);
        GestionnaireGuichetDAO.save(LoginController.gestionnaireGuichet);

        if (actionEvent.getSource() == deconnecterBtn){
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/login.fxml")));
            scene = deconnecterBtn.getScene();
            scene.setRoot(root);
            ((Stage)scene.getWindow()).setTitle("Login");
        }
    }
}
