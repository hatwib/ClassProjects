public class Square {
	private int width;
	private int length;
	
	private static int newver;
	
	public Square(int a, int b){
		width = a;
		length = b;
	}
	
	public void passByReference(int x[]){
		for(int i = 0; i < x.length; i++){
			x[i] = 10;
		}
	}
	
	public int area(){
		if(isWidthSet())
			return length * width;
		else
			return 0;
	}
	
	public int perimeter(){
		if(isWidthSet())
			return 2 * (length + width);
		else
			return 0;
	}
	
	public void setLength(int l){
		length = l;
	}
	
	public int getLength(){
		return length;
	}
	
	public void setWidth(int w){
		width = w;
	}
	
	public int getWidth(){
		return width;
	}
	
	private boolean isWidthSet(){
		if(width > 0)
			return true;
		else
			return false;
	}
	
	//Overriding
	public void increase(int a){
		width = a;
		length = a;
	}
	
	public String increase(int a, int b, String x){
		width = a;
		length = b;
		return x;
	}

	public double hype(){
		return Math.sqrt(width*width);
	}
}
