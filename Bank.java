public class Bank {
    protected double argent;
    protected int armure;

    // Constructeur de la classe:
    public Bank(double argent, int armure){
        this.argent = argent;
        this.armure = armure;
    }

    // Getters:
    public double getArgent(){ return this.argent; }
    public int getArmure(){ return this.armure; }

}