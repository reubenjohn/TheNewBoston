package com.homelysoft.thenewboston;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Data extends Activity implements View.OnClickListener{
	
	Button start, startFor;
	EditText send;
	TextView receivedAnswer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send);
		bridgeXML();
		setOnclickListeners();
		
	}
	
	public void bridgeXML()
	{
		start=(Button)findViewById(R.id.b_start_activity);
		startFor=(Button)findViewById(R.id.b_start_activity_for_result);
		send=(EditText)findViewById(R.id.et_send);
		receivedAnswer=(TextView)findViewById(R.id.tv_got);
	}
	
	public void setOnclickListeners()
	{
		start.setOnClickListener(this);
		startFor.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		switch(v.getId())
		{
		case R.id.b_start_activity:
			Bundle basket = new Bundle();
			basket.putString("myString", send.getText().toString());
			Intent sendData=new Intent(Data.this,OpenedClass.class);
			sendData.putExtras(basket);
			startActivity(sendData);
			break;
		case R.id.b_start_activity_for_result:
			Intent intent=new Intent(Data.this,OpenedClass.class);
			startActivityForResult(intent, 0);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==RESULT_OK)
		{
			Bundle backpack=data.getExtras();
			String user_choice=backpack.getString("SELECTED_NOUN");
			receivedAnswer.setText(user_choice);
		}
	}

}