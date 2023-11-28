package org.example.projet_guichet_banque.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Client implements Serializable {
    // ========== Attributs =========
    private int codeClient;
    private String prenom;
    private String nom;
    private String telephone;
    private String couriel;
    private int nip;
    private ArrayList<Compte> comptes;
    private String statut;

    /**
     * Constructeur pour créer un client
     *
     * @param prenom (String) prénom du client
     * @param nom (String) nom du client
     * @param telephone (String) numéro de téléphone du client
     * @param couriel (String) courriel du client
     * @param nip (int) nip du client
     * */
    public Client(int codeClient, String prenom, String nom, String telephone, String couriel, int nip) {
        this.codeClient = codeClient;
        this.prenom = prenom;
        this.nom = nom;
        this.telephone = telephone;
        this.couriel = couriel;
        this.nip = nip;
        this.comptes = new ArrayList<>();
    }

    // ============ Accesseurs et Mutateurs ============
    public int getCodeClient(){
        return codeClient;
    }

    public String getPrenom(){
        return prenom;
    }

    public int getNip(){
        return nip;
    }

    public ArrayList<Compte> getComptes() {
        return comptes;
    }

    public void ajouterCompte(Compte compte){
        this.comptes.add(compte);
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }


    @Override
    public String toString() {
        return "Client{" +
                "codeClient=" + codeClient +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", telephone='" + telephone + '\'' +
                ", couriel='" + couriel + '\'' +
                ", nip=" + nip +
                ", comptes=" + comptes +
                ", statut='" + statut + '\'' +
                '}';
    }
}
