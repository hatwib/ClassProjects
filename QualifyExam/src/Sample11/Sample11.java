package Sample11;

import java.util.Scanner;


public class Sample11 {

	public static void main(String[] arg) {
		try{
		Scanner scan = new Scanner(System.in);
		
		int i = 3;
		i = 3 / 0;
		int x = scan.nextInt();
		System.out.println(x);
		
		}catch(Exception e) {
			System.out.println("The input is not of type int, details below");
	
		}	
		
	}
}
