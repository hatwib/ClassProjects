package ver1.findpath;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import android.widget.ImageView;

import android.widget.RelativeLayout;

public class Box extends ImageView {

	private final static int START_DRAGGING = 0;
	private final static int STOP_DRAGGING = 1;
	private Box bx;
	private int status;
	private int dLeft;
	private int dTop;
	
	public Box(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public Box(Context context, AttributeSet attrs) {
		super(context, attrs);

		this.setDrawingCacheEnabled(true);
		bx = this;
		this.setOnTouchListener(new OnTouchListener() { 

			public boolean onTouch(View view, MotionEvent me) {

				RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams) bx.getLayoutParams();;
				
					    if (me.getAction() == MotionEvent.ACTION_DOWN) {
					        status = START_DRAGGING;
					        dLeft = (int) (me.getRawX()- mParams.leftMargin);
					        dTop =  (int) (me.getRawY()- mParams.topMargin);
					    }
					    if (me.getAction() == MotionEvent.ACTION_UP) {
					        status = STOP_DRAGGING;
					        //Code to position box in center of graph, still thinking of best way
					        
					        
					    } else if (me.getAction() == MotionEvent.ACTION_MOVE) {
					        if (status == START_DRAGGING) {
					        	mParams.leftMargin  = (int) me.getRawX()-dLeft; //Had to remove dLeft because of top title. 
					        	mParams.topMargin  = (int) me.getRawY()-dTop; //same with side space
					        	bx.setLayoutParams(mParams); 
					        }
					    }
					    return true;
					
			}
		
	});

	}
	
}
