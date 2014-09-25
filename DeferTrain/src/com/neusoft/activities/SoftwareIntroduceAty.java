package com.neusoft.activities;

import com.mycustom.entity.require.RegisterReqItem;
import com.mycustom.entity.result.ResultItem;
import com.mycustom.tools.ConstantValues;
import com.mycustom.tools.RequestService;
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
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ResourceAsColor")
public class SoftwareIntroduceAty extends Activity {
	private TitleView titleView;
	private TextView txtrelation_official_website, txtrelation_Twitter,
			txtrelation_Wechat, txtrelation_phone, txtrelation_email,
			txtmarket_phone, txtmarket_email, txtadvertisement_phone,
			txtadvertisement_email, txtcontent_phone, txtcontent_email;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_softwareintroduce);

		findView();

		init();

		setOnClickListener();

	}

	private void findView() {
		titleView = (TitleView) findViewById(R.id.mytitleview);
		
		txtrelation_official_website= (TextView) findViewById(R.id.relation_official_website);
		txtrelation_Twitter= (TextView) findViewById(R.id.relation_Twitter);
		txtrelation_Wechat= (TextView) findViewById(R.id.relation_Wechat);
		txtrelation_phone= (TextView) findViewById(R.id.relation_phone);
		txtrelation_email= (TextView) findViewById(R.id.relation_email);
		txtmarket_phone= (TextView) findViewById(R.id.market_phone);
		txtmarket_email= (TextView) findViewById(R.id.market_email);
		txtadvertisement_email= (TextView) findViewById(R.id.advertisement_email);
		txtadvertisement_phone= (TextView) findViewById(R.id.advertisement_phone);
		txtcontent_phone= (TextView) findViewById(R.id.content_phone);
		txtcontent_email= (TextView) findViewById(R.id.content_email);
		

	}

	private void init() {
		// TODO Auto-generated method stub
		titleView.setTitleText(getString(R.string.about));
		titleView.setLeftIcon(R.drawable.ic_launcher);
		titleView.setLeftIconVisible(View.VISIBLE);
		titleView.setRightIconVisible(View.GONE);
		titleView.setTitleTextSize(15);
		titleView.setTitleTextColor(R.color.personalcenter_txt);
		titleView.setBackgroundColor(getResources()
				.getColor(R.color.home_title));
		
		txtrelation_official_website.setText("");
		txtrelation_Twitter.setText("");
		txtrelation_Wechat.setText("");
		txtrelation_phone.setText("");
		txtrelation_email.setText("");
		txtmarket_phone.setText("");
		txtmarket_email.setText("");
		txtadvertisement_email.setText("");
		txtadvertisement_phone.setText("");
		txtcontent_phone.setText("");
		txtcontent_email.setText("");

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

	}
	
	private Handler myHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			switch (msg.what) {

			case ConstantValues.INTERNET_NOEXIST:
				Toast.makeText(SoftwareIntroduceAty.this, "当前没有网络",
						Toast.LENGTH_LONG).show();
				break;

			case ConstantValues.SERVICE_BAGIN:
				Toast.makeText(SoftwareIntroduceAty.this, "请耐心等待，正在与服务器通讯",
						Toast.LENGTH_LONG).show();
				break;

			case ConstantValues.SERVICE_SUCCESS:
				Toast.makeText(SoftwareIntroduceAty.this, "从服务器上成功获取数据",
						Toast.LENGTH_LONG).show();
				// 这是从服务器获取的数据，注意要转换成适当的类型，返回的类型已经在相应的服务调用方法中指出
				ResultItem result = (ResultItem) msg.obj;

				// result.setMessage(texts);

				break;

			case ConstantValues.SERVICE_ERROR:
				Toast.makeText(SoftwareIntroduceAty.this, "与服务器通讯发生错误",
						Toast.LENGTH_LONG).show();
				break;

			case ConstantValues.SERVICE_EXCEPTION:
				Toast.makeText(SoftwareIntroduceAty.this, "与服务器通讯发生异常",
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