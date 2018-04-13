package net.iclassmate.teacherspace.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.umeng.analytics.MobclickAgent;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.bean.general.SingleExamInfos;
import net.iclassmate.teacherspace.bean.spaper.ExamPaperUrls;
import net.iclassmate.teacherspace.bean.spaper.ExamQuestionPapers;
import net.iclassmate.teacherspace.bean.spaper.StudentExamPapers;
import net.iclassmate.teacherspace.bean.text.TextPager;
import net.iclassmate.teacherspace.constant.Constants;
import net.iclassmate.teacherspace.ui.activity.single.StudentPaperActivity;
import net.iclassmate.teacherspace.utils.JsonUtils;
import net.iclassmate.teacherspace.utils.NetWorkUtils;
import net.iclassmate.teacherspace.utils.UIUtils;
import net.iclassmate.teacherspace.view.MyScoreView;
import net.iclassmate.teacherspace.view.loading.LoadingHelper;
import net.iclassmate.teacherspace.view.loading.LoadingListener;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by xydbj on 2016.3.19.
 */
public class GeneralTextActivity extends FragmentActivity implements View.OnClickListener,LoadingListener{
    private TextView general_text_title, general_paper_item_tv, general_text_item_tv,general_text_item_score;
    private ImageView general_text_back, general_paper_item_iv, general_text_item_iv;
    private MyScoreView general_paper_item_scoreView;
    private List<SingleExamInfos> singleExamInfosList;
    private HttpUtils httpUtils;
    private String schoolId, meId, seId, studentid;
    private TextPager textpager = new TextPager();
    private LinearLayout general_text_item_linearLayout, general_text_linearLayout;
    private LoadingHelper loadingHelper;
    private boolean isShowLoading,isNoNetWork;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_general_text);
        initView();
        receiveData();
    }

    public void initView() {
        general_text_title = (TextView) findViewById(R.id.general_text_title);
        general_text_back = (ImageView) findViewById(R.id.general_text_back);
        general_text_linearLayout = (LinearLayout) findViewById(R.id.general_text_linearLayout);
        general_text_back.setOnClickListener(this);
        loadingHelper = new LoadingHelper(
                findViewById(R.id.loading_prompt_relative),
                findViewById(R.id.loading_empty_prompt_linear));

        checkState(false);
        if (!isShowLoading) {
            loadingHelper.ShowLoading();
            loadingHelper.SetListener(this);
            isShowLoading = true;
        }
    }

    public void receiveData() {
        Intent intent = getIntent();
        studentid = intent.getStringExtra("id");
        String studentname = intent.getStringExtra("name");
        singleExamInfosList = (List<SingleExamInfos>) intent.getSerializableExtra("list");
        String meIId = singleExamInfosList.get(0).getScore() + "";
        String arr[] = meIId.split("[.]");
        meId = arr[0];
        schoolId = singleExamInfosList.get(0).getSeFullScore() + "";
        seId = "-1";
        general_text_title.setText(studentname);
        Log.i("miss", "试卷url=" + Constants.GENERAL_TEXT + schoolId + "/" + meId + "/" + seId + "/" + studentid);
        getList();
    }

    public void setData() {
        if(textpager.getStudentExamPaperslist() !=null){
            if(isShowLoading){
                loadingHelper.HideLoading(View.INVISIBLE);
            }
            int studentExamPaperssize = textpager.getStudentExamPaperslist().size();
            for (int i = 0; i < studentExamPaperssize; i++) {
                View view = LayoutInflater.from(this).inflate(R.layout.activity_general_text_item, null);
                general_text_item_linearLayout = (LinearLayout) view.findViewById(R.id.general_text_item_linearLayout);
                general_text_item_iv = (ImageView) view.findViewById(R.id.general_text_item_iv);
                general_text_item_tv = (TextView) view.findViewById(R.id.general_text_item_tv);
                general_text_item_score = (TextView) view.findViewById(R.id.general_text_item_score);
                general_text_item_score.setText(textpager.getStudentExamPaperslist().get(i).getPersonScore()+"分");
                general_text_item_score.setVisibility(View.INVISIBLE);
                general_text_item_tv.setText(textpager.getStudentExamPaperslist().get(i).getPaperName());
                int courseId = Integer.parseInt(textpager.getStudentExamPaperslist().get(i).getCourseId());
                setImage(courseId);
                int examPaperUrlssize = textpager.getStudentExamPaperslist().get(i).getExamPaperUrlsList().size();
                for (int j = 0; j < examPaperUrlssize; j++) {
                    View view1 = LayoutInflater.from(this).inflate(R.layout.general_paper_item, null);
                    general_paper_item_iv = (ImageView) view1.findViewById(R.id.general_paper_item_iv);
                    general_paper_item_scoreView = (MyScoreView) view1.findViewById(R.id.general_paper_item_scoreView);
                    general_paper_item_scoreView.setScore(textpager.getStudentExamPaperslist().get(i).getPersonScore());
                    if (j == 0) {
                        general_paper_item_scoreView.setVisibility(View.VISIBLE);
                    } else {
                        general_paper_item_scoreView.setVisibility(View.GONE);
                    }
                    general_paper_item_tv = (TextView) view1.findViewById(R.id.general_paper_item_tv);
                    general_paper_item_tv.setText("第(" + (j + 1) + ")页");
                    general_paper_item_iv.setTag(j);
                    final int finalI = i;
                    final int finalJ = j;
                    general_paper_item_iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(GeneralTextActivity.this, StudentPaperActivity.class);
                            intent.putExtra("index", finalJ);
                            StudentExamPapers papers = new StudentExamPapers();

                            papers.setPaperName(textpager.getStudentExamPaperslist().get(finalI).getPaperName());
                            papers.setPersonScore(Double.parseDouble(textpager.getStudentExamPaperslist().get(finalI).getPersonScore()));
                            List<net.iclassmate.teacherspace.bean.text.ExamPaperUrls> examPaperUrlsList = textpager.getStudentExamPaperslist().get(finalI).getExamPaperUrlsList();
                            List<net.iclassmate.teacherspace.bean.spaper.ExamPaperUrls> list = new ArrayList<ExamPaperUrls>();
                            for (int i = 0; i < examPaperUrlsList.size(); i++) {
                                net.iclassmate.teacherspace.bean.spaper.ExamPaperUrls exam = new net.iclassmate.teacherspace.bean.spaper.ExamPaperUrls();
                                net.iclassmate.teacherspace.bean.text.ExamPaperUrls detais = examPaperUrlsList.get(i);
                                exam.setPageUrl(detais.getPageUrl());
                                exam.setPrefixUrl(detais.getPrefixUrl());
                                list.add(exam);
                            }
                            papers.setExamPaperUrlsList(list);

                            List<net.iclassmate.teacherspace.bean.text.ExamQuestionPapers> examQuestionPapersList = textpager.getStudentExamPaperslist().get(finalI).getExamQuestionPapersList();
                            List<net.iclassmate.teacherspace.bean.spaper.ExamQuestionPapers> list1 = new ArrayList<ExamQuestionPapers>();
                            for(int j=0;j<examQuestionPapersList.size();j++){
                                net.iclassmate.teacherspace.bean.spaper.ExamQuestionPapers eqp = new net.iclassmate.teacherspace.bean.spaper.ExamQuestionPapers();
                                net.iclassmate.teacherspace.bean.text.ExamQuestionPapers e = examQuestionPapersList.get(j);
                                eqp.setAnswerIpxywh(e.getAnswerIpxywh());
                                eqp.setDisplayIndex(Integer.parseInt(e.getDisplayIndex()));
                                eqp.setDisplayName(e.getDisplayName());
                                eqp.setEcPage(e.getEcPage());
                                eqp.setFullScore(Double.parseDouble(e.getFullScore()));
                                eqp.setScore(Double.parseDouble(e.getScore()));
                                eqp.setSeId(Integer.parseInt(e.getSeId()));
                                list1.add(eqp);
                            }
                            papers.setExamQuestionPapersList(list1);
                            intent.putExtra("url",papers);
                            startActivity(intent);
                        }
                    });
                    general_text_item_linearLayout.addView(view1);
                }
                general_text_linearLayout.addView(view);
            }
        }else{
            checkState(true);
        }
    }

    //通过拼接的接口获取数据
    public void getList() {
        httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, Constants.GENERAL_TEXT + schoolId + "/" + meId + "/" + seId + "/" + studentid, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String str = responseInfo.result;
                textpager = JsonUtils.StartTextPagerJson(str);
                setData();
            }

            @Override
            public void onFailure(HttpException error, String msg){
                error.printStackTrace();
                Log.i("miss", "网络请求失败：" + error.getMessage());
                checkState(true);
            }
        });
    }

    private void checkState(boolean flag) {
        if (!NetWorkUtils.isNetworkAvailable(UIUtils.getContext())) {
            loadingHelper.ShowError("请检查您的网络链接！");
            isShowLoading = true;
            isNoNetWork = true;
        }

        if (flag && !isNoNetWork) {
            loadingHelper.ShowError("没有试卷信息！");
            isShowLoading = true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.general_text_back:
                GeneralTextActivity.this.finish();//返回按钮
                break;
        }
    }

    public void setImage(int id) {
        if (id == 201) {
            general_text_item_iv.setImageResource(R.mipmap.yuwen);
        } else if (id == 202) {
            general_text_item_iv.setImageResource(R.mipmap.shuxue);
        } else if (id == 203) {
            general_text_item_iv.setImageResource(R.mipmap.yingyu);
        } else if (id == 204) {
            general_text_item_iv.setImageResource(R.mipmap.wuli);
        } else if (id == 205) {
            general_text_item_iv.setImageResource(R.mipmap.huaxue);
        } else if (id == 206) {
            general_text_item_iv.setImageResource(R.mipmap.shengwu);
        } else if (id == 207) {
            general_text_item_iv.setImageResource(R.mipmap.zhengzhi);
        } else if (id == 208) {
            general_text_item_iv.setImageResource(R.mipmap.lishi);
        } else if (id == 209) {
            general_text_item_iv.setImageResource(R.mipmap.dili);
        } else if (id == 210) {
            general_text_item_iv.setImageResource(R.mipmap.wenzong);
        } else if (id == 211) {
            general_text_item_iv.setImageResource(R.mipmap.lizong);
        } else if (id == 212) {
            general_text_item_iv.setImageResource(R.mipmap.yinyue);
        } else if (id == 213) {
            general_text_item_iv.setImageResource(R.mipmap.xinxi);
        } else if (id == 214) {
            general_text_item_iv.setImageResource(R.mipmap.meishu);
        } else if (id == 216) {
            general_text_item_iv.setImageResource(R.mipmap.tiyu);
        } else if (id == 221) {
            general_text_item_iv.setImageResource(R.mipmap.sixiangpinde);
        } else if (id == 222) {
            general_text_item_iv.setImageResource(R.mipmap.kexue);
        } else {
            general_text_item_iv.setImageResource(R.mipmap.moren);
        }
    }

    @Override
    public void OnRetryClick() {

    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("GeneralTextActivity"); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this);          //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("GeneralTextActivity"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
        MobclickAgent.onPause(this);
    }
}
