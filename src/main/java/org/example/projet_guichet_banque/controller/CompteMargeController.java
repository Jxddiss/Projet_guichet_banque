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
import org.example.projet_guichet_banque.model.Compte;

import java.io.IOException;
import java.util.Objects;

public class CompteMargeController {
    @FXML
    private Label prenomUtilisateurLbl;
    @FXML
    private Label numCompteLbl;
    @FXML
    private Label soldeLbl;
    @FXML
    private Button quitterBtn;
    private Compte compteChoisi;
    private Scene scene;
    private Parent root;

    @FXML
    public void initialize(){
        String prenom = LoginController.gestionnaireGuichet.getClient().getPrenom();
        prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1);
        prenomUtilisateurLbl.setText("Bonjour, " + prenom);
        for (Compte compte:
                LoginController.gestionnaireGuichet.getClient().getComptes()) {
            if (compte.getNumeroCompte() == AffichageCompteController.numChoisi){
                compteChoisi = compte;
            }
        }

        numCompteLbl.setText(String.format("%04d",compteChoisi.getNumeroCompte()));
        soldeLbl.setText(String.format("%.2f $",compteChoisi.getSoldeCompte()));

    }

    @FXML
    public void quitterClick(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == quitterBtn){
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/affichageCompte.fxml")));
            scene = quitterBtn.getScene();
            scene.setRoot(root);
            ((Stage)scene.getWindow()).setTitle("Comptes");
        }
    }
}
