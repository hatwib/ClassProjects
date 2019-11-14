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
import java.math.BigInteger;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;



public class ReadCode {
		public static Map<String, String> register = new HashMap<String, String>();
		public static Map<String, String> memory = new HashMap<String, String>();
		public static Map<String, String> opcode = new HashMap<String, String>();
		public static int asm=0;//assembly 
		public static String PC = "15",line="",content="",fileName="assembly8.txt";
		public static int bite_size = 16;
		public static JTextArea Regtext;
		public static JTextArea Memtext;
		public static JTextArea ASMText;
		public static JTextArea ResText;
		public static JFrame f;
		public static boolean err = false,selectFile=false,fetchInstruction = true;
		public static String operator[];
		public static String bitcode[];
	
		
		public static void main(String[] args) {
			operator = new String[]{"MOVE", "NOT", "AND", "OR", "ADD", "SUB", "ADDI", "SUBI", "SET", "SETH", "INCIZ", "DECIN", "MOVEZ", "MOVEX", "MOVEP", "MOVEN" };
			bitcode = new String[]{"0000","0001","0010","0011","0100","0101","0110","0111","1000","1001","1010","1011","1100","1101","1110","1111"};
		    f = new JFrame("Single Scalar Micro Processor");
		    Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
			Dimension frameSize=new Dimension((int)(screenSize.width),(int)(screenSize.height));
			
			f.setBounds(0,0,frameSize.width,frameSize.height);
		    JPanel upperPanel = new JPanel();
		    JPanel lowerPanel = new JPanel();
		    
		    f.getContentPane().add(upperPanel, "North");
		    f.getContentPane().add(lowerPanel, "South");

			Regtext=new JTextArea(content, 30, 33);
			Regtext.setLineWrap(false); //DWM 02-07-2012
			Regtext.setEditable(false); //DWM 02-07-2012
			
			Memtext=new JTextArea(content, 30, 35);
			Memtext.setLineWrap(false); //DWM 02-07-2012
			Memtext.setEditable(false); //DWM 02-07-2012
			
			ASMText=new JTextArea(content, 30, 15);
			ASMText.setLineWrap(false); //DWM 02-07-2012
			ASMText.setEditable(false); //DWM 02-07-2012
			
			ResText=new JTextArea(content, 4, 83);
			ResText.setLineWrap(false); //DWM 02-07-2012
			ResText.setEditable(false); //DWM 02-07-2012
			
		    upperPanel.add(new JScrollPane(Regtext));
		    upperPanel.add(new JScrollPane(Memtext));
		    upperPanel.add(new JScrollPane(ASMText));
		    
		    lowerPanel.add(new JScrollPane(ResText));
		    ResText.addMouseListener(new MouseListener(){

				public void mouseClicked(MouseEvent arg0)  {}

				public void mouseEntered(MouseEvent arg0)  {}

				public void mouseExited(MouseEvent arg0) {}

				public void mousePressed(MouseEvent arg0)  {}

				public void mouseReleased(MouseEvent arg0)  {if(!selectFile) decode();}
		    });
		    
		    ASMText.addMouseListener(new MouseListener(){

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
		    
		    ResText.addKeyListener(new KeyListener() {

				public void keyPressed(KeyEvent e) {if(!selectFile) decode();}
				
				

				public void keyReleased(KeyEvent e) {}

				public void keyTyped(KeyEvent e)  {}
		    });
		    f.addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent ev) {
	                System.exit(0);
	            }
	            public void windowOpened( WindowEvent e){ 
	            	ResText.requestFocus();
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
		}
		public static void loader(){
			BufferedReader br = null;
			String regString = "#",asmcode="";
			String regComment = "//";
			memory.clear();
			register.clear();
			if(!err)
			try {
				br = new BufferedReader(new FileReader(fileName)); 
				ASMText.setText("Assembly Code\n");
				selectFile=false;
				err=false;
				fetchInstruction=true;
				
				ResText.setText("");
				ResText.setBackground(Color.white);
				
				memory.put(toBinary(PC,bite_size), toBinary(PC,bite_size));
				
				while((line = br.readLine()) != null){
					asmcode = line.replaceAll("\\s+", " ").trim(); // Replace multiple whitespace with single whitespace/ trim sides
					int cmt_position = (asmcode.indexOf(regComment)>=0?asmcode.indexOf(regComment):asmcode.length());
					asmcode = asmcode.substring(0,cmt_position); // remove commented limes
					ASMText.append(asmcode+"\n");
					if(asmcode.startsWith(regString) && asmcode.split("=").length == 2){
						int cmt = (asmcode.split("=")[1].indexOf(regComment)>=0?asmcode.split("=")[1].indexOf(regComment):asmcode.split("=")[1].length());
						String param1 = asmcode.split("=")[0].replace(regString, "").trim();
						String param2 = asmcode.split("=")[1].substring(0,cmt).trim();
						if(param2.equals("asm")){
							if(isInteger(param1)) asm = Integer.parseInt(param1);
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
				decode();
				
				
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
//			 (register.get(toBinary(PC,bite_size)).equals(toBinary(PC,bite_size))) ||
			 if( (!memory.containsKey(register.get(toBinary(PC,bite_size)))) || Integer.parseInt(fromBinary(register.get(toBinary(PC,bite_size)),bite_size)) < 15)
				 fetchInstruction = false;
		 }
		 
		public static void decode(){
//			
			if(!err){
				canFetch();
				if(fetchInstruction){
					content="";
					Regtext.setText("          Register\n"+writeToRegister());
					Memtext.setText("          Memory\n"+writeToMemory());
					ResText.setText("");
					executeByteCode(memory.get(register.get(toBinary(PC,bite_size))));
					
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
		
		private static String writeToMemory(){
			Map<Integer, String> map = new TreeMap<Integer, String>(new Comparator<Integer>() { public int compare(Integer first, Integer second){return first.compareTo(second);}});
			@SuppressWarnings("rawtypes")
			Iterator k = memory.keySet().iterator();
			String str="";
			
			while(k.hasNext()){
				String key = (String) k.next();
				str=String.format("%-16s","Address "+ fromBinary(key,bite_size))+(String) memory.get(key);
				if(fetchInstruction && register.get(toBinary(PC,bite_size)).equals(key))
					str+="<===(FETCH) "+makeASMCode(memory.get(register.get(toBinary(PC,bite_size))))+"\n";
				else
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
		
		private static String writeToRegister(){
			Map<Integer, String> map = new TreeMap<Integer, String>(new Comparator<Integer>() { public int compare(Integer first, Integer second){return first.compareTo(second);}});
			@SuppressWarnings("rawtypes")
			Iterator k= register.keySet().iterator();
			String str="";
			
			while(k.hasNext()){
				String key = (String) k.next();
				str=String.format("%-16s","Address "+ fromBinary(key,bite_size)) + (String) register.get(key)+ " = "+ fromBinary((String) register.get(key),bite_size);
				boolean ck1 = !(memory.get(register.get(toBinary(PC,bite_size))).substring(1,3).equals("00"));
				boolean ck2 = !(memory.get(register.get(toBinary(PC,bite_size))).substring(0,3).equals("011"));
				if(memory.get(register.get(toBinary(PC,bite_size))).substring(4, 8).equals(key.substring(12, 16)))
					str+="<===(OUTPUT) R"+fromBinary(key,bite_size)+"\n";
				else if(ck1 && (memory.get(register.get(toBinary(PC,bite_size))).substring(8, 12).equals(key.substring(12, 16))))
					str+="===>(INPUT) R"+fromBinary(key,bite_size)+"\n";
				else if(ck1 && ck2 && (memory.get(register.get(toBinary(PC,bite_size))).substring(12, 16).equals(key.substring(12, 16))))
					str+="===>(INPUT) R"+fromBinary(key,bite_size)+"\n";
				else
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
		
		private static void executeByteCode(String string) {
			String param1 = string.substring(0,4);
			String[] param2 = {string.substring(4,8),string.substring(8,12),string.substring(12,16)};
			takeActions(param1,param2);
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

		public static void takeActions(String code, String[] arg){
			try{
			String Rd = "";
			String pc1 = "",pc2 = "";
			if(arg[0].equals("1111"))
				pc1 = register.get(toBinary(PC,bite_size));
			
			if(code.equals("0000")){ // Rd <- Ra //MOVE 
				Rd = register.get(toBinary(fromBinary(arg[2],bite_size),bite_size)); // Changed
			}else if(code.equals("0001")){ //NOT
				StringBuilder a = new StringBuilder();
				char[] c = register.get(toBinary(fromBinary(arg[2],bite_size),bite_size)).toCharArray();
				for(char d : c)
					if(d==48)
						a.append(1);
					else
						a.append(0);
				String e=a.toString();
				Rd = e;
			}else if(code.equals("0010")){ //AND
				char[] r1 = register.get(toBinary(fromBinary(arg[1],bite_size),bite_size)).toCharArray();
				char[] r2 = register.get(toBinary(fromBinary(arg[2],bite_size),bite_size)).toCharArray();
				
				StringBuilder a = new StringBuilder();
				for(int i = 0; i< r1.length; i++)
					if(r1[i]==49 && r1[i]==r2[i])
						a.append(1);
					else
						a.append(0);
				String e=a.toString();
				Rd = e;
			}else if(code.equals("0011")){ //OR
				char[] r1 = register.get(toBinary(fromBinary(arg[1],bite_size),bite_size)).toCharArray();
				char[] r2 = register.get(toBinary(fromBinary(arg[2],bite_size),bite_size)).toCharArray();
				
				StringBuilder a = new StringBuilder();
				for(int i = 0; i< r1.length; i++)
					if(r1[i]!=r2[i])
						a.append(1);
					else
						a.append(0);
				String e=a.toString();
				Rd = e;
			}else if(code.equals("0100")){ //ADD
				char[] r1 = register.get(toBinary(fromBinary(arg[1],bite_size),bite_size)).toCharArray();
				char[] r2 = register.get(toBinary(fromBinary(arg[2],bite_size),bite_size)).toCharArray();
				Rd = Addition(r1,r2);
			}else if(code.equals("0110")){ //ADDI
				char[] r1 = register.get(toBinary(fromBinary(arg[1],bite_size),bite_size)).toCharArray();
				char[] r2 = String.format("%16s",arg[2]).replace(' ', '0').toCharArray();
				if(isInteger(arg[2]))
					Rd = Addition(r1,r2);
			}else if(code.equals("0101")){ //SUB
				char[] r1 = register.get(toBinary(fromBinary(arg[1],bite_size),bite_size)).toCharArray();
				char[] r2 = register.get(toBinary(fromBinary(arg[2],bite_size),bite_size)).toCharArray();
				Rd = Substract(r1,r2);
			}else if(code.equals("0111")){ //SUBI
				char[] r1 = register.get(toBinary(fromBinary(arg[1],bite_size),bite_size)).toCharArray();
				char[] r2 = String.format("%16s",arg[2]).replace(' ', '0').toCharArray();
				if(isInteger(arg[2]))
					Rd = Substract(r1,r2);
			}else if(code.equals("1000")){ //SET
				Rd = (register.containsKey(toBinary(fromBinary(arg[0],bite_size),bite_size))?register.get(toBinary(fromBinary(arg[0],bite_size),bite_size)).substring(0, 8):toBinary("0",8))+arg[1]+arg[2];
			}else if(code.equals("1001")){ //SETH
				Rd = arg[1]+arg[2]+(register.containsKey(toBinary(fromBinary(arg[0],bite_size),bite_size))?register.get(toBinary(fromBinary(arg[0],bite_size),bite_size)).substring(8, 16):toBinary("0",8));
			}else if(code.equals("1010")){ //INCIZ if R2 == 0
				//Rd = register.get(toBinary(fromBinary(arg[0],bite_size),bite_size));

				if(register.get(toBinary(fromBinary(arg[2],bite_size),bite_size)).equals("0000000000000000")){
					char[] r1 = register.get(toBinary(fromBinary(arg[0],bite_size),bite_size)).toCharArray();
					char[] r2 = String.format("%16s",arg[1]).replace(' ', '0').toCharArray();
					Rd = Addition(r1,r2);					
				}
			}else if(code.equals("1011")){ //DECIN

				//Rd = register.get(toBinary(fromBinary(arg[0],bite_size),bite_size));				
				if(register.get(toBinary(fromBinary(arg[2],bite_size),bite_size)).substring(0, 1).equals("1")){
					char[] r1 = register.get(toBinary(fromBinary(arg[0],bite_size),bite_size)).toCharArray();
					char[] r2 = String.format("%16s",arg[1]).replace(' ', '0').toCharArray();
					Rd = Addition(r1,r2);
				}
			}else if(code.equals("1100")){ // R1 <- R2 if R3 == 0  //MOVEZ
				if(register.get(toBinary(fromBinary(arg[2],bite_size),bite_size)).equals("0000000000000000"))
						Rd = register.get(toBinary(fromBinary(arg[1],bite_size),bite_size));//:register.get(toBinary(fromBinary(arg[0],bite_size),bite_size));
			}else if(code.equals("1101")){ // R1 <- R2 if R3 != 0 //MOVEX
				if(!register.get(toBinary(fromBinary(arg[2],bite_size),bite_size)).equals("0000000000000000"))
					Rd = register.get(toBinary(fromBinary(arg[1],bite_size),bite_size));//:register.get(toBinary(fromBinary(arg[0],bite_size),bite_size));
			}else if(code.equals("1110")){ // R1 <- R2 if Rb15 == 0(R3 is Positive) //MOVEP
				if(register.get(toBinary(fromBinary(arg[2],bite_size),bite_size)).substring(0, 1).equals("0"))
					Rd = register.get(toBinary(fromBinary(arg[1],bite_size),bite_size));//:register.get(toBinary(fromBinary(arg[0],bite_size),bite_size));
			}else if(code.equals("1111")){ // R1 <- R2 if Rb15 == 1(R3 is Negative) //MOVEN
				if(register.get(toBinary(fromBinary(arg[2],bite_size),bite_size)).substring(0, 1).equals("1"))
					Rd = register.get(toBinary(fromBinary(arg[1],bite_size),bite_size));//:register.get(toBinary(fromBinary(arg[0],bite_size),bite_size));
			}
			
			if(!Rd.equals(""))
				register.put(toBinary(fromBinary(arg[0],bite_size),bite_size), Rd);	
			if(!(arg[0].equals("1111") && !Rd.equals("")))
				register.put(toBinary(PC,bite_size), toBinary(Integer.toString((Integer.parseInt(fromBinary(register.get(toBinary(PC,bite_size)),bite_size))+1)),bite_size));
			
			pc2 = register.get(toBinary(PC,bite_size));
			if(arg[0].equals("1111") && pc1==pc2){
				fetchInstruction=false;
			}else
				ResText.setText("                                                  Click here ot Enter any key to move to Next Instrustion\n");
//				register.put(toBinary(PC,bite_size), toBinary(Integer.toString((Integer.parseInt(fromBinary(register.get(toBinary(PC,bite_size)),bite_size))+1)),bite_size));
			
			}catch(Exception me){
				PrintError("Unable to execute Instruction '"+makeASMCode(code+arg[0]+arg[1]+arg[2])+"', confirm Opcode and Operands are valid");
			}
		}
		
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

}


