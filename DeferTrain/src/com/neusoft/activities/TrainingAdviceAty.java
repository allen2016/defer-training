package com.neusoft.activities;

import com.mycustom.view.TitleView;
import com.neusoft.defertrain.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class TrainingAdviceAty extends Activity {

	//训练建议
	private TextView textView;
	private TitleView titleView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.trainingadvice);
		
		textView = (TextView)findViewById(R.id.textView1);
		titleView = (TitleView) findViewById(R.id.mytitleview);
		
        titleView.setLeftIconOnClick(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
//				intent.setAction(MAIN_ACTION_STRING);
				startActivity(intent);
				finish();
			}
		});
		textView.setText("");
	}
}
