package com.neusoft.activities;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mycustom.entity.require.RegisterReqItem;
import com.mycustom.entity.result.ResultItem;
import com.mycustom.tools.ConstantValues;
import com.mycustom.tools.RequestService;
import com.mycustom.view.TitleView;
import com.neusoft.defertrain.R;

public class AddAttentionManAty extends Activity {
	
	//添加我关注的人
    private EditText add;
    private LinearLayout sweep;
    
    private final static String PERSONAL_ACTION_STRING = "android.intent.action.PERSONAL";
    private final static String MYATT_ACTION_STRING = "android.intent.action.MYATT";
    
    private TitleView titleView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.addattentionman);
		
		add = (EditText)findViewById(R.id.add);
		sweep = (LinearLayout)findViewById(R.id.sweep);
		
		titleView = (TitleView) findViewById(R.id.mytitleview);
		
		titleView.setLeftIconOnClick(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(MYATT_ACTION_STRING);
				startActivity(intent);
			}
		});
		titleView.setRightIconOnClick(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(add != null){
				Intent intent = new Intent();
				intent.setAction(PERSONAL_ACTION_STRING);
				startActivity(intent);}
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
				Toast.makeText(AddAttentionManAty.this, "当前没有网络", Toast.LENGTH_LONG)
						.show();
			/*	CustomDialogView customDialogView = new CustomDialogView(activity);
				customDialogView. setContents("正在努力下载数据，请稍候......");*/
				
				break;

			case ConstantValues.SERVICE_BAGIN:
				Toast.makeText(AddAttentionManAty.this, "请耐心等待，正在与服务器通讯",
						Toast.LENGTH_LONG).show();
				break;

			case ConstantValues.SERVICE_SUCCESS:
				Toast.makeText(AddAttentionManAty.this, "从服务器上成功获取数据",
						Toast.LENGTH_LONG).show();
				// 这是从服务器获取的数据，注意要转换成适当的类型，返回的类型已经在相应的服务调用方法中指出
				ResultItem result = (ResultItem) msg.obj;

				// result.setMessage(texts);

				// ArrayList();

				break;

			case ConstantValues.SERVICE_ERROR:
				Toast.makeText(AddAttentionManAty.this, "与服务器通讯发生错误",
						Toast.LENGTH_LONG).show();
				break;

			case ConstantValues.SERVICE_EXCEPTION:
				Toast.makeText(AddAttentionManAty.this, "与服务器通讯发生异常",
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
