package com.homelysoft.thenewboston;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Email extends Activity implements View.OnClickListener {
	EditText personsEmail, intro, personsName, stupidThings, hatefulAction,
			outro;
	String emailAdd, beginning, name, stupidAction, hatefulAct, out;
	Button sendEmail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email);
		bridgeXML();
		sendEmail.setOnClickListener(this);
	}

	private void bridgeXML() {

		personsEmail = (EditText) findViewById(R.id.et_emails);
		intro = (EditText) findViewById(R.id.et_intro);
		personsName = (EditText) findViewById(R.id.et_name);
		stupidThings = (EditText) findViewById(R.id.et_things);
		hatefulAction = (EditText) findViewById(R.id.et_action);
		outro = (EditText) findViewById(R.id.et_outro);
		sendEmail = (Button) findViewById(R.id.b_send_email);
	}

	public void onClick(View v) {
		convertEditTextVarsIntoStringsAndYesThisIsAMethodWeCreated();
		String emailaddress[] = { emailAdd };
		String message = "Well hello "
				+ name
				+ " I just wanted to say "
				+ beginning
				+ ".  Not only that but I hate when you "
				+ stupidAction
				+ ", that just really makes me crazy.  I just want to make you "
				+ hatefulAct
				+ ".  Welp, thats all I wanted to chit-chatter about, oh and"
				+ out
				+ ".  Oh also if you get bored you should check out www.mybringback.com"
				+ '\n' + "PS. I think I love you...   :(";
		Intent emailIntent=new Intent(android.content.Intent.ACTION_SEND);
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, emailaddress);
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, beginning);
		emailIntent.setType("plain/text");
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
		startActivity(emailIntent);
	}

	private void convertEditTextVarsIntoStringsAndYesThisIsAMethodWeCreated() {
		emailAdd = personsEmail.getText().toString();
		beginning = intro.getText().toString();
		name = personsName.getText().toString();
		stupidAction = stupidThings.getText().toString();
		hatefulAct = hatefulAction.getText().toString();
		out = outro.getText().toString();
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}

}
