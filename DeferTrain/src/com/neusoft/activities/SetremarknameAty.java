package com.neusoft.activities;



import com.mycustom.view.TitleView;
import com.neusoft.defertrain.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class SetremarknameAty extends Activity{

    //设置备注名
	private EditText editText;
	
//	private final static String PERSONAL_ACTION_STRING = "android.intent.action.PERSONAL";
    private final static String ATTENT_ACTION_STRING = "android.intent.action.ATTENT";
    
    private TitleView titleView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.setremarkname);
		editText = (EditText)findViewById(R.id.editText1);
		
        titleView = (TitleView) findViewById(R.id.mytitleview);
		
		titleView.setLeftIconOnClick(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(ATTENT_ACTION_STRING);
				intent.putExtra("rename", editText.toString());
				startActivity(intent);
				finish();
			}
		});
		
	}
}