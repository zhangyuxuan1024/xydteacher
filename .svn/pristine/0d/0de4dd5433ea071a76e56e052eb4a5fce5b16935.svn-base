package net.iclassmate.teacherspace.ui.activity.single;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.listener.RadarChartOndrawListener;
import com.github.mikephil.charting.utils.ChosedPoint;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ValueFormatter;
import com.umeng.analytics.MobclickAgent;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.bean.single.QstAnalyzes;
import net.iclassmate.teacherspace.bean.single.QuestionAnalyze;
import net.iclassmate.teacherspace.view.TitleBar;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class LeidtActivity extends FragmentActivity implements TitleBar.TitleOnClickListener, View.OnClickListener {
    private TitleBar titleBar;
    public RadarChart radarChart;
    public ArrayList<String> x;
    public ArrayList<Entry> y;
    public ArrayList<Entry> z1;
    public ArrayList<Entry> z2;
    public ArrayList<RadarDataSet> radarDataSets;
    public RadarData radarData;
    private QuestionAnalyze analyze;
    private boolean flag;
    private ImageView img1, img2;
    private int len, currentindex = 0;

    private LinearLayout linearLayout;
    private TextView tv, tv1, tv2, tv3;
    private List<QstAnalyzes> listQs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_leidt);
        Intent intent = getIntent();
        analyze = (QuestionAnalyze) intent.getSerializableExtra("analyze");
        flag = intent.getBooleanExtra("flag", false);

        initView();
    }

    private void initView() {
        titleBar = (TitleBar) findViewById(R.id.fragment_single_title_bar);
        titleBar.setTitle("知识点雷达图");
        titleBar.setLeftIcon(R.drawable.fragment_back);
        titleBar.setTitleClickListener(this);
        img1 = (ImageView) findViewById(R.id.single_know_img1);
        img2 = (ImageView) findViewById(R.id.single_know_img2);
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        tv = (TextView) findViewById(R.id.single_know_tv);
        tv1 = (TextView) findViewById(R.id.know_tv_1);
        tv2 = (TextView) findViewById(R.id.know_tv_2);
        tv3 = (TextView) findViewById(R.id.know_tv_3);
        linearLayout = (LinearLayout) findViewById(R.id.know_class_linear);

        len = 0;
        x = new ArrayList<String>();
        y = new ArrayList<Entry>();
        z1 = new ArrayList<Entry>();
        z2 = new ArrayList<Entry>();
        listQs = new ArrayList<>();
        radarDataSets = new ArrayList<RadarDataSet>();
        radarChart = (RadarChart) findViewById(R.id.single_know_radar_chart);
        radarChart.setRadarChartOndrawListener(radarCharonDrawListener);
        setBarData();
    }

    private void setBarData() {
        if (analyze != null) {
            radarData = getRadarData();
            showChart();
        }
    }

    /**
     * 初始化数据
     * count 表示坐标点个数，range表示等下y值生成的范围
     */
    public RadarData getRadarData() {
        listQs.clear();
        x.clear();
        y.clear();
        z1.clear();
        z2.clear();
        radarDataSets.clear();

        listQs.addAll(analyze.getListQstAnalyzes());
        len = listQs.size();
        //tv.setText(listQs.get(currentindex%len).getQstName());
        for (int i = 0; i < len; i++) {
            QstAnalyzes qs = listQs.get(i);
            String name = qs.getQstName();
            if (name.length() > 5) {
                name = name.substring(0, 5) + "...";
            }
            x.add(name);
            if (flag) {
                y.add(new Entry((float) qs.getClassProficiency(), i));
            }
            z1.add(new Entry((float) qs.getGradeProficiency(), i));
            z2.add(new Entry((float) qs.getProficiency(), i));
        }

        QstAnalyzes qs = listQs.get(currentindex % len);
        if (flag) {
            RadarDataSet radarDataSet = new RadarDataSet(y, "");
            radarDataSet.setLineWidth(1f);//线宽
            radarDataSet.setColor(Color.parseColor("#4fc1e9"));//现实颜色
            radarDataSet.setFillColor(Color.parseColor("#4fc1e9"));//各科成绩填充的颜色
            radarDataSet.setDrawFilled(true);
            radarDataSets.add(radarDataSet);
            linearLayout.setVisibility(View.VISIBLE);
        } else {
            linearLayout.setVisibility(View.GONE);
        }

        RadarDataSet radarDataSet2 = new RadarDataSet(z1, "");
        radarDataSet2.setLineWidth(1f);//线宽
        radarDataSet2.setColor(Color.parseColor("#ed5565"));//现实颜色
        radarDataSet2.setFillColor(Color.parseColor("#ed5565"));//各科成绩填充的颜色
        radarDataSet2.setDrawFilled(true);
        radarDataSets.add(radarDataSet2);

        radarDataSet2 = new RadarDataSet(z2, "");
        radarDataSet2.setLineWidth(1f);//线宽
        radarDataSet2.setColor(Color.parseColor("#74dd52"));//现实颜色
        radarDataSet2.setFillColor(Color.parseColor("#74dd52"));//各科成绩填充的颜色
        radarDataSet2.setDrawFilled(true);
        radarDataSets.add(radarDataSet2);

        radarData = new RadarData(x, radarDataSets);
        radarData.setDrawValues(false);//是否显示分数
        return radarData;
    }

    public void showChart() {
        radarChart.setDescription("");
        radarChart.setNoDataTextDescription("You need to provide data for the chart.");
        radarChart.setBackgroundColor(Color.WHITE);//背景颜色
        radarChart.setWebLineWidth(1f);//Y轴线宽度
        radarChart.setData(radarData);//设置数据
        radarChart.setRotationEnabled(true);

        Legend legend = radarChart.getLegend();//设置比例图片标示，就是那一组Y的value
        legend.setForm(Legend.LegendForm.SQUARE);//样式：SQUARE（正方形）
        legend.setFormSize(8f);//样式图形大小
        legend.setTextColor(getResources().getColor(R.color.ldt_tv));//设置颜色
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);//圆点的显示位置
        legend.setXEntrySpace(40f);
        legend.setEnabled(false);

        XAxis xAxis = radarChart.getXAxis();
        xAxis.setTextSize(getResources().getDimension(R.dimen.tv_3));
        xAxis.setTextColor(getResources().getColor(R.color.ldt_tv));

        YAxis yAxis = radarChart.getYAxis();
        yAxis.setTextSize(getResources().getDimension(R.dimen.tv_2));
        yAxis.setTextColor(getResources().getColor(R.color.ldt_tv));
        yAxis.setAxisMinValue(0);
        yAxis.setAxisMaxValue(1.0f);
        yAxis.setLabelCount(11, false);
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
    public void onClick(View view) {
        int id = view.getId();
        String name = "";
        switch (id) {
            case R.id.single_know_img1:
                radarChart.spin(150, radarChart.getRotationAngle(),
                        radarChart.getRotationAngle() + radarChart.getSliceAngle(),
                        Easing.EasingOption.EaseInCubic);
                break;
            case R.id.single_know_img2:
                radarChart.spin(150, radarChart.getRotationAngle(),
                        radarChart.getRotationAngle() - radarChart.getSliceAngle(),
                        Easing.EasingOption.EaseInCubic);
                break;
        }
    }

    RadarChartOndrawListener radarCharonDrawListener = new RadarChartOndrawListener() {
        @Override
        public void onEnterOnDrawInRadarchart() {
            currentindex = radarChart.getIndexForAngle(270f);
            ChosedPoint chosepoint = new ChosedPoint();
            //float textsize = Utils.convertDpToPixel(12f);
            float textsize = Utils.convertDpToPixel(0f);
            chosepoint.setPotition(currentindex);
            chosepoint.setTextSize(textsize);
//            chosepoint.setTextClour(Color.RED);
            chosepoint.setTextClour(Color.WHITE);
            radarChart.getXAxisRendererRadarChart().setChosePoint(chosepoint);

            tv.setText(listQs.get(currentindex % len).getQstName());
            //setBarData();
            QstAnalyzes qs = listQs.get(currentindex % len);
            String str = "班级得分率：" + String.format("%.2f", qs.getClassProficiency() * 100) + "%";
            tv1.setText(str);
            str = "年级得分率：" + String.format("%.2f", qs.getGradeProficiency() * 100) + "%";
            tv2.setText(str);
            str = "考试得分率：" + String.format("%.2f", qs.getProficiency() * 100) + "%";
            tv3.setText(str);
        }
    };


    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("LeidtActivity"); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this);          //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("LeidtActivity"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
        MobclickAgent.onPause(this);
    }
}
