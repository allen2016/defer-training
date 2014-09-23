package com.neusoft.activities;

import java.util.Hashtable;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.graphics.Bitmap;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.mycustom.entity.require.RegisterReqItem;
import com.mycustom.entity.result.ResultItem;
import com.mycustom.tools.ConstantValues;
import com.mycustom.tools.RequestService;
import com.mycustom.view.TitleView;
import com.neusoft.defertrain.R;
import com.neusoft.tools.ConstantKey;

@SuppressLint("ResourceAsColor")
public class MyCallingCardAty extends Activity {

	private ImageView codeImg;
	private TextView textname;
	private String name = "小明";
	private TitleView titleView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mycallingcard);

		findView();

		init();

		setListener();

		setOnClickListener();
	}

	private void findView() {
		titleView = (TitleView) findViewById(R.id.mytitleview);
		codeImg = (ImageView) findViewById(R.id.code_img);
		textname = (TextView) findViewById(R.id.textname);
		textname.setText(name);

	}

	private void setListener() {

		Bitmap bmp = null;
		try {
			if (name != null && !"".equals(name)) {
				bmp = CreateTwoDCode(name);
			}
		} catch (WriterException e) {
			e.printStackTrace();
		}
		if (bmp != null) {
			codeImg.setImageBitmap(bmp);
		}

	}

	public Bitmap CreateTwoDCode(String content) throws WriterException {

		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix matrix = new MultiFormatWriter().encode(content,
				BarcodeFormat.QR_CODE, 500, 500, hints);

		int width = matrix.getWidth();
		int height = matrix.getHeight();

		int[] pixels = new int[width * height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (matrix.get(x, y)) {
					pixels[y * width + x] = 0xff000000;
				}
			}
		}

		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}

	private void init() {
		// TODO Auto-generated method stub
		titleView.setTitleText(getString(R.string.mycallingcard));
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
	}

	private Handler myHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			switch (msg.what) {

			case ConstantValues.INTERNET_NOEXIST:
				Toast.makeText(MyCallingCardAty.this, "当前没有网络",
						Toast.LENGTH_LONG).show();
				break;

			case ConstantValues.SERVICE_BAGIN:
				Toast.makeText(MyCallingCardAty.this, "请耐心等待，正在与服务器通讯",
						Toast.LENGTH_LONG).show();
				break;

			case ConstantValues.SERVICE_SUCCESS:
				Toast.makeText(MyCallingCardAty.this, "从服务器上成功获取数据",
						Toast.LENGTH_LONG).show();
				// 这是从服务器获取的数据，注意要转换成适当的类型，返回的类型已经在相应的服务调用方法中指出
				ResultItem result = (ResultItem) msg.obj;

				// result.setMessage(texts);

				break;

			case ConstantValues.SERVICE_ERROR:
				Toast.makeText(MyCallingCardAty.this, "与服务器通讯发生错误",
						Toast.LENGTH_LONG).show();
				break;

			case ConstantValues.SERVICE_EXCEPTION:
				Toast.makeText(MyCallingCardAty.this, "与服务器通讯发生异常",
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
