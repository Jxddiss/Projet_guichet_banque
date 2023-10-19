package org.example;

import java.util.ArrayList;

public class GestionnaireGuichet {
    private Compte banque;
    private ArrayList<Client> clients;
    private ArrayList<CompteCheque> comptesCheque;
    private ArrayList<CompteEpargne> comptesEpargne;
    private ArrayList<MargeDeCredit> comptesMarges;
    private ArrayList<CompteHypothecaire> comptesHypotecaire;
    private ArrayList<Transaction> transactions;
    private double soldeCompteCourrant;

    public GestionnaireGuichet(Compte banque, ArrayList<Client> clients, ArrayList<CompteCheque> comptesCheque, ArrayList<CompteEpargne> comptesEpargne, ArrayList<MargeDeCredit> comptesMarges, ArrayList<CompteHypothecaire> comptesHypotecaire) {
        this.banque = banque;
        this.clients = clients;
        this.comptesCheque = comptesCheque;
        this.comptesEpargne = comptesEpargne;
        this.comptesMarges = comptesMarges;
        this.comptesHypotecaire = comptesHypotecaire;
    }

    public Client validerUtilisateur(){
        return null;
    }

    public void retraitCheque(){

    }

    public void retraitEpargne(){

    }

    public void depotCheque(){

    }

    public void depotEpargne(){

    }

    public void paiementFacture(){

    }

    public void transfertFond(){

    }

    public double afficherSoldeCompte(){
        return this.soldeCompteCourrant;
    }

    public void creerClient(){

    }

    public void creerCompte(){

    }
}
