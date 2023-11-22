package org.example.projet_guichet_banque.model;

import java.io.Serializable;

public class Transaction implements Serializable {
    private int numeroTransaction = 0;
    private double montant;
    private Compte compte;
    private Compte compteTransfert;
    private String type;

    public Transaction( double montant, Compte compte, Compte compteTransfert, String type) {
        this.numeroTransaction += 1;
        this.montant = montant;
        this.compte = compte;
        this.compteTransfert = compteTransfert;
        this.type = type;
    }

    public Transaction(int numeroTransaction, double montant, Compte compte, String type) {
        this.numeroTransaction = numeroTransaction;
        this.montant = montant;
        this.compte = compte;
        this.type = type;
        this.compteTransfert = null;
    }

    public Compte getCompte() {
        return compte;
    }



    public Compte getCompteTransfert() {
        return compteTransfert;
    }


    @Override
    public String toString() {
        String transStr = "";
        transStr = "Transaction{" +
                "numeroTransaction=" + numeroTransaction +
                ", montant=" + montant +
                ", compte=" + compte +
                ", type='" + type + '\'' +
                '}';

        if (this.compteTransfert != null){
            transStr += ", compteTransfert=" + compteTransfert;
        }

        return transStr;
    }


}
