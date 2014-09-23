package com.neusoft.activities;

import java.util.List;

import com.neusoft.defertrain.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class InteractionRoomAdp extends BaseAdapter {  
	      
	    //ListView视图的内容由IMsgViewType决定  
	    public static interface IMsgViewType  
	    {  
	        //对方发来的信息  
	        int IMVT_COM_MSG = 0;  
	        //自己发出的信息  
	        int IMVT_TO_MSG = 1;  
	    }  
	      
	    private static final String TAG = InteractionRoomAdp.class.getSimpleName();  
	    private List<InteractionRoomMsgEntity> data;  
	    private Context context;    
	    private LayoutInflater mInflater;  
	  
	    public InteractionRoomAdp(Context context, List<InteractionRoomMsgEntity> data) {  
	        this.context = context;  
	        this.data = data;  
	        mInflater = LayoutInflater.from(context);  
	    }  
	  
	    //获取ListView的项个数  
	    public int getCount() {  
	        return data.size();  
	    }  
	  
	    //获取项  
	    public Object getItem(int position) {  
	        return data.get(position);  
	    }  
	  
	    //获取项的ID  
	    public long getItemId(int position) {  
	        return position;  
	    }  
	  
	    //获取项的类型  
	    public int getItemViewType(int position) {  
	        // TODO Auto-generated method stub  
	        InteractionRoomMsgEntity entity = data.get(position);  
	          
	        if (entity.getMsgType())  
	        {  
	            return IMsgViewType.IMVT_COM_MSG;  
	        }else{  
	            return IMsgViewType.IMVT_TO_MSG;  
	        }  
	          
	    }  
	  
	    //获取项的类型数  
	    public int getViewTypeCount() {  
	        // TODO Auto-generated method stub  
	        return 2;  
	    }  
	      
	    //获取View  
	    public View getView(int position, View convertView, ViewGroup parent) {  
	          
	        InteractionRoomMsgEntity entity = data.get(position);  
	        boolean isComMsg = entity.getMsgType();  
	              
	        ViewHolder viewHolder = null;     
	        if (convertView == null)  
	        {  
	              if (isComMsg)  
	              {  
	                  //如果是对方发来的消息，则显示的是左气泡  
	                  convertView = mInflater.inflate(R.layout.chatting_item_msg_text_left, null);  
	              }else{  
	                  //如果是自己发出的消息，则显示的是右气泡  
	                  convertView = mInflater.inflate(R.layout.chatting_item_msg_text_right, null);  
	              }  
	  
	              viewHolder = new ViewHolder();  
	              viewHolder.txtSendTime = (TextView) convertView.findViewById(R.id.tv_sendtime);  
	              viewHolder.txtUserName = (TextView) convertView.findViewById(R.id.tv_username);  
	              viewHolder.txtContent = (TextView) convertView.findViewById(R.id.tv_chatcontent);  
	              viewHolder.isComMsg = isComMsg;  
	                
	              convertView.setTag(viewHolder);  
	        }else{  
	            viewHolder = (ViewHolder) convertView.getTag();  
	        }  
	        viewHolder.txtSendTime.setText(entity.getDate());  
	        viewHolder.txtUserName.setText(entity.getName());  
	        viewHolder.txtContent.setText(entity.getText());  
	          
	        return convertView;  
	    }  
	      
	    //通过ViewHolder显示项的内容  
	    static class ViewHolder {   
	        public TextView txtSendTime;  
	        public TextView txtUserName;  
	        public TextView txtContent;  
	        public boolean isComMsg = true;  
	    }

	      
	}  


