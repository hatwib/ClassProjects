package sample12;

public class myClass implements myADT{
	
	@Override
	public void firstFunction() {
		System.out.println("First Code");
		
	}

	@Override
	public void secondFunction(int x, int y) {
		System.out.println("Second Code"+x + " and "+y);
	}

	@Override
	public int thirdFuction(int x, int y) {
		return x + y;
	}

}
