package com.example.fragment;import java.util.ArrayList;import java.util.List;import org.apache.http.NameValuePair;import org.apache.http.message.BasicNameValuePair;import org.json.JSONArray;import org.json.JSONException;import org.json.JSONObject;import android.content.Intent;import android.graphics.Bitmap;import android.os.Bundle;import android.support.annotation.Nullable;import android.support.v4.app.Fragment;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.AdapterView;import android.widget.BaseAdapter;import android.widget.ImageView;import android.widget.ListView;import android.widget.ProgressBar;import android.widget.TextView;import android.widget.AdapterView.OnItemClickListener;import com.example.q3.R;import com.example.utils.Content;import com.lidroid.xutils.HttpUtils;import com.lidroid.xutils.exception.HttpException;import com.lidroid.xutils.http.RequestParams;import com.lidroid.xutils.http.ResponseInfo;import com.lidroid.xutils.http.callback.RequestCallBack;import com.lidroid.xutils.http.client.HttpRequest;import com.nostra13.universalimageloader.core.DisplayImageOptions;import com.nostra13.universalimageloader.core.ImageLoader;import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;public class Fragment2a extends Fragment{	protected ImageLoader imageLoader = ImageLoader.getInstance();	private View parentView;	private ListView mLv1;	private ProgressBar mPb1;	   private List<Data1> mlist =new ArrayList<Fragment2a.Data1>();	   private Myadapter adapter;	private DisplayImageOptions options;	@Override	@Nullable	public View onCreateView(LayoutInflater inflater,			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {		parentView = inflater.inflate(R.layout.fragment2a, container, false);		mLv1 =(ListView) parentView.findViewById(R.id.mLv1);		mPb1 =(ProgressBar)parentView.findViewById(R.id.mPb1);		mPb1.setVisibility(View.GONE);		mLv1.setOnItemClickListener(new OnItemClickListener() {			@Override			public void onItemClick(AdapterView<?> parent, View view,					int position, long id) { 			}		});		initData();				return parentView;	}	private void initData() {	downsearchdata("0");}public void downsearchdata(String ss){	mPb1.setVisibility(View.VISIBLE);	 RequestParams params = new RequestParams();   List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(10);   nameValuePairs.add(new BasicNameValuePair("userid", 184+""));   params.addBodyParameter(nameValuePairs);   HttpUtils http = new HttpUtils();   http.send(HttpRequest.HttpMethod.POST,  		 "http://pine.i3.com.hk/q3/json/yhfavouritelist.php",           params,           new RequestCallBack<String>() {				private String msg;				@Override				public void onFailure(HttpException arg0, String arg1) {									}				@Override				public void onSuccess(ResponseInfo<String> arg0) {					JSONObject jsonObject;					try {						jsonObject = new JSONObject(arg0.result);						String string_code = jsonObject.getString("code");						 msg = jsonObject.getString("msg");												 int  num_code=Integer.valueOf(string_code);						 if (num_code==1) {							 //���浽����							 mlist.clear();							 JSONArray array = jsonObject.getJSONArray("data");							 for(int i=0;i<array.length();i++){								 Data1 d1 =new Data1();									 d1.UserID=array.getJSONObject(i).getString("UserID");									 d1.ChannelID=array.getJSONObject(i).getString("ChannelID");									 d1.UserName=array.getJSONObject(i).getString("UserName");									 d1.UserNo=array.getJSONObject(i).getString("UserNo");									 d1.Website=array.getJSONObject(i).getString("Website");									 d1.WechatNo=array.getJSONObject(i).getString("WechatNo");									 d1.CompanyPhoto=array.getJSONObject(i).getString("CompanyPhoto");																		mlist.add(d1);							 }							 mPb1.setVisibility(View.GONE);                            initd1a();						 }						 else {									mPb1.setVisibility(View.GONE);						}					} catch (JSONException e) {						mPb1.setVisibility(View.GONE);					}				}				private void initd1a() {						adapter =new Myadapter();					mLv1.setAdapter(adapter);}        });}class Data1 {	public String ChannelID;	public String UserName;	public String UserNo;	public String UserID;	public String Website;	public String WechatNo;	public String CompanyPhoto;}	class Holder{		TextView mTv3,mTv4,mTv5,mTv6,mTv7;		ImageView mIv2,mIv3,mIv1;	}	class  Myadapter extends   BaseAdapter{		@Override		public View getView(int position, View convertView, ViewGroup parent) {			        						Holder holder = null;			if(convertView==null){				convertView = LayoutInflater.from(getActivity())						.inflate(R.layout.item_listview_2, null);				holder = new Holder();								holder.mIv1 =(ImageView)convertView.findViewById(R.id.mIv1);				holder.mIv2 =(ImageView)convertView.findViewById(R.id.mIv2);				holder.mIv3 =(ImageView)convertView.findViewById(R.id.mIv3);				holder.mTv3 =(TextView)convertView.findViewById(R.id.mTv3);				holder.mTv4 =(TextView)convertView.findViewById(R.id.mTv4);				holder.mTv5 =(TextView)convertView.findViewById(R.id.mTv5);				holder.mTv6 =(TextView)convertView.findViewById(R.id.mTv6);				holder.mTv7 =(TextView)convertView.findViewById(R.id.mTv7);								convertView.setTag(holder);			}else{				holder =(Holder)convertView.getTag();			}			holder.mTv4.setText(mlist.get(position).ChannelID);			holder.mTv6.setText(mlist.get(position).UserNo);			if("1".equals(mlist.get(position).UserName)){				holder.mIv2.setImageResource(R.drawable.t4);				holder.mIv3.setBackgroundResource(R.drawable.t3);			}if("2".equals(mlist.get(position).UserName)){				holder.mIv2.setImageResource(R.drawable.t3);				holder.mIv3.setBackgroundResource(R.drawable.t4);			}if("3".equals(mlist.get(position).UserName)){				holder.mIv2.setImageResource(R.drawable.t4);				holder.mIv3.setBackgroundResource(R.drawable.t4);			}												initImageLoaderOptions();			imageLoader.displayImage(Content.ImageUrl+mlist.get(position).CompanyPhoto,holder.mIv1, options);			return convertView;		}		@Override		public int getCount() {			// TODO Auto-generated method stub			return mlist.size();		}		@Override		public Object getItem(int position) {			// TODO Auto-generated method stub			return null;		}		@Override		public long getItemId(int position) {			// TODO Auto-generated method stub			return 0;		}	}	private void initImageLoaderOptions() {		options = new DisplayImageOptions.Builder()				.showImageForEmptyUri(R.drawable.aaa)				.cacheInMemory()				.cacheOnDisc().displayer(new FadeInBitmapDisplayer(2000))				.bitmapConfig(Bitmap.Config.RGB_565).build();	}}