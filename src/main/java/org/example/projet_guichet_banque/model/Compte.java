package org.example.projet_guichet_banque.model;

import java.io.Serializable;

public class Compte implements Serializable{
    // attributs a mettre privé
    protected final int numeroCompte;
    protected int codeClient;
    protected double soldeCompte;
    protected final double retraitMaximum = 1000;
    protected double montantTransfertMaximum;
    protected String type;

    /**
     * Constructeur pour créer un compte
     *
     * @param numeroCompte (int) numéro du compte
     * @param codeClient (codeClient) code du client a qui appartient le compte
     * @param montantTransfertMaximum (double) montant transfert maximum
     * */
    public Compte(int numeroCompte, int codeClient,  double montantTransfertMaximum) {
        this.numeroCompte = numeroCompte;
        this.codeClient = codeClient;
        this.soldeCompte = 0.0;
        this.montantTransfertMaximum = montantTransfertMaximum;
    }

    /**
     * Méthode pour déposer de l'argent sur le compte
     *
     * @param montant
     * */
    public void deposer(double montant) {
        if (montant > 0) {
            soldeCompte += montant;
        }
    }

    /**
     * Méthode pour retire de l'argent sur le compte
     *
     * @param montant
     * */
    public void retirer(double montant) {
        if (montant > 0 && montant <= soldeCompte) {
            soldeCompte -= montant;
        }
    }

    /**
     * Methode pour afficher le solde du compte
     * */
    public void afficherSolde() {
        System.out.println("Solde du compte " + numeroCompte + ": " + soldeCompte);
    }

    // ======== Accesseurs et Mutateurs ===========
    public int getNumeroCompte() {
        return numeroCompte;
    }

    public double getSoldeCompte() {
        return soldeCompte;
    }

    public void setSoldeCompte(double soldeCompte) {
        this.soldeCompte = soldeCompte;
    }

    public String getType() {
        return type;
    }

    public double getRetraitMaximum() {
        return retraitMaximum;
    }

    public double getMontantTransfertMaximum() {
        return montantTransfertMaximum;
    }
}
