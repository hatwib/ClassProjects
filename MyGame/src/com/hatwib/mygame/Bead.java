package com.hatwib.mygame;

import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.content.Context;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;


public class Bead extends ImageView{
	int position[] = new int[2];
	int nextCount;
	Animation animationSlide;
	public Barrow pBarrow;
	int dir;
	public int preSideSteps;
	public int preUpSteps;
	public int nextSteps;
	boolean isMoving;
	public Bead(Context context) {
		super(context);
		nextSteps = 0;	
	}
	
	public Bead(Context context, AttributeSet attrs){
		super(context, attrs);
	        // TODO Auto-generated constructor stub
		nextSteps = 0;	
	    }
	
	public Bead(Context context, int r,int h,int c,Barrow currentBarrow){
		super(context);
		pBarrow = currentBarrow;
		position[0] = r;
		position[1] = h;
		this.isMoving = false;
		preSideSteps = 0;
		preUpSteps = 0;
		nextSteps = 0;
		FrameLayout.LayoutParams p=new FrameLayout.LayoutParams(25,25);
		p.gravity = Gravity.TOP;
		p.leftMargin= (c*10)+currentBarrow.leftMargin;
		p.topMargin= (c*10)+currentBarrow.topMargin;
		this.setLayoutParams(p);	

	}
	
	public void PickBead() {
		
	}

	public LayoutParams GetParams(){
		return (FrameLayout.LayoutParams) this.getLayoutParams();		
	}

	public void SetParams(FrameLayout.LayoutParams beadparam){
		this.setLayoutParams(beadparam);
	}
	
	public void MoveBead(int steps) {
			if(position[0] == 1 || position[0] == 2)
				dir = 1;
			else
				dir = -1;
        	if (position[0] == 1 || position[0] == 3){
        		
        		if(steps > position[1] && !(position[1] == 0 && steps==1)){
					if(position[1] == 0 && steps > 1){
						nextSteps  = steps - 1;
						steps--;
					}else{
						nextSteps  = steps - position[1];
						steps = position[1];
						}
					this.isMoving = true;
				}else{
					this.bringToFront();
					this.isMoving = false;
				}

        		if (position[1] == 0){
					position[0]--;
					
					animationSlide = new TranslateAnimation(preSideSteps*85,preSideSteps*85,preUpSteps*85,-85);
					preUpSteps--;
					
					animationSlide.setInterpolator(new AccelerateDecelerateInterpolator());
					animationSlide.setFillAfter(true);
			        animationSlide.setDuration(500);	
			        
			        MyAnimationListener listener=new MyAnimationListener(this,getContext());
			        animationSlide.setAnimationListener(listener);
		        
			        this.startAnimation(animationSlide);
				}
				else{
					position[1] = position[1]-steps;

					animationSlide = new TranslateAnimation(preSideSteps*85,(preSideSteps*85)+(-85*steps),preUpSteps*85,preUpSteps*85);
					preSideSteps = preSideSteps-steps;
					
					animationSlide.setInterpolator(new AccelerateDecelerateInterpolator());
					animationSlide.setFillAfter(true);
			        animationSlide.setDuration(1000*steps);	
			        
			        MyAnimationListener listener=new MyAnimationListener(this,getContext());
			        animationSlide.setAnimationListener(listener);
			        
					this.startAnimation(animationSlide);
				}
        		}else if(position[0] == 0 || position[0] == 2){
					if(steps > (7-position[1]) && !(position[1] == 7 && steps == 1) ){
						if(position[1] == 7 && steps > 1){
							nextSteps  = steps - 1;
						}else{
							nextSteps  = steps-(7-position[1]);
							steps = 7-position[1];
						}
						this.isMoving = true;
					}else {
						this.bringToFront();
						this.isMoving = false;
					}
					if(position[1] == 7){
				        position[0]++;
						animationSlide = new TranslateAnimation(preSideSteps*85,preSideSteps*85,preUpSteps*85,85);
				        preUpSteps++;        
						animationSlide.setInterpolator(new AccelerateDecelerateInterpolator());
						animationSlide.setFillAfter(true);
				        animationSlide.setDuration(500);	

				        MyAnimationListener listener=new MyAnimationListener(this,getContext());
				        animationSlide.setAnimationListener(listener);
				      
						this.startAnimation(animationSlide);
					}
					else{
						position[1] = position[1] + steps;

						animationSlide = new TranslateAnimation(preSideSteps*85,(preSideSteps*85)+(85*steps),preUpSteps*85,preUpSteps*85);
						preSideSteps = preSideSteps+steps;
						
						animationSlide.setInterpolator(new AccelerateDecelerateInterpolator());
						animationSlide.setFillAfter(true);
				        animationSlide.setDuration(1000*steps);	
				        
				        MyAnimationListener listener=new MyAnimationListener(this,getContext());
				        animationSlide.setAnimationListener(listener);
				        this.startAnimation(animationSlide);
					}			
        		} 				
		}
		

	

	 
    
}
