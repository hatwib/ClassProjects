package sanple122;

public class SnakeSnailDemo {
	
	public static void main(String[] arg){
		
		SnakeSnail ss = new SnakeSnail();
		int[] a = {3,5,7,12,9,2};
		ss.makeSnail(a);
		
		
		
		if(ss.isSnake())
			System.out.println("I am a Snake");
		else
			System.out.println("I am a Snail");
			
		
	}

}
