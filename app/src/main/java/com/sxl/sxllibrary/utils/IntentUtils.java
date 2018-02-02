package com.sxl.sxllibrary.utils;

import android.Manifest;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;

/**
 * @ProjectName wmt
 * @ClassDescribe 跳转相关工具类
 * @Copyright 未名天
 */

public class IntentUtils {
    private IntentUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated, and its methods must be called directly.");
    }

    /**
     * 跳转Activity
     *
     * @param context 当前上下文
     * @param cls     目标Activity的字节码
     */
    public static void startActivity(Context context, Class<?> cls) {
        startActivity(context, cls, -1);
    }

    /**
     * 跳转Activity
     *
     * @param context 当前上下文
     * @param cls     目标Activity的字节码
     * @param flags   需要附加的flag
     */
    public static void startActivity(Context context, Class<?> cls, int flags) {
        Intent intent = new Intent(context, cls);
        if (flags > -1) {
            intent.setFlags(flags);
        }
        context.startActivity(intent);
    }

    /**
     * 跳转到系统的编辑联系人Activity
     *
     * @param context 当前上下文
     * @param id      联系人ID
     */
    public static void editContact(Context context, long id) {
        Intent intent = new Intent(Intent.ACTION_EDIT);
        Uri personUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id);
        intent.setData(personUri);
        context.startActivity(intent);
    }

    /**
     * 跳转到系统的查看联系人Activity
     *
     * @param context 当前上下文
     * @param id      联系人ID
     */
    public static void viewContact(Context context, long id) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri personUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id);
        intent.setData(personUri);
        context.startActivity(intent);
    }

    /**
     * 拨打指定号码
     *
     * @param context 当前上下文
     * @param phone   号码
     * @return 如果没有权限则返回false
     */
    public static boolean callPhone(Context context, String phone) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Uri data = Uri.parse("tel:" + phone);
            Intent intent = new Intent(Intent.ACTION_CALL, data);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 跳转到联系人的拨号界面
     *
     * @param context 当前上下文
     * @param phone   号码
     */
    public static void startDial(Context context, String phone) {
        Uri data = Uri.parse("tel:" + phone);
        Intent intent = new Intent(Intent.ACTION_DIAL, data);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 跳转到指定url
     *
     * @param context 当前上下文
     * @param url     网址
     */
    public static void openUrl(Context context, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
