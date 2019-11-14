
public class Assigment1 {
	
	public static void main(String[] arg){
		double[] a = {1,2,3,4,5,6,7,8,9,10};
		
		for(int i = 0; i < a.length-1; i++){
			for(int k = i+1; k < a.length; k++){
				if(a[i] > a[k]){
					double tmp = a[i];
					a[i] = a[k];
					a[k] = tmp;
				}
			}
		}
		
		double ave = 0;
		
		for(int i = 0; i < a.length; i++){
			ave+= a[i];
		}
		
		ave = ave/a.length;
		
		double min = a[0];
		double max = a[a.length-1];
		
		
		for(int i = 0; i < a.length; i++){
			if(a[i] <= ave && a[i] > min )
				min = a[i];
			else if (a[i] >= ave && a[i] < max)
				max = a[i];
		}
		System.out.println(min);
		System.out.println(max);
		System.out.println(ave);
		for(int i = 0; i < a.length; i++){
			System.out.print(a[i]+",");
		}
		if(Math.abs(ave - min) < Math.abs(ave-max))
			System.out.println("\n"+min);
		else if(Math.abs(ave - min) > Math.abs(ave-max))
			System.out.println("\n"+max);
		else
			System.out.println("\n"+min+" and "+max);
		
	}

}
