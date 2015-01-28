package com.msg.sdk;

import android.telephony.SmsMessage;


public interface InterceptListener {
	
	public void onItercepteFinished(SmsMessage[] smsArray);
}
