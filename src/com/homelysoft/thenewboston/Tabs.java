package com.homelysoft.thenewboston;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class Tabs extends Activity implements OnClickListener{

	TabHost th;
	TabSpec specs;
	Button newTab,start,stop;
	Button go,forward,backward,refresh,clear_history;
	TextView display;
	long strt=0,stp=0;
	WebView browser;
	EditText url;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		bridgeXML();
		setupTabs();
		setOnClickListeners();
		browser.getSettings().setJavaScriptEnabled(true);
		browser.getSettings().setLoadWithOverviewMode(true);
		browser.getSettings().setUseWideViewPort(true);
		browser.setWebViewClient(new OurViewClient());
		try
		{
			browser.loadUrl("http://www.mybringback.com");
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	protected void setOnClickListeners()
	{
		newTab.setOnClickListener(this);
		start.setOnClickListener(this);
		stop.setOnClickListener(this);
		go.setOnClickListener(this);
		forward.setOnClickListener(this);
		backward.setOnClickListener(this);
		refresh.setOnClickListener(this);
		clear_history.setOnClickListener(this);
		
	}
	
	protected void bridgeXML()
	{
		th = (TabHost) findViewById(R.id.tabhost);
		newTab = (Button) findViewById(R.id.b_add_tab);
		start = (Button) findViewById(R.id.b_tab1_start);
		stop = (Button) findViewById(R.id.b_tab1_stop);
		display=(TextView)findViewById(R.id.tv_stop_watch_result);
		browser=(WebView)findViewById(R.id.wv_browser);
		url=(EditText)findViewById(R.id.et_set_address);
		go = (Button) findViewById(R.id.browser_go);
		forward = (Button) findViewById(R.id.browser_forward);
		backward = (Button) findViewById(R.id.browser_backward);
		refresh = (Button) findViewById(R.id.browser_refresh);
		clear_history = (Button) findViewById(R.id.browser_clear_history);
	}
	
	protected void setupTabs()
	{
		th.setup();
		specs=th.newTabSpec("tag");
		specs.setContent(R.id.tab1);
		specs.setIndicator("StopWatch");
		th.addTab(specs);
		specs=th.newTabSpec("tag");
		specs.setContent(R.id.tab2);
		specs.setIndicator("Tab2");
		th.addTab(specs);
		specs=th.newTabSpec("tag");
		specs.setContent(R.id.tab3);
		specs.setIndicator("Add a Tab");
		th.addTab(specs);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.b_add_tab:
			specs=th.newTabSpec("tag");
			specs.setContent(new TabHost.TabContentFactory() {
				
				@Override
				public View createTabContent(String arg0) {
					TextView text=new TextView(Tabs.this);
					text.setText("Text created in Java");
					return text;
				}
			});
			specs.setIndicator("New");
			th.addTab(specs);
			break;
		case R.id.b_tab1_start:
			strt=System.currentTimeMillis();
			break;
		case R.id.b_tab1_stop:
			stp=System.currentTimeMillis();
			if(strt!=0)
			{
				long result=stp-strt;
				int ms=(int)result%1000;
				int sec=(int)(result/1000)%60;
				int min=(int)(result/1000*60)%3600;
				display.setText(String.format("%d:%02d.%02d", min,sec,ms));
			}
			break;
		case R.id.browser_go:
			String website=url.getText().toString();
			browser.loadUrl(website);
			InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(url.getWindowToken(),0);
			break;
		case R.id.browser_forward:
			if(browser.canGoForward())
				browser.goForward();
			break;
		case R.id.browser_backward:
			if(browser.canGoBack())
				browser.goBack();
			break;
		case R.id.browser_refresh:
			browser.refreshDrawableState();
			break;
		case R.id.browser_clear_history:
			browser.clearHistory();
			break;
		}
		
	}

}
