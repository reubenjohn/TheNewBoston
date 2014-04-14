package com.homelysoft.thenewboston;

import com.homelysoft.thenewboston.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class StartingPoint extends Activity {
	int counter;
	Button add, sub;
	TextView display;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.starting_point);
		counter = 0;
		add = (Button) findViewById(R.id.addB);
		sub = (Button) findViewById(R.id.subB);
		display = (TextView) findViewById(R.id.counter_display);
		updateDisplay(0);

		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				updateDisplay(++counter);
			}
		});
		sub.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				updateDisplay(--counter);
			}
		});
	}
	public void updateDisplay(int newCounter)
	{
		display.setText(getResources().getString(R.string.total_display)+" "+newCounter);
	}
}
