import java.util.Set;

public class Main {
    /**
     * Args: array with
     * <ol>
     *     <li>guild:&lt;montant initial&gt;,&lt;armures initiales&gt;</li>
     * </ol>
     *
     * @param args
     */
    public static void main(String[] args) {
        GuildCommandSystem guildCommandSystem = new GuildCommandSystem(args);

        Guilde maGuilde = makeGuilde(guildCommandSystem.actualCommand());

        System.out.println("\n------------* Bienvenue dans la guilde *-------------");
        System.out.println("Votre argent de depart est le suivant : " + maGuilde.getArgent());
        System.out.println("Vos armures de depart sont les suivantes: " + maGuilde.getArmure());
        System.out.println("-----------------------------------------------------\n");

        while (guildCommandSystem.hasNextCommand()) {
            GuildCommand command = guildCommandSystem.nextCommand();
            switch (command.getName()) {
                case "buy-hero" -> {
                    String nom = command.nextString();
                    System.out.println(" ----------- ACHETER HERO " + nom + " -----------");
                    maGuilde.acheterHero(nom, command.nextInt(), command.nextDouble(),
                                         command.nextInt(), command.nextDouble());
                    break;
                }
                case "buy-armor" ->{
                    System.out.println(" ----------- ACHETER ARMURE -----------");
                    maGuilde.acheterArmure(command.nextInt(), command.nextInt());
                    break;
                }
                case "do-quest" -> {
                    int niveauQuete = command.nextInt();
                    System.out.println(" ----------- QUETE DE NIVEAU " + niveauQuete + " -----------");
                    maGuilde.accomplirQuete(niveauQuete, command.nextDouble(), command.nextInt(), command.nextInt());
                    break;
                }
                case "train-hero" -> {
                    System.out.println(" ----------- ENTRAINER HERO -----------");
                    maGuilde.entrainerHero(command.nextString());
                    break;
                }
                default -> {
                    // Source: (https://www.w3schools.com/java/java_switch.asp)
                    System.out.println(" ----------- SECTION NON VALIDE -----------");
                    System.out.println("Commande non valide: " + command.getName());
                    break;
                }
            }
        }

        //------------------------------------------------------------------------------------------------------------//

        // Contenu genere apres la derniere commande:

        System.out.println("\n--------------------*--*--*--*--*--------------------");
        // Le code "String.format("%.1f", xxxxxxxx)" permet de conserver un seul decimal lors de l'impression. Il est
        // necessaire dans le cas ou il y a eu un calcul entre deux "double" (imprecision des nombres a virgule)
        // Source du code: https://www.javatpoint.com/how-to-round-double-and-float-up-to-two-decimal-places-in-java
        System.out.println("Guild Bank account: " + String.format("%.1f", maGuilde.getArgent()) + " ors & " +
                            maGuilde.getArmure() + " armures");

        // Affichage des heros survivant:
        if(maGuilde.getHeros().isEmpty() == false){
            System.out.println("Heros: ");
            // Source concernant les cles et le HashMap: https://www.geeksforgeeks.org/hashmap-keyset-method-in-java/
            // Obtenir les cles du HashMap contenant les listes de heros:
            Set<Integer> setOfKeySet = maGuilde.getHeros().keySet();
            // Pour chaque cle dans le "set" de cles, obtenir la liste de heros puis afficher tout les heros de la liste
            for(Integer key : setOfKeySet) {
                for(Hero chaqueHero : maGuilde.getHeros().get(key)) {
                    System.out.println("-" + chaqueHero.getNom() + ": niveau = " + chaqueHero.getCategorie() +
                                       ", PV = " + String.format("%.1f", chaqueHero.getPointsDeVie()));
                }
            }
        }

        // Affichage des erreurs rencontrees:
        if(maGuilde.getErreurs().size() != 0){
            System.out.println("Erreurs:");
            for(int i = 0; i < maGuilde.getErreurs().size(); i++){
                System.out.println("-" + maGuilde.getErreurs().get(i));
            }
        }

        System.out.println("\n-----------------------------------------------------");
        System.out.println("----------* Merci d'etre venu a la guilde *----------");
        System.out.println("-----------------------------------------------------\n");
    }

    public static Guilde makeGuilde(GuildCommand command) {
        double montantInitial = command.nextDouble();
        int nbArmures = command.nextInt();
        return new Guilde(montantInitial, nbArmures);
    }
}