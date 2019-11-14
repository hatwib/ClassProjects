import java.io.BufferedReader;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DMPrep {


	static ArrayList<Set> motherSet;
	public static void main(String[] args) throws IOException {
		try{

			motherSet = new ArrayList<Set>();
			String letters[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
			int y = 0;
			String img;
			if(args.length < 1)
				img = "girl.img";
			else
				img = args[0];
			BufferedReader reader = new BufferedReader( new FileReader (img));

			String line = null;


			while( ( line = reader.readLine() ) != null ) {
				for(int i=0; i < line.length()+1; i++){
					if (line.split("")[i].equals("+")){
						if (!(line.split("")[i-1].equals("+"))){
							Set ufSet = new Set("");
							ufSet.Insert_Set(i, y);
							motherSet.add(ufSet);
						}else
							motherSet.get(motherSet.size()-1).Insert_Set(i, y);

					}
				}
				y++;

			}

			int x=0;
			for(Set s : motherSet){
				if(!(s.hasBeenVisited)){
					s.Set_Name(letters[x++]);
					s.Union_Set(motherSet);
				}
			}

			// Write text to file

			PrintWriter out = new PrintWriter(new FileWriter("girl.txt"));
			String l1;
			String ls = System.getProperty("line.separator");
			for(y = 0; y < 71; y++){
				line = "";
				for(x = 1; x < 72; x++){
					l1= "";
					forloop:
						for(Set s : motherSet){
							l1=s.Find_Set(x,y);
							if(!(l1==" "))
								break forloop;
						}
					line= line+l1;	

				}
				out.print(line);
				out.print(ls);
			}out.close();

		}
		catch(IOException e)
		{
			PrintWriter out = new PrintWriter(new FileWriter("girl.txt"));
			String ls = System.getProperty("line.separator");
			out.print("Error");
			out.print(ls);
			out.print(e.getMessage());
			out.close();

		}
	}

}

class Set{
	public String set_name;
	ArrayList<Integer[]> coord;
	public Boolean hasBeenVisited;
	public Set(String s){
		set_name = s;
		coord = new ArrayList<Integer[]>();
		hasBeenVisited = false;
	}


	public void Set_Name(String s){
		set_name = s;
		hasBeenVisited = true;
	}

	public void Insert_Set(int i, int j){
		Integer[] myArray = new Integer[]{ i,j};
		coord.add(myArray);

	} 

	public void Union_Set(ArrayList<Set> a){
		for(Set s : a){
			if(!(s.hasBeenVisited)){
				for(Integer[] i : s.coord){
					for(Integer[] j : this.coord){
						if((!(s.hasBeenVisited) && (i[0] == j[0])) && (i[1] == j[1]+1 ||i[1] == j[1]-1) ){
							s.Set_Name(this.set_name);
							s.Union_Set(a);
						}
					}
				}
			}
		}

	}

	public String Find_Set(int i, int j){
		for(Integer[] ar : this.coord )
			if((ar[0]==i) && (ar[1]==j))
				return this.set_name;
		return " ";
	}

}

