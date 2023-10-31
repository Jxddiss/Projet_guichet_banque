package org.example;

public class Compte {
    protected final int numeroCompte;
    protected double soldeCompte;
    protected final double retraitMaximum = 1000;
    protected double montantTransfertMaximum;
    protected String type;

    public Compte(int numeroCompte, double montantTransfertMaximum) {
        this.numeroCompte = numeroCompte;
        this.soldeCompte = 0.0;
        this.montantTransfertMaximum = montantTransfertMaximum;
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
