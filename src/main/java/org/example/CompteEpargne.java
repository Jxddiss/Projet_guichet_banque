package org.example;

class CompteEpargne extends Compte {
    private double tauxInteret;
    public CompteEpargne(int numeroCompte, int codeClient, double tauxInteret) {
        super(numeroCompte);
        this.tauxInteret = tauxInteret;
    }
}
