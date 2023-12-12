package org.example.projet_guichet_banque.controller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.projet_guichet_banque.model.Compte;
import org.example.projet_guichet_banque.model.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class AdminListeCompteEpargneController extends AdminListeCompteParent{
    @FXML
    @Override
    public void initialize(){
        super.initialize();
        ArrayList<Compte> comptes;
        ObservableList<Compte> comptesObs;
        comptes = LoginController.gestionnaireGuichet.getComptesEpargnes();
        comptesObs = FXCollections.observableArrayList(comptes);
        tabCompte.setItems(comptesObs);

        ArrayList<Transaction> transactionsSansfiltre;
        ArrayList<Transaction> transactions = new ArrayList<>();
        ObservableList<Transaction> transactionsObs;

        transactionsSansfiltre = LoginController.gestionnaireGuichet.getTransactionsAdmin();
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
