package com.homelysoft.thenewboston;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

public class MyBringBack extends View {

	Bitmap ball;
	
	public MyBringBack(Context context) {
		super(context);
		ball=BitmapFactory.decodeResource(getResources(), R.drawable.b_ball);
	}

}
