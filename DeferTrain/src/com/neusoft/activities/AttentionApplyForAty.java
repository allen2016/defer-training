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
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

@SuppressLint("ResourceAsColor")
public class AttentionApplyForAty extends Activity {

	private TitleView titleView;
	private ToggleButton tglBtntraining_plan, tglBtnmental_report;
	private TextView txtname, txtinformation, txtadditioninformation, txtID;
	private Button btnagree, btncancel, btncancel_attention;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.attentionmy_apply_has_agree);

		findView();

		init();

		setOnClickListener();

		int agreebutton = 1;
		int traintglBtnbswitch = 1;
		int mentaltglBtnbswitch = 1;

		if (agreebutton == 1) {
			btnagree = (Button) findViewById(R.id.agree);
			btncancel = (Button) findViewById(R.id.cancel);
			btncancel.setText(R.string.cancel_attention);
			btnagree.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

				}
			});
			btncancel.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

				}
			});

		} else {
			btnagree = (Button) findViewById(R.id.agree);
			btnagree.setVisibility(View.VISIBLE);
			btncancel = (Button) findViewById(R.id.cancel);
			btncancel.setText(R.string.cancel);
			btnagree.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

				}
			});
			btncancel.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

				}
			});

		}
		if (traintglBtnbswitch == 0 && mentaltglBtnbswitch == 0) {

			tglBtntraining_plan = (ToggleButton) findViewById(R.id.togtraining_plan_off);
			tglBtntraining_plan.setVisibility(View.VISIBLE);
			tglBtnmental_report = (ToggleButton) findViewById(R.id.togmental_report_off);
			tglBtnmental_report.setVisibility(View.VISIBLE);
			tglBtntraining_plan
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							if (isChecked) {

							} else {

							}
						}
					});
			tglBtnmental_report
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							if (isChecked) {

							} else {

							}
						}
					});

		} else if (traintglBtnbswitch == 1 && mentaltglBtnbswitch == 0) {
			tglBtntraining_plan = (ToggleButton) findViewById(R.id.togtraining_plan_on);
			tglBtntraining_plan.setVisibility(View.VISIBLE);
			tglBtnmental_report = (ToggleButton) findViewById(R.id.togmental_report_off);
			tglBtnmental_report.setVisibility(View.VISIBLE);
			tglBtntraining_plan
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							if (isChecked) {

							} else {

							}
						}
					});
			tglBtnmental_report
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							if (isChecked) {

							} else {

							}
						}
					});
		} else if (traintglBtnbswitch == 0 && mentaltglBtnbswitch == 1) {
			tglBtntraining_plan = (ToggleButton) findViewById(R.id.togtraining_plan_off);
			tglBtntraining_plan.setVisibility(View.VISIBLE);
			tglBtnmental_report = (ToggleButton) findViewById(R.id.togmental_report_on);
			tglBtnmental_report.setVisibility(View.VISIBLE);
			tglBtntraining_plan
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							if (isChecked) {

							} else {

							}
						}
					});
			tglBtnmental_report
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							if (isChecked) {

							} else {

							}
						}
					});
		} else if (traintglBtnbswitch == 1 && mentaltglBtnbswitch == 1) {
			tglBtntraining_plan = (ToggleButton) findViewById(R.id.togtraining_plan_on);
			tglBtntraining_plan.setVisibility(View.VISIBLE);
			tglBtnmental_report = (ToggleButton) findViewById(R.id.togmental_report_on);
			tglBtnmental_report.setVisibility(View.VISIBLE);
			tglBtntraining_plan
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							if (isChecked) {

							} else {

							}
						}
					});
			tglBtnmental_report
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							if (isChecked) {

							} else {

							}
						}
					});
		}

	}

	private void findView() {
		titleView = (TitleView) findViewById(R.id.mytitleview);
		txtname = (TextView) findViewById(R.id.name);
		txtadditioninformation = (TextView) findViewById(R.id.additioninformation);
		txtinformation = (TextView) findViewById(R.id.information);
		txtID = (TextView) findViewById(R.id.ID);

	}

	private void init() {
		// TODO Auto-generated method stub
		titleView.setTitleText(getString(R.string.attentionapplyfor));
		titleView.setLeftIcon(R.drawable.ic_launcher);
		titleView.setLeftIconVisible(View.VISIBLE);
		titleView.setRightIconVisible(View.VISIBLE);
		titleView.setTitleTextSize(15);
		titleView.setTitleTextColor(R.color.personalcenter_txt);
		titleView.setBackgroundColor(getResources()
				.getColor(R.color.home_title));

		txtname.setText("");
		txtadditioninformation.setText("");
		txtinformation.setText("");
		txtID.setText("");
	}

	private void setOnClickListener() {

		titleView.setLeftIconOnClick(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(ConstantKey.ATTENTION_MY_ACTION);
				startActivity(intent);
			}
		});
		titleView.setRightIconOnClick(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(ConstantKey.SET_REMARK_ACTION);
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
				Toast.makeText(AttentionApplyForAty.this, "当前没有网络",
						Toast.LENGTH_LONG).show();
				break;

			case ConstantValues.SERVICE_BAGIN:
				Toast.makeText(AttentionApplyForAty.this, "请耐心等待，正在与服务器通讯",
						Toast.LENGTH_LONG).show();
				break;

			case ConstantValues.SERVICE_SUCCESS:
				Toast.makeText(AttentionApplyForAty.this, "从服务器上成功获取数据",
						Toast.LENGTH_LONG).show();
				// 这是从服务器获取的数据，注意要转换成适当的类型，返回的类型已经在相应的服务调用方法中指出
				ResultItem result = (ResultItem) msg.obj;

				// result.setMessage(texts);

				break;

			case ConstantValues.SERVICE_ERROR:
				Toast.makeText(AttentionApplyForAty.this, "与服务器通讯发生错误",
						Toast.LENGTH_LONG).show();
				break;

			case ConstantValues.SERVICE_EXCEPTION:
				Toast.makeText(AttentionApplyForAty.this, "与服务器通讯发生异常",
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