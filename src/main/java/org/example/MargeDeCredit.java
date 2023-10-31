package org.example;

class MargeDeCredit extends Compte {
    private final double tauxInteret;
    public MargeDeCredit(int numeroCompte, double tauxInteret, int nip,  double montantTransfertMaximum) {
        super(numeroCompte, nip, montantTransfertMaximum);
        this.tauxInteret = tauxInteret;
        super.type = "marge";
    }

    @Override
    public void deposer(double montant) {
        if ( 0 < this.soldeCompte && montant <= this.soldeCompte) {
            this.soldeCompte -= montant;
        }
    }

    @Override
    public void retirer(double montant) {
        this.soldeCompte += montant;
    }
}
