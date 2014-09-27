package com.neusoft.activities;

import java.util.ArrayList;
import java.util.List;

import com.mycustom.entity.items.Friend;
import com.mycustom.entity.require.RegisterReqItem;
import com.mycustom.entity.result.ResultItem;
import com.mycustom.tools.ConstantValues;
import com.mycustom.tools.RequestService;
import com.mycustom.view.TitleView;
import com.neusoft.adapters.UserFriendAdp;
import com.neusoft.defertrain.R;



import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MyAttentionpAty extends Activity {

   //我关注的人
	private ListView lVi;
	private TitleView titleView;
	private List<Friend> listitem;
	private Friend friend;
	private UserFriendAdp adp;
	
	private final static String ADDATTEN_ACTION_STRING = "android.intent.action.ADDATTEN";
	private final static String MAIN_ACTION_STRING = "com.neusoft.cao.HOME";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.myattentionp);
		titleView = (TitleView) findViewById(R.id.mytitleview);
		lVi = (ListView)findViewById(R.id.listView1);
		
		titleView.setRightIconOnClick(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(ADDATTEN_ACTION_STRING);
				startActivity(intent);
			}
		});
      titleView.setLeftIconOnClick(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(MAIN_ACTION_STRING);
				startActivity(intent);
				finish();
			}
		});
		
		listitem = new ArrayList<Friend>(); 
        friend = new Friend();
        
		listitem.add(friend);
		adp = new UserFriendAdp(this, listitem);
		lVi.setAdapter(adp);

	}
	private Handler myHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			switch (msg.what) {

			case ConstantValues.INTERNET_NOEXIST:
				Toast.makeText(MyAttentionpAty.this, "当前没有网络", Toast.LENGTH_LONG)
						.show();
			/*	CustomDialogView customDialogView = new CustomDialogView(activity);
				customDialogView. setContents("正在努力下载数据，请稍候......");*/
				
				break;

			case ConstantValues.SERVICE_BAGIN:
				Toast.makeText(MyAttentionpAty.this, "请耐心等待，正在与服务器通讯",
						Toast.LENGTH_LONG).show();
				break;

			case ConstantValues.SERVICE_SUCCESS:
				Toast.makeText(MyAttentionpAty.this, "从服务器上成功获取数据",
						Toast.LENGTH_LONG).show();
				// 这是从服务器获取的数据，注意要转换成适当的类型，返回的类型已经在相应的服务调用方法中指出
				ResultItem result = (ResultItem) msg.obj;

				// result.setMessage(texts);

				// ArrayList();

				break;

			case ConstantValues.SERVICE_ERROR:
				Toast.makeText(MyAttentionpAty.this, "与服务器通讯发生错误",
						Toast.LENGTH_LONG).show();
				break;

			case ConstantValues.SERVICE_EXCEPTION:
				Toast.makeText(MyAttentionpAty.this, "与服务器通讯发生异常",
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
