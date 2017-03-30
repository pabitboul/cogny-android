package com.medicoom.cogny.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

public class Utils {
    public static boolean isNullOrEmptyOrWhitespace(String s) {
        return isNullOrEmpty(s) || isWhitespace(s);
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.length() == 0;
    }

    static boolean isWhitespace(String s) {
        int length = s.length();
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(s.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static boolean isWifiConnected(@NonNull Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressWarnings("deprecation") NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return networkInfo.isConnected();
    }

    public static boolean isNetworkConnected(@NonNull Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return ((activeNetwork != null) &&
                activeNetwork.isConnectedOrConnecting());
    }

    public static boolean hasWifiConnection(Context context) {
        return isWifiConnected(context);
    }
}