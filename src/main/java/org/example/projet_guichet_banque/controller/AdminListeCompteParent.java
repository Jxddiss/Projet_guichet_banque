package org.example.projet_guichet_banque.controller;

import javafx.beans.property.SimpleStringProperty;
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

public class AdminListeCompteParent {
    @FXML
    private Label prenomUtilisateurLbl;
    @FXML
    private Button quitterBtn;
    @FXML
    protected TableView<Compte> tabCompte;
    @FXML
    private TableColumn<Compte,String> numColonne;
    @FXML
    private TableColumn<Compte,String> typeColonne;
    @FXML
    private TableColumn<Compte,String> soldeColonne;
    @FXML
    private TableColumn<Compte,String> clientColonne;
    @FXML
    protected TableView<Transaction> transactionTable;
    @FXML
    private TableColumn<Transaction, String> montantColonne;
    @FXML
    private TableColumn<Transaction, String> destinationColonne;
    @FXML
    private TableColumn<Transaction, String> typeTransactionColonne;

    private Scene scene;
    private Parent root;

    @FXML
    public void initialize(){
        String prenom = LoginController.gestionnaireGuichet.getClient().getPrenom();
        prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1);
        prenomUtilisateurLbl.setText("Bonjour, " + prenom);

        numColonne.setCellValueFactory(cellData -> {
            String numCompte = String.format("%04d",cellData.getValue().getNumeroCompte());
            return new SimpleStringProperty(numCompte);
        });
        typeColonne.setCellValueFactory(new PropertyValueFactory<>("type"));
        clientColonne.setCellValueFactory(cellData -> {
            String codeClient = String.format("%04d",cellData.getValue().getCodeClient());
            return new SimpleStringProperty(codeClient);
        });
        soldeColonne.setCellValueFactory(cellData -> {
            String soldeFormatter = String.format("%.2f $",cellData.getValue().getSoldeCompte());
            return new SimpleStringProperty(soldeFormatter);
        });

        montantColonne.setCellValueFactory(cellData -> {
            String montantFormater = String.format("%.2f $",cellData.getValue().getMontant());
            return new SimpleStringProperty(montantFormater);
        });
        destinationColonne.setCellValueFactory(cellData -> {
            String compteFormater = "Compte : "+ String.format("%04d",cellData.getValue().getCompte());
            return new SimpleStringProperty(compteFormater);
        });
        typeTransactionColonne.setCellValueFactory(new PropertyValueFactory<>("type"));
    }
    @FXML
    public void quitterClick(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == quitterBtn){
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/adminListeCompte.fxml")));
            scene = quitterBtn.getScene();
            scene.setRoot(root);
            ((Stage)scene.getWindow()).setTitle("Liste de Comptes");
        }
    }
}
