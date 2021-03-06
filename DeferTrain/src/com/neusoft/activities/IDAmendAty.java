package com.neusoft.activities;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.mycustom.entity.require.RegisterReqItem;
import com.mycustom.entity.result.ResultItem;
import com.mycustom.tools.ConstantValues;
import com.mycustom.tools.RequestService;
import com.mycustom.view.TitleView;
import com.neusoft.defertrain.R;
import com.neusoft.tools.ConstantKey;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

@SuppressLint("ResourceAsColor")
public class IDAmendAty extends Activity {

	private ImageView imgBtnheadportrait;
	private EditText edtTxtemaill, edtTxtid_name, edtTxtcell_phone_number,
			edtTxtlog_in_password, edtTxtsex, edtTxtbirth_date, edtTxtcity;
	private Button btnlog_out;
	private TitleView titleView;
	 private Bitmap myBitmap;  
	 private byte[] mContent; 
	 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_id_amend);

		findView();

		init();

		setOnClickListener();

	}

	private void findView() {
		titleView = (TitleView) findViewById(R.id.mytitleview);

		imgBtnheadportrait = (ImageView) findViewById(R.id.headportrait);

		edtTxtemaill = (EditText) findViewById(R.id.email);
		edtTxtid_name = (EditText) findViewById(R.id.id_name);
		edtTxtcell_phone_number = (EditText) findViewById(R.id.cell_phone_number);
		edtTxtlog_in_password = (EditText) findViewById(R.id.log_in_password);
		edtTxtsex = (EditText) findViewById(R.id.sex);
		edtTxtbirth_date = (EditText) findViewById(R.id.birth_date);
		edtTxtcity = (EditText) findViewById(R.id.city);

		btnlog_out = (Button) findViewById(R.id.log_out);
	}

	private void init() {
		// TODO Auto-generated method stub
		titleView.setTitleText(getString(R.string.personalcenter));
		titleView.setLeftIcon(R.drawable.ic_launcher);
		titleView.setLeftIconVisible(View.VISIBLE);
		titleView.setRightIconVisible(View.VISIBLE);
		titleView.setRightIcon(R.drawable.ic_launcher);
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
				intent.setAction(ConstantKey.INDEX_ACTION);
				startActivity(intent);
			}
		});

		btnlog_out.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				edtTxtemaill.getText().toString();
				edtTxtid_name.getText().toString();
				edtTxtcell_phone_number.getText().toString();
				edtTxtlog_in_password.getText().toString();
				edtTxtsex.getText().toString();
				edtTxtbirth_date.getText().toString();
				edtTxtcity.getText().toString();

			}
		});
		
		imgBtnheadportrait.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			/*	Intent intent = new Intent();
		       
			 	intent.setType("image/*");
		        
		        intent.setAction(Intent.ACTION_GET_CONTENT); 
		       
		        startActivityForResult(intent, 1);*/
				
			Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);  
                getImage.addCategory(Intent.CATEGORY_OPENABLE);  
                getImage.setType("image/jpeg");  
                startActivityForResult(getImage, 0);  

			}
		});
	}
	
 protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
	        // TODO Auto-generated method stub   
	        super.onActivityResult(requestCode, resultCode, data);  
	          
	        ContentResolver contentResolver  =getContentResolver();  
	        
	        try {  
                Uri selectedImage = data.getData();  
                String[] filePathColumn = { MediaStore.Images.Media.DATA };  
  
                Cursor cursor = getContentResolver().query(selectedImage,  
                        filePathColumn, null, null, null);  
                cursor.moveToFirst();  
  
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);  
                String picturePath = cursor.getString(columnIndex);  
                cursor.close();  
                imgBtnheadportrait.setImageBitmap(BitmapFactory.decodeFile(picturePath));  
            } catch (Exception e) {  
                // TODO: handle exception   
                e.printStackTrace();  
            }  
	 }
	 
	 public static Bitmap getPicFromBytes(byte[] bytes, BitmapFactory.Options opts) {   
	        if (bytes != null)   
	            if (opts != null)   
	                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,opts);   
	            else   
	                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);   
	        return null;   
	    }   
	      
	      
	      
	 public static byte[] readStream(InputStream in) throws Exception{  
	     byte[] buffer  =new byte[1024];  
	     int len  =-1;  
	     ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
	       
	     while((len=in.read(buffer))!=-1){  
	         outStream.write(buffer, 0, len);  
	     }  
	     byte[] data  =outStream.toByteArray();  
	     outStream.close();  
	     in.close();  
	     return data;  
	 }  
	
	
   
   
	private Handler myHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			switch (msg.what) {

			case ConstantValues.INTERNET_NOEXIST:
				Toast.makeText(IDAmendAty.this, "当前没有网络",
						Toast.LENGTH_LONG).show();
				break;

			case ConstantValues.SERVICE_BAGIN:
				Toast.makeText(IDAmendAty.this, "请耐心等待，正在与服务器通讯",
						Toast.LENGTH_LONG).show();
				break;

			case ConstantValues.SERVICE_SUCCESS:
				Toast.makeText(IDAmendAty.this, "从服务器上成功获取数据",
						Toast.LENGTH_LONG).show();
				// 这是从服务器获取的数据，注意要转换成适当的类型，返回的类型已经在相应的服务调用方法中指出
				ResultItem result = (ResultItem) msg.obj;

				// result.setMessage(texts);

				break;

			case ConstantValues.SERVICE_ERROR:
				Toast.makeText(IDAmendAty.this, "与服务器通讯发生错误",
						Toast.LENGTH_LONG).show();
				break;

			case ConstantValues.SERVICE_EXCEPTION:
				Toast.makeText(IDAmendAty.this, "与服务器通讯发生异常",
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