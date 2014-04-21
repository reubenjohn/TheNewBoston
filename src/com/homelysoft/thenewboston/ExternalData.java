package com.homelysoft.thenewboston;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ExternalData extends Activity implements OnClickListener,
		OnItemSelectedListener {

	private TextView canRead, canWrite;
	Spinner spinner;
	String state;
	boolean canR, canW;
	String[] paths = { "Music", "Pictures", "Download" };
	File path = null, file = null;
	EditText saveFile;
	Button confirm, save;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.external_data);
		bridgeXML();
		checkState();
		save.setVisibility(View.INVISIBLE);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				ExternalData.this, android.R.layout.simple_spinner_item, paths);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
		Toast.makeText(ExternalData.this, "Confirm pressed", Toast.LENGTH_LONG).show();
		save.setVisibility(View.VISIBLE);
	}

	protected void checkState() {
		state = Environment.getExternalStorageState();
		if (state.contentEquals(Environment.MEDIA_MOUNTED)) {
			canRead.setText("true");
			canWrite.setText("true");
			canW = canR = true;
		} else if (state.contentEquals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
			canRead.setText("true");
			canWrite.setText("false");
			canR = true;
			canW = false;
		} else {
			canRead.setText("false");
			canWrite.setText("false");
			canW = canR = false;
		}
	}

	protected void bridgeXML() {
		canRead = (TextView) findViewById(R.id.tv_can_read);
		canWrite = (TextView) findViewById(R.id.tv_can_write);
		spinner = (Spinner) findViewById(R.id.spinner1);
		saveFile = (EditText) findViewById(R.id.et_save_as);
		confirm = (Button) findViewById(R.id.b_external_data_confirm);
		save = (Button) findViewById(R.id.b_external_data_save);
	}

	protected void setOnClickListeners() {
		confirm.setOnClickListener(ExternalData.this);
		save.setOnClickListener(ExternalData.this);
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		int position = spinner.getSelectedItemPosition();
		switch (position) {
		case 0:
			path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
			break;
		case 1:
			path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			break;
		case 2:
			path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.b_external_data_confirm:
			Toast.makeText(ExternalData.this, "Confirm pressed", Toast.LENGTH_LONG).show();
			save.setVisibility(View.VISIBLE);
			break;
		case R.id.b_external_data_save:
			String f = saveFile.getText().toString();
			file = new File(path, f);
			checkState();
			if (canW == canR == true) {
				path.mkdirs();
				try {
					InputStream is=getResources().openRawResource(R.drawable.b_ball);
					OutputStream os=new FileOutputStream(file);
					byte[] data=new byte[is.available()];
					is.read(data);
					os.write(data);
					os.close();
					is.close();
					Toast toast=Toast.makeText(ExternalData.this, "File has been Saved", Toast.LENGTH_LONG);
					toast.show();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			break;
		}
	}

}
