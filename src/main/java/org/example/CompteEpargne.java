package org.example;

class CompteEpargne extends Compte {
    //=========== Attribut ===========
    private double tauxInteret;

    /**
     * Constructeur pour créer un compte de épargne
     *
     * @param numeroCompte (int) numéro du compte
     * @param codeClient (codeClient) code du client a qui appartient le compte
     * @param tauxInteret (double) taux d'intérêt du compte
     * @param montantTransfertMaximum (double) montant transfert maximum
     * */
    public CompteEpargne(int numeroCompte,int codeClient, double tauxInteret, double montantTransfertMaximum) {
        super(numeroCompte,codeClient, montantTransfertMaximum);
        this.tauxInteret = tauxInteret;
        super.type = "epargne";
    }
}
