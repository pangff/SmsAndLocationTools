package com.msg.sdk.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.msg.sdk.helper.SmsHelper;

public class SmsReceiver extends BroadcastReceiver{
	public static final String mACTION = "android.provider.Telephony.SMS_RECEIVED";
	
	public static final String DELIVERED_SMS_ACTION = "DELIVERED_SMS_ACTION";
	public static final String SENT_SMS_ACTION = "SENT_SMS_ACTION";

	@Override
	public void onReceive(Context context, Intent intent) {

		if (intent.getAction().equals(mACTION)) {
			Bundle bundle = intent.getExtras();

			if (bundle != null) {
				/* pdus为 android内置短信参数 identifier, 通过bundle.get("")返回一包含pdus的对象 */
				Object[] obt = (Object[]) bundle.get("pdus");
				SmsMessage[] messages = new SmsMessage[obt.length];
				for (int i = 0; i < obt.length; i++) {
					messages[i] = SmsMessage.createFromPdu((byte[]) obt[i]);
				}
				SmsMessage[] filterSms = null;
				if(SmsHelper.getInstance().getInterceptFilter()!=null){
					filterSms = SmsHelper.getInstance().getInterceptFilter().doFilter(messages);
					if(filterSms!=null && filterSms.length!=0){
						abortBroadcast();
					}
				}
				if(SmsHelper.getInstance().getInterceptCallback()!=null){
					SmsHelper.getInstance().getInterceptCallback().onItercepteFinished(messages);
				}
			}

		}
		if(intent.getAction().equals(SENT_SMS_ACTION)){
			if(SmsHelper.getInstance().getSmsSendListener()!=null){
				String sms  = intent.getStringExtra("sms");
				String del  = intent.getStringExtra("del");
				SmsHelper.getInstance().getSmsSendListener().onSendSuccess(del,sms);
			}
		}
		if(intent.getAction().equals(DELIVERED_SMS_ACTION)){
			if(SmsHelper.getInstance().getSmsSendListener()!=null){
				String sms  = intent.getStringExtra("sms");
				String del  = intent.getStringExtra("del");
				SmsHelper.getInstance().getSmsSendListener().onDeliverSuccess(del,sms);
			}
		}

	}
}
