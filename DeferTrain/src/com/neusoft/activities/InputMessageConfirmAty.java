package com.neusoft.activities;


import com.mycustom.entity.items.FriendRequire;
import com.mycustom.entity.require.RegisterReqItem;
import com.mycustom.entity.result.ResultItem;
import com.mycustom.tools.ConstantValues;
import com.mycustom.tools.RequestService;
import com.mycustom.view.CustomDialogView;
import com.mycustom.view.TitleView;
import com.neusoft.defertrain.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class InputMessageConfirmAty extends Activity {

	//添加关注人确认
	private ImageView head;
	private EditText setname1;
	private TextView name,age,sex,address,message1;
	
	private TitleView titleView;
	
	private final static String INPUTC_ACTION_STRING = "android.intent.action.INPUTC";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.inputmessageconfirm);
		

		 head = (ImageView)findViewById(R.id.head);

		 name = (TextView)findViewById(R.id.name);
		 sex = (TextView)findViewById(R.id.sex);
		 age = (TextView)findViewById(R.id.age);
		 address = (TextView)findViewById(R.id.address);
		 message1 = (TextView)findViewById(R.id.message1);
		 setname1 = (EditText)findViewById(R.id.setname1);
		 
		 Intent intent = getIntent();
		 message1.setText(intent.getStringExtra("messages"));
		 final CustomDialogView customDialogView = new CustomDialogView(this);

	     titleView = (TitleView) findViewById(R.id.mytitleview);
			
			titleView.setLeftIconOnClick(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					intent.setAction(INPUTC_ACTION_STRING);
					startActivity(intent);
					finish();
				}
			});
			titleView.setRightIconOnClick(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					customDialogView. setContents("申请信息以发送给对方，她/他将很快看到并回复你");
					customDialogView.show();
				}
			});
			FriendRequire friend = new FriendRequire();
			if (friend != null) {
				name.setText(friend.getName());
				sex.setText(friend.getSex());
				age.setText(friend.getBirthday());


					callTask();
				};
	}
	private Handler myHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			switch (msg.what) {

			case ConstantValues.INTERNET_NOEXIST:
				Toast.makeText(InputMessageConfirmAty.this, "当前没有网络", Toast.LENGTH_LONG)
						.show();
			/*	CustomDialogView customDialogView = new CustomDialogView(activity);
				customDialogView. setContents("正在努力下载数据，请稍候......");*/
				
				break;

			case ConstantValues.SERVICE_BAGIN:
				Toast.makeText(InputMessageConfirmAty.this, "请耐心等待，正在与服务器通讯",
						Toast.LENGTH_LONG).show();
				break;

			case ConstantValues.SERVICE_SUCCESS:
				Toast.makeText(InputMessageConfirmAty.this, "从服务器上成功获取数据",
						Toast.LENGTH_LONG).show();
				// 这是从服务器获取的数据，注意要转换成适当的类型，返回的类型已经在相应的服务调用方法中指出
				ResultItem result = (ResultItem) msg.obj;

				// result.setMessage(texts);

				// ArrayList();

				break;

			case ConstantValues.SERVICE_ERROR:
				Toast.makeText(InputMessageConfirmAty.this, "与服务器通讯发生错误",
						Toast.LENGTH_LONG).show();
				break;

			case ConstantValues.SERVICE_EXCEPTION:
				Toast.makeText(InputMessageConfirmAty.this, "与服务器通讯发生异常",
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

		loginUser.setPushId("");

		// 生成服务通讯类的对象，并调用其上的相应方法，从而发起网络通讯 [这里的this参数把当前组件的上下文Context]
		RequestService requestService = new RequestService(this, myHandler);
		requestService.login(loginUser);
	}
}
