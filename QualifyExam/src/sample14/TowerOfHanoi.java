package sample14;
/****************************

 * Christian Duncan
 * TowerOfHanoi Application/Applet Example
 *
 * This code gives a simple interface to the tower of Hanoi game.
 * It is still quite poorly documented... 
 * I apologize completely, but the code should still be
 * fairly readable.
 ****************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TowerOfHanoi extends JFrame 
    implements ActionListener, WindowListener { 
    // The main() method makes the class an application.
    //   It creates an instance of the game, sets size, and 
    //   makes the frame visible
    public static final int DEFAULT_NUM_DISCS = 10;

    // How much time to delay the animation steps
    public static final int PAUSE = 300;

    public static void main(String[] args) {
	TowerOfHanoi frame = new TowerOfHanoi();
	frame.setSize(300, 300);
	frame.setVisible(true);

	while (true) {
	    if (frame.wantToSolveIt()) {
		frame.solveIt();
	    }
	}
    }

    // VARIABLES
    private Stack<Integer>[] peg;   // Each peg represents a Stack of Discs (just Integers)
    private int numDiscs;  // Number of discs in the Game
    private JButton[] movePeg;  // One button per peg
    private int moveFrom;  // Move top disc from which peg
    private int moveTo;    // ..            to which peg
    private TowerCanvas canvas;   // The canvas where Discs/Pegs are drawn...
    private boolean wantToSolve;

    /********************************
     *  Constructors
     *******************************/
    // Default constructor
    public TowerOfHanoi() { this(DEFAULT_NUM_DISCS); }

    // Initialize the game using the given number of discs
    public TowerOfHanoi(int initNumDiscs) {
	// Call the super constructor (passing the frame title)
	super("The Terrible Tower Of Hanoi!!!!");

	int i;

	// Set the layout
	Container pane = this.getContentPane();
	pane.setLayout(new BorderLayout());

	// Make this object handle window operations on itself...
	this.addWindowListener(this);

	pane.setBackground(Color.blue);
	pane.setForeground(Color.black);

	if (initNumDiscs <= 0) {
	    numDiscs = DEFAULT_NUM_DISCS;
	} else {
	    numDiscs = initNumDiscs;
	}

	// Create the three peg stacks 
	// and place ALL discs on peg[0] for starters
	resetStack();
	resetMove();

	// Create the buttons
	JPanel buttonPanel = new JPanel(new GridLayout(1, peg.length));
	movePeg = new JButton[peg.length];
	for (i = 0; i < movePeg.length; i++) {
	    movePeg[i] = new JButton("Peg " + (i+1));
	    movePeg[i].addActionListener(this);
	    buttonPanel.add(movePeg[i]);
	}
	pane.add(buttonPanel, BorderLayout.SOUTH);

	// Create canvas where Discs are drawn on the screen...
	canvas = new TowerCanvas(this);
	pane.add(canvas, BorderLayout.CENTER);

	// Create MenuBar to select Solve It
	JMenuBar mBar = new JMenuBar();
	this.setJMenuBar(mBar);

	JMenuItem it;
	it = new JMenuItem("Reset");
	it.addActionListener(this);
	mBar.add(it);

	it = new JMenuItem("Solve It");
	it.addActionListener(this);
	mBar.add(it);

	wantToSolve = true;
    }

    public Stack getPeg(int i) {
	if ((i < 0) || (i >= peg.length)) {
	    return null;
	} else
	    return (Stack) peg[i].clone();
    }

    public int getNumDiscs() { return numDiscs; }
    public int getNumPegs() { return peg.length; }
    public int getMoveFrom() { return moveFrom; }
    public int getMoveTo() { return moveTo; }

    // This method resets the discs...
    //    Recreates the entire stack and discs from scratch
    private void resetStack() {
	int i;
	peg = (Stack<Integer>[]) new Stack[3];
	for (i = 0; i < peg.length; i++) 
	    peg[i] = new Stack<Integer>();

	for (i = numDiscs; i >= 1; i--) {
	    Integer d = new Integer(i);
	    peg[0].push(d);
	}
    }

    // This method listens for any action events to occur
    public void actionPerformed(ActionEvent e) {
	// Returns the Object which is the source of the action
	//    I.e. which button actually was pressed...
	Object source = e.getSource();
	
	// Find out if one of the buttons was pressed
	int i;
	int pegPressed = -1;
	for (i = 0; i < movePeg.length; i++) {
	    if (movePeg[i] == source) {
		// Found it
		pegPressed = i;
	    }
	}
	if (pegPressed != -1) {
	    selectPeg(pegPressed);
	    return;
	} else {
	    // Let us look at the ActionCommand instead
	    String command = e.getActionCommand();
	    if (command.equals("Reset")) {
		resetStack();
		resetMove();
		canvas.repaint();
	    }
	    if (command.equals("Solve It")) {
		wantToSolve = true;
	    }
	}
    }

    public boolean wantToSolveIt() { return wantToSolve; }

    // This method resets the discs and then 
    // recursively solves the problem...
    //   That is: move discs from peg 0 to peg 2
    public void solveIt() {
	resetStack();
	solveIt(numDiscs, 0, 2, 1);
	wantToSolve = false;
    }

    // This is the recursive method that actually 
    // solves the tower of hanoi problem.
    //    Move the topNumDiscs from "fromPeg" to "toPeg" using
    //    "otherPeg" as an intermediate peg.
    public void solveIt(int topNumDiscs, int fromPeg, int toPeg, int otherPeg) {
	if (topNumDiscs <= 0) {
	    // No discs to move
	    return;
	}

	// First move the top N-1 discs to the "other" peg
	solveIt(topNumDiscs - 1, fromPeg, otherPeg, toPeg);

	// Now that is done, move the bottom disc to the "toPeg"
	moveDisc(fromPeg, toPeg);

	// Pause for speed in animation
	try {
	    Thread.sleep(PAUSE);
	} catch (Exception ignored) { }

	// And move the remaining N-1 discs from "other" to "toPeg"
	solveIt(topNumDiscs - 1, otherPeg, toPeg, fromPeg);

	// And we are done!
    }

    // This method paints the frame...
    //    It draws the pegs and discs in proper location
    //    onto the graphics environment (g) given which should
    //    represent the location where this picture should go
    // This method moves the top disc from peg A to peg B
    //    Returns false if not allowed
    public boolean moveDisc(int a, int b) {
	if (a == b) 
	    // Can't move from a to a!!!
	    return false;

	if (a < 0 || a >= peg.length) 
	    // a is not a valid peg number
	    return false;
	
	if (b < 0 || b >= peg.length) 
	    // b is not a valid peg number
	    return false;

	// Make sure that A is not empty!
	if (peg[a].empty()) 
	    return false;

	// If peg b is empty we can move it
	if (peg[b].empty()) {
	    moveDiscWithoutCheck(a, b);
	    return true;
	} 

	// Otherwise, get the two values and make sure A's disc < B's disc
	int dA = ((Integer) peg[a].peek()).intValue();
	int dB = ((Integer) peg[b].peek()).intValue();
	
	if (dA > dB)
	    // Can't do it
	    return false;

	moveDiscWithoutCheck(a,b);
	return true;
    }

    // Notice, this private method does not need to check
    //   for validity.  Only this method has access!!!
    private void moveDiscWithoutCheck(int a, int b) {
	Integer dA = peg[a].pop();
	peg[b].push(dA);
	canvas.repaint();
    }

    // Reset the move portion.
    private void resetMove() {
	moveFrom = moveTo = -1;
    }

    // Selects a particular peg 
    // (as either move to or move from)
    private void selectPeg(int pegPressed) {
	// Now, are we moving FROM this peg or TO this peg?
	if (moveFrom == -1) {
	    // MoveFrom not selected yet...
	    moveFrom = pegPressed;
	    canvas.repaint();
	}
	else {
	    // Move Disc from moveFrom to moveTo
	    moveTo = pegPressed;
	    moveDisc(moveFrom, moveTo);
	    
	    // And reset back
	    resetMove();
	    canvas.repaint();
	}
    }

    /*************************************
     * Window Event Handlers
     ************************************/
    public void windowActivated(WindowEvent e) { }

    public void windowDeactivated(WindowEvent e) { }

    public void windowClosed(WindowEvent e) { }

    public void windowClosing(WindowEvent e) { 
	setVisible(false);
	System.exit(1);
    }

    public void windowOpened(WindowEvent e) { }

    public void windowIconified(WindowEvent e) { }

    public void windowDeiconified(WindowEvent e) { }
}

// A non-public class so it can be in the same file.
// It could have also been made as an INNER class.
class TowerCanvas extends Canvas {
    private TowerOfHanoi parent;

    public TowerCanvas(TowerOfHanoi myParent) {
	parent = myParent;
    }

    public void paint(Graphics g) {
	super.paint(g);
	int p;

	int numPegs = parent.getNumPegs();
	int numDiscs = parent.getNumDiscs();
	int moveFrom = parent.getMoveFrom();

	// Redraw the whole screen (clear it first)
	// g.setColor(getBackground());
	// g.fillRect(0, 0, getSize().width, getSize().height);
	g.setColor(getForeground());

	// Compute UNITS needed to draw smallest disc
	//   Compute width/height allowed for each peg
	int w = getSize().width/parent.getNumPegs();
	int h = getSize().height;

	//   Compute horizontal/vertical unit needed for smallest disc
	int hunit = w / numDiscs;
	int vunit = h / numDiscs;

	for (p = 0; p < parent.getNumPegs(); p++) {
	    // Paint each peg at proper location
	    // If this peg is selected in the moveFrom category...
	    //   then highlight the top disc as well...
	    Stack peg = parent.getPeg(p);

	    paintPeg(g, peg, hunit, vunit, w * p + w/2, h, (moveFrom == p));
	}
    }

    // This helper method simply paints one particular peg 
    // using the dimensions, etc given
    private void paintPeg(Graphics g, Stack p, int hunit, int vunit, 
			  int center, int bottom, boolean highlightTop) {
	// Make a copy of the current stack
	Stack copy = (Stack) p.clone();

	// Draw the actual peg
	int pegWidth = hunit/2;
	g.setColor(Color.darkGray);
	g.fillRoundRect(center - pegWidth/2, vunit/4, pegWidth, 
			bottom, pegWidth/2, pegWidth/2);

	int y = bottom - p.size() * vunit;

	// Go through the current stack
	while (!copy.empty()) {
	    // Get the current disc (created as an Integer object)
	    Integer d = (Integer) copy.pop();

	    // ... And its corresponding width
	    int width = d.intValue();

	    // Draw the disc on the screen (in correct color)
	    if (highlightTop) {
		g.setColor(Color.red);
		highlightTop = false;
	    } else {
		g.setColor(Color.orange);
	    }
	    g.fillRoundRect(center - (hunit * width)/2, y, hunit * width, vunit, vunit, vunit/2);
	    g.setColor(getForeground());
	    g.drawRoundRect(center - (hunit * width)/2, y, hunit * width, vunit, vunit, vunit/2);
	    
	    // Move y up to next disc
	    y += vunit;
	}
    }
}
