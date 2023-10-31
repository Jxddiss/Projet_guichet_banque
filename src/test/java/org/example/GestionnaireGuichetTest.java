package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestionnaireGuichetTest {

    private GestionnaireGuichet gestionnaireGuichet;
    private Banque banque;

    @BeforeEach
    void setUp() {
        banque = new Banque(1,10000,100000);
        gestionnaireGuichet = new GestionnaireGuichet(banque);
    }

    @AfterEach
    void tearDown() {
        banque = null;
        gestionnaireGuichet = null;
    }

    @Test
    void validerUtilisateur() {
    }

    @Test
    void retraitCheque() {
    }

    @Test
    void retraitEpargne() {
    }

    @Test
    void depotCheque() {
    }

    @Test
    void depotEpargne() {
    }

    @Test
    void paiementFacture() {
    }

    @Test
    void transfertFond() {
    }

    @Test
    void afficherSoldeCompte() {
    }

    @Test
    void creerClient() {
    }

    @Test
    void creerCompte() {
    }
}