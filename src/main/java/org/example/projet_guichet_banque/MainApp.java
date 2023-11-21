package org.example.projet_guichet_banque;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.projet_guichet_banque.model.Banque;
import org.example.projet_guichet_banque.model.Client;
import org.example.projet_guichet_banque.model.GestionnaireGuichet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/vues/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Banque banque = new Banque(1,10000);
        GestionnaireGuichet gestionnaireGuichet = new GestionnaireGuichet(banque);
        gestionnaireGuichet.validerUtilisateur(0,1111);
        gestionnaireGuichet.creerClient("Marc", "Jacques", "438-676-6483", "marc@test.com", 1222);
        gestionnaireGuichet.creerClient("Michel", "Turcot", "514-997-9089", "michel@test.com", 1222);
        gestionnaireGuichet.creerClient("Bob", "Jean", "514-567-7789", "bob@test.com", 1222);

        ;
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("banque.dat"));
            output.writeObject(banque);
            output.close();
        } catch (IOException ioe) {
            System.out.println("===> Erreur lors de la sauvegarde " );
            ioe.printStackTrace();
        }

        launch();
    }
}
