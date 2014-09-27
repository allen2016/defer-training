package com.neusoft.adapters;
//用户列表
import java.util.ArrayList;
import java.util.List;






import com.mycustom.entity.items.Friend;
import com.neusoft.activities.AttentionManDataAty;
import com.neusoft.activities.BrainsReportAty;
import com.neusoft.activities.InteractionAty;
import com.neusoft.activities.PlanAccomplishCaseAty;
import com.neusoft.activities.WeekTrainingPlanAty;
import com.neusoft.adapters.UserFriendAdp;
import com.neusoft.defertrain.R;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.sax.StartElementListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class UserFriendAdp extends BaseAdapter {

	private Context context;
	private List<Friend> listitem;
	private LayoutInflater inflater;
	
	 public UserFriendAdp(Context ct, List<Friend> datas){
			super();
			this.context = ct;
			this.listitem = datas;
			inflater = LayoutInflater.from(ct);
		}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listitem.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listitem.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = inflater.inflate(
					R.layout.myattentionp_item, null);}
			ImageView head = (ImageView)convertView.findViewById(R.id.head);
			Button hudong = (Button)convertView.findViewById(R.id.hudong);
			TextView remarkname = (TextView)convertView.findViewById(R.id.remarkname);
			TextView emila = (TextView)convertView.findViewById(R.id.emila);
			TextView data1 = (TextView)convertView.findViewById(R.id.data1);
			TextView data2 = (TextView)convertView.findViewById(R.id.data2);
			TextView data3 = (TextView)convertView.findViewById(R.id.data3);
			
//			head.setImageResource(listitem.get(posit);
			remarkname.setText(listitem.get(position).getRemarkName());
			emila.setText(listitem.get(position).getEmail());
			data1.setText(listitem.get(position).getThisWeekFinish()+"/"+listitem.get(position).getThisWeekTask());
			data2.setText(listitem.get(position).getFinishPercentage());
			data3.setText(listitem.get(position).getMark());
			
			head.setOnClickListener(listener);
			hudong.setOnClickListener(listener);
			remarkname.setOnClickListener(listener);
			data1.setOnClickListener(listener);
			data2.setOnClickListener(listener);
			data3.setOnClickListener(listener);
			
			
		
		return convertView;
		
	}
	OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent =new Intent();

			switch (v.getId()) {
			case R.id.head:
				
				intent.setClass(context,AttentionManDataAty.class);
                intent.putParcelableArrayListExtra("friend", (ArrayList<? extends Parcelable>) listitem);
				break;
				
            case R.id.hudong:
				intent.setClass(context, InteractionAty.class);
				break;				
			case R.id.remarkname:
				
				intent.setClass(context,AttentionManDataAty.class);
				 intent.putParcelableArrayListExtra("friend", (ArrayList<? extends Parcelable>) listitem);
				break;

			case R.id.data1:
				intent.setClass(context,WeekTrainingPlanAty.class);
				intent.putExtra("s", "0");
				break;
			case R.id.data2:
				intent.setClass(context,PlanAccomplishCaseAty.class);
				intent.putExtra("s", "0");

				break;
			case R.id.data3:
				intent.setClass(context,BrainsReportAty.class);
				intent.putExtra("s", "0");			
				break;			
			}
			context.startActivity(intent);
		}
	};

}
