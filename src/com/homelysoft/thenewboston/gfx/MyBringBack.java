package com.homelysoft.thenewboston.gfx;

import javax.vecmath.Point3f;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

import com.homelysoft.thenewboston.R;

public class MyBringBack extends View {

	Bitmap ball;
	Point3f pos, vel, acce;

	Rect rect;
	Paint paint, fontPaint;

	Typeface font;

	public MyBringBack(Context context) {
		super(context);
		ball = BitmapFactory.decodeResource(getResources(), R.drawable.b_ball);
		pos = new Point3f(200, 500, 0);
		vel = new Point3f(0, 10, 0);
		acce = new Point3f(0, 1, 0);
		rect = new Rect();
		paint = new Paint();
		paint.setColor(Color.RED);
		fontPaint = new Paint();
		fontPaint.setARGB(100, 100, 0, 100);
		fontPaint.setTextAlign(Align.CENTER);
		fontPaint.setTextSize(25);
		font = Typeface.createFromAsset(context.getAssets(), "lazy.ttf");
		fontPaint.setTypeface(font);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.GREEN);

		canvas.drawText(vel.toString(), pos.getX(), pos.getY(), fontPaint);

		canvas.drawBitmap(ball, canvas.getWidth() / 2, pos.getY(), null);

		pos.add(vel);
		vel.add(acce);
		
		if (pos.getY() + ball.getHeight()/2 >= canvas.getHeight()) {
			vel.negate();
		}

		rect.set(0, (int) (canvas.getHeight() * 0.4), canvas.getWidth(),
				(int) (canvas.getHeight() * 0.6));

		canvas.drawRect(rect, paint);

		invalidate();
	}
}
