package org.example.projet_guichet_banque.model;

import java.io.File;

public class Setup {
    public static void setup(){
        File f = new File("gestionnaire.dat");

        if (!f.isFile()){
            Banque banque = new Banque(0,10000);
            GestionnaireGuichet gestionnaireGuichet = new GestionnaireGuichet(banque);
            gestionnaireGuichet.validerUtilisateur(0,"1111");
            gestionnaireGuichet.creerClient("Marc", "Jacques", "438-676-6483", "marc@test.com", "1222");
            gestionnaireGuichet.creerClient("Michel", "Turcot", "514-997-9089", "michel@test.com", "1222");
            gestionnaireGuichet.creerClient("Bob", "Jean", "514-567-7789", "bob@test.com", "1222");

            gestionnaireGuichet.creerCompte("epargne",1,0,0);
            gestionnaireGuichet.creerCompte("cheque",1,2000,3000);
            gestionnaireGuichet.creerCompte("marge",3,0,0);
            gestionnaireGuichet.creerCompte("epargne",2,0,0);
            GestionnaireGuichetDAO.save(gestionnaireGuichet);
        }
    }
}
