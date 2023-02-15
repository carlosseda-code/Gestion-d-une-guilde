import java.util.ArrayList;
import java.util.List;

public class Guilde {
    private double montant;
    private int armures;
    private List<Hero> heros;

    public Guilde(double montant, int armures) {
        this.montant = montant;
        this.armures = armures;
        this.heros = new ArrayList<>();
    }

    public double getMontant(){
        return montant;
    }

    public int getArmures(){
        return armures;
    }

    public List<Hero> getHeros(){
        return heros;
    }

    public boolean supprimerHero(Hero hero){
        return heros.remove(hero);
    }

    // TODO Buy armor....

    // TODO Buy Hero ..

}
