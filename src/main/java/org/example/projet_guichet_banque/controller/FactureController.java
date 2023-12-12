package org.example.projet_guichet_banque.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class FactureController {
    @FXML
    private Label prenomUtilisateurLbl;
    @FXML
    private TextField montantTxtField;
    @FXML
    private TextField numFactureTxtField;
    @FXML
    private Label numCompteLbl;
    @FXML
    private Label soldeLbl;
    @FXML
    private Button quitterBtn;
    @FXML
    private Button confirmerBtn;
    private int numCompteCourrant;
    private Scene scene;
    private Parent root;

    @FXML
    public void initialize(){
        String prenom = LoginController.gestionnaireGuichet.getClient().getPrenom();
        prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1);
        prenomUtilisateurLbl.setText("Bonjour, " + prenom);
        numCompteCourrant = AffichageCompteController.compteChoisi.getNumeroCompte();
        numCompteLbl.setText(String.format("%04d",numCompteCourrant));
        soldeLbl.setText(String.format("%.2f $",AffichageCompteController.compteChoisi.getSoldeCompte()));
    }

    @FXML
    public void confirmerClick(ActionEvent actionEvent){
        if (actionEvent.getSource() == confirmerBtn){
            try{
                double montant = Double.parseDouble(montantTxtField.getText());
                String numFacture = numFactureTxtField.getText();
                if (!numFacture.isEmpty()){
                    if (LoginController.gestionnaireGuichet.paiementFacture(montant,numCompteCourrant)){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Paiement de la facture : "+ numFacture + " confirmé "+
                                "\nNouveau solde :"+
                                "\n\t\t" + String.format("%.2f $",LoginController.gestionnaireGuichet.afficherSoldeCompte(numCompteCourrant)));
                        alert.show();
                    }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Erreur lors du paiement de la facture!");
                        alert.show();
                    }
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/affichageCompte.fxml")));
                    scene = quitterBtn.getScene();
                    scene.setRoot(root);
                    ((Stage)scene.getWindow()).setTitle("Comptes");
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Veuillez entrer le numéro de facture");
                    alert.show();
                }
            }catch(Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Valeur pour montant entrée non prise en charge");
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
