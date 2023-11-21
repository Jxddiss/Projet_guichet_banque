package org.example.projet_guichet_banque.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BanqueDAO {
    public static Banque getAll(){
        Banque banque = null;

        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("banque.dat"));
            banque = (Banque) input.readObject();
            input.close();
        } catch (IOException ioe) {
            System.out.println("==> Erreur lors de l'ouverture du fichier " + ioe.getMessage());
            ;
        }catch(ClassNotFoundException cnfe){
            System.out.println("L,obet n'est pas une liste de compte "+ cnfe.getMessage());
        }

        return banque;
    }

    public static void save(){
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
    }
}
