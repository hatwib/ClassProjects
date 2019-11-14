package MemorySimulator;

import java.util.ArrayList;

public class Testmemory {
	static Storage register = new Storage(16);
	
	public static void main(String[] args){
//		register.putCellValue(1,"0000000000000");
//		register.putCellValue(2, toBinary(4,16));
//		register.putCellValue(3, toBinary(4,16));
//		register.putCellValue(4, toBinary(14,16));
		
		
//		ArrayList<Cell> c = register.getCellRange(1,4);
		
//		for(Cell b : c)
//			System.out.println(b.getAddress() +" "+b.getInstruction());
		
		
//		System.out.println(register.getCellValue(4));
		
		
	}

	public static String fromBoolean(Boolean[] b){
		String str = "";
		for(int i=0; i< b.length; i++)
			str+=(b[i]?"0":"1");
		return str;
	}
	

}
