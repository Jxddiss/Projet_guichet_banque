package org.example.projet_guichet_banque.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.projet_guichet_banque.model.GestionnaireGuichetDAO;

import java.io.IOException;
import java.util.Objects;

public class DeposerController {
    @FXML
    private Label prenomUtilisateurLbl;
    @FXML
    private Pane deposer10PaneBtn;
    @FXML
    private Pane deposer50PaneBtn;
    @FXML
    private Pane deposer100PaneBtn;
    @FXML
    private Pane deposer200PaneBtn;
    @FXML
    private Button confirmerBtn;
    @FXML
    private TextField montantTxtField;
    @FXML
    private Button quitterBtn;
    private Scene scene;
    private Parent root;

    @FXML
    public void initialize(){
        String prenom = LoginController.gestionnaireGuichet.getClient().getPrenom();
        prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1);
        prenomUtilisateurLbl.setText("Bonjour, " + prenom);
    }

    @FXML
    public void montantChoisiClick(MouseEvent event)throws IOException {
        if (event.getSource() == deposer10PaneBtn){
            montantTxtField.setText("10");
        }
        if (event.getSource() == deposer50PaneBtn){
            montantTxtField.setText("50");
        }
        if (event.getSource() == deposer100PaneBtn){
            montantTxtField.setText("100");
        }
        if (event.getSource() == deposer200PaneBtn){
            montantTxtField.setText("200");
        }
    }

    @FXML
    public void confirmerClick(ActionEvent actionEvent) throws IOException{
        double solde = 0;
        if (actionEvent.getSource() == confirmerBtn){
            try{
                double montant = Double.parseDouble(montantTxtField.getText());
                if (AffichageCompteController.compteChoisi.getType().equals("cheque")){
                    solde = LoginController.gestionnaireGuichet.depotCheque(montant,AffichageCompteController.compteChoisi.getNumeroCompte());
                } else if (AffichageCompteController.compteChoisi.getType().equals("epargne")) {
                    solde = LoginController.gestionnaireGuichet.depotEpargne(montant,AffichageCompteController.compteChoisi.getNumeroCompte());
                }

                if (solde != -1){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Dépot confirmé "+
                            "\nVotre nouveau solde est de : "+
                            String.format("%.2f $", solde));
                    alert.show();
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/affichageCompte.fxml")));
                    scene = confirmerBtn.getScene();
                    scene.setRoot(root);
                    ((Stage)scene.getWindow()).setTitle("Comptes");
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Erreur lors du dépot");
                    alert.show();
                }
            }catch (NumberFormatException nf){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("FORMAT NON ACCEPTÉ : Veuillez entré un nombre");
                alert.show();
            }finally {
                GestionnaireGuichetDAO.save(LoginController.gestionnaireGuichet);
            }
        }
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
