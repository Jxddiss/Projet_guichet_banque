package org.example;

public class Compte {
    private final String numeroCompte;
    private final String codeClient;
    private double solde;

    public Compte(String numeroCompte, String codeClient) {
        this.numeroCompte = numeroCompte;
        this.codeClient = codeClient;
        this.solde = 0.0;
    }

    public void deposer(double montant) {
        if (montant > 0) {
            solde += montant;
        }
    }

    public void retirer(double montant) {
        if (montant > 0 && montant <= solde) {
            solde -= montant;
        }
    }

    public void afficherSolde() {
        System.out.println("Solde du compte " + numeroCompte + ": " + solde);
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public String getCodeClient() {
        return codeClient;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }
}
