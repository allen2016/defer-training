package com.neusoft.activities;


import view.ChartView;

import com.mycustom.view.TitleView;
import com.neusoft.defertrain.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GraphAty extends Activity {

	//曲线图
	private final static String WEEKT_ACTION_STRING = "android.intent.action.WEEKT";
	private TitleView titleView;
    private ChartView chartView;
    private Button btn1,btn2,btn3,btn4;
    private String piaoti;
    private int shu[] = null ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.graph);
		Intent intent =getIntent();
		 piaoti = intent.getStringExtra("piaoti");
		 shu = intent.getIntArrayExtra("shuzi");
		
		titleView = (TitleView) findViewById(R.id.mytitleview);
        chartView = (ChartView) findViewById(R.id.mychartview);
        btn1 = (Button)findViewById(R.id.button1);
        btn2 = (Button)findViewById(R.id.button2);
        btn3 = (Button)findViewById(R.id.button3);
        btn4 = (Button)findViewById(R.id.button4);
        chartView.SetInfo(new String[] { "", "10", "40", "70", "100" }, // X轴刻度
				new String[] { "0", "10", "40", "70", "100" }, // Y轴刻度
				shu, // 数据
				piaoti);
		
		titleView.setLeftIconOnClick(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					intent.setAction(WEEKT_ACTION_STRING);
					startActivity(intent);
				}
			});
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				chartView.SetInfo(new String[] { "", "10", "40", "70", "100" }, // X轴刻度
						new String[] { "0", "10", "40", "70", "100" }, // Y轴刻度
						shu, // 数据
						piaoti);
			}
		});
       btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				chartView.SetInfo(new String[] { "", "10", "40", "70", "100" }, // X轴刻度
						new String[] { "0", "10", "40", "70", "100" }, // Y轴刻度
						shu, // 数据
						piaoti);
			}
		});
        btn3.setOnClickListener(new OnClickListener() {
	
	    @Override
	       public void onClick(View v) {
		  // TODO Auto-generated method stub
	    	chartView.SetInfo(new String[] { "", "10", "40", "70", "100" }, // X轴刻度
					new String[] { "0", "10", "40", "70", "100" }, // Y轴刻度
					shu, // 数据
					piaoti);
	       }
       });
        btn4.setOnClickListener(new OnClickListener() {
	
	      @Override
	     public void onClick(View v) {
		// TODO Auto-generated method stub
	    	  chartView.SetInfo(new String[] { "", "10", "40", "70", "100" }, // X轴刻度
						new String[] { "0", "10", "40", "70", "100" }, // Y轴刻度
						shu, // 数据
						piaoti);
	       }
          });
	}
}
