
public class Loop {
	public static void main(String[] arg){
		
		int cnt = 0;
		
		while(cnt <= 10){ // <check value(boolean)>, if true exe
			System.out.println("First While Loop "+ cnt);
			cnt++;
		}
		
		cnt = 0;
		
		do{
			System.out.println("Second While Loop "+cnt);
			cnt++;
		}while(cnt <= 10);
		
		int j = 0;
		for( ; j <= 10;j++){
			if(j%2 != 0) //Even
				continue; // go to start of loop
			
			System.out.println("For Loop "+j);
		}
		
		
		int[] a = {4,6,2,7,9,3,1};
		
		for(int i = 0; i < a.length-1; i++){
			for(int k = i+1; k < a.length; k++){
				if(a[i] > a[k]){
					int tmp = a[i];
					a[i] = a[k];
					a[k] = tmp;
				}
			}
		}
		
		for(int i = 0; i < a.length; i++){
			System.out.print(a[i]+",");
		}
		
		
		
		
		
	}
	
	
}
