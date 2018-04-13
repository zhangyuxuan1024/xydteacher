package net.iclassmate.teacherspace.ui.fragment.general;

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
import net.iclassmate.teacherspace.adapter.FragmentGeneralClassAdapter;
import net.iclassmate.teacherspace.adapter.single.FragmentBarDataAdapter;
import net.iclassmate.teacherspace.bean.Student;
import net.iclassmate.teacherspace.bean.general.GeneralAll;
import net.iclassmate.teacherspace.bean.general.ScoreReports;
import net.iclassmate.teacherspace.bean.single.GradeReport;
import net.iclassmate.teacherspace.ui.fragment.LazyFragment;
import net.iclassmate.teacherspace.view.FullListView;
import net.iclassmate.teacherspace.view.GeneralLineViewClass;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xydbj on 2016.1.30.
 */
public class GeneralClassFragment extends LazyFragment implements AdapterView.OnItemClickListener,View.OnClickListener {
    private LinearLayout linearLayout;
    private List<Object> listStudent;
    private List<Object> listStudentBarData;
    private boolean[] listState;
    private FullListView listViewCjd;
    private FullListView listViewStudent;
    private List<FullListView> listViews;
    private boolean isLoaded;
    private FragmentGeneralClassAdapter adapterCjd;
    private FragmentBarDataAdapter adapterStudent;
    private BarChart mBarChart;
    private BarData mBarData;
    private GeneralAll generalAll;
    private GeneralLineViewClass lineView;
    private List<ScoreReports> scoreReportsList;
    private int cur_tv;
    private int score,max;
    private List<Integer> listCount;
    private List<String> listScore;
    private List<Integer> listLj;

    private View titleView;
    private TextView general_class_cjd_title_tv1,general_class_cjd_title_tv2,general_class_cjd_title_tv3,general_class_cjd_title_tv4;
    private ImageView general_class_cjd_title_iv3,general_class_cjd_title_iv4;
    private LinearLayout general_class_cjd_title_linearLayout1,general_class_cjd_title_linearLayout2;
    private boolean tv1Flag,tv23Flag,tv4Flag;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general_class,container,false);
        initView(view);

        return view;
    }

    private void initView(View view){
        linearLayout = (LinearLayout) view.findViewById(R.id.fragment_general_class_linearlayout);
        listState = new boolean[2];
        listViews = new ArrayList<>();
        listStudent = new ArrayList<>();
        listStudentBarData = new ArrayList<>();
        score = 50;
        listCount = new ArrayList<>();
        listScore = new ArrayList<>();
        listLj = new ArrayList<>();

        addView();
        addViewData(cur_tv);

        tv1Flag = false;
        tv23Flag = true;
        tv4Flag = false;
    }

    @Override
    protected void lazyLoad() {

    }

    public void addView(){
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_general_exam_listview_cjd,null);
        linearLayout.addView(view);

        listViewCjd = (FullListView) view.findViewById(R.id.fragment_general_ListView_cjd);
        ImageView imageView = (ImageView) view.findViewById(R.id.fragment_general_title_iv_cjd);
        imageView.setImageResource(R.mipmap.img_chengjidan);
        adapterCjd = new FragmentGeneralClassAdapter(getActivity(), listStudent);

        titleView = LayoutInflater.from(getContext()).inflate(R.layout.general_class_cjd_title,null);
        listViewCjd.addHeaderView(titleView);
        listViewCjd.setAdapter(adapterCjd);
        listViewCjd.setOnItemClickListener(this);
        listState[0] = false;
        listViews.add(listViewCjd);
        initTitleView(titleView);

        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_general_class_listview_xsfb, null);
        linearLayout.addView(view);
        mBarChart = (BarChart) view.findViewById(R.id.fragment_single_grade_barchart);
        listViewStudent = (FullListView) view.findViewById(R.id.fragment_grade_xbtj_ListView);
        lineView = (GeneralLineViewClass) view.findViewById(R.id.fragment_single_grade_lineview);
        imageView = (ImageView) view.findViewById(R.id.fragment_title_iv);
        imageView.setImageResource(R.mipmap.img_xueshengfenbu);
        adapterStudent = new FragmentBarDataAdapter(getActivity(), listStudentBarData);
        listViewStudent.setAdapter(adapterStudent);
        listViews.add(listViewStudent);
        listViewStudent.setOnItemClickListener(this);
        listState[1] = false;
        lineView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String text = lineView.getText();
                text = text.substring(0, text.length() - 1);
                score = Integer.parseInt(text);
                setStudent(cur_tv);
                return false;
            }
        });
    }

    public void initTitleView(View titleView){
        general_class_cjd_title_tv1 = (TextView) titleView.findViewById(R.id.general_class_cjd_title_tv1);
        general_class_cjd_title_tv2 = (TextView) titleView.findViewById(R.id.general_class_cjd_title_tv2);
        general_class_cjd_title_tv3 = (TextView) titleView.findViewById(R.id.general_class_cjd_title_tv3);
        general_class_cjd_title_tv4 = (TextView) titleView.findViewById(R.id.general_class_cjd_title_tv4);
        general_class_cjd_title_iv3 = (ImageView) titleView.findViewById(R.id.general_class_cjd_title_iv3);
        general_class_cjd_title_iv4 = (ImageView) titleView.findViewById(R.id.general_class_cjd_title_iv4);
        general_class_cjd_title_linearLayout1 = (LinearLayout) titleView.findViewById(R.id.general_class_cjd_title_linearLayout1);
        general_class_cjd_title_linearLayout2 = (LinearLayout) titleView.findViewById(R.id.general_class_cjd_title_linearLayout2);

        general_class_cjd_title_tv1.setText("姓名");
        general_class_cjd_title_tv2.setText("分数");
        general_class_cjd_title_tv3.setText("班级排名");
        general_class_cjd_title_tv4.setText("进退步");

        general_class_cjd_title_iv3.setImageResource(R.mipmap.ic_shanglv);
        general_class_cjd_title_iv4.setImageResource(R.mipmap.ic_quanhui);

        general_class_cjd_title_tv1.setTextColor(Color.parseColor("#1698ff"));
        general_class_cjd_title_tv2.setTextColor(Color.parseColor("#1698ff"));
        general_class_cjd_title_tv3.setTextColor(Color.parseColor("#1698ff"));
        general_class_cjd_title_tv4.setTextColor(Color.parseColor("#1698ff"));

        general_class_cjd_title_tv1.setTextSize(16);
        general_class_cjd_title_tv2.setTextSize(16);
        general_class_cjd_title_tv3.setTextSize(16);
        general_class_cjd_title_tv4.setTextSize(16);

        general_class_cjd_title_linearLayout1.setOnClickListener(this);
        general_class_cjd_title_linearLayout2.setOnClickListener(this);

    }

    public void sendDataToFragment(int index, Object object) {
        if (object != null) {
            if (object instanceof GeneralAll) {
                generalAll = (GeneralAll) object;
                cur_tv = index;
                if (index == 0) {
                    cur_tv = 1;
                }
                setScore(cur_tv);
                addViewData(cur_tv);
            }
        }
    }

    public void textViewSelectedChanged(int index) {
        if (index == 0) return;
        if (index == cur_tv) return;
        if (generalAll == null) return;
        cur_tv = index;
        addViewData(index);

    }

    private void addViewData(int index) {
        if (index == 0) {
            index = 1;
            cur_tv = index;
        }
        if (isLoaded) {
            setScore(index);
            setStudent(index);
        } else {
            isLoaded = true;
        }
    }
    //成绩单
    public void setScore(int index){
        if (listStudent != null) {
            listStudent.clear();
        }else {
            listStudent = new ArrayList<>();
        }
        scoreReportsList = generalAll.getList().get(index).getClassSituation().getScoreReportslist();
        GradeReport student =null;/* new GradeReport();
        student.setName("姓名");
        student.setScore("分数");
        student.setClassOrder("班级排名");
        student.setRankChange("进退步");*/
//        listStudent.add(student);
        for (int i = 0; i < scoreReportsList.size(); i++) {
            student = new GradeReport();
            ScoreReports score = scoreReportsList.get(i);
            student.setsId(score.getStudentId());
            student.setName(score.getStudentName());
            student.setScore(score.getScore() + "");
            student.setClassOrder(score.getClassOrder());

            String str = score.getOffsetOrder();
            if (str != null && !str.equals("--") && !str.equals("--")) {
                int n = Integer.parseInt(str);
                int m = Math.abs(n);
                if (n > 0) {
                    student.setRankChange("↑" + m);
                } else if (n < 0) {
                    student.setRankChange("↓" + m);
                } else {
                    student.setRankChange("0");
                }
            } else {
                student.setRankChange("--");
            }
            listStudent.add(student);
        }
        try {
            notifyData(listState[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setStudent(int index) {
        List<ScoreReports> list = generalAll.getList().get(cur_tv).getClassSituation().getScoreReportslist();
        if (list.size() > 0) {
            upListData();
            setBarData();
        }
    }

    private void upListData(){
        listStudentBarData.clear();
        if(generalAll == null || generalAll.getList().size()<=0){
            return;
        }
        scoreReportsList = generalAll.getList().get(cur_tv).getClassSituation().getScoreReportslist();
        Student s = new Student();
        s.setsClass("分数段");
        s.setName(generalAll.getList().get(cur_tv).getClassName());
        s.setScore("所占比例");
        s.setRankChange("累加");
        listStudentBarData.add(s);
        getCalResult(generalAll.getList().get(cur_tv).getClassSituation().getScoreReportslist().get(0).getFullScore(), scoreReportsList);
        for (int i = 0; i < listScore.size(); i++) {
            Student student = new Student();
            student.setsClass(listScore.get(i));
            student.setName(listCount.get(i) + "");
            student.setScore((int) Math.rint(listCount.get(i) * 100 * 1.0 / scoreReportsList.size()) + "%");
            student.setRankChange(listLj.get(i) + "");
            listStudentBarData.add(student);
        }
        notifyBarChart(listState[1]);
    }

    private void notifyData(boolean flag) {
        adapterCjd = new FragmentGeneralClassAdapter(getActivity(),listStudent,generalAll);
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
            if (i == listStudent.size()+1) {
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
        barChart.setDescriptionTextSize(getResources().getDimension(R.dimen.tv_3));
        barChart.setDescriptionPosition(getResources().getDimension(R.dimen.view_30), getResources().getDimension(R.dimen.view_7));
        // 如果没有数据的时候，会显示这个，类似ListView的EmptyView
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
        xAxis.setTextSize(getResources().getDimension(R.dimen.tv_3));
        xAxis.setSpaceBetweenLabels(0);
        barChart.setVisibleXRangeMaximum(7.5f);
        barChart.setVisibleXRangeMinimum(7.5f);

        //隐藏左边坐标轴横网格线
        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setTextColor(ContextCompat.getColor(getActivity(),R.color.app_color));
        leftAxis.setTextSize(getResources().getDimension(R.dimen.tv_3));
        leftAxis.setValueFormatter(getValueFormatter());
        resetBarData(leftAxis, max);

        //隐藏右边坐标轴横网格线
        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setTextColor(ContextCompat.getColor(getActivity(),R.color.app_color));
        rightAxis.setTextSize(getResources().getDimension(R.dimen.tv_3));
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
        barDataSet.setValueTextSize(getResources().getDimension(R.dimen.tv_3));


        ArrayList<BarDataSet> barDataSets = new ArrayList<BarDataSet>();
        barDataSets.add(barDataSet);
        BarData barData = new BarData(xValues, barDataSets);
        barData.setDrawValues(true);
        barData.setValueFormatter(getValueFormatter());
        return barData;
    }

    public void notifyBarChart(boolean flag){
        adapterStudent = new FragmentBarDataAdapter(getActivity(), listStudentBarData);
        adapterStudent.setFlag(flag);
        listViewStudent.setAdapter(adapterStudent);
    }

    private void getCalResult(String fuScore, List<ScoreReports> list) {
        listCount.clear();
        listScore.clear();
        listLj.clear();
        int fullScore = Integer.parseInt(fuScore);
//        double d = (double) fullScore / score;
//        int n = (int) Math.ceil(d);
        int n = (int) Math.ceil(fullScore * 1.0 / score);
        int count = 0;
        double comp = 0;
        for (int i = 0; i < n; i++) {
            count = 0;
            comp = fullScore - score * (i + 1);
            for (int j = 0; j < list.size(); j++) {
                double m = Double.parseDouble(list.get(j).getScore());
                if (m >= comp) {
                    count++;
                } else {
                    break;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.general_class_cjd_title_linearLayout1:
                tv23Flag = !tv23Flag;
                if(tv23Flag){
                    general_class_cjd_title_iv3.setImageResource(R.mipmap.ic_shanglv);
                }else{
                    general_class_cjd_title_iv3.setImageResource(R.mipmap.ic_xialv);
                }
                general_class_cjd_title_iv4.setImageResource(R.mipmap.ic_quanhui);
                changeTv23();
                break;
            case R.id.general_class_cjd_title_linearLayout2:
                tv4Flag = !tv4Flag;
                if(tv4Flag){
                    general_class_cjd_title_iv4.setImageResource(R.mipmap.ic_shanglv);
                }else{
                    general_class_cjd_title_iv4.setImageResource(R.mipmap.ic_xialv);
                }
                general_class_cjd_title_iv3.setImageResource(R.mipmap.ic_quanhui);
                changeTv4();
                break;
        }
    }

    public void changeTv23(){
        Object[] data = null;
        if(listStudent != null && listStudent.size()>0){
            data = new Object[listStudent.size()];
            for(int i=0;i<data.length;i++){
                data[i] = listStudent.get(i);
            }
            for (int i = 0; i < data.length; i++) {
                for(int j=0;j<data.length-i-1;j++){
                    GradeReport gr1  = (GradeReport) data[j];
                    GradeReport gr2 = (GradeReport) data[j+1];
                    String offset1 = gr1.getClassOrder();
                    String offset2 = gr2.getClassOrder();
                    int m=0,n=0;
                    if(offset1.contains("--")){
                        m=0;
                    }else{
                        m=Integer.parseInt(offset1);
                    }

                    if(offset2.contains("--")){
                        n=0;
                    }else{
                        n=Integer.parseInt(offset2);
                    }
                    if(m>n){
                        GradeReport temp = (GradeReport) data[j];
                        data[j]=data[j+1];
                        data[j+1]=temp;
                    }
                }
            }
            listStudent.clear();
            if(tv23Flag){
                for (int i = 0; i < data.length; i++) {
                    GradeReport gr = (GradeReport) data[i];
                    listStudent.add(gr);
                }
            }else{
                for (int i = 0; i < data.length; i++) {
                    GradeReport gr = (GradeReport) data[data.length-i-1];
                    listStudent.add(gr);
                }
            }
        }
        notifyData(listState[0]);
        tv1Flag = false;
        tv4Flag = false;
    }
    public void changeTv4(){
        Object[] data = null;
        if(listStudent != null && listStudent.size() > 0){
            data = new Object[listStudent.size()];
            for(int i=0;i<listStudent.size();i++){
                data[i] = listStudent.get(i);
            }
            for(int i=0;i<data.length;i++){
                for(int j=0;j<data.length-i-1;j++){
                    GradeReport gr1 = (GradeReport) data[j];
                    GradeReport gr2 = (GradeReport) data[j+1];
                    String offset1 = gr1.getRankChange();
                    String offset2 = gr2.getRankChange();
                    int m=0,n=0;
                    if(offset1.contains("↑")){
                        offset1=offset1.replace("↑","");
                        m = Integer.parseInt(offset1);
                    }else if(offset1.contains("↓")){
                        offset1=offset1.replace("↓","");
                        m = -1*Integer.parseInt(offset1);
                    }else{
                        m=0;
                    }

                    if (offset2.contains("↑")){
                        offset2=offset2.replace("↑","");
                        n = Integer.parseInt(offset2);
                    }else if(offset2.contains("↓")){
                        offset2=offset2.replace("↓","");
                        n = -1*Integer.parseInt(offset2);
                    }else{
                        n=0;
                    }

                    if(m>n){
                        GradeReport temp = (GradeReport) data[j];
                        data[j] = data[j + 1];
                        data[j + 1] = temp;
                    }
                }
            }

            listStudent.clear();
            if(tv4Flag){
                for(int i=0;i<data.length;i++){
                    GradeReport gr = (GradeReport) data[data.length-1-i];
                    listStudent.add(gr);
                }
            }else{
                for(int i=0;i<data.length;i++){
                    GradeReport gr = (GradeReport) data[i];
                    listStudent.add(gr);
                }
            }
            notifyData(listState[0]);
            tv1Flag = false;
            tv23Flag = false;
        }
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("GeneralActivity_GeneralClassFragment"); //统计页面，"MainScreen"为页面名称，可自定义
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("GeneralActivity_GeneralClassFragment");
    }
}

























