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
		// 初始化定位，只采用网络定位
		
		mLocationManagerProxy = LocationManagerProxy.getInstance(this);
		mLocationManagerProxy.setGpsEnable(false);
		 mLocationManagerProxy.requestLocationData(LocationProviderProxy.AMapNetwork, -1, 15, SplishActivity.this);

		// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
		// 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用removeUpdates()方法来取消定位请求
		// 在定位结束后，在合适的生命周期调用destroy()方法
		// 其中如果间隔时间为-1，则定位只定一次,
		// 在单次定位情况下，定位无论成功与否，都无需调用removeUpdates()方法移除请求，定位sdk内部会移除
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
