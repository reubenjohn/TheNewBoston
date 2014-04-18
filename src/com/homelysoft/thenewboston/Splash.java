package com.homelysoft.thenewboston;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Splash extends Activity {

	MediaPlayer splashSound;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		final Intent openStaringPoint = new Intent(
				"com.homelysoft.thenewboston.HOME");
		Thread timer = new Thread() {
			@Override
			public void run() {
				super.run();
				try {
					sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					startActivity(openStaringPoint);
				}
			}

		};
		
		SharedPreferences getPreferences = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());
		String splash_selection = getPreferences.getString("splash", "1");
		if (splash_selection.contentEquals("1")) {

			timer.start();
			splashSound = MediaPlayer.create(Splash.this,
					R.raw.splash_sound_shortened);
			splashSound.start();

		} else if (splash_selection.contentEquals("2")) {

			timer.start();

		}
		else if (splash_selection.contentEquals("3")) {
			startActivity(openStaringPoint);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (splashSound != null)
			splashSound.release();
		finish();
	}

}
