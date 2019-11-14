package sanple122;

import java.util.ArrayList;
import java.util.LinkedList; 
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class DoubleLinkedList {

	private Node head;

	public class Node{
		private int data;
		private Node link;
		
		public Node(int i, Node n){
			data = i;
			link = n;
		}
	}
	public DoubleLinkedList(){
		head = null;
	}
	
	public void addFront(int x){
		Node newNode = new Node(x,head);
		head = newNode;
	}
	
	public void addBack(int x){
		Node newNode = new Node(x,null);
		if(head== null)
			head = newNode;
		else{
			Node tmp = head;
			while(tmp.link != null) // 
				tmp = tmp.link;  // search in next pointer
			
			tmp.link = newNode; //add element at the end
		}
	}

	public void addBothSides(int item){
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

	public static Integer getNElement(Set<Integer> collections){
		Integer ret = null;
		int n = collections.size();
		if(n >= 3){
			Integer ary[] = (Integer[]) collections.toArray(new Integer[collections.size()]);
			Arrays.sort(ary);
			ret = ary[n-4];
		}
		return ret;
	}	
	
	static Queue<Integer> get2Smallest(HashSet<Integer> set){
		Queue<Integer> ret = new LinkedList<Integer>();
		Iterator i = set.iterator();
		int nLarge = 0;
		while(i.hasNext()){
			int m = (Integer)i.next();
			if(!ret.contains(m)){
				if(ret.size() < 2){
					if(m < nLarge){
						nLarge = m;
						Integer n = ret.poll();
						ret.add(m);
						ret.add(n);
					}	
				}else{
					ret.add(m);
					nLarge = m;
				}
					
			}	
		}	
		return ret;
	}
	public static int Fib(int n){
		if(n <= 1)
			return n;
		else
			return Fib(n-1) + Fib(n-2);
	}
	
	public static List<Integer> Fibbo(int n, int i, List<Integer> r){
		if(n >= 1){
			r.add(i);
			r = Fibbo(n-i,++i,r);
		}
		return r;
			
	}
	public static void main(String[] arg){
		
		int bit = 8;
		int bit1 = 3;
		int bit2 = 5;

		while(bit2 != 0){
			int carry = bit1 & bit2;
			bit1 = bit1 ^ bit2;
			bit2 = carry << 1;
		}
		
		bit1 = Fib(6);
		List x = new ArrayList<Integer>();
		x = Fibbo(6,1, x);
		System.out.println(bit1);
		System.out.println(bit2);

		Set<Integer> t = new HashSet<Integer>();
		t.add(4);
		t.add(34);
		t.add(14);
		t.add(2);
		t.add(1);
		t.add(45);
		t.add(8);
		System.out.println(getNElement(t));

		DoubleLinkedList q = new DoubleLinkedList();
		
		q.addBothSides(3);
		q.addBothSides(4);
		q.addBothSides(7);
		q.addBothSides(9);
		q.addBothSides(4);
		q.addBothSides(10);
		
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
