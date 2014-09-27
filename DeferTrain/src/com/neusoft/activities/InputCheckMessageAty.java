package com.neusoft.activities;


import com.mycustom.view.TitleView;
import com.neusoft.defertrain.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class InputCheckMessageAty extends Activity{

	//输入验证信息
	private EditText message;
	
	private TitleView titleView;
	
	private final static String PERSONAL_ACTION_STRING = "android.intent.action.PERSONAL";
    private final static String INPUTM_ACTION_STRING = "android.intent.action.INPUTM";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.inputcheckmessage);
		
		message = (EditText)findViewById(R.id.message);
		final String s = message.toString();
		
		titleView = (TitleView) findViewById(R.id.mytitleview);
			
			titleView.setLeftIconOnClick(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					intent.setAction(PERSONAL_ACTION_STRING);
					startActivity(intent);
					finish();
				}
			});
			titleView.setRightTextOnClick(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					intent.putExtra("message", s);
					intent.setAction(INPUTM_ACTION_STRING);
					startActivity(intent);
				}
			});

		
	}
}
