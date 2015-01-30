package com.msg.sdk.inerface;

import android.telephony.SmsMessage;


public interface InterceptFilter {
	
	public SmsMessage[] doFilter(SmsMessage[] smsArray);
}
