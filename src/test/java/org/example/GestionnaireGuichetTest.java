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
        gestionnaireGuichet.creerClient(150,"Marc", "Jacques", "438-676-6483", "marc@test.com", 1222);
        gestionnaireGuichet.creerClient(150,"Michel", "Turcot", "514-997-9089", "michel@test.com", 1222);
        gestionnaireGuichet.creerClient(150,"Bob", "Jean", "514-567-7789", "bob@test.com", 1222);


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

    @ParameterizedTest
    @MethodSource("compteClients")
    void creerClient(int n1, String n2, String n3, String n4, String n5, int n6) {
        gestionnaireGuichet.creerClient(n1, n2, n3, n4, n5, n6);

    }

    @Test
    void creerCompte() {
    }

    static Stream<Arguments> compteClients(){
        return Stream.of(
                Arguments.of(150,"Marc", "Jacques", "438-676-6483", "marc@test.com", 1222),
                Arguments.of(150,"Michel", "Turcot", "514-997-9089", "michel@test.com", 1222),
                Arguments.of(150,"Bob", "Jean", "514-567-7789", "bob@test.com", 1222)
        );
    }
}