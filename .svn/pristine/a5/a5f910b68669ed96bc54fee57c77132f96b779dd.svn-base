package net.iclassmate.teacherspace.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import net.iclassmate.teacherspace.R;

public class GeneralPaperActivity extends FragmentActivity implements View.OnClickListener{
    private TextView paper_coursename;
    private ImageView paper_fanhui;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_general_paper);

        Intent intent = getIntent();
        int number = intent.getIntExtra("index", 0);
//        TextPager textPager = (TextPager) intent.getSerializableExtra("textpager");
        Log.i("miss", "index==" + number);
//        Log.i("miss","textPager==" + textPager.toString());

        init();
    }
    public void init(){
        paper_coursename = (TextView) findViewById(R.id.paper_coursename);
        paper_fanhui = (ImageView) findViewById(R.id.paper_fanhui);
        paper_fanhui.setOnClickListener(this);
//        receiveDate();
    }
    public void receiveDate(){
        Intent intent = getIntent();
//        textPager = (TextPager) intent.getSerializableExtra("textpager");
        String str = intent.getStringExtra("indexx");

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.paper_fanhui:
                GeneralPaperActivity.this.finish();
                break;
        }
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("GeneralPaperActivity"); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this);          //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("GeneralPaperActivity"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
        MobclickAgent.onPause(this);
    }
}
