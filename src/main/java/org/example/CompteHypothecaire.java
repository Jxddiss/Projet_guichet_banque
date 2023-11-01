package org.example;

class CompteHypothecaire extends Compte {

    public CompteHypothecaire(int numeroCompte,int codeClient, double montantTransfertMaximum) {
        super(numeroCompte,codeClient,  montantTransfertMaximum);
        this.type = "hypotheque";
    }
}
