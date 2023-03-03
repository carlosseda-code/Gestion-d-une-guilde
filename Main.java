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

        System.out.println();
        System.out.println("------------* Bienvenue dans la guilde *-------------");
        System.out.println("Votre argent de départ est le suivant : " + maGuilde.getArgent());
        System.out.println("Vos armures de départ sont les suivantes: " + maGuilde.getArmure());
        System.out.println("-----------------------------------------------------");
        System.out.println();

        while (guildCommandSystem.hasNextCommand()) {
            GuildCommand command = guildCommandSystem.nextCommand();
            switch (command.getName()) {
                case "buy-hero" -> {
                    String nom = command.nextString();
                    System.out.println(" ---------- ACHETER HERO " + nom + " ----------");
                    maGuilde.acheterHero(nom, command.nextInt(), command.nextDouble(), command.nextInt(), command.nextDouble());
                    break;
                }
                case "buy-armor" ->{
                    System.out.println(" ----------- ACHETER ARMURE -----------");
                    maGuilde.acheterArmure(command.nextInt(), command.nextInt());
                    break;
                }
                case "do-quest" -> {
                    int niveauQuete = command.nextInt();
                    System.out.println(" ------------ QUETE DE NIVEAU " + niveauQuete + " ------------");
                    // System.out.println("Categorie: " + command.nextInt());
                    // System.out.println("Cost of Points of life: " + command.nextDouble());
                    // System.out.println("Reward in money: " + command.nextInt());
                    // System.out.println("Reward in armor: " + command.nextInt());
                    maGuilde.accomplirQuete(niveauQuete, command.nextDouble(), command.nextInt(), command.nextInt());
                    break;
                }
                case "train-hero" -> {
                    System.out.println(" ----------- ENTRAINER HERO -----------");
                    maGuilde.trainHero(command.nextString());
                    break;
                }
                default -> {
                    System.out.println(" --------- SECTION NON VALIDE ---------");
                    System.out.println("Commande non valide: " + command.getName());
                    break;
                }
            }
        }
        System.out.println();
        System.out.println("--------------------*--*--*--*--*--------------------");
        System.out.println("Guild Bank account: " + maGuilde.getArgent() + " ors & " + maGuilde.getArmure() + " armures");
        if(maGuilde.getHeros().isEmpty() == false){
            System.out.println("Heros: ");
            Set<Integer> setOfKeySet = maGuilde.getHeros().keySet();
            for(Integer key : setOfKeySet) {
                for(Hero chaqueHero : maGuilde.getHeros().get(key)) {
                    System.out.println("-" + chaqueHero.getNom() + ": niveau = " + chaqueHero.getCategorie() +
                                       ", PV = " + chaqueHero.getPointsDeVie());
                }
            }
        }
        if(maGuilde.getErreurs().size() != 0){
            System.out.println("Erreurs:");
            for(int i = 0; i < maGuilde.getErreurs().size(); i++){
                System.out.println("-" + maGuilde.getErreurs().get(i));
            }
        }

        System.out.println();
        System.out.println("-----------------------------------------------------");
        System.out.println("----------* Merci d'être venu à la guilde *----------");
        System.out.println("-----------------------------------------------------");
        System.out.println();
    }

    public static Guilde makeGuilde(GuildCommand command) {
        double montantInitial = command.nextDouble();
        int nbArmures = command.nextInt();
        return new Guilde(montantInitial, nbArmures);
    }
}