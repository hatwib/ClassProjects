package sanple122;

public class LinkedList {
	private Node head;
	private Node tail;
	private int size = 0;
	public LinkedList(){
		head = null;
	}
	
	
	public void addFront(int x){
		Node newNode = new Node(x,head);
		head = newNode;
	}
	
	
	public void addStack(int x){
		Node newNode = new Node(x,head);
		head = newNode;
	}
	
	public void addQueue(int x){
		Node newNode = new Node(x,null);
		if(head== null)
			head = newNode;
		else{
			Node tmp = head;
			while(tmp.link != null)
				tmp = tmp.link;
			tmp.link = newNode;
		}
	}
	
	public void addBack(int x){
		Node newNode = new Node(x,null);
		if(head== null)
			head = newNode;
		else{
			Node tmp = head;
			while(tmp.link != null)
				tmp = tmp.link;
			tmp.link = newNode;
		}
	}

	public void remove(int item){
		if(head != null){
			Node childNode = head;
			if(head.data == item)
				head = head.link;
			else{
				Node parentNode = head;
				while(childNode != null){
					if(childNode.data == item)
						parentNode.link = childNode.link;
					parentNode = childNode;
					childNode = childNode.link;
				}
			}
		}
	}
	
	public void doubleQueue(int item){
		addFront(item);
		addBack(item);
		
	}
	
	public void addDifferentElements(int elem1, int elem2){
		
		addFront(elem1);
		addBack(elem2);
	}
	
	public void deleteBothSides(){
		deleteFront();
		deleteBack();
	}
	
	public void deleteFront(){
		if(head != null){
			head = head.link;
		}
	}

		public void deleteBack(){
			if(head != null){
				Node tmp = head;
				while(tmp.link.link != null)
					tmp = tmp.link;
				tmp.link = null;
			}
	}
	
	public void popStack(){
		if(head != null){
			if(head.link == null)
				head = null;
			else{
				head = head.link;
			}
		}
	}
	
	public void popQueue(){
		if(head != null){
			if(head.link == null)
				head = null;
			else{
				head = head.link;
			}
		}
	}
	
	public class Node{
		private int data;
		private Node link;
		
		public Node(int i, Node n){
			data = i;
			link = n;
		}
	}
	
	public void print(){
		Node tmp = head;
		int n = 0;
		while(tmp != null){
			System.out.print(tmp.data +" ");
			tmp = tmp.link;
			n++;
		}
		System.out.print("Size is "+n);
	
	}

}
