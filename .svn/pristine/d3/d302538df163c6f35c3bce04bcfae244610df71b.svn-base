package net.iclassmate.teacherspace.ui.fragment.single;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.adapter.FragmentSingleAdapter;
import net.iclassmate.teacherspace.adapter.single.SummaryInfoAdapter;
import net.iclassmate.teacherspace.bean.Student;
import net.iclassmate.teacherspace.bean.single.BackSliders;
import net.iclassmate.teacherspace.bean.single.ExcellentPersons;
import net.iclassmate.teacherspace.bean.single.Improvers;
import net.iclassmate.teacherspace.bean.single.Single;
import net.iclassmate.teacherspace.bean.single.SingleAll;
import net.iclassmate.teacherspace.bean.single.SingleTotalSituation;
import net.iclassmate.teacherspace.bean.single.SummaryInfoDetails;
import net.iclassmate.teacherspace.ui.fragment.LazyFragment;
import net.iclassmate.teacherspace.view.FullListView;

import java.util.ArrayList;
import java.util.List;

public class SingleAllFragment extends LazyFragment implements AdapterView.OnItemClickListener {
    // 标志位，标志已经初始化完成。
    private List<Object> listAll;
    private List<Object> listXb;
    private List<Object> listJb;
    private List<Object> listTb;

    private FragmentSingleAdapter adapter;
    private SummaryInfoAdapter summaryInfoAdapter;
    private LinearLayout linearLayout;
    private boolean[] listState;
    private List<FullListView> listViews;
    private SingleAll singleAll;
    private List<BaseAdapter> listAdapter;
    private boolean isLoaded;
    private int cur_tv;
    private TextView tv_Top;
    private TextView tv1, tv2;

    private View titleView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_all, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        linearLayout = (LinearLayout) view.findViewById(R.id.fragment_single_all_linear);
        titleView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_single_all_tittle, null);
        tv1 = (TextView) titleView.findViewById(R.id.fragment_single_all_title_tv_1);
        tv2 = (TextView) titleView.findViewById(R.id.fragment_single_all_title_tv_2);
        linearLayout.addView(titleView);
        listState = new boolean[4];
        listViews = new ArrayList<>();
        listAdapter = new ArrayList<>();

        addView();
        addData();
    }

    private void addView() {
        View v = null;
        FullListView listView = null;
        ImageView imageView = null;
        for (int i = 0; i < 4; i++) {
            if (i == 1) {
                v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_single_test_kgtj_listview, null);
                listView = (FullListView) v.findViewById(R.id.fragment_kgtj_ListView);
                imageView = (ImageView) v.findViewById(R.id.fragment_title_kgtj_iv);
                tv_Top = (TextView) v.findViewById(R.id.fragment_title_kgtj_tv);
                tv_Top.setText("※Top50");
            } else {
                v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_exam_listview, null);
                listView = (FullListView) v.findViewById(R.id.fragment_ListView);
                imageView = (ImageView) v.findViewById(R.id.fragment_title_iv);
            }
            listView.setOnItemClickListener(this);
            listViews.add(listView);
            linearLayout.addView(v);
            v = null;

            if (i == 0) {
                summaryInfoAdapter = new SummaryInfoAdapter(getActivity());
                summaryInfoAdapter.setFlag(listState[0]);
                listAdapter.add(summaryInfoAdapter);
                listView.setAdapter(summaryInfoAdapter);
            } else {
                adapter = new FragmentSingleAdapter(getActivity());
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

    public void sendDataToFragment(int index, Object o) {
        if (o != null) {
            if (o instanceof SingleAll) {
                singleAll = (SingleAll) o;
                cur_tv = index;
                addData();
            }
        }
    }

    private void addData() {
        if (isLoaded) {
            setTv(cur_tv);
            setSummary(cur_tv);
            setExcellent(cur_tv);
            setImprovers(cur_tv);
            setBackSliders(cur_tv);
        } else {
            isLoaded = true;
        }
    }

    private void setTv(int index) {
        SingleTotalSituation situation = singleAll.getList().get(index).getSingleTotalSituation();
        if ((situation.getSeName() == null || situation.getSeName().equals("null")) &&
                (situation.getSeDate() == null || situation.getSeDate().equals("null"))) {
            titleView.setVisibility(View.GONE);
            return;
        }
        tv1.setText(situation.getSeName());
        String time = situation.getSeDate();
        if (time != null && time.contains(" ")) {
            time = time.substring(0, time.indexOf(" "));
        }
        tv2.setText(time);
    }

    public void textViewSelectedChanged(int index) {
        if (index == cur_tv) return;
        setSummary(index);
        setExcellent(index);
        setImprovers(index);
        setBackSliders(index);

        cur_tv = index;
    }

    //总体情况
    private void setSummary(int index) {
        List<SummaryInfoDetails> listSI = singleAll.getList().get(index).getSingleTotalSituation().getListSummaryInfoDetails();
        listAll = new ArrayList<Object>();
        Student s = new Student();
        s.setsClass("班级");
        s.setName("班级平均分");
        s.setScore("排名");
        s.setRankChange("排名变化");
        listAll.add(s);
        if (listSI == null || listSI.size() < 1) {
            return;
        }
        for (int i = 0; i < listSI.size(); i++) {
            SummaryInfoDetails sI = listSI.get(i);
            s = new Student();
            s.setsClass(sI.getClassName());
            //Log.i("info","年级平均分="+sI.getUnitAvgScore());
            boolean isDouble = isDouble(sI.getUnitAvgScore() + "");
            String avg = "";
            if (isDouble) {
                avg = String.format("%.2f", sI.getUnitAvgScore());
            } else {
                avg = sI.getUnitAvgScore() + "";
            }
            s.setName(avg);
            s.setScore(sI.getUnitOrder());
            //Log.i("info", "排名变化=" + sI.getOffsetOrder());
            if (sI.getOffsetOrder() != null && !sI.getOffsetOrder().equals("") &&
                    !sI.getOffsetOrder().equals("--") && !sI.getOffsetOrder().equals("--")) {
                int score = Integer.parseInt(sI.getOffsetOrder());
                int n = Math.abs(score);
                if (score > 0) {
                    s.setRankChange("↑" + n);
                } else if (score < 0) {
                    s.setRankChange("↓" + n);
                } else {
                    s.setRankChange(n + "");
                }
            } else {
                s.setRankChange("--");
            }
            listAll.add(s);
        }
        notifyData(0);
        //Log.i("info", "更新数据=" + listAll.size());
    }

    //学霸榜
    private void setExcellent(int index) {
        listXb = new ArrayList<Object>();
        Student s = new Student();
        s.setsClass("班级");
        s.setName("学生");
        s.setScore("分数");
        s.setRankChange("年级排名");
        listXb.add(s);
        List<ExcellentPersons> listExcellentPersons = singleAll.getList().get(index).getSingleTotalSituation().getListExcellentPersons();
        Single single = singleAll.getList().get(index);
        tv_Top.setText("※Top" + listExcellentPersons.size());
        if (listExcellentPersons == null || listExcellentPersons.size() < 1) {
            return;
        }
        for (int i = 0; i < listExcellentPersons.size(); i++) {
            Student student = new Student();
            ExcellentPersons excellentPersons = listExcellentPersons.get(i);
            student.setSchoolId(single.getSchoolId());
            student.setMeId(single.getMeId());
            student.setSeId(single.getSeId());
            student.setStudentId(excellentPersons.getStudentId());

            student.setsId(excellentPersons.getStudentId() + "");
            student.setsClass(excellentPersons.getClassName());
            student.setName(excellentPersons.getStudentName());
            //Log.i("info","班级="+student.getsClass()+",姓名="+student.getName());
            boolean isDouble = isDouble(excellentPersons.getScore() + "");
            String score = "";
            if (isDouble) {
                score = String.format("%.1f", excellentPersons.getScore());
            } else {
                score = excellentPersons.getScore() + "";
            }
            student.setScore(score);

            String ret = excellentPersons.getGradeOrderOffset();
            String gradeOrder = "";
            if (ret != null && !ret.equals("") && !ret.equals("--") && !ret.equals("--")) {
                int n = Integer.parseInt(excellentPersons.getGradeOrderOffset());
                if (n > 0) {
                    gradeOrder = excellentPersons.getGradeOrder() + "( ↑" + n + " )";
                } else if (n < 0) {
                    n = Math.abs(n);
                    gradeOrder = excellentPersons.getGradeOrder() + "( ↓" + n + " )";
                } else {
                    gradeOrder = excellentPersons.getGradeOrder() + "( " + 0 + " )";
                }
                student.setRankChange(gradeOrder);
            } else {
                student.setRankChange(excellentPersons.getGradeOrder() + "（ -- ）");
            }
            listXb.add(student);
        }
        notifyData(1);
    }

    //进步榜
    private void setImprovers(int index) {
        listJb = new ArrayList<Object>();
        Student s = new Student();
        s.setsClass("班级");
        s.setName("学生");
        s.setScore("分数");
        s.setRankChange("年级排名");
        listJb.add(s);
        List<Improvers> listI = singleAll.getList().get(index).getSingleTotalSituation().getListImprovers();
        Single single = singleAll.getList().get(index);
        if (listI == null || listI.size() < 1) {
            return;
        }
        for (int i = 0; i < listI.size(); i++) {
            Student student = new Student();
            student.setSchoolId(single.getSchoolId());
            student.setMeId(single.getMeId());
            student.setSeId(single.getSeId());

            Improvers improvers = listI.get(i);

            student.setStudentId(improvers.getStudentId());
            student.setsId(improvers.getStudentId() + "");
            student.setsClass(improvers.getClassName());
            student.setName(improvers.getStudentName());
            boolean isDouble = isDouble(improvers.getScore() + "");
            String score = "";
            if (isDouble) {
                score = String.format("%.1f", improvers.getScore());
            } else {
                score = improvers.getScore() + "";
            }
            student.setScore(score);
            String ret = improvers.getGradeOrderOffset();
            if (ret != null && !ret.equals("--") && !ret.equals("--")) {
                int n = Integer.parseInt(ret);
                if (n == 0) {
                    student.setRankChange(improvers.getGradeOrder() + "( 0 )");
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
    private void setBackSliders(int index) {
        listTb = new ArrayList<>();
        Student s = new Student();
        s.setsClass("班级");
        s.setName("学生");
        s.setScore("分数");
        s.setRankChange("年级排名");
        listTb.add(s);
        List<BackSliders> listBs = singleAll.getList().get(index).getSingleTotalSituation().getListBackSliders();
        Single single = singleAll.getList().get(index);
        if (listBs == null || listBs.size() < 1) {
            return;
        }
        for (int i = 0; i < listBs.size(); i++) {
            Student student = new Student();
            student.setSchoolId(single.getSchoolId());
            student.setMeId(single.getMeId());
            student.setSeId(single.getSeId());

            BackSliders sliders = listBs.get(i);
            student.setStudentId(sliders.getStudentId());
            student.setsId(sliders.getStudentId() + "");
            student.setsClass(sliders.getClassName());
            student.setName(sliders.getStudentName());
            String score = "";
            boolean isDouble = isDouble(sliders.getScore() + "");
            if (isDouble) {
                score = String.format("%.1f", sliders.getScore());
            } else {
                score = sliders.getScore() + "";
            }
            student.setScore(score);

            String ret = sliders.getGradeOrderOffset();
            if (ret != null && !ret.equals("") && !ret.equals("--") && !ret.equals("--")) {
                int n = Integer.parseInt(sliders.getGradeOrderOffset());
                n = Math.abs(n);
                if (n == 0) {
                    student.setRankChange(sliders.getGradeOrder() + "( 0 )");
                } else {
                    student.setRankChange(sliders.getGradeOrder() + "( ↓" + n + " )");
                }
            } else {
                student.setRankChange(sliders.getGradeOrder() + "( -- )");
            }
            listTb.add(student);
        }
        notifyData(3);
    }

    private void notifyData(int index) {
        if (listViews == null || listViews.size() < 1) return;
        FullListView listView = listViews.get(index);
        if (index == 0) {
            summaryInfoAdapter = new SummaryInfoAdapter(getActivity(), listAll);
            summaryInfoAdapter.setFlag(listState[index]);
            listView.setAdapter(summaryInfoAdapter);
        } else {
            if (index == 1) {
                adapter = new FragmentSingleAdapter(getActivity(), listXb);
            } else if (index == 2) {
                adapter = new FragmentSingleAdapter(getActivity(), listJb);
            } else if (index == 3) {
                adapter = new FragmentSingleAdapter(getActivity(), listTb);
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

        Student s = null;
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

    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            if (value.contains("."))
                return true;
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("SingleReportActivity_SingleAllFragment"); //统计页面，"MainScreen"为页面名称，可自定义
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("SingleReportActivity_SingleAllFragment");
    }
}
