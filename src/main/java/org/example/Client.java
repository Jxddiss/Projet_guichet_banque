package org.example;

import java.util.ArrayList;

public class Client {

    private int codeClient;
    private String prenom;
    private String nom;
    private String telephone;
    private String couriel;
    private int nip;
    private ArrayList<Compte> comptes;

    public Client(int codeClient, String prenom, String nom, String telephone, String couriel, int nip) {
        this.codeClient = codeClient;
        this.prenom = prenom;
        this.nom = nom;
        this.telephone = telephone;
        this.couriel = couriel;
        this.nip = nip;
        this.comptes = new ArrayList<>();
    }

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

    public void ajouterCompte(){

    }
}
