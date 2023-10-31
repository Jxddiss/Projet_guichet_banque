package org.example;

import java.util.ArrayList;

public class GestionnaireGuichet {
    private Banque banque;
    private Client client;
    private ArrayList<Client> clients;
    private ArrayList<CompteCheque> comptesCheque;
    private ArrayList<CompteEpargne> comptesEpargne;
    private ArrayList<MargeDeCredit> comptesMarges;
    private ArrayList<CompteHypothecaire> comptesHypotecaire;
    private ArrayList<Transaction> transactions;
    private double soldeCompteCourrant;

    public GestionnaireGuichet(Banque banque) {
        this.banque = banque;
    }

    public boolean validerUtilisateur(String nom, int nip){
        for (Client client:
                this.clients) {
            if (client.getPrenom().equals(nom) && client.getNip() == nip){
                this.client = client;
                return true;
            }
        }
        return false;
    }

    public double retraitCheque(int nip, double montant){
        Compte compteCourrant =null;
        for (Compte compte:
             client.getComptes()) {
            if (compte instanceof CompteCheque)
                compteCourrant = compte;
        }
        if (compteCourrant!= null){
            compteCourrant.retirer(montant);
            return compteCourrant.getSoldeCompte();
        }
        return -1;
    }

    public double retraitEpargne(double montant){
        Compte compteCourrant =null;
        for (Compte compte:
                client.getComptes()) {
            if (compte instanceof CompteEpargne)
                compteCourrant = compte;
        }
        if (compteCourrant!= null){
            compteCourrant.retirer(montant);
            return compteCourrant.getSoldeCompte();
        }
        return -1;
    }

    public double depotCheque(double montant){
        Compte compteCourrant =null;
        for (Compte compte:
                client.getComptes()) {
            if (compte instanceof CompteCheque)
                compteCourrant = compte;
        }
        if (compteCourrant!= null){
            compteCourrant.deposer(montant);
            return compteCourrant.getSoldeCompte();
        }
        return -1;
    }

    public double depotEpargne(double montant){
        Compte compteCourrant =null;
        for (Compte compte:
                client.getComptes()) {
            if (compte instanceof CompteEpargne)
                compteCourrant = compte;
        }
        if (compteCourrant!= null){
            compteCourrant.deposer(montant);
            return compteCourrant.getSoldeCompte();
        }
        return -1;
    }

    public boolean paiementFacture(){
        return false;
    }

    public void transfertFond(){

    }

    public double afficherSoldeCompte(){
        return this.soldeCompteCourrant;
    }

    public void creerClient(){

    }

    public void creerCompte(String type){

    }
}
