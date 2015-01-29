package com.phone.util;

import java.util.Map;



import com.pangff.aboutmsg.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class DialogUtils {
	private static TextView tv,tv_result;
	
	/**显示对话框。*/
	public static  void showDialog(Context context,View view){
		new AlertDialog.Builder(context)
		.setIcon(android.R.drawable.ic_dialog_info)
		.setTitle(context.getResources().getString(R.string.title_search_result))
		.setView(view)
		.setNegativeButton(context.getResources().getString(R.string.button_close), new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		}).create().show();
	}
	
	/**显示手机号码查询结果的对话框。*/
	public static void showNumberDialog(Context context, View view, Map<String,String> map, String number){
		view = findViewId(context);
		if (number.isEmpty()){
			tv.setVisibility(View.GONE);
			tv_result.setText(context.getResources().getString(R.string.title_input_phone_nbumber));
		}
		else if (map == null)
			setNotFoundText(context, number);
		else{
			tv.setText(appendStr(number));
			String province = map.get("province");
			String city = map.get("city");
			if (province == null || city == null || province.isEmpty() || city.isEmpty()){
				tv_result.setText(context.getResources().getString(R.string.title_search_result_not_found));
			}else if ( province.equals(city))
				tv_result.setText(province);
			else
				tv_result.setText(province + "  " + city);
		}
		showDialog(context, view);
	}

	private static View findViewId(Context context) {
		View view = LayoutInflater.from(context).inflate(R.layout.alert_result_dialog, null);	
	    tv = (TextView) view.findViewById(R.id.string_search_query);
		tv_result = (TextView) view.findViewById(R.id.string_search_result);
		return view;
	}

	//显示city查询结果的对话框。
	public static void showCityDialog(Context context, View view, Map<String,String> map, String city){
		view = findViewId(context);
		if (city.isEmpty()){
			tv.setVisibility(View.GONE);
			tv_result.setText(context.getResources().getString(R.string.title_input_city_name));
		}
		else if (map == null)
			setNotFoundText(context, city);
		else {
			tv.setText(appendStr(city));
			String anum = map.get("rownumber");
			if (anum == null || anum.isEmpty())//前者为真时，后者不会执行
				tv_result.setText(context.getResources().getString(R.string.title_search_result_not_found));
			else{
				tv_result.setText("0" + anum);
			}
		}
		showDialog(context, view);
	}
	
	//显示city查询结果的对话框。
	public static void showCountryDialog(Context context, View view, Map<String,String> map, String country){
		view = findViewId(context);
		if (country.isEmpty()){
			tv.setVisibility(View.GONE);
			tv_result.setText(context.getResources().getString(R.string.title_input_country_name));
		}
		else if (map == null)
			setNotFoundText(context, country);
		else {
			tv.setText(appendStr(country));
			String anum = map.get("rownumber");
			if (anum == null || anum.isEmpty())
				tv_result.setText(context.getResources().getString(R.string.title_search_result_not_found));
			else{
				tv_result.setText("00" + anum);
			}
		}
		showDialog(context, view);
	}

	/**查询数据库中无匹配记录。*/
	private static void setNotFoundText(Context context, String str) {
		tv.setText(appendStr(str));
		tv_result.setText(context.getResources().getString(R.string.title_search_result_not_found));
	}

	private static String appendStr(String str) {
		StringBuilder sb = new StringBuilder();
		return sb.append("[").append(str).append("]").toString();
	}
}








