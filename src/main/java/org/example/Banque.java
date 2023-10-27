package org.example;

public class Banque extends Compte{
    private final double montantMaximum;
    private final double montantRemplissage;

    public Banque(int numeroCompte, int codeClient, double montantMaximum, double montantRemplissage) {
        super(numeroCompte, codeClient);
        this.montantMaximum = montantMaximum;
        this.montantRemplissage = montantRemplissage;
    }

    public void remplirGuichet(){
        if (this.montantMaximum > this.soldeCompte){
            this.deposer(montantRemplissage);
        }
    }
}
