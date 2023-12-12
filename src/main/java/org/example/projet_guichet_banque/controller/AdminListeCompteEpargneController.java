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

public class AdminListeCompteEpargneController extends AdminListeCompteParent{
    private ArrayList<Transaction> transactionsSansfiltre;
    private ObservableList<Transaction> transactionsObs;
    private ArrayList<Transaction> transactions;

    @FXML
    @Override
    public void initialize(){
        super.initialize();
        ArrayList<Compte> comptes;
        ObservableList<Compte> comptesObs;
        comptes = LoginController.gestionnaireGuichet.getComptesEpargnes();
        comptesObs = FXCollections.observableArrayList(comptes);
        tabCompte.setItems(comptesObs);
        transactions = new ArrayList<>();

        transactionsSansfiltre = LoginController.gestionnaireGuichet.getTransactionsAdmin();
        updateTransactions();

    }

    @FXML
    public void interetClick(){
        double interetVerser = LoginController.gestionnaireGuichet.payerInteret();
        tabCompte.refresh();
        updateTransactions();
        transactionTable.refresh();
        if (interetVerser != -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Intérêt appliqué à tout les comptes ! " +
                    "\nTOTAL : "+ String.format("%.2f $",interetVerser));
            alert.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Une erreur c'est produite");
            alert.show();
        }
    }

    private void updateTransactions(){
        transactions = new ArrayList<>();

        for (Transaction trans:
                transactionsSansfiltre) {
            if (trans.getType().startsWith("epargne")){
                transactions.add(trans);
            }
        }

        transactionsObs = FXCollections.observableArrayList(transactions);
        transactionTable.setItems(transactionsObs);
    }
}
