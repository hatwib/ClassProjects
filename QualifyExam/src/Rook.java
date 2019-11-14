public class Rook extends ChessPiece {
    // Attempt to move the piece to the given location
    //    Returns true if successful and false otherwise
    //    Now, we define the actual movement
    //    Rooks can move vertically OR horizontally but not both
    public boolean move(int newX, int newY) {
	// Compute the difference in position
	int dx = newX - xPos;
	int dy = newY - yPos;
	
	if ((dx == 0) && (dy == 0)) {
	    // Hmm, no change -- not allowed!
	    return false;
	}
	else if ((dx == 0) || (dy == 0)) {
	    // It moved in only one of the two directions!
	    // GOOD!
	    xPos = newX; yPos = newY;
	    return true;
	} else {
	    // Attempt to move in BOTH x and y direction!
	    return false;
	}
    }
}

    

