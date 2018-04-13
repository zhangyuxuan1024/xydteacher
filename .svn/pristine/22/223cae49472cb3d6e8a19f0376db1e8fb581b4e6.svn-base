package net.iclassmate.teacherspace.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.constant.Constants;
import net.iclassmate.teacherspace.utils.DataCallback;
import net.iclassmate.teacherspace.utils.HttpManger;
import net.iclassmate.teacherspace.utils.ToastUtils;
import net.iclassmate.teacherspace.utils.UIUtils;
import net.iclassmate.teacherspace.view.TitleBar;

import org.json.JSONException;
import org.json.JSONObject;

public class ForgetPsActivity extends Activity implements TitleBar.TitleOnClickListener ,View.OnClickListener,DataCallback{

    private TitleBar titleBar;
    private Button cancel_btn, ensure_btn;
    private String userCode;
    private EditText mNew,maffirm;
    private String mNewInfo,maffirmInfo;
    private HttpManger httpManger;
    private SharedPreferences msharedPreferences;
    private Context mContext;
    private Toast toast = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_change_password);
        mContext=this;
        msharedPreferences=mContext.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        userCode=msharedPreferences.getString("userCode","");

        initView();
    }

    private void initView() {
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        cancel_btn = (Button) findViewById(R.id.cancel_btn);
        ensure_btn = (Button) findViewById(R.id.ensure_btn);
        mNew= (EditText) findViewById(R.id.new_password_et);
        maffirm= (EditText) findViewById(R.id.affirm_new_password_et);

        titleBar.setTitle(getResources().getString(R.string.change_password));
        titleBar.setLeftIcon(R.mipmap.ic_fanhui);
        titleBar.setTitleClickListener(this);

        cancel_btn.setOnClickListener(this);
        ensure_btn.setOnClickListener(this);
        mNew.addTextChangedListener(textWatcher);
        maffirm.addTextChangedListener(textWatcher);

    }
    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            //TODO
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            //TODO
        }
        @Override
        public void afterTextChanged(Editable s) {
            Log.d("TAG","afterTextChanged    "+"str="+s.toString());
            int len = s.toString().length();
            if(len>=10){
                toast = Toast.makeText(UIUtils.getContext(),"密码不能超过10位", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 235);
                toast.show();
            }
        }
    };

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel_btn:
                this.finish();
                break;
            case R.id.ensure_btn:

                mNewInfo=mNew.getText().toString().trim();
                maffirmInfo=maffirm.getText().toString().trim();
                Log.i("两次输入的密码",mNewInfo+";"+maffirmInfo);
                if (mNewInfo.equals(maffirmInfo)){
                    if (!userCode.equals("") && !mNewInfo.equals("") && !maffirmInfo.equals("")){
                        httpManger=new HttpManger(this);
                        httpManger.changeNewPWD(userCode,mNewInfo,maffirmInfo);
                    }else {
                        ToastUtils.show(UIUtils.getContext(),"新密码不能为空");
                    }
                }else {
                    ToastUtils.show(UIUtils.getContext(),"两次输入密码不一致");
                }


                break;
        }

    }

    @Override
    public void sendData(Object object) {
        String result=object.toString();
        if (result.equals("404")){
            ToastUtils.show(UIUtils.getContext(),"修改密码失败");
        }else {
            try {
                JSONObject jsonObject=new JSONObject(result);
                String resultDesc=jsonObject.getString("resultDesc");
                ToastUtils.show(UIUtils.getContext(),resultDesc);
                this.finish();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("ForgetPsActivity"); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this);          //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("ForgetPsActivity"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
        MobclickAgent.onPause(this);
    }
}
