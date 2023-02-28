import java.util.ArrayList;
import java.util.List;

public class Guilde {
    private double montant;
    private int armures;
    private List<Hero> heros;
    private List<String> erreurs;

    public Guilde(double montant, int armures) {
        this.montant = montant;
        this.armures = armures;
        this.heros = new ArrayList<>();
        this.erreurs = new ArrayList<>();
    }

    public double getMontant(){
        return montant;
    }

    public int getArmures(){
        return armures;
    }

    public List<Hero> getHeros(){
        return heros;
    }

    public List<String> getErreurs(){
        return erreurs;
    }

    public boolean supprimerHero(Hero hero){
        return heros.remove(hero);
    }

    public void acheterArmure(int nombre, int prix) {
        if (prix * nombre <= montant) {
            this.armures += nombre;
            this.montant -= prix * nombre;
        }
        else{
            erreurs.add("Il vous manque de l'argent pour acheter " + nombre + " armures");
        }
    }

    public void acheterHero(String nom, int categorie, double coutArgent, int coutArmure, double pointsDeVie) {
        if (coutArgent <= this.montant && coutArmure <= this.armures) {
            Hero nouveauHero = new Hero(nom, categorie, coutArgent, coutArmure, pointsDeVie);
;           this.montant -= coutArgent;
            this.armures -= coutArmure;
            System.out.println("Categorie: " + categorie);
            System.out.println("Cout d'argent: " + coutArgent);
            System.out.println("Cout d'armures: " + coutArmure);
            System.out.println("Points de vie: " + pointsDeVie);
            heros.add(nouveauHero);
        }
        else {
            erreurs.add("Il vous manque de l'argent et/ou des armures pour acheter " + nom + ".");
        }
    }

}
