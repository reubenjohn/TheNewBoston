package com.homelysoft.thenewboston;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class OpenedClass extends Activity implements View.OnClickListener,
		android.widget.RadioGroup.OnCheckedChangeListener {
	TextView question, test;
	Button returnData;
	RadioGroup nounSelect;
	String receivedBread,data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.receive);
		bridgeXML();
		setOnClickListeners();
		
		SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		String name=preferences.getString("name", "Reuben");
		String values=preferences.getString("list", "4");
		
		if(values.contentEquals("1"))
		{
			question.setText(name+" is...");
		}
		
		/*Bundle recievedBasket=getIntent().getExtras();
		receivedBread=recievedBasket.getString("myString");
		question.setText(receivedBread);*/
	}

	public void bridgeXML() {
		question = (TextView) findViewById(R.id.tv_noun_prompt);
		test = (TextView) findViewById(R.id.tv_text);
		returnData = (Button) findViewById(R.id.b_return);
		nounSelect = (RadioGroup) findViewById(R.id.rg_noun_select);
	}

	public void setOnClickListeners() {
		returnData.setOnClickListener(this);
		nounSelect.setOnCheckedChangeListener(this);
	}

	@Override
	public void onClick(View v) {
		Bundle backpack=new Bundle();
		Intent person=new Intent();
		backpack.putString("SELECTED_NOUN", data);
		person.putExtras(backpack);
		setResult(RESULT_OK,person);
		finish();

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.rg_noun_selection_1:
			data=getResources().getString(R.string.rg_noun_selection_comment_1);
			break;
		case R.id.rg_noun_selection_2:
			data=getResources().getString(R.string.rg_noun_selection_comment_2);
			break;
		case R.id.rg_noun_selection_3:
			data=getResources().getString(R.string.rg_noun_selection_comment_3);
			break;
		}
		test.setText(data);
	}
}
