package org.example;

class CompteCheque extends Compte {
    private final double fraisPaiementFacture = 1.25;
    private final double montantFactureMaximum;

    public CompteCheque(int numeroCompte, int codeClient, double montantFactureMaximum) {
        super(numeroCompte, codeClient);
        this.montantFactureMaximum = montantFactureMaximum;
    }

}
