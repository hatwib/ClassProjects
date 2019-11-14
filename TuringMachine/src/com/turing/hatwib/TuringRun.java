package com.turing.hatwib;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;




public class TuringRun {


	public static Border border = BorderFactory.createLineBorder(Color.BLACK);
	public static String fileName = "inputFile.txt";
	public static boolean err = false, running=false, loaded = false, stopped=true;
	public static JFrame f = new JFrame();
	public static int Pindex = 0, Qindex = 0, Tindex = 0;
	public static String state = "", dir="";

	public static JPanel TapePPanel = new JPanel(); 
	public static JPanel TapeQPanel = new JPanel();
	public static JPanel TapeTPanel = new JPanel();
	public static JPanel middlePanel = new JPanel();
	public static JPanel upperPanel = new JPanel();
	public static JScrollPane vertical1,vertical2,vertical3;
	public static File openFile;
	public static JFileChooser fileDialog = new JFileChooser();
	public static JButton RunButton, NextButton, LoadButton;


	private static class LoadFile
	implements Runnable {
		public void run() {
			try {

				openFile = fileDialog.getSelectedFile();
				fileName = openFile.getPath();
				err=false;
				BufferedReader br = null;
				br = new BufferedReader(new FileReader(fileName));
				int l = 0, i = 0, pCount = 0;
				
				String[] qList = new String[100];

				TapeQPanel.removeAll();
				TapeQPanel.revalidate();
				TapeTPanel.removeAll();
				TapeTPanel.revalidate();
				TapePPanel.removeAll();
				TapePPanel.revalidate();
				String line = "";
				boolean firstLine = true;

				while((line = br.readLine()) != null){
					if(line.indexOf("\\") > 0){
						line = line.substring(0,line.indexOf("\\")).trim();
					}
					if(line.indexOf("/") > 0){
						line = line.substring(0,line.indexOf("/")).trim();
					}
						
					char[] lineArray = line.trim().toCharArray();
					boolean foundPS = false;
					boolean foundNS = false;
					String t ="";
					for(int k = 0; k < lineArray.length; k++){
						
						if(foundPS && lineArray[k] == ','){
							foundPS = false;
							k--;
							boolean foundQ = false;
							
							for(int p = 0 ; p < qList.length; p++){
								if((qList[p]!=null) && qList[p].equals(t)){
									foundQ = true;
									break;
								}
							}
							if(!foundQ){
								qList[pCount++] = t;
								JTextArea tapeQCell = new JTextArea(t, 1, 1);
								tapeQCell.setLineWrap(false);
								tapeQCell.setEditable(false);
								tapeQCell.setBorder(border);
								TapeQPanel.add(tapeQCell);
							}
							
						}else if(foundNS && lineArray[k] == ','){
							foundNS = false;
							k--;
							boolean foundQ = false;
							
							for(int p = 0; p < qList.length; p++){
								if((qList[p]!=null) && qList[p].equals(t)){
									foundQ = true;
									break;
								}
							}
							if(!foundQ){
								qList[pCount++] = t;

								JTextArea tapeQCell = new JTextArea(t, 1, 1);
								tapeQCell.setLineWrap(false);
								tapeQCell.setEditable(false);
								tapeQCell.setBorder(border);
								TapeQPanel.add(tapeQCell);
							}

						}else{
						
						if(l == 0)
							t = Character.toString(lineArray[k]).trim();
						else if(l == 1){
							t = line.trim();
						}else if(l >= 2){
							if(foundPS || foundNS){
								t += Character.toString(lineArray[k]).trim();
								continue;
							}else
								t = Character.toString(lineArray[k]).trim();
						}else
							t = Character.toString(lineArray[k]).trim();
						}
						
						JTextArea tapeCell = new JTextArea(t, 1, 1);
						if(t.equals("[")){
							foundPS = true;
						}else if(t.equals("{")){
							foundNS = true;
						}
						
						

						if(k == 0){
							if(l == 1){
								firstLine = true;
								i = -1;
							}else if(l == 0){
								firstLine = true;
								i = -1;
							}else if(l == 2){
								firstLine = true;
								i = -1;
							}
						}
						i++;

						tapeCell.setLineWrap(false);
						tapeCell.setEditable(false);
						tapeCell.setBorder(border);


						if(l == 1){
							state = tapeCell.getText();
							qList[pCount++] = state;
							TapeQPanel.add(tapeCell);
						}else if(l == 0){
							TapeTPanel.add(tapeCell);
						}else{
							TapePPanel.add(tapeCell);
						}
						if(!t.equals("_") && firstLine){
							tapeCell.setBackground(Color.YELLOW);
							firstLine = false;
							if(l == 1){
								Qindex = i;
							}else if(l == 0){
								Tindex = i;
							}else if(l == 2){
								Pindex = i;
							}
						}
						
						TapePPanel.revalidate();
						vertical1.revalidate();
						upperPanel.revalidate();
						t = "";
						if(l==1)
							break;
					}
					l++;
				}
				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static class RunFile
	implements Runnable {
		@SuppressWarnings("deprecation")
		public void run() {
			try {

				JScrollBar horizontalScrollBar = vertical1.getHorizontalScrollBar();  
				String out = "";
				while(true){
					
					boolean findPS = true;
					boolean findIN = false;
					boolean findNP = false;
					boolean findOUT = false;
					boolean findQ = false;
					boolean endFind = false;
					

					String str = "";

					String ps,in;
					
					
//					JTextArea tmpQ = (JTextArea) TapeQPanel.getComponent(Qindex);
//					ps = tmpQ.getText();
					ps = state;
					if(ps.equals("h")){
						running = false;
						loaded = false;
						stopped = true;
						JTextArea endP = (JTextArea) TapePPanel.getComponent(Pindex);
						endP.setBackground(Color.RED);
						Thread.currentThread().suspend();
						endP.setBackground(Color.WHITE);
						continue;
					}
					vertical1.getHorizontalScrollBar().setValue(0);
					vertical1.repaint();
					JTextArea tmpT = (JTextArea) TapeTPanel.getComponent(Tindex);
					in = tmpT.getText();

					for(int i = 0; i < TapePPanel.getComponentCount(); i++){
						
						JTextArea tmpP = (JTextArea) TapePPanel.getComponent(i);

						if(findPS){
							if(str.equals(ps) && tmpP.getText().trim().equals(",")){
								findPS = false;
								findIN = true;
								str = "";
							}
							else if(tmpP.getText().trim().equals("d") || tmpP.getText().trim().equals("["))
								str = "";
							else{
								str += tmpP.getText().trim();
							}
						}else if(findIN){
							if(str.equals(in) && tmpP.getText().trim().equals("]")){
								findIN = false;
								findNP = true;
								str = "";
							}else if(tmpP.getText().trim().equals(","))
								str = "";
							else{
								str += tmpP.getText().trim();
							}
						}else if(findNP){
							if(tmpP.getText().trim().equals(",")){
								findNP = false;
								findOUT = true;
								
//								tmpQ.setText(state);
//								tmpQ.setBackground(Color.YELLOW);
								
//								for(int r = 0; r < TapeQPanel.getComponentCount(); r++){
//									JTextArea tmpQ1 = (JTextArea) TapeQPanel.getComponent(r);
//									tmpQ1.setBackground(Color.WHITE);
//									if(tmpQ1.getText().trim().equals(state)){
//										tmpQ1.setBackground(Color.YELLOW);	
//									}
//								}
								

//								Thread.sleep(10); //10
							}
							else if(!(tmpP.getText().trim().equals("{") || tmpP.getText().trim().equals("="))){
								state = tmpP.getText().trim();
								Pindex = i;
							}
//								ns = tmpP.getText().trim();
						}else if(findOUT){
							if(tmpP.getText().trim().equals(",")){
								findOUT = false;
								findQ = true;						
								tmpT.setText(out);
								tmpT.revalidate();
								tmpT.repaint();
								Thread.sleep(100);
								TapeTPanel.revalidate();
//								if(Tindex == 0){
//									JTextArea tapeCell=new JTextArea("", 1, 1);
//									tapeCell.setLineWrap(false);
//									tapeCell.setEditable(false);
//									tapeCell.setBorder(border);
//									tapeCell.append("_");
//									TapeTPanel.add(tapeCell, 0);
//								}
//								
//								if(Tindex > 0){
//									JTextArea tmpTL = (JTextArea) TapeTPanel.getComponent(Tindex-1);
//									tmpTL.setBackground(Color.WHITE);
//								}
//								if(Tindex == TapeTPanel.getComponentCount()-1){
//									JTextArea tapeCell=new JTextArea("_", 1, 1);
//									tapeCell.setLineWrap(false);
//									tapeCell.setEditable(false);
//									tapeCell.setBorder(border);
//									TapeTPanel.add(tapeCell);
//								}
//								JTextArea tmpTR = (JTextArea) TapeTPanel.getComponent(Tindex+1);
//								tmpTR.setBackground(Color.WHITE);
								tmpT.setBackground(Color.YELLOW);
								tmpT.revalidate();
								TapeTPanel.revalidate();
//								Thread.sleep(10); //100			
							}else{
								out = tmpP.getText().trim();
								i++;
								findOUT = false;
								findQ = true;						
//								tmpT.setText(out);
							}
						}else if(findQ){
							if(tmpP.getText().trim().equals("}")){
								findQ = false;
								endFind = true;
							}
							else
								dir = tmpP.getText().trim(); // state += tmpP.getText().trim();
						}else if(endFind){
							tmpP.setBackground(Color.YELLOW);
							tmpP.revalidate();
							if(Tindex == 0){
								JTextArea tapeCell=new JTextArea("_", 1, 1);
								tapeCell.setLineWrap(false);
								tapeCell.setEditable(false);
								tapeCell.setBorder(border);
								TapeTPanel.add(tapeCell, 0);
								Tindex++;
								TapeTPanel.revalidate();
							}

							if(Tindex == TapeTPanel.getComponentCount()-1){
								JTextArea tapeCell=new JTextArea("_", 1, 1);
								tapeCell.setLineWrap(false);
								tapeCell.setEditable(false);
								tapeCell.setBorder(border);
								TapeTPanel.add(tapeCell);
								TapeTPanel.revalidate();
							}

							
//							JTextArea tmpOld = (JTextArea) TapeTPanel.getComponent(Tindex);
//							tmpOld.setBackground(Color.WHITE);
							if(dir.equals("R")){
								Tindex++;
							}else if(dir.equals("L")){
								Tindex--;
							}
							ps = state;
							if(running)
								Thread.sleep(10); //500
							else
								Thread.currentThread().suspend();
							tmpP.setBackground(Color.WHITE);
							
							for(int r = 0; r < TapeQPanel.getComponentCount(); r++){
								JTextArea tmpQ1 = (JTextArea) TapeQPanel.getComponent(r);
								tmpQ1.setBackground(Color.WHITE);
								tmpQ1.revalidate();
								if(tmpQ1.getText().trim().equals(state)){
									tmpQ1.setBackground(Color.YELLOW);	
									tmpQ1.revalidate();
								}
							}
							
//							vertical1.getHorizontalScrollBar().setValue(0);
//							vertical1.repaint();
//							tmpP.setBackground(Color.WHITE);
							
							tmpT.setBackground(Color.WHITE);
							tmpT.setText(out);
							tmpT.revalidate();
							tmpT.repaint();
							//Thread.sleep(10);
							JTextArea tmpNew = (JTextArea) TapeTPanel.getComponent(Tindex);
							tmpNew.setBackground(Color.YELLOW);
							tmpT.revalidate();
							tmpT.repaint();
							TapeTPanel.revalidate();
							//Thread.sleep(10);
							break;
						}
						
						
						if( i > 15 && horizontalScrollBar.getValue() + (17) > horizontalScrollBar.getMinimum() &&  
							    horizontalScrollBar.getValue() + horizontalScrollBar.getVisibleAmount() + (17) <= horizontalScrollBar.getMaximum()){
							horizontalScrollBar.setValue(horizontalScrollBar.getValue() + (17)); 
//							vertical1.getHorizontalScrollBar().setValue((i*20)-400);
							vertical1.repaint();
						}

		                
		                
						tmpP.setBackground(Color.YELLOW);
						tmpP.revalidate();
						if(!running)
							Thread.sleep(10);
						tmpP.setBackground(Color.WHITE);
						tmpP.revalidate();
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	public static void main(String args[]) throws InterruptedException {


		f = new JFrame("Turing Machine");
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		final Dimension frameSize=new Dimension((int)(screenSize.width),(int)(screenSize.height));

		f.setBounds(0,0,frameSize.width,frameSize.height);

		upperPanel.setLayout(new BorderLayout(10,20));



		NextButton = new JButton("Next Step");
		NextButton.setVerticalTextPosition(AbstractButton.CENTER);
		NextButton.setHorizontalTextPosition(AbstractButton.LEADING);

		RunButton = new JButton("Run All");
		RunButton.setVerticalTextPosition(AbstractButton.CENTER);
		RunButton.setHorizontalTextPosition(AbstractButton.LEADING);

		LoadButton = new JButton("Load File");
		LoadButton.setVerticalTextPosition(AbstractButton.CENTER);
		LoadButton.setHorizontalTextPosition(AbstractButton.LEADING);


		vertical1 = new JScrollPane(TapePPanel,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		vertical1.getHorizontalScrollBar().setUnitIncrement(10);
		upperPanel.add(vertical1, BorderLayout.NORTH);


		vertical2 = new JScrollPane(TapeQPanel,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		vertical2.getVerticalScrollBar().setUnitIncrement(10);
		upperPanel.add(vertical2, BorderLayout.CENTER);


		vertical3 = new JScrollPane(TapeTPanel,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		vertical3.getVerticalScrollBar().setUnitIncrement(10);
		upperPanel.add(vertical3, BorderLayout.SOUTH);


		middlePanel.add(NextButton);
		middlePanel.add(RunButton);
		middlePanel.add(LoadButton);

		f.getContentPane().add(upperPanel, BorderLayout.NORTH);
		f.getContentPane().add(middlePanel);

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				System.exit(0);
			}
		});
		f.setLocationRelativeTo(null);
		f.setVisible(true);


		LoadButton.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent arg0)  {

			}

			public void mouseEntered(MouseEvent arg0)  {}

			public void mouseExited(MouseEvent arg0) {}

			public void mousePressed(MouseEvent arg0)  {}

			public void mouseReleased(MouseEvent arg0)  {
//				if(!loaded){
					int openChoice = fileDialog.showOpenDialog(null);
					if (openChoice == JFileChooser.APPROVE_OPTION)
					{
						Thread loader = new Thread(new LoadFile());
						loader.start();
						loaded = true;
						running = false;
						stopped = false;
					}

//				}
			} 

		});
		final Thread runner = new Thread(new RunFile());
		
		RunButton.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent arg0)  {}

			public void mouseEntered(MouseEvent arg0)  {}

			public void mouseExited(MouseEvent arg0) {}

			public void mousePressed(MouseEvent arg0)  {}

			
			@SuppressWarnings("deprecation")
			public void mouseReleased(MouseEvent arg0)  {
				if(stopped)
					JOptionPane.showMessageDialog(null,"Please load a new File...","Load File", JOptionPane.ERROR_MESSAGE);
				else if(loaded){
//					Thread runner = new Thread(new RunFile());
					loaded = false;
					running = true;

					if(!runner.isAlive())
						runner.start();
					else{
						runner.resume();
					}					
				}
			} 
		});
		
		
		NextButton.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent arg0)  {}

			public void mouseEntered(MouseEvent arg0)  {}

			public void mouseExited(MouseEvent arg0) {}

			public void mousePressed(MouseEvent arg0)  {}

			
			@SuppressWarnings("deprecation")
			public void mouseReleased(MouseEvent arg0)  {
				if(stopped)
					JOptionPane.showMessageDialog(null,"Please load a new File...","Load File", JOptionPane.ERROR_MESSAGE);
				else if(loaded){
					if(!runner.isAlive())
						runner.start();
					else{
//						state="";
						runner.resume();
					}
					loaded = false;
					running = false;
				}
				else if(!running){
					runner.resume();
				}
			} 
		});

		
	}
}