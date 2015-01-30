package com.msg.sdk;

import android.content.Context;
import android.content.Intent;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.msg.sdk.helper.SmsHelper;
import com.msg.sdk.service.LocationService;

public class SdkUtil {
	protected static RequestQueue requestQueue;
	public static SmsHelper smsHelper;
	public static void init(Context context){
		
		/** 启动位置服务 **/
		Intent startIntent = new Intent(context, LocationService.class);  
		context.startService(startIntent);  
		
		/** 开启网络队列 **/
		requestQueue = Volley.newRequestQueue(context);
		
		/**短信工具**/
		smsHelper = SmsHelper.getInstance();
	}
	
	
	public static void realease(){
		
	}
	
}
