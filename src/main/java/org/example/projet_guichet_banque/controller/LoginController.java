package org.example.projet_guichet_banque.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.projet_guichet_banque.model.GestionnaireGuichet;
import org.example.projet_guichet_banque.model.GestionnaireGuichetDAO;
import org.example.projet_guichet_banque.model.Setup;

import java.io.IOException;
import java.util.Objects;

public class LoginController {
    static GestionnaireGuichet gestionnaireGuichet;
    @FXML
    private TextField codeClientTxtField;
    @FXML
    private PasswordField nipTxtField;
    @FXML
    private Button connecterBtn;

    @FXML
    public void initialize(){
        Setup.setup();
        gestionnaireGuichet = GestionnaireGuichetDAO.get();
    }

    public void connecterClick(ActionEvent actionEvent) throws IOException {
        Scene scene;
        Parent root;
        try{
            int codeClient = Integer.parseInt(codeClientTxtField.getText());
            int nip = Integer.parseInt(nipTxtField.getText());

            if (gestionnaireGuichet.validerUtilisateur(codeClient,nip) == 0){
                if (actionEvent.getSource() == connecterBtn && gestionnaireGuichet.getClient().getCodeClient() != 0){
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/affichageCompte.fxml")));
                    scene = connecterBtn.getScene();
                    scene.setRoot(root);
                    ((Stage)scene.getWindow()).setTitle("Comptes");
                }else{
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/affichageAdmin.fxml")));
                    scene = connecterBtn.getScene();
                    scene.setRoot(root);
                    ((Stage)scene.getWindow()).setTitle("Admin");
                }
            }else if (gestionnaireGuichet.validerUtilisateur(codeClient,nip) == 2){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Utilisateur ou nip Erronée");
                alert.show();
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Votre compte est bloqué, veuillez contacter l'administrateur");
                alert.show();
            }

            GestionnaireGuichetDAO.save(gestionnaireGuichet);
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Valeur entrée non prise en charge veuillez entrer des nombre entier");
            alert.show();
        }
    }
}