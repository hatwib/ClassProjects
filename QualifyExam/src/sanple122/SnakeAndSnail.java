package sanple122;

public class SnakeAndSnail {
	

	private Node head;
	private int size;
	
	public class Node{
		private int data;
		private Node tail; // Pointer to the tail
		
		public Node(int i, Node pointer){ // Constructor
			data = i;
			tail = pointer;
		}
	}
	
	public SnakeAndSnail(){
		head = null;
		size = 0;
	}
	
	public void makeSnake(int[] a){
		for(int i = 0; i < a.length; i++){
			Node newNode = new Node(a[i],head);
			head = newNode;
			size++;
		}
	}

	public void makeSnail(int[] a){
		for(int i = 0; i < a.length; i++){
			Node newNode = new Node(a[i],head);
			head = newNode;
			int nodeCnt = 0;
			Node tmp = head;
			while(nodeCnt < size){
				tmp = tmp.tail;
				nodeCnt++;
			}
			
			tmp.tail = head;
			size++;			
		}
				

	}

	public boolean isSnake(){
		int i = 0;
		Node tmp = head;
		while(i < size){
			if(tmp.tail == null)
				return true;
			tmp = tmp.tail;
			i++;
		}
		
		return false;	
		
	}
	
	public static void main(String[] arg){
		
		SnakeAndSnail ss = new SnakeAndSnail();
		int[] a = {3,5,7,12,9,2};
		ss.makeSnail(a);
		
		if(ss.isSnake())
			System.out.println("I am a Snake");
		else
			System.out.println("I am a Snail");
		
		
		
	}



}
