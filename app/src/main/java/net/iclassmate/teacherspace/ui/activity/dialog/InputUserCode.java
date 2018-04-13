package net.iclassmate.teacherspace.ui.activity.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.utils.DataCallback;
import net.iclassmate.teacherspace.utils.HttpManger;
import net.iclassmate.teacherspace.utils.ToastUtils;
import net.iclassmate.teacherspace.utils.UIUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class InputUserCode extends Activity implements View.OnClickListener,DataCallback{

    private EditText mInputUserCode;
    private TextView mCancel,mSure;
    private String userCode;
    private HttpManger httpManger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_user_code);
        initView();
    }

    private void initView() {
        mInputUserCode= (EditText) findViewById(R.id.bound_userCode);
        mCancel= (TextView) findViewById(R.id.login_cancel);
        mSure= (TextView) findViewById(R.id.login_sure);

        httpManger=new HttpManger(this);

        mCancel.setOnClickListener(this);
        mSure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_cancel:
                this.finish();
                break;
            case R.id.login_sure:
                userCode=mInputUserCode.getText().toString().trim();
                if (!userCode.equals("")){
                    httpManger.sendUserCode(userCode);
                }else {
                    ToastUtils.show(UIUtils.getContext(),"请输入账号");
                }

                break;
        }
    }

    @Override
    public void sendData(Object object) {
        String result=object.toString();
        if (!result.equals("404")){
            try {
                JSONObject js=new JSONObject(result);
                String phoneNum=js.getString("phoneNum");
                if (phoneNum.equals("null")){
                    Intent intent = new Intent(UIUtils.getContext(), LinkmanDialogActivity.class);
                    startActivity(intent);
                    this.finish();
                }else {
                    Intent intent2 = new Intent(UIUtils.getContext(), BoundPhoneDialogActivity.class);
                    intent2.putExtra("from","LoginActivity");
                    intent2.putExtra("type",3);
                    intent2.putExtra("resultType",4);
                    intent2.putExtra("message","输入已绑定的手机号");
                    startActivity(intent2);
                    this.finish();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            ToastUtils.show(UIUtils.getContext(),"服务器异常，请稍后再试");
        }

    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("InputUserCode"); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this);          //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("InputUserCode"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
        MobclickAgent.onPause(this);
    }
}
