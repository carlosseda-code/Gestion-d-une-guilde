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

        Guilde maGuilde = makeGuilde(guildCommandSystem.nextCommand());

        while (guildCommandSystem.hasNextCommand()) {
            GuildCommand command = guildCommandSystem.nextCommand();
            switch (command.getName()) {
                case "buy-hero" -> {
                    // TODO
                }
                case "buy-armor" ->{
                    // TODO
                }
                case "do-quest" -> {
                    // TODO
                }
                case "train-hero" -> {
                    // TODO
                }
            }
        }
    }


    public static Guilde makeGuilde(GuildCommand command) {
        int montantInitial = command.nextInt();
        int nbArmures = command.nextInt();
        return new Guilde(montantInitial, nbArmures);
    }
}