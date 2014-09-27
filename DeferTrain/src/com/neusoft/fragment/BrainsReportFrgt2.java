package com.neusoft.fragment;

import com.neusoft.defertrain.R;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

@SuppressLint("NewApi") 
public class BrainsReportFrgt2 extends Fragment{

	private TextView txt1, txt2, txt3, txt4, txt5, txt6;
	private TextView tet1, tet2, tet3, tet4, tet5, tet6;
	private ProgressBar proBar1, proBar2, proBar3, proBar4, proBar5, proBar6;
	private ProgressBar pro1, pro2, pro3, pro4, pro5, pro6;
	private Button jianyi;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.brainsreport_item2, container, false);
		txt1 = (TextView) container.findViewById(R.id.shu1);
		txt2 = (TextView) container.findViewById(R.id.shu2);
		txt3 = (TextView) container.findViewById(R.id.shu3);
		txt4 = (TextView) container.findViewById(R.id.shu4);
		txt5 = (TextView) container.findViewById(R.id.shu5);
		txt6 = (TextView) container.findViewById(R.id.shu6);

		tet1 = (TextView) container.findViewById(R.id.pinjun1);
		tet2 = (TextView) container.findViewById(R.id.pinjun2);
		tet3 = (TextView) container.findViewById(R.id.pinjun3);
		tet4 = (TextView) container.findViewById(R.id.pinjun4);
		tet5 = (TextView) container.findViewById(R.id.pinjun5);
		tet6 = (TextView) container.findViewById(R.id.pinjun6);

		proBar1 = (ProgressBar) container.findViewById(R.id.pros1);
		proBar2 = (ProgressBar) container.findViewById(R.id.pros2);
		proBar3 = (ProgressBar) container.findViewById(R.id.pros3);
		proBar4 = (ProgressBar) container.findViewById(R.id.pros4);
		proBar5 = (ProgressBar) container.findViewById(R.id.pros5);
		proBar6 = (ProgressBar) container.findViewById(R.id.pros6);
		
		pro1 = (ProgressBar) container.findViewById(R.id.pin1);
		pro2 = (ProgressBar) container.findViewById(R.id.pin2);
		pro3 = (ProgressBar) container.findViewById(R.id.pin3);
		pro4 = (ProgressBar) container.findViewById(R.id.pin4);
		pro5 = (ProgressBar) container.findViewById(R.id.pin5);
		pro6 = (ProgressBar) container.findViewById(R.id.pin6);
		
		jianyi = (Button)container.findViewById(R.id.xunlianb);

		String s = null;
		int shu = 0;
		
		txt1.setText(Integer.toString(shu));
		txt2.setText(Integer.toString(shu));
		txt3.setText(Integer.toString(shu));
		txt4.setText(Integer.toString(shu));
		txt5.setText(Integer.toString(shu));
		txt6.setText(Integer.toString(shu));

		tet1.setText(Integer.toString(shu));
		tet2.setText(Integer.toString(shu));
		tet3.setText(Integer.toString(shu));
		tet4.setText(Integer.toString(shu));
		tet5.setText(Integer.toString(shu));
		tet6.setText(Integer.toString(shu));

		proBar1.setProgress(shu);
		proBar2.setProgress(shu);
		proBar3.setProgress(shu);
		proBar4.setProgress(shu);
		proBar5.setProgress(shu);
		proBar6.setProgress(shu);
		
		pro1.setProgress(shu);
		pro2.setProgress(shu);
		pro3.setProgress(shu);
		pro4.setProgress(shu);
		pro5.setProgress(shu);
		pro6.setProgress(shu);

		return rootView;
	}
}
