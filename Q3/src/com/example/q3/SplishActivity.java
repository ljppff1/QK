package com.example.q3;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Window;

public class SplishActivity extends BaseActivity implements AMapLocationListener {

	private LocationManagerProxy mLocationManagerProxy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splish);
		SharedPreferences mySharedPreferences= getSharedPreferences("USERQ", Activity.MODE_PRIVATE); 
		SharedPreferences.Editor editor = mySharedPreferences.edit(); 
		editor.putString("zz", "lj"); 
		editor.commit(); 
		
		
		
		
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				SharedPreferences mySharedPreferences= getSharedPreferences("USERQ", Activity.MODE_PRIVATE); 
				String wechatNo = mySharedPreferences.getString("wechatNo", ""); 
		         if(TextUtils.isEmpty(wechatNo)){
				startActivity(new Intent(getApplicationContext(), LoginActivity.class));
				finish();
		         }else{
						startActivity(new Intent(getApplicationContext(), MainActivity.class));
						finish();
		         }
			}
		}, 2000);
	}
	private void init() {
		// ��ʼ����λ��ֻ�������綨λ
		
		mLocationManagerProxy = LocationManagerProxy.getInstance(this);
		mLocationManagerProxy.setGpsEnable(false);
		 mLocationManagerProxy.requestLocationData(LocationProviderProxy.AMapNetwork, -1, 15, SplishActivity.this);

		// �˷���Ϊÿ���̶�ʱ��ᷢ��һ�ζ�λ����Ϊ�˼��ٵ������Ļ������������ģ�
		// ע�����ú��ʵĶ�λʱ��ļ������С���֧��Ϊ2000ms���������ں���ʱ�����removeUpdates()������ȡ����λ����
		// �ڶ�λ�������ں��ʵ��������ڵ���destroy()����
		// ����������ʱ��Ϊ-1����λֻ��һ��,
		// �ڵ��ζ�λ����£���λ���۳ɹ���񣬶��������removeUpdates()�����Ƴ����󣬶�λsdk�ڲ����Ƴ�
		// mLocationManagerProxy.requestLocationData(LocationProviderProxy.AMapNetwork, -1, 15, this);
		
	}

private Handler handler =new Handler();

@Override
public void onLocationChanged(Location location) {
	// TODO Auto-generated method stub
	
}
@Override
public void onStatusChanged(String provider, int status, Bundle extras) {
	// TODO Auto-generated method stub
	
}
@Override
public void onProviderEnabled(String provider) {
	// TODO Auto-generated method stub
	
}
@Override
public void onProviderDisabled(String provider) {
	// TODO Auto-generated method stub
	
}
@Override
public void onLocationChanged(AMapLocation arg0) {
	SharedPreferences mySharedPreferences= getSharedPreferences("USERQ", Activity.MODE_PRIVATE); 
	SharedPreferences.Editor editor = mySharedPreferences.edit(); 
	editor.putString("Latitude", arg0.getLatitude()+""); 
	editor.putString("Longitude",arg0.getLongitude()+""); 
	editor.putString("Address",arg0.getAddress()+""); 
	editor.commit(); 
}
	
}
