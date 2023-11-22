package org.example.projet_guichet_banque.model;

import java.io.File;

public class Setup {
    public static void setup(){
        File f = new File("gestionnaire.dat");

        if (!f.isFile()){
            Banque banque = new Banque(1,10000);
            GestionnaireGuichet gestionnaireGuichet = new GestionnaireGuichet(banque);
            gestionnaireGuichet.validerUtilisateur(0,1111);
            gestionnaireGuichet.creerClient("Marc", "Jacques", "438-676-6483", "marc@test.com", 1222);
            gestionnaireGuichet.creerClient("Michel", "Turcot", "514-997-9089", "michel@test.com", 1222);
            gestionnaireGuichet.creerClient("Bob", "Jean", "514-567-7789", "bob@test.com", 1222);

            GestionnaireGuichetDAO.save(gestionnaireGuichet);
        }
    }
}
