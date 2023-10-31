package org.example;

import java.util.ArrayList;

public class GestionnaireGuichet {
    private Banque banque;
    private Client client;
    private ArrayList<Client> clients;
    private ArrayList<Transaction> transactions;

    /**
     *
     * @param banque
     * @param admin
     * */
    public GestionnaireGuichet(Banque banque, Client admin) {
        this.banque = banque;
    }

    /**
     *
     * @param nom
     * @param nip
     * */
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
            if (montant > compteCourrant.retraitMaximum){
                return -1;
            }

            soldeAvant = compteCourrant.getSoldeCompte();
            compteCourrant.retirer(montant);

            if (soldeAvant != compteCourrant.getSoldeCompte()){
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
            if (montant > compteCourrant.retraitMaximum){
                return -1;
            }

            soldeAvant = compteCourrant.getSoldeCompte();
            compteCourrant.retirer(montant);

            if (soldeAvant != compteCourrant.getSoldeCompte()){
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

            return soldeAvant != compteCourrant.getSoldeCompte();
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
        for (Compte compte:
             this.client.getComptes()) {
            if (compte.getNumeroCompte() == numCompteProv){
                compteProv = compte;
            }

            if (compte.getNumeroCompte() == numCompteDest){
                compteDest = compte;
            }
        }

        if (compteDest != null && compteProv != null){
            compteProv.retirer(montant);
            compteDest.deposer(montant);
            return true;
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
     * @param codeClient
     * @param prenom
     * @param nom
     * @param telephone
     * @param couriel
     * @param nip
     * */
    public void creerClient(int codeClient, String prenom, String nom, String telephone, String couriel, int nip){
        this.clients.add(new Client(codeClient, prenom, nom, telephone, couriel, nip));
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
                this.client.ajouterCompte(new CompteCheque(numeroCompte, montantFactureMaximum, montantTransfertMaximum));
                break;
            case "epargne":
                this.client.ajouterCompte(new CompteEpargne(numeroCompte, tauxInteret, montantTransfertMaximum));
                break;
            case "marge":
                this.client.ajouterCompte(new MargeDeCredit(numeroCompte, tauxInteret, montantTransfertMaximum));
                break;
            case "Hypothécaire":
                this.client.ajouterCompte(new CompteHypothecaire(numeroCompte, montantTransfertMaximum));
                break;
            default:
                System.out.println("Type de compte non existant");
                break;
        }
    }
}
