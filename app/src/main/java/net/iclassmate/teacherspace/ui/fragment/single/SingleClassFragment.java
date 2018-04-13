package net.iclassmate.teacherspace.ui.fragment.single;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import net.iclassmate.teacherspace.adapter.single.FragmentBarDataAdapter;
import net.iclassmate.teacherspace.adapter.single.FragmentClassAdapter;
import net.iclassmate.teacherspace.bean.Student;
import net.iclassmate.teacherspace.bean.single.GradeReport;
import net.iclassmate.teacherspace.bean.single.ScoreReports;
import net.iclassmate.teacherspace.bean.single.Single;
import net.iclassmate.teacherspace.bean.single.SingleAll;
import net.iclassmate.teacherspace.ui.fragment.LazyFragment;
import net.iclassmate.teacherspace.view.FullListView;
import net.iclassmate.teacherspace.view.LineViewStudent;
import net.iclassmate.teacherspace.view.StateView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SingleClassFragment extends LazyFragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    private LinearLayout linearLayout;
    private List<Object> listStudent;
    private FragmentClassAdapter adapterCjd;
    private FullListView listViewCjd;
    private boolean[] listState;

    private BarChart mBarChart;
    private BarData mBarData;
    private FullListView listViewStudent;
    private FragmentBarDataAdapter adapterStudent;
    private List<Object> listStudentBarData;
    private List<FullListView> listViews;
    private boolean isLoaded;
    private SingleAll singleAll;

    private LineViewStudent lineView;
    private int score;
    private List<Integer> listCount;
    private List<String> listScore;
    private List<Integer> listLj;
    private int tv_cur;

    private StateView stateView;
    private List<Double> listPointX;
    private List<Double> listPointY;
    private Context context;
    private  FragmentActivity activity;
    private int max;

    private View titleView;
    private TextView tv1, tv2, tv3, tv4;
    private ImageView iv1, iv2;
    private LinearLayout linearLayout1, linearLayout2;
    private boolean tv1Flag;
    private boolean tv23Flag;
    private boolean tv4Flag;
    //private long last_name_click;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_class, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        linearLayout = (LinearLayout) view.findViewById(R.id.fragment_single_class_linearlayout);
        listState = new boolean[2];
        listViews = new ArrayList<>();
        listStudent = new ArrayList<>();
        listStudentBarData = new ArrayList<>();
        score = 10;
        listCount = new ArrayList<>();
        listScore = new ArrayList<>();
        listLj = new ArrayList<>();

        listPointX = new ArrayList<>();
        listPointY = new ArrayList<>();
        context = getContext();
        activity = getActivity();
        addView();
        addViewData(tv_cur);

        tv1Flag = false;
        tv23Flag = true;
        tv4Flag = false;
    }

    private void addView() {
        //成绩单
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_exam_listview_cjd, null);
        linearLayout.addView(view);
        listViewCjd = (FullListView) view.findViewById(R.id.fragment_ListView_cjd);
        ImageView imageView = (ImageView) view.findViewById(R.id.fragment_title_iv_cjd);
        imageView.setImageResource(R.mipmap.img_chengjidan);
        adapterCjd = new FragmentClassAdapter(activity, listStudent);

        titleView = LayoutInflater.from(getContext()).inflate(R.layout.single_class_cjd_title, null);
        listViewCjd.addHeaderView(titleView);
        listViewCjd.setAdapter(adapterCjd);
        listViewCjd.setOnItemClickListener(this);
        listState[0] = false;
        listViews.add(listViewCjd);
        initTitleView(titleView);

        //学生分布
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_class_listview_xsfb, null);
        linearLayout.addView(view);
        mBarChart = (BarChart) view.findViewById(R.id.fragment_single_class_barchart);
        listViewStudent = (FullListView) view.findViewById(R.id.fragment_grade_xbtj_ListView);
        lineView = (LineViewStudent) view.findViewById(R.id.fragment_single_class_lineview);
        imageView = (ImageView) view.findViewById(R.id.fragment_title_iv);
        imageView.setImageResource(R.mipmap.img_xueshengfenbu);
        adapterStudent = new FragmentBarDataAdapter(activity, listStudentBarData);
        listViewStudent.setAdapter(adapterStudent);
        listViews.add(listViewStudent);
        listViewStudent.setOnItemClickListener(this);
        listState[1] = false;
        lineView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        String text = lineView.getText();
                        text = text.substring(0, text.length() - 1);
                        score = Integer.parseInt(text);
                        setStudent(tv_cur);
                        break;
                }
                return false;
            }
        });

        //状态诊断
        addStateView(true);
    }

    public void initTitleView(View view) {
        tv1 = (TextView) titleView.findViewById(R.id.fragment_single_title_tv_1);
        tv2 = (TextView) titleView.findViewById(R.id.fragment_single_title_tv_2);
        tv3 = (TextView) titleView.findViewById(R.id.fragment_single_title_tv_3);
        tv4 = (TextView) titleView.findViewById(R.id.fragment_single_title_tv_4);
        iv1 = (ImageView) titleView.findViewById(R.id.fragment_single_title_iv_1);
        iv2 = (ImageView) titleView.findViewById(R.id.fragment_single_title_iv_2);
        linearLayout1 = (LinearLayout) titleView.findViewById(R.id.fragment_single_title_linear_1);
        linearLayout2 = (LinearLayout) titleView.findViewById(R.id.fragment_single_title_linear_2);

        tv1.setText("姓名");
        tv2.setText("分数");
        tv3.setText("班级排名");
        tv4.setText("进退步");
        iv1.setImageResource(R.mipmap.ic_shanglv);
        iv2.setImageResource(R.mipmap.ic_quanhui);
        tv1.setTextColor(Color.parseColor("#1698ff"));
        tv2.setTextColor(Color.parseColor("#1698ff"));
        tv3.setTextColor(Color.parseColor("#1698ff"));
        tv4.setTextColor(Color.parseColor("#1698ff"));
        tv1.setTextSize(16);
        tv2.setTextSize(16);
        tv3.setTextSize(16);
        tv4.setTextSize(16);
//        tv1.setOnClickListener(this);
//        tv2.setOnClickListener(this);
//        tv3.setOnClickListener(this);
//        tv4.setOnClickListener(this);
        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
    }

    @Override
    protected void lazyLoad() {
    }

    public void sendDataToFragment(int index, Object o) {
        if (o != null) {
            if (o instanceof SingleAll) {
                singleAll = (SingleAll) o;
                tv_cur = index;
                if (index == 0) {
                    tv_cur = 1;
                }
                addViewData(index);
            }
        }
    }

    public void textViewSelectedChanged(int index) {
        if (index == 0) return;
        if (index == tv_cur) return;
        if (singleAll == null) return;
        tv_cur = index;
        addViewData(index);
    }

    private void addViewData(int index) {
        if (index == 0) {
            index = 1;
            tv_cur = index;
        }
        tv1Flag = false;
        tv23Flag = true;
        tv4Flag = false;

        if (iv1 != null && iv2 != null) {
            iv1.setImageResource(R.mipmap.ic_shanglv);
            iv2.setImageResource(R.mipmap.ic_quanhui);
        }

        if (isLoaded) {
            setScore(index);
            setStudent(index);
        } else {
            isLoaded = true;
        }
    }

    //成绩单
    public void setScore(int index) {
        listPointX.clear();
        listPointY.clear();
        listStudent.clear();

        if (singleAll.getList() == null || singleAll.getList().size() < 2) return;
        List<ScoreReports> list = singleAll.getList().get(index).getClassSituation().getList();
        Single single = singleAll.getList().get(index);
        GradeReport s = null;
        for (int i = 0; i < list.size(); i++) {
            ScoreReports score = list.get(i);
            s = new GradeReport();
            s.setSchoolId(single.getSchoolId());
            s.setMeId(single.getMeId());
            s.setSeId(single.getSeId());
            s.setStudentId(score.getStudentId());
            s.setsId(score.getStudentId() + "");
            s.setName(score.getStudentName());
            int state = score.getEssStatus();
            s.setEssStatus(state);
            if (state == 0) {
                s.setScore(String.format("%.1f", score.getScore()));
                s.setClassOrder(score.getClassOrder() + "");
                String str = score.getOffsetOrder();
                if (str != null && !str.equals("--") && !str.equals("--")) {
                    int n = Integer.parseInt(str);
                    int m = Math.abs(n);
                    if (n > 0) {
                        s.setRankChange("↑" + m);
                    } else if (n < 0) {
                        s.setRankChange("↓" + m);
                    } else {
                        s.setRankChange(m + "");
                    }
                } else {
                    s.setRankChange("--");
                }

                //double x = score.getCarelessIndex();
                double a = score.getCarelessIndex();
                if (a >= 0.99) {
                    a = 0.99;
                }
                listPointX.add(a);
                String py = score.getScoreRate();
                py = py.substring(0, py.length() - 1);
                double y = Double.parseDouble(py);
                a = y / 100;
                if (a >= 0.99) {
                    a = 0.99;
                }
                listPointY.add(a);
                //Log.i("info", "x=" + x + ",y=" + a);
            } else if (state == 1) {
                s.setScore("缺考");
                s.setClassOrder("--");
                s.setRankChange("--");
            } else if (state == 2) {
                if (score.getScore() > 0) {
                    s.setScore(score.getScore() + "");
                } else {
                    s.setScore("违纪");
                }
                s.setClassOrder("--");
                s.setRankChange("--");
            }
            listStudent.add(s);
        }
        notifyData(listState[0]);
        if (stateView != null) {
            addStateView(false);
            stateView.setFlag(false);
            stateView.setmPointX(listPointX);
            stateView.setmPointY(listPointY);
        }
    }

    //学生分布
    public void setStudent(int index) {
        if (singleAll.getList() == null || singleAll.getList().size() < 2) return;
        List<ScoreReports> list = singleAll.getList().get(tv_cur).getClassSituation().getList();
        if (list.size() > 0) {
            upListData();
            setBarData();
        }
    }

    private void upListData() {
        listStudentBarData.clear();
        if (singleAll == null || singleAll.getList().size() <= 0) {
            return;
        }
        List<ScoreReports> list = singleAll.getList().get(tv_cur).getClassSituation().getList();
        Student s = new Student();
        s.setsClass("分数段");
        s.setName(singleAll.getList().get(tv_cur).getClassName());
        s.setScore("所占比例");
        s.setRankChange("累加");
        listStudentBarData.add(s);
        int fullScore = list.get(0).getFullScore();
        getCalResult(fullScore, list);
        for (int i = 0; i < listScore.size(); i++) {
            Student student = new Student();
            student.setsClass(listScore.get(i));
            student.setName(listCount.get(i) + "");
            student.setScore((int) Math.rint(listCount.get(i) * 100 * 1.0 / list.size()) + "%");
            student.setRankChange(listLj.get(i) + "");
            listStudentBarData.add(student);
        }
        notifyBarChart(listState[1]);
    }

    private void notifyData(boolean flag) {
        adapterCjd = new FragmentClassAdapter(getActivity(), listStudent);
        adapterCjd.setFlag(flag);
        listViewCjd.setAdapter(adapterCjd);
    }

    @Override
    public String getFragmentTitle() {
        return "班级情况";
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        int index = listViews.indexOf(adapterView);
        Log.i("info", "点击了第" + index + "listview,第+" + i + "项");
        if (index == 0 && i == 11 && !listState[index]) {
            listState[index] = true;
        } else if (index == 1 && i == 4 && !listState[index]) {
            listState[index] = true;
        }

        if (index == 0) {
            if (i == listStudent.size() + 1) {
                listState[0] = false;
            }
            notifyData(listState[0]);
        } else if (index == 1) {
            if (i == listStudentBarData.size()) {
                listState[1] = false;
            }
            notifyBarChart(listState[1]);
        }
    }

    private void setBarData() {
        mBarData = getBarData(listCount.size());
        showBarChart(mBarChart, mBarData);
    }

    private void showBarChart(BarChart barChart, BarData barData) {
        barChart.setDrawBorders(false);  ////是否在折线图上添加边框
        barChart.setDescription("人数");// 数据描述
        barChart.setDescriptionColor(ContextCompat.getColor(getActivity(),R.color.app_color));
        barChart.setDescriptionTextSize(context.getResources().getDimension(R.dimen.tv_3));
        barChart.setDescriptionPosition(context.getResources().getDimension(R.dimen.view_30), context.getResources().getDimension(R.dimen.view_7));
        barChart.setNoDataTextDescription("You need to provide data for the chart.");
        barChart.setDrawGridBackground(true); // 是否显示表格颜色
        barChart.setGridBackgroundColor(Color.parseColor("#FFD9EAF9")); // 表格的的颜色，在这里是是给颜色设置一个透明度

        barChart.setTouchEnabled(true); // 设置是否可以触摸
        barChart.setDragEnabled(true);// 是否可以拖拽
        barChart.setScaleEnabled(false);// 是否可以缩放
        barChart.setHighlightEnabled(false);

        barChart.setPinchZoom(true);//
        barChart.setDrawBarShadow(false);
        barChart.setData(barData); // 设置数据

        Legend mLegend = barChart.getLegend(); // 设置比例图标示
        mLegend.setEnabled(false);

        // X轴设定
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setEnabled(true);
        //立即执行的动画
        barChart.animateY(3000);
        //隐藏X轴竖网格线
        xAxis.setDrawGridLines(false);
        xAxis.setTextColor(ContextCompat.getColor(getActivity(),R.color.app_color));
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
        leftAxis.setStartAtZero(true);
        leftAxis.setDrawLabels(true);
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
            xValues.add(listScore.get(i));
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
        barData.setValueTextColor(Color.parseColor("#296a8c"));
        barData.setValueTextSize(context.getResources().getDimension(R.dimen.tv_3));
        barData.setValueFormatter(getValueFormatter());
        return barData;
    }

    private void notifyBarChart(boolean flag) {
        adapterStudent = new FragmentBarDataAdapter(getActivity(), listStudentBarData);
        adapterStudent.setFlag(flag);
        listViewStudent.setAdapter(adapterStudent);
    }

    private void getCalResult(int fullScore, List<ScoreReports> list) {
        listCount.clear();
        listScore.clear();
        listLj.clear();
        int n = (int) Math.ceil(fullScore * 1.0 / score);
        int count = 0, comp = 0;
        for (int i = 0; i < n; i++) {
            count = 0;
            comp = fullScore - score * (i + 1);
            for (int j = 0; j < list.size(); j++) {
                double m = list.get(j).getScore();
                int state = list.get(j).getEssStatus();
                if (state == 0) {
                    if (m >= comp) {
                        count++;
                    } else {
                        break;
                    }
                }
            }
            if (i == 0) {
                listCount.add(count);
                listScore.add("[" + (fullScore - score * (i + 1)) + "," + (fullScore - score * i) + "]");
            } else if (comp >= 0) {
                listCount.add(count - listLj.get(i - 1));
                listScore.add("[" + (fullScore - score * (i + 1)) + "," + (fullScore - score * i) + ")");
            } else {
                listCount.add(count - listLj.get(i - 1));
                listScore.add("[" + 0 + "," + (fullScore - score * i) + ")");
            }
            listLj.add(count);
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

    //状态诊断
    private void addStateView(boolean flag) {
        if (!flag) {
            int n = linearLayout.getChildCount();
            linearLayout.removeViewAt(n - 1);
        }

        View view = LayoutInflater.from(context).inflate(R.layout.fragment_single_grade_state, null);
        stateView = (StateView) view.findViewById(R.id.fragment_single_class_stateview);
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels - (int) (activity.getResources().getDimension(R.dimen.view_84));    //得到宽度
        int height = dm.heightPixels;
        //Log.i("info", "st 宽=" + width + ",高=" + height);
        stateView.setmWidth(width);

        View v1 = view.findViewById(R.id.grade_layout_1);
        TextView tv1 = (TextView) v1.findViewById(R.id.fragment_single_grade_state_item_tv1);
        TextView tv2 = (TextView) v1.findViewById(R.id.fragment_single_grade_state_item_tv2);
        tv1.setText("A 优秀稳定性");
        tv2.setText("学习良好，稳定提高");

        View v2 = view.findViewById(R.id.grade_layout_2);
        tv1 = (TextView) v2.findViewById(R.id.fragment_single_grade_state_item_tv1);
        tv2 = (TextView) v2.findViewById(R.id.fragment_single_grade_state_item_tv2);
        tv1.setText("A' 粗心大意型");
        tv2.setText("粗心大意，不小心出错");

        View v3 = view.findViewById(R.id.grade_layout_3);
        tv1 = (TextView) v3.findViewById(R.id.fragment_single_grade_state_item_tv1);
        tv2 = (TextView) v3.findViewById(R.id.fragment_single_grade_state_item_tv2);
        tv1.setText("B 稳中求进型");
        tv2.setText("学习尚稳定，需要更用工");

        View v4 = view.findViewById(R.id.grade_layout_4);
        tv1 = (TextView) v4.findViewById(R.id.fragment_single_grade_state_item_tv1);
        tv2 = (TextView) v4.findViewById(R.id.fragment_single_grade_state_item_tv2);
        tv1.setText("B' 准备不足型");
        tv2.setText("偶尔粗心大意,准备不充足");

        View v5 = view.findViewById(R.id.grade_layout_5);
        tv1 = (TextView) v5.findViewById(R.id.fragment_single_grade_state_item_tv1);
        tv2 = (TextView) v5.findViewById(R.id.fragment_single_grade_state_item_tv2);
        tv1.setText("C 方法欠佳型");
        tv2.setText("对知识掌握不充分，需要改变学习，提高学习能力");

        View v6 = view.findViewById(R.id.grade_layout_6);
        tv1 = (TextView) v6.findViewById(R.id.fragment_single_grade_state_item_tv1);
        tv2 = (TextView) v6.findViewById(R.id.fragment_single_grade_state_item_tv2);
        tv1.setText("C' 表现异常性");
        tv2.setText("学习极不稳定，具有随性的学习习惯，对于考试内容没有充分的准备");

        linearLayout.addView(view);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.fragment_single_tv_1:
//                if (System.currentTimeMillis() - last_name_click >= 1000) {
//                    tv1Flag = !tv1Flag;
//                    changeTv1();
//                    last_name_click = System.currentTimeMillis();
//                }
//                break;
//            case R.id.fragment_single_tv_2:
            case R.id.fragment_single_title_linear_1:
                tv23Flag = !tv23Flag;
                if (tv23Flag) {
                    iv1.setImageResource(R.mipmap.ic_shanglv);
                } else {
                    iv1.setImageResource(R.mipmap.ic_xialv);
                }
                iv2.setImageResource(R.mipmap.ic_quanhui);
                changeTv23();
                break;
            case R.id.fragment_single_title_linear_2:
                tv4Flag = !tv4Flag;
                if (tv4Flag) {
                    iv2.setImageResource(R.mipmap.ic_shanglv);
                } else {
                    iv2.setImageResource(R.mipmap.ic_xialv);
                }
                iv1.setImageResource(R.mipmap.ic_quanhui);
                changeTv4();
                break;
        }
    }

    //点击成绩单 分数和班级排名的数据操作
    private void changeTv23() {
        Object[] data = null;
        if (listStudent != null && listStudent.size() > 0) {
            data = new Object[listStudent.size()];
            for (int i = 0; i < data.length; i++) {
                data[i] = listStudent.get(i);
            }

            //排序
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data.length - 1 - i; j++) {
                    GradeReport gr1 = (GradeReport) data[j];
                    GradeReport gr2 = (GradeReport) data[j + 1];
                    String offset1 = gr1.getClassOrder();
                    String offset2 = gr2.getClassOrder();
                    int m = 0, n = 0;
                    if (offset1.contains("--")) {
                        m = 0;
                    } else {
                        m = Integer.parseInt(offset1);
                    }

                    if (offset2.contains("--")) {
                        n = 0;
                    } else {
                        n = Integer.parseInt(offset2);
                    }

                    if (m > n) {
                        GradeReport temp = (GradeReport) data[j];
                        data[j] = data[j + 1];
                        data[j + 1] = temp;
                    }
                }
            }

            listStudent.clear();
            if (tv23Flag) {
                for (int i = 0; i < data.length; i++) {
                    GradeReport gr = (GradeReport) data[i];
                    int state = gr.getEssStatus();
                    if (state == 0) {
                        listStudent.add(gr);
                    }
                }

                for (int i = 0; i < data.length; i++) {
                    GradeReport gr = (GradeReport) data[i];
                    int state = gr.getEssStatus();
                    if (state != 0) {
                        listStudent.add(gr);
                    }
                }
            } else {
                for (int i = 0; i < data.length; i++) {
                    GradeReport gr = (GradeReport) data[data.length - 1 - i];
                    int state = gr.getEssStatus();
                    if (state == 0) {
                        listStudent.add(gr);
                    }
                }

                for (int i = 0; i < data.length; i++) {
                    GradeReport gr = (GradeReport) data[data.length - 1 - i];
                    int state = gr.getEssStatus();
                    if (state != 0) {
                        listStudent.add(gr);
                    }
                }
            }
        }
        notifyData(listState[0]);
        tv1Flag = false;
        tv4Flag = false;
    }


    //点击成绩单 班级排名的数据操作
    private void changeTv4() {
        Object[] data = null;
        if (listStudent != null && listStudent.size() > 0) {
            data = new Object[listStudent.size()];
            for (int i = 0; i < data.length; i++) {
                data[i] = listStudent.get(i);
            }

            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data.length - 1 - i; j++) {
                    GradeReport gr1 = (GradeReport) data[j];
                    GradeReport gr2 = (GradeReport) data[j + 1];
                    String offset1 = gr1.getRankChange();
                    String offset2 = gr2.getRankChange();
                    int m = 0, n = 0;
                    if (offset1.contains("↑")) {
                        offset1 = offset1.replace("↑", "");
                        m = Integer.parseInt(offset1);
                    } else if (offset1.contains("↓")) {
                        offset1 = offset1.replace("↓", "");
                        m = -1 * Integer.parseInt(offset1);
                    } else {
                        m = 0;
                    }

                    if (offset2.contains("↑")) {
                        offset2 = offset2.replace("↑", "");
                        n = Integer.parseInt(offset2);
                    } else if (offset2.contains("↓")) {
                        offset2 = offset2.replace("↓", "");
                        n = -1 * Integer.parseInt(offset2);
                    } else {
                        n = 0;
                    }

                    if (m > n) {
                        GradeReport temp = (GradeReport) data[j];
                        data[j] = data[j + 1];
                        data[j + 1] = temp;
                    }
                }
            }

            listStudent.clear();
            //Log.i("info","tv4--"+tv4Flag);
            if (tv4Flag) {
                for (int i = 0; i < data.length; i++) {
                    GradeReport gr = (GradeReport) data[data.length - 1 - i];
                    double state = gr.getEssStatus();
                    if (state == 0) {
                        listStudent.add(gr);
                    }
                }

                for (int i = 0; i < data.length; i++) {
                    GradeReport sr = (GradeReport) data[data.length - 1 - i];
                    double state = sr.getEssStatus();
                    if (state != 0) {
                        listStudent.add(sr);
                    }
                }
            } else {
                for (int i = 0; i < data.length; i++) {
                    GradeReport gr = (GradeReport) data[i];
                    double state = gr.getEssStatus();
                    if (state == 0) {
                        listStudent.add(gr);
                    }
                }

                for (int i = 0; i < data.length; i++) {
                    GradeReport gr = (GradeReport) data[i];
                    double state = gr.getEssStatus();
                    if (state != 0) {
                        listStudent.add(gr);
                    }
                }
            }
            notifyData(listState[0]);
            tv1Flag = false;
            tv23Flag = false;
        }
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("SingleReportActivity_SingleClassFragment"); //统计页面，"MainScreen"为页面名称，可自定义
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("SingleReportActivity_SingleClassFragment");
    }
}