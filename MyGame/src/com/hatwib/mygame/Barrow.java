package com.hatwib.mygame;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.util.AttributeSet;
import android.content.Context;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;


public class Barrow  extends ImageView{
	int position[] = new int[2];
	ArrayList<Barrow> capturedBarrow;
	int topMargin;
	int leftMargin;
	public int countBarrows;
	public Boolean isMoving;
	public Boolean myTurn;
	public 	ArrayList<Bead> beadArysNew; 

	Barrow parentBarrow;
	Barrow childBarrow;
	Barrow prevBarrow;
	ArrayList<Bead> beadArys;
	public String imgBarrow;
	ArrayList<Barrow> barrowArys;
	Animation animationSlide;
	
	public Barrow(Context context) {
		super(context);	
		isMoving = false;
	}

	public Barrow(Context context, AttributeSet attrs){
		super(context, attrs);
	        // TODO Auto-generated constructor stub
		isMoving = false;
	    }
	public Barrow(Context context, int r,int h,FrameLayout frameLayout, String imgName,Barrow pBarrow,int c){
		super(context);

		beadArys = new ArrayList<Bead>();
		capturedBarrow  = new ArrayList<Barrow>();
		
		isMoving = false;
		imgBarrow = imgName;
		parentBarrow = pBarrow;
			try{
			FrameLayout.LayoutParams p=new FrameLayout.LayoutParams(78,78);
			
			position[0] = r;
			position[1] = h;
			p.gravity=Gravity.TOP;
			topMargin= 25+(r*85);
			leftMargin= 27+(h*85);
			p.leftMargin= leftMargin;
			p.topMargin= topMargin;
			this.setLayoutParams(p);
			
			
			int bwid = getResources().getIdentifier(imgBarrow , "drawable", context.getPackageName());
			this.setImageResource(bwid);
			this.setId(c++);
			
			frameLayout.addView(this);
					
			
			for(int k = 1; k < 3; k++){
				Bead bead1 = new Bead (getContext(),r,h,k,this);
				int bdid = getResources().getIdentifier("bead4" , "drawable", context.getPackageName());
				bead1.setImageResource(bdid);
				AddBead(this,bead1,1); 
				frameLayout.addView(bead1);
				}

				if(r == 1 || r == 3)
					if(h == 0)
						r--;
					else if(h == 1) //original point
						r=-1;
					else
						h--;
				else if(r == 0 || r == 2)
					if(h == 7) //TODO changed from 7 to 4
						r++;
					else
						h++;
				if(r >= 0)
					childBarrow = new Barrow(context,r,h,frameLayout,imgBarrow, this,c);
				else{
					childBarrow = this.parentBarrow.parentBarrow.parentBarrow.parentBarrow.parentBarrow.parentBarrow.parentBarrow.parentBarrow.parentBarrow.parentBarrow.parentBarrow.parentBarrow.parentBarrow.parentBarrow.parentBarrow;
					//childBarrow = AssignChild(this);
					
					//this.parentBarrow.parentBarrow.parentBarrow.parentBarrow.parentBarrow.parentBarrow.parentBarrow.parentBarrow.parentBarrow.parentBarrow.parentBarrow.parentBarrow.parentBarrow.parentBarrow.parentBarrow.parentBarrow = this;
					AssignParent(this,this);
				   }
			}catch(Exception ex){
				//System.out.println(ex.getMessage());
			}
	}
	
	public void AssignParent(Barrow barrow,Barrow pBarrow){
		if (barrow.parentBarrow == null)
			barrow.parentBarrow = pBarrow;
		else 
			AssignParent(barrow.parentBarrow,pBarrow);
	}

	public Barrow AssignChild(Barrow pbarrow){
		if (pbarrow.parentBarrow == null)
			return pbarrow;
		else
		return AssignChild(pbarrow.parentBarrow);
		
	}
	
	
	public void AddBead(Barrow thisBarrow,Bead newbead,int count){
		if(count==1){
			thisBarrow.beadArys.add(newbead);
			newbead.pBarrow = thisBarrow;
		}
		else
		{
			count--;
			AddBead(thisBarrow.childBarrow,newbead,count);
		}
			
	}
	
	public Barrow GetBarrow(Barrow b, int c){
		if(c > 0){
			c--;
			return GetBarrow(b,c);
		}else if(c < 0){
			c++;
			return GetBarrow(b,c);
		}else
			return b;
	}
	
	public void MoveBeads(){
		
		int x = this.beadArys.size();
		Second.lastBeadPos[0]= this.position[0];
        Second.lastBeadPos[1]= this.position[1];
        Second.countBead = x;
		this.isMoving = false;
		while(x > 0){
			if((Second.lastBeadPos[0] == 0 && Second.lastBeadPos[1]==7) || (Second.lastBeadPos[0] == 2 && Second.lastBeadPos[1]==7))
				Second.lastBeadPos[0]++;
			else if((Second.lastBeadPos[0] == 1 && Second.lastBeadPos[1]==0) || (Second.lastBeadPos[0] == 3 && Second.lastBeadPos[1]==0))
				Second.lastBeadPos[0]--;
			else if((Second.lastBeadPos[0] == 0 && Second.lastBeadPos[1] < 7) || (Second.lastBeadPos[0] == 2 && Second.lastBeadPos[1] < 7))
				Second.lastBeadPos[1]++;
			else
				Second.lastBeadPos[1]--;
			x--;
			
		}
		//ArrayList<Bead> beadArysNew = null;
		beadArysNew = new ArrayList<Bead>();
		beadArysNew.addAll(this.beadArys);
		this.beadArys.clear();
		for(Bead b: beadArysNew){
			x++;
			AddBead(this.childBarrow,b,x);
			b.MoveBead(x);
		}
		beadArysNew.clear();
		beadArysNew = null;
		
		prevBarrow = this;
		while(x>0)
		{	x--;		
			prevBarrow = prevBarrow.parentBarrow;
		}
		
		
		this.isMoving=true;
	}

	public boolean CaptureBeads(){
		boolean answer = false;

		if((position[0] == 1 || position[0] == 2) && (capturedBarrow.get(0).beadArys.size()>0 && capturedBarrow.get(1).beadArys.size()>0)){
			for(Barrow bw1: capturedBarrow){
				for(Bead a : bw1.beadArys){
					a.bringToFront();
					AddBead(this,a,1);
					animationSlide = new TranslateAnimation(0,0,0,this.getTop()-a.getTop());
					animationSlide.setInterpolator(new AccelerateDecelerateInterpolator());
					animationSlide.setFillAfter(true);
					animationSlide.setDuration(700);	        
					a.position[0] = this.position[0];
					MyAnimationListener listener=new MyAnimationListener(a,getContext());
			        animationSlide.setAnimationListener(listener);

					a.startAnimation(animationSlide);
					
				}
			bw1.beadArys.clear();
			}
			for(Barrow bw2: capturedBarrow){
				for(Bead b : bw2.beadArys){
					
					b.bringToFront();
					AddBead(this,b,1);
					animationSlide = new TranslateAnimation(0,0,0,this.getTop()-b.getTop());
					animationSlide.setInterpolator(new AccelerateDecelerateInterpolator());
					animationSlide.setFillAfter(true);
					animationSlide.setDuration(700);	    
					b.position[0] = this.position[0];
					MyAnimationListener listener=new MyAnimationListener(b,getContext());
			        animationSlide.setAnimationListener(listener);

					b.startAnimation(animationSlide);
					
				}
				bw2.beadArys.clear();
			}
				answer = true;
			}else
				answer = false;
		return answer;
		
		
	}

	
	@Override
    public boolean onTouchEvent(MotionEvent event)
    {
		
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if(((this.position[0] <= 1 && Second.turnToPlay == 3) || (this.position[0] >= 2 && Second.turnToPlay == 0) )) //Has played
				Toast.makeText(this.getContext(), "It is not your turn to play", Toast.LENGTH_SHORT).show();
			else if(this.beadArys.size() < 2)
				Toast.makeText(this.getContext(), "You must move at least 2 beads", Toast.LENGTH_SHORT).show();
			else{
				int img = getResources().getIdentifier("barrow_select" , "drawable", this.getContext().getPackageName());
				this.setImageResource(img);
				Toast.makeText(this.getContext(), this.beadArys.size()+ " Steps Moved...", Toast.LENGTH_SHORT).show();
				
				MoveBeads();
				img = getResources().getIdentifier(imgBarrow , "drawable", this.getContext().getPackageName());
				this.setImageResource(img);
				Second.turnToPlay = -1*(Second.turnToPlay-3);

			}
			return true;
		}
		return super.onTouchEvent(event);
    } 
	
	public void PositionBead(){
		int x = 0, y=1;
		for(Bead a : this.beadArys){
			a.bringToFront();
			FrameLayout.LayoutParams mParams = (FrameLayout.LayoutParams) a.getLayoutParams();
			animationSlide = new TranslateAnimation(0,0,0,this.getTop()-a.getTop());
			animationSlide.setInterpolator(new BounceInterpolator());
			animationSlide.setFillAfter(true);
			animationSlide.setDuration(100);	        
			a.startAnimation(animationSlide);
			mParams.topMargin = this.topMargin+(this.getHeight()/2)+x;
			mParams.leftMargin = this.topMargin+(this.getWidth()/2)+x;
			x=(x+5*y);
			y=y*-1;
//			a.setLayoutParams(mParams);
			}
		
	}
	
}
