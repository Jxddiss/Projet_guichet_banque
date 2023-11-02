package org.example;

class CompteCheque extends Compte {
    //========== Attributs ===========
    private final double fraisPaiementFacture = 1.25;
    private final double montantFactureMaximum;

    /**
     * Constructeur pour créer un compte de chèque
     *
     * @param numeroCompte (int) numéro du compte
     * @param codeClient (codeClient) code du client a qui appartient le compte
     * @param montantTransfertMaximum (double) montant transfert maximum
     * */
    public CompteCheque(int numeroCompte,int codeClient, double montantFactureMaximum,   double montantTransfertMaximum) {
        super(numeroCompte,codeClient, montantTransfertMaximum);
        this.montantFactureMaximum = montantFactureMaximum;
        super.type = "cheque";
    }

    // ========= Accesseurs =========
    public double getFraisPaiementFacture() {
        return fraisPaiementFacture;
    }

    public double getMontantFactureMaximum() {
        return montantFactureMaximum;
    }
}
