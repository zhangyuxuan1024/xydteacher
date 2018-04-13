package net.iclassmate.teacherspace.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.umeng.analytics.MobclickAgent;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.bean.login.Login_school;
import net.iclassmate.teacherspace.bean.login.Login_school_con;
import net.iclassmate.teacherspace.bean.login.Login_school_config;
import net.iclassmate.teacherspace.constant.Constants;
import net.iclassmate.teacherspace.utils.JsonUtils;
import net.iclassmate.teacherspace.utils.NetWorkUtils;
import net.iclassmate.teacherspace.utils.ToastUtils;
import net.iclassmate.teacherspace.utils.UIUtils;
import net.iclassmate.teacherspace.view.TitleBar;
import net.iclassmate.teacherspace.view.loading.LoadingHelper;
import net.iclassmate.teacherspace.view.loading.LoadingListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginSearchSchoolActivity extends Activity implements TitleBar.TitleOnClickListener, LoadingListener {

    private TitleBar titleBar;
    private EditText mSearchSchool;
    private ListView search_list;
    private SimpleAdapter schoollistadapter;
    private String latestsearch, latestsearching;
    private ArrayList<Map<String, Object>> mData;
    private ArrayList<Login_school> schoollist;
    private Login_school_con loginschoolcon;
    private Context context;
    private HttpUtils httpUtils;
    private LoadingHelper loadingHelper;
    private ImageView mCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login_search_school);

        this.context = this;

        initView();
    }

    private void initView() {
        this.titleBar = (TitleBar) this.findViewById(R.id.title_bar);
        this.mSearchSchool = (EditText) this.findViewById(R.id.search_school);
        this.search_list = (ListView) this.findViewById(R.id.search_list);
        this.mCancel= (ImageView) this.findViewById(R.id.bt_search_cancel);
        this.httpUtils = new HttpUtils();
        loadingHelper = new LoadingHelper(
                findViewById(R.id.loading_prompt_relative),
                findViewById(R.id.loading_empty_prompt_linear));
        loadingHelper.SetListener(this);

        mSearchSchool.setHintTextColor(Color.parseColor("#bbbbbb"));

        titleBar.setTitle(getResources().getString(R.string.search_school));
        titleBar.setLeftIcon(R.mipmap.ic_fanhui);
        titleBar.setTitleClickListener(this);

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchSchool.setText("");
            }
        });

        inputSchoolName();
    }

    private void inputSchoolName() {

        mSearchSchool.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("lxw", s.toString());

                String text = s.toString();
                ArrayList<String> textList = new ArrayList<String>();
                char input[] = null;
                input = text.trim().toCharArray();
                int j = input.length;
                System.out.println("input" + j);

                latestsearch = s.toString().replace(" ", "");
                searchSchool(latestsearch);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    mCancel.setVisibility(View.GONE);
                } else {
                    mCancel.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    private void searchSchool(String latestsearch) {
        latestsearching = latestsearch;
        initemptylist();

        if (latestsearch.length() > 0) {
            loadingHelper.ShowLoading();
        } else {
            loadingHelper.HideLoading(View.INVISIBLE);
            return;
        }
        if (NetWorkUtils.isNetworkAvailable(context)){
            String url = Constants.LOGIN_SEARCHSCHOOL_DM_URL + latestsearch;
            Log.i("lxw", url + "search:" + latestsearch);
            httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {

                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    String result = responseInfo.result;
                    Log.i("lxw", result);
                    updateSchoolist(result);
                }

                @Override
                public void onFailure(HttpException error, String msg) {

                }
            });
        }else {
            loadingHelper.ShowError("请检查网络！");
        }

    }

    private void initemptylist() {
        if (mData != null)
            mData.clear();
        mData = new ArrayList<Map<String, Object>>();
        schoollistadapter = new SimpleAdapter(UIUtils.getContext(), mData,
                R.layout.item_school, new String[]{"schoolname"},
                new int[]{R.id.schoolname});
        search_list.setAdapter(schoollistadapter);
    }

    /**
     * 初始化搜索列表
     */
    private void initlist() {
        if (mData != null)
            mData.clear();
        mData = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < schoollist.size(); i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("schoolname", schoollist.get(i).getName());
            mData.add(item);
        }

        schoollistadapter = new SimpleAdapter(UIUtils.getContext(), mData,
                R.layout.item_school, new String[]{"schoolname"},
                new int[]{R.id.schoolname});
        search_list.setAdapter(schoollistadapter);

        loadingHelper.HideLoading(View.INVISIBLE);

        search_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int index,
                                    long arg3) {
                itemclick(index);
            }
        });
    }

    private void itemclick(final int index) {


        int schoolid = schoollist.get(index).getSchoolId();
        int appdid = 10003;
        String getshcoollisturl = Constants.GET_SCHOOL_SERVERLIST_DM + "?"
                + "schoolId=" + schoolid + "&" + "appId=" + appdid;
        Log.i("lxw", "getshcoollisturl:" + getshcoollisturl);
        httpUtils.send(HttpRequest.HttpMethod.GET, getshcoollisturl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;

                String filename = "xydschoolclick.txt";
                writeFileData(filename, result);
                try {
                    if (result.equals("[]")){
                        Intent it = new Intent(UIUtils.getContext(), LoginActivity.class);
                        String schoolInfo = schoollist.get(index).getName();
                        it.putExtra("schoolInfo", schoolInfo);
                        Log.i("lxw", schoolInfo);
                        setResult(LoginActivity.NOTICE_CODE_SCHOOL_ACTIVITY_RESULT_OTHER,
                                it);
                        finish();
                    }else {
                        Login_school_config loginschoolconfig = JsonUtils.analys_Login_schconfig(result);
                        Log.i("lxw", "itemclick=" + result + ",Login_school_config=" + loginschoolconfig.toString());

                        if (loginschoolconfig != null) {
                            Intent it = new Intent(UIUtils.getContext(), LoginActivity.class);
                            Bundle v_it = new Bundle();
                            v_it.putSerializable("schoolconfig", loginschoolconfig);
                            it.putExtras(v_it);
                            Log.i("lxw", it.toString());
                            setResult(LoginActivity.NOTICE_CODE_SCHOOL_ACTIVITY_RESULT,
                                    it);
                            finish();
                        } else {
                            ToastUtils.show(UIUtils.getContext(), "学校信息有错误，请联系学校！");
                        }
                    }
                }catch (Exception e){
                    ToastUtils.show(UIUtils.getContext(), "学校信息有错误，请联系学校！"+e);
                }


            }

            @Override
            public void onFailure(HttpException error, String msg) {
                ToastUtils.show(UIUtils.getContext(), "学校信息有错误，请联系学校！");
            }
        });
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            // 获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 解析返回的数据
     *
     * @param schoolInfo
     */
    private void updateSchoolist(String schoolInfo) {
        try {
            JSONArray shooljsonarry = new JSONArray(schoolInfo);

            if (shooljsonarry != null) {
//                loadingHelper.HideLoading(View.INVISIBLE);
                if (latestsearch.equals(latestsearching)) {
                    ArrayList<Login_school> loginschoollist = new ArrayList<Login_school>();
                    for (int i = 0; i < shooljsonarry.length(); i++) {
                        JSONObject objectlist = shooljsonarry.getJSONObject(i);
                        int schoolId = objectlist.getInt("schoolId");
                        int areaId = objectlist.getInt("areaId");
                        String name = objectlist.getString("name");
                        String areaName = objectlist.getString("areaName");
                        int status = objectlist.getInt("status");
                        Login_school loginschool = new Login_school(schoolId, areaId, name, areaName, status);
                        loginschoollist.add(loginschool);
                    }
                    if (loginschoollist != null &&
                            loginschoollist.size() > 0) {
                        schoollist = loginschoollist;
                        initlist();
                    } else {
                        loadingHelper.ShowError(this
                                .getString(R.string.loading_show_empty));
                    }
                } else {
                    searchSchool(latestsearch);
                }
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            loadingHelper.ShowError(this
                    .getString(R.string.loading_show_empty));
        }
    }

    public void writeFileData(String fileName, String message) {

        try {


            File file = new File(Environment.getExternalStorageDirectory()
                    .getAbsolutePath(), fileName);
            FileOutputStream fout = new FileOutputStream(file);
            System.out.println(message);
            byte[] bytes = message.getBytes();
            System.out.println(bytes);
            fout.write(bytes);

            fout.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }


    @Override
    public void leftClick() {
//        String schoolName=mSearchSchool.getText().toString().trim();
//        if (schoolName.equals("")){
//            Intent it = new Intent(UIUtils.getContext(), LoginActivity.class);
//            setResult(LoginActivity.NOTICE_CODE_SCHOOL_ACTIVITY_RESULT_NONE,
//                    it);
//            this.finish();
//        }else {
            this.finish();
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void rightClick() {

    }

    @Override
    public void titleClick() {

    }

    @Override
    public void OnRetryClick() {

        String schoolName=mSearchSchool.getText().toString().trim();
        if (schoolName!=null){
            Log.i("lxw","beforeTextChanged"+schoolName);
            latestsearch = schoolName.replace(" ", "");
            searchSchool(latestsearch);
        }

    }


    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("LoginSearchSchoolActivity"); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this);          //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("LoginSearchSchoolActivity"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
        MobclickAgent.onPause(this);
    }
}
