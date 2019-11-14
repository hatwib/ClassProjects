package sample14;

public class Sort {

	int[] a;
	static int w;
	
	public Sort(int[] b){
		a = b;
	}
	
	public void BubbleSort(){
		for(int j = a.length -1; j > 0 ; j--){
			for(int i = 0; i < j; i++){
				if(a[i] > a[i+1]){
					int tmp = a[i+1];
					a[i+1] = a[i];
					a[i] = tmp;
				}
			}
		}
	}
		
	public void SelectionSort(){
		for(int j = 0; j < a.length; j++){
			int pos = j;
			
			for(int i = j+1; i < a.length; i++){
				if(a[i] < a[pos]){
					pos = i; //change position to index of new minimum value
				}
			}
			
			int tmp = a[pos];
			a[pos] = a[j];
			a[j] = tmp;
			
		}
	}
	
	
	public void MergeSort(){
		a = Divide(a);
	}
	
	public int[] Divide(int[] ary){
		
		if(ary.length > 1){
			int mid = ary.length/2;
			int[] ary1 = new int[mid];
			int[] ary2 = new int[ary.length - mid];
			int i1 = 0, i2=0;
			
			for(int i = 0; i < ary.length; i++){
				if(i < mid){
					ary1[i1] = ary[i];
					i1++;
				}else{
					ary2[i2] = ary[i];
					i2++;
				}
			}
			
			ary1 = Divide(ary1); // 2
			ary2 = Divide(ary2); // 2
			
			ary = Combine(ary1, ary2); // 4
		}
		return ary;
	}
	
	public int[] Combine(int[] ary1, int[] ary2){
		int[] result = new int[ary1.length + ary2.length];
		int i1 = 0, i2 = 0;
		
		for(int i = 0; i < result.length; i++){
			if( i2 == ary2.length || (i1 < ary1.length && ary1[i1] < ary2[i2])){
				result[i] = ary1[i1];
				i1++;
			}else if (i1 == ary1.length || (i2 < ary2.length && ary1[i1] >= ary2[i2])){
				result[i] = ary2[i2];
				i2++;
				}
		}
		return result;
	}
	
	public void print(){
		for(int i = 0; i < a.length; i++)
			System.out.print(a[i]+" ");
	}
	
	
	public void QuickSort(){
		DivideQuickSort(0,1,1,a.length-1,a.length-1);
	}
	
	public void DivideQuickSort(int pivot, int i, int left, int j, int right){
		
		if(i==j && a[pivot] > a[j]){
			SwipeQuickSort(pivot,i);
		}
		else if (i < j){
//		int i = left;
		for(; i < j; i++){
			if(a[i] > a[pivot])
				break; // Stop
		}
		
//		int j = right;
		for(; j > i; j--){
			if(a[j] < a[pivot])
				break; //Stop
		}
		
		if(i != j){
			SwipeQuickSort(i,j);
			DivideQuickSort(pivot, i, i, j, right);
		}else{
			SwipeQuickSort(pivot,i-1);
			DivideQuickSort(pivot, pivot+1, pivot+1, i-2, i-2);
			DivideQuickSort(i, i+1,i+1,right,right);
		}
		
		}
	}

	
	public void SwipeQuickSort(int left, int right){
		int tmp = a[left];
		a[left] = a[right];
		a[right] = tmp;
		
		print();
		System.out.println();
	}
	
	public void Insertion(){
	for(int i = 1; i < a.length; i++){
		int pos = i;
		int tmp_a = a[i];
		for(int j = i-1; j >= 0; j--){
			if(tmp_a < a[j]){
				pos = j;
			}
		}
		if(pos < i){
			for(int k = i; k > pos; k--){
				a[k] = a[k-1];
			}
			a[pos]  = tmp_a;
		}
	}
		
		
	}
	
}
