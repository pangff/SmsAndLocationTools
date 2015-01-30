package com.pangff.aboutmsg;

import android.app.Application;

import com.msg.sdk.SdkUtil;

public class BaseApplication extends Application{

	@Override
	public void onCreate() {
		super.onCreate();
		SdkUtil.init(this);
	}
		
}
