package com.neusoft.fragment;

import com.neusoft.activities.AttentionManDataAty;
import com.neusoft.activities.GraphAty;
import com.neusoft.defertrain.R;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

@SuppressLint("NewApi")
public class BrainsReportFrgt1 extends Fragment {

	private TextView txt1, txt2, txt3, txt4, txt5, txt6;
	private TextView tet1, tet2, tet3, tet4, tet5, tet6;// 数据
	private ProgressBar proBar1, proBar2, proBar3, proBar4, proBar5, proBar6;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View rootView = inflater.inflate(R.layout.brainsreport_item1, container, false);
		txt1 = (TextView) container.findViewById(R.id.t1);
		txt2 = (TextView) container.findViewById(R.id.t2);
		txt3 = (TextView) container.findViewById(R.id.t3);
		txt4 = (TextView) container.findViewById(R.id.t4);
		txt5 = (TextView) container.findViewById(R.id.t5);
		txt6 = (TextView) container.findViewById(R.id.t6);

		tet1 = (TextView) container.findViewById(R.id.shuzi1);
		tet2 = (TextView) container.findViewById(R.id.shuzi2);
		tet3 = (TextView) container.findViewById(R.id.shuzi3);
		tet4 = (TextView) container.findViewById(R.id.shuzi4);
		tet5 = (TextView) container.findViewById(R.id.shuzi5);
		tet6 = (TextView) container.findViewById(R.id.shuzi6);

		proBar1 = (ProgressBar) container.findViewById(R.id.pros1);
		proBar2 = (ProgressBar) container.findViewById(R.id.pros2);
		proBar3 = (ProgressBar) container.findViewById(R.id.pros3);
		proBar4 = (ProgressBar) container.findViewById(R.id.pros4);
		proBar5 = (ProgressBar) container.findViewById(R.id.pros5);
		proBar6 = (ProgressBar) container.findViewById(R.id.pros6);

		String s = null;
		int shu = 0;

		tet1.setText(s);
		tet2.setText(s);
		tet3.setText(s);
		tet4.setText(s);
		tet5.setText(s);
		tet6.setText(s);

		proBar1.setProgress(shu);
		proBar2.setProgress(shu);
		proBar3.setProgress(shu);
		proBar4.setProgress(shu);
		proBar5.setProgress(shu);
		proBar6.setProgress(shu);

		return rootView;

	}

	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(getActivity(), GraphAty.class);

			int[] id = null;

			switch (getId()) {
			case R.id.t1:
				intent.putExtra("piaoti", "综合得分");
				intent.putExtra("shuzi", id);
				break;
			case R.id.t2:
				intent.putExtra("piaoti", "速度");
				intent.putExtra("shuzi", id);
				break;
			case R.id.t3:
				intent.putExtra("piaoti", "记忆力");
				intent.putExtra("shuzi", id);
				break;
			case R.id.t4:
				intent.putExtra("piaoti", "注意力");
				intent.putExtra("shuzi", id);
				break;
			case R.id.t5:
				intent.putExtra("piaoti", "灵活性");
				intent.putExtra("shuzi", id);
				break;
			case R.id.t6:
				intent.putExtra("piaoti", "问题解决能力");
				intent.putExtra("shuzi", id);
				break;

			}
			
             startActivity(intent);

		}

	};

}
