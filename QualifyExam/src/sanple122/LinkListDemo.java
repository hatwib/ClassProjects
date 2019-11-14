package sanple122;

public class LinkListDemo {
	public static void main(String[] arg){

//		LinkedList l = new LinkedList();
//		
//		l.addStack(3);
//		l.addStack(5);
//		l.addStack(10);
//		l.addStack(9);
//		l.addStack(7);
//		l.addStack(1);		
//		l.print();
//		System.out.println();
//		
//		l.popStack();
//		l.popStack();
//
//		
//		l.print();
//		System.out.println();
//		
		LinkedList q = new LinkedList();
		
		q.doubleQueue(3);
		q.doubleQueue(4);
		q.doubleQueue(7);
		q.doubleQueue(9);
		q.doubleQueue(4);
		q.doubleQueue(10);
		
		q.addDifferentElements(44, 33);
		
		System.out.println("Queue");
		q.print();

		q.deleteBothSides();  // Deleting both sides at once
		q.deleteFront();
		q.deleteBack();

		System.out.println();
		q.print();		
	}

}
