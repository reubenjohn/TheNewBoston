package com.homelysoft.thenewboston;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Splash extends Activity{


	MediaPlayer splashSound;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		splashSound=MediaPlayer.create(Splash.this, R.raw.splash_sound_shortened);
		splashSound.start();
		Thread timer=new Thread(){
			@Override
			public void run() {
				super.run();
				try{
					sleep(4000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent openStaringPoint=new Intent("com.homelysoft.thenewboston.HOME");
					startActivity(openStaringPoint);
				}
			}
			
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		splashSound.release();
		finish();
	}

}
