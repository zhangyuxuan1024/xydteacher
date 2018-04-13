package net.iclassmate.teacherspace.ui.fragment.single;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.adapter.FragmentSingleTestAdapter;
import net.iclassmate.teacherspace.adapter.FragmentSingleTestStatistricesAdapter;
import net.iclassmate.teacherspace.adapter.single.FragmentTestAdapter;
import net.iclassmate.teacherspace.bean.Statistics;
import net.iclassmate.teacherspace.bean.Student;
import net.iclassmate.teacherspace.bean.single.KnowledgePoint;
import net.iclassmate.teacherspace.bean.single.ObjectQuestion;
import net.iclassmate.teacherspace.bean.single.QstAnalyzes;
import net.iclassmate.teacherspace.bean.single.QuestionAnalyze;
import net.iclassmate.teacherspace.bean.single.QuestionDifficulty;
import net.iclassmate.teacherspace.bean.single.QuestionScoreDetails;
import net.iclassmate.teacherspace.bean.single.SingleAll;
import net.iclassmate.teacherspace.ui.activity.single.LeidtActivity;
import net.iclassmate.teacherspace.ui.activity.single.MoreActivity;
import net.iclassmate.teacherspace.ui.fragment.LazyFragment;
import net.iclassmate.teacherspace.view.CircleView;
import net.iclassmate.teacherspace.view.FullListView;

import java.util.ArrayList;
import java.util.List;

public class SingleTestFragment extends LazyFragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    private boolean isPrepared;
    private boolean isFirstLoad;

    private CircleView circleView1;
    private CircleView circleView2;
    private CircleView circleView3;
    private LinearLayout linearLayout;
    private LinearLayout linearLayout_test;
    private int width;
    private int height;

    private List<Object> listStudent;
    private FragmentSingleTestAdapter adapter;
    private List<Object> listStatistics;
    private FragmentSingleTestStatistricesAdapter statistricesAdapter;
    private List<Object> listKnowlwdge;
    private FragmentTestAdapter testAdapter;

    private List<FullListView> lists;
    private boolean flag;
    private SingleAll singleAll;

    private boolean isLoaded;
    private int tv_cur;
    private View view2;
    private View view3;
    private View view4;
    private View view5;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initCircle();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_test, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        linearLayout_test = (LinearLayout) view.findViewById(R.id.fragment_single_test_linear);
        findCircle();

        lists = new ArrayList<>();
        listKnowlwdge = new ArrayList<>();
        addView();
    }

    private void findCircle() {
        if (circleView1 != null) {
            linearLayout_test.removeViewAt(0);
        }
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_single_test_circle, null);
        linearLayout_test.addView(v, 0);

        linearLayout = (LinearLayout) v.findViewById(R.id.fragment_single_test_circle);
        linearLayout.setMinimumHeight((int) (2.5 * height));
        linearLayout.setVisibility(View.INVISIBLE);
        circleView1 = (CircleView) v.findViewById(R.id.fragment_single_circleview1);
        circleView2 = (CircleView) v.findViewById(R.id.fragment_single_circleview2);
        circleView3 = (CircleView) v.findViewById(R.id.fragment_single_circleview3);
    }

    private void initCircle() {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        width = dm.widthPixels - (int) (2 * getActivity().getResources().getDimension(R.dimen.singe_test_margin_lr));    //得到宽度
        CircleView.setR((int) (width * 0.12));
        width = width / 6;
        height = width;
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || isFirstLoad) {
            return;
        }
        isFirstLoad = true;
    }

    public void sendDataToFragment(int index, Object o) {
        if (o != null) {
            if (o instanceof SingleAll) {
                singleAll = (SingleAll) o;
                tv_cur = index;
                addData(index);
            }
        }
    }

    private void addData(int index) {
        if (isLoaded) {
            setCircle(index);
            setTestDetial(index);
            setObjectQuestion(index);
            setQstAnalyzes(index);
        } else {
            isLoaded = true;
        }
    }

    public void textViewSelectedChanged(int index) {
        if (index == tv_cur)
            return;
        findCircle();
        setCircle(index);
        setTestDetial(index);
        setObjectQuestion(index);
        setQstAnalyzes(index);

        tv_cur = index;
    }

    //添加View
    private void addView() {
        //试题详情
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_exam_listview, null);
        FullListView listView = (FullListView) view.findViewById(R.id.fragment_ListView);
        linearLayout_test.addView(view);
        ImageView imageView = (ImageView) view.findViewById(R.id.fragment_title_iv);
        imageView.setImageResource(R.mipmap.img_shitixiangqing);
        adapter = new FragmentSingleTestAdapter(getActivity());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        lists.add(listView);

        //客观统计
        view2 = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_single_test_kgtj_listview, null);
        FullListView listView2 = (FullListView) view2.findViewById(R.id.fragment_kgtj_ListView);
        linearLayout_test.addView(view2);
        imageView = (ImageView) view2.findViewById(R.id.fragment_title_kgtj_iv);
        imageView.setImageResource(R.mipmap.img_keguantitongji);
        TextView tv = (TextView) view2.findViewById(R.id.fragment_title_kgtj_tv);
        String s = "※红色代表答案正确";
        SpannableString sb = new SpannableString(s);
        sb.setSpan(new ForegroundColorSpan(Color.parseColor("#da4453")), 1, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(sb);
        statistricesAdapter = new FragmentSingleTestStatistricesAdapter(getActivity());
        listView2.setAdapter(statistricesAdapter);
        listView2.setOnItemClickListener(this);
        lists.add(listView2);
        view2.setVisibility(View.GONE);

        //客观统计 无数据
        view4 = LayoutInflater.from(getContext()).inflate(R.layout.single_no_data, null);
        imageView = (ImageView) view4.findViewById(R.id.single_no_data_img);
        imageView.setImageResource(R.mipmap.img_keguantitongji);
        TextView tvNoData = (TextView) view4.findViewById(R.id.single_no_data_tv);
        tvNoData.setText("本科考试无客观题");
        linearLayout_test.addView(view4);
        view4.setVisibility(View.GONE);

        //知识点统计
        view3 = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_test_listview, null);
        FullListView listView3 = (FullListView) view3.findViewById(R.id.fragment_ListView);
        linearLayout_test.addView(view3);
        imageView = (ImageView) view3.findViewById(R.id.fragment_test_title_iv);
        imageView.setImageResource(R.mipmap.img_zhishidiantongji);
        TextView textView = (TextView) view3.findViewById(R.id.fragment_test_leidt_tv);
        textView.setOnClickListener(this);
        flag = false;
        testAdapter = new FragmentTestAdapter(getActivity(), listKnowlwdge, flag);
        listView3.setAdapter(testAdapter);
        testAdapter.setFlag(false);
        listView3.setOnItemClickListener(this);
        lists.add(listView3);
        view3.setVisibility(View.GONE);

        //知识点统计 无数据
        view5 = LayoutInflater.from(getContext()).inflate(R.layout.single_no_data, null);
        imageView = (ImageView) view5.findViewById(R.id.single_no_data_img);
        imageView.setImageResource(R.mipmap.img_zhishidiantongji);
        tvNoData = (TextView) view5.findViewById(R.id.single_no_data_tv);
        tvNoData.setText("知识点未录入");
        linearLayout_test.addView(view5);
        view5.setVisibility(View.GONE);

        addData(tv_cur);
    }

    //设置圆，试题难度
    private void setCircle(int index) {
        QuestionDifficulty qd = singleAll.getList().get(index).getQuestionAnalyze().getQuestionDifficulty();

        linearLayout.setVisibility(View.VISIBLE);
        circleView1.setmXCenter(width);
        circleView1.setmYCenter((int) (1.25 * height));
        circleView1.setmTotalProgress((int) qd.getHardEqFullScore());
        circleView1.setCurFood(qd.getHardEqScore());
        circleView1.setTitle("难题");
        circleView1.setmLostScore("丢" + String.format("%.2f", qd.getHardEqLostScore()) + "分");

        circleView2.setmXCenter(width);
        circleView2.setmYCenter((int) (1.25 * height));
        circleView2.setmTotalProgress((int) qd.getMidEqFullScore());
        circleView2.setCurFood(qd.getMidEqScore());
        circleView2.setTitle("中等题");
        circleView2.setmLostScore("丢" + String.format("%.2f", qd.getMidEqLostScore()) + "分");

        circleView3.setmXCenter(width);
        circleView3.setmYCenter((int) (1.25 * height));
        circleView3.setmTotalProgress((int) Math.ceil(qd.getEsayEqFullScore()));
        circleView3.setCurFood(qd.getEsayEqScore());
        circleView3.setTitle("简单题");
        circleView3.setmLostScore("丢" + String.format("%.2f", qd.getEsayEqLostScore()) + "分");
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
        adapter.addData(listStudent);
        adapter.notifyDataSetChanged();
    }

    //客观统计
    private void setObjectQuestion(int index) {
        listStatistics = new ArrayList<>();
        List<ObjectQuestion> listOQ = singleAll.getList().get(index).getQuestionAnalyze().getListObjectQuestion();
        int len = listOQ.size();
        if (len > 0) {
            view2.setVisibility(View.VISIBLE);
        } else {
            view4.setVisibility(View.VISIBLE);
            return;
        }

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
        statistricesAdapter.addData(listStatistics);
        statistricesAdapter.notifyDataSetChanged();
    }

    //知识点统计
    private void setQstAnalyzes(int index) {
        listKnowlwdge.clear();

        List<QstAnalyzes> listQs = singleAll.getList().get(index).getQuestionAnalyze().getListQstAnalyzes();
        int len = listQs.size();
        if (len > 0) {
            if (len == 1) {
                String name = listQs.get(0).getQstName();
                if (name == null || name.equals("") || name.equals("null")) {
                    view5.setVisibility(View.VISIBLE);
                    return;
                }
            }
            view3.setVisibility(View.VISIBLE);
        } else {
            view5.setVisibility(View.VISIBLE);
            return;
        }
        KnowledgePoint kp = new KnowledgePoint();
        kp.setPoint("知识点");
        kp.setScore("分值");
        kp.setDegree("掌握程度");
        listKnowlwdge.add(kp);
        for (int i = 0; i < listQs.size(); i++) {
            QstAnalyzes analyzes = listQs.get(i);
            kp = new KnowledgePoint();
            kp.setPoint(analyzes.getQstName());
            kp.setScore(analyzes.getQstFullScore() + "");
            if (index == 0) {
                kp.setDegree(analyzes.getGradeProficiency() + "");
            } else {
                kp.setDegree(analyzes.getClassProficiency() + "");
                //Log.i("info","掌握程度="+analyzes.getClassProficiency()+"");
            }
            listKnowlwdge.add(kp);
        }
        notifyFragmentData(2);
    }

    @Override
    public String getFragmentTitle() {
        return "试题分析";
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        int index = lists.indexOf(adapterView);
        Log.i("info", "点击了第" + index + "个ListView的第" + i + "项");
        if (i == 6) {
            if (index == 2) {
                if (!flag) {
                    flag = !flag;
                    notifyFragmentData(index);
                }
            } else {
                Intent intent = new Intent(getActivity(), MoreActivity.class);
                intent.putExtra("index", tv_cur);
                intent.putExtra("type", index);
                intent.putExtra("single", singleAll);
                startActivity(intent);
            }
        }

        if (index == 2 && listKnowlwdge.size() == i) {
            flag = !flag;
            notifyFragmentData(index);
        }
    }

    private void notifyFragmentData(int index) {
        testAdapter = new FragmentTestAdapter(getActivity(), listKnowlwdge, flag);
        FullListView listView = lists.get(index);
        testAdapter.setFlag(flag);
        listView.setAdapter(testAdapter);
        testAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.fragment_test_leidt_tv:
                Intent intent = new Intent(getActivity(), LeidtActivity.class);
                QuestionAnalyze analyze = singleAll.getList().get(tv_cur).getQuestionAnalyze();
                intent.putExtra("analyze", analyze);
                if (tv_cur == 0) {
                    intent.putExtra("flag", false);
                } else {
                    intent.putExtra("flag", true);
                }
                startActivity(intent);
                break;
        }
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("SingleReportActivity_SingleTestFragment"); //统计页面，"MainScreen"为页面名称，可自定义
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("SingleReportActivity_SingleTestFragment");
    }
}