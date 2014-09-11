package com.neusoft.activities;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mycustom.tools.ConstantValues;
import com.mycustom.tools.RequestService;
import com.neusoft.defertrain.R;

public class SplashAty extends Activity {

	private final static String WIZARD_ACTION_STRING = "com.neusoft.cao.WIZARD";
	private final static String HOME_ACTION_STRING = "com.neusoft.cao.HOME";

	private static final int GO_HOME = 1000;
	private static final int GO_GUIDE = 1001;
	
	// 延迟3秒
	private static final long SPLASH_DELAY_MILLIS1 = 3000;
	private static final long SPLASH_DELAY_MILLIS2 = 500;
	private static final String SHAREDPREFERENCES_NAME = "first_pref";
	private static final String FIRST_PREFER_NAME = "isFirstIn";
	private static final String VERSION_CODE = "version_num";
	private SharedPreferences preferences;
	
	private MyHandler mHandler;
	
	private RelativeLayout hintView;
	private Button updateBtn;
	private Button cancalBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.splash);

		findView();
		
		init();
	}

	private void findView() {
		// TODO Auto-generated method stub
		updateBtn = (Button) findViewById(R.id.updateBtn);
		cancalBtn = (Button) findViewById(R.id.cancalBtn);
		hintView = (RelativeLayout) findViewById(R.id.chkview);
	}

	private void init() {
		
		mHandler = new MyHandler(this);
		
		// 读取SharedPreferences中需要的数据
		// 使用SharedPreferences来记录程序的使用次数
		preferences = getSharedPreferences(SHAREDPREFERENCES_NAME, MODE_PRIVATE);

		// 取得相应的值，如果没有该值，说明还未写入，用true作为默认值
		// 判断程序与第几次运行，如果是第一次运行则跳转到引导界面，否则跳转到主界面
		if (preferences.getBoolean(FIRST_PREFER_NAME, true)) {
			// 使用Handler的postDelayed方法，3秒后执行跳转到MainActivity
			mHandler.sendEmptyMessageDelayed(GO_GUIDE, SPLASH_DELAY_MILLIS1);
		} else {
			checkVersion();
		}
		
		updateBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		cancalBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				hintView.setVisibility(View.GONE);
				mHandler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS2);
			}
		});

	}

	private void checkVersion() {
		// TODO Auto-generated method stub
		RequestService requestService = new RequestService(this, mHandler);
//		requestService
	}

	private void goHome() {
		Intent intent = new Intent();
		intent.setAction(HOME_ACTION_STRING);
		startActivity(intent);
		finish();
	}

	private void goGuide() {
		//记录程序的使用次数
		preferences.edit().putBoolean(FIRST_PREFER_NAME, false).commit();
		
		Intent intent = new Intent();
		intent.setAction(WIZARD_ACTION_STRING);
		startActivity(intent);
		finish();
	}
	
	private void showInfo(int num){
		
		switch (num) {
		
			case ConstantValues.INTERNET_NOEXIST:
				Toast.makeText(this, "当前没有网络", Toast.LENGTH_LONG).show();
				break;
				
			case ConstantValues.SERVICE_BAGIN:
				Toast.makeText(this, "请耐心等待，正在与服务器通讯", Toast.LENGTH_LONG).show();
				break;
	
			case ConstantValues.SERVICE_ERROR:
				Toast.makeText(this, "与服务器通讯发生错误", Toast.LENGTH_LONG).show();
				break;
	
			case ConstantValues.SERVICE_EXCEPTION:
				Toast.makeText(this, "与服务器通讯发生异常", Toast.LENGTH_LONG).show();
				break;
		}
		
	}
	
	public void dealData(Message msg) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "从服务器上成功获取数据", Toast.LENGTH_LONG).show();
		//这是从服务器获取的数据，注意要转换成适当的类型，返回的类型已经在相应的服务调用方法中指出
		Object result = msg.obj;
		
		//比较版本号，决定是否给出升级提醒
		if (preferences.getInt(VERSION_CODE, 1)< 12) {
			hintView.setVisibility(View.VISIBLE);
		}else {
			mHandler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS2);
		}
	}
	
	static class MyHandler extends Handler {
		
        WeakReference<SplashAty> mActivity;

        MyHandler(SplashAty activity) {
              mActivity = new WeakReference<SplashAty>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
        	
        	SplashAty theActivity = mActivity.get();
        	
            switch (msg.what) {
            
				case GO_HOME:
					theActivity.goHome();
					break;
				
				case GO_GUIDE:
					theActivity.goGuide();
					break;
					
				case ConstantValues.INTERNET_NOEXIST:
				case ConstantValues.SERVICE_BAGIN:
				case ConstantValues.SERVICE_ERROR:
				case ConstantValues.SERVICE_EXCEPTION:
					theActivity.showInfo(msg.what);
					break;
					
				case ConstantValues.SERVICE_SUCCESS:
					theActivity.dealData(msg);
					break;
					
            }
        }
	}

}