package org.example;

import java.util.ArrayList;

public class GestionnaireGuichet {
    private Banque banque;
    private Client client;
    private ArrayList<Client> clients;
    private ArrayList<Transaction> transactions;

    public GestionnaireGuichet(Banque banque, Client admin) {
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

    public double retraitCheque(int nip, double montant, int numCompte){
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

    public double afficherSoldeCompte(int numCompte){
        for (Compte compte:
             this.client.getComptes()) {
            if (compte.getNumeroCompte() == numCompte){
                return compte.soldeCompte;
            }
        }
        return -1;
    }

    public void creerClient(int codeClient, String prenom, String nom, String telephone, String couriel, int nip){
        this.clients.add(new Client(codeClient, prenom, nom, telephone, couriel, nip));
    }

    public void creerCompte(String type, int numeroCompte, int nip, double retraitMaximum, double montantTransfertMaximum, double montantFactureMaximum, double tauxInteret){
        switch (type){
            case "cheque":
                this.client.ajouterCompte(new CompteCheque(numeroCompte, montantFactureMaximum, nip, retraitMaximum, montantTransfertMaximum));
                break;
            case "epargne":
                this.client.ajouterCompte(new CompteEpargne(numeroCompte, tauxInteret, nip, retraitMaximum, montantTransfertMaximum));
                break;
            case "marge":
                this.client.ajouterCompte(new MargeDeCredit(numeroCompte, tauxInteret, nip, retraitMaximum, montantTransfertMaximum));
                break;
            case "Hypothécaire":
                this.client.ajouterCompte(new CompteHypothecaire(numeroCompte, nip, retraitMaximum, montantTransfertMaximum));
                break;
            default:
                System.out.println("Type de compte non existant");
                break;
        }
    }
}
