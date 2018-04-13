package net.iclassmate.teacherspace.ui.activity.single;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.bean.spaper.ExamQuestionPapers;
import net.iclassmate.teacherspace.bean.spaper.StudentExamPapers;
import net.iclassmate.teacherspace.constant.Constants;
import net.iclassmate.teacherspace.utils.DataCallback;
import net.iclassmate.teacherspace.utils.LoadImage;
import net.iclassmate.teacherspace.utils.NetWorkUtils;
import net.iclassmate.teacherspace.utils.UIUtils;
import net.iclassmate.teacherspace.view.PhotoViewPager;
import net.iclassmate.teacherspace.view.TitleBar;
import net.iclassmate.teacherspace.view.ZoomImageView;
import net.iclassmate.teacherspace.view.loading.LoadingHelper;
import net.iclassmate.teacherspace.view.loading.LoadingListener;

import java.util.ArrayList;
import java.util.List;

public class StudentPaperActivity extends FragmentActivity implements TitleBar.TitleOnClickListener, LoadingListener {
    private PhotoViewPager viewPager;
    private List<ExamQuestionPapers> listPagers;
    private int width;
    private int height;
    private StudentExamPapers papers;

    private LoadingHelper loadingHelper;
    private boolean isShowLoading;
    private boolean isNoNetWork;

    //private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_student_paper);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        height = dm.heightPixels;

        Intent intent = getIntent();
        int index = intent.getIntExtra("index", -1);
        papers = (StudentExamPapers) intent.getSerializableExtra("url");


        loadingHelper = new LoadingHelper(
                findViewById(R.id.loading_prompt_relative),
                findViewById(R.id.loading_empty_prompt_linear));

        if (index == -1 || null == papers) {
            loadingHelper.ShowError("暂无试卷信息！");
            isShowLoading = true;
            return;
        }

        initView(papers, index);
        if (isShowLoading) {
            loadingHelper.HideLoading(View.INVISIBLE);
        }
    }

    private void initView(StudentExamPapers papers, int index) {
        TitleBar titleBar = (TitleBar) findViewById(R.id.student_paper_title_bar);
        titleBar.setTitle(papers.getPaperName());
        titleBar.setLeftIcon(R.drawable.fragment_back);
        titleBar.setTitleClickListener(this);
        viewPager = (PhotoViewPager) findViewById(R.id.pager_viewpager);

        listPagers = papers.getExamQuestionPapersList();
        List<String> listUrl = new ArrayList<>();
        String url = "";
        for (int i = 0; i < papers.getExamPaperUrlsList().size(); i++) {
            String fu = papers.getExamPaperUrlsList().get(i).getPrefixUrl();
            String su = papers.getExamPaperUrlsList().get(i).getPageUrl();
            if (su == null || su.equals("null") || su.equals("")) {
                Toast.makeText(StudentPaperActivity.this, "暂无试卷", Toast.LENGTH_LONG).show();
                break;
            } else {
                if (fu != null && !fu.equals("") && !fu.equals("null")) {
                    url = fu + su;
                } else {
                    url = Constants.DEFAULT_PREFIXURL + su;
                }
                listUrl.add(url);
            }
        }
        //Log.i("info", "图片网址=" + url);
        viewPager.setAdapter(new MyViewPagerAdapter(this, listUrl));
        viewPager.setCurrentItem(index);
        if (listUrl.size() >= 2) {
            viewPager.setOffscreenPageLimit(listUrl.size() - 1);
        }
    }

    @Override
    public void leftClick() {
        this.finish();
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

    private void checkState(boolean flag) {
        if (!NetWorkUtils.isNetworkAvailable(UIUtils.getContext())) {
            loadingHelper.ShowError("请检查您的网络链接！");
            isShowLoading = true;
            isNoNetWork = true;
        }

        if (flag && !isNoNetWork) {
            loadingHelper.ShowError("暂无试卷信息！");
            isShowLoading = true;
        }
    }

    class MyViewPagerAdapter extends PagerAdapter implements DataCallback {
        private Context context;
        private List<String> list;
        private LoadImage loader;

        public MyViewPagerAdapter(Context context, List<String> list) {
            this.context = context;
            this.list = list;
            loader = new LoadImage();
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(context).inflate(R.layout.activity_paper_item, null);
            ZoomImageView ret = (ZoomImageView) view.findViewById(R.id.single_zoom_iv);
            TextView tv = (TextView) view.findViewById(R.id.single_zoom_tv);
            ret.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            ret.setTag(list.get(position));
            loader.getImage(context, ret, listPagers, papers.getPersonScore(), position + 1, list.get(position), this);
            tv.setText((position + 1) + "/" + papers.getExamPaperUrlsList().size());
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void sendData(Object object) {
            if (object != null) {
                if (object instanceof Integer) {
                    Integer num = (Integer) object;
                    if (num == 404) {
                        loadingHelper.ShowError("暂无试卷信息！");
                        isShowLoading = true;
                    }
                }
            }
        }
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("StudentPaperActivity"); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this);          //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("StudentPaperActivity"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
        MobclickAgent.onPause(this);
    }
}