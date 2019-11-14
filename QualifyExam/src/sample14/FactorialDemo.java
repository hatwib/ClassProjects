package sample14;

public class FactorialDemo {

	
	public static void main(String[] arg){
		
		System.out.println(Factorial(-2));
	}
	
	public static int Factorial(int n){
		if(n < 0){
			throw new IllegalArgumentException("Number is out of bounds");
		}else if(n == 0  || n == 1){
				return 1;
		}else{
				return n * Factorial(n - 1);
			}
			
	}
	
}
