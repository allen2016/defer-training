package com.neusoft.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.mycustom.view.viewpagerindicator.CirclePageIndicator;
import com.neusoft.defertrain.R;
import com.neusoft.tools.ConstantKey;

public class WizardAty extends FragmentActivity {

	private final static String Index_ACTION_STRING = "com.neusoft.cao.Index";
	
	private Button startBtn;
	
	private ViewPager viewPager;
	private CirclePageIndicator pageIndicator;
	private FragmentPagerAdapter pageAdapter;
	
	private static final int[] LAYOUT_CONTENT = new int[] { R.drawable.home_btn1 , R.drawable.home_btn1, R.drawable.home_btn1, R.drawable.home_btn1 }; 
	
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
		
		startBtn = (Button) findViewById(R.id.startBtn);
	}

	private void init() {
		// TODO Auto-generated method stub
		startBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(ConstantKey.INDEX_ACTION);
				startActivity(intent);
				finish();
			}
		});
		
		viewPager.setAdapter(pageAdapter);  
		pageIndicator.setViewPager(viewPager);
		
	}
	
	private void setOnClickListener() {
		// TODO Auto-generated method stub
		
		pageIndicator.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				if (arg0==LAYOUT_CONTENT.length-1) {
					startBtn.setVisibility(View.VISIBLE);
				}else {
					startBtn.setVisibility(View.INVISIBLE);
				}
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
            return ContentFragment.newInstance(position % LAYOUT_CONTENT.length);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "";  
        }

        @Override
        public int getCount() {
          return LAYOUT_CONTENT.length;
        }
    }


	public static class ContentFragment extends Fragment {
		
	    private static final String KEY_CONTENT = "TestFragment:Content";
	    private int mContent;

	    public static ContentFragment newInstance(int index) {
	    	
	        ContentFragment fragment = new ContentFragment();

	        fragment.mContent = index;
	        
	        return fragment;
	    }

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);

	        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
	            mContent = savedInstanceState.getInt(KEY_CONTENT);
	        }
	    }

	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    	
	    	ImageView imgView = (ImageView) inflater.inflate(R.layout.wizard_item, null);
	    	imgView.setBackgroundResource(LAYOUT_CONTENT[mContent]);
	    	
	        return imgView;
	    }

	    @Override
	    public void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	        outState.putInt(KEY_CONTENT, mContent);
	    }
	}
}
