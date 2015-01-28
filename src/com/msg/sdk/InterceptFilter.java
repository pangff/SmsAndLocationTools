package com.msg.sdk;

import android.telephony.SmsMessage;


public interface InterceptFilter {
	
	public SmsMessage[] doFilter(SmsMessage[] smsArray);
}
