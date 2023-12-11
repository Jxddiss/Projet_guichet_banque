package org.example.projet_guichet_banque.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.example.projet_guichet_banque.model.Compte;

import java.io.IOException;
import java.util.Objects;

public class TransfertController {
    @FXML
    private Label prenomUtilisateurLbl;
    @FXML
    private Button quitterBtn;
    @FXML
    private TableView<Compte> tabComptes;
    @FXML
    private TableColumn<Compte, String> numCompteColonne;
    @FXML
    private TableColumn<Compte, String> typeCompteColonne;
    @FXML
    private TableColumn<Compte, String> soldeColonne;
    @FXML
    private TableColumn<Compte, Void> ouvrirColonne;
    private ObservableList<Compte> comptes;
    private Scene scene;
    private Parent root;

    @FXML
    public void initialize(){

        String prenom = LoginController.gestionnaireGuichet.getClient().getPrenom();
        prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1);
        prenomUtilisateurLbl.setText("Bonjour, " + prenom);

        comptes = FXCollections.observableArrayList(LoginController.gestionnaireGuichet.getClient().getComptes());
        soldeColonne.setCellValueFactory(cellData -> {
            String montantFormater = String.format("%.2f $",cellData.getValue().getSoldeCompte());
            return new SimpleStringProperty(montantFormater);
        });
        numCompteColonne.setCellValueFactory(cellData -> {
            String compteFormater = "Compte : "+ String.format("%04d",cellData.getValue().getNumeroCompte());
            return new SimpleStringProperty(compteFormater);
        });
        typeCompteColonne.setCellValueFactory(new PropertyValueFactory<>("type"));
        ajouterButtonColonne(ouvrirColonne);
        tabComptes.setItems(comptes);
        tabComptes.setSelectionModel(null);
    }

    /**
     * Fonction pour ajouter des boutons qui ouvre le compte associé
     * à la colonne
     *
     * @param colonne : colonne avec un compte qui sera passé en parametre
     * */
    private void ajouterButtonColonne(TableColumn<Compte, Void> colonne){
        Callback<TableColumn<Compte, Void>, TableCell<Compte, Void>> cellFactory = new Callback<TableColumn<Compte, Void>, TableCell<Compte, Void>>(){
            @Override
            public TableCell<Compte,Void> call(final TableColumn<Compte,Void> param){
                final TableCell<Compte, Void> cell = new TableCell<Compte, Void>(){
                    private final Button btn = new Button("Transferer");
                    {
                        btn.setOnAction((ActionEvent event)->{
                            Compte data = getTableView().getItems().get(getIndex());
                            AffichageCompteController.compteChoisi = data;
                            String type = data.getType().substring(0,1).toUpperCase() + data.getType().substring(1);
                            try {
                                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/compte"+type+".fxml")));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            scene = btn.getScene();
                            scene.setRoot(root);
                            ((Stage)scene.getWindow()).setTitle("Compte "+type);
                        });
                        btn.setCursor(Cursor.HAND);
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colonne.setCellFactory(cellFactory);
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
