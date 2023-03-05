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

    // Setters:
    public void setArgent(double argent) { this.argent = argent; }
    public void setArmure(int armure) { this.armure = armure; }

    // Methodes:
    public void ajouterArgent(double montant){      // si montant est de type "int", il y aura un casting implicite
        this.argent += montant;
    }
    public void retirerArgent(double montant){      // si montant est de type "int", il y aura un casting implicite
        this.argent -= montant;
    }
    public void ajouterArmure(int montant){
        this.armure += montant;
    }
    public void retirerArmure(int montant){
        this.armure -= montant;
    }
    public void retirerArmure(double montant){
        this.armure -= montant;
    }
}