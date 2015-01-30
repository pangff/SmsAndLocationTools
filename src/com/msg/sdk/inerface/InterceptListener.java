package com.msg.sdk.inerface;

import android.telephony.SmsMessage;


public interface InterceptListener {
	
	public void onItercepteFinished(SmsMessage[] smsArray);
}
