package net.iclassmate.teacherspace.ui.activity.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.bean.SMSInfo;
import net.iclassmate.teacherspace.constant.Constants;
import net.iclassmate.teacherspace.ui.activity.ForgetPsActivity;
import net.iclassmate.teacherspace.utils.DataCallback;
import net.iclassmate.teacherspace.utils.HttpManger;
import net.iclassmate.teacherspace.utils.NetWorkUtils;
import net.iclassmate.teacherspace.utils.ToastUtils;
import net.iclassmate.teacherspace.utils.UIUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BoundPhoneDialogActivity extends Activity implements View.OnClickListener, DataCallback {

    private Button mSecurityCodeBtn;
    private TextView mCancel, mSure, mText;
    private HttpManger httpManger;
    private EditText mPhoneNumber, mCode;
    private String mUserCode, from, message;
    private String mNumber = "";
    private int type, resultType;
    private SharedPreferences msharedPreferences;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound_phone_dialog);
        mContext = this;

        msharedPreferences = mContext.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mUserCode = msharedPreferences.getString("userCode", "");

        initView();
        initValues();


    }

    private void initValues() {

        Bundle bundle = this.getIntent().getExtras();
        from = bundle.getString("from");
        type = this.getIntent().getIntExtra("type", 0);
        resultType = this.getIntent().getIntExtra("resultType", 0);
        message = bundle.getString("message");

        if (from.equals("NoticeFragment")) {
            mText.setVisibility(View.VISIBLE);
            mText.setText(message);
        } else if (from.equals("LoginActivity")){
            mText.setVisibility(View.GONE);
            mPhoneNumber.setHint(message);
            mSure.setText("确定");
        }else if (from.equals("ChangePWDNoBound")){
            mText.setVisibility(View.GONE);
            mPhoneNumber.setHint(message);
            mSure.setText("确定");
        }else {
            mText.setVisibility(View.GONE);
            mPhoneNumber.setHint(message);
        }

    }

    private void initView() {
        mSecurityCodeBtn = (Button) findViewById(R.id.bound_btn);
        mCancel = (TextView) findViewById(R.id.bound_cancel);
        mSure = (TextView) findViewById(R.id.bound_sure);
        mText = (TextView) findViewById(R.id.bound_tv);
        mPhoneNumber = (EditText) findViewById(R.id.bound_phone);
        mCode = (EditText) findViewById(R.id.et_code);
        httpManger = new HttpManger(this);


        mSure.setOnClickListener(this);
        mCancel.setOnClickListener(this);
        mSecurityCodeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bound_btn:

                mNumber = mPhoneNumber.getText().toString().trim();
                if (!NetWorkUtils.isNetworkAvailable(UIUtils.getContext())) {
                    ToastUtils.show(UIUtils.getContext(), "请检查网络");
                } else {
                    if (mNumber.equals("")) {
                        ToastUtils.show(UIUtils.getContext(), "请输入手机号");
                    } else {
                        if (checkPhoneNumber(mNumber)) {

                            httpManger.getSMSData(mNumber,type,mUserCode,mSecurityCodeBtn);
                        } else {
                            ToastUtils.show(UIUtils.getContext(), "请输入正确的手机号");
                        }
                    }
                }


                break;
            case R.id.bound_cancel:
                this.finish();
                break;
            case R.id.bound_sure:
                String verificationCode = mCode.getText().toString().trim();
                String mNumber_later = mPhoneNumber.getText().toString().trim();
                if (!NetWorkUtils.isNetworkAvailable(UIUtils.getContext())) {
                    ToastUtils.show(UIUtils.getContext(), "请检查网络");
                } else {
                    if (mNumber_later.equals("")) {
                        ToastUtils.show(UIUtils.getContext(), "手机号不能为空");
                    } else {
                        if (!mNumber.equals("")) {
                            if (mNumber.equals(mNumber_later)) {
                                if (verificationCode.equals("")) {
                                    ToastUtils.show(UIUtils.getContext(), "请输入验证码");
                                } else {
                                    if (verificationCode.length()<6) {
                                        ToastUtils.show(UIUtils.getContext(), "请输入正确的验证码");
                                    }
                                    httpManger.ifCodeRight(mNumber, verificationCode, mUserCode, type);
                                }
                            } else {
                                ToastUtils.show(UIUtils.getContext(), "请检验手机号");
                            }
                        } else {
                            ToastUtils.show(UIUtils.getContext(), "请先获取验证码");
                        }
                    }

                }

                break;
        }

    }

    /**
     * 验证手机号码
     *
     * @param phoneNumber 手机号码
     * @return boolean
     */
    public static boolean checkPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("^1[0-9]{10}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    @Override
    public void sendData(Object object) {
        String result = object.toString();
        if (result.equals("404")) {
            ToastUtils.show(UIUtils.getContext(), "服务器异常，请稍后再试");
        } else {
            SMSInfo smsInfo = null;
            try {
                JSONObject jsonObject = new JSONObject(result);
                int resultCode = jsonObject.getInt("resultCode");
                String resultDesc = jsonObject.getString("resultDesc");
                String phoneNum = jsonObject.getString("phoneNum");
                smsInfo = new SMSInfo(resultCode, resultDesc, phoneNum);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            int resultNum = smsInfo.getResultCode();
            if (resultNum == 0) {
                if (resultType == 1) {
                    ToastUtils.show(UIUtils.getContext(), "绑定成功");
                    msharedPreferences.edit().putString("mobileNum", smsInfo.getPhoneNum()).apply();
                    this.finish();
                } else if (resultType == 2) {
                    ToastUtils.show(UIUtils.getContext(), "成功修改已绑定手机号");
                    msharedPreferences.edit().putString("mobileNum", smsInfo.getPhoneNum()).apply();
                    this.finish();
                } else if (resultType == 3) {
                    Intent intent = new Intent(UIUtils.getContext(), ForgetPsActivity.class);
                    startActivity(intent);
                    this.finish();
                } else if (resultType == 4) {
                    Intent intent = new Intent(UIUtils.getContext(), ForgetPsActivity.class);
                    msharedPreferences.edit().putString("mobileNum", smsInfo.getPhoneNum()).apply();
                    startActivity(intent);
                    this.finish();
                }
            } else if (resultNum == -1) {
                ToastUtils.show(UIUtils.getContext(), "验证码错误或失效");

            } else if (resultNum == -2) {
                ToastUtils.show(UIUtils.getContext(), "服务器繁忙或手机已绑定");
            }else if (resultNum==-3){
                ToastUtils.show(UIUtils.getContext(), "手机号码不匹配");
            }


        }
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("BoundPhoneDialogActivity"); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this);          //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("BoundPhoneDialogActivity"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
        MobclickAgent.onPause(this);
    }
}
