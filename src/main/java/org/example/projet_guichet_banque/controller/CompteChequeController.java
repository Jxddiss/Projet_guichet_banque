package org.example.projet_guichet_banque.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CompteChequeController extends CompteControllerParent{
    // Payer facture juste compte marge et hypothécaires dans la liste
    @FXML
    private Button transfertBtn;


    @FXML
    public void transfertClick(ActionEvent actionEvent)throws IOException {
        if (actionEvent.getSource() == transfertBtn){
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/transfert.fxml")));
            scene = transfertBtn.getScene();
            scene.setRoot(root);
            ((Stage)scene.getWindow()).setTitle("Transfert");
        }
    }

}
