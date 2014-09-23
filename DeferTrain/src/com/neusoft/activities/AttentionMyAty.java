package com.neusoft.activities;

import java.util.ArrayList;
import java.util.List;

import com.mycustom.entity.require.RegisterReqItem;
import com.mycustom.entity.result.ResultItem;
import com.mycustom.tools.ConstantValues;
import com.mycustom.tools.RequestService;
import com.mycustom.view.TitleView;
import com.neusoft.defertrain.R;
import com.neusoft.tools.ConstantKey;

import android.R.attr;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("ResourceAsColor")
public class AttentionMyAty extends Activity {

	private ListView listView;
	private ImageView imageView;
	private TitleView titleView;
	private AttentionApplyControlAdp adapter;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.attentionmy);

		findView();

		init();

		setOnClickListener();

		int[] pic = new int[] { R.drawable.ic_launcher };
		String[] name = new String[] { "aaa", "bbbb", "cccc", "dddd", "eeee",
				"ffff", "gggggg", "hhhhh", "kkkkkkkk", "kkkkkkkk", "kkkkkkkk",
				"kkkkkkkk", "kkkkkkkk", "kkkkkkkk", "kkkkkkkk", "kkkkkkkk" };
		String[] text = new String[] { "aaa", "bbbb", "cccc", "dddd", "eeee",
				"ffff", "gggggg", "hhhhh", "kkkkkkkk", "kkkkkkkk", "kkkkkkkk",
				"kkkkkkkk", "kkkkkkkk", "kkkkkkkk", "kkkkkkkk", "kkkkkkkk" };
		String[] text_remark = new String[] { "aaa", "bbbb", "cccc", "dddd",
				"eeee", "ffff", "gggggg", "hhhhh", "kkkkkkkk", "kkkkkkkk",
				"kkkkkkkk", "kkkkkkkk", "kkkkkkkk", "kkkkkkkk", "kkkkkkkk",
				"kkkkkkkk" };
		List<AttentionMyNews> listItems = new ArrayList<AttentionMyNews>();
		for (int i = 0; i < name.length; i++) {
			AttentionMyNews tempStudent = new AttentionMyNews();
			tempStudent.setFaceId(R.drawable.ic_launcher);
			tempStudent.setName(name[i]);
			tempStudent.setText(text[i]);
			tempStudent.setText_remark(text_remark[i]);
			listItems.add(tempStudent);

		}
		AttentionApplyControlAdp adapter = new AttentionApplyControlAdp(this,
				listItems);
		listView.setAdapter(adapter);

	}

	private void findView() {
		titleView = (TitleView) findViewById(R.id.mytitleview);
		listView = (ListView) findViewById(R.id.list);
	}

	private void init() {
		// TODO Auto-generated method stub
		titleView.setTitleText(getString(R.string.attentionmy));
		titleView.setLeftIcon(R.drawable.ic_launcher);
		titleView.setLeftIconVisible(View.VISIBLE);
		titleView.setRightIconVisible(View.GONE);
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
				intent.setAction(ConstantKey.PERSONALCENTER_ACTION);
				startActivity(intent);

			}
		});
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				Intent intent = new Intent();
				intent.setAction(ConstantKey.ATTENTION_APPLY_FOR_ACTION);
				startActivity(intent);
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
				Toast.makeText(AttentionMyAty.this, "当前没有网络", Toast.LENGTH_LONG)
						.show();
				break;

			case ConstantValues.SERVICE_BAGIN:
				Toast.makeText(AttentionMyAty.this, "请耐心等待，正在与服务器通讯",
						Toast.LENGTH_LONG).show();
				break;

			case ConstantValues.SERVICE_SUCCESS:
				Toast.makeText(AttentionMyAty.this, "从服务器上成功获取数据",
						Toast.LENGTH_LONG).show();
				// 这是从服务器获取的数据，注意要转换成适当的类型，返回的类型已经在相应的服务调用方法中指出
				ResultItem result = (ResultItem) msg.obj;

				// result.setMessage(texts);

				break;

			case ConstantValues.SERVICE_ERROR:
				Toast.makeText(AttentionMyAty.this, "与服务器通讯发生错误",
						Toast.LENGTH_LONG).show();
				break;

			case ConstantValues.SERVICE_EXCEPTION:
				Toast.makeText(AttentionMyAty.this, "与服务器通讯发生异常",
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
}
