public abstract class Creature {
    private int strength;
    private int defense;
    private int maximumHitPoints;
    private int currentHitPoints;

    public Creature() {
	strength = 1;
	defense = 1;
	maximumHitPoints = 1;
	currentHitPoints = 1;
    }

    public int getStrength() { return strength; }
    public int getDefense() { return defense; }
    public int getMaximumHitPoints() { return maximumHitPoints; } 
    public int getCurrentHitPoints() { return currentHitPoints; }
    
    public void setStrength(int newStrength) { strength = newStrength; }
    public void setDefense(int newDefense) { defense = newDefense; }
    public void setMaximumHitPoints(int newMaximumHitPoints) { 
	maximumHitPoints = newMaximumHitPoints; }
    public void setCurrentHitPoints(int newCurrentHitPoints) { 
	currentHitPoints = newCurrentHitPoints; }

    public abstract void attack(Creature otherCreature);

    public abstract void print();
}
