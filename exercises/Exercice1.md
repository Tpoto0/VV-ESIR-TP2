# TCC *vs* LCC

Explain under which circumstances *Tight Class Cohesion* (TCC) and *Loose Class Cohesion* (LCC) metrics produce the same value for a given Java class. Build an example of such as class and include the code below or find one example in an open-source project from Github and include the link to the class below. Could LCC be lower than TCC for any given class? Explain.

A refresher on TCC and LCC is available in the [course notes](https://oscarlvp.github.io/vandv-classes/#cohesion-graph).

## Answer

TCC and LCC produce the same value when: all methods are either fully directly related (TCC = LCC = 1), none of the methods are related (TCC = LCC = 0), the class has only one method (TCC = LCC = 1).

Example :
public class Voitures {
    private int prix;
    private String marque;
    private String modele;

    public Voitures(String marque, String modele, int prix){
        this.marque = marque;
        this.modele = modele;
        this.prix = prix;
    }

    String getMarque(){
        return this.marque
    }

    String setMarque(String marque){
        this.marque = marque;
    }

    public void afficherDetails() {
        System.out.println("Marque : " + this.marque);
        System.out.println("Modèle : " + this.modele);
        System.out.println("Prix : " + this.prix);
    }
}

LCC can't be lower than TCC because LCC considers both direct and indirect connections between methods, while TCC only accounts for direct connections, making LCC equal to or greater than TCC.