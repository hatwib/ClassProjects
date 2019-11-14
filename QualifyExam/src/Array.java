
public class Array {
	
	public static void main(String[] arg){
		
		int[] a = {1,2,3,4};
		int[] b;
		
		b = a; // passsing by reference
		
		a[0] = 10;
		
		
		System.out.println(b[0]);
		
		b[3] = 30;
		
		System.out.println(a[3]);
		
//		int[] c = new int[a.length];
//		
//		for(int i = 0; i < a.length; i++){ 
//			c[i] = a[i];
//		}
		
		int[] c = (int[]) a.clone();
		
		a[0] = 50;
		
		for(int i = 0; i < c.length; i++)
			System.out.println(" Array a " +a[i] + " and array c " + c[i]);
		
		
		
		int[][] x = new int[4][5]; // ragged array
		
		int[][] y = {
				{1,3,4},
				{4,5,6},
				{6,7,8}
				};
		
		
		int[][][][] z = new int[4][4][4][4];
		
		
		
		System.out.println(z[0][0][0]);
		
		int[][] y_tmp = (int[][])y.clone();
	
		transposeArray(y_tmp);

		
		System.out.println();
		for(int i = 0; i < y_tmp.length; i++){
			for(int j = 0; j < y_tmp[i].length; j++){
				System.out.print(y_tmp[i][j]+ " ");
			}
			System.out.println();
		}

		System.out.println();
		for(int i = 0; i < y.length; i++){
			for(int j = 0; j < y[i].length; j++){
				System.out.print(y[i][j]+ " ");
			}
			System.out.println();
		}		
		
		Object o;
		String s = "String";
		
		o = s;
		
				

	}
	
	public static void transposeArray(int[][] t){
		for(int i = 0; i < t.length; i++){
			for(int j = 0; j < t[i].length; j++){
				int t_tmp = t[j][i];
				t[j][i] = t[i][j];
				t[i][j] = t_tmp;
			}
		}

}
	

}
