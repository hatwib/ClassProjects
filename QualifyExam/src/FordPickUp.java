
public class FordPickUp extends Pickup implements Car,Train{

	public void drive(){
		System.out.print("Am driving");
	}
	
	public void stop(){
		System.out.print("I've stopped");
	}

	@Override
	public void veryfast() {
		System.out.print("Move Very fast");
		
	}
}
