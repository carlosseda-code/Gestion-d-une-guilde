public class Bank {
    protected double argent;
    protected int armure;

    // TODO - Constructor of the class
    public Bank(double argent, int armure){
        this.argent = argent;
        this.armure = armure;
    }

    // TODO - AJOUTER DE L'ARGENT FUNCTION 
    public void ajouterArgent(int montant){
        this.argent += montant;
    }

    // TODO - SUPRIMMER L'ARGENT FUNCTION
    public void suprimmerArgent(int montant){
        this.argent -= montant;
    }

    // TODO - AJOUTER ARMURE FUNCTION
    public void ajouterArmure(int montant){
        this.armure += montant;
    }

    // TODO - SUPRIMMER ARMURE FUNCTION
    public void suprimmerArmure(int montant){
        this.armure -= montant;
    }

    public double getArgent(){
        return this.argent;
    }

    public int getArmure(){ return this.armure; }

    public boolean assezArgent(int montant){
        return this.argent >= montant;
    }

    public boolean assezArmure(int armures){
        return this.armure >= armures;
    }
}
