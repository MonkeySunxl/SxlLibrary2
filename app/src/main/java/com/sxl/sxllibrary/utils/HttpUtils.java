package com.sxl.sxllibrary.utils;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//Created by My on 2016/10/8.
public class HttpUtils {
    public static boolean isNetWorkConn(Context context){
        ConnectivityManager manager=(ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=manager.getActiveNetworkInfo();
        if(info!=null){
            return info.isConnected();
        }else{
            return false;
        }
    }
    //单例只产生一个httputils对象
    private HttpUtils() {
    }
    public static HttpUtils httpUtils = new HttpUtils();

    public static HttpUtils getHttpUtils() {
        return httpUtils;
    }

    OkHttpClient okHttpClient = new OkHttpClient();
    //直接拿到返回的json
    public String getJson(String url){
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
 try {
            Response response = call.execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //response.body().
        return null;
    }
}
