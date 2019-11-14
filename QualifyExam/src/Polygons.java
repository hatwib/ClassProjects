
public class Polygons {
	public double PI;
	
	public Polygons(){
		PI = 3.1415;	
	}
	
	public static void Parameter(){
		System.out.print("Printing parameter");
	}
	
	//Overloading
	public double area(int l){ //square
		return l * l;
	}
	
	public double area(int l, int w){ // rectangle
		return l * w;
	}
	
	public double area(double r){ // circle
		return PI * r * r;
	}
	

}
