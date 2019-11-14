package MemorySimulator;

public class Cell {
	
	public boolean ready;
	
	private int index;
	private String instruction;
	private String instruction_address;
	private String result_address;
	private String operand1_address;
	private String operand2_address;
	private String opcode;
	private String result;
	private String operand1;
	private String operand2;
	
	
	public Cell(){
		this.index = 0;
		this.ready = true;
		this.instruction_address=null;
		this.instruction = null;
		this.opcode = null;
		this.operand1_address = null;
		this.operand2_address = null;
		this.result = null;
		this.operand1 =null;
		this.operand2 =null;
	}
	
	public void putIndex(int i) {
		this.index = i;
	}

	public void putInstruction(String pc, String i){
		this.instruction_address=pc;
		this.instruction=i;		
	}


	
	public void putOpcode(String o, String r) {
		this.opcode = o;
		this.result_address = r;
	}
	
	public void putOperand1_Address(String a) {
		this.operand1_address = a;
	}

	public void putOperand2_Address(String a) {
		this.operand2_address = a;
	}
	
	public void putResult(String r) {
		this.result = r;
	}
	
	public void putAllOperands(String r1, String r2) {
		this.operand1 =r1;
		this.operand2 =r2;	
	}

	public void putOperand1(String a) {
		this.operand1 = a;
	}

	public void putOperand2(String a) {
		this.operand2 = a;
	}
	
	public String getInstruction(){
		return instruction;
	}
	
	public String getAddress(){
		return instruction_address;
	}


	public String getResult_Address() {
		return result_address;
	}

	public String getOperand1_address() {
		return operand1_address;
	}

	public String getOperand2_address() {
		return operand2_address;
	}

	public String getOpcode() {
		return opcode;
	}

	public String getResult() {
		return result;
	}

	public String getOperand1() {
		return operand1;
	}

	public String getOperand2() {
		return operand2;
	}


	public int getIndex() {
		return index;
	}

	public void ClearCell() {
		this.instruction_address=null;
		this.instruction = null;
		this.opcode = null;
		this.operand1_address = null;
		this.operand2_address = null;
		this.result = null;
		this.operand1 =null;
		this.operand2 =null;
		this.ready = true;
		
	}








}
