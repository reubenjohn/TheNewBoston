package com.homelysoft.thenewboston.gfx;


import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class GFX extends Activity{

	MyBringBack myView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		/*PowerManager pM=(PowerManager)getSystemService(Context.POWER_SERVICE);
		WakeLock wl=pM.newWakeLock(, "whatever");*/
		myView=new MyBringBack(this);
		setContentView(myView);
		
	}

}
