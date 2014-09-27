package com.neusoft.activities;


import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mycustom.tools.ChineseCalendar;
import com.mycustom.view.TitleView;
import com.neusoft.defertrain.R;
import com.neusoft.tools.SpecialCalendar;

public class WeekTrainingPlanAty extends Activity {

	//本周训练计划
	private TitleView titleView;
	private TextView weeks,time;
	private TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7;
	private TextView text1,text2,text3,text4,text5,text6,text7;
	private TextView tet1,tet2,tet3,tet4,tet5,tet6,tet7;
	private TextView times1,times2,plan1,plan2,accomplish1,accomplish2;
	private ProgressBar  proBar1, proBar2, proBar3, proBar4, proBar5, proBar6, proBar7;
	private LinearLayout lay1,lay2,lay3,lay4,lay5,lay6,lay7;
	SpecialCalendar sp = new SpecialCalendar();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.weektrainingplan);
		
	    titleView = (TitleView)findViewById(R.id.mytitleview);
		weeks = (TextView)findViewById(R.id.week);
		time = (TextView)findViewById(R.id.time);
		//显示公历
		txt1 = (TextView)findViewById(R.id.week1);
		txt2 = (TextView)findViewById(R.id.week2);
		txt3 = (TextView)findViewById(R.id.week3);
		txt4 = (TextView)findViewById(R.id.week4);
		txt5 = (TextView)findViewById(R.id.week5);
		txt6 = (TextView)findViewById(R.id.week6);
		txt7 = (TextView)findViewById(R.id.week7);
		//显示农历
		text1 = (TextView)findViewById(R.id.day1);
		text2 = (TextView)findViewById(R.id.day2);
		text3 = (TextView)findViewById(R.id.day3);
		text4 = (TextView)findViewById(R.id.day4);
		text5 = (TextView)findViewById(R.id.day5);
		text6 = (TextView)findViewById(R.id.day6);
		text7 = (TextView)findViewById(R.id.day7);
		//显示比列
		tet1 = (TextView)findViewById(R.id.percent1);
		tet2 = (TextView)findViewById(R.id.percent2);
		tet3 = (TextView)findViewById(R.id.percent3);
		tet4 = (TextView)findViewById(R.id.percent4);
		tet5 = (TextView)findViewById(R.id.percent5);
		tet6 = (TextView)findViewById(R.id.percent6);
		tet7 = (TextView)findViewById(R.id.percent7);
		
		times1 = (TextView)findViewById(R.id.times1);
		plan1 = (TextView)findViewById(R.id.plan1);
		accomplish1 = (TextView)findViewById(R.id.accomplish1);
		times2 = (TextView)findViewById(R.id.times2);
		plan2 = (TextView)findViewById(R.id.plan2);
		accomplish2 = (TextView)findViewById(R.id.accomplish2);
		//圆形进度条
		proBar1 = (ProgressBar)findViewById(R.id.progressBar1);
		proBar2 = (ProgressBar)findViewById(R.id.progressBar2);
		proBar3 = (ProgressBar)findViewById(R.id.progressBar3);
		proBar4 = (ProgressBar)findViewById(R.id.progressBar4);
		proBar5 = (ProgressBar)findViewById(R.id.progressBar5);
		proBar6 = (ProgressBar)findViewById(R.id.progressBar6);
		proBar7 = (ProgressBar)findViewById(R.id.progressBar7);
		//边框
		lay1 = (LinearLayout)findViewById(R.id.lay1);
		lay2 = (LinearLayout)findViewById(R.id.lay2);
		lay3 = (LinearLayout)findViewById(R.id.lay3);
		lay4 = (LinearLayout)findViewById(R.id.lay4);
		lay5 = (LinearLayout)findViewById(R.id.lay5);
		lay6 = (LinearLayout)findViewById(R.id.lay6);
		lay7 = (LinearLayout)findViewById(R.id.lay7);
		
		lay1.setOnClickListener(laylistener);
		lay2.setOnClickListener(laylistener);
		lay3.setOnClickListener(laylistener);
		lay4.setOnClickListener(laylistener);
		lay5.setOnClickListener(laylistener);
		lay6.setOnClickListener(laylistener);
		lay7.setOnClickListener(laylistener);
		
		final Intent intents = getIntent();
		  int week = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);//本星期的第几天
	       int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);;//几号
	       int year = Calendar.getInstance().get(Calendar.YEAR);//年
	       int month =  Calendar.getInstance().get(Calendar.MONTH) + 1;//月
	       boolean b = sp.isLeapYear(year);//是否闰年
	       int datas = sp.getDaysOfMonth(b, month);//当月的天数
	       int latsdatas = sp.getDaysOfMonth(b, month-1);//上个月的天数
	       
	      
		  
	       time.setText(Integer.toString(year)+"年"+Integer.toString(month)+"月"+Integer.toString(day)+"日");
	       
	       switch (week) {
		   case 1:
			   weeks.setText("星期日"); 
			   lay1.setBackgroundResource(R.color.white);
			    break;
		   case 2:
			   weeks.setText("星期一");
			   lay2.setBackgroundResource(R.color.white);
				break;
		   case 3:
			   weeks.setText("星期二");
			   lay3.setBackgroundResource(R.color.white);
				break;
		   case 4:
			   weeks.setText("星期三");
			   lay4.setBackgroundResource(R.color.white);
				break;
		   case 5:
			   weeks.setText("星期四");
			   lay5.setBackgroundResource(R.color.white);
				break;
		   case 6:
			   weeks.setText("星期五");
			   lay6.setBackgroundResource(R.color.white);
				break;
		   case 7:
			   weeks.setText("星期六");
			   lay7.setBackgroundResource(R.color.white);
				break;
		}
	       

	       
	       switch (week) {
		     case 1:
		    	 txt1.setText(Integer.toString(day));
		    	 text1.setText(lunarday(year,month,day));
					if (day+6 < datas) {
						txt2.setText(Integer.toString(day+1));
						text2.setText(lunarday(year,month,day+1));
						txt3.setText(Integer.toString(day+2));
						text3.setText(lunarday(year,month,day+2));
						txt4.setText(Integer.toString(day+3));
						text4.setText(lunarday(year,month,day+3));
						txt5.setText(Integer.toString(day+4));
						text5.setText(lunarday(year,month,day+4));
						txt6.setText(Integer.toString(day+5));
						text6.setText(lunarday(year,month,day+5));
						txt7.setText(Integer.toString(day+6));
						text7.setText(lunarday(year,month,day+6));
	                
					}
					if (day+5 < datas) {
						txt2.setText(Integer.toString(day+1));
						text2.setText(lunarday(year,month,day+1));
						txt3.setText(Integer.toString(day+2));
						text3.setText(lunarday(year,month,day+2));
						txt4.setText(Integer.toString(day+3));
						text4.setText(lunarday(year,month,day+3));
						txt5.setText(Integer.toString(day+4));
						text5.setText(lunarday(year,month,day+4));
						txt6.setText(Integer.toString(day+5));
						text6.setText(lunarday(year,month,day+5));
						txt7.setText("1");
						text7.setText(lunarday(year,month+1,1));
		                
						}
					if (day+4 < datas) {
						txt2.setText(Integer.toString(day+1));
						text2.setText(lunarday(year,month,day+1));
						txt3.setText(Integer.toString(day+2));
						text3.setText(lunarday(year,month,day+2));
						txt4.setText(Integer.toString(day+3));
						text4.setText(lunarday(year,month,day+3));
						txt5.setText(Integer.toString(day+4));
						text5.setText(lunarday(year,month,day+4));
						txt6.setText("1");
						text6.setText(lunarday(year,month+1,1));
						txt7.setText("2");
						text7.setText(lunarday(year,month+1,2));
		                 
						}
					if (day+3 < datas) {
						txt2.setText(Integer.toString(day+1));
						text2.setText(lunarday(year,month,day+1));
						txt3.setText(Integer.toString(day+2));
						text3.setText(lunarday(year,month,day+2));
						txt4.setText(Integer.toString(day+3));
						text4.setText(lunarday(year,month,day+3));
						txt5.setText("1");
						text5.setText(lunarday(year,month+1,1));
						txt6.setText("2");
						text6.setText(lunarday(year,month+1,2));
						txt7.setText("3");
						text7.setText(lunarday(year,month+1,3));
		                 
						}
					if (day+2 < datas) {
						txt2.setText(Integer.toString(day+1));
						text2.setText(lunarday(year,month,day+1));
						txt3.setText(Integer.toString(day+2));
						text3.setText(lunarday(year,month,day+2));
						txt4.setText("1");
						text4.setText(lunarday(year,month+1,1));
						txt5.setText("2");
						text5.setText(lunarday(year,month+1,2));
						txt6.setText("3");
						text6.setText(lunarday(year,month+1,3));
						txt7.setText("4");
						text7.setText(lunarday(year,month+1,4));
		                
						}
					if (day+1 < datas) {
						txt2.setText(Integer.toString(day+1));
						text2.setText(lunarday(year,month,day+1));
						txt3.setText("1");
						text3.setText(lunarday(year,month+1,1));
						txt4.setText("2");
						text4.setText(lunarday(year,month+1,2));
						txt5.setText("3");
						text5.setText(lunarday(year,month+1,3));
						txt6.setText("4");
						text6.setText(lunarday(year,month+1,4));
						txt7.setText("5");
						text7.setText(lunarday(year,month+1,5));
						}
					if (day == datas) {
						txt2.setText("1");
						text6.setText(lunarday(year,month+1,1));
						txt3.setText("2");
						text6.setText(lunarday(year,month+1,2));
						txt4.setText("3");
						text6.setText(lunarday(year,month+1,3));
						txt5.setText("4");
						text6.setText(lunarday(year,month+1,4));
						txt6.setText("5");
						text6.setText(lunarday(year,month+1,5));
						txt7.setText("6");
						text7.setText(lunarday(year,month+1,6));
		                 
						}
					break;
		     case 2:
		    	 txt2.setText(Integer.toString(day));
		    	 text2.setText(lunarday(year,month,day));
		    	 if (day+5 < datas) {
		    		 txt1.setText(Integer.toString(day-1));
		    		 text1.setText(lunarday(year,month,day-1));
		    		 txt3.setText(Integer.toString(day+1));
		    		 text3.setText(lunarday(year,month,day+1));
		    		 txt4.setText(Integer.toString(day+2));
		    		 text4.setText(lunarday(year,month,day+2));
		    		 txt5.setText(Integer.toString(day+3));
		    		 text5.setText(lunarday(year,month,day+3));
		    		 txt6.setText(Integer.toString(day+4));
		    		 text6.setText(lunarday(year,month,day+4));
		    		 txt7.setText(Integer.toString(day+5));
		    		 text7.setText(lunarday(year,month,day+5));
	                
					}
				if (day+4 < datas) {
					txt1.setText(Integer.toString(day-1));
					text1.setText(lunarday(year,month,day-1));
					txt3.setText(Integer.toString(day+1));
					text3.setText(lunarday(year,month,day+1));
					txt4.setText(Integer.toString(day+2));
					text4.setText(lunarday(year,month,day+2));
					txt5.setText(Integer.toString(day+3));
					text5.setText(lunarday(year,month,day+3));
					txt6.setText(Integer.toString(day+4));
					text6.setText(lunarday(year,month,day+4));
					txt7.setText("1");
					text7.setText(lunarday(year,month+1,1));
	                 
					}
				if (day+3 < datas) {
					txt1.setText(Integer.toString(day-1));
					text1.setText(lunarday(year,month,day-1));
					txt3.setText(Integer.toString(day+1));
					text3.setText(lunarday(year,month,day+1));
					txt4.setText(Integer.toString(day+2));
					text4.setText(lunarday(year,month,day+2));
					txt5.setText(Integer.toString(day+3));
					text5.setText(lunarday(year,month,day+3));
					txt6.setText("1");
					text6.setText(lunarday(year,month+1,1));
					txt7.setText("2");
					text7.setText(lunarday(year,month+1,2));
	                 
					}
				if (day+2 < datas) {
					txt1.setText(Integer.toString(day-1));
					txt3.setText(Integer.toString(day+1));
					txt4.setText(Integer.toString(day+2));
					txt5.setText("1");
					txt6.setText("2");
					txt7.setText("3");
					text1.setText(lunarday(year,month,day-1));
					text3.setText(lunarday(year,month,day+1));
					text4.setText(lunarday(year,month,day+2));
					text5.setText(lunarday(year,month+1,1));
					text6.setText(lunarday(year,month+1,2));
					text7.setText(lunarday(year,month+1,3));
					}
				if (day+1 < datas) {
					txt1.setText(Integer.toString(day-1));
					txt3.setText(Integer.toString(day+1));
					txt4.setText("1");
					txt5.setText("2");
					txt6.setText("3");
					txt7.setText("4");
					text1.setText(lunarday(year,month,day-1));
					text3.setText(lunarday(year,month,day+1));
					text4.setText(lunarday(year,month+1,1));
					text5.setText(lunarday(year,month+1,2));
					text6.setText(lunarday(year,month+1,3));
					text7.setText(lunarday(year,month+1,4));
	                
					}
				if (day == datas) {
					txt1.setText(Integer.toString(day-1));
					txt3.setText("1");
					txt4.setText("2");
					txt5.setText("3");
					txt6.setText("4");
					txt7.setText("5");
					text1.setText(lunarday(year,month,day-1));
					text3.setText(lunarday(year,month+1,1));
					text4.setText(lunarday(year,month+1,2));
					text5.setText(lunarday(year,month+1,3));
					text6.setText(lunarday(year,month+1,4));
					text7.setText(lunarday(year,month+1,5));
	                 
					}
				if (day == 1) {
					txt1.setText(Integer.toString(latsdatas));
					txt3.setText(Integer.toString(day+1));
					txt4.setText(Integer.toString(day+2));
					txt5.setText(Integer.toString(day+3));
					txt6.setText(Integer.toString(day+4));
					txt7.setText(Integer.toString(day+5));
					text1.setText(lunarday(year,month-1,latsdatas));
					text3.setText(lunarday(year,month,2));
					text4.setText(lunarday(year,month,3));
					text5.setText(lunarday(year,month,4));
					text6.setText(lunarday(year,month,5));
					text7.setText(lunarday(year,month,6));
					}
				
				break;
		     case 3:
		    	 txt3.setText(Integer.toString(day));
		    	 text3.setText(lunarday(year,month,day));
		    	 if (day+4 < datas) {
		    		 txt1.setText(Integer.toString(day-2));
					 txt2.setText(Integer.toString(day-1));
		    		 txt4.setText(Integer.toString(day+1));
		    		 txt5.setText(Integer.toString(day+2));
		    		 txt6.setText(Integer.toString(day+3));
		    		 txt7.setText(Integer.toString(day+4));
		    		 text1.setText(lunarday(year,month,day-2));
		    		 text2.setText(lunarday(year,month,day-1));
		    		 text4.setText(lunarday(year,month,day+1));
		    		 text5.setText(lunarday(year,month,day+2));
		    		 text6.setText(lunarday(year,month,day+3));
		    		 text7.setText(lunarday(year,month,day+4));
	                 
					}
				if (day+3 < datas) {
					txt1.setText(Integer.toString(day-2));
					txt2.setText(Integer.toString(day-1));
					txt4.setText(Integer.toString(day+1));
					txt5.setText(Integer.toString(day+2));
					txt6.setText(Integer.toString(day+3));
					txt7.setText("1");
					text1.setText(lunarday(year,month,day-2));
		    		text2.setText(lunarday(year,month,day-1));
		    		text4.setText(lunarday(year,month,day+1));
		    		text5.setText(lunarday(year,month,day+2));
		    		text6.setText(lunarday(year,month,day+3));
		    		text7.setText(lunarday(year,month+1,1));
	                 
					}
				if (day+2 < datas) {
					txt1.setText(Integer.toString(day-2));
					txt2.setText(Integer.toString(day-1));
					txt4.setText(Integer.toString(day+1));
					txt5.setText(Integer.toString(day+2));
					txt6.setText("1");
					txt7.setText("2");
					text1.setText(lunarday(year,month,day-2));
		    		text2.setText(lunarday(year,month,day-1));
		    		text4.setText(lunarday(year,month,day+1));
		    		text5.setText(lunarday(year,month,day+2));
		    		text6.setText(lunarday(year,month+1,1));
		    		text7.setText(lunarday(year,month+1,2));
	                 
					}
				if (day+1 < datas) {
					txt1.setText(Integer.toString(day-2));
					txt2.setText(Integer.toString(day-1));
					txt4.setText(Integer.toString(day+1));
					txt5.setText("1");
					txt6.setText("2");
					txt7.setText("3");
					text1.setText(lunarday(year,month,day-2));
		    		text2.setText(lunarday(year,month,day-1));
		    		text4.setText(lunarday(year,month,day+1));
		    		text5.setText(lunarday(year,month+1,1));
		    		text6.setText(lunarday(year,month+1,2));
		    		text7.setText(lunarday(year,month+1,3));
	                
					}
				if (day == datas) {
					txt1.setText(Integer.toString(day-2));
					txt2.setText(Integer.toString(day-1));
					txt4.setText("1");
					txt5.setText("2");
					txt6.setText("3");
					txt7.setText("4");
					text1.setText(lunarday(year,month,day-2));
		    		text2.setText(lunarday(year,month,day-1));
		    		text4.setText(lunarday(year,month+1,1));
		    		text5.setText(lunarday(year,month+1,2));
		    		text6.setText(lunarday(year,month+1,3));
		    		text7.setText(lunarday(year,month+1,2));
	                 
					}
				if (day == 1) {
					txt1.setText(Integer.toString(latsdatas-1));
					txt2.setText(Integer.toString(latsdatas));
					txt4.setText(Integer.toString(day+1));
					txt5.setText(Integer.toString(day+2));
					txt6.setText(Integer.toString(day+3));
					txt7.setText(Integer.toString(day+4));
					text1.setText(lunarday(year,month-1,latsdatas-1));
		    		text2.setText(lunarday(year,month-1,latsdatas));
		    		text4.setText(lunarday(year,month,day+1));
		    		text5.setText(lunarday(year,month,day+2));
		    		text6.setText(lunarday(year,month,day+3));
		    		text7.setText(lunarday(year,month,day+4));
					}
								
				break;
		     case 4:
		    	 txt4.setText(Integer.toString(day));
		    	 text4.setText(lunarday(year,month,day));
		    	   if (day+3 < datas) {
		    		   txt1.setText(Integer.toString(day-3));
					   txt2.setText(Integer.toString(day-2));
                       txt3.setText(Integer.toString(day-1));
		    		   txt5.setText(Integer.toString(day+1));
		    		   txt6.setText(Integer.toString(day+2));
		    		   txt7.setText(Integer.toString(day+3));
		    		   text1.setText(lunarday(year,month,day-3));
		    		   text2.setText(lunarday(year,month,day-2));
		    		   text3.setText(lunarday(year,month,day-1));
		    		   text5.setText(lunarday(year,month,day+1));
		    		   text6.setText(lunarday(year,month,day+2));
		    		   text7.setText(lunarday(year,month,day+3));
		                 
						}
					if (day+2 < datas) {
						txt1.setText(Integer.toString(day-3));
						txt2.setText(Integer.toString(day-2));
						txt3.setText(Integer.toString(day-1));
						txt5.setText(Integer.toString(day+1));
						txt6.setText(Integer.toString(day+2));
						txt7.setText("1");
						text1.setText(lunarday(year,month,day-3));
			    		text2.setText(lunarday(year,month,day-2));
			    		text3.setText(lunarday(year,month,day-1));
			    		text5.setText(lunarday(year,month,day+1));
			    		text6.setText(lunarday(year,month,day+2));
			    		text7.setText(lunarday(year,month+1,1));
		                 
						}
					if (day+1 < datas) {
						txt1.setText(Integer.toString(day-3));
						txt2.setText(Integer.toString(day-2));
						txt3.setText(Integer.toString(day-1));
						txt5.setText(Integer.toString(day+1));
						txt6.setText("1");
						txt7.setText("2");
						text1.setText(lunarday(year,month,day-3));
			    		text2.setText(lunarday(year,month,day-2));
			    		text3.setText(lunarday(year,month,day-1));
			    		text5.setText(lunarday(year,month,day+1));
			    		text6.setText(lunarday(year,month+1,1));
			    		text7.setText(lunarday(year,month+1,2));
		                
						}
					if (day == datas) {
						txt1.setText(Integer.toString(day-3));
						txt2.setText(Integer.toString(day-2));
						txt3.setText(Integer.toString(day-1));
						txt5.setText("1");
						txt6.setText("2");
						txt7.setText("3");
						text1.setText(lunarday(year,month,day-3));
			    		text2.setText(lunarday(year,month,day-2));
			    		text3.setText(lunarday(year,month,day-1));
			    		text5.setText(lunarday(year,month+1,1));
			    		text6.setText(lunarday(year,month+1,2));
			    		text7.setText(lunarday(year,month+1,3)); 
						}
					if (day == 1) {
						txt1.setText(Integer.toString(latsdatas-2));
						txt2.setText(Integer.toString(latsdatas-1));
						txt3.setText(Integer.toString(latsdatas));
						txt5.setText(Integer.toString(day+1));
						txt6.setText(Integer.toString(day+2));
						txt7.setText(Integer.toString(day+3));
						text1.setText(lunarday(year,month-1,latsdatas-2));
			    		text2.setText(lunarday(year,month-1,latsdatas-1));
			    		text3.setText(lunarday(year,month-1,latsdatas));
			    		text5.setText(lunarday(year,month,day+1));
			    		text6.setText(lunarday(year,month,day+2));
			    		text7.setText(lunarday(year,month,day+3)); 
						}
					
					
		    	 break;
		     case 5:
		    	 txt5.setText(Integer.toString(day));
		    	 text5.setText(lunarday(year,month,day));
		    	   if (day+2 < datas) {
		    		   txt1.setText(Integer.toString(day-4));
					   txt2.setText(Integer.toString(day-3));
					   txt3.setText(Integer.toString(day-2));
					   txt4.setText(Integer.toString(day-1));
		    		   txt6.setText(Integer.toString(day+1));
		    		   txt7.setText(Integer.toString(day+2));
		    		   text1.setText(lunarday(year,month,day-4));
		    		   text2.setText(lunarday(year,month,day-3));
		    		   text3.setText(lunarday(year,month,day-2));
		    		   text4.setText(lunarday(year,month,day-1));
		    		   text6.setText(lunarday(year,month,day+1));
		    		   text7.setText(lunarday(year,month,day+2));
						}
					if (day+1 < datas) {

						txt1.setText(Integer.toString(day-4));
						txt2.setText(Integer.toString(day-3));
						txt3.setText(Integer.toString(day-2));
						txt4.setText(Integer.toString(day-1));
						txt6.setText(Integer.toString(day+1));
						txt7.setText("1");
						text1.setText(lunarday(year,month,day-4));
			    		text2.setText(lunarday(year,month,day-3));
			    		text3.setText(lunarday(year,month,day-2));
			    		text4.setText(lunarday(year,month,day-1));
			    		text6.setText(lunarday(year,month,day+1));
			    		text7.setText(lunarday(year,month+1,1));
		                
						}
					if (day == datas) {
						txt1.setText(Integer.toString(day-4));
						txt2.setText(Integer.toString(day-3));
						txt3.setText(Integer.toString(day-2));
						txt4.setText(Integer.toString(day-1));
						txt6.setText("1");
						txt7.setText("2");
						text1.setText(lunarday(year,month,day-4));
			    		text2.setText(lunarday(year,month,day-3));
			    		text3.setText(lunarday(year,month,day-2));
			    		text4.setText(lunarday(year,month,day-1));
			    		text6.setText(lunarday(year,month+1,1));
			    		text7.setText(lunarday(year,month+1,2));
	                 
						}
					if (day == 1) {
						txt1.setText(Integer.toString(latsdatas-3));
						txt2.setText(Integer.toString(latsdatas-2));
						txt3.setText(Integer.toString(latsdatas-1));
						txt4.setText(Integer.toString(latsdatas));
						txt6.setText(Integer.toString(day+1));
						txt7.setText(Integer.toString(day+2));
						text1.setText(lunarday(year,month-1,latsdatas-4));
			    		text2.setText(lunarday(year,month-1,latsdatas-3));
			    		text3.setText(lunarday(year,month-1,latsdatas-2));
			    		text4.setText(lunarday(year,month-1,latsdatas-1));
			    		text6.setText(lunarday(year,month,day+1));
			    		text7.setText(lunarday(year,month,day+2));
						}
					
		    	 break;
		     case 6:
		    	 txt6.setText(Integer.toString(day));
		    	 text6.setText(lunarday(year,month,day));
		    	 if (day+1 < datas) {

		    		 txt1.setText(Integer.toString(day-5));
					 txt2.setText(Integer.toString(day-4));
					 txt3.setText(Integer.toString(day-3));
					 txt4.setText(Integer.toString(day-2));
					 txt5.setText(Integer.toString(day-1));
		    		 txt7.setText(Integer.toString(day+1));
		    		 text1.setText(lunarday(year,month,day-5));
		    		 text2.setText(lunarday(year,month,day-4));
		    		 text3.setText(lunarday(year,month,day-3));
		    		 text4.setText(lunarday(year,month,day-2));
		    		 text5.setText(lunarday(year,month,day-1));
		    		 text7.setText(lunarday(year,month,day+1));
		                
						}
					if (day == datas) {
						txt1.setText(Integer.toString(day-5));
						txt2.setText(Integer.toString(day-4));
						txt3.setText(Integer.toString(day-3));
						txt4.setText(Integer.toString(day-2));
						txt5.setText(Integer.toString(day-1));
						txt7.setText("1");
						text1.setText(lunarday(year,month,day-5));
			    		text2.setText(lunarday(year,month,day-4));
			    		text3.setText(lunarday(year,month,day-3));
			    		text4.setText(lunarday(year,month,day-2));
			    		text5.setText(lunarday(year,month,day-1));
			    		text7.setText(lunarday(year,month+1,1));
	              
						}
					if (day == 1) {
						txt1.setText(Integer.toString(latsdatas-4));
						txt2.setText(Integer.toString(latsdatas-3));
						txt3.setText(Integer.toString(latsdatas-2));
						txt4.setText(Integer.toString(latsdatas-1));
						txt5.setText(Integer.toString(latsdatas));
						txt7.setText(Integer.toString(day+1));
						text1.setText(lunarday(year,month-1,latsdatas-5));
			    		text2.setText(lunarday(year,month-1,latsdatas-4));
			    		text3.setText(lunarday(year,month-1,latsdatas-3));
			    		text4.setText(lunarday(year,month-1,latsdatas-2));
			    		text5.setText(lunarday(year,month-1,latsdatas-1));
			    		text7.setText(lunarday(year,month,day+1));
						}
					
		    	 break;
		     case 7:
		    	 txt7.setText(Integer.toString(day));
		    	 text7.setText(lunarday(year,month,day));
		    	 if (day == datas) {
		    		 txt1.setText(Integer.toString(day-6));
		    		 txt2.setText(Integer.toString(day-5));
		    		 txt3.setText(Integer.toString(day-4));
		    		 txt4.setText(Integer.toString(day-3));
		    		 txt5.setText(Integer.toString(day-2));
		    		 txt6.setText(Integer.toString(day-1));
		    		 text1.setText(lunarday(year,month,day-6));
		    		 text2.setText(lunarday(year,month,day-5));
		    		 text3.setText(lunarday(year,month,day-4));
		    		 text4.setText(lunarday(year,month,day-3));
		    		 text5.setText(lunarday(year,month,day-2));
		    		 text6.setText(lunarday(year,month,day-1));
				}
		    	 if (day == 1) {
		    		 txt1.setText(Integer.toString(latsdatas-5));
		    		 txt2.setText(Integer.toString(latsdatas-4));
		    		 txt3.setText(Integer.toString(latsdatas-3));
		    		 txt4.setText(Integer.toString(latsdatas-2));
		    		 txt5.setText(Integer.toString(latsdatas-1));
		    		 txt6.setText(Integer.toString(latsdatas));
		    		 text1.setText(lunarday(year,month-1,latsdatas-5));
		    		 text2.setText(lunarday(year,month-1,latsdatas-4));
		    		 text3.setText(lunarday(year,month-1,latsdatas-3));
		    		 text4.setText(lunarday(year,month-1,latsdatas-2));
		    		 text5.setText(lunarday(year,month-1,latsdatas-1));
		    		 text6.setText(lunarday(year,month-1,latsdatas));
				}
		    	 if (day == 2) {
		    		 txt1.setText(Integer.toString(latsdatas-4));
		    		 txt2.setText(Integer.toString(latsdatas-3));
		    		 txt3.setText(Integer.toString(latsdatas-2));
		    		 txt4.setText(Integer.toString(latsdatas-1));
		    		 txt5.setText(Integer.toString(latsdatas));
		    		 txt6.setText("1");
		    		 text1.setText(lunarday(year,month-1,latsdatas-4));
		    		 text2.setText(lunarday(year,month-1,latsdatas-3));
		    		 text3.setText(lunarday(year,month-1,latsdatas-2));
		    		 text4.setText(lunarday(year,month-1,latsdatas-1));
		    		 text5.setText(lunarday(year,month-1,latsdatas));
		    		 text6.setText(lunarday(year,month,1));
				}
		    	 if (day == 3) {
		    		 txt1.setText(Integer.toString(latsdatas-3));
		    		 txt2.setText(Integer.toString(latsdatas-2));
		    		 txt3.setText(Integer.toString(latsdatas-1));
		    		 txt4.setText(Integer.toString(latsdatas));
		    		 txt5.setText("1");
		    		 txt6.setText("2");
		    		 text1.setText(lunarday(year,month-1,latsdatas-3));
		    		 text2.setText(lunarday(year,month-1,latsdatas-2));
		    		 text3.setText(lunarday(year,month-1,latsdatas-1));
		    		 text4.setText(lunarday(year,month-1,latsdatas));
		    		 text5.setText(lunarday(year,month,1));
		    		 text6.setText(lunarday(year,month,2));
				}
		    	 if (day == 4) {
		    		 txt1.setText(Integer.toString(latsdatas-2));
		    		 txt2.setText(Integer.toString(latsdatas-1));
		    		 txt3.setText(Integer.toString(latsdatas));
		    		 txt4.setText("1");
		    		 txt5.setText("2");
		    		 txt6.setText("3");
		    		 text1.setText(lunarday(year,month-1,latsdatas-2));
		    		 text2.setText(lunarday(year,month-1,latsdatas-1));
		    		 text3.setText(lunarday(year,month-1,latsdatas));
		    		 text4.setText(lunarday(year,month,1));
		    		 text5.setText(lunarday(year,month,2));
		    		 text6.setText(lunarday(year,month,3));
				}
		    	 if (day == 5) {
		    		 txt1.setText(Integer.toString(latsdatas-1));
		    		 txt2.setText(Integer.toString(latsdatas));
		    		 txt3.setText("1");
		    		 txt4.setText("2");
		    		 txt5.setText("3");
		    		 txt6.setText("4");
		    		 text1.setText(lunarday(year,month-1,latsdatas-1));
		    		 text2.setText(lunarday(year,month-1,latsdatas));
		    		 text3.setText(lunarday(year,month,1));
		    		 text4.setText(lunarday(year,month,2));
		    		 text5.setText(lunarday(year,month,3));
		    		 text6.setText(lunarday(year,month,4));
				}
		    	 if (day == 6) {
		    		 txt1.setText(Integer.toString(latsdatas));
		    		 txt2.setText("1");
		    		 txt3.setText("2");
		    		 txt4.setText("3");
		    		 txt5.setText("4");
		    		 txt6.setText("5");
		    		 text1.setText(lunarday(year,month-1,latsdatas));
		    		 text2.setText(lunarday(year,month,1));
		    		 text3.setText(lunarday(year,month,2));
		    		 text4.setText(lunarday(year,month,3));
		    		 text5.setText(lunarday(year,month,4));
		    		 text6.setText(lunarday(year,month,5));
				}
		    	 
		     break;
		         
		}
		
		
		
		titleView.setLeftIconOnClick(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				double s = Double.parseDouble(intents.getStringExtra("s"));
				Intent intent = new Intent();
				if (s == 0) {
					intent.setClass(WeekTrainingPlanAty.this, MyAttentionpAty.class);
					startActivity(intent);
					finish();
				}
				intent.setClass(WeekTrainingPlanAty.this, AttentionManDataAty.class);
				startActivity(intent);
				finish();
			}
		});
		times1.setText("");
		plan1.setText("");
		accomplish1.setText("");
		times2.setText("");
		plan2.setText("");
		accomplish2.setText("");
		
	}
	
	 public String lunarday(int year,int month,int day){

	   		ChineseCalendar calendar = new  ChineseCalendar(year,month,day);
	   		int da = calendar.get(ChineseCalendar.CHINESE_DATE);
	   		String lunarday = "";
	   		switch (da) {
			case 1:		lunarday = "一";		break;
			case 2:		lunarday = "二";		break;
			case 3:		lunarday = "三";		break;
			case 4:		lunarday = "四";		break;
			case 5:		lunarday = "五";		break;
			case 6:		lunarday = "六";		break;
			case 7:		lunarday = "七";		break;
			case 8:		lunarday = "八";		break;
			case 9:		lunarday = "九";		break;
			case 10:	lunarday = "十";		break;
			case 11:	lunarday = "十一";		break;
			case 12:	lunarday = "十二";		break;
			case 13:	lunarday = "十三";		break;
			case 14:	lunarday = "十四";		break;
			case 15:	lunarday = "十五";		break;
			case 16:	lunarday = "十六";		break;
			case 17:	lunarday = "十七";		break;
			case 18:	lunarday = "十八";		break;
			case 19:	lunarday = "十九";		break;
			case 20:	lunarday = "二十";		break;
			case 21:	lunarday = "二一";		break;
			case 22:	lunarday = "二二";		break;
			case 23:	lunarday = "二三";		break;
			case 24:	lunarday = "二四";		break;
			case 25:	lunarday = "二五";		break;
			case 26:	lunarday = "二六";		break;
			case 27:	lunarday = "二七";		break;
			case 28:	lunarday = "二八";		break;
			case 29:	lunarday = "二九";		break;
			case 30:	lunarday = "三十";		break;
			

			}
	   		return lunarday;
	   		
	   	}
	 
	 OnClickListener laylistener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.lay1:
                lay1.setBackgroundResource(R.color.white);
                lay2.setBackgroundResource(R.color.ri);
                lay3.setBackgroundResource(R.color.ri);
                lay4.setBackgroundResource(R.color.ri);
                lay5.setBackgroundResource(R.color.ri);
                lay6.setBackgroundResource(R.color.ri);
                lay7.setBackgroundResource(R.color.ri);
				break;
			case R.id.lay2:
                lay1.setBackgroundResource(R.color.ri);
                lay2.setBackgroundResource(R.color.white);
                lay3.setBackgroundResource(R.color.ri);
                lay4.setBackgroundResource(R.color.ri);
                lay5.setBackgroundResource(R.color.ri);
                lay6.setBackgroundResource(R.color.ri);
                lay7.setBackgroundResource(R.color.ri);
				break;
			case R.id.lay3:
                lay1.setBackgroundResource(R.color.ri);
                lay2.setBackgroundResource(R.color.ri);
                lay3.setBackgroundResource(R.color.white);
                lay4.setBackgroundResource(R.color.ri);
                lay5.setBackgroundResource(R.color.ri);
                lay6.setBackgroundResource(R.color.ri);
                lay7.setBackgroundResource(R.color.ri);
				break;
			case R.id.lay4:
                lay1.setBackgroundResource(R.color.ri);
                lay2.setBackgroundResource(R.color.ri);
                lay3.setBackgroundResource(R.color.ri);
                lay4.setBackgroundResource(R.color.white);
                lay5.setBackgroundResource(R.color.ri);
                lay6.setBackgroundResource(R.color.ri);
                lay7.setBackgroundResource(R.color.ri);
				break;
			case R.id.lay5:
                lay1.setBackgroundResource(R.color.ri);
                lay2.setBackgroundResource(R.color.ri);
                lay3.setBackgroundResource(R.color.ri);
                lay4.setBackgroundResource(R.color.ri);
                lay5.setBackgroundResource(R.color.white);
                lay6.setBackgroundResource(R.color.ri);
                lay7.setBackgroundResource(R.color.ri);
				break;
			case R.id.lay6:
                lay1.setBackgroundResource(R.color.ri);
                lay2.setBackgroundResource(R.color.ri);
                lay3.setBackgroundResource(R.color.ri);
                lay4.setBackgroundResource(R.color.ri);
                lay5.setBackgroundResource(R.color.ri);
                lay6.setBackgroundResource(R.color.white);
                lay7.setBackgroundResource(R.color.ri);
				break;
			case R.id.lay7:
                lay1.setBackgroundResource(R.color.ri);
                lay2.setBackgroundResource(R.color.ri);
                lay3.setBackgroundResource(R.color.ri);
                lay4.setBackgroundResource(R.color.ri);
                lay5.setBackgroundResource(R.color.ri);
                lay6.setBackgroundResource(R.color.ri);
                lay7.setBackgroundResource(R.color.white);
				break;


			}
		}
	};
}
