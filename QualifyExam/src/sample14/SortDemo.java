package sample14;

public class SortDemo {

	public static void main(String[] args) {
//		int[] x = {95,7,9,2,85,8,1,23,5,77,3,1,-4};
		int[] x = {7,2,3,8,5,19,6,9,7};
		
		Sort s = new Sort(x);
		s.print();
		
		System.out.println();
		
		s.QuickSort();
		
		s.print();

	}

}
