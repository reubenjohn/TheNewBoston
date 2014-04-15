package com.homelysoft.thenewboston;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TextPlay extends Activity implements View.OnClickListener {

	Button checkCommand;
	ToggleButton passwordToggle;
	EditText commandInput;
	TextView display;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_play);

		bridgeXML();

		setOnClickListeners();
	}

	private void bridgeXML() {
		checkCommand = (Button) findViewById(R.id.b_run_command);
		passwordToggle = (ToggleButton) findViewById(R.id.tb_password);
		commandInput = (EditText) findViewById(R.id.et_command);
		display = (TextView) findViewById(R.id.tv_command_display);
	}

	private void setOnClickListeners() {
		checkCommand.setOnClickListener(this);
		passwordToggle.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.b_run_command:
			String input = commandInput.getText().toString();
			display.setText(input);
			if (input.contentEquals("left")) {
				display.setGravity(Gravity.LEFT);
			} else if (input.contentEquals("center")) {
				display.setGravity(Gravity.CENTER);
			} else if (input.contentEquals("right")) {
				display.setGravity(Gravity.RIGHT);
			} else if (input.contentEquals("blue")) {
				display.setTextColor(Color.BLUE);
			} else if (input.contentEquals("WTF")) {
				Random crazy = new Random();
				display.setText("WTF!!!");
				display.setTextSize(crazy.nextInt(100));
				display.setTextColor(Color.rgb(crazy.nextInt(255),
						crazy.nextInt(255), crazy.nextInt(255)));
				switch (crazy.nextInt(3)) {
				case 0:
					display.setGravity(Gravity.LEFT);
					break;
				case 1:
					display.setGravity(Gravity.CENTER);
					break;
				case 2:
					display.setGravity(Gravity.RIGHT);
					break;

				}
			} else {
				display.setText(getResources().getString(
						R.string.command_display_default));
				display.setTextColor(Color.BLACK);
			}
			break;
		case R.id.tb_password:
			if (passwordToggle.isChecked()) {
				commandInput.setInputType(InputType.TYPE_CLASS_TEXT
						| InputType.TYPE_TEXT_VARIATION_PASSWORD);
			} else {
				commandInput.setInputType(InputType.TYPE_CLASS_TEXT);
			}
			break;
		}
	}

}
