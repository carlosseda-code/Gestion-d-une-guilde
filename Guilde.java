import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Guilde {
    private double montant;
    private int armures;
    private HashMap<Integer, ArrayList<Hero>> heros;
    private List<String> erreurs;

    public Guilde(double montant, int armures) {
        this.montant = montant;
        this.armures = armures;
        this.heros = new HashMap<>();
        for (int i = 0; i <= 4; i++) {
            heros.put(i, new ArrayList<>());
        }
        this.erreurs = new ArrayList<>();
    }

    public double getMontant(){
        return montant;
    }

    public int getArmures(){
        return armures;
    }

    public HashMap<Integer, ArrayList<Hero>> getHeros(){
        return heros;
    }

    public List<String> getErreurs(){
        return erreurs;
    }

    public boolean supprimerHero(Hero hero){
        for (ArrayList<Hero> herosCategorie : heros.values()) {
            for (Hero chaqueHero : herosCategorie) {
                if (chaqueHero.equals(hero)) {
                    herosCategorie.remove(hero);
                    return true;
                }
            }
        }
        return false;
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
            Hero hero = null;
            switch (categorie) {
                case 0 -> hero = new Hero0(nom, coutArgent, coutArmure, pointsDeVie);
                case 1 -> hero = new Hero1(nom, coutArgent, coutArmure, pointsDeVie);
                case 2 -> hero = new Hero2(nom, coutArgent, coutArmure, pointsDeVie);
                case 3 -> hero = new Hero3(nom, coutArgent, coutArmure, pointsDeVie);
                case 4 -> hero = new Hero4(nom, coutArgent, coutArmure, pointsDeVie);
            }
            this.montant -= coutArgent;
            this.armures -= coutArmure;
            System.out.println("Categorie: " + categorie);
            System.out.println("Cout d'argent: " + coutArgent);
            System.out.println("Cout d'armures: " + coutArmure);
            System.out.println("Points de vie: " + pointsDeVie);
            heros.get(categorie).add(hero);
        }
        else {
            erreurs.add("Il vous manque de l'argent et/ou des armures pour acheter " + nom + ".");
        }
    }

}
