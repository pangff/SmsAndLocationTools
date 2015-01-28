package com.pangff.aboutmsg;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.msg.sdk.SdkUtil;

import android.app.Application;

public class BaseApplication extends Application{

	@Override
	public void onCreate() {
		super.onCreate();
		SdkUtil.init(this);
	}
		
}
