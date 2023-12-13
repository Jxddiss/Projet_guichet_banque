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
import org.example.projet_guichet_banque.model.GestionnaireGuichetDAO;

import java.io.IOException;
import java.util.Objects;

public class AffichageAdminController {
    @FXML
    private Label prenomUtilisateurLbl;
    @FXML
    private Button deconnecterBtn;
    @FXML
    private Pane creerClientPaneBtn;
    @FXML
    private Pane demandeComptePaneBtn;
    @FXML
    private Pane remplirGuichetPaneBtn;
    @FXML
    private Pane listeComptePaneBtn;
    @FXML
    private Pane listeClientPaneBtn;
    @FXML
    private Pane fermerGuichetPaneBtn;
    @FXML
    private Rectangle caseCreerClient;
    @FXML
    private Rectangle caseDemandeCompte;
    @FXML
    private Rectangle caseRemplirGuichet;
    @FXML
    private Rectangle caseListeCompte;
    @FXML
    private Rectangle caseListeClient;
    @FXML
    private Rectangle caseFermerGuichet;
    private Scene scene;
    private Parent root;

    @FXML
    public void initialize(){
        String prenom = LoginController.gestionnaireGuichet.getClient().getPrenom();
        prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1);
        prenomUtilisateurLbl.setText("Bonjour, " + prenom);

        // Code pour avoir un effet de Hover sur les 6 options afficher.
        creerClientPaneBtn.setOnMouseEntered(event -> {caseCreerClient.setStroke(Color.BLACK); caseCreerClient.setStrokeWidth(2);});
        creerClientPaneBtn.setOnMouseExited(event -> {caseCreerClient.setStroke(Color.BLACK); caseCreerClient.setStrokeWidth(0);});

        demandeComptePaneBtn.setOnMouseEntered(event -> {caseDemandeCompte.setStroke(Color.BLACK); caseDemandeCompte.setStrokeWidth(2);});
        demandeComptePaneBtn.setOnMouseExited(event -> {caseDemandeCompte.setStroke(Color.BLACK); caseDemandeCompte.setStrokeWidth(0);});

        remplirGuichetPaneBtn.setOnMouseEntered(event -> {caseRemplirGuichet.setStroke(Color.BLACK); caseRemplirGuichet.setStrokeWidth(2);});
        remplirGuichetPaneBtn.setOnMouseExited(event -> {caseRemplirGuichet.setStroke(Color.BLACK); caseRemplirGuichet.setStrokeWidth(0);});

        listeComptePaneBtn.setOnMouseEntered(event -> {caseListeCompte.setStroke(Color.BLACK); caseListeCompte.setStrokeWidth(2);});
        listeComptePaneBtn.setOnMouseExited(event -> {caseListeCompte.setStroke(Color.BLACK); caseListeCompte.setStrokeWidth(0);});

        listeClientPaneBtn.setOnMouseEntered(event -> {caseListeClient.setStroke(Color.BLACK); caseListeClient.setStrokeWidth(2);});
        listeClientPaneBtn.setOnMouseExited(event -> {caseListeClient.setStroke(Color.BLACK); caseListeClient.setStrokeWidth(0);});

        fermerGuichetPaneBtn.setOnMouseEntered(event -> {caseFermerGuichet.setStroke(Color.BLACK); caseFermerGuichet.setStrokeWidth(2);});
        fermerGuichetPaneBtn.setOnMouseExited(event -> {caseFermerGuichet.setStroke(Color.BLACK); caseFermerGuichet.setStrokeWidth(0);});
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

    @FXML
    public void creerClient(MouseEvent event) throws IOException{
        if(event.getSource() == creerClientPaneBtn){
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/adminCreerClient.fxml")));
            scene = creerClientPaneBtn.getScene();
            scene.setRoot(root);
            ((Stage)scene.getWindow()).setTitle("Création de client");
        }
    }
    @FXML
    public void ouvrirDemandes(MouseEvent event) throws IOException{
        if(event.getSource() == demandeComptePaneBtn){
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/adminListeDemande.fxml")));
            scene = demandeComptePaneBtn.getScene();
            scene.setRoot(root);
            ((Stage)scene.getWindow()).setTitle("Liste de demandes");
        }
    }
    @FXML
    public void remplirGuichet(){
        double soldeBanque = LoginController.gestionnaireGuichet.remplirGuichet();
        if (soldeBanque != -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Guichet rempli" +
                    "\nNouveau solde : "+String.format("%.2f $",soldeBanque));
            alert.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Guichet déjà rempli!");
            alert.show();
        }

    }
    @FXML
    public void ouvrirComptes(MouseEvent event) throws IOException{
        if(event.getSource() == listeComptePaneBtn){
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/adminListeCompte.fxml")));
            scene = listeComptePaneBtn.getScene();
            scene.setRoot(root);
            ((Stage)scene.getWindow()).setTitle("Liste de comptes");
        }
    }
    @FXML
    public void ouvrirClients(MouseEvent event) throws IOException{
        if(event.getSource() == listeClientPaneBtn){
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/adminListeDesClients.fxml")));
            scene = listeClientPaneBtn.getScene();
            scene.setRoot(root);
            ((Stage)scene.getWindow()).setTitle("Liste de clients");
        }
    }
    @FXML
    public void fermerGuichet(MouseEvent event) throws IOException{
        if(event.getSource() == fermerGuichetPaneBtn){
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/adminFermerGuichet.fxml")));
            scene = fermerGuichetPaneBtn.getScene();
            scene.setRoot(root);
            ((Stage)scene.getWindow()).setTitle("Fermeture du guichet");
        }
    }

}
