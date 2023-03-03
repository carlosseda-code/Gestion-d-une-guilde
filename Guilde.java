import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Guilde extends Bank implements Quete{
    /*private double montant;
    private int armures;*/
    private HashMap<Integer, ArrayList<Hero>> heros;
    private List<String> erreurs;

    public Guilde(double argent, int armure) {
        super(argent, armure);
        /*this.argent = argent;
        this.armure = armure;*/
        this.heros = new HashMap<>();
        for (int i = 0; i <= 4; i++) {
            heros.put(i, new ArrayList<>());
        }
        this.erreurs = new ArrayList<>();
    }

    /*public double getMontant(){
        return montant;
    }

    public int getArmures(){
        return armures;
    }*/

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
        if (prix * nombre <= argent) {
            this.armure += nombre;
            this.argent -= prix * nombre;
        }
        else{
            erreurs.add("Il vous manque de l'argent pour acheter " + nombre + " armures");
        }
    }

    public void acheterHero(String nom, int categorie, double coutArgent, int coutArmure, double pointsDeVie) {
        if (coutArgent <= this.argent && coutArmure <= this.armure) {
            Hero hero = null;
            switch (categorie) {
                case 0 -> hero = new Hero0(nom, coutArgent, coutArmure, pointsDeVie);
                case 1 -> hero = new Hero1(nom, coutArgent, coutArmure, pointsDeVie);
                case 2 -> hero = new Hero2(nom, coutArgent, coutArmure, pointsDeVie);
                case 3 -> hero = new Hero3(nom, coutArgent, coutArmure, pointsDeVie);
                case 4 -> hero = new Hero4(nom, coutArgent, coutArmure, pointsDeVie);
            }
            this.argent -= coutArgent;
            this.armure -= coutArmure;
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

    public boolean trainHero(String name) {
        for (int i = 0; i < heros.size(); i++) {
            ArrayList<Hero> heroes = heros.get(i);
            for (int j = 0; j < heroes.size(); j++) {
                Hero hero = heroes.get(j);
                if (hero.getNom().equals(name)) {
                    if (hero.getCategorie() >= 4) {
                        erreurs.add("Impossible d'entraîner le héros " + hero.getNom() + " car il est au niveau max!");
                        return false;
                    }
                    double coutArgent = 20 * Math.log(hero.getCategorie() + 10);
                    if (argent < coutArgent) {
                        erreurs.add("Il vous manque de l'argent pour améliorer " + hero.getNom() + ".");
                        return false;
                    }
                    double coutArmure = Math.log(hero.getCategorie() + 10);
                    if (armure < coutArmure) {
                        erreurs.add("Il vous manque des armures pour améliorer " + hero.getNom() + ".");
                        return false;
                    }

                    heros.get(hero.getCategorie()).remove(hero);
                    argent -= coutArgent;
                    armure -= coutArmure;
                    Hero nouveauHero = null;
                    switch (hero.getCategorie() + 1) {
                        case 0:
                            nouveauHero = new Hero0(hero.getNom(), hero.getCoutArgent(), hero.getCoutArmure(), hero.getMaxPV() * 1.5);
                            break;
                        case 1:
                            nouveauHero = new Hero1(hero.getNom(), hero.getCoutArgent(), hero.getCoutArmure(), hero.getMaxPV() * 1.5);
                            break;
                        case 2:
                            nouveauHero = new Hero2(hero.getNom(), hero.getCoutArgent(), hero.getCoutArmure(), hero.getMaxPV() * 1.5);
                            break;
                        case 3:
                            nouveauHero = new Hero3(hero.getNom(), hero.getCoutArgent(), hero.getCoutArmure(), hero.getMaxPV() * 1.5);
                            break;
                        case 4:
                            nouveauHero = new Hero4(hero.getNom(), hero.getCoutArgent(), hero.getCoutArmure(), hero.getMaxPV() * 1.5);
                            break;
                        default:
                            return false;
                    }
                    heros.get(nouveauHero.getCategorie()).add(nouveauHero);
                    return true;
                }
            }
        }
        erreurs.add(name + " n'existe pas dans votre inventaire.");
        return false;
    }

    public void accomplirQuete(int niveauQuete, double pvPerdus, int argentGagne, int armureGagne){

        int niveauHeroEnvoye = 0;
        ArrayList<Hero> listeHeros = null;

        // trouver la liste d'heros du meme niveau ou de niveau superieur a la quete (en suivant la priorite):
        for (int i = niveauQuete; i < heros.size(); i++) {
            listeHeros = heros.get(i);
            if (listeHeros.size() != 0){                   // si la liste d'hero n'est pas vide, on conserve le niveau
                niveauHeroEnvoye = i;                      // et on continue (sortie de la boucle "for")
                break;
            }

            // chercher un hero de niveau inferieur s'il n'existe pas d'hero de niveau egal ou superieur a la quete:
            if (i == heros.size() - 1){
                for (int j = niveauQuete - 1; j >= 0; j--){
                    listeHeros = heros.get(j);
                    if (listeHeros.size() != 0){
                        niveauHeroEnvoye = j;
                        break;
                    }
                }
            }
        }

        // si la guilde ne contient pas d'hero(s): erreur et quete echouee
        if (listeHeros.size() == 0) {
            System.out.println("*** QUETE ECHOUEE ***");
            erreurs.add("Impossible de faire la quete (" + niveauQuete + "," + pvPerdus + "," +
                        argentGagne + "," + armureGagne + "), car vous n'avez pas d'heros dans votre inventaire.");
            return;
        }

        // on envoie le hero qui a le plus de points de vie (actuel) pour la quete:
        Hero heroEnvoye = listeHeros.get(0);
        if (listeHeros.size() > 1){
            for (int k = 1; k < listeHeros.size(); k++){  // comparaison avec tout les heros du meme niveau
                if (listeHeros.get(k).getPointsDeVie() > heroEnvoye.getPointsDeVie()){
                    heroEnvoye = listeHeros.get(k);
                }
            }
        }
        String nom = heroEnvoye.getNom();
        System.out.println("-> " + nom + " (niveau " + niveauHeroEnvoye + ") est envoye a la quete");

        // consequences de la quete selon les points de vie du hero (quete reussie ou quete echouee):
        double pvPerdusTotal = pvPerdus - (niveauHeroEnvoye - niveauQuete) * 1.5;
        if (pvPerdusTotal < 0) {          // empeche l'hero de regagner des points de vie
            pvPerdusTotal = 0;
        }
        heroEnvoye.setPointsDeVie(heroEnvoye.getPointsDeVie() - pvPerdusTotal);

        double pvRestant = heroEnvoye.getPointsDeVie();
        if (pvRestant <= 0){
            System.out.println("-> " + nom + " a perdu tout ses points de vie");
            System.out.println("*** QUETE ECHOUEE ***");
            heros.get(niveauHeroEnvoye).remove(heroEnvoye);
        } else {
            System.out.println("-> " + nom + " a survecu avec " + pvRestant + " PV restants");
            System.out.println("*** QUETE REUSSIE ***");
            System.out.println("+" + argentGagne + " ors, +" + armureGagne + " armures" );
            this.argent += argentGagne;
            this.armure += armureGagne;
            System.out.println("Inventaire: " + this.argent + " ors, " + this.armure + " armures");
        }
    };
}
