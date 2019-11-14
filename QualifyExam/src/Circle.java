
public class Circle {
	int radius; // class variable
	
	static int p; // instance variable
	
	private void setRadius(int a){
		radius = a;
	}

	public double area(){
		double PI = 3.14;
		return PI*radius*radius;
	}
	
	public static void Print(){ // static method
		
	}
}
  //  Circle c1 = new Circle(); c1.radius

//	Circle.Print();
  
  // Circel c2 = new Circel(); c2.radius