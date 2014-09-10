package com.neusoft.activities;

import com.mycustom.view.TitleView;
import com.neusoft.defertrain.R;

import android.app.Activity;
import android.os.Bundle;

public class HomeAty extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.example_layout);
		
		TitleView titleView = (TitleView) findViewById(R.id.mytitleview);
		titleView.setTitleText("这是标题栏");
		titleView.setBackgroundColor(getResources().getColor(R.color.vpi__background_holo_dark));
	}

	
	
}
