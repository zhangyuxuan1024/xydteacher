package net.iclassmate.teacherspace.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.umeng.analytics.MobclickAgent;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.adapter.FragmentExamAdapter;
import net.iclassmate.teacherspace.bean.exam.Exam;
import net.iclassmate.teacherspace.bean.exam.ExamInfo;
import net.iclassmate.teacherspace.bean.exam.ExamLists;
import net.iclassmate.teacherspace.bean.exam.MultiExamInfo;
import net.iclassmate.teacherspace.bean.exam.SingleExamInfos;
import net.iclassmate.teacherspace.constant.Constants;
import net.iclassmate.teacherspace.ui.activity.GeneralActivity;
import net.iclassmate.teacherspace.ui.activity.SingleReportActivity;
import net.iclassmate.teacherspace.utils.DataCallback;
import net.iclassmate.teacherspace.utils.LoadExamData;
import net.iclassmate.teacherspace.utils.NetWorkUtils;
import net.iclassmate.teacherspace.utils.UIUtils;
import net.iclassmate.teacherspace.view.FullListView;
import net.iclassmate.teacherspace.view.TitleBar;
import net.iclassmate.teacherspace.view.loading.LoadingHelper;
import net.iclassmate.teacherspace.view.loading.LoadingListener;
import net.iclassmate.teacherspace.view.pullrefreshview.MyListener;
import net.iclassmate.teacherspace.view.pullrefreshview.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class ExamFragment extends LazyFragment implements AdapterView.OnItemClickListener, DataCallback, LoadingListener, View.OnTouchListener {
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    private boolean isFirstLoad;
    private TitleBar titleBar;
    private LinearLayout linearLayout;
    private FullListView listView;
    private FragmentExamAdapter adapter;
    private List<FullListView> listViews;

    private int cur_selected;
    //private boolean flag;
    private ExamLists examLists;
    private boolean isLoaded;
    private List<Object> list;
    private List<List<Object>> listAll;
    private List<FragmentExamAdapter> listAdapter;
    private boolean state;
    private LoadingHelper loadingHelper;
    private boolean isShowLoading;
    private boolean isNoNetWork;
    private PullToRefreshLayout pull;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exam, container, false);
        initView(view);
        isPrepared = true;
        lazyLoad();
        return view;
    }

    private void checkState(boolean flag) {
        if (!NetWorkUtils.isNetworkAvailable(UIUtils.getContext())) {
            loadingHelper.ShowError("请检查您的网络连接");
            isShowLoading = true;
            isNoNetWork = true;
        }

        if (flag && !isNoNetWork) {
            loadingHelper.ShowError("服务器繁忙，请稍后再试！");
            isShowLoading = true;
        }
    }

    private void initView(View view) {
        pull = (PullToRefreshLayout) view.findViewById(R.id.pull);
        pull.setOnRefreshListener(new MyListener());
        pull.setOnTouchListener(this);
        linearLayout = (LinearLayout) view.findViewById(R.id.fragment_exam_linear);
        listViews = new ArrayList<FullListView>();
        listAll = new ArrayList<>();
        listAdapter = new ArrayList<>();
        loadingHelper = new LoadingHelper(
                view.findViewById(R.id.loading_prompt_relative),
                view.findViewById(R.id.loading_empty_prompt_linear));

        cur_selected = 0;
        state = true;
        addView2();
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || isFirstLoad) {
            return;
        }
        loadData();
        isFirstLoad = true;
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        String classCode = sharedPreferences.getString("classCode", "");
        String courseId = sharedPreferences.getString("courseId", "");
        String gradeId = sharedPreferences.getString("gradeId", "");
        String roleId = sharedPreferences.getString("roleId", "");
        String schoolId = sharedPreferences.getString("schoolId", "");
        String termCode = sharedPreferences.getString("termCode", "");
        String userId = sharedPreferences.getString("userId", "");
        String enterYear = sharedPreferences.getString("enterYear", "");
        if (!isShowLoading) {
            loadingHelper.ShowLoading();
            loadingHelper.SetListener(this);
            isShowLoading = true;
        }
        new LoadExamData(classCode, courseId, gradeId, roleId, schoolId, termCode, userId, enterYear, this).execute(Constants.EXAM);
    }

    @Override
    public void sendData(Object object) {
        if (object != null) {
            if (object instanceof ExamLists) {
                state = true;
                cur_selected = -1;
                pull.refreshFinish(PullToRefreshLayout.SUCCEED);
                pull.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                examLists = (ExamLists) object;
                addView2();
                cur_selected = 0;
            } else if (object instanceof Integer) {
                checkState(true);
                pull.refreshFinish(PullToRefreshLayout.FAIL);
                pull.loadmoreFinish(PullToRefreshLayout.FAIL);
            }
        } else {
            checkState(true);
            pull.refreshFinish(PullToRefreshLayout.FAIL);
            pull.loadmoreFinish(PullToRefreshLayout.FAIL);
        }
    }

    private void addView2() {
        if (isLoaded) {
            setData(0);
        } else {
            isLoaded = true;
        }
    }

    private void setData(int index) {
        listAll.clear();
        listViews.clear();
        linearLayout.removeAllViews();

        boolean b = false;
        List<Exam> examList = examLists.getList();
        //Log.i("info", "数据长度=" + examList.size());
        for (int i = 0; i < examList.size(); i++) {
            list = new ArrayList<Object>();
            Exam exam = examList.get(i);
            ExamInfo examInfo = exam.getExamInfo();
            MultiExamInfo multiExamInfo = exam.getMultiExamInfo();
            List<SingleExamInfos> singleExamInfosesList = exam.getSingleExamInfosesList();
            if (examInfo != null) {
                list.add(examInfo);
            }
            if (multiExamInfo != null && multiExamInfo.getMeName() != null) {
                list.add(multiExamInfo);
            }
            if (singleExamInfosesList != null && singleExamInfosesList.size() > 0) {
                list.addAll(singleExamInfosesList);
            }

            if (i == index) {
                b = state;
            } else {
                b = false;
            }
            final View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_listview, null);
            if (isShowLoading) {
                loadingHelper.HideLoading(View.INVISIBLE);
                isShowLoading = false;
            }
            linearLayout.addView(view);

            adapter = new FragmentExamAdapter(getActivity(), list, b);
            listView = (FullListView) view.findViewById(R.id.fragment_ListView);
            listView.setOnItemClickListener(this);
            listViews.add(listView);
            listView.setAdapter(adapter);
            listAll.add(list);
            listAdapter.add(adapter);
        }
    }

    private void notifyData(int index) {
        if (index == cur_selected) {
            state = !state;
        } else {
            cur_selected = index;
            state = true;
        }
        setData(index);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int index = listViews.indexOf(parent);
        listViewClick(index, position);
    }

    private void listViewClick(int index, int position) {
        //Log.i("info", "点击了" + index + " ListView,的" + position);
        List<Object> objectList = listAll.get(index);
        ExamInfo examInfo = (ExamInfo) objectList.get(0);
        Object object = objectList.get(position);
        if (position == 0) {
            notifyData(index);
        } else if (object instanceof MultiExamInfo) {
            MultiExamInfo multiExamInfo = (MultiExamInfo) objectList.get(1);
            Intent intent = new Intent(getActivity(), GeneralActivity.class);
            intent.putExtra("examInfo", examInfo);
            intent.putExtra("multiExamInfo", multiExamInfo);
            startActivity(intent);
        } else if (object instanceof SingleExamInfos) {
            SingleExamInfos singleExamInfos = (SingleExamInfos) objectList.get(position);
            singleExamInfos.setMsgId(null);
            singleExamInfos.setIsReaded(1);
            Intent intent = new Intent(getActivity(), SingleReportActivity.class);
            intent.putExtra("examInfo", examInfo);
            intent.putExtra("singleExamInfos", singleExamInfos);
            startActivity(intent);
        }
    }

    @Override
    public void OnRetryClick() {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (NetWorkUtils.isNetworkAvailable(UIUtils.getContext())) {
            if (pull.getCurrentState() == PullToRefreshLayout.LOADING || pull.getCurrentState() == PullToRefreshLayout.REFRESHING) {
                isShowLoading = true;
                loadData();
            }
        }
        return true;
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("MainActivity_ExamFragment"); //统计页面，"MainScreen"为页面名称，可自定义
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MainActivity_ExamFragment");
    }
}
