package com.neusoft.activities;

import java.io.File;

import com.mycustom.view.TitleView;
import com.neusoft.defertrain.R;
import com.neusoft.tools.ConstantKey;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("ResourceAsColor")
public class CleanAty extends Activity {

	private static String TAG = "AM_MEMORYIPROCESS";

	private ActivityManager ActivityManager = null;

	private TextView txtsystemspace, txtdata;

	private Button ProcessInfobtn;

	private TitleView titleView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.home_clean);

		findView();

		init();

		setOnClickListener();

	}

	/*
	 * txtsystemspace = (TextView) findViewById(R.id.usable); txtdata =
	 * (TextView) findViewById(R.id.data); ProcessInfobtn = (Button)
	 * findViewById(R.id.clean_room);
	 * 
	 * ProcessInfobtn.setOnClickListener(new View.OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { // TODO Auto-generated method
	 * stub DataCleanManager.cleanInternalCache(CleanHlr.this);
	 * 
	 * 
	 * 
	 * ActivityManager = (ActivityManager)
	 * getSystemService(Context.ACTIVITY_SERVICE);
	 * 
	 * 
	 * String availMemStr = getSystemAvaialbeMemorySize();
	 * txtsystemspace.setText("可用空间：" + availMemStr);
	 * 
	 * 
	 * } });
	 * 
	 * ActivityManager = (ActivityManager)
	 * getSystemService(Context.ACTIVITY_SERVICE);
	 * 
	 * String availMemStr = getSystemAvaialbeMemorySize();
	 * 
	 * txtsystemspace.setText("可用空间：" + availMemStr);
	 * 
	 * 
	 * }
	 */
	private String getSystemAvaialbeMemorySize() {

		MemoryInfo memoryInfo = new MemoryInfo();

		ActivityManager.getMemoryInfo(memoryInfo);
		long memSize = memoryInfo.availMem;

		String availMemStr = formateFileSize(memSize);

		return availMemStr;
	}

	private String formateFileSize(long size) {
		return Formatter.formatFileSize(CleanAty.this, size);
	}

	public static double getDirSize(File file) {

		if (file.exists()) {

			if (file.isDirectory()) {
				File[] children = file.listFiles();
				double size = 0;
				for (File f : children)
					size += getDirSize(f);
				return size;
			} else {
				double size = (double) file.length() / 1024 / 1024;
				return size;
			}
		} else {

			return 0.0;
		}
	}

	public static class DataCleanManager {

		public static void cleanInternalCache(Context context) {
			deleteFilesByDirectory(context.getCacheDir());
		}

		public static void cleanDatabases(Context context) {
			deleteFilesByDirectory(new File("/data/data/"
					+ context.getPackageName() + "/databases"));
		}

		private static void deleteFilesByDirectory(File directory) {
			if (directory != null && directory.exists()
					&& directory.isDirectory()) {
				for (File item : directory.listFiles()) {
					item.delete();
				}
			}
		}
	}

	private void findView() {
		titleView = (TitleView) findViewById(R.id.mytitleview);

		txtsystemspace = (TextView) findViewById(R.id.usable);
		txtdata = (TextView) findViewById(R.id.data);
		ProcessInfobtn = (Button) findViewById(R.id.clean_room);

		ActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

		String availMemStr = getSystemAvaialbeMemorySize();

		txtsystemspace.setText("可用空间：" + availMemStr);
	}

	private void init() {
		// TODO Auto-generated method stub
		titleView.setTitleText(getString(R.string.clean_room));
		titleView.setLeftIcon(R.drawable.ic_launcher);
		titleView.setLeftIconVisible(View.VISIBLE);
		titleView.setRightIconVisible(View.GONE);
		titleView.setRightIcon(R.drawable.ic_launcher);
		titleView.setTitleTextSize(15);
		titleView.setTitleTextColor(R.color.personalcenter_txt);
		titleView.setBackgroundColor(getResources()
				.getColor(R.color.home_title));

	}

	private void setOnClickListener() {

		titleView.setLeftIconOnClick(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(ConstantKey.INDEX_ACTION);
				startActivity(intent);
			}
		});

		ProcessInfobtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DataCleanManager.cleanInternalCache(CleanAty.this);

				ActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

				String availMemStr = getSystemAvaialbeMemorySize();
				txtsystemspace.setText("可用空间：" + availMemStr);

			}
		});
	}
}
