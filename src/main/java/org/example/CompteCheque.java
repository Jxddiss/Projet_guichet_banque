package org.example;

class CompteCheque extends Compte {
    private final double fraisPaiementFacture = 1.25;
    private final double montantFactureMaximum;


    public CompteCheque(int numeroCompte, double montantFactureMaximum,   double montantTransfertMaximum) {
        super(numeroCompte, montantTransfertMaximum);
        this.montantFactureMaximum = montantFactureMaximum;
        super.type = "cheque";
    }

    public double getFraisPaiementFacture() {
        return fraisPaiementFacture;
    }

    public double getMontantFactureMaximum() {
        return montantFactureMaximum;
    }
}
