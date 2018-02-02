package com.sxl.sxllibrary.update;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sxl.sxllibrary.R;

import java.io.File;

import cn.woblog.android.downloader.DownloadService;
import cn.woblog.android.downloader.callback.DownloadListener;
import cn.woblog.android.downloader.config.Config;
import cn.woblog.android.downloader.domain.DownloadInfo;
import cn.woblog.android.downloader.exception.DownloadException;

import static cn.woblog.android.downloader.DownloadService.downloadManager;

/**
 * 下载中界面
 */
public class DownLoadDialog extends Activity {
    private ImageView close;
    public ProgressBar progressBar;
    public TextView textView;
    private boolean isShowToast=true;
    DownloadInfo downloadInfo;
    String saveFilePath= Environment.getExternalStorageDirectory().getPath() + "/"+"NewApk.apk";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_dialog);

        close = (ImageView) findViewById(R.id.downloaddialog_close);
        progressBar = (ProgressBar) findViewById(R.id.downloaddialog_progress);
        textView = (TextView) findViewById(R.id.downloaddialog_count);
//配置多线程下载
        try {
            Config config = new Config();
            //set database path.
//    config.setDatabaseName("/sdcard/a/d.db");
//      config.setDownloadDBController(dbController);

            //set download quantity at the same time.
            config.setDownloadThread(6);

            //set each download info thread number
            config.setEachDownloadThread(5);

            // set connect timeout,unit millisecond
            config.setConnectTimeout(10000);

            // set read data timeout,unit millisecond
            config.setReadTimeout(10000);
            DownloadService.getDownloadManager(this.getApplicationContext(), config);
        } catch (Exception e) {
            e.printStackTrace();
        }
        downloadInfo = downloadManager.getDownloadById(CheckVersion.updateUrl.hashCode());
        downLoadApk();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (downloadInfo!=null) {
                    downloadManager.pause(downloadInfo);
                }
                finish();

            }
        });

    }

    private void downLoadApk() {
        if (downloadInfo!=null){
            downloadManager.resume(downloadInfo);
        }else {
             downloadInfo = new DownloadInfo.Builder().setUrl(CheckVersion.updateUrl)
                    .setPath(saveFilePath)
                    .build();
            downloadManager.download(downloadInfo);
        }
        downloadInfo.setDownloadListener(new DownloadListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onWaited() {

            }

            @Override
            public void onPaused() {

            }

            @Override
            public void onDownloading(long progress, long size) {
//                    Log.i("aaa", "onDownloading: progress-->"+progress);
//                    Log.i("aaa", "onDownloading: size-->"+size);
                int i = (int) (((float) progress / size) * 100);
                textView.setText(i+"%");
                progressBar.setProgress(i);
            }

            @Override
            public void onRemoved() {

            }

            @Override
            public void onDownloadSuccess() {
                    Log.i("APK", "onDownloadSuccess: ");
                //下载完成删除数据库中的记录
                downloadManager.getDownloadDBController().delete(downloadInfo);
                installApk();
                finish();
            }

            @Override
            public void onDownloadFailed(DownloadException e) {
                downloadManager.pause(downloadInfo);
                if (isShowToast) {
                    Log.e("APK", "onDownloadFailed: "+e.getMessage());
                    Toast.makeText(DownLoadDialog.this, "亲~更新失败了", Toast.LENGTH_SHORT).show();
                    isShowToast=false;
                }
            }
        });

    }
    /**
     * 安装apk
     * @param
     */
    private void installApk(){
        File apkfile = new File(saveFilePath);
        if (!apkfile.exists()) {
            return;
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
        this.startActivity(i);
    }

}
