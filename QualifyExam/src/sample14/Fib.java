package sample14;

public class Fib {
	
	public static void main(String[] arg){
		System.out.print(Fibonacci(1));
	}
	
	public static int Fibonacci(int n){
		if(n <= 0)
			return 0;
		else if(n <= 2)
			return 1;
		else
			return Fibonacci(n-2) + Fibonacci(n-1);
	}

}
