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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
    @FXML
    private Rectangle caseCheque;
    @FXML
    private Rectangle caseEpargne;
    @FXML
    private Rectangle caseHypotheque;
    @FXML
    private Rectangle caseMargeCredit;
    private Scene scene;
    private Parent root;
    private boolean margePresente = false;

    @FXML
    public void initialize(){
        String prenom = LoginController.gestionnaireGuichet.getClient().getPrenom();
        prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1);
        prenomUtilisateurLbl.setText("Bonjour, " + prenom);

        // Code pour avoir un effet de Hover sur les 4 cases afficher.
        chequePaneBtn.setOnMouseEntered(event -> {caseCheque.setStroke(Color.BLACK); caseCheque.setStrokeWidth(2);});
        chequePaneBtn.setOnMouseExited(event -> {caseCheque.setStroke(Color.BLACK); caseCheque.setStrokeWidth(0);});

        epargnePaneBtn.setOnMouseEntered(event -> {caseEpargne.setStroke(Color.BLACK); caseEpargne.setStrokeWidth(2);});
        epargnePaneBtn.setOnMouseExited(event -> {caseEpargne.setStroke(Color.BLACK); caseEpargne.setStrokeWidth(0);});

        hypothecairePaneBtn.setOnMouseEntered(event -> {caseHypotheque.setStroke(Color.BLACK); caseHypotheque.setStrokeWidth(2);});
        hypothecairePaneBtn.setOnMouseExited(event -> {caseHypotheque.setStroke(Color.BLACK); caseHypotheque.setStrokeWidth(0);});

        margePaneBtn.setOnMouseEntered(event -> {caseMargeCredit.setStroke(Color.BLACK); caseMargeCredit.setStrokeWidth(2);});
        margePaneBtn.setOnMouseExited(event -> {caseMargeCredit.setStroke(Color.BLACK); caseMargeCredit.setStrokeWidth(0);});
    }

    @FXML
    public void creerClick(MouseEvent mouseEvent)throws IOException{
        int codeClient = LoginController.gestionnaireGuichet.getClient().getCodeClient();
        String statut = "En cours";

        if (mouseEvent.getSource() == chequePaneBtn){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Votre demande à été envoyée");
            alert.show();
            LoginController.gestionnaireGuichet.envoyerDemande(new Demande(codeClient,"cheque",statut));
        }
        if (mouseEvent.getSource() == epargnePaneBtn){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Votre demande à été envoyée");
            alert.show();
            LoginController.gestionnaireGuichet.envoyerDemande(new Demande(codeClient,"epargne",statut));
        }
        if (mouseEvent.getSource() == hypothecairePaneBtn){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Votre demande à été envoyée");
            alert.show();
            LoginController.gestionnaireGuichet.envoyerDemande(new Demande(codeClient,"hypotheque",statut));
        }
        if (mouseEvent.getSource() == margePaneBtn){

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
                if (LoginController.gestionnaireGuichet.envoyerDemande(new Demande(codeClient,"marge",statut))){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Votre demande à été envoyée");
                    alert.show();
                    margePresente = true;
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Demande impossible vous avez déjà fait une demande pour une marge!");
                    alert.show();
                    margePresente = true;
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Demande impossible vous avez déjà fait une demande pour une marge!");
                alert.show();
            }
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
