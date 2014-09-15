package com.neusoft.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.mycustom.view.slidingmenu.lib.SlidingMenu;
import com.mycustom.view.slidingmenu.lib.app.SlidingFragmentActivity;
import com.neusoft.defertrain.R;

@SuppressLint("NewApi")
public class HomeAty extends SlidingFragmentActivity {

	private SlidingMenu menu;
	
	private Fragment mContent;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// 初始化滑动视图         
		initSlidingMenu(savedInstanceState);
		
	}

	private void initSlidingMenu(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// 设置标题栏的标题        
		setTitle(getString(R.string.homeTitle)); 
		// 设置是否能够使用ActionBar来滑动         
		setSlidingActionBarEnabled(true);           
//		// 设置是否显示Home图标按钮         
//		ActionBar actionBar = getActionBar();
//		actionBar.setDisplayHomeAsUpEnabled(true);         
//		actionBar.setIcon(R.drawable.ic_launcher); 
//		//使用左上方icon可点，这样在onOptionsItemSelected里面才可以监听到R.id.home
//		actionBar.setDisplayHomeAsUpEnabled(true);
        
		// 如果保存的状态不为空则得到之前保存的Fragment，否则实例化MyFragment         
		if (savedInstanceState != null) {             
			mContent = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");         
		}         
		if (mContent == null) {            
			mContent = new HomeContentFrg();         
		} 
		
		// 设置主界面视图         
		setContentView(R.layout.home_content);      
		HomeContentFrg homeContentFrg = new HomeContentFrg();
		homeContentFrg.setContainer(this);
		getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, homeContentFrg).commit();
		
		// 设置滑动菜单的视图         
		setBehindContentView(R.layout.home_menu);         
		getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, new HomeMenuFrg()).commit(); 
		
		// 设置滑动菜单的属性值   
		menu = getSlidingMenu();
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);  
		menu.setShadowWidthRes(R.dimen.shadow_width);  
		menu.setShadowDrawable(R.drawable.home_menu_shadow);  
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);  
		menu.setFadeDegree(0.35f);  
		
	}
	
	/**      * 保存Fragment的状态      */    
	@Override    
	protected void onSaveInstanceState(Bundle outState) {         
		super.onSaveInstanceState(outState);         
		getSupportFragmentManager().putFragment(outState, "mContent", mContent);     
	}
	
	@Override  
	public void onBackPressed() {  
		//点击返回键关闭滑动菜单   
		if (menu.isMenuShowing()) {  
			menu.showContent();  
		} else {  
			super.onBackPressed();  
		}  
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
        switch (item.getItemId()) {
        
        	case android.R.id.home:
            
            toggle(); //动态判断自动关闭或开启SlidingMenu
//          getSlidingMenu().showMenu();//显示SlidingMenu
//          getSlidingMenu().showContent();//显示内容
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    /**      
     *  切换视图     
     *  @param fragment      
     */    
    public void switchContent(Fragment fragment) {         
    	mContent = fragment;         
    	getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();         
    	getSlidingMenu().showContent();     
    }
}
