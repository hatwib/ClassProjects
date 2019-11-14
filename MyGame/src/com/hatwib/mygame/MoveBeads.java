package com.hatwib.mygame;

import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.FrameLayout;
import android.app.Activity;


public class MoveBeads extends Activity  {

	
	public MoveBeads(ImageView e){
//		ImageView imageView = (ImageView) findViewById(e);
		FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) e.getLayoutParams();
		lp.leftMargin = 100;

	}
	
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        // TODO Auto-generated method stub

	    //this.MoveBeads();
		return false;
    }  
}
