package com.msg.sdk.util;

import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;

import com.msg.sdk.dao.AssetsDatabaseManager;
import com.msg.sdk.dao.DatabaseDAO;

public class DelLocationUtil {

	public static Map<String,String> search(String phoneNumber, Context context,
			DatabaseDAO dao) {

		String prefix, center;
		Map<String, String> map = null;

		if (isZeroStarted(phoneNumber) && getNumLength(phoneNumber) > 2) {
			prefix = getAreaCodePrefix(phoneNumber);
			map = dao.queryAeraCode(prefix);

		} else if (!isZeroStarted(phoneNumber) && getNumLength(phoneNumber) > 6) {
			prefix = getMobilePrefix(phoneNumber);
			center = getCenterNumber(phoneNumber);
			map = dao.queryNumber(prefix, center);
		}
		map.put("del", phoneNumber);
		return map;
	}

	public static void closeDatabase(){
		AssetsDatabaseManager.closeAllDatabase();
	}
	
	/** 得到输入区号中的前三位数字或前四位数字去掉首位为零后的数字。 */
	public static String getAreaCodePrefix(String number) {
		if (number.charAt(1) == '1' || number.charAt(1) == '2')
			return number.substring(1, 3);
		return number.substring(1, 4);
	}

	/** 得到输入手机号码的前三位数字。 */
	public static String getMobilePrefix(String number) {
		return number.substring(0, 3);
	}

	/** 得到输入号码的中间四位号码，用来判断手机号码归属地。 */
	public static String getCenterNumber(String number) {
		return number.substring(3, 7);
	}

	/** 判断号码是否以零开头 */
	@SuppressLint("NewApi")
	public static boolean isZeroStarted(String number) {
		if (number == null || number.isEmpty()) {
			return false;
		}
		return number.charAt(0) == '0';
	}

	/** 得到号码的长度 */
	@SuppressLint("NewApi")
	public static int getNumLength(String number) {
		if (number == null || number.isEmpty())
			return 0;
		return number.length();
	}

}
