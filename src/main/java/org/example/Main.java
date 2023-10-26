package org.example;

public class Main {
    public static void main(String[] args) {

        CompteCheque compte1 = new CompteCheque(2985, 1122);
        compte1.afficherSolde();
        compte1.deposer(37824.98);
        compte1.afficherSolde();
    }
}