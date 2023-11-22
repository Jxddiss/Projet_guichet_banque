package org.example.projet_guichet_banque.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.projet_guichet_banque.model.GestionnaireGuichet;
import org.example.projet_guichet_banque.model.GestionnaireGuichetDAO;

import java.io.IOException;
import java.util.Objects;

public class LoginController {
    public static GestionnaireGuichet gestionnaireGuichet = GestionnaireGuichetDAO.get();
    @FXML
    private TextField codeClientTxtField;
    @FXML
    private PasswordField nipTxtField;
    @FXML
    private Button connecterBtn;

    @FXML
    public void initialize(){

    }

    public void connecterClick(ActionEvent actionEvent) throws IOException {
        Scene scene = null;
        Parent root = null;
        int codeClient = Integer.parseInt(codeClientTxtField.getText());
        int nip = Integer.parseInt(nipTxtField.getText());

        if (gestionnaireGuichet.validerUtilisateur(codeClient,nip)){
            System.out.println(gestionnaireGuichet.getClient().toString());
            System.out.println("Connecter en tant que "+ gestionnaireGuichet.getClient().getPrenom());

            if (actionEvent.getSource() == connecterBtn){
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/affichageCompte.fxml")));
                scene = connecterBtn.getScene();
                scene.setRoot(root);
                ((Stage)scene.getWindow()).setTitle("Fenetre 2");
            }
        }

    }

}