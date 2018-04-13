package net.iclassmate.teacherspace.ui.activity.dialog;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import net.iclassmate.teacherspace.R;

public class LinkmanDialogActivity extends Activity implements View.OnClickListener{
    private TextView mcancel,mcall,mphone;
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_forget_password);

        initView();
    }

    private void initView() {
        mcancel= (TextView) findViewById(R.id.dialog_cancel);
        mcall= (TextView) findViewById(R.id.dialog_call);
        mphone= (TextView) findViewById(R.id.linkman_phone_tv);


        phoneNumber=mphone.getText().toString().trim();

        mcall.setOnClickListener(this);
        mcancel.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_cancel:
                this.finish();
                break;
            case R.id.dialog_call:
                Intent i=new Intent("android.intent.action.CALL", Uri.parse("tel:"+phoneNumber));
                startActivity(i);
                break;
        }

    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("LinkmanDialogActivity"); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this);          //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("LinkmanDialogActivity"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
        MobclickAgent.onPause(this);
    }
}
