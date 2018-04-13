package net.iclassmate.teacherspace.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import net.iclassmate.teacherspace.BuildConfig;
import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.adapter.MyNoticeAdapter;
import net.iclassmate.teacherspace.application.MyApplication;
import net.iclassmate.teacherspace.bean.exam.SingleExamInfos;
import net.iclassmate.teacherspace.bean.notice.Notice_Message;
import net.iclassmate.teacherspace.constant.Constants;
import net.iclassmate.teacherspace.ui.activity.MainActivity;
import net.iclassmate.teacherspace.ui.activity.SingleReportActivity;
import net.iclassmate.teacherspace.ui.activity.dialog.BoundPhoneDialogActivity;
import net.iclassmate.teacherspace.utils.DataCallback;
import net.iclassmate.teacherspace.utils.FileUtils;
import net.iclassmate.teacherspace.utils.HttpManger;
import net.iclassmate.teacherspace.utils.JsonUtils;
import net.iclassmate.teacherspace.utils.NetWorkUtils;
import net.iclassmate.teacherspace.utils.ToastUtils;
import net.iclassmate.teacherspace.utils.UIUtils;
import net.iclassmate.teacherspace.view.MyListView;
import net.iclassmate.teacherspace.view.loading.LoadingHelper;
import net.iclassmate.teacherspace.view.loading.LoadingListener;
import net.iclassmate.teacherspace.view.pullrefreshview.PullToRefreshBase;
import net.iclassmate.teacherspace.view.pullrefreshview.PullToRefreshListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticeFragment extends LazyFragment implements DataCallback,LoadingListener{
    public static final int NOTICE_START_ACTIVITY_CODE=100;
    public static final int NOTICE_ACTIVITY_CODE = 101;

    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    private boolean isFirstLoad;
    private TextView tv_notice;
    private PullToRefreshListView mPullRefreshListView;
    private boolean loading = false;
//    private boolean isFirst = true;
    private boolean isEnd = false;
//    private int currentPage = 1;
    private Handler mHandler;
    private Context mContext;
    private MyNoticeAdapter myNoticeAdapter;
    private SharedPreferences msharedPreferences;
    private HttpManger httpManger;
    private Notice_Message notice_message;
    private LoadingHelper loadingHelper;
    private int infoType;
    private MainActivity activity;
    private String schoolid;
    private String userid;
    private double versionCode;
    private MyApplication myApplication;

    public NoticeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice, container, false);

        mContext=getActivity();

        //XXX初始化view的各控件
        initView(view);
        isPrepared = true;
        lazyLoad();
        return view;
    }

    private void initValues() {
        msharedPreferences=mContext.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        String mobileNum = msharedPreferences.getString("mobileNum", "");
         schoolid=msharedPreferences.getString("schoolId","");
         userid=msharedPreferences.getString("userId","");
        Log.i("NoticeFragment",mobileNum+";"+schoolid+";"+userid);
        initData(schoolid,userid);

        double  nowCode= BuildConfig.VERSION_CODE;
        Log.i("通知界面绑定手机号","---------------"+nowCode+",,,,"+versionCode);
        if (mobileNum.equals("null")&& versionCode<=nowCode){

            Intent intent=new Intent(mContext, BoundPhoneDialogActivity.class);

            intent.putExtra("from","NoticeFragment");
            intent.putExtra("type",1);
            intent.putExtra("resultType",1);
            intent.putExtra("message","为确保账号安全，请绑定手机号");
            startActivity(intent);
        }
        isFirstLoad=false;
    }

    private void initData(String schoolid, String userid) {
        String[] schoolId=schoolid.split(",");
        String[] userId=userid.split(",");
        Log.i("通知界面请求：",schoolId[0]+";"+userId[0]);

        loadData(schoolId[0],userId[0]);
    }

    private void loadData(String s, String s1) {
        httpManger=new HttpManger(this);
        try {
            String result2SD= FileUtils.read2Sd("xyd_notice.dat");
            if (NetWorkUtils.isNetworkAvailable(mContext)) {
                if (result2SD==null || result2SD.equals("")){
                    loadingHelper.ShowLoading();
                    httpManger.getNoticeData(s, s1);
                }else {
                    if (isFirstLoad){
                        loadingHelper.ShowLoading();
                        httpManger.getNoticeData(s, s1);
                    }else {
                        showData(result2SD);
                    }
                }

            }else {

                if (result2SD==null || result2SD.equals("")){
                    loadingHelper.ShowError("请检查您的网络链接！");
                }else {
                    showData(result2SD);
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private void showData(String result2SD) {
            notice_message = JsonUtils.jsonNoticeInfo(result2SD);
            Log.i("showData", "解析后数据为=" + notice_message.toString());
            if (notice_message.getResultCode()==0){
                loadItemData(notice_message);
            }else {
                loadingHelper.ShowError(notice_message.getResultDesc());
            }

    }

    private void initView(View view) {
        loadingHelper = new LoadingHelper(
                view.findViewById(R.id.loading_prompt_relative),
                view.findViewById(R.id.loading_empty_prompt_linear));
        loadingHelper.SetListener(this);

        activity= (MainActivity) getActivity();
        myApplication= (MyApplication) activity.getApplication();
        versionCode=myApplication.getVersionCode();

        mHandler=new Handler();
        mPullRefreshListView = (PullToRefreshListView) view
                .findViewById(R.id.my_notice_frame);
        isFirstLoad=true;

        mPullRefreshListView.onPullDownRefreshComplete();
        mPullRefreshListView.onPullUpRefreshComplete();


        try {
            initPullRefreshListView();
        }catch (Exception e){
            e.printStackTrace();
        }


        setOnItemOnclick();
    }

    private void setOnItemOnclick() {
        mPullRefreshListView.getRefreshableView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                infoType=notice_message.getNotice_info().getNotice_exam_infoList().get(position).getMsgType();

                if (infoType==1){
                    ToastUtils.show(UIUtils.getContext(),"点击阅卷通知");
                }else if (infoType==2){
                    SingleExamInfos singleExamInfos=new SingleExamInfos();
                    singleExamInfos.setCourseName(notice_message.getNotice_info().getNotice_exam_infoList().get(position).getCourseName());
                    singleExamInfos.setMeId(notice_message.getNotice_info().getNotice_exam_infoList().get(position).getMeId());
                    singleExamInfos.setSeId(notice_message.getNotice_info().getNotice_exam_infoList().get(position).getSeId());
                    singleExamInfos.setSchoolId(notice_message.getNotice_info().getNotice_exam_infoList().get(position).getSchoolId());
                    singleExamInfos.setClassId(notice_message.getNotice_info().getNotice_exam_infoList().get(position).getClassId());
                    singleExamInfos.setMsgId(notice_message.getNotice_info().getNotice_exam_infoList().get(position).getMsgId());
                    singleExamInfos.setIsReaded(notice_message.getNotice_info().getNotice_exam_infoList().get(position).getIsReaded());

                    Intent intent = new Intent(getActivity(), SingleReportActivity.class);
                    intent.putExtra("singleExamInfos", singleExamInfos);
                    intent.putExtra("currentindex",position);
                    startActivityForResult(intent,NOTICE_START_ACTIVITY_CODE);
                }
            }
        });
    }

    private void initPullRefreshListView() {


        // 下拉加载的事件支持
        mPullRefreshListView.setPullLoadEnabled(true);
        // 上拉加载下拉刷新支持
        mPullRefreshListView.setScrollLoadEnabled(false);
        mPullRefreshListView
                .setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<MyListView>() {
                    @Override
                    public void onPullDownToRefresh(PullToRefreshBase<MyListView> refreshView) {
                        // 下拉刷新,请求网络,url(一样),清除之前数据,再次加载一遍
                        if (!loading) {
                            mHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (NetWorkUtils.isNetworkAvailable(UIUtils.getContext())){

                                        httpManger.getNoticeData(schoolid.split(",")[0],userid.split(",")[0]);
                                        myNoticeAdapter.notifyDataSetChanged();
                                        mPullRefreshListView
                                                .onPullDownRefreshComplete();
                                    }else {
                                        mPullRefreshListView
                                                .onPullDownRefreshComplete();
                                    }

                                }
                            }, 1500);
                            return;
                        }
                    }

                    @Override
                    public void onPullUpToRefresh(PullToRefreshBase<MyListView> refreshView) {
                        // 上拉加载,请求网络,url(moreUrl),原有数据的基础上,再添加一部分
                        if (true) {
                            mHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtils.show(UIUtils.getContext(),
                                            "没有更多数据了");
                                    mPullRefreshListView
                                            .onPullUpRefreshComplete();
                                }
                            }, 1500);
                            return;
                        }
                    }
                });
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || !isFirstLoad) {
            return;
        }
        //填充各控件的数据
        initValues();
    }

    @Override
    public void sendData(Object object) {

        loadingHelper.HideLoading(View.INVISIBLE);
        Log.i("sendData", "数据为=" + object.toString());
        String result = object.toString();
        if (!result.equals("404")) {
            notice_message = JsonUtils.jsonNoticeInfo(result);
            Log.i("sendData", "解析后数据为=" + notice_message.toString());
            if (notice_message.getResultCode()==0){
                loadItemData(notice_message);
            }else {
                loadingHelper.ShowError(notice_message.getResultDesc());
            }

        } else {
            loadingHelper.ShowError("服务器繁忙，请稍后再试");
        }


    }


    private void loadItemData(Notice_Message notice_message) {
        myNoticeAdapter=new MyNoticeAdapter(UIUtils.getContext(),notice_message.getNotice_info());


        int totalCount=notice_message.getNotice_info().getTotalCount();
        int unReadCount=notice_message.getNotice_info().getUnReadCount();
        activity.setDisplayNum(unReadCount);
        mPullRefreshListView.setTotalCount(totalCount);
        mPullRefreshListView.getRefreshableView().setAdapter(myNoticeAdapter);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==NOTICE_START_ACTIVITY_CODE){
            switch (resultCode){
                case NOTICE_ACTIVITY_CODE:
                    int position = data.getExtras().getInt("currentindex");
                    int isRead = data.getExtras().getInt("isReaded");
                    Log.i("onActivityResult", position+"------"+isRead);
                    notice_message.getNotice_info().getNotice_exam_infoList().get(position).setIsReaded(1);
                    if (isRead != 1) {
                        myNoticeAdapter.notifyDataSetChanged();
                        int num=notice_message.getNotice_info().getUnReadCount()-1;
                        notice_message.getNotice_info().setUnReadCount(num);
                        activity.setDisplayNum(num);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void OnRetryClick() {
        httpManger.getNoticeData(schoolid.split(",")[0],userid.split(",")[0]);
    }


    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("MainActivity_NoticeFragment"); //统计页面，"MainScreen"为页面名称，可自定义
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MainActivity_NoticeFragment");
    }
}
