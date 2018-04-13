package net.iclassmate.teacherspace.ui.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import net.iclassmate.teacherspace.BuildConfig;
import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.application.MyApplication;
import net.iclassmate.teacherspace.bean.login.Login_userInfo;
import net.iclassmate.teacherspace.constant.Constants;
import net.iclassmate.teacherspace.ui.activity.IntroduceActivity;
import net.iclassmate.teacherspace.ui.activity.LoginActivity;
import net.iclassmate.teacherspace.ui.activity.dialog.BoundPhoneDialogActivity;
import net.iclassmate.teacherspace.ui.activity.dialog.DialogActivity;
import net.iclassmate.teacherspace.ui.activity.dialog.IfChangePhoneNumber;
import net.iclassmate.teacherspace.ui.activity.weike.WeiKeActivity;

import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class OwnerFragment extends LazyFragment implements View.OnClickListener {
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    private boolean isFirstLoad;
    private TextView changePassword, mUserName, mUserCode;
    private RelativeLayout weikerl, aboutApp, boundrl, updaterl, exitrl;
    private Login_userInfo loginUserInfo;
    private SharedPreferences msharedPreferences;
    private Context mContext;
    private String userName, userCode;
    private LinearLayout tag_linear;
    private Set<String> role;
    private Set<String> teacherInfo;
    private String mobileNum;

    private TextView textView;
    private TextView version;
    private ImageView update;

    private int nowCode;

    private String versionName;
    private String versionMark;
    private String versionUrl;
    private double versionSize;
    private double versionCode;
    private MyApplication myApplication;

    public OwnerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_owner, container, false);
        //XXX初始化view的各控件
        mContext = getActivity();
        msharedPreferences = mContext.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        userCode = msharedPreferences.getString("userCode", "用户账号");
        userName = msharedPreferences.getString("teacherName", "用户名");
        role = msharedPreferences.getStringSet("role", null);
        teacherInfo = msharedPreferences.getStringSet("teacherInfo", null);
        mobileNum = msharedPreferences.getString("mobileNum", "");


        initView(view);
        isPrepared = true;
        lazyLoad();
        return view;
    }


    private void initView(View view) {
        changePassword = (TextView) view.findViewById(R.id.change_password_tv);
        aboutApp = (RelativeLayout) view.findViewById(R.id.owner_about_relativeLayout);
        weikerl = (RelativeLayout) view.findViewById(R.id.owner_weike_relativeLayout);
        boundrl = (RelativeLayout) view.findViewById(R.id.owner_bound_tele_relativeLayout);
        updaterl = (RelativeLayout) view.findViewById(R.id.owner_update_relativeLayout);
        exitrl = (RelativeLayout) view.findViewById(R.id.owner_exit_relativeLayout);
        mUserCode = (TextView) view.findViewById(R.id.person_code_tv);
        mUserName = (TextView) view.findViewById(R.id.person_name_tv);
        tag_linear = (LinearLayout) view.findViewById(R.id.tag_linear);
        textView = (TextView) view.findViewById(R.id.tv_bound_tele);
        version = (TextView) view.findViewById(R.id.tv_version);
        update = (ImageView) view.findViewById(R.id.update);

        changePassword.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

        changePassword.setOnClickListener(this);
        aboutApp.setOnClickListener(this);
        weikerl.setOnClickListener(this);
        boundrl.setOnClickListener(this);
        updaterl.setOnClickListener(this);
        exitrl.setOnClickListener(this);
    }

    /**
     * 初始化标签
     */
    private void initTag() {
        int index = 0;
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int scrWidth = (int) (dm.widthPixels);
        float rate = (float) scrWidth / 1440;

        LinearLayout.LayoutParams L_TAG = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, (int) (rate * 120));
        L_TAG.topMargin = (int) (rate * 25);
        L_TAG.bottomMargin = (int) (rate * 25);

        LinearLayout.LayoutParams L_TEXT = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        L_TEXT.leftMargin = (int) (rate * 25);
        L_TEXT.rightMargin = (int) (rate * 25);

        LinearLayout layout_L_tag = new LinearLayout(mContext);
        layout_L_tag.setLayoutParams(L_TAG);
        layout_L_tag.setOrientation(LinearLayout.HORIZONTAL);


        for (String str_role : role) {
            TextView tag_tv = new TextView(mContext);
            tag_tv.setText(str_role);
            tag_tv.setBackgroundResource(R.mipmap.ic_biaoqian);
            tag_tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.app_color));
            tag_tv.setTextSize(13f);
            tag_tv.setLayoutParams(L_TEXT);
            tag_tv.setGravity(Gravity.CENTER);

            if (index == 4) {
                index = 0;

                tag_linear.addView(layout_L_tag);


                layout_L_tag = new LinearLayout(mContext);
                layout_L_tag.setLayoutParams(L_TAG);
                layout_L_tag.setOrientation(LinearLayout.HORIZONTAL);
                layout_L_tag.addView(tag_tv);
            } else {
                layout_L_tag.addView(tag_tv);

            }
            index++;


        }
        for (String str : teacherInfo) {
            TextView tag_1 = new TextView(mContext);
            tag_1.setText(str);
            tag_1.setBackgroundResource(R.mipmap.ic_biaoqian);
            tag_1.setTextColor(ContextCompat.getColor(getActivity(), R.color.app_color));
            tag_1.setTextSize(13f);
            tag_1.setLayoutParams(L_TEXT);
            tag_1.setGravity(Gravity.CENTER);

            if (index == 4) {
                index = 0;

                tag_linear.addView(layout_L_tag);

                layout_L_tag = new LinearLayout(mContext);
                layout_L_tag.setLayoutParams(L_TAG);
                layout_L_tag.setOrientation(LinearLayout.HORIZONTAL);
                layout_L_tag.addView(tag_1);

            } else {
                layout_L_tag.addView(tag_1);
            }
            index++;
        }
        if (index != 0) {
            for (int i = 0; i < 4 - index; i++) {
                TextView tag_empty = new TextView(mContext);
                tag_empty.setLayoutParams(L_TEXT);
                layout_L_tag.addView(tag_empty);
            }
            tag_linear.addView(layout_L_tag);
        }
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || isFirstLoad) {
            return;
        }
        //填充各控件的数据
        initValuse();
        isFirstLoad = true;
    }

    private void initValuse() {

        myApplication = (MyApplication) getActivity().getApplication();
        versionMark = myApplication.getVersionMark();
        versionName = myApplication.getVersionName();
        versionSize = myApplication.getVersionSize();
        versionUrl = myApplication.getVersionUrl();
        versionCode = myApplication.getVersionCode();

        nowCode = BuildConfig.VERSION_CODE;
        Log.i("版本信息", versionCode + "," + nowCode);
        if (versionCode > nowCode) {
            version.setVisibility(View.GONE);
            update.setVisibility(View.VISIBLE);
        } else {
            version.setVisibility(View.VISIBLE);
            update.setVisibility(View.GONE);
        }


        if (!mobileNum.equals("null")) {
            textView.setText("已绑定" + mobileNum);
        }

        initTag();

        mUserCode.setText("账号：" + userCode);
        mUserName.setText(userName);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change_password_tv:
                if (mobileNum.equals("null")) {
                    Intent i1 = new Intent(getActivity(), BoundPhoneDialogActivity.class);
                    i1.putExtra("from", "ChangePWD");
                    i1.putExtra("type", 1);
                    i1.putExtra("resultType", 4);
                    i1.putExtra("message", "请输入手机号");
                    startActivity(i1);
                } else {
                    Intent i1 = new Intent(getActivity(), BoundPhoneDialogActivity.class);
                    i1.putExtra("from", "ChangePWDNoBound");
                    i1.putExtra("type", 3);
                    i1.putExtra("resultType", 3);
                    i1.putExtra("message", "输入已绑定的手机号");
                    startActivity(i1);
                }

                break;
            case R.id.owner_weike_relativeLayout:
                if (Build.VERSION.SDK_INT < 23) {
                    Intent i2 = new Intent(getActivity(), WeiKeActivity.class);
                    startActivity(i2);
                } else {
                    AndPermission.with(this).permission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE).requestCode(1011).send();
                }
                break;
            case R.id.owner_bound_tele_relativeLayout:
                if (mobileNum.equals("null")) {
                    Intent i3 = new Intent(getActivity(), BoundPhoneDialogActivity.class);
                    i3.putExtra("from", "BoundPhone");
                    i3.putExtra("type", 1);
                    i3.putExtra("resultType", 1);
                    i3.putExtra("message", "输入要绑定的手机号");
                    startActivity(i3);
                } else {
                    Intent i3 = new Intent(getActivity(), IfChangePhoneNumber.class);
                    startActivity(i3);
                }
                break;
            case R.id.owner_update_relativeLayout:
                if (nowCode < versionCode) {
                    Intent i4 = new Intent(getActivity(), DialogActivity.class);
                    i4.putExtra("versionName", versionName);
                    i4.putExtra("versionMark", versionMark);
                    i4.putExtra("versionUrl", versionUrl);
                    i4.putExtra("versionSize", versionSize);
                    startActivity(i4);
                }

                break;
            case R.id.owner_about_relativeLayout:
                Intent i5 = new Intent(getActivity(), IntroduceActivity.class);
                startActivity(i5);
                break;
            case R.id.owner_exit_relativeLayout:
                Intent i6 = new Intent(getActivity(), LoginActivity.class);
                msharedPreferences.edit().putBoolean("had_login", false).apply();
                startActivity(i6);
                getActivity().finish();

                //友盟统计账号退出
                MobclickAgent.onProfileSignOff();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        AndPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults, listener);
    }

    public PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode) {
            if (requestCode == 1011) {
                Intent i2 = new Intent(getActivity(), WeiKeActivity.class);
                startActivity(i2);
            }
        }

        @Override
        public void onFailed(int requestCode) {
            if (requestCode == 1011) {
                Toast.makeText(getActivity(), "您未开放读写权限", Toast.LENGTH_SHORT).show();
            }
        }
    };

    public void onResume() {
        super.onResume();
        mobileNum = msharedPreferences.getString("mobileNum", "");

        if (!mobileNum.equals("null")) {
            textView.setText("已绑定" + mobileNum);
        }

        MobclickAgent.onPageStart("MainActivity_OwnerFragment"); //统计页面，"MainScreen"为页面名称，可自定义
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MainActivity_OwnerFragment");
    }
}
