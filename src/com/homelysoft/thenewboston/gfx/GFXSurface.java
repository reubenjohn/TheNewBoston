package com.homelysoft.thenewboston.gfx;

import javax.vecmath.Point3f;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

import com.homelysoft.thenewboston.R;

public class GFXSurface extends Activity implements OnTouchListener {

	MySurfaceView mySurfaceView;
	Point3f pos, start, finish, invalidPoint, speed, animate, scale, acce;
	Bitmap ball;
	Paint circlePaint;
	boolean showCursor = false, showBall = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mySurfaceView = new MySurfaceView(this);
		mySurfaceView.setOnTouchListener(this);
		setContentView(mySurfaceView);
		pos = new Point3f(0, 0, 0);
		invalidPoint = new Point3f(0, 0, 0);
		start = new Point3f(0, 0, 0);
		finish = new Point3f(0, 0, 0);
		speed = new Point3f(0, 0, 0);
		animate = new Point3f(0, 0, 0);
		scale = new Point3f(0, 0, 0);
		acce = new Point3f(0, 0.1f, 0);
		ball = BitmapFactory.decodeResource(getResources(), R.drawable.b_ball);
		circlePaint = new Paint();
		circlePaint.setColor(Color.GREEN);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mySurfaceView.pause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mySurfaceView.resume();
	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		pos.setX(event.getX());
		pos.setY(event.getY());
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			start.set(pos);
			showCursor = true;
			break;
		case MotionEvent.ACTION_UP:
			finish.setX(event.getX());
			finish.setY(event.getY());
			animate.set(pos);
			speed.set(start);
			speed.sub(finish);
			speed.scale(0.05f);
			showCursor = false;
			showBall = true;
			break;
		}
		return true;
	}

	public class MySurfaceView extends SurfaceView implements Runnable {

		SurfaceHolder holder;
		Thread thread = null;
		private boolean isRunning = false;

		public MySurfaceView(Context context) {
			super(context);
			holder = getHolder();
		}

		public void pause() {
			isRunning = false;
			while (true) {
				try {
					thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}
			thread = null;
		}

		public void resume() {
			isRunning = true;
			thread = new Thread(this);
			thread.start();

		}

		@Override
		public void run() {
			while (isRunning) {
				if (!holder.getSurface().isValid()) {
					continue;
				}
				Canvas canvas = holder.lockCanvas();
				canvas.drawRGB(02, 200, 200);

				animate.add(speed);
				speed.add(acce);
				if (animate.getY() + ball.getHeight()/2 >= canvas.getHeight()) {
					speed.negate();
				}
				
				if (showCursor) {
					canvas.drawCircle(pos.getX(), pos.getY(), 30, circlePaint);
				}
				if (!pos.equals(invalidPoint)) {
					canvas.drawCircle(start.getX(), start.getY(), 10,
							circlePaint);
					canvas.drawCircle(finish.getX(), finish.getY(), 10,
							circlePaint);
					if (showBall)
						canvas.drawBitmap(ball,
								animate.getX() - ball.getWidth() / 2,
								animate.getY() - ball.getHeight() / 2, null);
				}
				holder.unlockCanvasAndPost(canvas);
				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

	}

}
