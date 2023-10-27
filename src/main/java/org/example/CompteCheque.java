package org.example;

class CompteCheque extends Compte {
    private final double fraisPaiementFacture = 1.25;
    private final double montantFactureMaximum;

    public CompteCheque(int numeroCompte, double montantFactureMaximum) {
        super(numeroCompte);
        this.montantFactureMaximum = montantFactureMaximum;
    }

}
