package com.homelysoft.thenewboston;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

@SuppressWarnings("deprecation")
public class Slider extends Activity implements OnClickListener,
		OnCheckedChangeListener, OnDrawerOpenListener {

	Button dummy1,dummy2,dummy3,dummy4;
	SlidingDrawer sd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sliding);
		bridgeXML();
		setOnClickListeners();
		sd=(SlidingDrawer)findViewById(R.id.sliding_drawer);
		sd.setOnDrawerOpenListener(this);
	}

	private void bridgeXML() {
		dummy1 = (Button) findViewById(R.id.b_dummy1);
		dummy2 = (Button) findViewById(R.id.b_dummy2);
		dummy3 = (Button) findViewById(R.id.b_dummy3);
		dummy4 = (Button) findViewById(R.id.b_dummy4);
	}

	private void setOnClickListeners() {
		dummy1.setOnClickListener(this);
		dummy2.setOnClickListener(this);
		dummy3.setOnClickListener(this);
		dummy4.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.b_dummy1:
			sd.open();
			break;
		case R.id.b_dummy2:
			sd.close();
			break;
		case R.id.b_dummy3:
			sd.toggle();
			break;
		case R.id.b_dummy4:
			break;

		}

	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			sd.unlock();

		} else {
			sd.lock();

		}

	}

	@Override
	public void onDrawerOpened() {
		MediaPlayer mp=MediaPlayer.create(this, R.raw.splash_sound_shortened);
		mp.start();
	}

}
