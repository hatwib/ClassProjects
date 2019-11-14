package com.hatwib.mygame;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MyAnimationListener implements AnimationListener {
	Bead mView;
	FrameLayout.LayoutParams mParams;
	Context mContext;

	public MyAnimationListener(Bead v,Context c){
		mParams = (FrameLayout.LayoutParams) v.getLayoutParams();
	    mView=v;
	    mContext=c;
	}

	@Override
	public void onAnimationEnd(Animation arg0) {
			mView.clearAnimation();

			if(mView.isMoving){
//				Toast.makeText(mContext, "nextSteps = "+mView.nextSteps+" previous slide is = "+mView.preSideSteps+" and previous up ="+mView.preUpSteps, Toast.LENGTH_SHORT).show();
				mView.MoveBead(mView.nextSteps);
			}
			else{
				try {
					mParams.topMargin = (8)+mView.pBarrow.topMargin+(mView.pBarrow.beadArys.size()*3);
					mParams.leftMargin = (8)+mView.pBarrow.leftMargin+(mView.pBarrow.beadArys.size()*3);
					Second.countBead--;
				
					Thread.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mView.setLayoutParams(mParams);
//				mView.pBarrow.PositionBead();
				mView.preSideSteps = 0;
				mView.preUpSteps = 0;
				
				if(!(Second.lastBeadPos==null) && (mView.pBarrow.position[0]==Second.lastBeadPos[0]) && (mView.pBarrow.position[1]==Second.lastBeadPos[1])){
//					Second.lastBeadPos = null;
					
					if( mView.pBarrow.capturedBarrow.size() == 2 ){
						mView.pBarrow.CaptureBeads();
						mView.pBarrow.isMoving=false;
					}

					//mView.pBarrow.MoveBeads();
					if(Second.turnToPlay == 0 && Second.countBead==0){
						if(Second.ForceMove(Second.barrow1, 16))
							Second.turnToPlay = -1*(Second.turnToPlay-3);
						else 
							Toast.makeText(mContext,"Now next player can move",Toast.LENGTH_SHORT).show();

					}
				}
					
			}
//	    Toast.makeText(mContext, "left="+mView.getLeft()+" against "+mView.pBarrow.leftMargin+" and ntop="+mParams.leftMargin, Toast.LENGTH_SHORT).show();
	}
	

	@Override
	public void onAnimationRepeat(Animation arg0) {
		// TODO Auto-generated method stub

	}
	

	@Override
	public void onAnimationStart(Animation arg0) {
		// TODO Auto-generated method stub
	}

}
