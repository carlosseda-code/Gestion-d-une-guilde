import java.util.List;
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
        System.out.println("----------* Bienvenue dans la guilde *----------");
        System.out.println("Votre argent de départ est le suivant : " + maGuilde.getMontant());
        System.out.println("Vos armures de départ sont les suivantes: " + maGuilde.getArmures());
        System.out.println("------------------------------------------------");
        System.out.println();

        while (guildCommandSystem.hasNextCommand()) {
            GuildCommand command = guildCommandSystem.nextCommand();
            switch (command.getName()) {
                case "buy-hero" -> {
                    String nom = command.nextString();
                    System.out.println(" ------ ACHETER HERO " + nom + "---------");
                    maGuilde.acheterHero(nom, command.nextInt(), command.nextDouble(), command.nextInt(), command.nextDouble());
                    break;
                }
                case "buy-armor" ->{
                    System.out.println(" --------- ACHETER ARMURE ---------");
                    maGuilde.acheterArmure(command.nextInt(), command.nextInt());
                    break;
                }
                case "do-quest" -> {
                    System.out.println(" ------ DO QUEST SECTION ---------");
                    System.out.println("Categorie: " + command.nextInt());
                    System.out.println("Cost of Points of life: " + command.nextDouble());
                    System.out.println("Reward in money: " + command.nextInt());
                    System.out.println("Reward in armor: " + command.nextInt());
                    break;
                }
                case "train-hero" -> {
                    System.out.println(" ------ TRAIN HERO SECTION ---------");
                    System.out.println("Name of hero: " + command.nextString());
                    break;
                }
                default -> {
                    System.out.println(" ------ NOT VALID SECTION ---------");
                    System.out.println("Commande non valide: " + command.getName());
                    break;
                }
            }
        }
        System.out.println();
        System.out.println("Guild Bank account: " + maGuilde.getMontant() + " & " + maGuilde.getArmures() + " armours");
        if(maGuilde.getHeros().isEmpty() == false){
            Set<Integer> setOfKeySet = maGuilde.getHeros().keySet();
            for(Integer key : setOfKeySet) {
                for(Hero chaqueHero : maGuilde.getHeros().get(key)) {
                    System.out.println("-" + chaqueHero.getNom() + ": level=" + chaqueHero.getCategorie() + ", HP=" + chaqueHero.getPointsDeVie());
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

    // guild:100.0,10 buy-hero:Berserker,2,52.5,6,30.5 buy-hero:Zorro,1,36.2,2,15.0 do-quest:2,5.3,60,3 train-hero:Zorro buy-armor:1,10 carlos-seda:10


    public static Guilde makeGuilde(GuildCommand command) {
        double montantInitial = command.nextDouble();
        int nbArmures = command.nextInt();
        return new Guilde(montantInitial, nbArmures);
    }
}