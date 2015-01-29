package com.pangff.aboutmsg;

import com.msg.sdk.SdkUtil;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	//	SdkUtil.init(this);
	//	SdkUtil.getSmsHelper().searchNumber(this, "15350715961");
	}
}
