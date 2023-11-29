package org.example.projet_guichet_banque.model;

import java.io.Serializable;

public class Transaction implements Serializable {
    private int numeroTransaction = 0;
    private double montant;
    private int compte;
    private int compteTransfert;
    private String type;

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setCompteTransfert(int compteTransfert) {
        this.compteTransfert = compteTransfert;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Transaction(double montant, int compte, int compteTransfert, String type) {
        this.numeroTransaction += 1;
        this.montant = montant;
        this.compte = compte;
        this.compteTransfert = compteTransfert;
        this.type = type;
    }

    public Transaction(int numeroTransaction, double montant, int compte, String type) {
        this.numeroTransaction = numeroTransaction;
        this.montant = montant;
        this.compte = compte;
        this.type = type;
        this.compteTransfert = 0;
    }

    public int getCompte() {
        return compte;
    }



    public int getCompteTransfert() {
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

        if (this.compteTransfert != 0){
            transStr += ", compteTransfert=" + compteTransfert;
        }

        return transStr;
    }


}
