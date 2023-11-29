package org.example.projet_guichet_banque.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.projet_guichet_banque.model.Compte;
import org.example.projet_guichet_banque.model.GestionnaireGuichetDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class AffichageCompteController {
    @FXML
    private Label prenomUtilisateurLbl;
    @FXML
    private Button deconnecterBtn;
    @FXML
    private Pane creerComptePaneButton;
    @FXML
    private Pane voirToutComptePaneBtn;
    @FXML
    private Label typeCompteLbl;
    @FXML
    private Label numCompteLbl;
    @FXML
    private Label soldeCompteLbl;
    @FXML
    private Label typeCompteLbl1;
    @FXML
    private Label numCompteLbl1;
    @FXML
    private Label soldeCompteLbl1;
    @FXML
    private Label typeCompteLbl2;
    @FXML
    private Label numCompteLbl2;
    @FXML
    private Label soldeCompteLbl2;
    @FXML
    private Pane compte1PaneBtn;
    @FXML
    private Pane compte2PaneBtn;
    @FXML
    private Pane compte3PaneBtn;
    private Scene scene;
    private Parent root;
    private String type = "";
    static String num = "";
    private String solde = "";
    private String type1 = "";
    static String num1 = "";
    private String solde1 = "";
    private String type2 = "";
    static String num2 = "";
    private String solde2 = "";

    @FXML
    public void initialize(){
        String prenom = LoginController.gestionnaireGuichet.getClient().getPrenom();
        prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1);

        prenomUtilisateurLbl.setText("Bonjour, " + prenom);

        ArrayList<Compte> comptes = LoginController.gestionnaireGuichet.getClient().getComptes();
        type = comptes.get(0).getType().substring(0,1).toUpperCase() + comptes.get(0).getType().substring(1);
        num = String.format("%04d",comptes.get(0).getNumeroCompte());
        solde = String.format("%.2f $",comptes.get(0).getSoldeCompte());

        if(comptes.size() >= 3){
            type1 = comptes.get(1).getType().substring(0,1).toUpperCase() + comptes.get(1).getType().substring(1);
            num1 = String.format("%04d",comptes.get(1).getNumeroCompte());
            solde1 = String.format("%.2f $",comptes.get(1).getSoldeCompte());

            type2 = comptes.get(2).getType().substring(0,1).toUpperCase() + comptes.get(2).getType().substring(1);
            num2 = String.format("%04d",comptes.get(2).getNumeroCompte());
            solde2 = String.format("%.2f $",comptes.get(2).getSoldeCompte());
        }else if(comptes.size() == 2){
            type1 = comptes.get(1).getType().substring(0,1).toUpperCase() + comptes.get(1).getType().substring(1);
            num1 = String.format("%04d",comptes.get(1).getNumeroCompte());
            solde1 = String.format("%.2f $",comptes.get(1).getSoldeCompte());
        }

        typeCompteLbl.setText(type);
        numCompteLbl.setText(num);
        soldeCompteLbl.setText(solde);

        typeCompteLbl1.setText(type1);
        numCompteLbl1.setText(num1);
        soldeCompteLbl1.setText(solde1);

        typeCompteLbl2.setText(type2);
        numCompteLbl2.setText(num2);
        soldeCompteLbl2.setText(solde2);
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
    public void creerButtonClick(MouseEvent event) throws IOException{
        if(event.getSource() == creerComptePaneButton){
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/creationCompte.fxml")));
            scene = deconnecterBtn.getScene();
            scene.setRoot(root);
            ((Stage)scene.getWindow()).setTitle("Création de compte");
        }
    }

    @FXML
    public void toutCompteClick(MouseEvent event) throws IOException{
        if(event.getSource() == voirToutComptePaneBtn){
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/listeDesCompte.fxml")));
            scene = voirToutComptePaneBtn.getScene();
            scene.setRoot(root);
            ((Stage)scene.getWindow()).setTitle("Tout les comptes");
        }
    }
    @FXML
    public void ouvrirCompteClick(MouseEvent event) throws IOException{
        if(event.getSource() == compte1PaneBtn){
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/compte"+type+".fxml")));
            scene = compte1PaneBtn.getScene();
            scene.setRoot(root);
            ((Stage)scene.getWindow()).setTitle("Compte "+type);
        }

        if (!Objects.equals(type1, "")){
            if(event.getSource() == compte2PaneBtn){
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/compte"+type1+".fxml")));
                scene = compte1PaneBtn.getScene();
                scene.setRoot(root);
                ((Stage)scene.getWindow()).setTitle("Compte "+type1);
            }
        }

        if (!Objects.equals(type2, "")){
            if(event.getSource() == compte3PaneBtn){
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/compte"+type2+".fxml")));
                scene = compte1PaneBtn.getScene();
                scene.setRoot(root);
                ((Stage)scene.getWindow()).setTitle("Compte "+type2);
            }
        }
    }
}

