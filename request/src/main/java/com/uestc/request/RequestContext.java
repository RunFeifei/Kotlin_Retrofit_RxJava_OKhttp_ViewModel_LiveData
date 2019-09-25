package com.uestc.request;

import android.content.Context;

/**
 * Created by PengFeifei on 2018/10/27.
 */
public class RequestContext {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        RequestContext.context = context;
    }
}
