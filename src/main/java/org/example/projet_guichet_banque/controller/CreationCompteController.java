package org.example.projet_guichet_banque.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.projet_guichet_banque.model.Compte;
import org.example.projet_guichet_banque.model.Demande;

import java.io.IOException;
import java.util.Objects;

public class CreationCompteController {
    @FXML
    private Button quitterBtn;
    @FXML
    private Label prenomUtilisateurLbl;
    @FXML
    private Pane chequePaneBtn;
    @FXML
    private Pane epargnePaneBtn;
    @FXML
    private Pane hypothecairePaneBtn;
    @FXML
    private Pane margePaneBtn;
    private Scene scene;
    private Parent root;

    @FXML
    public void initialize(){
        String prenom = LoginController.gestionnaireGuichet.getClient().getPrenom();
        prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1);
        prenomUtilisateurLbl.setText("Bonjour, " + prenom);
    }

    @FXML
    public void creerClick(MouseEvent mouseEvent)throws IOException{
        int codeClient = LoginController.gestionnaireGuichet.getClient().getCodeClient();
        String statut = "En cours";
        boolean demandeEnvoyee = false;
        if (mouseEvent.getSource() == chequePaneBtn){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Votre demande à été envoyée");
            alert.show();
            LoginController.gestionnaireGuichet.envoyerDemande(new Demande(codeClient,"cheque",statut));
            demandeEnvoyee = true;
        }
        if (mouseEvent.getSource() == epargnePaneBtn){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Votre demande à été envoyée");
            alert.show();
            LoginController.gestionnaireGuichet.envoyerDemande(new Demande(codeClient,"epargne",statut));
            demandeEnvoyee = true;
        }
        if (mouseEvent.getSource() == hypothecairePaneBtn){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Votre demande à été envoyée");
            alert.show();
            LoginController.gestionnaireGuichet.envoyerDemande(new Demande(codeClient,"hypotheque",statut));
            demandeEnvoyee = true;
        }
        if (mouseEvent.getSource() == margePaneBtn){
            boolean margePresente = false;
            for (Compte compte:
                 LoginController.gestionnaireGuichet.getClient().getComptes()) {
                if (compte.getType().equals("marge")){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Demande impossible vous avez déjà une marge !");
                    alert.show();
                    margePresente = true;
                }
            }
            if (!margePresente){
                LoginController.gestionnaireGuichet.envoyerDemande(new Demande(codeClient,"marge",statut));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Votre demande à été envoyée");
                alert.show();
                demandeEnvoyee = true;
                margePresente = true;
            }
        }
        if (demandeEnvoyee){
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/affichageCompte.fxml")));
            scene = quitterBtn.getScene();
            scene.setRoot(root);
            ((Stage)scene.getWindow()).setTitle("Comptes");
        }
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
