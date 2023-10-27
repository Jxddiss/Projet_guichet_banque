package org.example;

public class Compte {
    protected final int numeroCompte;
    protected int numeroNip;
    protected final int codeClient;
    protected double soldeCompte;
    protected double retraitMaximum;
    protected double montantTransfertMaximum;

    public Compte(int numeroCompte, int codeClient) {
        this.numeroCompte = numeroCompte;
        this.codeClient = codeClient;
        this.soldeCompte = 0.0;
    }

    public void deposer(double montant) {
        if (montant > 0) {
            soldeCompte += montant;
        }
    }

    public void retirer(double montant) {
        if (montant > 0 && montant <= soldeCompte) {
            soldeCompte -= montant;
        }
    }

    public void afficherSolde() {
        System.out.println("Solde du compte " + numeroCompte + ": " + soldeCompte);
    }

    public int getNumeroCompte() {
        return numeroCompte;
    }

    public int getCodeClient() {
        return codeClient;
    }

    public double getSoldeCompte() {
        return soldeCompte;
    }

    public void setSoldeCompte(double soldeCompte) {
        this.soldeCompte = soldeCompte;
    }
}
