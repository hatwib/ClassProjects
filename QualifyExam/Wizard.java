/*******************
* Christian Duncan
* Wizard Class
*   extends a Human with special attack skills...
*******************/
import java.io.*;

public class Wizard extends Human {
    private int magicPoints;

    // Default constructor:  Calls super class constructor
    public Wizard() {
	super();
	magicPoints = 0;
    }

    // Constructor: Calls super(initName) (Create a Human with given name)
    public Wizard(String initName) {
	super(initName);
	magicPoints = 0;
    }

    public void print() {
	System.out.println("Name:      " + getName());
	System.out.println(" Class:    Wizard");
	System.out.println(" Intellig: " + getIntelligence());
	System.out.println(" Defense:  " + getDefense());
	System.out.println(" Magic P:  " + magicPoints);
	System.out.println("Hit Points:   " + getCurrentHitPoints() +
			   "("+ getMaximumHitPoints() + ")");
    }

    // Accessor methods
    public int getMagicPoints() { return magicPoints; }

    // Mutator methods
    public void setMagicPoints(int newMagicPoints) { magicPoints = newMagicPoints; }

    // Casts a spell
    public void castSpell() { System.out.println("Spell Cast!"); }

    public void attack(Creature otherCreature) {
	super.attack(otherCreature);
	castSpell();
    }
}
