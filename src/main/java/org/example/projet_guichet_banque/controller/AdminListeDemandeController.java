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
import org.example.projet_guichet_banque.model.Demande;

import java.io.IOException;
import java.util.Objects;

public class AdminListeDemandeController {
    @FXML
    private Label prenomUtilisateurLbl;
    @FXML
    private Button quitterBtn;
    @FXML
    private TableView<Demande> demandeTab;
    @FXML
    private TableColumn<Demande,String> numDemandeColonne;
    @FXML
    private TableColumn<Demande,String> codeClientColonne;
    @FXML
    private TableColumn<Demande,String> typeCompteColonne;
    @FXML
    private TableColumn<Demande,String> statutColonne;
    private ObservableList<Demande> demandes;
    private Scene scene;
    private Parent root;

    @FXML
    public void initialize(){
        String prenom = LoginController.gestionnaireGuichet.getClient().getPrenom();
        prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1);
        prenomUtilisateurLbl.setText("Bonjour, " + prenom);

        demandes = FXCollections.observableArrayList(LoginController.gestionnaireGuichet.getDemandesComptes());
        numDemandeColonne.setCellValueFactory(cellData -> {
            String numDemande = String.format("%04d",cellData.getValue().getNumeroDemande());
            return new SimpleStringProperty(numDemande);
        });
        codeClientColonne.setCellValueFactory(cellData -> {
            String codeClient = String.format("%04d",cellData.getValue().getCodeClient());
            return new SimpleStringProperty(codeClient);
        });
        typeCompteColonne.setCellValueFactory(new PropertyValueFactory<>("typeCompte"));
        statutColonne.setCellValueFactory(new PropertyValueFactory<>("statut"));
        demandeTab.setItems(demandes);
    }

    @FXML
    public void approuverClick(){
        Demande demande = demandeTab.getSelectionModel().getSelectedItem();
        double montantTransfert = 0;
        double montantFacture = 0;
        if (demande.getStatut().equals("En cours")){
            demande.setStatut("approuve");
            if (demande.getTypeCompte().equals("cheque")){
                montantTransfert = 2000;
                montantFacture = 3000;
            }
            demandeTab.refresh();
            LoginController.gestionnaireGuichet.creerCompte(demande.getTypeCompte(),demande.getCodeClient(),montantTransfert,montantFacture);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Demande Approuvée");
            alert.show();
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
