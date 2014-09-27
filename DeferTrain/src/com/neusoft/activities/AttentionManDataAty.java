package com.neusoft.activities;

import com.mycustom.entity.items.Friend;
import com.mycustom.view.TitleView;
import com.neusoft.defertrain.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AttentionManDataAty extends Activity {

	//关注人明细
	private TextView name,sex,age,address,emila,lasttime,data1,data2,data3;
	private Button hudong;
	private ImageView head;
	private TitleView titleView;
	 
	private final static String MYATT_ACTION_STRING = "android.intent.action.MYATT";
	private final static String SETRE_ACTION_STRING = "android.intent.action.SETRE";
	private final static String INTER_ACTION_STRING = "com.neusoft.cao.interaction";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.attentionmandata);
		
		name = (TextView)findViewById(R.id.name);
		sex = (TextView)findViewById(R.id.sex);
		age = (TextView)findViewById(R.id.age);
		address = (TextView)findViewById(R.id.address);
		emila = (TextView)findViewById(R.id.emila);
		lasttime = (TextView)findViewById(R.id.lasttime);
		data1 = (TextView)findViewById(R.id.data1);
		data2 = (TextView)findViewById(R.id.data2);
		data3 = (TextView)findViewById(R.id.data3);	
		hudong = (Button)findViewById(R.id.hudong);
		head = (ImageView)findViewById(R.id.head);

        titleView = (TitleView) findViewById(R.id.mytitleview);

		titleView.setLeftIconOnClick(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(MYATT_ACTION_STRING);
				startActivity(intent);
				finish();
			}
		});
		titleView.setRightTextOnClick(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(SETRE_ACTION_STRING);
				startActivity(intent);
			}
		});
		
		Friend friend = new Friend();
		name.setText("");
		sex.setText(friend.getSex());
		age.setText(friend.getBirthday());
		address.setText("");
		emila.setText(friend.getEmail());
		lasttime.setText(friend.getLastTraningDate());
		data1.setText(friend.getThisWeekFinish()+"/"+friend.getThisWeekTask());
		data2.setText(friend.getFinishPercentage());
		data3.setText(friend.getMark());
		head.setImageResource(R.drawable.brain);
		
		Intent intents = getIntent();
		String s = intents.getStringExtra("rename");
		if(s != null){
		name.setText("");
		}
		
		hudong.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(INTER_ACTION_STRING);
				startActivity(intent);
			}
		});
		
	}
	
}
