
public class HelloWorld {

	public static void main(String[] arg){
		
		char[] str1 = {'H','e','l','l','o'};
		
		String str2 = "Hello";
		
		String str3 = String.copyValueOf(str1);
		
		Integer[] a,d = new Integer[4],e = {3}; // Define
		
		int[] b = new int[10]; //Define and initialize all elements to 0
		
		int[] c = {3,4,6,7,2,5,7}; // Define and initialize
		
//		Squire s = new Squire();
//		
		// +
 		// -
		// *
		// /
		// %
		str2 = "10";
		
		int x = 2, y = 6;
		int z;
		z = x%y;
		System.out.println(z);
		System.out.println(3 + (y * x % 2 *2) + 5);
		
		str3 = str2 + (x+y * x % y); //B Off Div Mult Mod Add Sub 
		
		// Automatic type casting to String
		System.out.println(str3);
		
		x = Integer.parseInt(str2) + 5;
		
		x -= 4; // x = x - 4;
 		
 		
		
		x -= 1; // x--;
		
		y --; // y = y - 1;
		x = 10;
		
		x /= 5; // x = x / 5;
		System.out.println("divide "+x);
		
		
		while((--x) == 10){
			System.out.println("upper"+x);	
		}
		
		x = 10;
		while((x--) == 10){
			System.out.println("lower"+x);	
		}
		
		System.out.println("Hello World"+x);
	}

}
