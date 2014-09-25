package com.neusoft.activities;

import java.util.ArrayList;
import java.util.HashMap;

import com.mycustom.entity.require.RegisterReqItem;
import com.mycustom.entity.result.ResultItem;
import com.mycustom.tools.ConstantValues;
import com.mycustom.tools.RequestService;
import com.mycustom.view.CustomDialogView;
import com.mycustom.view.TitleView;
import com.neusoft.defertrain.R;
import com.neusoft.tools.ConstantKey;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

@SuppressLint("ResourceAsColor")
public class HomeGiftAty extends Activity {


	private String texts[] = null;
	private int imagesfruit[] = null;
	private TextView textgift;
	private Button btntrain;
	private TitleView titleView;
	private int a = 2;
	private GridView gridview;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.homegift);

		findView();

		init();

		getData();

		ArrayList();

		setOnClickListener();

		// callTask();

	}

	private void getData() {
		imagesfruit = new int[] { R.drawable.ic_launcher,
				R.drawable.ic_launcher, R.drawable.ic_launcher,
				R.drawable.ic_launcher };

		texts = new String[] { "1", "2", "3", "4", "5", "6", "7", "8" };

	}

	private void findView() {
		titleView = (TitleView) findViewById(R.id.mytitleview);

		gridview = (GridView) findViewById(R.id.grid);
		textgift = (TextView) findViewById(R.id.text_gift);
		btntrain = (Button) findViewById(R.id.train);

	}

	private void init() {
		// TODO Auto-generated method stub
		titleView.setTitleText(getString(R.string.gift_box));
		titleView.setLeftIcon(R.drawable.ic_launcher);
		titleView.setLeftIconVisible(View.VISIBLE);
		titleView.setRightIconVisible(View.GONE);
		titleView.setTitleTextSize(15);
		titleView.setTitleTextColor(R.color.personalcenter_txt);
		titleView.setBackgroundColor(getResources()
				.getColor(R.color.home_title));

		textgift.setText("你有" + a + "礼物");
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

		btntrain.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
	}

	private Handler myHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			switch (msg.what) {

			case ConstantValues.INTERNET_NOEXIST:
				Toast.makeText(HomeGiftAty.this, "当前没有网络", Toast.LENGTH_LONG)
						.show();
			/*	CustomDialogView customDialogView = new CustomDialogView(activity);
				customDialogView. setContents("正在努力下载数据，请稍候......");*/
				
				break;

			case ConstantValues.SERVICE_BAGIN:
				Toast.makeText(HomeGiftAty.this, "请耐心等待，正在与服务器通讯",
						Toast.LENGTH_LONG).show();
				break;

			case ConstantValues.SERVICE_SUCCESS:
				Toast.makeText(HomeGiftAty.this, "从服务器上成功获取数据",
						Toast.LENGTH_LONG).show();
				// 这是从服务器获取的数据，注意要转换成适当的类型，返回的类型已经在相应的服务调用方法中指出
				ResultItem result = (ResultItem) msg.obj;

				// result.setMessage(texts);

				// ArrayList();

				break;

			case ConstantValues.SERVICE_ERROR:
				Toast.makeText(HomeGiftAty.this, "与服务器通讯发生错误",
						Toast.LENGTH_LONG).show();
				break;

			case ConstantValues.SERVICE_EXCEPTION:
				Toast.makeText(HomeGiftAty.this, "与服务器通讯发生异常",
						Toast.LENGTH_LONG).show();
				break;
			}
		}

	};

	public void callTask() {

		// 组织上传的参数
		RegisterReqItem loginUser = new RegisterReqItem();
		loginUser.setChannel("1");
		loginUser.setEmail("12345@qq.com");
		loginUser.setPassword("12345678");
		loginUser.setPushChannel("");
		loginUser.setPushId("");

		// 生成服务通讯类的对象，并调用其上的相应方法，从而发起网络通讯 [这里的this参数把当前组件的上下文Context]
		RequestService requestService = new RequestService(this, myHandler);
		requestService.login(loginUser);
	}

	public void ArrayList() {

		ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < imagesfruit.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemImage", imagesfruit[i]);
			map.put("itemText", texts[i]);

			lstImageItem.add(map);
		}

		SimpleAdapter saImageItems = new SimpleAdapter(this, lstImageItem,
				R.layout.homegift_gridding, new String[] { "itemImage",
						"itemText" }, new int[] { R.id.image_fruit, R.id.text });

		gridview.setAdapter(saImageItems);

		gridview.setOnItemClickListener(new ItemClickListener());
	}

	class ItemClickListener implements OnItemClickListener {

		public void onItemClick(AdapterView<?> parent, View view, int position,
				long rowid) {
			// ���ͼƬ������Ӧ����ת
			/*
			 * switch (imagesfruit[position]) { case R.drawable.bomb10:
			 * Toast.makeText(HomeGiftHlr.this, "����ɹ�",
			 * Toast.LENGTH_LONG).show(); break; }
			 */
			 switch(position){
			 case 0://点击图片0跳转
			 {
				 Intent intent = new Intent();
					intent.setAction(ConstantKey.INTERACTION_ACTION);
					startActivity(intent);
			 }
			 break;
			 case 1://点击图片1跳转
			 {
				 Intent intent = new Intent();
					intent.setAction(ConstantKey.ATTENTION_MY_ACTION);
					startActivity(intent);
			 }
			 break;
			 }

		}
	}
}
