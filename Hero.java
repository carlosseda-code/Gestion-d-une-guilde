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

    public String getNom(){
        return this.nom;
    }

    public Integer getCategorie(){
        return this.categorie;
    }

    public Double getPointsDeVie(){
        return this.pointsDeVie;
    }

    public double getMaxPV(){
        return this.maxPV;
    }
}

