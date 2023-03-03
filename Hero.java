public class Hero {
    private String nom;
    private int categorie;
    private double coutArgent;
    private int coutArmure;
    private double pointsDeVie;
    private double maxPV;

    public Hero(String nom, int categorie, double coutArgent, int coutArmure, double pointsDeVie) {
        this.nom = nom;
        this.categorie = categorie;
        this.coutArgent = coutArgent;
        this.coutArmure = coutArmure;
        this.pointsDeVie = pointsDeVie;
        this.maxPV = pointsDeVie;
    }
    // Getters:
    public String getNom(){
        return this.nom;
    }
    public int getCategorie(){ return this.categorie; }
    public double getPointsDeVie(){ return this.pointsDeVie; }
    public double getMaxPV(){ return this.maxPV; }
    public double getCoutArgent(){ return this.coutArgent; }
    public int getCoutArmure(){ return this.coutArmure; }

    // Setters:
    public void setPointsDeVie(double pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
    }
}

