package sanple122;

public class SnakeSnail {

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
	
	public SnakeSnail(){
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
		while(i <= size){
			if(tmp.tail == null)
				return true;
			tmp = tmp.tail;
			i++;
		}
		
		return false;
	}
	

	
}
