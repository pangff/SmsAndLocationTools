package com.msg.sdk.inerface;



public interface SmsSendListener {
	
	public void onSendSuccess(String del,String sms);
	
	public void onDeliverSuccess(String del,String sms);
}
