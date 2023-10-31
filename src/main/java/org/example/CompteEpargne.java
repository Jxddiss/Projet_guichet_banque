package org.example;

class CompteEpargne extends Compte {
    private double tauxInteret;
    public CompteEpargne(int numeroCompte, double tauxInteret, double montantTransfertMaximum) {
        super(numeroCompte, montantTransfertMaximum);
        this.tauxInteret = tauxInteret;
        super.type = "epargne";
    }
}
