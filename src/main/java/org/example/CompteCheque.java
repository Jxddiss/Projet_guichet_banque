package org.example;

class CompteCheque extends Compte {
    private final double fraisPaiementFacture = 1.25;
    private final double montantFactureMaximum;


    public CompteCheque(int numeroCompte, double montantFactureMaximum, int nip, double retraitMaximum, double montantTransfertMaximum) {
        super(numeroCompte, nip, retraitMaximum, montantTransfertMaximum);
        this.montantFactureMaximum = montantFactureMaximum;
        super.type = "cheque";
    }

}
