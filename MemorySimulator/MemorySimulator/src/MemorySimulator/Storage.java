package MemorySimulator;

import java.util.ArrayList;


public class Storage {
	private ArrayList<Cell> cells = new ArrayList<Cell>();
	
	
	public Storage(int x){
		
		for(int i=0;i<x;i++){
			Cell temp_cell = new Cell();
			temp_cell.putIndex(i);
			cells.add(temp_cell);
		}
	}
	
	public Boolean WriteReady(int i){
		for(Cell c : cells){
			if(c.getIndex()==i)
				return c.ready;
			}
		return false;
	}
	
//	public ArrayList<Cell> getCellRange(int min, int max){
//		ArrayList<Cell> temp_storage = new ArrayList<Cell>();
//		for(Cell c : cells){
//			if(c.getIndex()>=min && c.getIndex()<=max)
//				temp_storage.add(c);
//		} 
//		return temp_storage;
//	}

	public  String getCellInstruction(int i){

		for(Cell c : cells){
			if(c.getIndex()==i){
				notifyAll();

				return c.getInstruction();
			}
		}
		return null;
		
	}

	public  void putCellInstruction(int i, String a, String b){
		for(Cell c : cells){
			if(c.getIndex()==i){
				c.ClearCell();
				c.putInstruction(a,b);
				
				break;
			}
		}

	}
	
	public  void putAddressOperand1(int i, String a){

		for(Cell c : cells){
			if(c.getIndex()==i){
				c.putOperand1_Address(a);
				break;
			}
		}
	}
	
	public  void putAddressOperand2(int i, String a){

		for(Cell c : cells){
			if(c.getIndex()==i){
				c.putOperand2_Address(a);
				break;
			}
		} 
	}
	
	public  void putOperand1(int i, String a){

		for(Cell c : cells){
			if(c.getIndex()==i){
				c.putOperand1(a);
				break;
			}
		}
	}
	
	public  void putOperand2(int i, String a){

		for(Cell c : cells){
			if(c.getIndex()==i){
				c.putOperand2(a);
				break;
			}
		}
	}
	
	public  void putAllOperand(int i, String a, String b){

		for(Cell c : cells){
			if(c.getIndex()==i){
				c.putAllOperands(a,b);
				break;
			}
		} 
	}
	
	public String[] decodeInstruction(int ins){

		String[] i = null;
		String instruction = "";
		for(Cell c : cells){
			if(c.getIndex()==ins){
				instruction = c.getInstruction();
				i = new String[] {instruction.substring(0, 4),instruction.substring(4, 8),instruction.substring(8, 12),instruction.substring(12, 16)};
				c.putOpcode(i[0],i[1]);
				return i;
			}
		}
		return null;
	}

	public  String getOpCode(int i) {
		for(Cell c : cells){
			if(c.getIndex()==i){
				return c.getOpcode();
			}
		}
		return null; 
	}

	public String getResult_Address(int i) {
		for(Cell c : cells){
			if(c.getIndex()==i){
				return c.getResult_Address();
			}
		}
		return null;
	}
	


	public String getOperand1_Address(int i) {
		for(Cell c : cells){
			if(c.getIndex()==i){
				return c.getOperand1_address();
			}
		}
		return null;
	}

	public String getOperand2_Address(int i) {
		for(Cell c : cells){
			if(c.getIndex()==i){
				return c.getOperand2_address();
			}
		}
		return null;
	}

	public String getOperand1(int i) {
		for(Cell c : cells){
			if(c.getIndex()==i){
				return c.getOperand1();
			}
		}
		return null;
	}
	
	public String getOperand2(int i) {
		for(Cell c : cells){
			if(c.getIndex()==i){
				return c.getOperand2();
			}
		}
		return null;
	}
	
	public String getResult(int i) {
		for(Cell c : cells){
			if(c.getIndex()==i){				
				return c.getResult();
			}
		}
		return null;
	}

	public void putResult(int i, String a) {
		for(Cell c : cells){
			if(c.getIndex()==i){
				c.putResult(a);
				break;
			}
		}
		
	}
	
	public void Clear() {
		for(Cell c : cells){
			c.ClearCell();
		}
		
	}

	public void moveNext(int i) {
	  	String instruction = null;
		String instruction_address = null;
		String result_address = null;
		String operand1_address = null;
		String operand2_address = null;
		String opcode = null;
		String result = null;
		String operand1 = null;
		String operand2 = null;
		for(Cell c : cells){
			if(c.getIndex()==i){
				c.ready = false;
				instruction = c.getInstruction();
				instruction_address = c.getAddress();
				result_address = c.getResult_Address();
				operand1_address = c.getOperand1_address();
				operand2_address = c.getOperand2_address();
				opcode = c.getOpcode();
				result = c.getResult();
				operand1 = c.getOperand1();
				operand2 = c.getOperand2();
				c.ClearCell();
				break;
			}
		}
		
		for(Cell c : cells){
			if(c.getIndex()==i+1){
				c.ClearCell();
				c.ready = false;
				c.putInstruction(instruction_address, instruction);
				c.putAllOperands(operand1, operand2);
				c.putOpcode(opcode, result_address);
				c.putResult(result);
				c.putOperand1_Address(operand1_address);
				c.putOperand2_Address(operand2_address);
				c.ready = true;
				break;
			}
		}
		
		
	}

//	public synchronized void ClearCells(int i) {
//	      while (available_ClearCells == false) {
//	          try {
//	             wait();
//	          }
//	          catch (InterruptedException e) {
//	          }
//	       }
//	      available_ClearCells = false;
//	      notifyAll();
//		Boolean cellFound = false;
//		for(Cell c : cells){
//			if(c.getIndex()==i){
//				c.ClearCell();
//				cellFound = true;
//				available_ClearCells = true;
//				notifyAll();
//				break;
//			}
//		}
//
//		if(!cellFound){
//			Cell temp_cell = new Cell();
//			temp_cell.putIndex(i);
//			cells.add(temp_cell);
//			available_ClearCells = true;
//			notifyAll();
//		}
//		
//	}
	
}
