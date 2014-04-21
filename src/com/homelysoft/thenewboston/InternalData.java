package com.homelysoft.thenewboston;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
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
	String fileName = "internal_string";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shared_prefs);
		bridgeXML();
		setOnClickListeners();
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
			dataDisplay.setText("Yes, you tapped load button");
			new loadStuff().execute(fileName);
			break;
		}

	}

	public class loadStuff extends AsyncTask<String, Integer, String> {
		
		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			dialog=new ProgressDialog(InternalData.this);
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			dialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			String collected = null;
			FileInputStream fis = null;
						
			try {
				fis = openFileInput(fileName);
				byte[] dataArray = new byte[fis.available()];
				dialog.setMax(fis.available());
				while (fis.read(dataArray) != -1) {
					publishProgress(dataArray.length);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				collected = new String(dataArray);
				dialog.dismiss();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fis.close();
					return collected;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... progress) {
			dialog.incrementProgressBy(progress[0]);

		}

		protected void onPostExecute(String result) {
			dataDisplay.setText(result);
		}

	}

}
