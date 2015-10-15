package com.example.q3;


import java.util.ArrayList;

import com.example.fragment.*;
import com.jauker.widget.BadgeView;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends FragmentActivity {

	private Fragment1 f1;
	private Fragment2 f2;
	private Fragment3 f3;
	private Fragment4 f4;
	private Fragment5 f5;
	private RadioGroup group;
	private ArrayList<Fragment> fragments;
	private long exitTime = 0;
	private String number;
	private BadgeView badgeView;
	private RadioButton main_tab_item_jingxuan;
	private RadioButton rb1;
	private RadioButton rb2;
	private RadioButton rb3;
	private RadioButton rb4;
	private RadioButton rb5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		AppManager.getAppManager().addActivity(this);
		setContentView(R.layout.main);
		initViews();
		rb1 =(RadioButton)this.findViewById(R.id.rb1);
		rb2 =(RadioButton)this.findViewById(R.id.rb2);
		rb3 =(RadioButton)this.findViewById(R.id.rb3);
		rb4 =(RadioButton)this.findViewById(R.id.rb4);
		rb5 =(RadioButton)this.findViewById(R.id.rb5);

		group = (RadioGroup) findViewById(R.id.main_tab_bar);
		group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				int childCount = group.getChildCount();
				int checkedIndex = 0;
				if(rb1.isChecked()){
					checkedIndex = 0;
			}
				if(rb2.isChecked()){
					checkedIndex = 1;
			}
				if(rb3.isChecked()){
					checkedIndex = 2;
			}
				if(rb4.isChecked()){
					checkedIndex =3;
			}
				if(rb5.isChecked()){
					checkedIndex =4;
			}
				
				FragmentManager manager = getSupportFragmentManager();
				FragmentTransaction transaction = manager.beginTransaction();
				Fragment fragment = null;
				switch (checkedIndex) {
				case 0:
					fragment = fragments.get(0);
					transaction.replace(R.id.main_framelayout, fragment);
					transaction.commit();
					break;
				case 1:
					fragment = fragments.get(1);
					transaction.replace(R.id.main_framelayout, new Fragment2());
					transaction.commit();
					break;
				case 2:
					fragment = fragments.get(2);
					transaction.replace(R.id.main_framelayout, fragment);
					transaction.commit();

					break;
				case 3:
					fragment = fragments.get(3);
					transaction.replace(R.id.main_framelayout, fragment);
					transaction.commit();
					break;
				case 4:
					fragment = fragments.get(4);
					transaction.replace(R.id.main_framelayout, fragment);
					transaction.commit();
					break;

				default:
					break;
				}

						}
		});
		fragments = new ArrayList<Fragment>();
		fragments.add(f1);
		fragments.add(f2);
		fragments.add(f3);
		fragments.add(f4);
		fragments.add(f5);
	/*	badgeView = new BadgeView(this);
		badgeView.setTargetView(rb1);
		badgeView.setBadgeGravity(Gravity.TOP | Gravity.RIGHT);
	    badgeView.setBackgroundColor(Color.RED);
		badgeView.setBadgeCount(4);
*/
		/*BadgeView badgeView = new com.jauker.widget.BadgeView(this);
		badgeView.setTargetView(rb1);
		badgeView.setBackground(1111, Color.RED);
		badgeView.setGravity(Gravity.TOP|Gravity.RIGHT);
		badgeView.setTextSize(10);
		
		badgeView.setBadgeCount(3);
		badgeView.setFocusable(false);
		badgeView.setClickable(false);
		*/
		
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		Fragment fragment = null;
		fragment = fragments.get(0);
		transaction.replace(R.id.main_framelayout, fragment);
		transaction.commit();
         
	

		

	}

	private void initViews() {
		f1 =new Fragment1();
		f2 =new Fragment2();
		f3 =new Fragment3();
		f4 =new Fragment4();
		f5 =new Fragment5();
		
	}


	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if(event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK){
			if((System.currentTimeMillis() - exitTime) > 2000){
				Toast.makeText(getApplicationContext(), R.string.c5, 1).show();
				exitTime = System.currentTimeMillis();
				}else{
				AppManager.getAppManager().AppExit(getApplicationContext());
					android.os.Process.killProcess(android.os.Process.myPid());
				}
			return true;
			}
		return false;
		}

	
}
