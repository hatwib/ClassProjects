public abstract class ChessPiece {
    // Indicates what TEAM the piece belongs to
    // true=>White, false=>Black
    public boolean team;   
    
    public int xPos;
    public int yPos;

    // Attempt to move the piece to the given location
    //    Returns true if successful and false otherwise
    //    Notice the method is not defined (hence abstract)
    public abstract boolean move(int newX, int newY);

    // Convert the piece to String representation
    public String toString() {
	return "Piece: (" + xPos + "," + yPos + ")";
    }
}
