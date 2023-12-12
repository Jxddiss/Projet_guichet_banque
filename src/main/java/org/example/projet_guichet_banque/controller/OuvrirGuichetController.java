package org.example.projet_guichet_banque.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class OuvrirGuichetController {
    @FXML
    private PasswordField nipPasswordField;
    @FXML
    private Button ouvrirBtn;
    @FXML
    private Button quitterBtn;
    private Parent root;
    private Scene scene;
    @FXML
    public void ouvrirClick(ActionEvent actionEvent)throws IOException{
        if (actionEvent.getSource() == ouvrirBtn){
            int nip = Integer.parseInt(nipPasswordField.getText());
            if (LoginController.gestionnaireGuichet.validerUtilisateur(0,nip) == 0){
                LoginController.gestionnaireGuichet.setStatut("ouvert");
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/affichageAdmin.fxml")));
                scene = ouvrirBtn.getScene();
                scene.setRoot(root);
                ((Stage)scene.getWindow()).setTitle("Panel Admin");
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("nip Erronée");
                alert.show();
            }
        }
    }

    @FXML
    public void quitterClick(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == quitterBtn){
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/guichetFermer.fxml")));
            scene = quitterBtn.getScene();
            scene.setRoot(root);
            ((Stage)scene.getWindow()).setTitle("Panel Admin");
        }
    }

}
