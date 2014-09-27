package com.neusoft.activities;

import java.util.Calendar;

import com.mycustom.view.TitleView;
import com.neusoft.defertrain.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class PlanAccomplishCaseAty extends Activity {
	
	//计划完成情况
	private ImageView head;
	private TextView name,accdata,time;
	private TextView accday1,unday1,ratio1;
	private TextView accday2,unday2,ratio2;
	private TextView accday3,unday3,ratio3;
	private TextView accday4,unday4,ratio4;
	private TitleView titleView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.planaccomplishcase);
		
        titleView = (TitleView) findViewById(R.id.mytitleview);
        head = (ImageView)findViewById(R.id.head);
        name =(TextView)findViewById(R.id.name);
        accdata = (TextView)findViewById(R.id.accdata);
        time = (TextView)findViewById(R.id.time);
        
        accday1 = (TextView)findViewById(R.id.accday1);
        unday1 = (TextView)findViewById(R.id.unday1);
        ratio1 = (TextView)findViewById(R.id.ratio1);
        
        accday2 = (TextView)findViewById(R.id.accday2);
        unday2 = (TextView)findViewById(R.id.unday2);
        ratio2 = (TextView)findViewById(R.id.ratio2);
        
        accday3 = (TextView)findViewById(R.id.accday3);
        unday3 = (TextView)findViewById(R.id.unday3);
        ratio3 = (TextView)findViewById(R.id.ratio3);
        
        accday4 = (TextView)findViewById(R.id.accday4);
        unday4 = (TextView)findViewById(R.id.unday4);
        ratio4 = (TextView)findViewById(R.id.ratio4);
        final Intent intents = getIntent();
        titleView.setLeftIconOnClick(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				double s = Double.parseDouble(intents.getStringExtra("s"));
				Intent intent = new Intent();
				if (s == 0) {
					intent.setClass(PlanAccomplishCaseAty.this, MyAttentionpAty.class);
					startActivity(intent);
					finish();
				}
				intent.setClass(PlanAccomplishCaseAty.this, AttentionManDataAty.class);
				startActivity(intent);
				finish();
			}
		});
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);;//几号
	       int year = Calendar.getInstance().get(Calendar.YEAR);//年
	       int month =  Calendar.getInstance().get(Calendar.MONTH) + 1;//月
//        head.setImageResource();
        name.setText("");
        accdata.setText("今日计划完成"+"");
        time.setText(year+"年"+month+"月"+day+"日");
        int a1 = 0;
        int u1 = 0;
        double r1 = a1 / (a1+u1) ;
        accday1.setText(Integer.toString(a1));
        unday1.setText(Integer.toString(u1));
        ratio1.setText(Double.toString(r1));
        
        int a2 = 0;
        int u2 = 0;
        double r2 = a1 / (a2+u2) ;
        accday2.setText(Integer.toString(a2));
        unday2.setText(Integer.toString(u2));
        ratio2.setText(Double.toString(r2));
        
        int a3 = 0;
        int u3 = 0;
        double r3 = a1 / (a3+u3) ;
        accday3.setText(Integer.toString(a3));
        unday3.setText(Integer.toString(u3));
        ratio3.setText(Double.toString(r3));
        
        int a4 = 0;
        int u4 = 0;
        double r4 = a1 / (a4+u4) ;
        accday4.setText(Integer.toString(a4));
        unday4.setText(Integer.toString(u4));
        ratio4.setText(Double.toString(r4));
	}

}
