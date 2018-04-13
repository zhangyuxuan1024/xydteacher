package net.iclassmate.teacherspace.ui.activity.single;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.bean.Student;
import net.iclassmate.teacherspace.bean.spaper.StudentExam;
import net.iclassmate.teacherspace.bean.spaper.StudentExamPapers;
import net.iclassmate.teacherspace.utils.DataCallback;
import net.iclassmate.teacherspace.utils.HttpManger;
import net.iclassmate.teacherspace.utils.NetWorkUtils;
import net.iclassmate.teacherspace.utils.UIUtils;
import net.iclassmate.teacherspace.view.MyScoreView;
import net.iclassmate.teacherspace.view.TitleBar;
import net.iclassmate.teacherspace.view.loading.LoadingHelper;
import net.iclassmate.teacherspace.view.loading.LoadingListener;

public class StudentTestActivity extends FragmentActivity implements TitleBar.TitleOnClickListener,
        DataCallback, LoadingListener, View.OnClickListener {
    private TitleBar titleBar;
    private TextView tv, tv_score;
    private ImageView imageView;
    private LinearLayout linearLayout;
    private Student student;

    private LoadingHelper loadingHelper;
    private boolean isShowLoading;
    private boolean isNoNetWork;
    private StudentExam studentExam;
    private long last_click_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_test_paper);
        initView();
        Intent intent = getIntent();
        student = (Student) intent.getSerializableExtra("student");

        loadingHelper = new LoadingHelper(
                findViewById(R.id.loading_prompt_relative),
                findViewById(R.id.loading_empty_prompt_linear));

        checkState(false);
        if (!isShowLoading) {
            loadingHelper.ShowLoading();
            loadingHelper.SetListener(this);
            isShowLoading = true;
        }
        HttpManger manger = new HttpManger(this);
        manger.getTestPaper(student.getSchoolId() + "", student.getMeId() + "", student.getSeId() + "", student.getStudentId() + "");
    }

    private void initView() {
        titleBar = (TitleBar) findViewById(R.id.paper_title_bar);
        titleBar.setLeftIcon(R.drawable.fragment_back);
        titleBar.setTitleClickListener(this);
        linearLayout = (LinearLayout) findViewById(R.id.test_pager_linearlayout);
        tv = (TextView) findViewById(R.id.test_paper_tv);
        tv_score = (TextView) findViewById(R.id.test_paper_score_tv);
        imageView = (ImageView) findViewById(R.id.test_paper_iv);
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
    public void sendData(Object object) {
        if (object != null) {
            if (object instanceof StudentExam) {
                if (isShowLoading) {
                    loadingHelper.HideLoading(View.INVISIBLE);
                }

                studentExam = (StudentExam) object;
                titleBar.setTitle(student.getName());
                tv.setText(studentExam.getStudentExamPapersList().get(0).getPaperName());
                int courseId = studentExam.getStudentExamPapersList().get(0).getCourseId();
                setImage(courseId);
                String score = "成绩：" + student.getScore() + "分";
                SpannableString sb = new SpannableString(score);
                sb.setSpan(new ForegroundColorSpan(Color.parseColor("#999999")), 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                sb.setSpan(new ForegroundColorSpan(Color.parseColor("#f83045")), 3, score.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tv_score.setText(sb);
                tv_score.setVisibility(View.INVISIBLE);

                int len = studentExam.getStudentExamPapersList().get(0).getExamPaperUrlsList().size();
                for (int i = 0; i < len; i++) {
                    View view = LayoutInflater.from(this).inflate(R.layout.single_paper_item, null);
                    ImageView iv = (ImageView) view.findViewById(R.id.single_paper_item_iv);
                    TextView tv = (TextView) view.findViewById(R.id.single_paper_item_tv);
                    MyScoreView scoreView = (MyScoreView) view.findViewById(R.id.single_paper_score);
                    scoreView.setScore(student.getScore());
                    if (i == 0) {
                        scoreView.setVisibility(View.VISIBLE);
                    } else {
                        scoreView.setVisibility(View.GONE);
                    }
                    iv.setTag(i);
                    iv.setOnClickListener(this);
                    tv.setText("第( " + (i + 1) + " )页");
                    linearLayout.addView(view);
                }
            } else {
                checkState(true);
            }
        } else {
            checkState(true);
        }
    }

    @Override
    public void onClick(View view) {
        int n = (int) view.getTag();
        StudentExamPapers papers = studentExam.getStudentExamPapersList().get(0);
        String url = papers.getExamPaperUrlsList().get(n).getPageUrl();
        if (url == null || url.equals("") || url.equals("null")) {
            if (System.currentTimeMillis() - last_click_time >= 2000) {
                Toast.makeText(this, "暂无试卷信息！", Toast.LENGTH_SHORT).show();
                last_click_time = System.currentTimeMillis();
            }
        } else {
            Intent intent = new Intent(this, StudentPaperActivity.class);
            intent.putExtra("index", n);
            intent.putExtra("url", papers);
            startActivity(intent);
        }
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

    @Override
    public void OnRetryClick() {

    }

    public void setImage(int id) {
        if (id == 201) {
            imageView.setImageResource(R.mipmap.yuwen);
        } else if (id == 202) {
            imageView.setImageResource(R.mipmap.shuxue);
        } else if (id == 203) {
            imageView.setImageResource(R.mipmap.yingyu);
        } else if (id == 204) {
            imageView.setImageResource(R.mipmap.wuli);
        } else if (id == 205) {
            imageView.setImageResource(R.mipmap.huaxue);
        } else if (id == 206) {
            imageView.setImageResource(R.mipmap.shengwu);
        } else if (id == 207) {
            imageView.setImageResource(R.mipmap.zhengzhi);
        } else if (id == 208) {
            imageView.setImageResource(R.mipmap.lishi);
        } else if (id == 209) {
            imageView.setImageResource(R.mipmap.dili);
        } else if (id == 210) {
            imageView.setImageResource(R.mipmap.wenzong);
        } else if (id == 211) {
            imageView.setImageResource(R.mipmap.lizong);
        } else if (id == 212) {
            imageView.setImageResource(R.mipmap.yinyue);
        } else if (id == 213) {
            imageView.setImageResource(R.mipmap.xinxi);
        } else if (id == 214) {
            imageView.setImageResource(R.mipmap.meishu);
        } else if (id == 216) {
            imageView.setImageResource(R.mipmap.tiyu);
        } else if (id == 221) {
            imageView.setImageResource(R.mipmap.sixiangpinde);
        } else if (id == 222) {
            imageView.setImageResource(R.mipmap.kexue);
        } else {
            imageView.setImageResource(R.mipmap.moren);
        }
        ;
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("StudentTestActivity"); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this);          //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("StudentTestActivity"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
        MobclickAgent.onPause(this);
    }
}

