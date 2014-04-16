package com.homelysoft.thenewboston;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

public class GFX extends Activity{

	MyBringBack myView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		myView=new MyBringBack(this);
		
	}

}
