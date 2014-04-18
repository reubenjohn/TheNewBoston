package com.homelysoft.thenewboston;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPrefs extends Activity implements OnClickListener {

	EditText sharedData;
	TextView dataDisplay;
	Button save, load;
	public static String fileName = "MySharedString";
	SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shared_prefs);
		bridgeXML();
		setOnClickListeners();
		sharedPreferences = getSharedPreferences(fileName, 0);
	}

	private void bridgeXML() {
		sharedData = (EditText) findViewById(R.id.et_prefs);
		dataDisplay = (TextView) findViewById(R.id.tv_prefs_status);
		save = (Button) findViewById(R.id.b_pref_save);
		load = (Button) findViewById(R.id.b_pref_load);
	}

	private void setOnClickListeners() {
		save.setOnClickListener(this);
		load.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.b_pref_save:
			String data = sharedData.getText().toString();
			SharedPreferences.Editor editor=sharedPreferences.edit();
			editor.putString("sharedString", data);
			editor.commit();
			break;
		case R.id.b_pref_load:
			sharedPreferences = getSharedPreferences(fileName, 0);
			String dataBack=sharedPreferences.getString("sharedString", "Load failed");
			dataDisplay.setText(dataBack);
			break;
		}

	}

}
