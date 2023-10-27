package org.example;

public class Banque extends Compte{
    private final double montantMaximum = 20000;
    private final double montantRemplissage;

    public Banque(int numeroCompte, int codeClient, double montantRemplissage) {
        super(numeroCompte);
        this.montantRemplissage = montantRemplissage;
    }

    public void remplirGuichet(){
        if (this.montantMaximum > this.soldeCompte){
            this.deposer(montantRemplissage);
        }
    }
}
