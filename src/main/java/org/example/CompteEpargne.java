package org.example;

class CompteEpargne extends Compte {
    private double tauxInteret;
    public CompteEpargne(int numeroCompte, int codeClient, double tauxInteret, int nip, double retraitMaximum, double montantTransfertMaximum) {
        super(numeroCompte, nip, retraitMaximum, montantTransfertMaximum);
        this.tauxInteret = tauxInteret;
        super.type = "epargne";
    }
}
