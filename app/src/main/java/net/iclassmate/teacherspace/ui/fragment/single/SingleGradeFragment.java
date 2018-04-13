package net.iclassmate.teacherspace.ui.fragment.single;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ValueFormatter;
import com.umeng.analytics.MobclickAgent;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.adapter.FragmentSingleTestFAdapter;
import net.iclassmate.teacherspace.adapter.single.FragmentBarDataAdapter;
import net.iclassmate.teacherspace.bean.Student;
import net.iclassmate.teacherspace.bean.single.ClassNum;
import net.iclassmate.teacherspace.bean.single.ClassScore;
import net.iclassmate.teacherspace.bean.single.ClassValue;
import net.iclassmate.teacherspace.bean.single.ExcellentPersons;
import net.iclassmate.teacherspace.bean.single.RatesAndScores;
import net.iclassmate.teacherspace.bean.single.SingleAll;
import net.iclassmate.teacherspace.ui.fragment.LazyFragment;
import net.iclassmate.teacherspace.view.FullListView;
import net.iclassmate.teacherspace.view.LineView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SingleGradeFragment extends LazyFragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private RadioButton rb1, rb2, rb3;
    private LinearLayout linearLayout;

    private List<Object> listClassValue;
    private List<Object> listClassRate;
    private List<Object> listClassNum;
    private List<Object> listStudent;
    private List<FullListView> listViews;
    private FragmentSingleTestFAdapter adapter;
    private boolean[] data;
    private int cur;
    private FullListView listView;
    private FullListView listViewXbtj;
    private FragmentBarDataAdapter adapterXbtj;

    private BarChart mBarChart;
    private BarData mBarData;

    private SingleAll singleAll;
    private boolean isLoaded;
    private List<String> listClass;
    private List<Integer> listCount;
    private List<Double> listAvg;
    private List<String> listScale;
    private boolean flag;
    private LineView lineView;
    private int studentCount;
    private Context context;
    private int max;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_grade, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        linearLayout = (LinearLayout) view.findViewById(R.id.fragment_single_grade_linear);
        cur = 0;
        data = new boolean[4];
        listViews = new ArrayList<>();
        studentCount = 10;
        listClass = new ArrayList<>();
        listCount = new ArrayList<>();
        listAvg = new ArrayList<>();
        listScale = new ArrayList<>();
        context = getContext();

        addView();
        addData();
    }

    private void addView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_grade_listview, null);
        listView = (FullListView) view.findViewById(R.id.fragment_grade_ListView);
        ImageView imageView = (ImageView) view.findViewById(R.id.fragment_grade_title_iv);
        imageView.setImageResource(R.mipmap.img_sanlvyifen);
        adapter = new FragmentSingleTestFAdapter(getActivity());
        linearLayout.addView(view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listViews.add(listView);
        initRG(view);

        addBarChart();
    }

    private void addData() {
        if (isLoaded) {
            setRatesAndScores();
            setExcellent();
            notifyBarChart(flag);
        } else {
            isLoaded = true;
        }
    }

    @Override
    protected void lazyLoad() {
    }

    public void sendDataToFragment(int index, Object o) {
        if (o != null) {
            if (o instanceof SingleAll) {
                singleAll = (SingleAll) o;
                addData();
            }
        }
    }

    //三率一分
    private void setRatesAndScores() {
        listClassValue = new ArrayList<>();
        listClassRate = new ArrayList<>();
        listClassNum = new ArrayList<>();

        ClassValue cv = new ClassValue();
        cv.setsClass("班级");
        cv.setAvg("均分");
        cv.setExcellentRate("优秀率");
        cv.setPassRate("及格率");
        cv.setLowRate("低分率");
        listClassValue.add(cv);

        ClassScore cs = new ClassScore();
        cs.setsClass("班级");
        cs.setAvg("均分排名");
        cs.setExcellentRate("优秀排名");
        cs.setPassRate("及格排名");
        cs.setLowRate("低分排名");
        listClassRate.add(cs);

        ClassNum cn = new ClassNum();
        cn.setsClass("班级");
        cn.setAvg("总人数");
        cn.setExcellentRate("优秀人数");
        cn.setPassRate("及格人数");
        cn.setLowRate("低分人数");
        listClassNum.add(cn);

        List<RatesAndScores> listRAS = singleAll.getList().get(0).getGradeSituation().getListRatesAndScores();
        for (int i = 0; i < listRAS.size(); i++) {
            cv = new ClassValue();
            cs = new ClassScore();
            cn = new ClassNum();
            RatesAndScores scores = listRAS.get(i);

            cv.setsClass(scores.getClassName());
            cv.setAvg(String.format("%.2f", scores.getAvgScore()));
            cv.setExcellentRate(scores.getExcellentRate());
            cv.setPassRate(scores.getPassRate());
            cv.setLowRate(scores.getLowRate());
            listClassValue.add(cv);

            cs.setsClass(scores.getClassName());
            if (i == listRAS.size() - 1) {
                cs.setAvg("--");
                cs.setExcellentRate("--");
                cs.setPassRate("--");
                cs.setLowRate("--");
            } else {
                cs.setAvg(scores.getAvgScoreOrder() + "");
                cs.setExcellentRate(scores.getExcellentOrder() + "");
                cs.setPassRate(scores.getPassOrder() + "");
                cs.setLowRate(scores.getLowOrder() + "");
            }
            listClassRate.add(cs);

            cn.setsClass(scores.getClassName());
            cn.setAvg(scores.getExamNum() + "");
            cn.setExcellentRate(scores.getExcellentNum() + "");
            cn.setPassRate(scores.getPassNum() + "");
            cn.setLowRate(scores.getLowNum() + "");
            listClassNum.add(cn);
        }
        setData(cur);
    }

    //学霸统计柱形图
    private void setExcellent() {
        // addBarChart();
        upData();
        lineView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        upData();
                        break;
                }
                return false;
            }
        });
    }

    private void upData() {
        listStudent = new ArrayList<>();
        String text = lineView.getText();
        if (text != null) {
            studentCount = Integer.parseInt(text);
        }
        final List<ExcellentPersons> listEP = singleAll.getList().get(0).getGradeSituation().getListExcellentPersons();
        Student s = new Student();
        s.setsClass("班级");
        s.setName("班级均分");
        s.setScore("前" + studentCount + "名");
        s.setRankChange("所占比例");
        listStudent.add(s);
        getCalResult(listEP, studentCount);
        int len = listClass.size();
        for (int i = 0; i < len; i++) {
            Student student = new Student();
            ExcellentPersons persons = listEP.get(i);
            student.setsClass(listClass.get(i));
            student.setName(String.format("%.2f", listAvg.get(i)));
            student.setScore(listCount.get(i) + "");
            student.setRankChange(listScale.get(i));
            listStudent.add(student);
        }
        adapterXbtj = new FragmentBarDataAdapter(getActivity(), listStudent);
        adapterXbtj.setFlag(flag);
        listViewXbtj.setAdapter(adapterXbtj);

        setBarData();
    }

    private void setData(int index) {
        if (index == 0) {
            rb1.setChecked(true);
        } else if (index == 1) {
            rb2.setChecked(true);
        } else if (index == 2) {
            rb3.setChecked(true);
        }
        if (cur == 0) {
            adapter.setData(listClassValue);
        } else if (cur == 1) {
            adapter.setData(listClassRate);
        } else if (cur == 2) {
            adapter.setData(listClassNum);
        }
        adapter.setFlag(data[cur]);
        adapter.notifyDataSetChanged();
    }

    //学霸统计柱形图
    private void addBarChart() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_grade_listview_xbtj, null);
        linearLayout.addView(view);
        lineView = (LineView) view.findViewById(R.id.fragment_single_grade_lineview_s);
        mBarChart = (BarChart) view.findViewById(R.id.fragment_single_grade_barchart);
        listViewXbtj = (FullListView) view.findViewById(R.id.fragment_grade_xbtj_ListView);
        flag = false;
        listViews.add(listViewXbtj);
        listViewXbtj.setOnItemClickListener(this);

//        MyMarkView markView = new MyMarkView(getContext(), R.layout.my_mark_view);
//        mBarChart.setMarkerView(markView);
//        mBarChart.getMarkerView().setEnabled(true);
    }

    private void setBarData() {
        //柱形图数据
        mBarData = getBarData(listClass.size());
        //显示柱形图
        showBarChart(mBarChart, mBarData);
    }

    private void showBarChart(BarChart barChart, BarData barData) {
        barChart.setDrawBorders(false);  ////是否在折线图上添加边框
        barChart.setDescription("人数");// 数据描述
        barChart.setDescriptionColor(ContextCompat.getColor(getActivity(),R.color.app_color));
        barChart.setDescriptionTextSize(context.getResources().getDimension(R.dimen.tv_3));
        barChart.setDescriptionPosition(context.getResources().getDimension(R.dimen.view_30), context.getResources().getDimension(R.dimen.view_7));
        // 如果没有数据的时候，会显示这个，类似ListView的EmptyView
        barChart.setNoDataTextDescription("You need to provide data for the chart.");
        barChart.setDrawGridBackground(true); // 是否显示表格颜色
        barChart.setGridBackgroundColor(Color.parseColor("#FFD9EAF9")); // 表格的的颜色，在这里是是给颜色设置一个透明度

        barChart.setTouchEnabled(true); // 设置是否可以触摸
        barChart.setDragEnabled(true);// 是否可以拖拽
        barChart.setScaleEnabled(false);// 是否可以缩放
        barChart.setHighlightEnabled(false);

        barChart.setPinchZoom(true);
        barChart.setDrawBarShadow(false);
        barChart.setData(barData); // 设置数据

        Legend mLegend = barChart.getLegend(); // 设置比例图标示
        mLegend.setEnabled(false);

        // X轴设定
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //xAxis.setEnabled(true);
        //立即执行的动画
        barChart.animateY(3000);

        //隐藏X轴竖网格线
        xAxis.setDrawGridLines(false);
        xAxis.setTextColor(ContextCompat.getColor(getActivity(),R.color.app_color));
//        String text = xAxis.getLongestLabel();
        //Log.i("info","text,最大长度="+text);
        xAxis.setTextSize(context.getResources().getDimension(R.dimen.tv_3));
        xAxis.setDrawAxisLine(false);           //设置显示x轴
        xAxis.setSpaceBetweenLabels(0); // 设置x轴label不间隔
        xAxis.setLabelsToSkip(0);
        barChart.setVisibleXRangeMaximum(7.5f);
        barChart.setVisibleXRangeMinimum(7.5f);

        //隐藏左边坐标轴横网格线
        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setTextColor(ContextCompat.getColor(getActivity(),R.color.app_color));
        leftAxis.setTextSize(context.getResources().getDimension(R.dimen.tv_3));
        leftAxis.setValueFormatter(getValueFormatter());
        resetBarData(leftAxis, max);

        //隐藏右边坐标轴横网格线
        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setTextColor(ContextCompat.getColor(getActivity(),R.color.app_color));
        rightAxis.setTextSize(context.getResources().getDimension(R.dimen.tv_3));
        rightAxis.setValueFormatter(getValueFormatter());
        resetBarData(rightAxis, max);

        barChart.invalidate();
    }

    private BarData getBarData(int count) {
        ArrayList<String> xValues = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            String s = listClass.get(i);
            if (s.length() > 5) {
                s = s.substring(s.length() - 5);
            }
            xValues.add(s);
        }

        max = -1;
        ArrayList<BarEntry> yValues = new ArrayList<BarEntry>();
        for (int i = 0; i < count; i++) {
            yValues.add(new BarEntry(listCount.get(i), i));
            if (listCount.get(i) > max) {
                max = listCount.get(i);
            }
        }

        // y轴的数据集合
        BarDataSet barDataSet = new BarDataSet(yValues, "测试图");
        barDataSet.setColor(ContextCompat.getColor(getActivity(),R.color.app_color));
        barDataSet.setValueTextColor(ContextCompat.getColor(getActivity(),R.color.app_color));
        barDataSet.setValueTextSize(context.getResources().getDimension(R.dimen.tv_3));

        ArrayList<BarDataSet> barDataSets = new ArrayList<BarDataSet>();
        barDataSets.add(barDataSet);
        BarData barData = new BarData(xValues, barDataSets);
        barData.setDrawValues(true);
        barData.setValueFormatter(getValueFormatter());
        return barData;
    }

    private void notifyBarChart(boolean flag) {
        adapterXbtj = new FragmentBarDataAdapter(getContext(), listStudent);
        adapterXbtj.setFlag(flag);
        listViewXbtj.setAdapter(adapterXbtj);
    }

    private void initRG(View view) {
        rb1 = (RadioButton) view.findViewById(R.id.single_grade_rb1);
        rb2 = (RadioButton) view.findViewById(R.id.single_grade_rb2);
        rb3 = (RadioButton) view.findViewById(R.id.single_grade_rb3);

        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);
        rb3.setOnClickListener(this);
    }

    @Override
    public String getFragmentTitle() {
        return "年级情况";
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.single_grade_rb1:
                cur = 0;
                break;
            case R.id.single_grade_rb2:
                cur = 1;
                break;
            case R.id.single_grade_rb3:
                cur = 2;
                break;
        }
        setData(cur);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        int index = listViews.indexOf(adapterView);
        Log.i("info", "点击了第" + i + "项，list长度=" + listClassRate.size() + "，第" + cur + "个listview,index=" + index);
        if (index == 0) {
            if (i == 5 && !data[cur]) {
                data[cur] = true;
            }
        }

        if (index == 0) {
            if (cur == 0 && listClassValue.size() == i) {
                data[cur] = false;
            } else if (cur == 1 && listClassRate.size() == i) {
                data[cur] = false;
            } else if (cur == 2 && listClassNum.size() == i) {
                data[cur] = false;
            }
            setData(cur);
        } else if (index == 1) {
            if (i == 4 && !flag) {
                flag = true;
            } else if (listStudent.size() == i) {
                flag = false;
            }
            notifyBarChart(flag);
        }
    }

    private void getCalResult(List<ExcellentPersons> list, int n) {
        listClass.clear();
        listCount.clear();
        listAvg.clear();
        listScale.clear();

        int classId = 0;
        int temp = 0;
        int len = list.size();
        int count_p = 0;
        ExcellentPersons[] p = new ExcellentPersons[len];
        for (int i = 0; i < len; i++) {
            p[i] = list.get(i);
            int order = p[i].getGradeOrder();
            if (order <= n) {
                count_p++;
            } else {
                break;
            }
        }

        ExcellentPersons persons;
        for (int i = 0; i < count_p; i++) {
            for (int j = 0; j < count_p - 1 - i; j++) {
                classId = Integer.parseInt(p[j].getClassId());
                temp = Integer.parseInt(p[j + 1].getClassId());
                if (classId > temp) {
                    persons = p[j];
                    p[j] = p[j + 1];
                    p[j + 1] = persons;
                }
            }
        }

        for (int i = 0; i < count_p; i++) {
            String name = p[i].getClassName();
            if (!listClass.contains(name)) {
                listClass.add(name);
            }
        }

        for (int i = 0; i < listClass.size(); i++) {
            int m = 0;
            double avg = 0;
            for (int j = 0; j < count_p; j++) {
                String name = p[j].getClassName();
                if (name.equals(listClass.get(i))) {
                    m++;
                    avg = p[j].getClassAvgScore();
                }
            }
            listCount.add(m);
            listAvg.add(avg);
            listScale.add((int) Math.rint(listCount.get(i) * 100 * 1.0 / count_p) + "%");
        }
    }

    public ValueFormatter getValueFormatter() {
        ValueFormatter vf = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                DecimalFormat decimalFormat = new DecimalFormat("0");
                String s = decimalFormat.format(value);
                return s;
            }
        };
        return vf;
    }

    public void resetBarData(YAxis axis, int max) {
        if (max == 5) {
            axis.setLabelCount(5, false);
        } else if (max == 4) {
            axis.setLabelCount(4, false);
        } else if (max == 3) {
            axis.setLabelCount(3, false);
        } else if (max == 2) {
            axis.setLabelCount(2, false);
        } else if (max == 1) {
            axis.setLabelCount(1, false);
        } else if (max == 0) {
            axis.setLabelCount(0, false);
        } else {
            axis.resetAxisMinValue();
        }
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("SingleReportActivity_SingleGradeFragment"); //统计页面，"MainScreen"为页面名称，可自定义
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("SingleReportActivity_SingleGradeFragment");
    }
}