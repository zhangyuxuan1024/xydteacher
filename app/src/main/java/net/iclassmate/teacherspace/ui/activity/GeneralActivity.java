package net.iclassmate.teacherspace.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.umeng.analytics.MobclickAgent;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.adapter.SingleReportFragmentAdapter;
import net.iclassmate.teacherspace.bean.exam.ExamInfo;
import net.iclassmate.teacherspace.bean.exam.MultiExamInfo;
import net.iclassmate.teacherspace.bean.general.GeneralAll;
import net.iclassmate.teacherspace.constant.Constants;
import net.iclassmate.teacherspace.ui.fragment.LazyFragment;
import net.iclassmate.teacherspace.ui.fragment.general.GeneralAllFragment;
import net.iclassmate.teacherspace.ui.fragment.general.GeneralClassFragment;
import net.iclassmate.teacherspace.ui.fragment.general.GeneralGradeFragment;
import net.iclassmate.teacherspace.utils.FileUtils;
import net.iclassmate.teacherspace.utils.JsonUtils;
import net.iclassmate.teacherspace.utils.NetWorkUtils;
import net.iclassmate.teacherspace.utils.UIUtils;
import net.iclassmate.teacherspace.view.TitleBar;
import net.iclassmate.teacherspace.view.loading.LoadingHelper;
import net.iclassmate.teacherspace.view.loading.LoadingListener;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class GeneralActivity extends FragmentActivity implements LoadingListener, View.OnClickListener, TitleBar.TitleOnClickListener {
    private ViewPager general_vp;
    private LinearLayout general_grade_ll;
    private TitleBar titleBar;
    private ArrayList<LazyFragment> fragments;
    private SingleReportFragmentAdapter adapter;
    private List<TextView> textViewList;
    private TabLayout general_fragment_tablayout;
    private int tv_cur, vp_cur, a, meId, schoolId;
    private String classId, ss;
    private GeneralAll generalAll;
    private List<String> classList;
    private String str;

    private LoadingHelper loadingHelper;
    private boolean isShowLoading;
    private boolean isNoNetWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_general);
        init();
    }

    public void init() {
        classList = new ArrayList<>();
        titleBar = (TitleBar) findViewById(R.id.general_title);
        titleBar.setTitle("全科");
        titleBar.setLeftIcon(R.drawable.fragment_back);
        titleBar.setTitleClickListener(this);
        general_vp = (ViewPager) findViewById(R.id.general_vp);
        general_grade_ll = (LinearLayout) findViewById(R.id.general_grade_ll);
        general_fragment_tablayout = (TabLayout) findViewById(R.id.general_fragment_tablayout);
        textViewList = new ArrayList<TextView>();
        fragments = new ArrayList<LazyFragment>();

        Intent intent = getIntent();
        ExamInfo examInfo = (ExamInfo) intent.getSerializableExtra("examInfo");
        MultiExamInfo multiExamInfo = (MultiExamInfo) intent.getSerializableExtra("multiExamInfo");
        meId = multiExamInfo.getMeId();
        schoolId = multiExamInfo.getSchoolId();
        classId = multiExamInfo.getClassId();
        classList = multiExamInfo.getClassIds();
        String classIds = classList.toString();
        ss = classIds.substring(1, classIds.length() - 1);
        Log.i("miss", "ss=" + ss);
        Log.i("miss", "------" + Constants.GENERAL + meId + "/" + schoolId + "/" + ss);
        loadingHelper = new LoadingHelper(findViewById(R.id.loading_prompt_relative), findViewById(R.id.loading_empty_prompt_linear));

        setTrueData();

//        String string = FileUtils.read2Sd("quanke.txt");
//        if (string == null || string.equals("") || string.equals("null")) {
//            setTrueData();
//        } else {
//            try {
//                if (string.contains("xydzyx") && string.contains(Constants.GENERAL + meId + "/" + schoolId + "/" + URLEncoder.encode(ss, "utf-8"))) {
//                    String[] s = string.split("xydzyx");
//                    generalAll = JsonUtils.StartJson(s[0]);
//                    loadData(generalAll);
//                    sendDataTo(generalAll);
//                } else {
//                    setTrueData();
//                }
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public void setTrueData() {
        loadingHelper.ShowLoading();
        loadingHelper.SetListener(this);
        HttpUtils httpUtils = new HttpUtils();
        try {
            httpUtils.send(HttpRequest.HttpMethod.GET, Constants.GENERAL + meId + "/" + schoolId + "/" + URLEncoder.encode(ss, "utf-8"), new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    try {
                        Log.i("miss", "url=" + Constants.GENERAL + meId + "/" + schoolId + "/" + URLEncoder.encode(ss, "utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    str = responseInfo.result;
                    try {
                        FileUtils.write2Sd(str + "xydzyx" + Constants.GENERAL + meId + "/" + schoolId + "/" + URLEncoder.encode(ss, "utf-8"), "quanke.txt");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    generalAll = JsonUtils.StartJson(str);
                    Log.i("miss", "---" + generalAll);
                    if (generalAll.getList() == null || generalAll.getList().equals("") || generalAll.getList().equals("null")) {
                        Log.i("miss", "---" + generalAll.getList());
                        checkState(true);
                    } else {
                        loadData(generalAll);
                        sendDataTo(generalAll);
                    }
                }

                @Override
                public void onFailure(HttpException error, String msg) {
                    error.printStackTrace();
                    Log.i("miss", "网络请求失败：" + error.getMessage());
                    checkState(true);
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void checkState(boolean flag) {
        if (!NetWorkUtils.isNetworkAvailable(UIUtils.getContext())) {
            loadingHelper.ShowError("请检查您的网络链接！");
            isShowLoading = true;
            isNoNetWork = true;
        }

        if (flag && !isNoNetWork) {
            loadingHelper.ShowError("您所在学校或您所任教学科没有任何考试信息！");
            isShowLoading = true;
        }
    }

    private void loadData(GeneralAll generalAll) {
        loadingHelper.HideLoading(View.INVISIBLE);
        titleBar.setTitle("全科");
        for (int i = 0; i < generalAll.getList().size(); i++) {
            TextView tv = new TextView(this);
            tv.setText(generalAll.getList().get(i).getClassName());
            tv.setTag(i);
            tv.setTextSize(15);
            tv.setPadding(25, 0, 25, 0);
            tv.setOnClickListener(this);
            textViewList.add(tv);
            general_grade_ll.addView(tv);
        }
        vp_cur = 0;
        tv_cur = 0;
        a = 0;
        setTextColor(tv_cur, vp_cur, a);
        setFragment();
    }


    private void setFragment() {

        fragments.add(new GeneralAllFragment());
        fragments.add(new GeneralGradeFragment());
        fragments.add(new GeneralClassFragment());

        adapter = new SingleReportFragmentAdapter(getSupportFragmentManager(), fragments);
        general_vp.setAdapter(adapter);
        general_vp.setOffscreenPageLimit(3);
        general_fragment_tablayout.setupWithViewPager(general_vp);
        general_vp.setOnPageChangeListener(listener);
        general_vp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        int index = 0;
        Object object = view.getTag();
        if (object instanceof Integer) {
            index = (Integer) object;
            if (vp_cur == 1) {
                return;
            }
            tv_cur = index;
            setTextColor(tv_cur, vp_cur, tv_cur);
        }
    }

    private void setTextColor(int index, int cur, int a) {
        if (cur == 0) {
            setColor(index, true);
        } else if (cur == 1) {
            tv_cur = 0;
            setColor(tv_cur, false);
        } else if (cur == 2) {
            if (index == 0) {
                tv_cur = 1;
            }
            setColor(tv_cur, true);
            if (textViewList != null && textViewList.size() > 0) {
                textViewList.get(0).setTextColor(Color.parseColor("#dddddd"));
            }
        }
        tvChanged(tv_cur);
    }

    private void setColor(int index, boolean flag) {
        for (int i = 0; i < textViewList.size(); i++) {
            TextView tv = textViewList.get(i);
            if (i == index) {
                tv.setTextColor(ContextCompat.getColor(this,R.color.app_color));
            }
            if (flag) {
                if (i != index) {
                    tv.setTextColor(Color.parseColor("#999999"));
                }
            } else {
                if (i != index) {
                    tv.setTextColor(Color.parseColor("#dddddd"));
                }
            }
        }
    }

    private ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            general_vp.getParent().requestDisallowInterceptTouchEvent(true);
        }

        @Override
        public void onPageSelected(int position) {
            general_vp.setCurrentItem(position);
            vp_cur = position;
            setTextColor(tv_cur, vp_cur, a);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void sendDataTo(GeneralAll generalAll) {
        Log.i("info", "点击了" + tv_cur + "tv");
        LazyFragment fragment;
        for (int i = 0; i < fragments.size(); i++) {
            fragment = fragments.get(i);
            fragment.sendDataToFragment(tv_cur, generalAll);
        }
    }

    //TextView改变
    private void tvChanged(int index) {
        LazyFragment fragment;
        for (int i = 0; i < fragments.size(); i++) {
            fragment = fragments.get(i);
            fragment.textViewSelectedChanged(index);
        }
    }

    @Override
    public void leftClick() {
        GeneralActivity.this.finish();
    }

    @Override
    public void rightClick() {

    }

    @Override
    public void titleClick() {

    }

    @Override
    public void OnRetryClick() {

    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);       //统计时长
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
