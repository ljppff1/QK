/*******************************************************************************
 * Copyright 2011-2013 Sergey Tarasevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.example.utils;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class UILApplication1 extends Application {
	private ArrayList<Activity> activityList;
	private String mHotDetail_ID;
	private String mHotDetail_Name;
	
	
	public static Context applicationContext;

	@SuppressWarnings("unused")
	@Override
	public void onCreate() {
		super.onCreate();
		
        applicationContext = this;
        activityList = new ArrayList<Activity>();
		initImageLoader(getApplicationContext());
	}
	public void addAct(Activity activity) {
		activityList.add(activity);
          }
    public void removeAct(Activity activity) {
    	activityList.remove(activity);
	}

 

    public void exit() {
		for (int i = 0; i < activityList.size(); i++) {
			activityList.get(i).finish();
		}
	}
	public static void initImageLoader(Context context) {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
		.threadPriority(Thread.NORM_PRIORITY - 2)
		.denyCacheImageMultipleSizesInMemory()
		.discCacheFileNameGenerator(new Md5FileNameGenerator())
		.tasksProcessingOrder(QueueProcessingType.LIFO)
		.build();
ImageLoader.getInstance().init(config);

	}
	public String getHotDetail_ID() {
		// TODO Auto-generated method stub
		return   mHotDetail_ID;
	}
	public void setHotDetail_ID(String string_ID) {
		// TODO Auto-generated method stub
		this.mHotDetail_ID=string_ID;
	}
	public void setHotDetail_Name(String string_Name) {
		// TODO Auto-generated method stub
		this.mHotDetail_Name=string_Name;
	}
	public String getHotDetail_Name() {
		// TODO Auto-generated method stub
		return   mHotDetail_Name;
	}
	
	
}