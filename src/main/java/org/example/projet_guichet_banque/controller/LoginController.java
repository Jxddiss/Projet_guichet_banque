package org.example.projet_guichet_banque.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.projet_guichet_banque.model.Banque;
import org.example.projet_guichet_banque.model.BanqueDAO;
import org.example.projet_guichet_banque.model.GestionnaireGuichet;
import org.example.projet_guichet_banque.model.GestionnaireGuichetDAO;

public class LoginController {

    GestionnaireGuichet gestionnaireGuichet = GestionnaireGuichetDAO.get();

    @FXML
    public void initialize(){
    }


}