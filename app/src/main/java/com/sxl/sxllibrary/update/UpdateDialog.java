package com.sxl.sxllibrary.update;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.sxl.sxllibrary.R;


/**
   更新提示界面
 */
public class UpdateDialog extends Activity {

    private TextView yes, no;
    private TextView tv_version, tv_changelog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_dialog);

        yes = (TextView) findViewById(R.id.updatedialog_yes);
        no = (TextView) findViewById(R.id.updatedialog_no);
        tv_version = (TextView) findViewById(R.id.title);
        tv_changelog = (TextView) findViewById(R.id.updatedialog_text_changelog);

        tv_version.setText("发现新版本: V" + CheckVersion.newVersion);
        tv_changelog.setText("更新日志：\n" + CheckVersion.changeLog);

        yes.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent=new Intent(UpdateDialog.this,DownLoadDialog.class);
                startActivity(intent);
                finish();
            }
        });
        no.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }


}
