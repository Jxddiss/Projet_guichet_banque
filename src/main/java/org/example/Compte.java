package org.example;

public class Compte {
    protected final int numeroCompte;
    protected int numeroNip;
    protected double soldeCompte;
    protected double retraitMaximum;
    protected double montantTransfertMaximum;
    protected String type;

    public Compte(int numeroCompte, int nip, double retraitMaximum, double montantTransfertMaximum) {
        this.numeroCompte = numeroCompte;
        this.soldeCompte = 0.0;
        this.numeroNip = nip;
        this.retraitMaximum = retraitMaximum;
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

    public int getNumeroNip() {
        return numeroNip;
    }

    public double getRetraitMaximum() {
        return retraitMaximum;
    }

    public double getMontantTransfertMaximum() {
        return montantTransfertMaximum;
    }
}
