package org.example.projet_guichet_banque.model;

class CompteEpargne extends Compte {
    //=========== Attribut ===========
    private final double tauxInteret = 1.1;

    /**
     * Constructeur pour créer un compte de épargne
     *
     * @param numeroCompte (int) numéro du compte
     * @param codeClient (codeClient) code du client a qui appartient le compte
     * @param montantTransfertMaximum (double) montant transfert maximum
     * */
    public CompteEpargne(int numeroCompte,int codeClient, double montantTransfertMaximum) {
        super(numeroCompte,codeClient, montantTransfertMaximum);
        super.type = "epargne";
    }

    public double getTauxInteret() {
        return tauxInteret;
    }
}
