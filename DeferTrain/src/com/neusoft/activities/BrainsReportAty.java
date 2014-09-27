package com.neusoft.activities;

import com.mycustom.view.TitleView;
import com.neusoft.defertrain.R;
import com.neusoft.defertrain.R.color;
import com.neusoft.defertrain.R.drawable;

import com.neusoft.fragment.BrainsReportFrgt1;
import com.neusoft.fragment.BrainsReportFrgt2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.sip.SipAudioCall.Listener;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("NewApi") public class BrainsReportAty extends Activity {
//脑力报告
	private TitleView titleView;
	private TextView name,time,text1,text2;
	private ImageView head;
    private BrainsReportFrgt1 fm1;
    private BrainsReportFrgt2 fm2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.brainsreport);
		
		titleView = (TitleView) findViewById(R.id.mytitleview);
		name = (TextView)findViewById(R.id.name);
		time = (TextView)findViewById(R.id.time);
		text1 = (TextView)findViewById(R.id.text1);
		text2 = (TextView)findViewById(R.id.text2);
		head = (ImageView)findViewById(R.id.head);
		
		fm1 = new BrainsReportFrgt1();
		fm2 = new BrainsReportFrgt2();
		final Intent intents = getIntent();
		
		titleView.setLeftIconOnClick(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				double s = Double.parseDouble(intents.getStringExtra("s"));
				Intent intent = new Intent();
				if (s == 0) {
					intent.setClass(BrainsReportAty.this, MyAttentionpAty.class);
					startActivity(intent);
					finish();
				}
				intent.setClass(BrainsReportAty.this, AttentionManDataAty.class);
				startActivity(intent);
				finish();
			}
		});
		
		
		
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.add(R.id.frame, fm1).commit();
		text1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				text1.setBackgroundResource(color.home_btn2);
				text2.setBackgroundResource(drawable.biankuan);
				FragmentTransaction ft = getFragmentManager().beginTransaction().replace(R.id.frame	, fm1);
						ft.commit();
			}
		});
		text2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				text1.setBackgroundResource(drawable.biankuan);
				text2.setBackgroundResource(color.home_btn2);				
				FragmentTransaction ft = getFragmentManager().beginTransaction().replace(R.id.frame	, fm2);
						ft.commit();
			}
		});
		
		
	}

}
