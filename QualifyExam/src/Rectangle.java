
public class Rectangle extends Square{

	public Rectangle(int a, int b) {
		super(a, b);
	}

	
	public double hype(){
		return Math.sqrt(getWidth()*getLength());
		
	}

}
