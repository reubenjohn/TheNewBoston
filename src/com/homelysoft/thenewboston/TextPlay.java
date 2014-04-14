package com.homelysoft.thenewboston;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TextPlay extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_play);
		
		Button checkCommand=(Button)findViewById(R.id.check_button);
		ToggleButton passwordToggle=(ToggleButton)findViewById(R.id.password_tb);
		TextView display=(TextView)findViewById(R.id.command_display_tv);
		
	}

}
