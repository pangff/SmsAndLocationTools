package com.msg.sdk;

import android.content.Context;
import android.content.Intent;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SdkUtil {
	protected static RequestQueue requestQueue;
	protected static SmsHelper smsHelper;
	public static void init(Context context){
		
		/** 启动位置服务 **/
		Intent startIntent = new Intent(context, LocationService.class);  
		context.startService(startIntent);  
		
		/** 开启网络队列 **/
		requestQueue = Volley.newRequestQueue(context);
		
		/**短信工具**/
		smsHelper = SmsHelper.getInstance();
	}
	
	public static SmsHelper getSmsHelper(){
		return smsHelper;
	}
	
	public static RequestQueue getRequestQueue(){
		return requestQueue;
	}
}
