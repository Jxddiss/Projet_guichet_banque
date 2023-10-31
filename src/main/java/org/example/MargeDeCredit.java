package org.example;

class MargeDeCredit extends Compte {
    private final double tauxInteret;
    public MargeDeCredit(int numeroCompte, int codeClient, double tauxInteret) {
        super(numeroCompte);
        this.tauxInteret = tauxInteret;
        super.type = "marge";
    }

    @Override
    public void retirer(double montant) {
        double nvxSolde = getSoldeCompte();
        if (montant > 0 && montant <= nvxSolde) {
            nvxSolde -= montant;
            setSoldeCompte(nvxSolde);
        }
    }
}
