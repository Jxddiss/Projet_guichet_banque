package org.example.projet_guichet_banque.controller;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.projet_guichet_banque.model.Client;
import org.example.projet_guichet_banque.model.Demande;

import java.io.IOException;
import java.util.Objects;

public class AdminListeDesClientsController {
    @FXML
    private Label prenomUtilisateurLbl;
    @FXML
    private Button quitterBtn;
    @FXML
    private Button debloquerBtn;
    @FXML
    private Button bloquerBtn;
    @FXML
    private TableView<Client> clientsTab;
    @FXML
    private TableColumn<Client, String> numColonne;
    @FXML
    private TableColumn<Client, String> prenomColonne;
    @FXML
    private TableColumn<Client, String> nomColonne;
    @FXML
    private TableColumn<Client, String> telephoneColonne;
    @FXML
    private TableColumn<Client, String> courrielColonne;
    @FXML
    private TableColumn<Client, String> statutColonne;
    private ObservableList<Client> clients;
    private Scene scene;
    private Parent root;

    @FXML
    public void initialize(){
        String prenom = LoginController.gestionnaireGuichet.getClient().getPrenom();
        prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1);
        prenomUtilisateurLbl.setText("Bonjour, " + prenom);

        clients = FXCollections.observableArrayList(LoginController.gestionnaireGuichet.getClientsAdmin());
        numColonne.setCellValueFactory(cellData -> {
            String numDemande = String.format("%04d",cellData.getValue().getCodeClient());
            return new SimpleStringProperty(numDemande);
        });
        prenomColonne.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        nomColonne.setCellValueFactory(new PropertyValueFactory<>("nom"));
        telephoneColonne.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        courrielColonne.setCellValueFactory(new PropertyValueFactory<>("couriel"));
        statutColonne.setCellValueFactory(new PropertyValueFactory<>("statut"));
        clientsTab.setItems(clients);
    }

    @FXML
    public void debloquerClick(ActionEvent actionEvent){
        if (actionEvent.getSource() == debloquerBtn){
            Client client = clientsTab.getSelectionModel().getSelectedItem();

            if (client.getStatut().equals("Bloque")){
                client.setStatut("Ok");
            }
            clientsTab.refresh();
        }
    }

    @FXML
    public void bloquerClick(ActionEvent actionEvent){
        if (actionEvent.getSource() == bloquerBtn){
            Client client = clientsTab.getSelectionModel().getSelectedItem();

            if (client.getStatut().equals("Ok")){
                client.setStatut("Bloque");
            }
            clientsTab.refresh();
        }
    }

    @FXML
    public void quitterClick(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == quitterBtn){
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/affichageAdmin.fxml")));
            scene = quitterBtn.getScene();
            scene.setRoot(root);
            ((Stage)scene.getWindow()).setTitle("Panel Admin");
        }
    }
}
