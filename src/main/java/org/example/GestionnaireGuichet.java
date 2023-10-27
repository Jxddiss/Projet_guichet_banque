package org.example;

import java.util.ArrayList;

public class GestionnaireGuichet {
    private Banque banque;
    private int codeClient;
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
        Compte compteCourrant =null;
        for (Compte compteCheque:
             this.comptesCheque) {
            if (compteCheque.getCodeClient() == this.codeClient){
                compteCourrant = compteCheque;
            }
        }
        compteCourrant.retirer(montant);
        return compteCourrant.getSoldeCompte();
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

    public void creerCompte(String type){

    }
}
