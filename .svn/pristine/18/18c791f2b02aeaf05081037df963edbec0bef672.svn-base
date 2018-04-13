package net.iclassmate.teacherspace.ui.activity.single;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.ImageView;

import com.umeng.analytics.MobclickAgent;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.adapter.FragmentSingleTestAdapter;
import net.iclassmate.teacherspace.adapter.FragmentSingleTestStatistricesAdapter;
import net.iclassmate.teacherspace.bean.Statistics;
import net.iclassmate.teacherspace.bean.Student;
import net.iclassmate.teacherspace.bean.single.ObjectQuestion;
import net.iclassmate.teacherspace.bean.single.QuestionScoreDetails;
import net.iclassmate.teacherspace.bean.single.SingleAll;
import net.iclassmate.teacherspace.view.FullListView;
import net.iclassmate.teacherspace.view.TitleBar;

import java.util.ArrayList;
import java.util.List;

public class MoreActivity extends FragmentActivity implements TitleBar.TitleOnClickListener {
    private SingleAll singleAll;
    private List<Object> listStudent;
    private FragmentSingleTestAdapter adapter;
    private List<Object> listStatistics;
    private FragmentSingleTestStatistricesAdapter statistricesAdapter;

    private FullListView listView;
    private ImageView imageView;
    private TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_more);
        listView = (FullListView) findViewById(R.id.more_fulllistview);
        imageView = (ImageView) findViewById(R.id.more_image);
        titleBar = (TitleBar) findViewById(R.id.more_title_bar);
        titleBar.setLeftIcon(R.drawable.fragment_back);
        titleBar.setTitleClickListener(this);

        Intent intent = getIntent();
        int type = intent.getIntExtra("type", -1);
        int index = intent.getIntExtra("index", -1);
        singleAll = (SingleAll) intent.getSerializableExtra("single");
        loadData(type, index);
    }

    private void loadData(int type, int index) {
        if (type == 0) {
            imageView.setImageResource(R.mipmap.img_shitixiangqing);
            titleBar.setTitle("试题详情");
            setTestDetial(index);
        } else if (type == 1) {
            imageView.setImageResource(R.mipmap.img_keguantitongji);
            titleBar.setTitle("客观题统计");
            setObjectQuestion(index);
        } else {
            return;
        }
    }

    //试题详情
    private void setTestDetial(int index) {
        List<QuestionScoreDetails> listQS = singleAll.getList().get(index).getQuestionAnalyze().getListQuestionScoreDetails();
        listStudent = new ArrayList<Object>();
        Student s = new Student();
        s.setsClass("题目");
        s.setName("均分(满分)");
        s.setScore("得分率");
        s.setRankChange("难度");
        listStudent.add(s);
        for (int i = 0; i < listQS.size(); i++) {
            QuestionScoreDetails qs = listQS.get(i);
            Student student = new Student();
            student.setsClass(qs.getEqDisplayName());
            String ret = String.format("%.2f", qs.getScore());
            student.setName(ret + "(" + qs.getFullScore() + ")");

            student.setScore(qs.getEqScoreRate());

            ret = String.format("%.2f", qs.getEqDifficulty());
            student.setRankChange(ret + "( " + qs.getEqDifficultyDesc() + " )");
            listStudent.add(student);
        }
        adapter = new FragmentSingleTestAdapter(this, listStudent, true);
        listView.setAdapter(adapter);
    }

    //客观统计
    private void setObjectQuestion(int index) {
        listStatistics = new ArrayList<>();
        List<ObjectQuestion> listOQ = singleAll.getList().get(index).getQuestionAnalyze().getListObjectQuestion();
        Statistics statistics = new Statistics();
        statistics.setTitle("题目");
        statistics.setCorrectRate("正确率");
        statistics.setAnswerA("A");
        statistics.setAnswerB("B");
        statistics.setAnswerC("C");
        statistics.setAnswerD("D");
        statistics.setAnswerOther("其他");
        listStatistics.add(statistics);
        for (int i = 0; i < listOQ.size(); i++) {
            ObjectQuestion q = listOQ.get(i);
            statistics = new Statistics();
            statistics.setTitle(q.getEqDisplayName());
            statistics.setCorrectRate(q.getCorrectRate());
            statistics.setAnswerA(q.getOptionANum() + "");
            statistics.setAnswerB(q.getOptionBNum() + "");
            statistics.setAnswerC(q.getOptionCNum() + "");
            statistics.setAnswerD(q.getOptionDNum() + "");
            statistics.setAnswerOther(q.getOptionOtherNum() + "");
            statistics.setRight(q.getCorrectAnswer());
            listStatistics.add(statistics);
        }
        statistricesAdapter = new FragmentSingleTestStatistricesAdapter(this, listStatistics, true);
        listView.setAdapter(statistricesAdapter);
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
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("MoreActivity"); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this);          //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MoreActivity"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
        MobclickAgent.onPause(this);
    }
}
