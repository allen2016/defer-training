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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalCenterAty extends Activity {

	private final static String RIGHTICON_ACTION_STRING = "com.neusoft.cao.HOME";
	private TextView txtname, txtemail, txtintegral_number, txtgift_number,
			txtinteraction_number, txtprompt_number;
	private ImageButton imgBtngift, imgBtninteractionl;
	private Button btnattentionbtnone, btnattentionbtntwo, btnmycollectbtn,
			btnmycallingcardbtn;
	private TitleView titleView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personalcenter);

		findView();

		init();

		setOnClickListener();

	}

	private void findView() {
		titleView = (TitleView) findViewById(R.id.mytitleview);

		txtname = (TextView) findViewById(R.id.name);
		txtemail = (TextView) findViewById(R.id.email);
		txtgift_number = (TextView) findViewById(R.id.textgift_number);
		txtintegral_number = (TextView) findViewById(R.id.textintegral_number);
		txtinteraction_number = (TextView) findViewById(R.id.textinteraction_number);

		imgBtngift = (ImageButton) findViewById(R.id.giftbtn);
		imgBtninteractionl = (ImageButton) findViewById(R.id.interactionlbtn);

		btnattentionbtnone = (Button) findViewById(R.id.attentionbtnone);
		btnattentionbtntwo = (Button) findViewById(R.id.attentionbtntwo);
		btnmycollectbtn = (Button) findViewById(R.id.mycollectbtn);
		btnmycallingcardbtn = (Button) findViewById(R.id.mycallingcardbtn);
	}

	@SuppressLint("ResourceAsColor")
	private void init() {
		// TODO Auto-generated method stub
		titleView.setTitleText(getString(R.string.personalcenter));
		titleView.setLeftIcon(R.drawable.ic_launcher);
		titleView.setLeftIconVisible(View.VISIBLE);
		titleView.setRightIconVisible(View.GONE);
		titleView.setTitleTextSize(15);
		titleView.setTitleTextColor(R.color.personalcenter_txt);
		titleView.setBackgroundColor(getResources()
				.getColor(R.color.home_title));

		txtname.setText("11");
		txtemail.setText("22");
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

		imgBtngift.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(ConstantKey.HOME_GIFT_ACTION);
				startActivity(intent);
			}
		});

		imgBtninteractionl.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(ConstantKey.INTERACTION_ACTION);
				startActivity(intent);
			}
		});

		btnattentionbtnone.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(ConstantKey.SET_REMARK_ACTION);
				startActivity(intent);
			}
		});

		btnattentionbtntwo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(ConstantKey.ATTENTION_MY_ACTION);
				startActivity(intent);
			}
		});

		btnmycollectbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(ConstantKey.INTERACTION_ROOM_ACTION);
				startActivity(intent);
			}
		});

		btnmycallingcardbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(ConstantKey.MY_CALLING_CARD_ACTION);
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
				Toast.makeText(PersonalCenterAty.this, "当前没有网络",
						Toast.LENGTH_LONG).show();
				break;

			case ConstantValues.SERVICE_BAGIN:
				Toast.makeText(PersonalCenterAty.this, "请耐心等待，正在与服务器通讯",
						Toast.LENGTH_LONG).show();
				break;

			case ConstantValues.SERVICE_SUCCESS:
				Toast.makeText(PersonalCenterAty.this, "从服务器上成功获取数据",
						Toast.LENGTH_LONG).show();
				// 这是从服务器获取的数据，注意要转换成适当的类型，返回的类型已经在相应的服务调用方法中指出
				ResultItem result = (ResultItem) msg.obj;

				// result.setMessage(texts);

				break;

			case ConstantValues.SERVICE_ERROR:
				Toast.makeText(PersonalCenterAty.this, "与服务器通讯发生错误",
						Toast.LENGTH_LONG).show();
				break;

			case ConstantValues.SERVICE_EXCEPTION:
				Toast.makeText(PersonalCenterAty.this, "与服务器通讯发生异常",
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
