package org.example;

class CompteEpargne extends Compte {
    private double tauxInteret;
    public CompteEpargne(int numeroCompte, double tauxInteret, int nip, double montantTransfertMaximum) {
        super(numeroCompte, nip, montantTransfertMaximum);
        this.tauxInteret = tauxInteret;
        super.type = "epargne";
    }
}
