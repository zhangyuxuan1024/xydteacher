package net.iclassmate.teacherspace.ui.activity.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.utils.UIUtils;

public class IfChangePhoneNumber extends Activity implements View.OnClickListener{

    private TextView mYes,mNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_if_change_phone_number);
        initView();
    }

    private void initView() {
        mYes= (TextView) findViewById(R.id.yes);
        mNo= (TextView) findViewById(R.id.no);

        mYes.setOnClickListener(this);
        mNo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.yes:
                Intent intent=new Intent(UIUtils.getContext(),BoundPhoneDialogActivity.class);
                intent.putExtra("from","ChangeBoundPhone");
                intent.putExtra("type",2);
                intent.putExtra("resultType",2);
                intent.putExtra("message","输入要绑定的手机号");
                startActivity(intent);
                this.finish();
                break;
            case R.id.no:
                this.finish();
                break;
        }
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("IfChangePhoneNumber"); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this);          //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("IfChangePhoneNumber"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
        MobclickAgent.onPause(this);
    }
}
