import java.util.Scanner;

public class ChessDemo {
    // This is of course a VERY basic toy demo...
    // Overly simplistic!!!
    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);

	// Make two pieces for each player
	ChessPiece[] white = new ChessPiece[2];
	ChessPiece[] black = new ChessPiece[2];
	white[0] = new Pawn();
	white[0].team = true;
	white[0].xPos = 0;
	white[0].yPos = 0;
	
	white[1] = new Rook();
	white[1].team = true;
	white[1].xPos = 1;
	white[1].yPos = 0;
	
	black[0] = new Pawn();
	black[0].team = true;
	black[0].xPos = 0;
	black[0].yPos = 8;

	black[1] = new Rook();
	black[1].team = true;
	black[1].xPos = 1;
	black[1].yPos = 8;

	// Move one of the pieces
	int piece;
	int newX, newY;
	do {
	    System.out.println("White: Move which piece [0-1]");
	    piece = in.nextInt();
	} while ((piece < 0) || (piece > 1));
	
	System.out.println("White: Move " + white[piece] + " to where?");
	System.out.print("X? ");
	newX = in.nextInt();
	System.out.print("Y? ");
	newY = in.nextInt();
	
	if (white[piece].move(newX, newY)) {
	    System.out.println("Move successful");
	} else {
	    System.out.println("Move not allowed");
	}
	System.out.println("White: " + white[piece]);

	// And so on...
    }
}
