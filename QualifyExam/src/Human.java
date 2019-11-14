/*******************
* Christian Duncan
* Human Class
*   Represents the class of all humans.
*   Allows humans to attack() another human
*******************/
import java.io.*;

public class Human extends Creature {
    private int intelligence;
    String name;
    public int x;


    // Default constructor:  Roll dice to get random values
    // for intelligence, etc.  Well, for now, we'll set them to 10
    // Uses a default name of "Nobody"
    Human() {
	this("Nobody");
    }

    // Constructor: Uses the given name.   And should roll dice to determine
    // stats... but uses the value 10 for now.
    Human(String initName) {
	super();
	setStrength(10);
	intelligence = 10;
	setDefense(10);
	setMaximumHitPoints(20);
	setCurrentHitPoints(20);
	name = initName;
    }

    // Accessor methods
    public int getIntelligence() { return intelligence; }
    public String getName() { return name; }

    // Mutator methods
    public void setIntelligence(int newIntelligence) { intelligence = newIntelligence; }
    public void setName(String newName) { name = newName; }

    // Attack another human
    public void attack(Creature otherCreature) {
	// The "this" operator refers to this particular object.
	System.out.println(this.name + " attacks " + otherCreature);
	int difference = this.getStrength() - otherCreature.getDefense();
	if (difference > 0) {
	    // You are stronger than the other person's defense
	    otherCreature.setCurrentHitPoints(otherCreature.getCurrentHitPoints()
					   - difference);
	}
    }

    // Decided if the current person is alive or not
    // Simply if his currentHitPoints are above zero
    public boolean isAlive() {
	if (this.getCurrentHitPoints() <= 0) {
	    return false;
	}
	else {
	    return true;
	}
    }
  
    // Prints the info related to the current human
    public void print() {
	System.out.println("Name: " + name + " Class: Human");
	System.out.println("Strength:     " + getStrength());
	System.out.println("Intelligence: " + getIntelligence());
	System.out.println("Defense:      " + getDefense());
	System.out.println("Hit Points:   " + getCurrentHitPoints() +
			   "("+ getMaximumHitPoints() + ")");
    }


}
