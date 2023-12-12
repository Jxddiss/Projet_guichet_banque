package org.example.projet_guichet_banque.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.projet_guichet_banque.model.CompteCheque;

import java.io.IOException;
import java.util.Objects;

public class CompteChequeController extends CompteControllerParent{
    // Payer facture juste compte marge et hypothécaires dans la liste
    @FXML
    private Button transfertBtn;
    @FXML
    private Button factureBtn;
    @FXML
    private Label transfertMaxLbl;
    @FXML
    private Label factureMaxLbl;

    @FXML
    @Override
    public void initialize(){
        super.initialize();
        transfertMaxLbl.setText(String.format("%.2f $",AffichageCompteController.compteChoisi.getMontantTransfertMaximum()));
        factureMaxLbl.setText(String.format("%.2f $",((CompteCheque) AffichageCompteController.compteChoisi).getMontantFactureMaximum()));
    }

    @FXML
    public void transfertClick(ActionEvent actionEvent)throws IOException {
        if (actionEvent.getSource() == transfertBtn){
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/transfert.fxml")));
            scene = transfertBtn.getScene();
            scene.setRoot(root);
            ((Stage)scene.getWindow()).setTitle("Transfert");
        }
    }

    @FXML
    public void factureClick(ActionEvent actionEvent)throws IOException{
        if (actionEvent.getSource() == factureBtn){
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/facture.fxml")));
            scene = factureBtn.getScene();
            scene.setRoot(root);
            ((Stage)scene.getWindow()).setTitle("Paiement de facture");
        }
    }

}
