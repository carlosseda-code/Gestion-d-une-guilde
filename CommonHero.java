public class CommonHero {
    // TODO VARIABLES
    protected String name;
    protected int level;
    protected double maxHP;
    protected double currentHP;

    // TODO CONSTRUCTOR AND FUNCTIONS
    public CommonHero(String name, int level, double maxHP, double currentHP) {
        this.name = name;
        this.level = level;
        this.maxHP = maxHP;
        this.currentHP = currentHP;
    }

    // Getters:
    public String getName() {
        return name;
    }
    public int getLevel() {
        return level;
    }
    public double getMaxHP() {
        return maxHP;
    }
    public double getCurrentHP() {
        return currentHP;
    }

    // Setters: *****I DIDN'T ADD A SETTER FOR THE NAME BECAUSE IT DOESN'T NEED TO CHANGE?****
    public void setLevel(int level) {
        this.level = level;
    }
    public void setMaxHP(double maxHP) {
        this.maxHP = maxHP;
    }
    public void setCurrentHP(double currentHP) {
        this.currentHP = currentHP;
    }
}
