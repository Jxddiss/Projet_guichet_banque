package org.example;

public class Client {

    private int codeClient;
    private String prenom;
    private String nom;
    private String telephone;
    private String couriel;
    private int nip;

    public Client(int codeClient, String prenom, String nom, String telephone, String couriel, int nip) {
        this.codeClient = codeClient;
        this.prenom = prenom;
        this.nom = nom;
        this.telephone = telephone;
        this.couriel = couriel;
        this.nip = nip;
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

}
