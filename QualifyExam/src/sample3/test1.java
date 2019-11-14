package sample3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Meetings {
	ArrayList<Integer> meet;
	Map<String,Integer> name;

	Meetings(int[] a, String[] b){
		name = new HashMap<String,Integer>();
		for(int i = 0; i < a.length; i++)
			name.put(b[i],a[i]);
			
		
	}
	
	Meetings(int[] a){
		meet = new ArrayList<Integer>();
		Arrays.sort(a);
		for(int i = 0; i < a.length; i++)
			meet.add(a[i]);
	}
	
	int getMostMeet(int n){
		int tot = 0;
		int i = meet.size();
		for(int j = 0; j < i; j++){
			if(meet.get(j) <= n){
				System.out.print(meet.get(j)+", ");
				n -=meet.get(j);
				tot++;
			}else
				break;
		}
		return tot;

	}
}

class Meet {
	String name;
	Integer hour;
	Meet(String b, int a){
		hour = a;
		name = b; 
	}
}


class Scheduler{
	Map<Integer,List<String>> meetings = new HashMap<Integer, List<String>>();
	List<Meet> orig_Meet = new ArrayList<Meet>();
	int maxHours = 0;
	List<Meet> max_Meet = new ArrayList<Meet>();
	Scheduler(Meet[] m) {
		for(int i = 0; i < m.length; i++){
			List<String> a = new ArrayList<String>();
			if(meetings.containsKey(m[i].hour))
				a = meetings.get(m[i].hour);
			a.add(m[i].name);
			meetings.put(m[i].hour, a);			
			orig_Meet.add(m[i]);
		}
	}

	public List<Meet> getMeetings(int t){	
		List<Meet> attend_meetings = new ArrayList<Meet>();
		int j = 0;
		for(Integer i : meetings.keySet()){
			List<String> a = meetings.get(i);
			for(int k = 0; k < a.size(); k++)
				if(j+i <= t){
					attend_meetings.add(new Meet(a.get(k),i));
					j += i;
				}else
					return attend_meetings;
		}
		return attend_meetings;
	}
	
	public List<Meet> getMaxMeetings(int tot, int i, int j){	
		List<Meet> m = new ArrayList<Meet>();
		for(;i < orig_Meet.size(); i++){
			if(orig_Meet.get(i).hour+j <= tot){
				j += orig_Meet.get(i).hour;
				m.addAll(getMaxMeetings(tot,++i, j));
				//m.add(orig_Meet.get(i));
				}
		}
		if(maxHours < j && m.size() > 0){
			max_Meet = m;
			maxHours = j;
			System.out.println("Level " +i+ " at "+maxHours);
		}
		return m;
		}
}
	
class Cube{
	int[][] cubes;
	boolean[][] visit;
	int W;
	int L;
	Map<String,Set<String>> myCluster = new HashMap<String,Set<String>>();
	
	Cube(int[][] c){
		cubes = c;
		W = c.length; L = c[0].length;
		visit = new boolean[W][L];
/*		for(int i=0; i < c.length; i++){
			for(int j = 0; j < c[i].length; j++ ){
				visit[i][j] = false;
			}
		}*/
	}
	
/*	Cube(int c, Cube[] prev){
		visit = false;
		color = c;
		int i = 0;
		for(Cube cb : prev){
			cubes[i] = cb;	
			i++;	
		}
	}*/
	public int GetHightest(){
		int H = 0;
		for(int i=0; i < cubes.length; i++){
			for(int j = 0; j < cubes[i].length; j++ ){
				if(!visit[i][j]){
					int h = Traverse(cubes[i][j],i,j,1);
					if(h > H)
						H = h;
				}
			}
		}
		visit = new boolean[W][L];
		return H;
	}
	
	public void GetCluster(){
		int k = 0;
		for(int i=0; i < cubes.length; i++){
			for(int j = 0; j < cubes[i].length; j++ ){
				if(!visit[i][j]){
					Set<String> h = new HashSet<String>();
					h.add(i+","+j);
					h = makeCluster(cubes[i][j],i,j,h);
					myCluster.put("Cluster "+(++k),h);
				}
			}
		}
		visit = new boolean[W][L];
	}
	
	public int Traverse(int c, int i, int j, int h){

		visit[i][j] = true;
		if(i < (W-1) && c == cubes[i+1][j] && !visit[i+1][j]){
			h = Traverse(c, i+1, j, ++h);
		}
		if(i > 0 && c == cubes[i-1][j]&& !visit[i-1][j]){
			h = Traverse(c, i-1, j, ++h);
		}
		if(j < (L-1) && c == cubes[i][j+1] && !visit[i][j+1]){
			h = Traverse(c, i, j+1, ++h);
		}
		if(j > 0 && c == cubes[i][j-1] && !visit[i][j-1]){
			h = Traverse(c, i, j-1, ++h);
		}

		return h;
	}
	
	public Set<String> makeCluster(int c, int i, int j, Set<String> h){
		visit[i][j] = true;
		h.add(i+","+j);
		if(i < (W-1) && c == cubes[i+1][j] && !visit[i+1][j]){
			h = makeCluster(c, i+1, j, h);
		}
		if(i > 0 && c == cubes[i-1][j]&& !visit[i-1][j]){
			h = makeCluster(c, i-1, j, h);
		}
		if(j < (L-1) && c == cubes[i][j+1] && !visit[i][j+1]){
			h = makeCluster(c, i, j+1, h);
		}
		if(j > 0 && c == cubes[i][j-1] && !visit[i][j-1]){
			h = makeCluster(c, i, j-1, h);
		}

		return h;
	}
}


class Catersian{
	List<List<Integer>> myCatersian;
	static int cnt = 0;
	public Catersian(List<List<Integer>> a){
		myCatersian = a;
	}
	
	public List<Integer> getProduct(int i){
	List<Integer> p = new ArrayList<Integer>();
	if(i < myCatersian.size()-1){
		List<Integer> a = getProduct(i+1);
		List<Integer> b = myCatersian.get(i);
		for(Integer y : b){
			for(Integer x : a){
				p.add(y*x);
			}
		}
	}
	else{
		return myCatersian.get(i);
	}
	
	return p;
	}
}


class MyNode{
	
	Integer value;
	MyNode rightNode;
	MyNode leftNode;
	
	public MyNode(){
		value = null;
		leftNode = null;
		rightNode=null;
	}
	
	public void AddNode(int i){

		if(value == null){
			value = i;
		}else{
			MyNode tmp = this;
			do{
				if(tmp.value < i){
					if(tmp.leftNode == null)
						tmp.leftNode = new MyNode();	
					tmp = tmp.leftNode;
				}else {
					if (tmp.rightNode == null)
						tmp.rightNode = new MyNode();
					tmp = tmp.rightNode;
				}
			}while(tmp.value != null);
			tmp.value = new Integer(i);
		}
	}
	
}
class test1 {

	public static int getDet(int[][] x,  int n, int p){
		int Det = 1;

		if(n==2){
			Det = (x[0][0] * x[1][1]) - (x[0][1] * x[1][0]);
		}else{
			int[][] tmp = new int[n-1][n-1];
			int i2 = 0, j2 = 0;
			for(int i = 0; i < n; i++)
				for(int j = 0; j < n; j++){
					if(j != p ){
						tmp[i2][j2++] = x[i][j];
					}
                    if (j2 == n - 1) 
                    { 
                        j2 = 0; 
                        i2++; 
                    }
				}
			Det = x[p][0]*getDet(tmp,n-1,p-1);			
		}
		return Det;
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] argv){
		
		
		int[][] myDet = new int[][]{
				{1,1,3,3,2,7},
				{1,3,3,3,2,7},
				{1,3,4,3,7,7},
				{3,3,4,4,4,3},
				{0,3,3,3,4,3},
				{0,3,3,3,4,4}};
		
		int[][] myDet2 = new int[][]{
				{3,2,7},
				{3,2,7},
				{3,7,7}};
		
		int d = getDet(myDet2, 3,0);
		
		MyNode nd = new MyNode();
		nd.AddNode(2);
		nd.AddNode(4);
		nd.AddNode(3);
		nd.AddNode(0);
		nd.AddNode(6);
		nd.AddNode(8);
		nd.AddNode(1);
		
		Cube myCube = new Cube(new int[][]{
				{1,1,3,3,2,7},
				{1,3,3,3,2,7},
				{1,3,4,3,7,7},
				{3,3,4,4,4,3},
				{0,3,3,3,4,3},
				{0,3,3,3,4,4}});
		myCube.GetCluster();
		System.out.println(myCube.GetHightest());
	
		List<List<Integer>> Cart = new ArrayList<List<Integer>>(); //(Arrays.asList(2,3,4,2),Arrays.asList(2,3,4,2),Arrays.asList(2,3,4,2));
		Cart.add(Arrays.asList(0,1));
		Cart.add(Arrays.asList(2,3));
		Cart.add(Arrays.asList(1,2,3));
		
		Catersian catersian = new Catersian(Cart);
		List<Integer> prod = catersian.getProduct(0);
		Meetings m = new Meetings(new int[]{3,3,4,3});
		int n = m.getMostMeet(8);
		
		Meet mm[] = new Meet[]{new Meet("Home",5),new Meet("School",3),new Meet("Play",2)};
		Scheduler sd = new Scheduler(mm);
		List<Meet> attend = sd.getMeetings(8);
		
		boolean b = checkStr("abcunicode of lowerK? case and upper case letters","ABCunicode of lower case and upper case letters");
		
		List<Meet> attend2 = new ArrayList<Meet>();
		attend2 = sd.getMaxMeetings(8,0,0);	

	}

	

	
	public static boolean checkStr(String s1, String s2){
		boolean chk = true;
		int n = (s1.length()> s2.length()? s2.length(): s1.length());
		
		for(int i1 = 0,i2 = 0; i1 < n && i2 <n; i1++,i2++){
			int m = s1.charAt(i1)- s2.charAt(i2);
			if(!(m == 0 || m == Math.abs(32)))
				if(chk){
					chk=false;
					if(s1.length() > s2.length()) i2--; else i1--;
				}else
					return false;
		}
		return true;
	}
}
