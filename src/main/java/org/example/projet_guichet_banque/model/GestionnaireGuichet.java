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
    private ArrayList<Compte> comptesCheques;
    private ArrayList<Compte> comptesEpargnes;
    private ArrayList<Compte> comptesHypotheques;
    private ArrayList<Compte> comptesMarges;
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
        this.demandesComptes = new ArrayList<>();
        this.comptesCheques = new ArrayList<>();
        this.comptesEpargnes = new ArrayList<>();
        this.comptesHypotheques = new ArrayList<>();
        this.comptesMarges = new ArrayList<>();
        this.admin = new Client(0, "admin", "admin", "111-111-111", "admin@tech.com","1111");
        this.clients.add(this.admin);
    }

    public Client getClient() {
        return client;
    }

    public ArrayList<Client> getClientsAdmin() {
        if (this.client != this.admin){
            return null;
        }
        ArrayList<Client> clientsBanque = (ArrayList<Client>) this.clients.clone();
        clientsBanque.remove(this.admin);
        return clientsBanque;
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

    public ArrayList<Compte> getComptesCheques() {
        if (this.client != this.admin){
            return null;
        }
        return comptesCheques;
    }

    public ArrayList<Compte> getComptesEpargnes() {
        if (this.client != this.admin){
            return null;
        }
        return comptesEpargnes;
    }

    public ArrayList<Compte> getComptesHypotheques() {
        if (this.client != this.admin){
            return null;
        }
        return comptesHypotheques;
    }

    public ArrayList<Compte> getComptesMarges() {
        if (this.client != this.admin){
            return null;
        }
        return comptesMarges;
    }

    public ArrayList<Demande> getDemandesComptes() {
        return demandesComptes;
    }

    public boolean envoyerDemande(Demande demande){
        boolean demandeMargePresente = false;
        for (Demande demandeExistante:
             this.demandesComptes) {
            if (demandeExistante.getCodeClient() == demande.getCodeClient()
                    && demandeExistante.getTypeCompte().equals("marge")) {
                demandeMargePresente = true;
                break;
            }
        }

        if (demandeMargePresente && !demande.getTypeCompte().equals("marge")){
            this.demandesComptes.add(demande);
            return true;
        }else if(!demandeMargePresente){
            this.demandesComptes.add(demande);
            return true;
        }else{
            return false;
        }
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
    public int validerUtilisateur(int codeClient, String nip){
        this.client = null;
        for (Client client:
                this.clients) {
            if (client.getCodeClient() == codeClient && client.getStatut().equals("Bloque")){
                return 1;
            }

            if (client.getCodeClient() == codeClient && client.getNip().equals(nip)){
                this.client = client;
                this.nbEssaie = 0;
                return 0;
            } else if (client.getCodeClient() == codeClient && !client.getNip().equals(nip)) {
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
            if (montant > compteCourrant.retraitMaximum || montant % 10 != 0 || this.banque.getSoldeCompte() < montant ){
                return -1;
            }

            soldeAvant = compteCourrant.getSoldeCompte();
            compteCourrant.retirer(montant);

            if (soldeAvant != compteCourrant.getSoldeCompte()){
                this.banque.retirer(montant);
                this.transactions.add(new Transaction(montant,compteCourrant.getNumeroCompte(),this.banque.getNumeroCompte(), "cheque-Retrait chèque"));
                return compteCourrant.getSoldeCompte();
            }else {
                double difference;
                difference = montant - compteCourrant.getSoldeCompte();
                compteCourrant.retirer(compteCourrant.getSoldeCompte());
                this.transactions.add(new Transaction(soldeAvant,compteCourrant.getNumeroCompte(),this.banque.getNumeroCompte(), "cheque-Retrait chèque"));
                compteCourrant = null;
                for (Compte compte:
                     client.getComptes()) {
                    if (compte.getType().equals("marge")){
                        compteCourrant = compte;
                    }
                }
                if(compteCourrant != null){
                    compteCourrant.retirer(difference);
                    this.banque.retirer(montant);
                    this.transactions.add(new Transaction(difference,compteCourrant.getNumeroCompte(),this.banque.getNumeroCompte(), "marge-Dépassement retrait"));
                    return compteCourrant.getSoldeCompte();
                }
            }
        }
        return -2;
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
                this.transactions.add(new Transaction(montant,compteCourrant.getNumeroCompte(),this.banque.getNumeroCompte(), "epargne-Retrait épargne"));
                return compteCourrant.getSoldeCompte();
            }else {
                double difference;
                difference = montant - compteCourrant.getSoldeCompte();
                compteCourrant.retirer(compteCourrant.getSoldeCompte());
                this.transactions.add(new Transaction(soldeAvant,compteCourrant.getNumeroCompte(),this.banque.getNumeroCompte(), "epargne-Retrait epargne"));
                compteCourrant = null;
                for (Compte compte:
                        client.getComptes()) {
                    if (compte.getType().equals("marge")){
                        compteCourrant = compte;
                    }
                }
                if(compteCourrant != null){
                    compteCourrant.retirer(difference);
                    this.banque.retirer(montant);
                    this.transactions.add(new Transaction(difference,compteCourrant.getNumeroCompte(),this.banque.getNumeroCompte(), "marge-Dépassement retrait"));
                    return compteCourrant.getSoldeCompte();
                }
            }
        }
        return -2;
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
            this.transactions.add(new Transaction(montant,this.banque.getNumeroCompte(),compteCourrant.getNumeroCompte(), "cheque-Dépot chèque"));
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
            this.transactions.add(new Transaction(montant,this.banque.getNumeroCompte(),compteCourrant.getNumeroCompte(), "epargne-Dépot épargne"));
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
                this.transactions.add(new Transaction(montant,compteCourrant.getNumeroCompte(),this.banque.getNumeroCompte(), "cheque-Paiement de facture"));
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
                this.transactions.add(new Transaction(montant,compteProv.getNumeroCompte(),compteDest.getNumeroCompte(), "cheque-Transfert de fond"));
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
    public boolean creerClient(String prenom, String nom, String telephone, String couriel, String nip){
        if (this.client != this.admin){
            return false;
        }
        this.numClient += 1;
        this.clients.add(new Client(this.numClient, prenom, nom, telephone, couriel, nip));
        creerCompte("cheque", this.numClient, 1000, 2000);
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
     * */
    public void creerCompte(String type, int codeClient, double montantTransfertMaximum, double montantFactureMaximum){

        boolean compteChequePresent = false;
        if (this.client == this.admin){
            Client client = this.getClientAvecCode(codeClient);
            switch (type){
                case "cheque":
                    this.numCompte += 1;
                    CompteCheque compteCheque = new CompteCheque(this.numCompte, codeClient, montantFactureMaximum, montantTransfertMaximum);
                    client.ajouterCompte(compteCheque);
                    this.comptesCheques.add(compteCheque);
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
                        CompteEpargne compteEpargne = new CompteEpargne(this.numCompte, codeClient, montantTransfertMaximum);
                        client.ajouterCompte(compteEpargne);
                        this.comptesEpargnes.add(compteEpargne);
                    }
                    break;
                case "marge":
                    boolean margePresente = false;
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
                        MargeDeCredit margeCredit = new MargeDeCredit(this.numCompte, codeClient, montantTransfertMaximum);
                        client.ajouterCompte(margeCredit);
                        this.comptesMarges.add(margeCredit);
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
                        CompteHypothecaire compteHypothecaire = new CompteHypothecaire(this.numCompte, codeClient, montantTransfertMaximum);
                        client.ajouterCompte(compteHypothecaire);
                        this.comptesHypotheques.add(compteHypothecaire);
                    }
                    break;
                default:
                    System.out.println("Type de compte non existant");
                    break;
            }
        }
    }

    public double remplirGuichet(){
        if (this.client == this.admin){
            if (this.banque.getSoldeCompte() < 20000){
                this.banque.remplirGuichet();
                return this.banque.getSoldeCompte();
            }
        }
        return -1;
    }

    public double preleverMontant(int numCompte, double montant){
        CompteHypothecaire compteCourrant = null;
        double soldeAvant;

        if (this.client != this.admin){
            return -1;
        }

        for (Compte hypotheque:
             this.comptesHypotheques) {
            if (hypotheque.getNumeroCompte() == numCompte){
                compteCourrant = (CompteHypothecaire) hypotheque;
            }
        }

        if (compteCourrant!= null){
            soldeAvant = compteCourrant.getSoldeCompte();
            compteCourrant.retirer(montant);

            if (soldeAvant != compteCourrant.getSoldeCompte()){
                this.transactions.add(new Transaction(montant,compteCourrant.getNumeroCompte(),this.banque.getNumeroCompte(), "hypotheque-Prélèvement"));
                return compteCourrant.getSoldeCompte();
            }else {
                double difference;
                difference = montant - compteCourrant.getSoldeCompte();
                compteCourrant.retirer(compteCourrant.getSoldeCompte());
                this.transactions.add(new Transaction(soldeAvant,compteCourrant.getNumeroCompte(),this.banque.getNumeroCompte(), "hypotheque-Prélèvement"));
                MargeDeCredit margeCourrante = null;
                for (Compte compte:
                        this.comptesMarges) {
                    if (compte.getCodeClient() == compteCourrant.getCodeClient()){
                        margeCourrante = (MargeDeCredit) compte;
                    }
                }
                if(margeCourrante != null){
                    margeCourrante.retirer(difference);
                    this.transactions.add(new Transaction(difference,margeCourrante.getNumeroCompte(),this.banque.getNumeroCompte(), "marge-Dépassement prélèvement"));
                    return margeCourrante.getSoldeCompte();
                }
            }
        }
        return -2;
    }

    public double payerInteret(){
        if (this.client == this.admin){
            double difference;
            double montant;
            double interetTotalVerser = 0;
            for (Compte epargne:
                    this.comptesEpargnes) {
                if (epargne.getSoldeCompte() > 0){
                    montant = epargne.getSoldeCompte()*((CompteEpargne) epargne).getTauxInteret();
                    difference = montant - epargne.getSoldeCompte();
                    interetTotalVerser += difference;
                    epargne.setSoldeCompte(montant);
                    this.transactions.add(new Transaction(difference,epargne.getNumeroCompte(),this.banque.getNumeroCompte(), "epargne-Paiement des intérêts"));
                }
            }
            return interetTotalVerser;
        }
        return -1;
    }

    public double augmenterMarge(){
        if (this.client == this.admin){
            double interetTotal = 0;
            double difference;
            double montant;
            for (Compte marge:
                    this.comptesMarges) {
                if (marge.getSoldeCompte() > 0){
                    montant = marge.getSoldeCompte()*((MargeDeCredit) marge).getTauxInteret();
                    difference = montant - marge.getSoldeCompte();
                    interetTotal += difference;
                    marge.setSoldeCompte(montant);
                    this.transactions.add(new Transaction(difference,marge.getNumeroCompte(),this.banque.getNumeroCompte(), "marge-Intérêts Appliqués"));
                }
            }
            return interetTotal;
        }
        return -1;
    }
}