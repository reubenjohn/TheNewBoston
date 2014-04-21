package com.homelysoft.thenewboston;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SQLite extends Activity implements OnClickListener {

	Button update, view, getInfo, edit, delete;
	EditText name, id;
	SeekBar scale;
	TextView display;
	long row;
	String nameString, hotness = "5";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqllite);
		bridgeXML();
		setupListeners();
		scale.setMax(10);
	}

	protected void bridgeXML() {
		update = (Button) findViewById(R.id.b_sql_update);
		view = (Button) findViewById(R.id.b_sql_view);
		name = (EditText) findViewById(R.id.et_sql_name);
		scale = (SeekBar) findViewById(R.id.sb_hotness_scale);
		display = (TextView) findViewById(R.id.tv_sql_hotness_display);
		id = (EditText) findViewById(R.id.et_sql_id);
		getInfo = (Button) findViewById(R.id.b_sql_info);
		edit = (Button) findViewById(R.id.b_sql_edit);
		delete = (Button) findViewById(R.id.b_sql_delete);

	}

	protected void setupListeners() {
		update.setOnClickListener(this);
		view.setOnClickListener(this);
		getInfo.setOnClickListener(this);
		edit.setOnClickListener(this);
		delete.setOnClickListener(this);
		scale.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {

			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {

			}

			@Override
			public void onProgressChanged(SeekBar seekbar, int progress,
					boolean fromUser) {
				hotness = Integer.toString(progress + 1);
				display.setText("You picked " + hotness);
			}
		});

	}

	protected void updateInputs() {
		nameString = this.name.getText().toString();
		row = Long.parseLong(id.getText().toString());
	}

	@Override
	public void onClick(View v) {
		boolean didItWork = true;
		switch (v.getId()) {
		case R.id.b_sql_update:
			updateInputs();
			try {
				HotOrNot entry = new HotOrNot(SQLite.this);
				entry.open();
				entry.createEntry(nameString, hotness);
				entry.close();
			} catch (Exception e) {
				e.printStackTrace();
				didItWork = false;
				Dialog d = new Dialog(this);
				d.setTitle("Dang it!");
				TextView tv = new TextView(this);
				tv.setText(e.toString());
				d.setContentView(tv);
				d.show();

			} finally {
				if (didItWork) {
					Dialog d = new Dialog(this);
					d.setTitle("Heck yea!");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.setContentView(tv);
					d.show();

				}
			}
			break;
		case R.id.b_sql_view:
			Intent i = new Intent("com.homelysoft.thenewboston.SQLView");
			startActivity(i);
			break;
		case R.id.b_sql_info:
			updateInputs();
			HotOrNot hon;
			try {
				hon = new HotOrNot(this);
				hon.open();

				name.setText(hon.getName(row));
				scale.setProgress(Integer.parseInt(hon.getHotness(row)));

				hon.close();
			} catch (Exception e) {
				e.printStackTrace();
				didItWork = false;
				Dialog d = new Dialog(this);
				d.setTitle("Dang it!");
				TextView tv = new TextView(this);
				tv.setText(e.toString());
				d.setContentView(tv);
				d.show();

			} finally {
				if (didItWork) {
					Dialog d = new Dialog(this);
					d.setTitle("Heck yea!");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.setContentView(tv);
					d.show();

				}
			}
			break;

		case R.id.b_sql_edit:
			updateInputs();

			try {
				hon = new HotOrNot(this);
				hon.open();
				hon.updateEntry(row, nameString, hotness);
				hon.close();
			} catch (Exception e) {
				e.printStackTrace();
				didItWork = false;
				Dialog d = new Dialog(this);
				d.setTitle("Dang it!");
				TextView tv = new TextView(this);
				tv.setText(e.toString());
				d.setContentView(tv);
				d.show();

			} finally {
				if (didItWork) {
					Dialog d = new Dialog(this);
					d.setTitle("Heck yea!");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.setContentView(tv);
					d.show();

				}
			}
			break;
		case R.id.b_sql_delete:
			updateInputs();
			try {
				hon = new HotOrNot(this);
				hon.open();
				hon.deleteEntry(row);
				hon.close();
			} catch (Exception e) {
				e.printStackTrace();
				didItWork = false;
				Dialog d = new Dialog(this);
				d.setTitle("Dang it!");
				TextView tv = new TextView(this);
				tv.setText(e.toString());
				d.setContentView(tv);
				d.show();

			} finally {
				if (didItWork) {
					Dialog d = new Dialog(this);
					d.setTitle("Heck yea!");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.setContentView(tv);
					d.show();

				}
			}
			break;

		}

	}
}
