package org.example.projet_guichet_banque.model;

public class Demande {
    public static int compteurNumeroDemande = 0;
    private int numeroDemande;
    private int codeClient;
    private String typeCompte;
    private String statut;

    public Demande(int codeClient, String typeCompte, String statut) {
        compteurNumeroDemande += 1;
        this.numeroDemande = compteurNumeroDemande;
        this.codeClient = codeClient;
        this.typeCompte = typeCompte;
        this.statut = statut;
    }

    public int getNumeroDemande() {
        return numeroDemande;
    }

    public int getCodeClient() {
        return codeClient;
    }

    public String getTypeCompte() {
        return typeCompte;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Demande{" +
                "codeClient=" + codeClient +
                ", typeCompte='" + typeCompte + '\'' +
                ", statut='" + statut + '\'' +
                '}';
    }
}
