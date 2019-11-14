import java.util.Scanner;


public class SquareDemo {
	
	public static void main(String[] arg){
		int i = 0;
//		String a = "abc";
//		String b;
//		
//		Scanner sc = new Scanner(System.in);
//		
//		b = sc.next();	
//		System.out.println(a.equals(b)); 
		
		Square sqr = new Square(2,6); //sqr instance of an object Square
		
		int a[] = {3,5,6,7};
		
		System.out.println("Array is "+a[2]);
		
		sqr.passByReference(a);
		
		System.out.println("Array is "+a[2]);

		
		System.out.println(sqr.area());
		
		System.out.println(sqr.perimeter());
		
		sqr.setWidth(4);
		
		System.out.println(sqr.getWidth());
		
		
		Rectangle rec = new Rectangle(9,10);
		
		System.out.println("Square Hype = "+sqr.hype());
		System.out.println("Rectangle Hype = "+rec.hype());
		
		
		Circle cir = new Circle();
		
		System.out.println(cir.area());
	//	cir.setRadius(10);
		System.out.println(cir.area());
		
		
		
	}

}
