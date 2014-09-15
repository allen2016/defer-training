package com.neusoft.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.mycustom.view.TitleView;
import com.mycustom.view.slidingmenu.lib.app.SlidingFragmentActivity;
import com.neusoft.defertrain.R;

@SuppressLint("NewApi")
public class HomeContentFrg extends Fragment {
	
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
	
	private SlidingFragmentActivity container;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.home, container, false);
         
        findView(rootView);
		
        init();
        
        setOnClickListener();
        
        return rootView;
    }

	private void findView(View rootView) {
		titleView = (TitleView) rootView.findViewById(R.id.mytitleview);
        trainBtn = (Button) rootView.findViewById(R.id.trainBtn);
        tipsBtn = (Button) rootView.findViewById(R.id.tipsBtn);
        friendBtn = (Button) rootView.findViewById(R.id.friendBtn);
        centerBtn = (Button) rootView.findViewById(R.id.centerBtn);
	}
	
	private void init() {
		// TODO Auto-generated method stub
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
				container.toggle();
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
	
	public void setContainer(SlidingFragmentActivity container) {
		this.container = container;
	}
}
