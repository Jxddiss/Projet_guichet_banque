package org.example.projet_guichet_banque.controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AdminListeCompteController {
    @FXML
    private Label prenomUtilisateurLbl;
    @FXML
    private Button quitterBtn;
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
    public void ouvrirClick(MouseEvent mouseEvent)throws IOException{
        if (mouseEvent.getSource() == chequePaneBtn){
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/adminListeCompteCheque.fxml")));
            scene = chequePaneBtn.getScene();
            scene.setRoot(root);
            ((Stage)scene.getWindow()).setTitle("Liste Compte Chèque");
        }

        if (mouseEvent.getSource() == epargnePaneBtn){
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/adminListeCompteEpargne.fxml")));
            scene = epargnePaneBtn.getScene();
            scene.setRoot(root);
            ((Stage)scene.getWindow()).setTitle("Liste Compte Épargne");
        }

        if (mouseEvent.getSource() == hypothecairePaneBtn){
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/adminListeCompteHypotheque.fxml")));
            scene = hypothecairePaneBtn.getScene();
            scene.setRoot(root);
            ((Stage)scene.getWindow()).setTitle("Liste Compte Hypothécaire");
        }

        if (mouseEvent.getSource() == margePaneBtn){
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/adminListeCompteMarge.fxml")));
            scene = margePaneBtn.getScene();
            scene.setRoot(root);
            ((Stage)scene.getWindow()).setTitle("Liste Marges de Crédits");
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
