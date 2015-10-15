package com.example.q3;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class LoginActivity extends BaseActivity {

	private EditText mEt1;
	private EditText mEt2;
	private Button mBt1;
	private Button mBt2;
	private ProgressBar progressBar_sale;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);

		initView();
		
		
}

	private void initView() {
		progressBar_sale =(ProgressBar)this.findViewById(R.id.progressBar_sale);
		mEt1 =(EditText)this.findViewById(R.id.mEt1);
		mEt2 =(EditText)this.findViewById(R.id.mEt2);
		mBt1 =(Button)this.findViewById(R.id.mBt1);
		mBt2 =(Button)this.findViewById(R.id.mBt2);
		mBt1.setOnClickListener(listener);
		mBt2.setOnClickListener(listener);
		
		
	}
	
	private void initData() {
		downloadsearch("0");
	}
	public void downloadsearch(String area11){
		progressBar_sale.setVisibility(View.VISIBLE);
		 RequestParams params = new RequestParams();
	   List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(10);
	   nameValuePairs.add(new BasicNameValuePair("account", mEt1.getEditableText().toString()));
	   nameValuePairs.add(new BasicNameValuePair("password", mEt2.getEditableText().toString()));
	   
	   params.addBodyParameter(nameValuePairs);
	   HttpUtils http = new HttpUtils();
	   http.send(HttpRequest.HttpMethod.POST,
	  		 "http://pine.i3.com.hk/q3/json/sjlogoin.php",
	           params,
	           new RequestCallBack<String>() {

					private String msg;
					private String UserGender;
					private String UserBirthday;

					@Override
					public void onFailure(HttpException arg0, String arg1) {

					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						JSONObject jsonObject;
						try {
							jsonObject = new JSONObject(arg0.result);
							String string_code = jsonObject.getString("code");
							msg =jsonObject.getString("msg");
							 int  num_code=Integer.valueOf(string_code);
							 if (num_code==1) {
								 //保存到本地
								 JSONObject array = jsonObject.getJSONObject("data");
									progressBar_sale.setVisibility(View.GONE);
									String UserID = array.getString("UserID");
									String UserType = array.getString("UserType");
									String ChannelID = array.getString("ChannelID");
									SharedPreferences mySharedPreferences= getSharedPreferences("USERQ", Activity.MODE_PRIVATE); 
											SharedPreferences.Editor editor = mySharedPreferences.edit(); 
											editor.putString("UserID", UserID); 
											editor.putString("UserType",UserType); 
											editor.putString("ChannelID",ChannelID); 
											editor.commit(); 
												startActivity(new Intent(getApplicationContext(), MainActivity.class));
			                                AppManager.getAppManager().finishActivity();
							 }
							 else {
								 Toast.makeText(getApplicationContext(), msg, 0).show();
										progressBar_sale.setVisibility(View.GONE);
							}
						} catch (JSONException e) {
							progressBar_sale.setVisibility(View.GONE);
							 Toast.makeText(getApplicationContext(), msg, 0).show();
						}
					}
	   });
	}
	OnClickListener listener =new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.mBt1:
				if(TextUtils.isEmpty(mEt1.getEditableText().toString())){
					Toast.makeText(getApplicationContext(), R.string.c31, 0).show();
				}else if(TextUtils.isEmpty(mEt2.getEditableText().toString())){
					Toast.makeText(getApplicationContext(), R.string.c32, 0).show();
				}else{
					initData();
				}
				break;
			case R.id.mBt2:
				startActivity(new Intent(getApplicationContext(), RegActivity.class));
				break;

			default:
				break;
			}
		}
	};
}