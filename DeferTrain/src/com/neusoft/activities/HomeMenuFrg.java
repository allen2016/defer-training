package com.neusoft.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.neusoft.defertrain.R;
import com.neusoft.tools.ConstantKey;

/**
 * @author yangyu
 *	功能描述：列表Fragment，用来显示列表视图
 */
public class HomeMenuFrg extends ListFragment {

	private int[] iconList = {R.drawable.home, R.drawable.setting2,
			                  R.drawable.hint,R.drawable.trash,
			                  R.drawable.terms,R.drawable.feedback,
			                  R.drawable.about };
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.home_list, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		SampleAdapter<SampleItem> adapter = new SampleAdapter<SampleItem>(getActivity());
		
		String[] menu = getResources().getStringArray(R.array.homeMenu);
		
		SampleItem sampleItem;
		for (int i = 0; i < menu.length; i++) {
			sampleItem = new SampleItem(menu[i], iconList[i]);
			adapter.add(sampleItem);
		}

		setListAdapter(adapter);
	}

	public class SampleAdapter<T> extends ArrayAdapter<T> {
		
		public SampleAdapter(Context context) {
			super(context, 0);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.home_list_item, null);
			}
			
			ImageView icon = (ImageView) convertView.findViewById(R.id.row_icon);
			icon.setImageResource(((SampleItem)getItem(position)).iconRes);
			TextView title = (TextView) convertView.findViewById(R.id.row_title);
			title.setText(((SampleItem)getItem(position)).name);
			
			return convertView;
		}
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		
		String actionString = "";
		Intent intent = new Intent();
		
		switch (position) {
			case 0:
				actionString = ConstantKey.WIZARD_ACTION;
				break;
			case 1:
				actionString = ConstantKey.WIZARD_ACTION;
				break;
			case 2:
				actionString = ConstantKey.WIZARD_ACTION;
				break;
			case 3:
				actionString = ConstantKey.WIZARD_ACTION;
				break;
			case 4:
				actionString = ConstantKey.WIZARD_ACTION;
				break;
			case 5:
				actionString = ConstantKey.WIZARD_ACTION;
				break;
			case 6:
				actionString = ConstantKey.WIZARD_ACTION;
				break;
		}
		
		if (!"".equals(actionString)) {
			intent.setAction(actionString);
			startActivity(intent);
		}
		
		
//		super.onListItemClick(l, v, position, id);
	}

	private class SampleItem {
		
		public String name;
		public int iconRes;

		public SampleItem(String tag, int iconRes) {
			this.name = tag;
			this.iconRes = iconRes;
		}
	}

}
