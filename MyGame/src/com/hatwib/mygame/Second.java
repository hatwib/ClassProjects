package com.hatwib.mygame;


import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.FrameLayout;


public class Second extends Activity {
	
	public static Barrow barrow1; 
	public static Barrow barrow2;
	public static int turnToPlay;
	public static int lastBeadPos[] = new int[2];
	public static int countBead = 0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		FrameLayout frameLayout = (FrameLayout)findViewById(R.id.FrameLayoutIdInXml);
		turnToPlay = 3;

		barrow1 = new Barrow(getBaseContext(),1,0,frameLayout,"barrow1",null,1);
		
		barrow2 = new Barrow(getBaseContext(),3,0,frameLayout,"barrow2",null,17);
		AssignCaptureID(barrow1,barrow2.childBarrow);
		


	}
	
	public void AssignCaptureID(Barrow b1, Barrow b2){

		if(b1.position[0] == 1 && b2.position[0] == 2){
			b1.capturedBarrow.add(b2);
			b2.capturedBarrow.add(b1);
			AssignCaptureID(b1.parentBarrow,b2.childBarrow);
		}
		else if(b1.position[0] == 0 && b2.position[0] == 3){	
				AssignCaptureID(b1,b2.parentBarrow);

				//AssignCaptureID(b1.childBarrow,b2);

			}else if(b1.position[0] == 0 && b2.position[0] == 2 && b2.capturedBarrow.size() < 2){
				b2.capturedBarrow.add(b1);
				AssignCaptureID(b1.parentBarrow,b2.parentBarrow);
			}else if(b1.position[0] == 1 && b2.position[0] == 3 && b1.capturedBarrow.size() < 2){
				b1.capturedBarrow.add(b2);
				AssignCaptureID(b1.parentBarrow,b2.parentBarrow);
			}
	}
	public static Barrow canCaptureFrom(Barrow a){
		Barrow tmp;
		tmp = a.parentBarrow;
		int i = 1;
		while(!(tmp==a)){
			i++;
			tmp = tmp.parentBarrow;
			if(tmp.beadArys.size()== i)
				return tmp;
		}
		return null;		
	}

	public static boolean ForceMove(Barrow b,int i){
		if(i==0){
			return false;
		}else if(i==16){
			Barrow a =  b;
			Barrow d = null;
			int z = 0;
			while(a.position[0]==1){
				Barrow x =  a.capturedBarrow.get(0);
				Barrow y =  a.capturedBarrow.get(1);
				if((x.beadArys.size()+y.beadArys.size()) > z && !(canCaptureFrom(a)==null)){
					z = x.beadArys.size()+y.beadArys.size();
					d = canCaptureFrom(a);
				}
				a = a.parentBarrow;
			}
			if(!(d==null)){
				b=d;
				b.MoveBeads();
				return true;
			//  .dispatchTouchEvent(); //MotionEvent.ACTION_DOWN);
			}else{
			i--;
			ForceMove(b.childBarrow,i);
			}
		}else if(b.beadArys.size() > 1){
			b.MoveBeads();
			return true;
		}else{
			i--;
			ForceMove(b.childBarrow,i);
		}
		
			return true;
	}
	
	
	
	
}
