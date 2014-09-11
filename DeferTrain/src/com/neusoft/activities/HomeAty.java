package com.neusoft.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.mycustom.view.TitleView;
import com.neusoft.defertrain.R;

public class HomeAty extends Activity {

	private final static String LEFTICON_ACTION_STRING = "";
	private final static String RIGHTICON_ACTION_STRING = "";
	private final static String TRAIN_ACTION_STRING = "";
	private final static String TIPS_ACTION_STRING = "";
	private final static String FRIEND_ACTION_STRING = "";
	private final static String CENTER_ACTION_STRING = "";
	
	private TitleView titleView;
	private Button trainBtn;
	private Button tipsBtn;
	private Button friendBtn;
	private Button centerBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.home);
		
		findView();
		
		init();
		
		setOnClickListener();

	}

	private void findView() {
		// TODO Auto-generated method stub
		titleView = (TitleView) findViewById(R.id.mytitleview);
		trainBtn = (Button) findViewById(R.id.trainBtn);
		tipsBtn = (Button) findViewById(R.id.tipsBtn);
		friendBtn = (Button) findViewById(R.id.friendBtn);
		centerBtn = (Button) findViewById(R.id.centerBtn);
	}

	private void init() {
		// TODO Auto-generated method stub
		titleView = new TitleView(this);
		titleView.setTitleText(getString(R.string.homeTitle));
		titleView.setLeftIcon(R.drawable.ic_launcher);
		titleView.setRightIcon(R.drawable.ic_launcher);
		titleView.setLeftIconVisible(View.VISIBLE);
		titleView.setRightIconVisible(View.VISIBLE);
		titleView.setBackgroundColor(getResources().getColor(R.color.home_title));
	}
	
	private void setOnClickListener() {
		// TODO Auto-generated method stub
		
		titleView.setLeftIconOnClick(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(LEFTICON_ACTION_STRING);
				startActivity(intent);
			}
		});
		
		titleView.setRightIconOnClick(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(RIGHTICON_ACTION_STRING);
				startActivity(intent);
			}
		});
		
		trainBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(TRAIN_ACTION_STRING);
				startActivity(intent);
			}
		});
		
		tipsBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(TIPS_ACTION_STRING);
				startActivity(intent);
			}
		});
		
		friendBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(FRIEND_ACTION_STRING);
				startActivity(intent);
			}
		});
		
		centerBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(CENTER_ACTION_STRING);
				startActivity(intent);
			}
		});
	}
	
}
