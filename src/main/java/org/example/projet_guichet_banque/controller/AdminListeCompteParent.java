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
    private TableColumn<Compte,String> clientColonne;

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
