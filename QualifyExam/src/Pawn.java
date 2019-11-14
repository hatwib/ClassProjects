public class Pawn extends ChessPiece {
    // Attempt to move the piece to the given location
    //    Returns true if successful and false otherwise
    //    Now, we define the actual movement
    public boolean move(int newX, int newY) {
	// Compute the difference in position
	int dx = newX - xPos;
	int dy = newY - yPos;

	if (dx != 0) {
	    // Won't allow pawns to move diagonally!
	    //  This isn't always true though is it?
	    return false;
	}

	if (team == true) {
	    // White pawns can only move in the positive Y direction (up)
	    //    And by one unit only
	    if (dy == 1) {
		xPos = newX; yPos = newY;
		return true;
	    }
	    else return false;
	}
	else {
	    // Black pawns can only move in the negative Y direction (down)
	    //    And by one unit only
	    if (dy == -1) {
		xPos = newX; yPos = newY;
		return true;
	    }
	    else return false;
	}
    }
}
