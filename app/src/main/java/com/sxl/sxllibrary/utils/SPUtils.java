package com.sxl.sxllibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;


/**
 * @ProjectName wmt
 * @ClassDescribe sharePreferences 工具类
 */

public class SPUtils {
    /**
     * 使用单例模式来创建共享对象的变量
     */
    private static SharedPreferences mSharedPreferences;
    /**
     * 自定义创建共享对象的变量
     */
    private static SharedPreferences mCustomSP;
    /**
     * 当前选择的对象
     */
    private static SharedPreferences currentSP;
    /**
     * 共享文件的名称
     */
    private static final String SP_NAME = "config";

    private SPUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 初始化SharedPreferences
     */
    private static void init(Context context, String spName) {
        if (SP_NAME.equals(spName)) {
            /** 默认的对象 */
            if (mSharedPreferences == null) {
                mSharedPreferences = context.getSharedPreferences(spName,
                        Context.MODE_PRIVATE);// 这里额外修改过，之前是MODE_MULTI_PROCESS
            }
        } else {
            if (!(mCustomSP != null && spName.equals(mCustomSP.getString(spName, "")))) {
                /** 为空或者是不同对象 */
                mCustomSP = context.getSharedPreferences(spName,
                        Context.MODE_PRIVATE);// 这里额外修改过，之前是MODE_MULTI_PROCESS
                mCustomSP.edit().putString(spName, spName).commit();
            }
        }
    }

    /**
     * 添加二级Tag，该方法需要在打印日志的类中获得类对象，比较方便的是在类的父类中获取即可
     */
    public static synchronized SPTagName spTagName(String spName) {
        SPTagName mTagName = new SPTagName();
        mTagName.setSpName(spName);
        return mTagName;
    }

    /**
     * 使用默认文件添加键的内容
     */
    public static void putData(Context context, String key, Object data) {
        putData(context, SP_NAME, key, data);
    }

    /**
     * 使用默认文件获得键的内容
     */
    public static Object getData(Context context, String key, Object defValue) {
        return getData(context, SP_NAME, key, defValue);
    }

    /**
     * 自定义文件添加键的内容
     */
    public static void putData(Context context, String spName, String key, Object data) {
        String type = data.getClass().getSimpleName();
        init(context, spName);
        if (SP_NAME.equals(spName)) {
            currentSP = mSharedPreferences;
        } else {
            currentSP = mCustomSP;
        }
        if ("Integer".equals(type)) {
            currentSP.edit().putInt(key, (Integer) data).commit();
        } else if ("Boolean".equals(type)) {
            currentSP.edit().putBoolean(key, (Boolean) data).commit();
        } else if ("String".equals(type)) {
            currentSP.edit().putString(key, (String) data).commit();
//            currentSP.edit().putString(key, CryptUtil.encrypt((String) data)).commit();
        } else if ("Float".equals(type)) {
            currentSP.edit().putFloat(key, (Float) data).commit();
        } else if ("Long".equals(type)) {
            currentSP.edit().putLong(key, (Long) data).commit();
        }
        currentSP = null;
    }

    /**
     * 自定义文件获得键的内容
     */
    public static Object getData(Context context, String spName, String key, Object defValue) {
        String type = defValue.getClass().getSimpleName();
        init(context, spName);
        if (SP_NAME.equals(spName)) {
            currentSP = mSharedPreferences;
        } else {
            currentSP = mCustomSP;
        }
        if ("Integer".equals(type)) {
            return currentSP.getInt(key, (Integer) defValue);
        } else if ("Boolean".equals(type)) {
            return currentSP.getBoolean(key, (Boolean) defValue);
        } else if ("String".equals(type)) {
//            ic_black_back CryptUtil.decrypt(currentSP.getString(key, (String) defValue));
            return currentSP.getString(key, (String) defValue);
        } else if ("Float".equals(type)) {
            return currentSP.getFloat(key, (Float) defValue);
        } else if ("Long".equals(type)) {
            return currentSP.getLong(key, (Long) defValue);
        }
        currentSP = null;
        return null;
    }


    /**
     * 使用默认文件删除某个键的内容
     */
    public static void remove(Context context, String key) {
        remove(context, SP_NAME, key);
    }

    /**
     * 使用默认文件清空所有的键值对
     */
    public static void clearAll(Context context) {
        clearAll(context, SP_NAME);
    }

    /**
     * 自定义文件删除某个键的内容
     */
    public static void remove(Context context, String spName, String key) {
        init(context, spName);
        if (SP_NAME.equals(spName)) {
            currentSP = mSharedPreferences;
        } else {
            currentSP = mCustomSP;
        }
        currentSP.edit().remove(key).commit();
        currentSP = null;
    }

    /**
     * 自定义文件清空所有的键值对
     */
    public static void clearAll(Context context, String spName) {
        init(context, spName);
        if (SP_NAME.equals(spName)) {
            currentSP = mSharedPreferences;
        } else {
            currentSP = mCustomSP;
        }
        currentSP.edit().clear().commit();
        currentSP = null;
    }
 
    public static void setLoginStatus(Context context, boolean loginStatus) {
        SPUtils.putData(context, "UserInfo", "loginstatus", loginStatus);
    }

    public static boolean getLoginStatus(Context context) {
        return (boolean) SPUtils.getData(context, "UserInfo", "loginstatus", false);
    }
    public static void setUid(Context context, String uid) {
        SPUtils.putData(context, "UserInfo", "uid", uid);
    }
    public static String getUid(Context context) {
        return (String) SPUtils.getData(context, "UserInfo", "uid", "");
    }
    public static void setToken(Context context, String token) {
        SPUtils.putData(context, "UserInfo", "token", token);
    }
    public static String getToken(Context context) {
        return (String) SPUtils.getData(context, "UserInfo", "token", "");
    }
    /**
     * ========================================
     * <p>
     * 版 权：dou361.com 版权所有 （C） 2015
     * <p>
     * 作 者：陈冠明
     * <p>
     * 个人网站：http://www.dou361.com
     * <p>
     * 版 本：1.0
     * <p>
     * 创建日期：2015/11/11
     * <p>
     * 描 述：共享文件的SPUtils的包装类，打印信息中包含其对应的类名称
     * <p>
     * <p>
     * 修订历史：
     * <p>
     * ========================================
     */
    public static class SPTagName implements Serializable {

        /**
         *
         */
        private final long serialVersionUID = -765691622218959750L;

        /**
         * 自定义共享文件名，相当于新开一个
         */
        private String spName;

        public void setSpName(String spName) {
            this.spName = spName;
        }

        /**
         * 自定义文件添加键的内容
         */
        public void putData(Context context, String key, Object value) {
            SPUtils.putData(context, spName, key, value);
        }

        /**
         * 自定义文件获得键的内容
         */
        public Object getData(Context context, String key,
                              Object defValue) {
            return SPUtils.getData(context, spName, key, defValue);
        }

        /**
         * 自定义文件删除某个键的内容
         */
        public void remove(Context context, String key) {
            SPUtils.remove(context, spName, key);
        }

        /**
         * 自定义文件清空所有的键值对
         */
        public void clearAll(Context context) {
            SPUtils.clearAll(context, spName);
        }

    }
}
