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
                    // TODO
                    // TESTING ...
                    System.out.println(" ------ BUY HERO SECTION ---------");
                    System.out.println("NameOfHero: " + command.nextString());
                    System.out.println("Categorie: " + command.nextString());
                    System.out.println("Cost in money: " + command.nextDouble());
                    System.out.println("Cost in armor: " + command.nextString());
                    System.out.println("Points of life: " + command.nextDouble());
                    break;
                    //guild:100.0,10 buy-hero:Berserker,2,52.5,6,30.5
                }
                case "buy-armor" ->{
                    // TODO
                    System.out.println(" ------ BUY ARMOR SECTION ---------");
                    System.out.println("Amount of armor to buy: " + command.nextInt());
                    System.out.println("Price per armor: " + command.nextInt());
                    break;
                }
                case "do-quest" -> {
                    // TODO
                    // TESTING ...
                    System.out.println(" ------ DO QUEST SECTION ---------");
                    System.out.println("Categorie: " + command.nextInt());
                    System.out.println("Cost of Points of life: " + command.nextDouble());
                    System.out.println("Reward in money: " + command.nextInt());
                    System.out.println("Reward in armor: " + command.nextInt());
                    break;
                }
                case "train-hero" -> {
                    // TODO
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