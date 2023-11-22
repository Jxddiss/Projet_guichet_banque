package org.example.projet_guichet_banque.model;

import java.io.*;

public class GestionnaireGuichetDAO {
    public static GestionnaireGuichet get(){
        GestionnaireGuichet gestionnaireGuichet = null;

        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("gestionnaire.dat"));
            gestionnaireGuichet = (GestionnaireGuichet) input.readObject();
            input.close();
        } catch (IOException ioe) {
            System.out.println("==> Erreur lors de l'ouverture du fichier " + ioe.getMessage());
            ;
        }catch(ClassNotFoundException cnfe){
            System.out.println("L,obet n'est pas une liste de compte "+ cnfe.getMessage());
        }

        return gestionnaireGuichet;
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
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("gestionnaire.dat"));
            output.writeObject(gestionnaireGuichet);
            output.close();
        } catch (IOException ioe) {
            System.out.println("===> Erreur lors de la sauvegarde " );
            ioe.printStackTrace();
        }
    }
}
