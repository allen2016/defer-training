package com.neusoft.activities;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.mycustom.view.viewpagerindicator.CirclePageIndicator;
import com.neusoft.defertrain.R;

public class WizardAty extends FragmentActivity {

	private final static String LOGIN_ACTION_STRING = "";
	private final static String REGISTER_ACTION_STRING = "";
	
	private ViewPager viewPager;
	private CirclePageIndicator pageIndicator;
	private FragmentPagerAdapter pageAdapter;
	private List<View> views;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.wizard);
		
		findView();
		
		init();
		
		setOnClickListener();
		
	}

	private void findView() {
		// TODO Auto-generated method stub
		viewPager = (ViewPager) findViewById(R.id.pager);
		pageIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
		
		pageAdapter = new WizardIntroAdapter(getSupportFragmentManager()); 
	}

	private void init() {
		// TODO Auto-generated method stub
		
		final float density = getResources().getDisplayMetrics().density;
		pageIndicator.setBackgroundColor(0xFFCCCCCC);
		pageIndicator.setRadius(10 * density);
		pageIndicator.setPageColor(0x880000FF);
		pageIndicator.setFillColor(0xFF888888);
		pageIndicator.setStrokeColor(0xFF000000);
		pageIndicator.setStrokeWidth(2 * density);
		
		viewPager.setAdapter(pageAdapter);  
		pageIndicator.setViewPager(viewPager);
		
	}
	
	private void setOnClickListener() {
		// TODO Auto-generated method stub
		
		pageIndicator.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	

	class WizardIntroAdapter extends FragmentPagerAdapter {
		
        public WizardIntroAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return null; //ContentFragment.newInstance(CONTENT[position % CONTENT.length]);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "SDSDSD";  //CONTENT[position % CONTENT.length].toUpperCase();
        }

        @Override
        public int getCount() {
          return 0; //CONTENT.length;
        }
    }


	public static class ContentFragment extends Fragment {
		
	    private static final String KEY_CONTENT = "TestFragment:Content";
	    private String mContent = "";

	    public static ContentFragment newInstance(String content) {
	    	
	        ContentFragment fragment = new ContentFragment();

	        StringBuilder builder = new StringBuilder();
	        for (int i = 0; i < 10; i++) {
	            builder.append(content).append(" ");
	        }
	        builder.deleteCharAt(builder.length() - 1);
	        fragment.mContent = builder.toString();

	        return fragment;
	    }

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);

	        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
	            mContent = savedInstanceState.getString(KEY_CONTENT);
	        }
	    }

	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    	
	        TextView text = new TextView(getActivity());
	        text.setGravity(Gravity.CENTER);
	        text.setText(mContent);
	        text.setTextSize(20 * getResources().getDisplayMetrics().density);
	        text.setPadding(20, 20, 20, 20);

	        LinearLayout layout = new LinearLayout(getActivity());
	        layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	        layout.setGravity(Gravity.CENTER);
	        layout.addView(text);

	        return layout;
	    }

	    @Override
	    public void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	        outState.putString(KEY_CONTENT, mContent);
	    }
	}
}
