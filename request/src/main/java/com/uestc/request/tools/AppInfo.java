package com.uestc.request.tools;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.uestc.request.RequestContext;


public class AppInfo {

    private static final String META_CLIENT_TYPE = "client_type";
    private static final String META_CHANNEL_NAME = "channel_name";
    private static final String META_NULL = "";


    /*******************************package Info*******************************/

    public static PackageInfo getPackageInfo(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            Log.e("AppInfo", e.getMessage());
        }
        return null;
    }

    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }


    /*******************************meta Info*******************************/

    public static Bundle getMetaData() {
        try {
            return RequestContext.getContext().getPackageManager().getApplicationInfo(RequestContext.getContext().getPackageName(), PackageManager
                    .GET_META_DATA)
                    .metaData;
        } catch (NameNotFoundException e) {
            Log.e("AppInfo", e.getMessage());

        }
        return null;
    }

    public static String getMetaInfo(String key) {
        if (getMetaData() == null) {
            return META_NULL;
        }
        String metaInfo = getMetaData().getString(key);
        if (metaInfo == null) {
            metaInfo = META_NULL;
        }
        return metaInfo;
    }

    public static String getClientType() {
        if (TextUtils.isEmpty(getMetaInfo(META_CLIENT_TYPE))) {
            return "Android";
        }
        return getMetaInfo(META_CLIENT_TYPE);
    }

    public static String getChannelName() {
        return getMetaInfo(META_CHANNEL_NAME);
    }

    /*******************************Build Info*******************************/

    public static String getDeviceName() {
        return Build.MODEL;
    }

    public static String getManufacture() {
        return Build.MANUFACTURER;
    }

    public static String getSystemVersion() {
        return "" + Build.VERSION.SDK_INT;
    }


}
