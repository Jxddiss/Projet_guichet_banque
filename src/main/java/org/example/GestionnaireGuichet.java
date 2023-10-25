package org.example;

import java.util.ArrayList;

public class GestionnaireGuichet {
    private Compte banque;
    private int codeClient;
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

    public Client validerUtilisateur(String nom, int nip){
        for (Client client:
                this.clients) {
            if (client.getPrenom().equals(nom) && client.getNip() == nip){
                this.codeClient = client.getCodeClient();
                return client;
            }
        }
        return null;
    }

    public double retraitCheque(int nip, double montant){
        CompteCheque compteCourrant;
        for (Compte compteCheque:
             this.comptesCheque) {
            if (compteCheque.getCodeClient() == this.codeClient){
                compteCourrant = compteCheque;
            }

        }
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
