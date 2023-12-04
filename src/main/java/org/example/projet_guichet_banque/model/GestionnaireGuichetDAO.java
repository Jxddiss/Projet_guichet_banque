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
        }catch(ClassNotFoundException cnfe){
            System.out.println("L'objet n'est pas un gestionnaire de guichet "+ cnfe.getMessage());
        }

        return gestionnaireGuichet;
    }

    public static void save(GestionnaireGuichet gestionnaireGuichet){
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("gestionnaire.dat"));
            output.writeObject(gestionnaireGuichet);
            output.close();
        } catch (IOException ioe) {
            System.out.println("===> Erreur lors de la sauvegarde " );
            System.out.println(ioe.getMessage());
        }
    }
}
