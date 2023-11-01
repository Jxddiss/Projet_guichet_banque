package org.example;

public class Banque extends Compte{
    private final double montantMaximum = 20000;
    private final double montantRemplissage;

    public Banque(int numeroCompte, double montantRemplissage, double montantTransfertMaximum) {
        super(numeroCompte,0, montantTransfertMaximum);
        this.montantRemplissage = montantRemplissage;
        super.type = "banque";
    }

    public void remplirGuichet(){
        if (this.montantMaximum > this.soldeCompte){
            this.deposer(montantRemplissage);
        }
    }
}
