package MemorySimulator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;


public class Pipelines {
	public static Map<String, String> register = new HashMap<String, String>();
	public static Map<String, String> memory = new HashMap<String, String>();
	//		public static String[] buffer = new String[8];
	static Storage buffer = new Storage(16);
	public static Map<String, String> fetch_buffer =  new HashMap<String, String>();
	public static Map<String, String> decode_buffer = new HashMap<String, String>();
	public static Map<String, String> fetchoperand_buffer = new HashMap<String, String>();
	public static Map<String, String> execute_buffer = new HashMap<String, String>();

	public static Map<String, String> opcode = new HashMap<String, String>();
	public static int asm=0, asm_count = 0, inst_count = 0,fetch_count=0; 
	public static String PC = "15",line="",content="",fileName="div.txt", mem_highlight="",reg_highlight1="",reg_highlight2="",reg_highlight3="";
	public static int bite_size = 16;
	public static JTextArea Regtext;
	public static JTextArea Memtext;
	public static JTextArea ASMText;
	public static JTextArea ResText;
	public static JButton NextButton, LoadButton;
	public static JFrame f;
	public static boolean err = false,selectFile=false,can_fetch_Instruction = true;
	public static String operator[];
	public static String bitcode[];
	public static Boolean fetch_free = false,decode_free=false,fetchop_free=false,execute_free=false,writeback_free=false;
	private static boolean available = false;
	
	
	public static void main(String[] args) {
		

		
		JScrollPane vertical1,vertical2;
		operator = new String[]{"MOVE", "NOT", "AND", "OR", "ADD", "SUB", "ADDI", "SUBI", "SET", "SETH", "INCIZ", "DECIN", "MOVEZ", "MOVEX", "MOVEP", "MOVEN" };
		bitcode = new String[]{"0000","0001","0010","0011","0100","0101","0110","0111","1000","1001","1010","1011","1100","1101","1110","1111"};
		f = new JFrame("Single Scalar Micro Processor");
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize=new Dimension((int)(screenSize.width),(int)(screenSize.height));

		f.setBounds(0,0,frameSize.width,frameSize.height);
		JPanel upperPanel = new JPanel();
		JPanel middlePanel = new JPanel();
		JPanel bottomPanel = new JPanel();



		f.getContentPane().add(upperPanel, "North");
		f.getContentPane().add(middlePanel,"Center");
		f.getContentPane().add(bottomPanel, "South");

		Regtext=new JTextArea(content, 30, 22);
		Regtext.setLineWrap(false); //DWM 02-07-2012
		Regtext.setEditable(false); //DWM 02-07-2012

		Memtext=new JTextArea(content, 30, 20);
		Memtext.setLineWrap(false); //DWM 02-07-2012
		Memtext.setEditable(false); //DWM 02-07-2012

		ASMText=new JTextArea(content, 30, 15);
		ASMText.setLineWrap(false); //DWM 02-07-2012
		ASMText.setEditable(false); //DWM 02-07-2012

		ResText=new JTextArea(content, 5, 100);
		ResText.setLineWrap(false); //DWM 02-07-2012
		ResText.setEditable(false); //DWM 02-07-2012

		NextButton = new JButton("Next Step");
		NextButton.setVerticalTextPosition(AbstractButton.CENTER);
		NextButton.setHorizontalTextPosition(AbstractButton.LEADING);

		LoadButton = new JButton("Load File");
		LoadButton.setVerticalTextPosition(AbstractButton.CENTER);
		LoadButton.setHorizontalTextPosition(AbstractButton.LEADING);

		//		    upperPanel.add(new JScrollPane(Regtext));
		vertical1 = new JScrollPane(Regtext);
		vertical1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		upperPanel.add(vertical1);		    

		//		    upperPanel.add(new JScrollPane(Memtext));
		vertical2 = new JScrollPane(Memtext);
		vertical2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		upperPanel.add(vertical2);

		upperPanel.add(new JScrollPane(ASMText));
		//		    vertical3 = new JScrollPane(ASMText);
		//		    vertical3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//		    upperPanel.add(vertical3);


		middlePanel.add(NextButton);
		middlePanel.add(LoadButton);
		bottomPanel.add(new JScrollPane(ResText));

		NextButton.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent arg0)  {}

			public void mouseEntered(MouseEvent arg0)  {}

			public void mouseExited(MouseEvent arg0) {}

			public void mousePressed(MouseEvent arg0)  {}

			public void mouseReleased(MouseEvent arg0)  {
				if(!selectFile) {
					inst_count++;
//					fetchInstruction.start();
//					FetchInstruction(inst_count);
//					DecodeInstruction(inst_count);
//					FetchOperands(inst_count);
//					Execute(inst_count);
//					WriteBack(inst_count);
					
				}
			}
		});

		LoadButton.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent arg0)  {}

			public void mouseEntered(MouseEvent arg0)  {}

			public void mouseExited(MouseEvent arg0) {}

			public void mousePressed(MouseEvent arg0)  {}

			public void mouseReleased(MouseEvent arg0)  {
				if(selectFile){
					if(selectFile())
						loader();
				} }
		});

		NextButton.addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent e) {
				if(!selectFile) {
//					FetchInstruction(inst_count);
//					DecodeInstruction(inst_count);
//					FetchOperands(inst_count);
//					Execute(inst_count);
//					WriteBack(inst_count);
					
				}
			}

			public void keyReleased(KeyEvent e) {}

			public void keyTyped(KeyEvent e)  {}
		});
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				System.exit(0);
			}
			public void windowOpened( WindowEvent e){ 
				NextButton.requestFocus();
			} 
		});
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		ASMText.setText("Assembly Code(Click here to load file)\n");

		for(int a=0; a<16;a++){
			opcode.put(operator[a], bitcode[a]);
		}
		
		loader();
		fetch_free = true;
		FetchInstruction fetchInstruction = new FetchInstruction();
		DecodeInstruction decodeInstruction = new DecodeInstruction();
		FetchOperands fetchOperands = new FetchOperands();
		Execute execute = new Execute();
		WriteBack writeBack = new WriteBack();
		
		fetchInstruction.start();
		decodeInstruction.start();
		fetchOperands.start();
		execute.start();
		writeBack.start();
		
		
//		FetchInstruction(inst_count);
//		DecodeInstruction(inst_count);
//		FetchOperands(inst_count);
//		Execute(inst_count);
//		WriteBack(inst_count);
		

		

	}


	public static void loader(){
		BufferedReader br = null;
		String regString = "#",asmcode="";
		String regComment = "//";
		asm=0; asm_count = 0; inst_count = 0;
		memory.clear();
		register.clear();
		buffer.Clear();
		for(int i=0;i<bite_size;i++){
			register.put(toBinary(Integer.toString(i),bite_size), toBinary(Integer.toString(0),bite_size));
			memory.put(toBinary(Integer.toString(i),bite_size), toBinary(Integer.toString(0),bite_size));
		}
		for(int i=0; i<2; i++){
			fetch_buffer = new HashMap<String, String>();
			decode_buffer = new HashMap<String, String>();
			fetchoperand_buffer = new HashMap<String, String>();
			execute_buffer = new HashMap<String, String>();

		}

		if(!err)
			try {
				br = new BufferedReader(new FileReader(fileName)); 
				ASMText.setText("Assembly Code\n");
				selectFile=false;
				err=false;
				can_fetch_Instruction=true;

				ResText.setText("");
				ResText.setBackground(Color.white);

				memory.put(toBinary(PC,bite_size), toBinary(PC,bite_size));

				while((line = br.readLine()) != null){
					asmcode = line.replaceAll("\\s+", " ").trim(); // Replace multiple whitespace with single whitespace/ trim sides
					int cmt_position = (asmcode.indexOf(regComment)>=0?asmcode.indexOf(regComment):asmcode.length());
					asmcode = asmcode.substring(0,cmt_position); // remove commented limes
					if(asmcode.trim().length() > 0) ASMText.append(asmcode+"\n");
					if(asmcode.startsWith(regString) && asmcode.split("=").length == 2){
						int cmt = (asmcode.split("=")[1].indexOf(regComment)>=0?asmcode.split("=")[1].indexOf(regComment):asmcode.split("=")[1].length());
						String param1 = asmcode.split("=")[0].replace(regString, "").trim();
						String param2 = asmcode.split("=")[1].substring(0,cmt).trim();
						if(param2.equals("asm")){
							if(isInteger(param1)){
								asm = Integer.parseInt(param1);
								asm_count = asm;
								for(int i=asm;i<asm+20;i++){
									memory.put(toBinary(Integer.toString(i),bite_size), toBinary(Integer.toString(0),bite_size));
								}
							}
						}else{
							memory.put(toBinary(param1,bite_size), toBinary(param2,bite_size));

						}
					}else {
						String param1 = asmcode.replaceAll("\\s+", " ").split(" ")[0].trim();
						if(opcode.containsKey(param1)){
							asmcode=asmcode.substring(param1.length(), asmcode.length()).replaceAll(",", " ").replaceAll("\\s+", " ").trim();
							String param2[] = asmcode.split(" ");
							if(ErrorCkeck(param1,param2)){
								String byteCode = makeByteCode(param1,param2);
								memory.put(toBinary(Integer.toString(asm++),bite_size),byteCode);
							}
						}else if(!(asmcode.length() == 0))
							PrintError("Unknown Opcode in instruction '"+line+"' ");
					}

				}


				br.close();
				for(int x=0;x<16;x++){
					if(memory.containsKey((toBinary(Integer.toString(x),bite_size)))) 
						register.put(toBinary(Integer.toString(x),bite_size), memory.get(toBinary(Integer.toString(x),bite_size)));
				}
				//				FetchInstruction(0);
				//				Execute(0);
				//				FetchInstruction(1);
				//				Execute(1);


			} catch (FileNotFoundException e) {
				PrintError(e.getMessage());
				ASMText.setText("Assembly Code(Click here to load file)\n");
				if(selectFile())
					loader();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				PrintError(e.getMessage());
			}
	}

	private static boolean ErrorCkeck(String arg1, String[] arg2){
		// Semantic Error Check
		if(arg2.length < 2 && arg2.length > 3){
			if(!(arg1.equals("MOVE") && arg2.length==1)){
				PrintError("Unknown Operands in instruction '"+line+"' ");
				return false;
			}
		}else{
			if((!arg2[0].trim().matches("R[0-9]+|PC")) || ((arg1.equals("NOT")||arg1.equals("AND")||arg1.equals("OR")||arg1.equals("ADD")||arg1.equals("SUB")||arg1.equals("ADDI")||arg1.equals("SUBI")) && !arg2[1].trim().matches("R[0-9]+|PC"))){
				PrintError("Unknown Register Number in instruction '"+line+"' ");
				return false;
			}else if((!arg2[0].trim().equals("PC") && Integer.parseInt(arg2[0].trim().replace("R", ""))>15) || (arg2.length > 1 && arg2[1].startsWith("R") && Integer.parseInt(arg2[1].trim().replace("R", ""))>15) || (arg2.length==3 && arg2[2].startsWith("R") && Integer.parseInt(arg2[2].trim().replace("R", ""))>15)){
				PrintError("Invalid Register Number in instruction '"+line+"' , can not assign Register greater than 15");
				return false;
			}else if(arg1.equals("ADDI") || arg1.equals("SUBI")){
				if(arg2.length==2){
					PrintError("Invalid Number of Operands in instruction '"+line+"' ");
					return false; // Check Semantic
				}else if(!isInteger(arg2[2])){
					return false; // Check Semantic
				}else if((Integer.parseInt(arg2[2]) < -8 || Integer.parseInt(arg2[2]) > 7)){
					PrintError("Operand expects an 4bit binary singed number in line '"+line+"' between -8 to 7");
					return false;
				}
			}else if(arg1.equals("SET") || arg1.equals("SETH")){
				if(arg2.length==3 || !isInteger(arg2[1]) || (Integer.parseInt(arg2[1]) < -128 || Integer.parseInt(arg2[1]) > 127)){
					PrintError("Operand expects an 8bit binary singed number in line '"+line+"' between -128 to 127");
					return false;
				}
			}
		}
		return true;
	}

	private static boolean selectFile()
	{
		JFileChooser fileDialog = new JFileChooser();
		int openChoice = fileDialog.showOpenDialog(f);
		if (openChoice == JFileChooser.APPROVE_OPTION)
		{
			//Put open file code in here
			File openFile = fileDialog.getSelectedFile();
			fileName = openFile.getPath();
			err=false;
			ResText.setBackground(Color.white);
			return true;
		}
		return false;
	}
	public static void canFetch(){
		//			 (register.get(toBinary(PC,bite_size)).equals(toBinary(PC,bite_size))) ||  (!memory.containsKey(register.get(toBinary(PC,bite_size)))) || 
		if(Integer.parseInt(fromBinary(register.get(toBinary(PC,bite_size)),bite_size)) < 15)
			can_fetch_Instruction = false;
	}

	
	static class FetchInstruction extends Thread {
	    		public synchronized void FetchInstruction(int bf){
		      while (available == false) {
		          try {
		             wait();
		          }
		          catch (InterruptedException e) {
		          }
		       }
		      available = false;
			if(!err){
				canFetch();
				if(can_fetch_Instruction){
					buffer.moveNext(bf);
					bf++;
					
					content="";
					Regtext.setText("          Register\n"+writeToRegister());
					Memtext.setText("          Memory\n"+writeToMemory());
					if(mem_highlight.length() > 0) highlight(Memtext,mem_highlight);
					if(reg_highlight1.length() > 0) highlight(Regtext,reg_highlight1);
					if(reg_highlight2.length() > 0) highlight(Regtext,reg_highlight2);
					if(reg_highlight3.length() > 0) highlight(Regtext,reg_highlight3);
					buffer.moveNext(bf);
					bf++;
					if(memory.containsKey(toBinary(Integer.toString(asm_count+fetch_count),bite_size))) ResText.setText("Register Content: \nInstruction: "+makeASMCode(memory.get(toBinary(Integer.toString(asm_count+fetch_count),bite_size))));
					//					Execute(memory.get(register.get(toBinary(PC,bite_size))));  ///////////////  Execute removed from here
					

					buffer.putCellInstruction(bf,toBinary(Integer.toString(asm_count+fetch_count),bite_size),memory.get(toBinary(Integer.toString(asm_count+fetch_count),bite_size)));
				}else{
					@SuppressWarnings("rawtypes")
					Iterator k = register.keySet().iterator();
					while(k.hasNext()){
						String key = (String) k.next();
						memory.put(key, (String) register.get(key));
					}
					Memtext.setText("          Memory(Write Back Register to Memory)\n"+writeToMemory());
	
					if(!selectFile) ASMText.append("(Click here to load file)\n");
					selectFile=true;
					ResText.setText("                                                  No More Instructions...........................PROGRAM EXITED!\n");
					ResText.setBackground(Color.yellow);
				}
			}
			
		}
		

		
		public void run() {
			try {
				while(true){
					if(buffer.WriteReady(0) && buffer.WriteReady(1)){
						available = true;
						FetchInstruction(0);
						fetch_count++;
					}
				}
			
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				notifyAll();
			
		}
	}

	static class FetchOperands extends Thread {
		
		public synchronized void FetchOperands(int bf){
		      while (available == false) {
		          try {
		             wait();
		          }
		          catch (InterruptedException e) {
		          }
		       }
		       available = false;
			buffer.moveNext(bf);
			bf++;
			
			String code = buffer.getOpCode(bf);
			String r1 = buffer.getOperand1_Address(bf);
			String r2 = buffer.getOperand2_Address(bf);

			
			if(code.substring(0, 3).equals("000")){
				buffer.putOperand1(bf,register.get("000000000000"+r1));
			}else if(code.substring(0, 3).equals("011")){
				buffer.putOperand1(bf,register.get("000000000000"+r1));
				buffer.putOperand2(bf,buffer.getOperand2_Address(bf));
			}else if(code.substring(0, 3).equals("101")){
				buffer.putOperand1(bf,buffer.getOperand1_Address(bf));
				buffer.putOperand2(bf,register.get("000000000000"+r2));
				//buffer.putResult(bf,register.get("000000000000"+buffer.getResult_Address(bf)));
			}else if(code.substring(0, 3).equals("100")){
				if(code.equals("1001"))
					buffer.putOperand1(bf,r1+register.get("000000000000"+r2).subSequence(8, 16));
				else
					buffer.putOperand1(bf,"00000000"+r1);
			}else{
				buffer.putOperand1(bf,register.get("000000000000"+r1));
				buffer.putOperand2(bf,register.get("000000000000"+r2));
			}
			notifyAll();
		}
		
		public void run() {
			try {
				while(true)
					if(buffer.WriteReady(2) && buffer.WriteReady(3)){
						available = true;
						FetchOperands(2);
					}
				//fetch_free = false,decode_free=false,fetchop_free=false,execute_free=false,writeback_free=false;
			
			} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	}

	static class DecodeInstruction extends Thread{
		
		public synchronized void DecodeInstruction(int bf){
		      while (available == false) {
		          try {
		             wait();
		          }
		          catch (InterruptedException e) {
		          }
		       }
		       available = false;
			buffer.moveNext(bf);
			bf++;
			String[] i = buffer.decodeInstruction(bf);

			if(i[0].substring(0, 3).equals("000")){
				buffer.putAddressOperand1(bf,i[3]);
			}else if(i[0].substring(0, 3).equals("011")){
				buffer.putAddressOperand1(bf,i[2]);
				buffer.putAddressOperand2(bf, "000000000000"+i[3]);
			}else if(i[0].substring(0, 3).equals("101")){
				buffer.putAddressOperand1(bf, "000000000000"+i[2]);
				buffer.putAddressOperand2(bf,i[3]);
			}else if(i[0].substring(0, 3).equals("100")){
				buffer.putAddressOperand1(bf, i[2]+i[3]);
			}else{
				buffer.putAddressOperand1(bf,i[2]);
				buffer.putAddressOperand2(bf,i[3]);
			}
			
			notifyAll();
		}
		
		public void run() {
			try {
				while(true)
					if(buffer.WriteReady(1) && buffer.WriteReady(2)){
						available = true;
						DecodeInstruction(1);
					}
				//fetch_free = false,decode_free=false,fetchop_free=false,execute_free=false,writeback_free=false;
					
			
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}

	}
	
	private static String writeToMemory(){
		mem_highlight = "";
		Map<Integer, String> map = new TreeMap<Integer, String>(new Comparator<Integer>() { public int compare(Integer first, Integer second){return first.compareTo(second);}});
		@SuppressWarnings("rawtypes")
		Iterator k = memory.keySet().iterator();
		String str="";

		while(k.hasNext()){
			String key = (String) k.next();
			//				if(Integer.parseInt(fromBinary(register.get(toBinary(PC,bite_size)),16)) <= Integer.parseInt(fromBinary("0"+key,17)) && Integer.parseInt(fromBinary(register.get(toBinary(PC,bite_size)),16))+20 >= Integer.parseInt(fromBinary("0"+key,17)) ){

			str=String.format("%-16s","Address "+ fromBinary("0"+key,bite_size+1))+(String) memory.get(key);
			if(can_fetch_Instruction && register.get(toBinary(PC,bite_size)).equals(key)){
				//+makeASMCode(memory.get(register.get(toBinary(PC,bite_size))))+"\n";
				mem_highlight = str;
			}
			str+="\n";
			map.put(Integer.parseInt(fromBinary(key,bite_size)), str);

		}
		//		}
		str="";

		k = map.keySet().iterator();
		while(k.hasNext()){
			int key = (Integer) k.next();
			str+=(String) map.get(key);
		}
		return str;
	}

	private static String writeToRegister(){
		reg_highlight1 = "";reg_highlight2 = "";reg_highlight3 = "";
		Map<Integer, String> map = new TreeMap<Integer, String>(new Comparator<Integer>() { public int compare(Integer first, Integer second){return first.compareTo(second);}});
		@SuppressWarnings("rawtypes")
		Iterator k= register.keySet().iterator();
		String str="";

		while(k.hasNext()){
			String key = (String) k.next();
			str=String.format("%-16s","Address "+ fromBinary(key,bite_size)) + (String) register.get(key)+ " = "+ fromBinary((String) register.get(key),bite_size);
			if(memory.get(register.get(toBinary(PC,bite_size))) == null) memory.put(register.get(toBinary(PC,bite_size)), String.format("%16s","0").replace(' ', '0'));
			boolean ck1 = !(memory.get(register.get(toBinary(PC,bite_size))).substring(1,3).equals("00"));
			boolean ck2 = !(memory.get(register.get(toBinary(PC,bite_size))).substring(0,3).equals("011"));
			if(memory.get(register.get(toBinary(PC,bite_size))).substring(4, 8).equals(key.substring(12, 16))){
				//					str+="<"; //===(OUTPUT) R"+fromBinary(key,bite_size)+"\n";
				reg_highlight1 = str;
			}else if(ck1 && (memory.get(register.get(toBinary(PC,bite_size))).substring(8, 12).equals(key.substring(12, 16)))){
				//					str+=">"; //(INPUT) R"+fromBinary(key,bite_size)+"\n";
				reg_highlight2 = str;
			}else if(ck1 && ck2 && (memory.get(register.get(toBinary(PC,bite_size))).substring(12, 16).equals(key.substring(12, 16)))){
				//					str+=">"; //(INPUT) R"+fromBinary(key,bite_size)+"\n";
				reg_highlight3 = str;
			}

			str+="\n";
			map.put(Integer.parseInt(fromBinary(key,bite_size)), str);
		}
		str="";

		k = map.keySet().iterator();
		while(k.hasNext()){
			int key = (Integer) k.next();
			str+=(String) map.get(key);
		}

		return str;

	}

	static class Execute extends Thread{
		private synchronized void Execute(int bf) {
		      while (available == false) {
		          try {
		             wait();
		          }
		          catch (InterruptedException e) {
		          }
		       }
		       available = false;
			buffer.moveNext(bf);
			bf++;
			String code = buffer.getOpCode(bf);
			//			String[] arg = {buffer.getOpCode(bf),string.substring(8,12),string.substring(12,16)};
			try{
				//			takeActions(param1,param2);

	
				String Rd = "";
	
	
				
				
				if(code.equals("0000")){ // Rd <- Ra //MOVE 
					Rd = buffer.getOperand1(bf); //register.get(toBinary(fromBinary(arg[2],bite_size),bite_size)); // Changed
				}else if(code.equals("0001")){ //NOT
					StringBuilder a = new StringBuilder();
					char[] c = buffer.getOperand1(bf).toCharArray(); //register.get(toBinary(fromBinary(arg[2],bite_size),bite_size)).toCharArray();
					for(char d : c)
						if(d==48)
							a.append(1);
						else
							a.append(0);
					String e=a.toString();
					Rd = e;
				}else if(code.equals("0010")){ //AND
					char[] r1 = buffer.getOperand1(bf).toCharArray();  //register.get(toBinary(fromBinary(arg[1],bite_size),bite_size)).toCharArray();
					char[] r2 = buffer.getOperand2(bf).toCharArray();  //register.get(toBinary(fromBinary(arg[2],bite_size),bite_size)).toCharArray();
	
					StringBuilder a = new StringBuilder();
					for(int i = 0; i< r1.length; i++)
						if(r1[i]==49 && r1[i]==r2[i])
							a.append(1);
						else
							a.append(0);
					String e=a.toString();
					Rd = e;
				}else if(code.equals("0011")){ //OR
					char[] r1 = buffer.getOperand1(bf).toCharArray(); //register.get(toBinary(fromBinary(arg[1],bite_size),bite_size)).toCharArray();
					char[] r2 = buffer.getOperand2(bf).toCharArray(); //register.get(toBinary(fromBinary(arg[2],bite_size),bite_size)).toCharArray();
	
					StringBuilder a = new StringBuilder();
					for(int i = 0; i< r1.length; i++)
						if(r1[i]!=r2[i])
							a.append(1);
						else
							a.append(0);
					String e=a.toString();
					Rd = e;
				}else if(code.equals("0100")){ //ADD
					char[] r1 = buffer.getOperand1(bf).toCharArray(); //register.get(toBinary(fromBinary(arg[1],bite_size),bite_size)).toCharArray();
					char[] r2 = buffer.getOperand2(bf).toCharArray(); //register.get(toBinary(fromBinary(arg[2],bite_size),bite_size)).toCharArray();
					Rd = Addition(r1,r2);
				}else if(code.equals("0110")){ //ADDI
					char[] r1 = buffer.getOperand1(bf).toCharArray(); //register.get(toBinary(fromBinary(arg[1],bite_size),bite_size)).toCharArray();
					char[] r2 = buffer.getOperand2(bf).toCharArray(); //String.format("%16s",arg[2]).replace(' ', '0').toCharArray();
	//				if(isInteger(arg[2]))
						Rd = Addition(r1,r2);
				}else if(code.equals("0101")){ //SUB
					char[] r1 = buffer.getOperand1(bf).toCharArray(); //register.get(toBinary(fromBinary(arg[1],bite_size),bite_size)).toCharArray();
					char[] r2 = buffer.getOperand2(bf).toCharArray(); //register.get(toBinary(fromBinary(arg[2],bite_size),bite_size)).toCharArray();
					Rd = Substract(r1,r2);
				}else if(code.equals("0111")){ //SUBI
					char[] r1 = buffer.getOperand1(bf).toCharArray(); //register.get(toBinary(fromBinary(arg[1],bite_size),bite_size)).toCharArray();
					char[] r2 = buffer.getOperand2(bf).toCharArray(); //String.format("%16s",arg[2]).replace(' ', '0').toCharArray();
	//				if(isInteger(arg[2]))
					Rd = Substract(r1,r2);
				}else if(code.equals("1000")){ //SET
					Rd = buffer.getOperand1(bf); //(register.containsKey(toBinary(fromBinary(arg[0],bite_size),bite_size))?register.get(toBinary(fromBinary(arg[0],bite_size),bite_size)).substring(0, 8):toBinary("0",8))+arg[1]+arg[2];
				}else if(code.equals("1001")){ //SETH
					Rd = buffer.getOperand1(bf); //arg[1]+arg[2]+(register.containsKey(toBinary(fromBinary(arg[0],bite_size),bite_size))?register.get(toBinary(fromBinary(arg[0],bite_size),bite_size)).substring(8, 16):toBinary("0",8));
				}else if(code.equals("1010")){ //INCIZ if R2 == 0
					//Rd = register.get(toBinary(fromBinary(arg[0],bite_size),bite_size));
	
					if(buffer.getOperand2(bf).equals("0000000000000000")){
						char[] r1 = register.get("000000000000"+buffer.getResult_Address(bf)).toCharArray(); //register.get(toBinary(fromBinary(arg[0],bite_size),bite_size)).toCharArray();
						char[] r2 = buffer.getOperand1(bf).toCharArray(); //String.format("%16s",arg[1]).replace(' ', '0').toCharArray();
						Rd = Addition(r1,r2);					
					}
				}else if(code.equals("1011")){ //DECIN
	
					//Rd = register.get(toBinary(fromBinary(arg[0],bite_size),bite_size));				
					if(buffer.getOperand2(bf).substring(0, 1).equals("1")){
						char[] r1 = buffer.getResult(bf).toCharArray(); // register.get(toBinary(fromBinary(arg[0],bite_size),bite_size)).toCharArray();
						char[] r2 = buffer.getOperand1(bf).toCharArray(); //String.format("%16s",arg[1]).replace(' ', '0').toCharArray();
						Rd = Substract(r1,r2);
					}
				}else if(code.equals("1100")){ // R1 <- R2 if R3 == 0  //MOVEZ
					if(buffer.getOperand2(bf).equals("0000000000000000"))
						Rd = buffer.getOperand1(bf); // register.get(toBinary(fromBinary(arg[1],bite_size),bite_size));//:register.get(toBinary(fromBinary(arg[0],bite_size),bite_size));
				}else if(code.equals("1101")){ // R1 <- R2 if R3 != 0 //MOVEX
					if(buffer.getOperand2(bf).equals("0000000000000000"))
						Rd = buffer.getOperand1(bf); // register.get(toBinary(fromBinary(arg[1],bite_size),bite_size));//:register.get(toBinary(fromBinary(arg[0],bite_size),bite_size));
				}else if(code.equals("1110")){ // R1 <- R2 if Rb15 == 0(R3 is Positive) //MOVEP
					if(buffer.getOperand2(bf).substring(0, 1).equals("0"))
						Rd = buffer.getOperand1(bf); // register.get(toBinary(fromBinary(arg[1],bite_size),bite_size));//:register.get(toBinary(fromBinary(arg[0],bite_size),bite_size));
				}else if(code.equals("1111")){ // R1 <- R2 if Rb15 == 1(R3 is Negative) //MOVEN
					if(buffer.getOperand2(bf).substring(0, 1).equals("1"))
						Rd = buffer.getOperand1(bf); // register.get(toBinary(fromBinary(arg[1],bite_size),bite_size));//:register.get(toBinary(fromBinary(arg[0],bite_size),bite_size));
				}
	

				if(!Rd.equals("")){
					buffer.putResult(bf+1,Rd);
				}
				register.put(toBinary(PC,bite_size), toBinary(Integer.toString((Integer.parseInt(fromBinary(register.get(toBinary(PC,bite_size)),bite_size))+1)),bite_size));
	
			}catch(Exception me){
				PrintError("Unable to execute Instruction '"+makeASMCode(buffer.getCellInstruction(bf))+"', confirm Opcode and Operands are valid");
			}
		}
		
		public void run() {
			try {
				while(true)
					if(buffer.WriteReady(3)){
						available = true;
						Execute(3);
					}
//				fetch_free = false,decode_free=false,fetchop_free=false,execute_free=false,writeback_free=false;
					
			
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	}
	
	static class WriteBack extends Thread{
		private synchronized void WriteBack(int bf){
			
		      while (available == false) {
		          try {
		             wait();
		          }
		          catch (InterruptedException e) {
		          }
		       }
		       available = false;

			buffer.moveNext(bf);
			bf++;
			
			String Rd_Address = buffer.getResult_Address(bf);
	
			int pc1 = 0,pc2 = 0;
			pc1 = Integer.parseInt(fromBinary(register.get(toBinary(PC,bite_size)),bite_size));
			
			if(!(buffer.getResult(bf) ==null)){
				register.put("000000000000"+buffer.getResult_Address(bf),buffer.getResult(bf));
				memory.put("000000000000"+buffer.getResult_Address(bf),buffer.getResult(bf));
			}
			
			if(!(Rd_Address.equals("1111") && !(buffer.getResult(bf) ==null))) // If  NOT(PC is 15 and NOT(Rd is Empty)), then increase PC counter by 1 
				register.put(toBinary(PC,bite_size), toBinary(Integer.toString((Integer.parseInt(fromBinary(register.get(toBinary(PC,bite_size)),bite_size))+1)),bite_size));
	
			pc2 = Integer.parseInt(fromBinary(register.get(toBinary(PC,bite_size)),bite_size));
			inst_count  = inst_count + (pc2-pc1);
			if(Rd_Address.equals("1111") && pc1==pc2){  //PC Stayed at R15 even after updating Operands, e.g MOVE R15,R15, thus end application
				can_fetch_Instruction=false;
			}
			notifyAll();
			
		}
		
		public void run() {
			try {
				while(true)
					if(buffer.WriteReady(4)){
						available = true;
						WriteBack(4);
					}
//				fetch_free = false,decode_free=false,fetchop_free=false,execute_free=false,writeback_free=false;
				
			
			
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	}

	private static String makeByteCode(String param1, String[] param2) {
		String str="";
		for(int j = 1; j < param2.length; j++){

			if(isInteger(param2[j].replace("PC", "R15").replace("R", "").trim())) 
				if(param1.startsWith("SET"))
					str+=toBinary(param2[j].replace("PC", "R15").replace("R", "").trim(),8);
				else
					str+=toBinary(param2[j].replace("PC", "R15").replace("R", "").trim(),4);
		}
		str = String.format("%8s",str).replace(' ', '0');
		return opcode.get(param1)+toBinary(param2[0].replace("PC", "R15").replace("R", "").trim(),4)+str;

	}

	private static String makeASMCode(String param) {
		String str="";
		String ret_str = "";
		
		if(param == null)
			param = null;

		if(param.length() == 16){
			str=param.substring(0,4);
			int x = 0;
			for(String b : bitcode){
				if(b.equals(str)){
					ret_str +=operator[x]+" ";
					str="";
					break;
				}
				x++;
			}

			str=param.substring(4,8);
			ret_str +="R"+fromBinary(String.format("%8s",str).replace(' ', '0'),8)+",";

			if(param.substring(0,4).equals("0000")){
				str=param.substring(12,16);
				ret_str +="R"+fromBinary(String.format("%8s",str).replace(' ', '0'),8);
			}else if(param.substring(0,3).equals("100")){
				str=param.substring(8,16);
				ret_str +=fromBinary(String.format("%16s",str).replace(' ', '0'),16);
			}else if(param.substring(0,3).equals("011")){
				str=param.substring(8,12);
				ret_str +="R"+fromBinary(String.format("%8s",str).replace(' ', '0'),8)+",";
				str=param.substring(12,16);
				ret_str +=fromBinary(String.format("%16s",str).replace(' ', '0'),16);
			}else{
				str=param.substring(8,12);
				ret_str +="R"+fromBinary(String.format("%8s",str).replace(' ', '0'),8)+",";
				str=param.substring(12,16);
				ret_str +="R"+fromBinary(String.format("%8s",str).replace(' ', '0'),8);
			}
		}else
			PrintError("ERROR in binary string '"+param+"', unable to decode instrusction");
		return ret_str;

	}

	//		public static void takeActions(String code, String[] arg){
	//			
	//		}

	public static final String toBinary(String bstr, int bsize) {
		StringBuilder builder = new StringBuilder();
		int temp = 0,bint = 0,base=2;
		String str = "",sign = "0";
		if(bstr.startsWith("0x")){
			base = 16;
			str = String.format("%"+(bsize)+"s",hexToBin(bstr.substring(2),base)).replace(' ', '0');
		}else if(bstr.startsWith("b")){
			base = 2;
			str = String.format("%"+(bsize)+"s",hexToBin(bstr.substring(1),base)).replace(' ', '0');		    	
		}else if(isInteger(bstr)){
			bint = Integer.parseInt(bstr);
			if(bint<0){
				sign = "1";
				bint = 32768+bint;
			}			    
			while (bint>0) {
				temp = bint;
				bint = (temp>>1);
				builder.append(temp%2);
			}
			str=builder.reverse().toString();
			str = sign+String.format("%"+(bsize-1)+"s",str).replace(' ', '0');
			str = str.substring(str.length()-bsize);
		}
		return str;

	}

	public static final String fromBinary(String value, int bsize) {
		char c[] = String.format("%"+(bsize)+"s",value).replace(' ', '0').toCharArray();
		int temp = 0;
		int sign = 1;
		if(c[0] == 49){
			sign = -1;
		}
		for(int n=(bsize-1); n>0; n--) {
			temp += (sign < 0 ? (Character.digit(c[n],10)==1?0:1):Character.digit(c[n],10))*Math.pow(2,((bsize-1)-n)); 
		}
		temp +=(sign < 0? 1:0);

		return Integer.toString(sign*temp);
	}

	public static String Substract(char[] r1,char[] r2){
		int x=0;
		StringBuilder a = new StringBuilder();
		for(x=(16-1); x >= 0; x--)
			if(r1[x]==r2[x]){
				a.append("0");
			}else if(r2[x] == 48){
				a.append("1");
			}else{
				int y = x;
				while(y > 0 && r1[y-1] == 48){
					y--;
					r1[y] = 49;
				};
				if(y > 0)
					r1[y-1] = 48;
				a.append("1");

			}
		String e=a.reverse().toString();
		return e;
	}

	public static String Addition(char[] r1, char[] r2){
		int x=0,rmd=0;
		StringBuilder a = new StringBuilder();
		for(x=(16-1); x >= 0; x--)
			if(r1[x]==r2[x]){
				if(r1[x] == 49 && rmd == 0){
					rmd = 1;
					a.append("0");
				}else if(r1[x] == 49 && rmd == 1){
					rmd = 1;
					a.append("1");
				}else if(r1[x] == 48 && rmd == 0){
					rmd = 0;
					a.append("0");
				}else if(r1[x] == 48 && rmd == 1){
					rmd = 0;
					a.append("1");
				}
			}else{
				if(rmd == 0){
					rmd = 0;
					a.append("1");
				}else if(rmd == 1){
					rmd = 1;
					a.append("0");
				}

			}
		String e=a.reverse().toString();
		return e;
	}

	public static boolean isInteger(String str){
		try{
			Integer.parseInt(str);
		}catch(NumberFormatException e){
			PrintError("Integer is expected in Operand '"+str+"' in instruction '"+line+"' ");
			return false;
		}

		return true;
	}
	public static void PrintError(String str){
		ResText.append("ERROR: "+str+"\n");
		ResText.setBackground(Color.red);
		err = true;
		selectFile=true;

	}

	public static String hexToBin(String s, int bsize) {
		return new BigInteger(s,bsize).toString(2);
	}

	// Creates highlights around all occurrences of pattern in textComp
	public static void highlight(JTextComponent textComp, String pattern) {
		// First remove all old highlights

		try {
			Highlighter hilite = textComp.getHighlighter();
			Document doc = textComp.getDocument();
			String text = doc.getText(0, doc.getLength());

			int pos = 0;
			// Search for pattern
			while ((pos = text.indexOf(pattern, pos)) >= 0) {
				// Create highlighter using private painter and apply around pattern
				hilite.addHighlight(pos, pos+pattern.length(),DefaultHighlighter.DefaultPainter);
				pos += pattern.length();
			}

		} catch (BadLocationException e) {
		}
	}


}


