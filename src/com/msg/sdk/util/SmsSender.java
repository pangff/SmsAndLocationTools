package com.msg.sdk.util;

import java.util.List;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

public class SmsSender {

	public static void sendSms(Context context,String del,String text){
		SmsManager msmMessage = SmsManager.getDefault();
		Intent sendIntent = new Intent("SENT_SMS_ACTION");
		sendIntent.putExtra("del", del);
		sendIntent.putExtra("sms", text);
		
		Intent deliverIntent = new Intent("DELIVERED_SMS_ACTION");
		deliverIntent.putExtra("del", del);
		deliverIntent.putExtra("sms", text);
		PendingIntent pendingSendIntent = PendingIntent.getBroadcast(context, 0, sendIntent, 0);
		PendingIntent pendingDeliverIntent = PendingIntent.getBroadcast(context, 0, deliverIntent, 0);
		if (text.length() >= 70) {
			List<String> ms = msmMessage.divideMessage(text);
			for (String message : ms) {
				msmMessage.sendTextMessage(del, null, message,pendingSendIntent, pendingDeliverIntent);
			}
		} else {
			msmMessage.sendTextMessage(del, null, text,pendingSendIntent, pendingDeliverIntent);
		}
	}
}
