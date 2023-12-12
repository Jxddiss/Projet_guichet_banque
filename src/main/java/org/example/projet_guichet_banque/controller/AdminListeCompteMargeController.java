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
import javafx.stage.Stage;
import org.example.projet_guichet_banque.model.Compte;
import org.example.projet_guichet_banque.model.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class AdminListeCompteMargeController extends AdminListeCompteParent{

    @FXML
    @Override
    public void initialize(){
        super.initialize();
        ArrayList<Compte> comptes;
        ObservableList<Compte> comptesObs;
        comptes = LoginController.gestionnaireGuichet.getComptesMarges();
        comptesObs = FXCollections.observableArrayList(comptes);
        tabCompte.setItems(comptesObs);
        transactions = new ArrayList<>();

        transactionsSansfiltre = LoginController.gestionnaireGuichet.getTransactionsAdmin();
        updateTransactions("marge");
    }

    @FXML
    public void augmenterClick(){
        double interetAppliquer = LoginController.gestionnaireGuichet.augmenterMarge();
        tabCompte.refresh();
        updateTransactions("marge");
        transactionTable.refresh();
        if (interetAppliquer != -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Intérêt ajoutés à tout les comptes ! " +
                    "\nTOTAL : "+ String.format("%.2f $",interetAppliquer));
            alert.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Une erreur c'est produite");
            alert.show();
        }
    }
}
