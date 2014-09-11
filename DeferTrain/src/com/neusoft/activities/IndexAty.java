package com.neusoft.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.neusoft.defertrain.R;

public class IndexAty extends Activity {

	private final static String LOGIN_ACTION_STRING = "";
	private final static String REGISTER_ACTION_STRING = "";
	
	private Button loginBtn;
	private Button registerBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.index);
		
		findView();
		
		setOnClickListener();
		
//		TitleView titleView = (TitleView) findViewById(R.id.mytitleview);
//		titleView.setTitleText("这是标题栏");
//		titleView.setBackgroundColor(getResources().getColor(R.color.vpi__background_holo_dark));
	}

	private void findView() {
		// TODO Auto-generated method stub
		loginBtn = (Button) findViewById(R.id.loginBtn);
		registerBtn = (Button) findViewById(R.id.registerBtn);
	}

	private void setOnClickListener() {
		// TODO Auto-generated method stub
		loginBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(LOGIN_ACTION_STRING);
				startActivity(intent);
			}
		});
		
		registerBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(REGISTER_ACTION_STRING);
				startActivity(intent);
			}
		});
	}
	
}
