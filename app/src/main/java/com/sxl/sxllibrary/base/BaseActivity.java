package com.sxl.sxllibrary.base;


import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.sxl.sxllibrary.utils.AppManager;
import com.sxl.sxllibrary.utils.NetWorkUtils;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    protected Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
//                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//保持屏幕常亮
//        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        setContentView(getLayoutId());
        initBefore(savedInstanceState);
        context=this;
        ButterKnife.bind(this);
        //初始化其他控件操作
        init();
    }

    /**
     * 检查网络
     */
    public boolean checkNetWork() {
        if (NetWorkUtils.isConnected(this)) {//判断是否连接
            return  true;
        }else {
            return  false;
        }
    }


    public void initBefore(Bundle savedInstanceState) {

    }
    protected void init() {
        initView();
        initData();
    }
    protected abstract void initView();

    protected abstract void initData();

    protected abstract int getLayoutId();
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity从堆栈中移除
        AppManager.getAppManager().finishActivity(this);
    }
@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {
    AudioManager am=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
    switch (keyCode){
        //监听音量键点击
        case KeyEvent.KEYCODE_VOLUME_UP:
            am.adjustStreamVolume (AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
            break;
        case KeyEvent.KEYCODE_VOLUME_DOWN:
            am.adjustStreamVolume (AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
            break;
        //监听手机返回键
        case KeyEvent.KEYCODE_BACK:
            finish();
            break;
    }
    return true;
}

}
