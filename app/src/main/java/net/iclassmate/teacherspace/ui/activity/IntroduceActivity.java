package net.iclassmate.teacherspace.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import net.iclassmate.teacherspace.BuildConfig;
import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.application.MyApplication;
import net.iclassmate.teacherspace.ui.activity.dialog.DialogActivity;
import net.iclassmate.teacherspace.utils.ToastUtils;
import net.iclassmate.teacherspace.view.TitleBar;

public class IntroduceActivity extends Activity implements View.OnClickListener, View.OnLongClickListener, TitleBar.TitleOnClickListener {

    private TitleBar titleBar;
    private TextView mVersion, tv_isnew;
    private ImageView iv_hasnew;
    private RelativeLayout rl_check, rl_call, rl_qq;

    private String nowName;
    private int nowCode;

    private String versionName;
    private String versionMark;
    private String versionUrl;
    private double versionSize;
    private double versionCode;
    private MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_introduce);
        initView();
        getVersion();
    }

    private void initView() {
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        mVersion = (TextView) findViewById(R.id.describe_tv);
        rl_check = (RelativeLayout) findViewById(R.id.rl_check);
        rl_call = (RelativeLayout) findViewById(R.id.rl_call);
        rl_qq = (RelativeLayout) findViewById(R.id.rl_qq);
        tv_isnew = (TextView) findViewById(R.id.tv_isnew);
        iv_hasnew = (ImageView) findViewById(R.id.iv_hasnew);

        rl_check.setOnClickListener(this);
        //          注释掉之后，客服电话和客服QQ都不能点击
        //        rl_call.setOnClickListener(this);
        //        rl_qq.setOnClickListener(this);
        //        rl_qq.setOnLongClickListener(this);

        titleBar.setTitle(getResources().getString(R.string.about_app));
        titleBar.setLeftIcon(R.mipmap.ic_fanhui);
        titleBar.setTitleClickListener(this);
    }

    private void getVersion() {
        nowName = BuildConfig.VERSION_NAME;
        nowCode = BuildConfig.VERSION_CODE;
        mVersion.setText("当前版本 : V" + nowName);

        myApplication = (MyApplication) IntroduceActivity.this.getApplication();
        versionMark = myApplication.getVersionMark();
        versionName = myApplication.getVersionName();
        versionSize = myApplication.getVersionSize();
        versionUrl = myApplication.getVersionUrl();
        versionCode = myApplication.getVersionCode();

        if (versionCode > nowCode) {
            tv_isnew.setVisibility(View.GONE);
            iv_hasnew.setVisibility(View.VISIBLE);
        } else {
            tv_isnew.setVisibility(View.VISIBLE);
            iv_hasnew.setVisibility(View.GONE);
        }

        Log.i("info", "当前Code:" + nowCode + ";最新Code:" + versionCode);
        Log.i("info", "当前Name:" + nowName + ";最新Name:" + versionName);
    }


    @Override
    public void leftClick() {
        finish();

    }

    @Override
    public void rightClick() {

    }

    @Override
    public void titleClick() {

    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("IntroduceActivity"); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this);          //统计时长
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("IntroduceActivity"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
        MobclickAgent.onPause(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_check:
                if (nowCode < versionCode) {
                    Intent i4 = new Intent(IntroduceActivity.this, DialogActivity.class);
                    i4.putExtra("versionName", versionName);
                    i4.putExtra("versionMark", versionMark);
                    i4.putExtra("versionUrl", versionUrl);
                    i4.putExtra("versionSize", versionSize);
                    startActivity(i4);
                } else {
                    ToastUtils.show(IntroduceActivity.this, "当前版本是最新版本");
                }
                break;
            case R.id.rl_call:
                if (Build.VERSION.SDK_INT < 23) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + getResources().getString(R.string.ruilong_phone)));
                    startActivity(intent);
                } else {
                    AndPermission.with(this)
                            .requestCode(1011)
                            .permission(Manifest.permission.CALL_PHONE)
                            .send();
                }
                Log.i("info", "tel: " + getResources().getString(R.string.ruilong_phone));
                break;
            case R.id.rl_qq:
                ToastUtils.show(IntroduceActivity.this, "长按试试");
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        AndPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults, listener);
    }

    public PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode) {
            if (requestCode == 1011) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + getResources().getString(R.string.ruilong_phone)));
                startActivity(intent);
                Log.i("info", "tel: " + getResources().getString(R.string.ruilong_phone));
            }
        }

        @Override
        public void onFailed(int requestCode) {
            if (requestCode == 1011) {
                Toast.makeText(IntroduceActivity.this, "您未开拨打电话权限", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public boolean onLongClick(View v) {
        ClipboardManager copy = (ClipboardManager) IntroduceActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
        copy.setText(getResources().getString(R.string.ruilong_qq));
        ToastUtils.show(IntroduceActivity.this, "已复制");
        return true;
    }
}
