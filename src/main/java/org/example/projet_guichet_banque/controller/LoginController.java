package org.example.projet_guichet_banque.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.projet_guichet_banque.model.GestionnaireGuichet;
import org.example.projet_guichet_banque.model.GestionnaireGuichetDAO;
import org.example.projet_guichet_banque.model.Setup;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class LoginController {
    static GestionnaireGuichet gestionnaireGuichet;
    @FXML
    private TextField codeClientTxtField;
    @FXML
    private PasswordField nipTxtField;
    @FXML
    private Button connecterBtn;
    @FXML
    private Label temps;

    @FXML
    public void initialize(){
        Setup.setup();
        gestionnaireGuichet = GestionnaireGuichetDAO.get();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            String formattedTime = dateFormat.format(new Date());
            temps.setText(formattedTime);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    public void connecterClick(ActionEvent actionEvent) throws IOException {
        Scene scene;
        Parent root;
        try{
            int codeClient = Integer.parseInt(codeClientTxtField.getText());
            int nip = Integer.parseInt(nipTxtField.getText());
            int verification = gestionnaireGuichet.validerUtilisateur(codeClient,nip);

            if ( verification == 0){
                if (actionEvent.getSource() == connecterBtn && gestionnaireGuichet.getClient().getCodeClient() != 0
                        && gestionnaireGuichet.getStatut().equals("ouvert")) {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/affichageCompte.fxml")));
                    scene = connecterBtn.getScene();
                    scene.setRoot(root);
                    ((Stage)scene.getWindow()).setTitle("Comptes");
                }else if (gestionnaireGuichet.getStatut().equals("ouvert")){
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/affichageAdmin.fxml")));
                    scene = connecterBtn.getScene();
                    scene.setRoot(root);
                    ((Stage)scene.getWindow()).setTitle("Admin");
                }else{
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vues/guichetFermer.fxml")));
                    scene = connecterBtn.getScene();
                    scene.setRoot(root);
                    ((Stage)scene.getWindow()).setTitle("Guichet Fermé");
                }
            }else if (verification == 2){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Utilisateur ou nip Erronée");
                alert.show();
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Votre compte est bloqué, veuillez contacter l'administrateur");
                alert.show();
            }

            GestionnaireGuichetDAO.save(gestionnaireGuichet);
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Valeur entrée non prise en charge veuillez entrer des nombre entier");
            alert.show();
        }
    }

}