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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.projet_guichet_banque.model.Compte;
import org.example.projet_guichet_banque.model.Transaction;

import java.io.IOException;
import java.util.Objects;

public class CompteHypothecaireController {
    @FXML
    private Label prenomUtilisateurLbl;
    @FXML
    private Label numCompteLbl;
    @FXML
    private Label soldeLbl;
    @FXML
    private Button quitterBtn;
    @FXML
    private TableView<Transaction> transactionTable;
    @FXML
    private TableColumn<Transaction, String> montantColonne;
    @FXML
    private TableColumn<Transaction, String> destinationColonne;
    @FXML
    private TableColumn<Transaction, String> typeColonne;
    private ObservableList<Transaction> transactions;
    private Scene scene;
    private Parent root;

    @FXML
    public void initialize(){
        String prenom = LoginController.gestionnaireGuichet.getClient().getPrenom();
        prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1);
        prenomUtilisateurLbl.setText("Bonjour, " + prenom);
        numCompteLbl.setText(String.format("%04d",AffichageCompteController.compteChoisi.getNumeroCompte()));
        soldeLbl.setText(String.format("%.2f $",AffichageCompteController.compteChoisi.getSoldeCompte()));


        transactions = FXCollections.observableArrayList(LoginController.gestionnaireGuichet.getTransactions(AffichageCompteController.compteChoisi.getNumeroCompte()));
        montantColonne.setCellValueFactory(cellData -> {
            String montantFormater = String.format("%.2f $",cellData.getValue().getMontant());
            return new SimpleStringProperty(montantFormater);
        });
        destinationColonne.setCellValueFactory(cellData -> {
            String compteFormater = "Compte : "+ cellData.getValue().getCompte();
            return new SimpleStringProperty(compteFormater);
        });
        typeColonne.setCellValueFactory(new PropertyValueFactory<>("type"));
        transactionTable.setItems(transactions);
    }

    @FXML
    public void quitterClick(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == quitterBtn){
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/affichageCompte.fxml")));
            scene = quitterBtn.getScene();
            scene.setRoot(root);
            ((Stage)scene.getWindow()).setTitle("Comptes");
        }
    }
}
