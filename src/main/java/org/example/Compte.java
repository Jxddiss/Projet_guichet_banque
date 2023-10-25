package org.example;

public class Compte {
    private final int numeroCompte;
    private int numeroNip;
    private final String codeClient;
    private double soldeCompte;
    private double retraitMaximum;
    private double montantTransfertMaximum;

    public Compte(int numeroCompte, String codeClient) {
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

    public String getCodeClient() {
        return codeClient;
    }

    public double getSoldeCompte() {
        return soldeCompte;
    }

    public void setSoldeCompte(double soldeCompte) {
        this.soldeCompte = soldeCompte;
    }
}
