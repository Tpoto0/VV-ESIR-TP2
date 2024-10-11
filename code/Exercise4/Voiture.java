package fr.istic.vv;

public class Voiture {
    private String marque;
    private String modele;
    private int prix;

    Voiture(String marque, String modele, int prix){
        this.marque = marque;
        this.modele = modele;
        this.prix = prix;
    }

    public String getMarque(){
        return this.marque;
    }

    public int getPrix(){
        return this.prix;
    }

    public void afficherStat(){
        System.out.println(marque);
        System.out.println(modele);
        System.out.println(prix);
    }
}
