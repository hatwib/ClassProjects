
public class Circle2 extends Polygons{

	public static void main(String[] arg){
		
		Polygons p = new Polygons();
		
		System.out.println(p.area(3.5)); // Circle
		System.out.println(p.area(3)); // Square
		System.out.println(p.area(3,5)); // Rectangle
		
		Polygons.Parameter();
	}
}
