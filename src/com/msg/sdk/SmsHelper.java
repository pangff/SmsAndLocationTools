package com.msg.sdk;

import com.phone.sdk.SearchNumber;
import com.phone.util.AssetsDatabaseManager;
import com.phone.util.DatabaseDAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.TelephonyManager;
import android.util.Log;

public class SmsHelper {
	final String TAG = SmsHelper.class.getName();
	
	private static SmsHelper smsUtil;

	private InterceptFilter interceptFilter;

	private InterceptListener interceptCallback;

	private SmsSendListener smsSendListener;

	protected static SmsHelper getInstance() {
		if (smsUtil == null) {
			smsUtil = new SmsHelper();
		}
		return smsUtil;
	}

	public InterceptFilter getInterceptFilter() {
		return interceptFilter;
	}

	public void setInterceptFilter(InterceptFilter interceptFilter) {
		this.interceptFilter = interceptFilter;
	}

	/**
	 * 发短信
	 * @param context
	 * @param phoneNum
	 * @param text
	 */
	public void sendSms(Context context, String phoneNum, String text) {
		SmsSender.sendSms(context, phoneNum, text);
	}

	/**
	 * 获取运营商编码
	 * @param context
	 * @return
	 */
	public String getOperator(Context context){
		TelephonyManager telManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);   
	      String operator = telManager.getSimOperator();  
	      if(operator!=null){
		    	  if(operator.equals("46000") || operator.equals("46002")|| operator.equals("46007")){  
		    		  Log.e(TAG, "运营商:中国移动");
		      }else if(operator.equals("46001")){  
		    	  	Log.e(TAG, "运营商:中国联通");
		      }else if(operator.equals("46003")){  
		    	  	Log.e(TAG, "运营商:中国电信");
		      }
		 }
	      return operator;
	}
	
	/**
	 *查询号码归属地
	 */
	public void searchNumber(Context context,String phoneNumber){
		AssetsDatabaseManager.initManager(context.getApplicationContext());
		AssetsDatabaseManager mg = AssetsDatabaseManager
				.getAssetsDatabaseManager();
		SQLiteDatabase	sqliteDB = mg.getDatabase("number_location.zip");
		DatabaseDAO dao = new DatabaseDAO(sqliteDB);
		
		SearchNumber.search(phoneNumber,context,dao);
	}
	
	/**
	 * 关闭数据库
	 * @return
	 */
	public void closeDatabase(){
		SearchNumber.closeDatabase();
	}
	

	public InterceptListener getInterceptCallback() {
		return interceptCallback;
	}

	public void setInterceptCallback(InterceptListener interceptCallback) {
		this.interceptCallback = interceptCallback;
	}

	public SmsSendListener getSmsSendListener() {
		return smsSendListener;
	}

	public void setSmsSendListener(SmsSendListener smsSendListener) {
		this.smsSendListener = smsSendListener;
	}

	private SmsHelper() {

	}

}
