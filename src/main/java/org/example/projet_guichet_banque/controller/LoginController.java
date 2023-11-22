package org.example.projet_guichet_banque.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.projet_guichet_banque.model.Banque;
import org.example.projet_guichet_banque.model.BanqueDAO;
import org.example.projet_guichet_banque.model.GestionnaireGuichet;
import org.example.projet_guichet_banque.model.GestionnaireGuichetDAO;

public class LoginController {
    private GestionnaireGuichet gestionnaireGuichet = GestionnaireGuichetDAO.get();
    @FXML
    private TextField codeClientTxtField;
    @FXML
    private PasswordField nipTxtField;

    @FXML
    public void initialize(){
    }

    public void connecterClick(){
        int codeClient = Integer.parseInt(codeClientTxtField.getText());
        int nip = Integer.parseInt(nipTxtField.getText());
        gestionnaireGuichet.validerUtilisateur(codeClient,nip);

        System.out.println("Connecter en tant que "+ gestionnaireGuichet.getClient().getPrenom());
    }

}