package com.hatwib.JDBCTester;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.Console;

public class InputFileDemo {

	/**
	 * @param args
	 */
	static BufferedReader reader;
	static String s;
	public static void main(String[] args)  throws Exception{
		// TODO Auto-generated method stub
		
		BufferedReader br = null;
		String line,input="",transaction="",inputKey="",inputText="",currentState="0";
		String fileContent = "",transactionKey="",transactionValue="",transactionValueSplit="";
		String nextState="",currentStateAndInputValue="";
		char inputValue[];
		int i=0,n;
		try {
			br = new BufferedReader(new FileReader("sample.txt"));
			
			while((line = br.readLine()) != null){		
				 fileContent  += line + "\n";
			}
			System.out.println("file values are: " +fileContent +"\n");
			StringTokenizer fileContentToken = new StringTokenizer(fileContent,"\n");
			while(fileContentToken.hasMoreTokens()){
				
				 input = fileContentToken.nextToken();
				 transaction = fileContentToken.nextToken();
				 System.out.println("input is --->" +input);
				System.out.println("transaction is --->" +transaction );
				//System.in.read();
			}
			
			StringTokenizer inputToken = new StringTokenizer(input,"=");
			while (inputToken.hasMoreTokens()){
				inputKey = inputToken.nextToken();
				inputText = inputToken.nextToken().trim();
				System.out.println("\n inputKey is --->"+inputKey);
				System.out.println("\n inputvalue is --->"+inputText);
				//System.in.read();
			}
			n = inputText.length();
			System.out.println("inputText length is -->"+n);
			System.out.println("\n************** Transaction Begins ***************");
			inputValue = inputText.toCharArray();
			//System.out.println("input value is-->"+ inputValue[0]);
			while(i <= n-1 && currentState != "h"){
				currentStateAndInputValue = currentState+","+inputValue[i];	
				StringTokenizer transactionToken = new StringTokenizer(transaction, "=d");
				while(transactionToken.hasMoreTokens()) {
				 
				 transactionKey = transactionToken.nextToken();
				 transactionValue = transactionToken.nextToken();
				 //System.out.println("transactionKey is -->"+transactionKey+"\n");
				 
				 if(transactionKey.contains(currentStateAndInputValue)){
					 
					 System.out.print(inputValue[i]);
					 Thread.sleep(1000);
					 System.out.print(currentStateAndInputValue);
					 Thread.sleep(1000);
					 StringTokenizer transactionValueToken = new StringTokenizer(transactionValue,"{}");
					 while(transactionValueToken.hasMoreTokens()){
						 transactionValueSplit = transactionValueToken.nextToken();
					 	String s2 = transactionValueToken.nextToken();
					 	Thread.sleep(1000);
					 	System.out.print(transactionValueSplit); 	
					 }
					 StringTokenizer transactionValueSplitToken = new StringTokenizer(transactionValueSplit,",");
					 while(transactionValueSplitToken.hasMoreTokens()){
						 nextState = transactionValueSplitToken.nextToken();
						 String output = transactionValueSplitToken.nextToken();
						 String movement = transactionValueSplitToken.nextToken();
						 Thread.sleep(1000);						 
						 System.out.print(nextState);
						 Thread.sleep(1000);
						 System.out.print(output);
						 //System.in.read();
						 if(movement.equalsIgnoreCase("R")){
							 movement = "Right";
						 }else if (movement.equalsIgnoreCase("L")) {
							 movement = "Left";
						}
						 Thread.sleep(1000);
						 System.out.print(movement);
						 
					 }
				 }
				 
				
				}
				currentState = nextState;
				i++;
			}
			br.close();
		} //catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//	//		e.printStackTrace();
//			System.out.println("Error is : "+e.getMessage());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
////			e.printStackTrace();
//			System.out.println("Error is : "+e.getMessage());
//		}
	 catch (Exception e) {
		// TODO Auto-generated catch block
//		e.printStackTrace();
		System.out.println("Error is : "+e.getMessage());
	}		

	}

}

