package com.homelysoft.thenewboston;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InternalData extends Activity implements OnClickListener {

	EditText sharedData;
	TextView dataDisplay;
	Button save, load;
	FileOutputStream fos;
	String fileName = "InternalString";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shared_prefs);
		bridgeXML();
		setOnClickListeners();
		try {
			fos = openFileOutput(fileName, Context.MODE_PRIVATE);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
			/*
			 * Saving data via File File f=new File(fileName); try { fos=new
			 * FileOutputStream(f); //write something here fos.close(); } catch
			 * (FileNotFoundException e) { e.printStackTrace(); } catch
			 * (IOException e) { e.printStackTrace(); }
			 */
			try {
				fos = openFileOutput(fileName, Context.MODE_PRIVATE);
				fos.write(data.getBytes());
				fos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case R.id.b_pref_load:
			String collected=null;
			try {
				FileInputStream fis = openFileInput(fileName);
				byte[] dataArray = new byte[fis.available()];
				while (fis.read() != -1) {
					collected = new String(dataArray);
					fis.close();
					dataDisplay.setText(collected);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}

	}

}
