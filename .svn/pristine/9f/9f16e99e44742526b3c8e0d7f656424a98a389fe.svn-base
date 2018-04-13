package net.iclassmate.teacherspace.utils;

import android.content.Context;
import android.util.Log;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import net.iclassmate.teacherspace.bean.login.Login_use;

/**
 * Created by xyd on 2016/2/23.
 * 登陆帮助类
 */
public class LoginUtils {
    private LoginCallback callback;
    private String LoginUser;
    private String LoginPw;
    private HttpUtils httpUtils;

    public void setLoginCallback(LoginCallback cb) {
        this.callback = cb;
    }

    /**
     * 登录回调
     */
    public interface LoginCallback {
        public void successMsg(String msg);

        public void errorMsg(String errorMsg);
    }

    public void login(Context ctx, Login_use login) {
        String loginurl = login.getUrlPath();
        LoginUser = login.getUsercode();
        LoginPw = login.getPassword();

        String url = loginurl + LoginUser + "/" + LoginPw;

        Log.i("lxw", "url=" + url);
        httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Log.i("lxw", "result=" + result + "callback=" + callback);
                    if (callback != null) {
                        callback.successMsg(result);
                    }
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                if (callback != null) {
//                    callback.errorMsg("服务器繁忙，请稍后再试");
                    callback.errorMsg(msg);
                }
            }
        });

    }
}
