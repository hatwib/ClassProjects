package Enhancement;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

import javax.imageio.ImageIO;


public class Improve
{


	static int[][] arry;
	static int[][] v_arry;
	static boolean gray1 = true;
	static boolean gray2 = false;
	static boolean gray3 = false;
	static boolean sce = false;
	
	
	
	
	static boolean gamma_corr = true;
	static boolean gamma_corr_gray = false;


	public static void main( String arg[] ) throws Exception
	{


		String args = "D:/DF/images/sample9.jpg";


		String inputFileName = args;
		String outputFileName = "D:/DF/images/output/sample9Delta.jpg";
		double delta = 0.1;
		double gamma = 0.3;



		improveImage( inputFileName, outputFileName, delta, gamma );
	}

	public static boolean improveImage( String inputFileName, String outputFileName, double delta, double gamma) throws Exception
	{
		int[] dimimage;
		System.gc();
		System.out.println("Reading input file.");

		if(gamma_corr){
			BufferedImage outputFile = MyReader.convertRGBtoGamma(inputFileName,gamma);
			File file = new File(outputFileName.replace(".jpg","Gamma")+".jpg");
			ImageIO.write( outputFile, "jpg", file );
		}
		if(gamma_corr_gray)
			inputFileName = outputFileName.replace(".jpg","Gamma")+".jpg";

		dimimage = MyReader.imgDimension(inputFileName);
		System.out.println("Image Size " +  dimimage[1] + " x " + dimimage[2]);
		int N = dimimage[1];        
		int M = dimimage[2];
		int[][][] X = MyReader.getRGB(inputFileName);
		if(gray3)
			X =  MyReader.convertRGBtoLuminosity(inputFileName);
		if(gray1 || gray2){
			int mean = 0;

			for(int k = 0; k < X[0].length; k++){ // height	
				for(int l = 0; l < X[0][k].length; l++){ //width

					if(gray1)
						mean = findImageLight(X[0][k][l],X[1][k][l],X[2][k][l]);
					else if(gray2)
						mean = findImageAverage(X[0][k][l],X[1][k][l],X[2][k][l]);
					X[0][k][l] = (int) (mean);
					X[1][k][l] = (int) (mean);
					X[2][k][l] = (int) (mean);

				}
			}
		}
		else if(sce){
			for(int c = 0; c < dimimage[0]; c++){
				v_arry = new int[N][M];
				arry = X[c];



				for(int i = 0; i < N ;i++){ // height
					for(int j = 0;j < M;j++){ // width

						if(v_arry[i][j] == 0){
							v_arry[i][j] = 1;
							int m = X[c][i][j];
							if(j < M-1)
								j++;
							else if(i < N-1)
								i++;
							else
								break;
							int[] r = findUnion(i,j,delta,m,1);

							int mean = (int)((double)r[0]/(double)r[1]);

							for(int k = 0; k < N; k++){ // height	
								for(int l = 0; l < M; l++){ //width
									if(v_arry[k][l] == 1){
										v_arry[k][l] = -1;
										X[c][k][l] = (int) (mean + 0.05*(X[c][k][l] - mean));
										arry[k][l] = X[c][k][l]; 
									}else if(v_arry[k][l] == 2){
										v_arry[k][l] = 0;
									}

								}
							}
						}

					}

				}
			}
		}




		if (!MyWriter.writeImage( inputFileName, outputFileName, X ))
			return false;

		System.out.println("DONE");

		return true;
	}

	public static int findImageLight(int x1, int x2, int x3){
		int[] x = {x1,x2,x3};
		Arrays.sort(x);


		return (int) (x[0]+x[2])/2;
	}


	public static int findImageAverage(int x1, int x2, int x3){
		return (int) (x1+x2+x3)/3;
	}

	public static int[] findUnion(int i, int j, double tol, int mn, int cnt){


		//		if(v_arry[i][j]==0){
		v_arry[i][j] = 2;
		int m = (int)(mn/cnt);
		if((m == 0 && Math.sqrt(Math.pow((m-arry[i][j]),2))/(m+1) <= tol) || (m > 0 && Math.sqrt(Math.pow((m-arry[i][j]),2))/m <= tol) ){
			v_arry[i][j] = 1;		
			mn += arry[i][j];
			cnt++;
			//				int n = 8;
			//				while(n > 0){
			//					n--;
			if(arry[i].length-1 > j && v_arry[i][j+1]==0){
				int[] r = findUnion(i, j+1,tol,mn,cnt); // E
				mn = r[0];
				cnt = r[1];
			}
			if(arry.length-1 > i && arry[i].length-1 > j && v_arry[i+1][j+1]==0){
				int[] r= findUnion(i+1, j+1,tol,mn,cnt); // SE
				mn = r[0];
				cnt = r[1];
			}
			if(arry.length-1 > i && v_arry[i+1][j]==0){
				int[] r= findUnion(i+1, j,tol,mn,cnt); // S
				mn = r[0];
				cnt = r[1];
			}
			if(arry.length-1 > i && j > 0 && v_arry[i+1][j-1]==0){
				int[] r= findUnion(i+1, j-1,tol,mn,cnt); // SW
				mn = r[0];
				cnt = r[1];
			}
			if(j > 0 && v_arry[i][j-1]==0){
				int[] r= findUnion(i, j-1,tol,mn,cnt); // W
				mn = r[0];
				cnt = r[1];
			}
			if(i > 0 && j > 0 && v_arry[i-1][j-1]==0){
				int[] r= findUnion(i-1, j-1,tol,mn,cnt); // NW
				mn = r[0];
				cnt = r[1];
			}
			if(i > 0 && v_arry[i-1][j]==0){
				int[] r= findUnion(i-1, j,tol,mn,cnt); // N
				mn = r[0];
				cnt = r[1];
			}
			if(i > 0 && arry[i].length-1 > j && v_arry[i-1][j+1]==0){
				int[] r= findUnion(i-1, j+1,tol,mn,cnt); // NE
				mn = r[0];
				cnt = r[1];

			}
			//					else{
			//						//						if(n == 7)
			//						//							System.out.println("All closed..");
			//						n = 0;
			//					}
			//				}
			//			}
		}

		return new int[]{mn,cnt};	

	}


	public static int[] findEdge(int[][] arry, int si, int sj, double tol ){
		int[] r;
		double mn = arry[0][0];
		double mn_cnt = arry[0][0];
		int i=si, j=sj, l,k,n=arry.length,m=arry[i].length,cnt=1;
		boolean chki = true;

		boolean chkj = false;
		for(k = i,l=j; (chki || chkj) && (i <  n-1 && j < m-1); ){
			if(chki){
				if(Math.sqrt(Math.pow((mn-arry[i+1][j]),2))/mn <= tol ){
					i++;
					cnt++;
					mn_cnt += arry[i+1][j];
					mn = mn_cnt/cnt;
					if(i == n){
						j++;
						l++;
						chki = false;
						chkj = true;
					}
				}else{
					n = i;
					i=si;	
					k = n;
					j++;
					chki = false;

					chkj = true;
				}
			}
			else if(chkj){
				if(Math.sqrt(Math.pow((mn-arry[i][j+1]),2))/mn <= tol ){
					chki = true;

				}
				chkj = false;
			}
		}

		r = new int[]{k,l,(int)mn};

		return r;
	}
}
