package org.example.projet_guichet_banque.model;

class MargeDeCredit extends Compte {
    //===== Attribut =====
    private final double tauxInteret = 1.05;

    /**
     * Constructeur pour créer un compte de type marge de crédit
     *
     * @param numeroCompte (int) numéro du compte
     * @param codeClient (codeClient) code du client a qui appartient le compte
     * @param montantTransfertMaximum (double) montant transfert maximum
     * */
    public MargeDeCredit(int numeroCompte,int codeClient,  double montantTransfertMaximum) {
        super(numeroCompte,codeClient, montantTransfertMaximum);
        super.type = "marge";
    }

    public double getTauxInteret() {
        return tauxInteret;
    }

    /**
     * Change la logique de la méthode déposer pour
     * diminuer le solde à chaque dépot
     *
     * @param montant (double) montant
     * */
    @Override
    public void deposer(double montant) {
        if ( 0 < this.soldeCompte && montant <= this.soldeCompte) {
            this.soldeCompte -= montant;
        }
    }

    /**
     * Change la logique de la méthode retirer pour
     * augmenter le solde à chaque dépot
     *
     * @param montant (double) montant
     * */
    @Override
    public void retirer(double montant) {
        this.soldeCompte += montant;
    }
}
