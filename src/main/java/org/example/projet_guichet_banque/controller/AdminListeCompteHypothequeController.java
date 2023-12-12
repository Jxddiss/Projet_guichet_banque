package org.example.projet_guichet_banque.controller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import org.example.projet_guichet_banque.model.Compte;
import org.example.projet_guichet_banque.model.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class AdminListeCompteHypothequeController extends AdminListeCompteParent{
    @FXML
    private TextField montantTxtField;
    @FXML
    @Override
    public void initialize(){
        super.initialize();
        ArrayList<Compte> comptes;
        ObservableList<Compte> comptesObs;
        comptes = LoginController.gestionnaireGuichet.getComptesHypotheques();
        comptesObs = FXCollections.observableArrayList(comptes);
        tabCompte.setItems(comptesObs);
        transactions = new ArrayList<>();

        transactionsSansfiltre = LoginController.gestionnaireGuichet.getTransactionsAdmin();
        updateTransactions("hypotheque");
    }

    @FXML
    public void preleverClick(){
        try{
            double montant = Double.parseDouble(montantTxtField.getText());
            double solde = LoginController.gestionnaireGuichet.preleverMontant(tabCompte.getSelectionModel().getSelectedItem().getNumeroCompte(),montant);
            if (solde == -2){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Prélèvement Impossible");
                alert.show();
            }else if(solde == -1){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Vous n'êtes pas autorisé à faire cette action");
                alert.show();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Prélèvement confirmé " +
                        "\nTOTAL : "+ String.format("%.2f",solde));
                alert.show();
            }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuiller entré un montant valide!");
            alert.show();
        }
    }
}
