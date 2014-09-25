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
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ResourceAsColor")
public class IDAty extends Activity {

	private ImageButton imgBtnheadportrait;
	private TextView txtemaill, txtid_name, txtcell_phone_number,
			txtlog_in_password, txtsex, txtbirth_date, txtcity;
	private Button btnlog_out;
	private TitleView titleView;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_id);

		findView();

		init();

		setOnClickListener();

	}

	private void findView() {
		titleView = (TitleView) findViewById(R.id.mytitleview);

		imgBtnheadportrait = (ImageButton) findViewById(R.id.headportrait);

		txtemaill = (TextView) findViewById(R.id.email);

		txtid_name = (TextView) findViewById(R.id.id_name);

		txtcell_phone_number = (TextView) findViewById(R.id.cell_phone_number);

		txtlog_in_password = (TextView) findViewById(R.id.log_in_password);

		txtsex = (TextView) findViewById(R.id.sex);

		txtbirth_date = (TextView) findViewById(R.id.birth_date);

		txtcity = (TextView) findViewById(R.id.city);

		btnlog_out = (Button) findViewById(R.id.log_out);
	}

	private void init() {
		// TODO Auto-generated method stub
		titleView.setTitleText(getString(R.string.ID));
		titleView.setLeftIcon(R.drawable.ic_launcher);
		titleView.setLeftIconVisible(View.VISIBLE);
		titleView.setRightIconVisible(View.VISIBLE);
		titleView.setRightIcon(R.drawable.ic_launcher);
		titleView.setTitleTextSize(15);
		titleView.setTitleTextColor(R.color.personalcenter_txt);
		titleView.setBackgroundColor(getResources()
				.getColor(R.color.home_title));

		imgBtnheadportrait.setImageResource(R.drawable.ic_launcher);

		
		  txtemaill.setText("");
		 
		 txtid_name.setText("AA");
		 
		 txtcell_phone_number.setText("AA");
		 
		 txtlog_in_password.setText("AA");
		 
		 txtsex.setText("AA");
		 
		txtbirth_date.setText("AA");
		 
		 txtcity.setText("AA");
		

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

		titleView.setRightIconOnClick(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(ConstantKey.ID_AMEND_ACTION);
				startActivity(intent);
			}
		});

		btnlog_out.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

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
				Toast.makeText(IDAty.this, "当前没有网络",
						Toast.LENGTH_LONG).show();
				break;

			case ConstantValues.SERVICE_BAGIN:
				Toast.makeText(IDAty.this, "请耐心等待，正在与服务器通讯",
						Toast.LENGTH_LONG).show();
				break;

			case ConstantValues.SERVICE_SUCCESS:
				Toast.makeText(IDAty.this, "从服务器上成功获取数据",
						Toast.LENGTH_LONG).show();
				// 这是从服务器获取的数据，注意要转换成适当的类型，返回的类型已经在相应的服务调用方法中指出
				ResultItem result = (ResultItem) msg.obj;

				// result.setMessage(texts);

				break;

			case ConstantValues.SERVICE_ERROR:
				Toast.makeText(IDAty.this, "与服务器通讯发生错误",
						Toast.LENGTH_LONG).show();
				break;

			case ConstantValues.SERVICE_EXCEPTION:
				Toast.makeText(IDAty.this, "与服务器通讯发生异常",
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
