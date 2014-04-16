package com.homelysoft.thenewboston;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Preferences extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*
		 * if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
		 * onCreatePreferenceActivity(); } else { onCreatePreferenceFragment();
		 * }
		 */
		onCreatePreferenceActivity();
	}

	/**
	 * Wraps legacy {@link #onCreate(Bundle)} code for Android < 3 (i.e. API lvl
	 * < 11).
	 */
	@SuppressWarnings("deprecation")
	private void onCreatePreferenceActivity() {
		addPreferencesFromResource(R.xml.preferences);
	}
	
	/*
    *//**
	 * Wraps {@link #onCreate(Bundle)} code for Android >= 3 (i.e. API lvl >=
	 * 11).
	 */
	/*
	 * @TargetApi(Build.VERSION_CODES.HONEYCOMB) private void
	 * onCreatePreferenceFragment() { getFragmentManager().beginTransaction()
	 * .replace(android.R.id.content, new MyPreferenceFragment ()) .commit(); }
	 */
}