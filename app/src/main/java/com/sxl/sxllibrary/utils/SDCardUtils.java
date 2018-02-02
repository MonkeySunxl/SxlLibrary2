package com.sxl.sxllibrary.utils;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * @ProjectName wmt
 * @ClassDescribe 手机总容量
 */

public class SDCardUtils {

    /**
     * 判断SD卡是否存在
     *
     * @return
     */
    private static boolean ExistSDCard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else
            return false;
    }

    /**
     * 查看SD卡剩余空间
     *
     * @return
     */
    public static long getSDFreeSize() {
        //取得SD卡文件路径
        File path = Environment.getExternalStorageDirectory();
        StatFs sf = new StatFs(path.getPath());
        //获取单个数据块的大小(Byte)
        long blockSize = sf.getBlockSize();
        //空闲的数据块的数量
        long freeBlocks = sf.getAvailableBlocks();
        //返回SD卡空闲大小
//        ic_black_back freeBlocks * blockSize;  //单位Byte
        return (freeBlocks * blockSize)/1024;   //单位KB
//        ic_black_back (freeBlocks * blockSize) / 1024 / 1024; //单位MB
    }

    /**
     * 查看SD卡总容量
     *
     * @return
     */
    public static long getSDAllSize() {
        //取得SD卡文件路径
        File path = Environment.getExternalStorageDirectory();
        StatFs sf = new StatFs(path.getPath());
        //获取单个数据块的大小(Byte)
        long blockSize = sf.getBlockSize();
        //获取所有数据块数
        long allBlocks = sf.getBlockCount();
        //返回SD卡大小
//        ic_black_back allBlocks * blockSize; //单位Byte
        return (allBlocks * blockSize)/1024; //单位KB
//        ic_black_back (allBlocks * blockSize) / 1024 / 1024; //单位MB
    }

}
