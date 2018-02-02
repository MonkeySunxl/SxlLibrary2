package com.sxl.sxllibrary.update;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * @ProjectName pkusky
 * @ClassDescribe
 * @Author
 * @Date 2017/7/28 11:03
 * @Copyright 未名天
 *
 * 添加依赖
 * //多线程下载
        compile 'cn.woblog.android:downloader:1.0.1'
 */

public class CheckVersion {
    private Context context;
    public static String newVersion;//新版本号
    public static String changeLog;//更新日志
    public static String updateUrl;//更新apk下载网址

    public CheckVersion(Context context, String newVersion, String changeLog, String updateUrl) {
        this.context=context;
        this.newVersion=newVersion;
        this.updateUrl = updateUrl;
        this.changeLog=changeLog;
        if (!getVersion(context).equals(newVersion)){
                //提示更新
                showUpdate();
        }
    }


    /**
     * 显示更新
     */
    private void showUpdate() {
        Intent intent = new Intent();
        intent.setClass(context, UpdateDialog.class);
        context.startActivity(intent);
    }

    /**
     * 获取当前应用的版本号
     * @param mContext
     */
    public  String getVersion(Context mContext) {
        PackageManager manager = mContext.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
