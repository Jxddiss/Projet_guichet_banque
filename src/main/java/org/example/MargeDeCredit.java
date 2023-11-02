package org.example;

class MargeDeCredit extends Compte {
    //===== Attribut =====
    private final double tauxInteret;

    /**
     * Constructeur pour créer un compte de type marge de crédit
     *
     * @param numeroCompte (int) numéro du compte
     * @param codeClient (codeClient) code du client a qui appartient le compte
     * @param tauxInteret (double) taux d'intérêt du compte
     * @param montantTransfertMaximum (double) montant transfert maximum
     * */
    public MargeDeCredit(int numeroCompte,int codeClient, double tauxInteret,  double montantTransfertMaximum) {
        super(numeroCompte,codeClient, montantTransfertMaximum);
        this.tauxInteret = tauxInteret;
        super.type = "marge";
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
