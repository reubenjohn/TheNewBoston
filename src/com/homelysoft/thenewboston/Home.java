package com.homelysoft.thenewboston;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Home extends ListActivity {

	String classes[] = { "StartingPoint", "Splash", "Home", "TextPlay",
			"Email", "Photo", "Data", "gfx.GFX", "gfx.GFXSurface",
			"SoundPoolPlay", "Slider", "Tabs", "Flipper", "SharedPrefs",
			"InternalData", "ExternalData", "SQLite", "Accelerate" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(Home.this,
				android.R.layout.simple_list_item_1, classes));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		try {
			Class<?> ourClass = Class.forName("com.homelysoft.thenewboston."
					+ classes[position]);
			Intent ourIntent = new Intent(Home.this, ourClass);
			startActivity(ourIntent);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater blowUp = getMenuInflater();
		try {
			blowUp.inflate(R.menu.home_menu, menu);
		} catch (InflateException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent i;
		switch (item.getItemId()) {
		case R.id.mi_about_us:
			i = new Intent("com.homelysoft.thenewboston.ABOUT_US");
			startActivity(i);
			break;
		case R.id.mi_preferences:
			i = new Intent("com.homelysoft.thenewboston.PREFERENCES");
			startActivity(i);
			break;
		case R.id.mi_exit:
			finish();
			break;
		}
		return false;
	}

}
