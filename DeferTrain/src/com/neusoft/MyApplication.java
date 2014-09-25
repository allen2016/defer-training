package com.neusoft;

import com.mycustom.tools.ConstantValues;

import android.app.Application;

public class MyApplication extends Application {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		ConstantValues.URI_HOME = "http://www.xxx.com";
	}

	
}
