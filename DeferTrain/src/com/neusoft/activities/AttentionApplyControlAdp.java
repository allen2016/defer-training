package com.neusoft.activities;

import java.util.List;

import com.neusoft.defertrain.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class AttentionApplyControlAdp extends BaseAdapter {

	private List<AttentionMyNews> listItems;
	private LayoutInflater inflater = null;

	public AttentionApplyControlAdp(Context context,
			List<AttentionMyNews> listItems) {
		super();
		this.listItems = listItems;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listItems.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final Holder holder;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.attentionmy_top, parent,
					false);
			holder = new Holder();

			holder.added = (ImageView) convertView.findViewById(R.id.added);
			holder.add = (ImageButton) convertView.findViewById(R.id.add);
			holder.imageViewheadportrait = (ImageView) convertView
					.findViewById(R.id.pic);
			holder.nameTextView = (TextView) convertView
					.findViewById(R.id.text_name);
			holder.txtTextView = (TextView) convertView.findViewById(R.id.text);
			holder.remarkTextView = (TextView) convertView
					.findViewById(R.id.text_remark);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		holder.imageViewheadportrait.setImageResource(listItems.get(position)
				.getFaceId());
		holder.nameTextView.setText(listItems.get(position).getName());
		holder.txtTextView.setText(listItems.get(position).getText());
		holder.remarkTextView.setText(listItems.get(position).getText_remark());

		holder.add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				holder.added.setVisibility(View.VISIBLE);
				holder.add.setVisibility(View.GONE);
			}
		});

		return convertView;

	}

	static class Holder {
		ImageButton add;
		TextView nameTextView, txtTextView, remarkTextView;
		ImageView added, imageViewheadportrait;
	}

}