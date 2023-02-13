public class Main {
    public static void main(String args[]) {
        GuildCommandSystem arguments = new GuildCommandSystem(args);
        GuildCommand commands = new GuildCommand(arguments.command[arguments.index]);
        while(arguments.hasNextCommand()){
            System.out.println("Name: " + commands.getName());
            commands.nextString();
            // System.out.println("args[" + i + "]: " + args[i]);
        }
        System.out.println("*-------------------------------*");
        System.out.println("Welcome to the Management of the guild");
        System.out.println("*-------------------------------*");
        // stuff to do
        System.out.println("*-------------------------------*");
        System.out.println("Thank you for using the system!");
        System.out.println("*-------------------------------*");
    }
 }