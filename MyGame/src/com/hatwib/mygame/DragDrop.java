package com.hatwib.mygame;

import java.util.ArrayList;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DragDrop extends Activity {
	public ArrayList<Box> boxList;
	public static ArrayList<Box> path;
	public static int pathid;
	public static boolean pickEnabled = false;
	public static boolean foundPath;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drag_drop);
		path = new ArrayList<Box>();
		foundPath = false;
		

		Button pick = (Button) findViewById(R.id.widget51);
		pick.setOnClickListener(new Button.OnClickListener() {  
		public void onClick(View arg0) {
			path.clear();
			pickEnabled = true;
			foundPath = false;
		}
     });


		
		Button refresh = (Button) findViewById(R.id.widget53);
		refresh.setOnClickListener(new Button.OnClickListener() {  
		public void onClick(View arg0) {
			path.clear();
			foundPath = false;
			Box start_b = (Box) findViewById(R.id.white40);
			int id;
			for(int i=4; i>=0; i--)
				for(int j=0; j<5; j++){
					id = getResources().getIdentifier("white"+i+j, "id", start_b.getContext().getPackageName());
					Box next_bx = (Box) findViewById(id);
					next_bx.setBackgroundResource(R.drawable.white);
					next_bx.VISITED = false;
					
					}

		}
     });
		
		Button bt = (Button) findViewById(R.id.widget52);
		bt.setOnClickListener(new Button.OnClickListener() {  
		public void onClick(View arg0) {
			Box start_b = (Box) findViewById(R.id.white40);
			path.clear();
			//foundPath = false;
			int id;
			for(int i=4; i>=0; i--)
				for(int j=0; j<5; j++){
					id = getResources().getIdentifier("white"+i+j, "id", start_b.getContext().getPackageName());
					Box next_bx = (Box) findViewById(id);
					int x=i;
					int y=j;
						if(i < 4){
							x=i+1;
							y=j;
							id = getResources().getIdentifier("white"+x+y, "id", start_b.getContext().getPackageName());
							next_bx.adjacencies.add((Box) findViewById(id));
						}if(i > 0){
							x=i-1;
							y=j;
							id = getResources().getIdentifier("white"+x+y, "id", start_b.getContext().getPackageName());
							next_bx.adjacencies.add((Box) findViewById(id));
						}	
						if(j < 4){
							y=j+1;
							x=i;
						id = getResources().getIdentifier("white"+x+y, "id", start_b.getContext().getPackageName());
						next_bx.adjacencies.add((Box) findViewById(id));
						}					
						if(j > 0){
							y=j-1;
							x=i;
							id = getResources().getIdentifier("white"+x+y, "id", start_b.getContext().getPackageName());
							next_bx.adjacencies.add((Box) findViewById(id));
							}					
					}
			
			Box end_b = (Box) findViewById(R.id.white04);
			
			start_b.getPathTo(end_b);
		
			if(path.size() < 1)
				Toast.makeText(start_b.getContext(),"No Path Found, Press Refresh",Toast.LENGTH_SHORT).show();	
			else{
				
				start_b.setBackgroundResource(R.drawable.green);
				int w = 1;
			for(Box p : path){
						p.setBackgroundResource(R.drawable.green);
				}
			}
		}
     });
		
		
	}


}
