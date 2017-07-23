package com.gugutian.wifiadb.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class NetWorkUtils {

	public static boolean checkEnable(Context paramContext) {
		NetworkInfo localNetworkInfo = ((ConnectivityManager) paramContext.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
		return (localNetworkInfo != null) && (localNetworkInfo.isAvailable());
	}

	private static String int2ip(int ipInt) {
		return String.valueOf(ipInt & 0xFF) + "." +
				((ipInt >> 8) & 0xFF) + "." +
				((ipInt >> 16) & 0xFF) + "." +
				((ipInt >> 24) & 0xFF);
}
	public static String getLocalIpAddress(Context context) {
		try {
			WifiManager wifiManager = (WifiManager)context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
			WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			int i = wifiInfo.getIpAddress(); 
			return int2ip(i);
		} catch (Exception ex) {
			return " 获取IP出错鸟!!!!请保证是WIFI,或者请重新打开网络!\n"+ex.getMessage();
		}
	}
}