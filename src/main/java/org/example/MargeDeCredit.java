package org.example;

class MargeDeCredit extends Compte {
    public MargeDeCredit(String numeroCompte, String codeClient) {
        super(numeroCompte, codeClient);
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
