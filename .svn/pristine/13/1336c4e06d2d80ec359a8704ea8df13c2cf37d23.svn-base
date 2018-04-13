package net.iclassmate.teacherspace.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.readystatesoftware.viewbadger.BadgeView;
import com.umeng.analytics.MobclickAgent;

import net.iclassmate.teacherspace.BuildConfig;
import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.adapter.MainFragmentAdapter;
import net.iclassmate.teacherspace.application.MyApplication;
import net.iclassmate.teacherspace.ui.activity.dialog.DialogActivity;
import net.iclassmate.teacherspace.ui.fragment.ExamFragment;
import net.iclassmate.teacherspace.ui.fragment.NoticeFragment;
import net.iclassmate.teacherspace.ui.fragment.OwnerFragment;
import net.iclassmate.teacherspace.utils.DataCallback;
import net.iclassmate.teacherspace.utils.HttpManger;
import net.iclassmate.teacherspace.utils.NetWorkUtils;
import net.iclassmate.teacherspace.utils.ToastUtils;
import net.iclassmate.teacherspace.utils.UIUtils;
import net.iclassmate.teacherspace.view.MyViewPager;
import net.iclassmate.teacherspace.view.TitleBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class    MainActivity extends FragmentActivity implements View.OnClickListener, ViewPager.OnPageChangeListener,DataCallback {
    private List<Fragment> fragments;
    private ImageView iv_notice, iv_exam, iv_owner;
    private MyViewPager main_viewPager;
    private TitleBar titleBar;
    private MainFragmentAdapter mainFragmentAdapter;
    private BadgeView badgeView;
    private Context mContext;
    private int nowCode;
    private HttpManger httpManger;
    private MyApplication baseApplication;
    private int forceFlag;
    private String versionName;
    private String versionMark;
    private String versionUrl;
    private double versionCode;
    private double versionSize;

    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Bundle bundle = msg.getData();
                    String jsonresult = bundle.getString("jsonresult");
                    JSONObject object;
                    try {
                        object = new JSONObject(jsonresult);
                        JSONObject jsonObject = object.getJSONObject("versionInfo");
                        forceFlag = jsonObject.getInt("forceFlag");
                        versionName = jsonObject.getString("versionName");
                        versionMark = jsonObject.getString("versionMark");
                        versionSize = jsonObject.getDouble("versionSize");
                        versionUrl = jsonObject.getString("versionUrl");
                        versionCode = jsonObject.getDouble("versionCode");
                        baseApplication = (MyApplication) getApplication();
                        baseApplication.setVersionName(versionName);
                        baseApplication.setVersionCode(versionCode);
                        baseApplication.setVersionMark(versionMark);
                        baseApplication.setVersionSize(versionSize);
                        baseApplication.setVersionUrl(versionUrl);
                        Log.i("baseApplication",baseApplication.getVersionName());
                        if (versionCode > nowCode) {
                            if (forceFlag == 0) {
                                // updateManager.showNoticeDialog(versionMark,
                                // versionUrl);
                                Intent intent = new Intent(UIUtils.getContext(),
                                        DialogActivity.class);
                                intent.putExtra("versionName", versionName);
                                intent.putExtra("versionMark", versionMark);
                                intent.putExtra("versionUrl", versionUrl);
                                intent.putExtra("versionSize", versionSize);
                                startActivity(intent);
                            }else {
                                ToastUtils.show(UIUtils.getContext(),"强制更新");
                            }
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mContext = this;
        update();
        initView();
    }

    private void initView() {
        iv_notice = (ImageView) findViewById(R.id.main_iv_notice);
        iv_exam = (ImageView) findViewById(R.id.main_iv_exam);
        iv_owner = (ImageView) findViewById(R.id.main_iv_owner);
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        main_viewPager = (MyViewPager) findViewById(R.id.main_ViewPager);
        badgeView = new BadgeView(mContext, findViewById(R.id.bt));
        badgeView.setBadgePosition(BadgeView.POSITION_TOP_LEFT);
        badgeView.hide();

        nowCode= BuildConfig.VERSION_CODE;

        mainTvSelected(0);

        iv_notice.setOnClickListener(this);
        iv_exam.setOnClickListener(this);
        iv_owner.setOnClickListener(this);

        fragments = new ArrayList<Fragment>();
        fragments.add(new NoticeFragment());
        fragments.add(new ExamFragment());
        fragments.add(new OwnerFragment());
        mainFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager(), fragments);
        main_viewPager.setAdapter(mainFragmentAdapter);
        main_viewPager.setOnPageChangeListener(this);
        main_viewPager.setOffscreenPageLimit(2);
        main_viewPager.setCurrentItem(0);
        main_viewPager.setScanScroll(false);
    }

    private void update() {
        httpManger=new HttpManger(this);
        if (NetWorkUtils.isNetworkAvailable(UIUtils.getContext())){

            httpManger.updateAPK();
        }

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        int index = 0;
        switch (id) {
            case R.id.main_iv_notice:
                index = 0;
                break;
            case R.id.main_iv_exam:
                index = 1;
                break;
            case R.id.main_iv_owner:
                index = 2;
                break;
        }
        main_viewPager.setCurrentItem(index);
        mainTvSelected(index);
    }

    public void mainTvSelected(int index) {
        iv_notice.setSelected(false);
        iv_exam.setSelected(false);
        iv_owner.setSelected(false);
        if (index == 0) {
            iv_notice.setSelected(true);
            titleBar.setTitle(getResources().getString(R.string.main_title_notice));
        } else if (index == 1) {
            iv_exam.setSelected(true);
            titleBar.setTitle(getResources().getString(R.string.main_title_exam));
        } else if (index == 2) {
            iv_owner.setSelected(true);
            titleBar.setTitle(getResources().getString(R.string.main_title_owner));
        }

//        int length = lazyFragments.length;
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction tx = manager.beginTransaction();
//        for (int i = 0; i < length; i++) {
//            if (i == index) {
//                tx.show(lazyFragments[i]);
//            } else {
//                tx.hide(lazyFragments[i]);
//            }
//        }
//        tx.commit();
    }


    /**
     * 设置button显示数量小标
     *
     * @param num
     */
    public void setDisplayNum(int num) {
        if (null != badgeView) {
            if (num > 0) {
                badgeView.setText("" + num);
                badgeView.show();
            } else {
                badgeView.hide();
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mainTvSelected(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void sendData(Object object) {
        String result=object.toString();
        if (!result.equals("404")) {
            try {
                JSONObject json=new JSONObject(result);
                int resultCode = json.getInt("resultCode");
//                if (resultCode==0){
//                    Message message = new Message();
//                    message.what = 1;
//                    Bundle bundle = new Bundle();
//                    bundle.putString("jsonresult", result);
//                    message.setData(bundle);
//                    handler.sendMessage(message);
//                }
                if (resultCode==0){

                    JSONObject js;
                    try {
                        js = new JSONObject(result);
                        JSONObject jsonObject = js.getJSONObject("versionInfo");
                        forceFlag = jsonObject.getInt("forceFlag");
                        versionName = jsonObject.getString("versionName");
                        versionMark = jsonObject.getString("versionMark");
                        versionSize = jsonObject.getDouble("versionSize");
                        versionUrl = jsonObject.getString("versionUrl");
                        versionCode = jsonObject.getDouble("versionCode");
                        baseApplication = (MyApplication) getApplication();
                        baseApplication.setVersionName(versionName);
                        baseApplication.setVersionCode(versionCode);
                        baseApplication.setVersionMark(versionMark);
                        baseApplication.setVersionSize(versionSize);
                        baseApplication.setVersionUrl(versionUrl);
                        Log.i("baseApplication",baseApplication.getVersionName());
                        if (versionCode > nowCode) {
                            if (forceFlag == 0) {
                                // updateManager.showNoticeDialog(versionMark,
                                // versionUrl);
                                Intent intent = new Intent(UIUtils.getContext(), DialogActivity.class);
                                intent.putExtra("versionName", versionName);
                                intent.putExtra("versionMark", versionMark);
                                intent.putExtra("versionUrl", versionUrl);
                                intent.putExtra("versionSize", versionSize);
                                startActivity(intent);
                            }else {
                                ToastUtils.show(UIUtils.getContext(),"强制更新");
                            }
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private long time = 0;

    @Override
    public void onBackPressed() {
        long now_time = System.currentTimeMillis();
        if (now_time - time > 2000) {
            time = now_time;
            ToastUtils.show(MainActivity.this, "再次点击返回键,系统将退出");
            // UIUtils.showToastSafe("再次点击返回键,系统将退出");
        } else {
            finish();
        }
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
