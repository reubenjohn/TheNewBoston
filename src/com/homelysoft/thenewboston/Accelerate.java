package com.homelysoft.thenewboston;

import javax.vecmath.Point3f;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Accelerate extends Activity implements SensorEventListener {

	Point3f pos,sensorInput,center;
	Bitmap ball;
	SensorManager sm;
	MySurfaceView mySurfaceView;
	
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
				center.set(canvas.getWidth()-ball.getWidth(), canvas.getWidth()-ball.getHeight(), 0);
				center.scale(0.5f);
				canvas.drawBitmap(ball, center.getX()-sensorInput.getX()*50, center.getY()+sensorInput.getY()*50, null);
				holder.unlockCanvasAndPost(canvas);
				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mySurfaceView=new MySurfaceView(this);
		setContentView(mySurfaceView);
		
		mySurfaceView.resume();
		sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		if (sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
			Sensor s=sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			sm.registerListener(this, s,SensorManager.SENSOR_DELAY_NORMAL);
			ball=BitmapFactory.decodeResource(getResources(), R.drawable.b_ball);
			pos=new Point3f(0,0,0);
			sensorInput=new Point3f(0,0,0);
			center=new Point3f(0,0,0);
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		sm.unregisterListener(this);
	}



	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		sensorInput.set(event.values[0],event.values[1],0);
	}

}
