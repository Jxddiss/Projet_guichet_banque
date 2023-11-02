package org.example;

public class Banque extends Compte{
    // ======= Attributs ==========
    private final double montantMaximum = 20000;
    private final double montantRemplissage;

    /**
     * Constructeur pour la classe Banque, crée un objet de type banque qui
     * représente le guichet
     *
     * @param numeroCompte (int) : numéro de compte
     * @param montantRemplissage (double) montant pour le remplissage du guichet
     * */
    public Banque(int numeroCompte, double montantRemplissage) {
        super(numeroCompte,0, 0);
        this.montantRemplissage = montantRemplissage;
        super.type = "banque";
        this.soldeCompte = 20000;
    }

    /**
     * Méthode qui permet de remplir le guichet
     * */
    public void remplirGuichet(){
        if (this.montantMaximum > this.soldeCompte){
            this.deposer(montantRemplissage);
        }
    }
}
