
public class LinkedList {
	Node head;
	int size;
	
	public class Node{
		public Node tail;
		public int item;
		
		public Node(int i){
			item = i;
			tail = null;
		}
	}
	
	public void insertNode(int i){
		Node n = new Node(i);
		n.tail = head;
		head = n;
		size++;
	}
	
	public LinkedList(){
		head = null;
		size = 0;
	}
	
	public void printList(){
		Node tmp = head;
		int j = 0;
		while(j  < size)
		{
			System.out.println(tmp.item);
			tmp = tmp.tail;
			j++;
		}
	}
	
	public static void main(String[] arg){
		LinkedList l = new LinkedList();
		l.insertNode(3);
		l.insertNode(6);
		l.insertNode(8);
		
		l.printList();

		
	}

}
