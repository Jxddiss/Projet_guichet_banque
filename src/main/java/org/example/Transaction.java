package org.example;

public class Transaction {
    private int numeroTransaction;
    private double montant;
    private Compte compte;
    private Compte compteTransfert;
    private String type;

    public Transaction(int numeroTransaction, double montant, Compte compte, Compte compteTransfert, String type) {
        this.numeroTransaction = numeroTransaction;
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
