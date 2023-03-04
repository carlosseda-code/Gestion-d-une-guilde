import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Guilde extends Bank implements Quete{
    private HashMap<Integer, ArrayList<Hero>> heros;
    private List<String> erreurs;

    // Constructeur de la classe:
    public Guilde(double argent, int armure) {
        super(argent, armure);
        this.heros = new HashMap<>();
        for (int i = 0; i <= 4; i++) {              // pour tous les niveaux de heros, associer une liste à ce niveau
            heros.put(i, new ArrayList<>());
        }
        this.erreurs = new ArrayList<>();
    }

    // Getters:
    public HashMap<Integer, ArrayList<Hero>> getHeros(){
        return heros;
    }
    public List<String> getErreurs(){
        return erreurs;
    }

    // Methode qui gere l'achat d'armures selon l'argent du guilde
    public void acheterArmure(int nombre, int prix) {
        if (prix * nombre <= argent) {
            ajouterArmure(nombre);
            retirerArgent(prix * nombre);
            System.out.println(nombre + " armures achetees avec succes");
        }
        else{
            System.out.println("Erreur d'achat");
            erreurs.add("Il vous manque de l'argent pour acheter " + nombre + " armures");
        }
    }

    // Methode qui determine si la guilde a l'argent et l'armure necessaire pour acheter un heros, si oui, l'heros est
    // ajoute a la liste de heros
    public void acheterHero(String nom, int categorie, double coutArgent, int coutArmure, double pointsDeVie) {
        if (coutArgent <= getArgent() && coutArmure <= getArmure()) {
            Hero hero = null;
            // Utilisation d'un switch case au lieu de plusieurs if, else
            // Source: (https://www.w3schools.com/java/java_switch.asp)
            switch (categorie) {
                case 0 -> hero = new Hero0(nom, coutArgent, coutArmure, pointsDeVie);
                case 1 -> hero = new Hero1(nom, coutArgent, coutArmure, pointsDeVie);
                case 2 -> hero = new Hero2(nom, coutArgent, coutArmure, pointsDeVie);
                case 3 -> hero = new Hero3(nom, coutArgent, coutArmure, pointsDeVie);
                case 4 -> hero = new Hero4(nom, coutArgent, coutArmure, pointsDeVie);
            }
            retirerArgent(coutArgent);
            retirerArmure(coutArmure);
            System.out.println("Categorie: " + categorie);
            System.out.println("Cout d'argent: " + coutArgent);
            System.out.println("Cout d'armures: " + coutArmure);
            System.out.println("Points de vie: " + pointsDeVie);
            heros.get(categorie).add(hero);
        }
        else {
            System.out.println("Erreur d'achat");
            erreurs.add("Il vous manque de l'argent et/ou des armures pour acheter " + nom + ".");
        }
    }

    // Methode qui ameliore (ou non) le heros recu en parametre selon l'argent et l'armure disponible de la guilde
    public void entrainerHero(String nomHero) {

        for (int i = 0; i < heros.size(); i++) {
            ArrayList<Hero> heroes = heros.get(i);                   // obtenir la liste de heros de niveau i

            for (int j = 0; j < heroes.size(); j++) {
                Hero hero = heroes.get(j);                           // obtenir le heros a l'index j de la liste

                if (hero.getNom().equals(nomHero)) {                 // verifier si c'est le bon heros
                    if (hero.getCategorie() >= 4) {
                        System.out.println("Erreur");
                        erreurs.add("Impossible d'entraîner le héros " + hero.getNom() + " car il est au niveau max!");
                        return;
                    }

                    double coutArgent = 20 * Math.log(hero.getCategorie() + 10);
                    if (argent < coutArgent) {
                        System.out.println("Erreur");
                        erreurs.add("Il vous manque de l'argent pour améliorer " + hero.getNom() + ".");
                        return;
                    }

                    double coutArmure = Math.log(hero.getCategorie() + 10);
                    if (armure < coutArmure) {
                        System.out.println("Erreur");
                        erreurs.add("Il vous manque des armures pour améliorer " + hero.getNom() + ".");
                        return;
                    }

                    // supprimer le heros de niveau (i) et creer un nouveau heros de niveau (i + 1)
                    heros.get(hero.getCategorie()).remove(hero);
                    retirerArgent(coutArgent);
                    retirerArmure(coutArmure);
                    Hero nouveauHero;
                    switch (hero.getCategorie() + 1) {    // Source: (https://www.w3schools.com/java/java_switch.asp)
                        case 1:
                            nouveauHero = new Hero1(hero.getNom(), hero.getCoutArgent(),
                                                    hero.getCoutArmure(), hero.getMaxPV() * 1.5);
                            break;
                        case 2:
                            nouveauHero = new Hero2(hero.getNom(), hero.getCoutArgent(),
                                                    hero.getCoutArmure(), hero.getMaxPV() * 1.5);
                            break;
                        case 3:
                            nouveauHero = new Hero3(hero.getNom(), hero.getCoutArgent(),
                                                    hero.getCoutArmure(), hero.getMaxPV() * 1.5);
                            break;
                        case 4:
                            nouveauHero = new Hero4(hero.getNom(), hero.getCoutArgent(),
                                                    hero.getCoutArmure(), hero.getMaxPV() * 1.5);
                            break;
                        default:
                            return;
                    }
                    heros.get(nouveauHero.getCategorie()).add(nouveauHero);
                    System.out.println("Entrainement reussi");
                    System.out.println(nomHero + ": niveau " + (nouveauHero.getCategorie()-1)
                                        + " -> " + nouveauHero.getCategorie());
                    return;
                }
            }
        }
        // si le heros n'existe pas:
        System.out.println("Erreur");
        erreurs.add(nomHero + " n'existe pas dans votre inventaire.");
    }

    // Methode qui assigne la quete au meilleur heros (celui avec le plus grand PV) selon le niveau de la quete, puis
    // applique les consequences de la quete (echec ou reussite)
    public void accomplirQuete(int niveauQuete, double pvPerdus, int argentGagne, int armureGagne){

        int niveauHeroEnvoye = 0;
        ArrayList<Hero> listeHeros = null;

        // trouver la liste de heros du meme niveau ou de niveau superieur a la quete (en suivant la priorite):
        for (int i = niveauQuete; i < heros.size(); i++) {
            listeHeros = heros.get(i);
            if (listeHeros.size() != 0){                  // si la liste de heros n'est pas vide, on conserve le niveau
                niveauHeroEnvoye = i;                     // et on continue (sortie de la boucle "for")
                break;
            }

            // chercher un heros de niveau inferieur s'il n'existe pas d'hero de niveau egal ou superieur a la quete:
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

        // on envoie le heros qui a le plus de points de vie (actuel) pour la quete:
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

        // consequences de la quete selon les points de vie du heros (quete reussie ou quete echouee):
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
            // Le code "String.format("%.1f", xxxxxxxx)" permet de conserver un seul decimal lors de l'impression. Il
            // est necessaire dans le cas ou il y a eu un calcul entre deux "double" (imprecision des nombres a virgule)
            // Source du code: https://www.javatpoint.com/how-to-round-double-and-float-up-to-two-decimal-places-in-java
            System.out.println("-> " + nom + " a survecu avec " + String.format("%.1f", pvRestant) + " PV restants");
            System.out.println("*** QUETE REUSSIE ***");
            System.out.println("+" + argentGagne + " ors, +" + armureGagne + " armures" );
            ajouterArgent(argentGagne);
            ajouterArmure(armureGagne);
            System.out.println("Inventaire: " + String.format("%.1f", getArgent()) + " ors, " +
                                getArmure() + " armures");
        }
    }
}