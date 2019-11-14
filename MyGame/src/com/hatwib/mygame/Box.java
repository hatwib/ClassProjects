package com.hatwib.mygame;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.Toast;


public class Box extends ImageView {

	public Vertex target;
	public double weight;
	public ArrayList<Box> adjacencies; // = new Box;
	boolean VISITED = false;
	

	
	public Box(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public Box(Context context, AttributeSet attrs) {
		super(context, attrs);

		this.setDrawingCacheEnabled(true);
		adjacencies = new ArrayList<Box>();
		final Box bx = this;
		weight = 1;
		bx.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View arg0) {
				if(DragDrop.pickEnabled){
				if(!bx.VISITED){
					bx.setBackgroundResource(R.drawable.red);
					bx.VISITED = true;
				}
				else{
					bx.setBackgroundResource(R.drawable.white);
					bx.VISITED = false;
				}
				}else{
					
					Toast.makeText(getContext(),"Press Pick Node Button!!!",Toast.LENGTH_SHORT).show();
				}
			}
	     });
	}
	
    public boolean getPathTo(Box end_box)
    {
    	//DragDrop.path.clear();
    	
        for (Box b : adjacencies){
        	if(!b.VISITED){
        		b.VISITED=true;
	        	if(b == end_box){
	        		DragDrop.path.add(b);
	        		DragDrop.foundPath = true;
	        		return DragDrop.foundPath;
	        	}else if(b.getPathTo(end_box)){
	        		if(DragDrop.foundPath) DragDrop.path.add(b);
	        		return DragDrop.foundPath;
	        	}
	        	
        	}
        	else if(DragDrop.foundPath)
        		break;
        }
        return false;
		
    }

		
	
}
