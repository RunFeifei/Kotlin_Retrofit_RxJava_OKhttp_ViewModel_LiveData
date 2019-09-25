package com.uestc.request.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;


import androidx.annotation.NonNull;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.uestc.request.BuildConfig;
import com.uestc.request.RequestContext;
import com.uestc.request.cookie.CookieStore;
import com.uestc.request.host.BaseUrlBinder;
import com.uestc.request.tools.XTrustManager;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ClientBuilder {

    private static final String TAG = "OkHttp-->";

    private static final long CONNECT_TIMEOUT = 10_000;
    private static final long READ_TIMEOUT = 10_000;
    private static final long WRITE_TIMEOUT = 20_000;
    private static final byte[] retrofitInitLock = new byte[0];
    private static OkHttpClient client;
    private static Retrofit retrofit;
    private static HeaderInterceptor headerInterceptor;
    private static ExtendInterceptor networkInterceptor;


    private static void initHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            try {
                final SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, new TrustManager[]{new XTrustManager()}, new java.security.SecureRandom());
                final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
                builder.sslSocketFactory(sslSocketFactory);
                builder.hostnameVerifier((hostname, session) -> true);
                LoggingInterceptor loggingInterceptor = new LoggingInterceptor();
                builder.addNetworkInterceptor(loggingInterceptor);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // 设置缓存大小(只有在服务器返回的Header中的Cache-Control字段maxAge>0才生效 否则需要客户端改写Header see:HeaderInterceptor.class)
        File file = new File(RequestContext.getContext().getCacheDir().getAbsolutePath(), "okhttp");
        builder.cache(new Cache(file, 4 * 1024 * 1024));

        builder.addInterceptor(getHeaderInterceptor());
        builder.cookieJar(CookieStore.getInstance());
        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        builder.readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS);
        builder.writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS);
        builder.addNetworkInterceptor(getNetworkInterceptor());

        client = builder.build();
    }

    public static OkHttpClient getClient() {
        if (client == null) {
            initHttpClient();
        }
        return client;
    }

    /**
     * 初始化,并将client和retrofit联系在一起
     */
    private static void initRetrofit(String baseUrl) {
        Retrofit.Builder builder = new Retrofit.Builder();
        if (!TextUtils.isEmpty(baseUrl) && (baseUrl.startsWith("http://") || baseUrl.startsWith("https://"))) {
            builder.baseUrl(baseUrl);
        }
        builder.addConverterFactory(JacksonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.client(ClientBuilder.getClient());
        retrofit = builder.build();
        Log.d(TAG, "baseUrl change to " + getBaseUrl());
    }

    public static Retrofit getRetrofit() {
        if (client == null) {
            initHttpClient();
        }
        synchronized (retrofitInitLock) {
            if (retrofit == null) {
                initRetrofit(BaseUrlBinder.getBaseUrl());
            }
        }

        return retrofit;
    }

    public static void resetBaseUrl(@NonNull String baseUrl) {
        if (baseUrl.equals(getBaseUrl())) {
            Log.d(TAG, "baseUrl stays in " + baseUrl);
            return;
        }
        if (TextUtils.isEmpty(baseUrl) || !baseUrl.startsWith("http://") && !baseUrl.startsWith("https://")) {
            throw new IllegalStateException("baseUrl is illegal: " + baseUrl);
        }
        CookieStore.getInstance().updateWebCookie(baseUrl);
        rebuildRetrofit(baseUrl);
    }

    /**
     * navite->web 域名切换的同时 需要把cookie同步过去
     * 此时需要重新将CrOkcookieStore重新和OkHttpClient绑定吗??
     */
    private static void rebuildRetrofit(String baseUrl) {
        if (client == null) {
            initHttpClient();
        }
        initRetrofit(baseUrl);
    }

    public static <S> S createService(Class<S> serviceClass) {
        return getRetrofit().create(serviceClass);
    }


    private static HeaderInterceptor getHeaderInterceptor() {
        if (headerInterceptor == null) {
            headerInterceptor = new HeaderInterceptor();
        }
        return headerInterceptor;
    }


    public static String getUserAgent() {
        return getHeaderInterceptor().getUserAgent();
    }

    public static void setUserAgent(String userAgent) {
        getHeaderInterceptor().setUserAgent(userAgent);
    }

    public static void putHeader(String key, String value) {
        if (value == null) {
            return;
        }
        getHeaderInterceptor().putHeader(key, value);
    }

    public static String getBaseUrl() {
        if (retrofit == null || client == null) {
            return null;
        }
        return retrofit.baseUrl().toString();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) RequestContext.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] infos = cm.getAllNetworkInfo();
        for (NetworkInfo info : infos) {
            if (info != null && info.isConnected()) {
                return true;
            }
        }
        return false;
    }


    public static void addNetworkInterceptor(Interceptor interceptor) {
        getNetworkInterceptor().addInterceptor(interceptor);
    }

    public static ExtendInterceptor getNetworkInterceptor() {
        if (networkInterceptor == null) {
            networkInterceptor = new ExtendInterceptor();
        }
        return networkInterceptor;
    }



}