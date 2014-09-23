package com.neusoft.activities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.mycustom.view.TitleView;
import com.neusoft.defertrain.R;
import com.neusoft.tools.ConstantKey;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.Window;
import android.content.Intent;  
import android.view.KeyEvent;  
import android.view.Menu;  
import android.view.View;  
import android.widget.AdapterView;  
import android.widget.AdapterView.OnItemClickListener;  
import android.widget.Button;  
import android.widget.EditText;  
import android.widget.ListView;  
import android.widget.TextView;  
@SuppressLint("ResourceAsColor")
public class InteractionRoomAty extends Activity implements OnClickListener {  
    private Button BtnSend;  
    private EditText edtTxtContent;  
    //聊天内容的适配器  
    private InteractionRoomAdp Adapter;  
    private ListView  lVi;  
    //聊天的内容  
    private List<InteractionRoomMsgEntity> DataArrays = new ArrayList<InteractionRoomMsgEntity>();  
    private TitleView titleView; 
    
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏  
        setContentView(R.layout.chat); 
        
        initView();  
        
        init();
        
        initData();
        
        getDate();
        
        send() ;
        
        setOnClickListener();
    }  
      
    //初始化视图  
    private void initView() {  
    	 lVi = (ListView) findViewById(R.id.listview);  
        titleView = (TitleView) findViewById(R.id.mytitleview);
   
        BtnSend = (Button) findViewById(R.id.btn_send);  
         
        edtTxtContent = (EditText) findViewById(R.id.et_sendmessage);  
    }  
  
    private String[] msgArray = new String[]{"  孩子们，要好好学习，天天向上！要好好听课，不要翘课！不要挂科，多拿奖学金！三等奖学金的争取拿二等，二等的争取拿一等，一等的争取拿励志！",   
            "姚妈妈还有什么吩咐...",   
            "还有，明天早上记得跑操啊，不来的就扣德育分！",   
            "德育分是什么？扣了会怎么样？",   
            "德育分会影响奖学金评比，严重的话，会影响毕业",   
            "哇！学院那么不人道？",  
            "你要是你不听话，我当场让你不能毕业！",   
            "姚妈妈，我知错了(- -我错在哪了...)"};  
  
    private String[]dataArray = new String[]{"2012-09-01 18:00", "2012-09-01 18:10",   
            "2012-09-01 18:11", "2012-09-01 18:20",   
            "2012-09-01 18:30", "2012-09-01 18:35",   
            "2012-09-01 18:40", "2012-09-01 18:50"};  
    private final static int COUNT = 8;  
      
    //初始化要显示的数据  
    private void initData() {  
        for(int i = 0; i < COUNT; i++) {  
            InteractionRoomMsgEntity entity = new InteractionRoomMsgEntity();  
            entity.setDate(dataArray[i]);  
           // if (i % 2 == 0)  
          //  {  
                entity.setName("姚妈妈");  
                entity.setMsgType(true);  
          //  }else{  
           //     entity.setName("Shamoo");  
            //    entity.setMsgType(false);  
           // }  
              
            entity.setText(msgArray[i]);  
            DataArrays.add(entity);  
        }  
        Adapter = new InteractionRoomAdp(this, DataArrays);  
        lVi.setAdapter(Adapter);  
    }  
      
    public void onClick(View view) {  
        // TODO Auto-generated method stub  
        switch(view.getId()) {  
         
        case R.id.btn_send:  
            send();  
            break;  
        }  
    }  
  
    
    private void init() {
		// TODO Auto-generated method stub
		titleView.setTitleText(getString(R.string.about));
		titleView.setLeftIcon(R.drawable.ic_launcher);
		titleView.setLeftIconVisible(View.VISIBLE);
		titleView.setRightIconVisible(View.GONE);
		titleView.setRightIcon(R.drawable.ic_launcher);
		titleView.setTitleTextSize(15);
		titleView.setTitleTextColor(R.color.personalcenter_txt);
		titleView.setBackgroundColor(getResources()
				.getColor(R.color.home_title));
		
		BtnSend.setOnClickListener(this); 
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
    
    private void send()  
    {  
        String contString = edtTxtContent.getText().toString();  
        if (contString.length() > 0)  
        {  
            InteractionRoomMsgEntity entity = new InteractionRoomMsgEntity();  
            entity.setDate(getDate());  
            entity.setName("");  
            entity.setMsgType(false);  
            entity.setText(contString);  
            Adapter.notifyDataSetChanged();  
            edtTxtContent.setText("");  
            lVi.setSelection( lVi.getCount() - 1);  
        }  
    }  
      
    //获取日期  
    private String getDate() {  
        Calendar c = Calendar.getInstance();  
        String year = String.valueOf(c.get(Calendar.YEAR));  
        String month = String.valueOf(c.get(Calendar.MONTH)+1);  
        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));  
        String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));  
        String mins = String.valueOf(c.get(Calendar.MINUTE));  
        StringBuffer sbBuffer = new StringBuffer();  
        sbBuffer.append(year + "-" + month + "-" + day + " " + hour + ":" + mins);   
        return sbBuffer.toString();  
    }  
    public boolean onKeyDown(int keyCode, KeyEvent event) {  
        back();  
        return true;  
    }  
      
    private void back() {  
        finish();  
    }

	
}  