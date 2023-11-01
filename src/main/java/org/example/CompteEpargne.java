package org.example;

class CompteEpargne extends Compte {
    private double tauxInteret;
    public CompteEpargne(int numeroCompte,int codeClient, double tauxInteret, double montantTransfertMaximum) {
        super(numeroCompte,codeClient, montantTransfertMaximum);
        this.tauxInteret = tauxInteret;
        super.type = "epargne";
    }
}
