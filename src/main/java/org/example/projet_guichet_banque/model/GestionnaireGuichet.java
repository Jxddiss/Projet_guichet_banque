package org.example.projet_guichet_banque.model;

import java.io.Serializable;
import java.util.ArrayList;

// Paiement intérêt et prélever montant a faire dans les classes ToString dans toutes les classes
public class GestionnaireGuichet implements Serializable {
    //========== Attributs ========
    private final Banque banque;
    private Client client;
    private Client admin;
    private ArrayList<Demande> demandesComptes;
    private ArrayList<Client> clients;
    private ArrayList<Transaction> transactions;
    private int nbEssaie = 0;
    private int numClient = 0;
    private int numCompte = 0;
    private String statut = "ouvert";

    /**
     * Constructeur qui initialise le programme de gestion du guichet
     * Prend comme argument un guichet (banque) et initialise la liste de client
     * et de transactions
     *
     * @param banque
     * */
    public GestionnaireGuichet(Banque banque) {
        this.banque = banque;
        this.clients = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.admin = new Client(0, "admin", "admin", "111-111-111", "admin@tech.com",1111);
        this.clients.add(this.admin);
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client){
        this.client = client;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getStatut() {
        return statut;
    }

    public ArrayList<Transaction> getTransactions(int numCompte){
        ArrayList<Transaction> transactionsCompte = new ArrayList<>();
        for (Transaction transaction:
             this.transactions) {
            if (transaction.getCompte() == numCompte || transaction.getCompteTransfert() == numCompte){
                transactionsCompte.add(transaction);
            }
        }
        
        return transactionsCompte;
    }

    public ArrayList<Transaction> getTransactionsAdmin(){
        if (this.client != this.admin){
            return null;
        }

        return this.transactions;
    }

    public ArrayList<Demande> getDemandesComptes() {
        return demandesComptes;
    }

    public void envoyerDemande(Demande demande){
        this.demandesComptes.add(demande);
    }

    /**
     * Méthode qui permet à l'admin
     * de retrouver un client seulement avec son codeClient
     * dans la liste de client
     *
     * @param codeClient (int) code du client recherché
     * */
    public Client getClientAvecCode(int codeClient){
        if (this.client != this.admin){
            return null;
        }

        for (Client client:
             this.clients) {
            if (client.getCodeClient() == codeClient){
                return client;
            }
        }
        return null;
    }

    /**
     * Méthode pour valider l'utilisateur du guichet
     * avec son codeClient et son nip
     *
     * @param codeClient (int) code du client
     * @param nip (int) nip du client
     * */
    public int validerUtilisateur(int codeClient, int nip){
        this.client = null;
        for (Client client:
                this.clients) {
            if (client.getCodeClient() == codeClient && client.getStatut().equals("Bloque")){
                return 1;
            }

            if (client.getCodeClient() == codeClient && client.getNip() == nip){
                this.client = client;
                this.nbEssaie = 0;
                return 0;
            } else if (client.getCodeClient() == codeClient && client.getNip() != nip) {
                if (this.nbEssaie == 3){
                    client.setStatut("Bloque");
                    this.nbEssaie = 0;
                    return 1;
                }
                this.nbEssaie += 1;
                return 2;
            }
        }
        return 2;
    }

    /**
     * Méthode pour retirer de l'argent d'un compte cheque identifier par le numero
     * de compte, confirme la presence du compte cheque dans la liste de compte du client
     * verifie si le montant demandé est au dessus du retrait maximum et est
     * un multiple de 10
     *
     * @param montant (double)
     * @param numCompte (int) numéro du compte ou retirer l'argent
     *
     * @return double nouveau solde du compte ou -1 si la transactin n'est pas effectué
     * */
    public double retraitCheque( double montant, int numCompte){
        Compte compteCourrant =null;
        double soldeAvant;

        for (Compte compte:
             client.getComptes()) {
            if (compte.getNumeroCompte() == numCompte && compte.getType().equals("cheque"))
                compteCourrant = compte;
        }
        if (compteCourrant!= null){
            if ((montant > compteCourrant.retraitMaximum && montant % 10 != 0) || this.banque.getSoldeCompte() < montant ){
                return -1;
            }

            soldeAvant = compteCourrant.getSoldeCompte();
            compteCourrant.retirer(montant);

            if (soldeAvant != compteCourrant.getSoldeCompte()){
                this.banque.retirer(montant);
                this.transactions.add(new Transaction(montant,compteCourrant.getNumeroCompte(),this.banque.getNumeroCompte(), "Retrait chèque"));
                return compteCourrant.getSoldeCompte();
            }else {
                return -1;
            }
        }
        return -1;
    }

    /**
     * Méthode pour retirer de l'argent d'un compte epargne identifier par le numero
     * de compte, confirme la presence du compte epargne dans la liste de compte du client
     * verifie si le montant demandé est au dessus du retrait maximum et est
     * un multiple de 10 et crée une nouvelle transaction
     *
     * @param montant (double)
     * @param numCompte (int) numéro du compte ou retirer l'argent
     *
     * @return double nouveau solde du compte ou -1 si la transactin n'est pas effectué
     * */
    public double retraitEpargne(double montant, int numCompte){
        Compte compteCourrant =null;
        double soldeAvant;
        for (Compte compte:
                client.getComptes()) {
            if (compte.getNumeroCompte() == numCompte && compte.getType().equals("epargne"))
                compteCourrant = compte;
        }
        if (compteCourrant!= null){
            if ((montant > compteCourrant.retraitMaximum && montant % 10 != 0) || this.banque.getSoldeCompte() < montant){
                return -1;
            }

            soldeAvant = compteCourrant.getSoldeCompte();
            compteCourrant.retirer(montant);


            if (soldeAvant != compteCourrant.getSoldeCompte()){
                this.banque.retirer(montant);
                this.transactions.add(new Transaction(montant,compteCourrant.getNumeroCompte(),this.banque.getNumeroCompte(), "Retrait épargne"));
                return compteCourrant.getSoldeCompte();
            }else {
                return -1;
            }
        }
        return -1;
    }

    /**
     * Méthode pour déposer de l'argent dans un compte cheque identifier par le numero
     * de compte, confirme la presence du compte cheque dans la liste de compte du client
     * et crée une nouvelle transaction
     *
     * @param montant (double)
     * @param numCompte (int) numéro du compte ou retirer l'argent
     *
     * @return double nouveau solde du compte ou -1 si la transactin n'est pas effectué
     * */
    public double depotCheque(double montant, int numCompte){
        Compte compteCourrant =null;
        for (Compte compte:
                client.getComptes()) {
            if (compte.getNumeroCompte() == numCompte && compte.getType().equals("cheque"))
                compteCourrant = compte;
        }
        if (compteCourrant!= null){
            compteCourrant.deposer(montant);
            this.transactions.add(new Transaction(montant,this.banque.getNumeroCompte(),compteCourrant.getNumeroCompte(), "Dépot chèque"));
            return compteCourrant.getSoldeCompte();
        }
        return -1;
    }

    /**
     * Méthode pour déposer de l'argent dans un compte épargne identifier par le numero
     * de compte, confirme la presence du compte épargne dans la liste de compte du client
     * et crée une nouvelle transaction
     *
     * @param montant (double)
     * @param numCompte (int) numéro du compte ou retirer l'argent
     *
     * @return double nouveau solde du compte ou -1 si la transactin n'est pas effectué
     * */
    public double depotEpargne(double montant, int numCompte){
        Compte compteCourrant =null;
        for (Compte compte:
                client.getComptes()) {
            if (compte.getNumeroCompte() == numCompte && compte.getType().equals("epargne"))
                compteCourrant = compte;
        }
        if (compteCourrant!= null){
            compteCourrant.deposer(montant);
            this.transactions.add(new Transaction(montant,this.banque.getNumeroCompte(),compteCourrant.getNumeroCompte(), "Dépot épargne"));
            return compteCourrant.getSoldeCompte();
        }
        return -1;
    }

    /**
     * Méthode pour payer une facture, retire l'argent du compte et applique les frais de
     * paiement de facture, ajoute une transaction, verifie aussi que le compte est un compte
     * chèque et que le montant ne dépasse pas la limite de facture
     *
     * @param montant (double) montant de la facture
     * @param numCompte (int) numero du compte
     *
     * @return boolean Confirme si le paiement à été fait
     * */
    public boolean paiementFacture(double montant, int numCompte){
        double soldeAvant;
        CompteCheque compteCourrant =null;
        for (Compte compte:
                client.getComptes()) {
            if (compte.getNumeroCompte() == numCompte && compte.getType().equals("cheque"))
                compteCourrant = (CompteCheque) compte;
        }
        if (compteCourrant!= null && montant < compteCourrant.getMontantFactureMaximum()){
            soldeAvant = compteCourrant.getSoldeCompte();
            compteCourrant.retirer(montant + compteCourrant.getFraisPaiementFacture());

            if (soldeAvant != compteCourrant.getSoldeCompte()){
                this.transactions.add(new Transaction(montant,compteCourrant.getNumeroCompte(),this.banque.getNumeroCompte(), "Paiement de facture"));
                return true;
            }
        }
        return false;
    }

    /**
     * Méthode pour transférer de l'argent entre deux comptes et vérifie que
     * le compte chèque est un compte chèque que le montant ne dépasse pas
     * la limite de facture
     *
     * @param numCompteProv (int) numéro du compte de provenance
     * @param numCompteDest (int) numéro du compte de destination
     * @param montant (double) montant du transfert
     *
     * @return boolean comfirme si le transfert à été effectué
     * */
    public boolean transfertFond(int numCompteProv, int numCompteDest, double montant){
        Compte compteProv = null;
        Compte compteDest = null;
        double soldeAvant;

        for (Compte compte:
             this.client.getComptes()) {
            if (compte.getNumeroCompte() == numCompteProv){
                compteProv = compte;
            }

            if (compte.getNumeroCompte() == numCompteDest){
                compteDest = compte;
            }
        }

        if (compteDest != null && compteProv != null && compteProv.getType().equals("cheque") && montant < compteProv.getMontantTransfertMaximum()){
            soldeAvant = compteProv.getSoldeCompte();
            compteProv.retirer(montant);
            compteDest.deposer(montant);
            if (soldeAvant != compteProv.getSoldeCompte()){
                this.transactions.add(new Transaction(montant,compteProv.getNumeroCompte(),compteDest.getNumeroCompte(), "Transfert de fond"));
                return true;
            }
        }
        return false;
    }

    /**
     * Méthode pour afficher le solde d'un compte identifié par le numéro de
     * compte
     *
     * @param numCompte (int) numéro du compte dont le solde à été affiché
     *
     * @return double solde du compte
     * */
    public double afficherSoldeCompte(int numCompte){
        for (Compte compte:
             this.client.getComptes()) {
            if (compte.getNumeroCompte() == numCompte){
                return compte.soldeCompte;
            }
        }
        return -1;
    }


    /**
     * Méthode qui permet à l'administrateur de créer un client et l'ajoute à
     * la liste du gestionnaire
     *
     * @param prenom (String) prénom du client
     * @param nom (String) nom du client
     * @param telephone (String) numéro de téléphone du client
     * @param couriel (String) courriel du client
     * @param nip (int) nip du client
     *
     * @return boolean Confirme si le client à été crée
     * */
    public boolean creerClient(String prenom, String nom, String telephone, String couriel, int nip){
        if (this.client != this.admin){
            return false;
        }
        this.numClient += 1;
        this.clients.add(new Client(this.numClient, prenom, nom, telephone, couriel, nip));
        creerCompte("cheque", this.numClient, 1000, 2000, 0);
        return true;

    }

    /**
     * Méthode qui permet à l'admin de créer un compte pour un utilisateur donnée
     * vérifie si un compte chèque existe déjà dans le client avant d'ajouter un
     * compte d'un autre type
     *
     * @param type (String) type du compte à créer
     * @param montantTransfertMaximum (double) montant maximum pour les transfert
     * @param montantFactureMaximum (double) montant maximum pour le paiement de facture
     * @param tauxInteret (double) taux d'intéret pour les compte épargne ou marge
     * */
    public void creerCompte(String type, int codeClient, double montantTransfertMaximum, double montantFactureMaximum, double tauxInteret){

        boolean compteChequePresent = false;
        if (this.client == this.admin){
            Client client = this.getClientAvecCode(codeClient);
            switch (type){
                case "cheque":
                    this.numCompte += 1;
                    client.ajouterCompte(new CompteCheque(this.numCompte, codeClient, montantFactureMaximum, montantTransfertMaximum));
                    break;
                case "epargne":
                    for (Compte compte:
                            client.getComptes()) {
                        if (compte.getType().equals("cheque")){
                            compteChequePresent = true;
                        }
                    }

                    if (compteChequePresent){
                        this.numCompte += 1;
                        client.ajouterCompte(new CompteEpargne(this.numCompte, codeClient, tauxInteret, montantTransfertMaximum));
                    }
                    break;
                case "marge":
                    Boolean margePresente = false;
                    for (Compte compte:
                            client.getComptes()) {
                        if (compte.getType().equals("cheque")){
                            compteChequePresent = true;
                        } else if (compte.getType().equals("marge")) {
                            margePresente = true;
                        }
                    }

                    if (compteChequePresent && !margePresente){
                        this.numCompte += 1;
                        client.ajouterCompte(new MargeDeCredit(this.numCompte, codeClient, tauxInteret, montantTransfertMaximum));
                    }
                    break;
                case "hypotheque":
                    for (Compte compte:
                            client.getComptes()) {
                        if (compte.getType().equals("hypotheque")){
                            break;
                        }else if (compte.getType().equals("cheque")){
                            compteChequePresent = true;
                        }
                    }

                    if (compteChequePresent){
                        this.numCompte += 1;
                        client.ajouterCompte(new CompteHypothecaire(this.numCompte, codeClient, montantTransfertMaximum));
                    }
                    break;
                default:
                    System.out.println("Type de compte non existant");
                    break;
            }
        }
    }
}