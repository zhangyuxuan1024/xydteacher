package net.iclassmate.teacherspace.ui.fragment.general;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.utils.ValueFormatter;
import com.umeng.analytics.MobclickAgent;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.adapter.FragmentGeneralAdapter;
import net.iclassmate.teacherspace.adapter.FragmentGeneralSummaryInfoAdapter;
import net.iclassmate.teacherspace.bean.Student;
import net.iclassmate.teacherspace.bean.general.BackSliders;
import net.iclassmate.teacherspace.bean.general.ExcellentPersons;
import net.iclassmate.teacherspace.bean.general.GeneralAll;
import net.iclassmate.teacherspace.bean.general.Improvers;
import net.iclassmate.teacherspace.bean.general.SingleExamInfos;
import net.iclassmate.teacherspace.bean.general.SummaryInfoDetails;
import net.iclassmate.teacherspace.ui.fragment.LazyFragment;
import net.iclassmate.teacherspace.view.FullListView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GeneralAllFragment extends LazyFragment implements AdapterView.OnItemClickListener {
    private List<Object> listAll;
    private List<Object> listXb;
    private List<Object> listJb;
    private List<Object> listTb;

    private FragmentGeneralAdapter adapter;
    private FragmentGeneralSummaryInfoAdapter summaryInfoAdapter;
    private LinearLayout linearLayout;
    private boolean[] listState;
    private boolean isLoaded;
    private List<FullListView> listViews;
    private GeneralAll generalAll;
    private List<BaseAdapter> listAdapter;
    private List<SummaryInfoDetails> SummaryInfoDetailslist;
    private List<Improvers> Improverslist;
    private List<ExcellentPersons> ExcellentPersonslist;
    private List<BackSliders> BackSliderslist;
    private int cur_tv;
    private TextView fragment_generall_all_listview_top;

    public RadarChart radarChart;
    public ArrayList<String> x = new ArrayList<String>();
    public ArrayList<Entry> y = new ArrayList<Entry>();
    public ArrayList<Entry> z = new ArrayList<Entry>();
    public ArrayList<RadarDataSet> radarDataSets = new ArrayList<RadarDataSet>();
    public RadarData radarData = new RadarData();
    public TextView fragment_general_all_title_meName;
    public TextView fragment_general_all_title_meDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general_all, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        linearLayout = (LinearLayout) view.findViewById(R.id.fragment_general_all_linear);
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_general_all_title, null);
        fragment_general_all_title_meName = (TextView) v.findViewById(R.id.fragment_general_all_title_meName);
        fragment_general_all_title_meDate = (TextView) v.findViewById(R.id.fragment_general_all_title_meDate);
        linearLayout.addView(v);
        listState = new boolean[4];
        listViews = new ArrayList<>();
        listAdapter = new ArrayList<>();
        SummaryInfoDetailslist = new ArrayList<>();
        Improverslist = new ArrayList<>();
        ExcellentPersonslist = new ArrayList<>();
        BackSliderslist = new ArrayList<>();
        addView();

        addData();
    }

    private void addView() {
        View v = null;
        FullListView listView = null;
        ImageView imageView = null;

        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_exam_listview_radar, null);
                listView = (FullListView) v.findViewById(R.id.fragment_ListView_radar);
                imageView = (ImageView) v.findViewById(R.id.fragment_title_iv_radar);
                radarChart = (RadarChart) v.findViewById(R.id.general_zhongtiqingkuang_radar);

            }else if(i ==1){
                v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_general_all_listview, null);
                listView = (FullListView) v.findViewById(R.id.fragment_ListView);
                imageView = (ImageView) v.findViewById(R.id.fragment_title_iv);
                fragment_generall_all_listview_top = (TextView) v.findViewById(R.id.fragment_generall_all_listview_top);
                fragment_generall_all_listview_top.setVisibility(View.INVISIBLE);
            } else {
                v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_general_all_listview, null);
                listView = (FullListView) v.findViewById(R.id.fragment_ListView);
                imageView = (ImageView) v.findViewById(R.id.fragment_title_iv);
            }
            listView.setOnItemClickListener(this);
            listViews.add(listView);
            linearLayout.addView(v);
            v = null;

            if (i == 0) {
                summaryInfoAdapter = new FragmentGeneralSummaryInfoAdapter(getActivity());
                summaryInfoAdapter.setFlag(listState[0]);
                listAdapter.add(summaryInfoAdapter);
                listView.setAdapter(summaryInfoAdapter);
            } else {
                adapter = new FragmentGeneralAdapter(getActivity());
                adapter = new FragmentGeneralAdapter(getActivity(),generalAll);
                adapter.setFlag(listState[i]);
                listAdapter.add(adapter);
                listView.setAdapter(adapter);
            }

            if (i == 0) {
                imageView.setImageResource(R.mipmap.img_zongtiqingkuang);
            } else if (i == 1) {
                imageView.setImageResource(R.mipmap.img_xuebabang);
            } else if (i == 2) {
                imageView.setImageResource(R.mipmap.img_jinbubang);
            } else if (i == 3) {
                imageView.setImageResource(R.mipmap.img_tuibubang);
            }
        }
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void sendDataToFragment(int index, Object o) {
        if (o != null) {
            if (o instanceof GeneralAll) {
                generalAll = (GeneralAll) o;
                cur_tv = index;
                addData();
            }
        }
    }

    private void addData() {
        if (isLoaded) {
            setRadarData(cur_tv);
            setSummary(cur_tv);
            setExcellent(cur_tv);
            setImprovers(cur_tv);
            setBackSliders(cur_tv);
        } else {
            isLoaded = true;
        }
    }

    public void textViewSelectedChanged(int index) {
        if (index == cur_tv) {
            return;
        }
        setRadarData(index);
        setSummary(index);
        setExcellent(index);
        setImprovers(index);
        setBackSliders(index);
        cur_tv = index;
    }

    //总体情况（不包含雷达图）
    public void setSummary(int index) {
        fragment_general_all_title_meName.setText(generalAll.getList().get(index).getTotalSituation().getMeName());
        String Date = generalAll.getList().get(index).getTotalSituation().getMeDate().substring(0,10);
        fragment_general_all_title_meDate.setText(Date);

        SummaryInfoDetailslist = generalAll.getList().get(index).getTotalSituation().getSummaryInfo().getSummaryInfoDetailslist();
        listAll = new ArrayList<Object>();
        Student s = new Student();
        s.setsClass("班级");
        s.setName("班级平均分");
        s.setScore("排名");
        s.setRankChange("排名变化");
        listAll.add(s);
        for (int i = 0; i < SummaryInfoDetailslist.size(); i++) {
            SummaryInfoDetails sI = SummaryInfoDetailslist.get(i);
            s = new Student();
            s.setsClass(sI.getClassName());
            s.setName(sI.getUnitAvgScore() + "");
            s.setScore(sI.getUnitOrder());

            if (sI.getOffsetOrder() != null && !sI.getOffsetOrder().equals("") && !sI.getOffsetOrder().equals("--")) {
                int score = Integer.parseInt(sI.getOffsetOrder());
                if (score > 0) {
                    s.setRankChange("↑" + sI.getOffsetOrder());
                } else if (score < 0) {
                    s.setRankChange("↓" + sI.getOffsetOrder().substring(1));
                } else if(score == 0){
                    s.setRankChange("0");
                }
            } else {
                s.setRankChange(sI.getOffsetOrder() + "");
            }
            listAll.add(s);
        }

        notifyData(0);
        Log.i("info", "更新数据=" + listAll.size());
    }

    //学霸榜
    public void setExcellent(int index) {
        ExcellentPersonslist = generalAll.getList().get(index).getTotalSituation().getExcellentPersonslist();
        listXb = new ArrayList<Object>();
        Student s = new Student();
        s.setsClass("班级");
        s.setName("学生");
        s.setScore("分数");
        s.setRankChange("年级排名");
        listXb.add(s);
        fragment_generall_all_listview_top.setVisibility(View.VISIBLE);
        fragment_generall_all_listview_top.setText("※Top" + ExcellentPersonslist.size());
        for (int i = 0; i < ExcellentPersonslist.size(); i++) {
            Student student = new Student();
            ExcellentPersons excellentPersons = ExcellentPersonslist.get(i);
            student.setsId(excellentPersons.getStudentId());
            student.setsClass(excellentPersons.getClassName());
            student.setName(excellentPersons.getStudentName());
            student.setScore(excellentPersons.getScore() + "");
            if (excellentPersons.getGradeOrderOffset() != null && !excellentPersons.getGradeOrderOffset().equals("") && !excellentPersons.getGradeOrderOffset().equals("--")) {
                int n = Integer.parseInt(excellentPersons.getGradeOrderOffset());
                if (n > 0) {
                    student.setRankChange(excellentPersons.getGradeOrder() + "( ↑" + n + " )");
                } else if (n < 0) {
                    n = Math.abs(n);
                    student.setRankChange(excellentPersons.getGradeOrder() + "( ↓" + n + " )");
                } else {
                    student.setRankChange(excellentPersons.getGradeOrder()+"("+excellentPersons.getGradeOrderOffset()+")");
                }
            } else {
                student.setRankChange(excellentPersons.getGradeOrder()+"("+excellentPersons.getGradeOrderOffset()+")");
            }
            listXb.add(student);
        }
        notifyData(1);
    }

    //进步榜
    public void setImprovers(int index) {
        Improverslist = generalAll.getList().get(index).getTotalSituation().getImproverslist();
        listJb = new ArrayList<>();
        Student s = new Student();
        s.setsClass("班级");
        s.setName("学生");
        s.setScore("分数");
        s.setRankChange("年级排名");
        listJb.add(s);
        for (int i = 0; i < Improverslist.size(); i++) {
            Student student = new Student();
            Improvers improvers = Improverslist.get(i);
            student.setsId(improvers.getStudentId());
            student.setsClass(improvers.getClassName());
            student.setName(improvers.getStudentName());
            student.setScore(improvers.getScore() + "");
            String ret = improvers.getGradeOrderOffset();
            if (ret != null && !ret.equals("--") && !ret.equals("--")) {
                int n = Integer.parseInt(ret);
                if (n == 0) {
                    student.setRankChange(improvers.getGradeOrder() + "( -- )");
                } else {
                    student.setRankChange(improvers.getGradeOrder() + "( ↑" + ret + " )");
                }
            } else {
                student.setRankChange(improvers.getGradeOrder() + "( -- )");
            }
            listJb.add(student);
        }
        notifyData(2);
    }

    //退步榜
    public void setBackSliders(int index) {
        BackSliderslist = generalAll.getList().get(index).getTotalSituation().getBackSliderslist();
        listTb = new ArrayList<>();
        Student s = new Student();
        s.setsClass("班级");
        s.setName("学生");
        s.setScore("分数");
        s.setRankChange("年级排名");
        listTb.add(s);
        for (int i = 0; i < BackSliderslist.size(); i++) {
            Student student = new Student();
            BackSliders sliders = BackSliderslist.get(i);
            student.setsId(sliders.getStudentId());
            student.setsClass(sliders.getClassName());
            student.setName(sliders.getStudentName());
            student.setScore(sliders.getScore() + "");
            if (sliders.getGradeOrderOffset() != null && !sliders.getGradeOrderOffset().equals("") && !sliders.getGradeOrderOffset().equals("--")) {
                int n = Integer.parseInt(sliders.getGradeOrderOffset());
                n = Math.abs(n);
                student.setRankChange(sliders.getGradeOrder() + "( ↓" + n + " )");
            } else {
                student.setRankChange("--");
            }
            listTb.add(student);
        }
        notifyData(3);
    }

    private void notifyData(int index) {
        FullListView listView = listViews.get(index);
        if (index == 0) {
            summaryInfoAdapter = new FragmentGeneralSummaryInfoAdapter(getActivity(), listAll);
            summaryInfoAdapter.setFlag(listState[index]);
            listView.setAdapter(summaryInfoAdapter);
        } else {
            if (index == 1) {
                adapter = new FragmentGeneralAdapter(getActivity(), listXb,generalAll);
            } else if (index == 2) {
                adapter = new FragmentGeneralAdapter(getActivity(), listJb,generalAll);
            } else if (index == 3) {
                adapter = new FragmentGeneralAdapter(getActivity(), listTb,generalAll);
            }
            listView.setAdapter(adapter);
            adapter.setFlag(listState[index]);
        }
    }

    @Override
    public String getFragmentTitle() {
        return "总体情况";
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        int index = listViews.indexOf(adapterView);
        if (i == 5 && index == 0 && !listState[index]) {
            listState[index] = true;
        }

        if (i == 6 && !listState[index]) {
            listState[index] = true;
        }
        if (index == 0) {
            if (listAll.size() == i) {
                listState[index] = false;
            }
        } else if (index == 1) {
            if (listXb.size() == i) {
                listState[index] = false;
            }
        } else if (index == 2) {
            if (listJb.size() == i) {
                listState[index] = false;
            }
        } else if (index == 3) {
            if (listTb.size() == i) {
                listState[index] = false;
            }
        }
        notifyData(index);
    }

    public void setRadarData(int index) {
//        if(!radarData.equals("null") || radarData != null){
//            radarData.clearValues();
//        }
        radarData = getRadarData(generalAll.getList().get(index).getTotalSituation().getSummaryInfo().getSubjectSummarieslist().get(0).getSingleExamInfoslist().size(), 1, index);
        showChart();
    }

    public RadarData getRadarData(int count, float range, int index){
        try {
            radarDataSets.clear();
            List<SingleExamInfos> singleExamInfosList = new ArrayList<>();
            singleExamInfosList.clear();
            x.clear();
            y.clear();
            z.clear();
            if (generalAll.getList().get(index).getTotalSituation().getSummaryInfo().getSubjectSummarieslist().get(0).getSingleExamInfoslist().size() == 2) {
                for (int i = 0; i < generalAll.getList().get(index).getTotalSituation().getSummaryInfo().getSubjectSummarieslist().get(0).getSingleExamInfoslist().size(); i++) {
                    SingleExamInfos s = new SingleExamInfos();
                    s.setCourseName(generalAll.getList().get(index).getTotalSituation().getSummaryInfo().getSubjectSummarieslist().get(0).getSingleExamInfoslist().get(i).getCourseName());
                    singleExamInfosList.add(s);
                }
                SingleExamInfos ss = new SingleExamInfos();
                ss.setCourseName("");
                singleExamInfosList.add(ss);
                for (int i = 0; i < singleExamInfosList.size(); i++) {
                    x.add(singleExamInfosList.get(i).getCourseName());
                }
                for (int i = 0; i < count; i++) {
                    float result = generalAll.getList().get(index).getTotalSituation().getSummaryInfo()
                            .getSubjectSummarieslist().get(0).getSingleExamInfoslist().get(i).getScore();
                    int seFullScore = generalAll.getList().get(index).getTotalSituation().getSummaryInfo()
                            .getSubjectSummarieslist().get(0).getSingleExamInfoslist().get(i).getSeFullScore();
                    float lv = result/seFullScore;
                    y.add(new Entry(lv, i));
                }
                y.add(new Entry(0,2));
            } else {
                for (int i = 0; i < generalAll.getList().get(index).getTotalSituation().getSummaryInfo().getSubjectSummarieslist().get(0).getSingleExamInfoslist().size(); i++) {
                    SingleExamInfos s = new SingleExamInfos();
                    s.setCourseName(generalAll.getList().get(index).getTotalSituation().getSummaryInfo().getSubjectSummarieslist().get(0).getSingleExamInfoslist().get(i).getCourseName());
                    singleExamInfosList.add(s);
                }
                for (int i = 0; i < singleExamInfosList.size(); i++) {
                    x.add(singleExamInfosList.get(i).getCourseName());
                }
                for (int i = 0; i < count; i++) {
                    float result = generalAll.getList().get(index).getTotalSituation().getSummaryInfo()
                            .getSubjectSummarieslist().get(0).getSingleExamInfoslist().get(i).getScore();
                    int seFullScore = generalAll.getList().get(index).getTotalSituation().getSummaryInfo()
                            .getSubjectSummarieslist().get(0).getSingleExamInfoslist().get(i).getSeFullScore();
                    float lv = result/seFullScore;
                    y.add(new Entry(lv, i));
                }
            }
            RadarDataSet radarDataSet = new RadarDataSet(y, "年级情况");
            radarDataSet.setLineWidth(1f);//线宽
            radarDataSet.setColor(Color.RED);//现实颜色
            radarDataSet.setFillColor(Color.RED);//各科成绩填充的颜色
            radarDataSet.setDrawFilled(true);

            radarDataSets.add(radarDataSet);
            if (generalAll.getList().get(index).getTotalSituation()
                    .getSummaryInfo().getSubjectSummarieslist().size() == 2) {
                if(generalAll.getList().get(index).getTotalSituation().getSummaryInfo().getSubjectSummarieslist().get(0).getSingleExamInfoslist().size() == 2){
                    for (int i = 0; i < count; i++) {
                        float result = generalAll.getList().get(index).getTotalSituation().getSummaryInfo()
                                .getSubjectSummarieslist().get(1).getSingleExamInfoslist().get(i).getScore();
                        int seFullScore = generalAll.getList().get(index).getTotalSituation().getSummaryInfo()
                                .getSubjectSummarieslist().get(1).getSingleExamInfoslist().get(i).getSeFullScore();
                        float lv = result/seFullScore;
                        z.add(new Entry(lv, i));
                    }
                    z.add(new Entry(0,2));
                }else{
                    for (int i = 0; i < generalAll.getList().get(index).getTotalSituation().getSummaryInfo().getSubjectSummarieslist().get(1).getSingleExamInfoslist().size(); i++) {
                        float result = generalAll.getList().get(index).getTotalSituation().getSummaryInfo()
                                .getSubjectSummarieslist().get(1).getSingleExamInfoslist().get(i).getScore();
                        int seFullScore = generalAll.getList().get(index).getTotalSituation().getSummaryInfo()
                                .getSubjectSummarieslist().get(1).getSingleExamInfoslist().get(i).getSeFullScore();
                        float lv = result/seFullScore;
                        z.add(new Entry(lv, i));
                    }
                }
                RadarDataSet radarDataSet2 = new RadarDataSet(z, generalAll.getList().get(index).getTotalSituation().getSummaryInfo()
                        .getSubjectSummarieslist().get(1).getName());
                radarDataSet2.setLineWidth(1f);//线宽
                radarDataSet2.setColor(Color.BLUE);//现实颜色
                radarDataSet2.setFillColor(Color.BLUE);//各科成绩填充的颜色
                radarDataSet2.setDrawFilled(true);
                radarDataSets.add(radarDataSet2);
            }
            radarData = new RadarData(x, radarDataSets);
            radarData.setDrawValues(false);//是否显示分数
            return radarData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void showChart() {
        radarChart.setDescription("  ");//数据描述
        radarChart.setNoDataTextDescription("我需要数据");//没数据显示
        radarChart.setBackgroundColor(Color.WHITE);//背景颜色
//        radarChart.setWebColorInner(Color.parseColor("#aa0000"));//边框颜色
//        radarChart.setWebColor(Color.parseColor("#aa0000"));//Y轴颜色
//        radarChart.setDrawingCacheBackgroundColor(Color.parseColor("#aa0000"));
//        radarChart.setDescriptionColor(Color.parseColor("#aa0000"));
        radarChart.setWebLineWidth(1f);//Y轴线宽度

        radarChart.setTouchEnabled(true);
//        radarChart.setRotationEnabled(false);//是否可以旋转
//        radarChart.setLabelFor(0);
//        radarChart.setContextClickable(true);
        radarChart.setData(radarData);//设置数据
        Legend legend = radarChart.getLegend();//设置比例图片标示，就是那一组Y的value
        legend.setForm(Legend.LegendForm.CIRCLE);//样式：SQUARE（正方形）
        legend.setFormSize(8f);//样式图形大小
        legend.setTextColor(Color.BLACK);//设置颜色
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);//圆点的显示位置
//        科目名称
//        XAxis xAxis = radarChart.getXAxis();
//        xAxis.setTextSize(getResources().getDimension(R.dimen.tv_10));
//        xAxis.setTextColor(getResources().getColor(R.color.ldt_tv));
//      Y轴的分度值
        YAxis yAxis = radarChart.getYAxis();
        yAxis.setTextSize(getResources().getDimension(R.dimen.tv_4));
        yAxis.setTextColor(getResources().getColor(R.color.ldt_tv));
        yAxis.setAxisMinValue(0);
        yAxis.setAxisMaxValue(1.0f);
        yAxis.setLabelCount(6,true);
        yAxis.setStartAtZero(true);
        yAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                DecimalFormat decimalFormat = new DecimalFormat("0.0");
                String s = decimalFormat.format(value);
                return s;
            }
        });
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("GeneralActivity_GeneralAllFragment"); //统计页面，"MainScreen"为页面名称，可自定义
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("GeneralActivity_GeneralAllFragment");
    }
}
