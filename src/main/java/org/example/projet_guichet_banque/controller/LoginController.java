package org.example.projet_guichet_banque.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.projet_guichet_banque.model.Banque;
import org.example.projet_guichet_banque.model.BanqueDAO;
import org.example.projet_guichet_banque.model.GestionnaireGuichet;

public class LoginController {

    Banque banque = BanqueDAO.getAll();
    GestionnaireGuichet gestionnaireGuichet = new GestionnaireGuichet(banque);

    @FXML
    public void initialize(){
    }
}