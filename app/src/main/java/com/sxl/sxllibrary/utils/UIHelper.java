package com.sxl.sxllibrary.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @ProjectName wmt
 * @ClassDescribe 应用程序UI工具包：封装UI相关一些操作
 */

public class UIHelper {

    /**
     * 弹出Toast消息
     *
     * @param msg
     */
    public static void ToastMessage(Context cont, String msg) {
        Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
    }

    public static void ToastMessage(Context cont, int msg) {
        Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
    }

    public static void ToastMessage(Context cont, String msg, int time) {
        Toast.makeText(cont, msg, time).show();
    }

    /**
     * 显示在中间
     * @param cont
     * @param msg
     */
    public static void ToastMessageCenter(Context cont, String msg,int MipmapID) {
        Toast toast = Toast.makeText(cont, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout toastView = (LinearLayout) toast.getView();
        ImageView imageCodeProject = new ImageView(cont);
        imageCodeProject.setImageResource(MipmapID);
        toastView.addView(imageCodeProject, 0);
        toast.show();
    }
    /**
     * 返回byte的数据大小对应的文本
     *
     * @param size
     * @return
     */
    public static String getDataSize(long size) {
        if (size < 0) {
            size = 0;
        }
        DecimalFormat formater = new DecimalFormat("####.00");
        if (size < 1024) {
        //    ic_black_back size + "bytes";
            return size + "MB";
        } else if (size < 1024 * 1024) {
            float kbsize = size / 1024f;
            return formater.format(kbsize) + "KB";
        } else if (size < 1024 * 1024 * 1024) {
            float mbsize = size / 1024f / 1024f;
            return formater.format(mbsize) + "MB";
        } else if (size < 1024 * 1024 * 1024 * 1024) {
            float gbsize = size / 1024f / 1024f / 1024f;
            return formater.format(gbsize) + "GB";
        } else {
            return "size: error";
        }
    }

    /**
     * 返回kb的数据大小对应的文本
     */
    public static String getKBDataSize(long size) {
        if (size < 0) {
            size = 0;
        }
        return getDataSize(size * 1024);
    }

    /**
     * 专门设置价格
     * 金钱算法--更精确
     *
     * @param price
     * @return
     */
    public static float setPrice(Float price) {
        BigDecimal bigDecimal = new BigDecimal(price);
        BigDecimal scale = bigDecimal.setScale(2, 4);

        return scale.floatValue();
    }

//    public static void setDialogByCallPhone(final Context context, final String tel) {
//        View numberView = View.inflate(context, R.layout.call_phone_layout, null);
//        TextView t = (TextView) numberView.findViewById(R.id.tel);
//        t.setText(tel);
//        AlertDialog.Builder alert = new AlertDialog.Builder(context);
//
////        alert.setTitle("设置网络")//4000798400
//        alert.setMessage("")
//                .setView(numberView)
//                .setPositiveButton("呼叫", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        // 跳转到系统打电话界面
//                        Intent intent = new Intent(Intent.ACTION_CALL);
//                        intent.setData(Uri.parse("tel:"+tel));
//                        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                            return;
//                        }
//                        context.startActivity(intent);
//                    }
//                })
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//        alert.create().show();
//    }
public static String getVerson(Context mContext) {
    /**
     * 获取版本号
     */
        PackageManager manager = mContext.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
//    ic_black_back "3.2.0";
}
}
