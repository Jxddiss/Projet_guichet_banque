package org.example;

class CompteHypothecaire extends Compte {

    public CompteHypothecaire(int numeroCompte, double montantTransfertMaximum) {
        super(numeroCompte,  montantTransfertMaximum);
        this.type = "hypotheque";
    }
}
