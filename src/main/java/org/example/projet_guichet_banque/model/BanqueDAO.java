package org.example.projet_guichet_banque.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class BanqueDAO {
    public Banque getAll(){
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

}
