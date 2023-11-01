package org.example;

import java.util.ArrayList;

public class GestionnaireGuichet {
    private Banque banque;
    private Client client;
    private ArrayList<Client> clients;
    private ArrayList<Transaction> transactions;
    private int nbEssaie = 0;
    private int numClient = 0;

    /**
     *
     * @param banque
     * */
    public GestionnaireGuichet(Banque banque) {
        this.banque = banque;
        this.clients = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public Client getClient() {
        return client;
    }

    /**
     *
     * @param codeClient
     * @param nip
     * */
    public boolean validerUtilisateur(int codeClient, int nip){

        for (Client client:
                this.clients) {
            if (client.getCodeClient() == codeClient && client.getNip() == nip){
                this.client = client;
                this.nbEssaie = 0;
                return true;
            } else if (client.getCodeClient() == codeClient && client.getNip() != nip) {
                this.nbEssaie += 1;
                if (this.nbEssaie == 3){
                    client.setStatut("Bloque");
                }
                return false;
            }
        }

        return false;
    }

    /**
     *
     * @param montant
     * @param numCompte
     * */
    public double retraitCheque( double montant, int numCompte){
        Compte compteCourrant =null;
        double soldeAvant;

        for (Compte compte:
             client.getComptes()) {
            if (compte.getNumeroCompte() == numCompte && compte.getType().equals("cheque"))
                compteCourrant = compte;
        }
        if (compteCourrant!= null){
            if (montant > compteCourrant.retraitMaximum && montant % 10 != 0){
                return -1;
            }

            soldeAvant = compteCourrant.getSoldeCompte();
            compteCourrant.retirer(montant);

            if (soldeAvant != compteCourrant.getSoldeCompte()){
                this.transactions.add(new Transaction(montant,compteCourrant,this.banque, "cheque"));
                return compteCourrant.getSoldeCompte();
            }else {
                return -1;
            }
        }
        return -1;
    }

    /**
     *
     * @param montant
     * @param numCompte
     * */
    public double retraitEpargne(double montant, int numCompte){
        Compte compteCourrant =null;
        double soldeAvant;
        for (Compte compte:
                client.getComptes()) {
            if (compte.getNumeroCompte() == numCompte && compte.getType().equals("epargne"))
                compteCourrant = compte;
        }
        if (compteCourrant!= null){
            if (montant > compteCourrant.retraitMaximum  && montant % 10 != 0){
                return -1;
            }

            soldeAvant = compteCourrant.getSoldeCompte();
            compteCourrant.retirer(montant);


            if (soldeAvant != compteCourrant.getSoldeCompte()){
                this.transactions.add(new Transaction(montant,compteCourrant,this.banque, "cheque"));
                return compteCourrant.getSoldeCompte();
            }else {
                return -1;
            }
        }
        return -1;
    }

    /**
     *
     * @param montant
     * @param numCompte
     * */
    public double depotCheque(double montant, int numCompte){
        Compte compteCourrant =null;
        for (Compte compte:
                client.getComptes()) {
            if (compte.getNumeroCompte() == numCompte && compte.getType().equals("cheque"))
                compteCourrant = compte;
        }
        if (compteCourrant!= null){
            compteCourrant.deposer(montant);
            this.transactions.add(new Transaction(montant,this.banque,compteCourrant, "cheque"));
            return compteCourrant.getSoldeCompte();
        }
        return -1;
    }

    /**
     *
     * @param montant
     * @param numCompte
     * */
    public double depotEpargne(double montant, int numCompte){
        Compte compteCourrant =null;
        for (Compte compte:
                client.getComptes()) {
            if (compte.getNumeroCompte() == numCompte && compte.getType().equals("epargne"))
                compteCourrant = compte;
        }
        if (compteCourrant!= null){
            compteCourrant.deposer(montant);
            this.transactions.add(new Transaction(montant,this.banque,compteCourrant, "cheque"));
            return compteCourrant.getSoldeCompte();
        }
        return -1;
    }

    /**
     *
     * @param montant
     * @param numCompte
     * */
    public boolean paiementFacture(double montant, int numCompte){
        double soldeAvant;
        CompteCheque compteCourrant =null;
        for (Compte compte:
                client.getComptes()) {
            if (compte.getNumeroCompte() == numCompte && compte.getType().equals("cheque"))
                compteCourrant = (CompteCheque) compte;
        }
        if (compteCourrant!= null){
            soldeAvant = compteCourrant.getSoldeCompte();
            compteCourrant.retirer(montant + compteCourrant.getFraisPaiementFacture());

            if (soldeAvant != compteCourrant.getSoldeCompte()){
                this.transactions.add(new Transaction(montant,compteCourrant,this.banque, "cheque"));
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param numCompteProv
     * @param numCompteDest
     * @param montant
     * */
    public boolean transfertFond(int numCompteProv, int numCompteDest, double montant){
        Compte compteProv = null;
        Compte compteDest = null;
        double soldeAvant;
        for (Compte compte:
             this.client.getComptes()) {
            if (compte.getNumeroCompte() == numCompteProv){
                compteProv = compte;
            }

            if (compte.getNumeroCompte() == numCompteDest){
                compteDest = compte;
            }
        }

        if (compteDest != null && compteProv != null && compteProv.getType().equals("cheque")){
            soldeAvant = compteProv.getSoldeCompte();
            compteProv.retirer(montant);
            compteDest.deposer(montant);
            return soldeAvant != compteProv.getSoldeCompte();
        }
        return false;
    }

    /**
     *
     * @param numCompte
     * */
    public double afficherSoldeCompte(int numCompte){
        for (Compte compte:
             this.client.getComptes()) {
            if (compte.getNumeroCompte() == numCompte){
                return compte.soldeCompte;
            }
        }
        return -1;
    }

    /**
     *
     * @param
     * @param prenom
     * @param nom
     * @param telephone
     * @param couriel
     * @param nip
     * */
    public boolean creerClient(String prenom, String nom, String telephone, String couriel, int nip){
        this.numClient += 1;
        return this.clients.add(new Client(this.numClient, prenom, nom, telephone, couriel, nip));
    }

    /**
     *
     * @param type
     * @param numeroCompte
     * @param montantTransfertMaximum
     * @param montantFactureMaximum
     * @param tauxInteret
     * */
    public void creerCompte(String type, int numeroCompte, double montantTransfertMaximum, double montantFactureMaximum, double tauxInteret){
        switch (type){
            case "cheque":
                this.client.ajouterCompte(new CompteCheque(numeroCompte, this.client.getCodeClient(), montantFactureMaximum, montantTransfertMaximum));
                break;
            case "epargne":
                this.client.ajouterCompte(new CompteEpargne(numeroCompte, this.client.getCodeClient(), tauxInteret, montantTransfertMaximum));
                break;
            case "marge":
                this.client.ajouterCompte(new MargeDeCredit(numeroCompte, this.client.getCodeClient(), tauxInteret, montantTransfertMaximum));
                break;
            case "Hypotheque":
                for (Compte compte:
                     this.client.getComptes()) {
                    if (compte.getType(""));
                }
                this.client.ajouterCompte(new CompteHypothecaire(numeroCompte, this.client.getCodeClient(), montantTransfertMaximum));
                break;
            default:
                System.out.println("Type de compte non existant");
                break;
        }
    }
}
