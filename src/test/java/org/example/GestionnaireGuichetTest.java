package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GestionnaireGuichetTest {

    private GestionnaireGuichet gestionnaireGuichet;
    private Banque banque;

    @BeforeEach
    void setUp() {
        banque = new Banque(1,10000,100000);
        gestionnaireGuichet = new GestionnaireGuichet(banque);
        gestionnaireGuichet.creerClient(101,"Marc", "Jacques", "438-676-6483", "marc@test.com", 1222);
        gestionnaireGuichet.creerClient(102,"Michel", "Turcot", "514-997-9089", "michel@test.com", 1222);
        gestionnaireGuichet.creerClient(103,"Bob", "Jean", "514-567-7789", "bob@test.com", 1222);

    }

    @AfterEach
    void tearDown() {
        banque = null;
        gestionnaireGuichet = null;
    }

    @Test
    void validerUtilisateur() {
        assertTrue(gestionnaireGuichet.validerUtilisateur(102,1222));
    }

    @Test
    void retraitCheque() {
        gestionnaireGuichet.validerUtilisateur(101, 1222);
        gestionnaireGuichet.creerCompte("cheque",1,1000,1000,0.003);
        gestionnaireGuichet.depotCheque(1000, 1);

        assertEquals(500, gestionnaireGuichet.retraitCheque(500, 1));
    }

    @Test
    void retraitEpargne() {
        gestionnaireGuichet.validerUtilisateur(101, 1222);
        gestionnaireGuichet.creerCompte("cheque",1,1000,1000,0.003);
        gestionnaireGuichet.creerCompte("epargne",2,1000,1000,0.03);
        gestionnaireGuichet.depotEpargne(1000, 2);

        assertEquals(500, gestionnaireGuichet.retraitEpargne(500, 2));
    }

    @Test
    void depotCheque() {
        gestionnaireGuichet.validerUtilisateur(101, 1222);
        gestionnaireGuichet.creerCompte("cheque",1,1000,1000,0.003);
        gestionnaireGuichet.depotCheque(1000, 1);

        assertEquals(1500,gestionnaireGuichet.depotCheque(500, 1));
    }

    @Test
    void depotEpargne() {
        gestionnaireGuichet.validerUtilisateur(101, 1222);
        gestionnaireGuichet.creerCompte("cheque",1,1000,1000,0.003);
        gestionnaireGuichet.creerCompte("epargne",2,1000,1000,0.03);
        gestionnaireGuichet.depotEpargne(1000, 2);

        assertEquals(1500,gestionnaireGuichet.depotEpargne(500, 2));
    }

    @Test
    void paiementFacture() {
        gestionnaireGuichet.validerUtilisateur(101, 1222);
        gestionnaireGuichet.creerCompte("cheque",1,1000,1000,0.003);
        gestionnaireGuichet.creerCompte("epargne",2,1000,1000,0.03);
        gestionnaireGuichet.creerCompte("marge",3,10000,2000,0);
        gestionnaireGuichet.creerCompte("hypotheque",1,1000,1000,0.3);
    }

    @Test
    void transfertFond() {
        gestionnaireGuichet.validerUtilisateur(101, 1222);
        gestionnaireGuichet.creerCompte("cheque",1,1000,1000,0.003);
        gestionnaireGuichet.creerCompte("epargne",2,1000,1000,0.03);
        gestionnaireGuichet.creerCompte("marge",3,10000,2000,0);
        gestionnaireGuichet.creerCompte("hypotheque",1,1000,1000,0.3);
    }

    @Test
    void afficherSoldeCompte() {
        gestionnaireGuichet.validerUtilisateur(101, 1222);
        gestionnaireGuichet.creerCompte("cheque",1,1000,1000,0.003);

    }

    @ParameterizedTest
    @MethodSource("compteClients")
    void creerClient(int n1, String n2, String n3, String n4, String n5, int n6) {
        assertTrue(gestionnaireGuichet.creerClient(n1, n2, n3, n4, n5, n6));
    }

    @Test
    void creerCompte() {
    }

    static Stream<Arguments> compteClients(){
        return Stream.of(
                Arguments.of(101,"Marc", "Jacques", "438-676-6483", "marc@test.com", 1222),
                Arguments.of(102,"Michel", "Turcot", "514-997-9089", "michel@test.com", 1222),
                Arguments.of(103,"Bob", "Jean", "514-567-7789", "bob@test.com", 1222)
        );
    }
}