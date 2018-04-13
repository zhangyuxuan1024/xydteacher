package net.iclassmate.teacherspace.adapter;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.bean.exam.ExamInfo;
import net.iclassmate.teacherspace.bean.exam.MultiExamInfo;
import net.iclassmate.teacherspace.bean.exam.SingleExamInfos;

import java.util.List;

/**
 * Created by xydbj on 2016/1/29.
 */
public class FragmentExamAdapter extends BaseAdapter {
    private Context context;
    private List<Object> list;
    private boolean flag;

    public FragmentExamAdapter(Context context, List<Object> list, boolean flag) {
        this.context = context;
        this.list = list;
        this.flag = flag;
    }

    public void addData(List<Object> data) {
        list.addAll(data);
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (list != null) {
            if (flag) {
                ret = list.size();
            } else {
                ret = 1;
            }
        }
        return ret;
    }

    public boolean isFlag() {
        return flag;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        int ret = 0;
        if (position == 0) {//第一行
            ret = 0;
        } else if (position > 0) { //展开的其他行
            ret = 1;
        }
        return ret;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        int ret = getItemViewType(position);
        if (ret == 0) {
            view = bindTitle(position, view, viewGroup);
        } else if (ret == 1) {
            view = bindContent(position, view, viewGroup);
        }
        return view;
    }

    private View bindTitle(int position, View view, ViewGroup viewGroup) {
        ViewHolderTitle holderTitle = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_exam_listview_title, viewGroup, false);
            holderTitle = new ViewHolderTitle();
            holderTitle.tv_grade = (TextView) view.findViewById(R.id.fragment_exam_tv_grade);
            holderTitle.tv_school = (TextView) view.findViewById(R.id.fragment_exam_tv_school);
            holderTitle.tv_score = (TextView) view.findViewById(R.id.fragment_exam_tv_score);
            view.setTag(holderTitle);
        } else {
            holderTitle = (ViewHolderTitle) view.getTag();
        }

        Object object = list.get(position);
        if (object instanceof ExamInfo) {
            ExamInfo info = (ExamInfo) object;
            String name = info.getGradeName();
            String s = info.getMeName();
            if (name != null && !name.equals("") && !name.equals("null")) {

            } else if (s.contains("年级")) {
                name = s.substring(s.indexOf("年级") - 1, s.indexOf("年级")) + "年级";
            } else {
                name = "--";
            }
            holderTitle.tv_grade.setText(name);

            if (s != null && s.contains("(") && s.indexOf("(") > 0) {
                s = s.substring(0, s.indexOf("(")) + "\n" + s.substring(s.indexOf("("));
            } else if (s != null && s.contains("（") && s.indexOf("（") > 0) {
                s = s.substring(0, s.indexOf("（")) + "\n" + s.substring(s.indexOf("（"));
            }
            SpannableString sb = new SpannableString(s);
            holderTitle.tv_school.setText(sb);

            s = "年级均分" + info.getMultiExamAvg();
            s = s.substring(0, 4) + "\n" + s.substring(4);
            sb = new SpannableString(s);
            sb.setSpan(new AbsoluteSizeSpan(context.getResources().getDimensionPixelSize(R.dimen.tv_11)), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            sb.setSpan(new AbsoluteSizeSpan(context.getResources().getDimensionPixelSize(R.dimen.tv_15)), 4, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holderTitle.tv_score.setText(sb);
        }
        return view;
    }

    private View bindContent(int position, View view, ViewGroup viewGroup) {
        ViewHolderContent holderContent = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_exam_listview_content, viewGroup, false);
            holderContent = new ViewHolderContent();
            holderContent.img_left = (ImageView) view.findViewById(R.id.fragment_exam_listview_item_image);
            holderContent.tv_lesson = (TextView) view.findViewById(R.id.fragment_exam_listview_item_tv);
            holderContent.img_right = (ImageView) view.findViewById(R.id.fragment_exam_listview_item_more);
            view.setTag(holderContent);
        } else {
            holderContent = (ViewHolderContent) view.getTag();
        }
        Object object = list.get(position);
        if (object instanceof MultiExamInfo) {
            holderContent.img_left.setImageResource(R.mipmap.quanke);
            holderContent.tv_lesson.setText("全科成绩");
        } else if (object instanceof SingleExamInfos) {
            SingleExamInfos infos = (SingleExamInfos) object;
            String name = infos.getCourseName();
            holderContent.tv_lesson.setText(name + "成绩");
            int id = infos.getCourseId();
            if (id == 201) {
                holderContent.img_left.setImageResource(R.mipmap.yuwen);
            } else if (id == 202) {
                holderContent.img_left.setImageResource(R.mipmap.shuxue);
            } else if (id == 203) {
                holderContent.img_left.setImageResource(R.mipmap.yingyu);
            } else if (id == 204) {
                holderContent.img_left.setImageResource(R.mipmap.wuli);
            } else if (id == 205) {
                holderContent.img_left.setImageResource(R.mipmap.huaxue);
            } else if (id == 206) {
                holderContent.img_left.setImageResource(R.mipmap.shengwu);
            } else if (id == 207) {
                holderContent.img_left.setImageResource(R.mipmap.zhengzhi);
            } else if (id == 208) {
                holderContent.img_left.setImageResource(R.mipmap.lishi);
            } else if (id == 209) {
                holderContent.img_left.setImageResource(R.mipmap.dili);
            } else if (id == 210) {
                holderContent.img_left.setImageResource(R.mipmap.wenzong);
            } else if (id == 211) {
                holderContent.img_left.setImageResource(R.mipmap.lizong);
            } else if (id == 212) {
                holderContent.img_left.setImageResource(R.mipmap.yinyue);
            } else if (id == 213) {
                holderContent.img_left.setImageResource(R.mipmap.xinxi);
            } else if (id == 214) {
                holderContent.img_left.setImageResource(R.mipmap.meishu);
            } else if (id == 216) {
                holderContent.img_left.setImageResource(R.mipmap.tiyu);
            } else if (id == 221) {
                holderContent.img_left.setImageResource(R.mipmap.sixiangpinde);
            } else if (id == 222) {
                holderContent.img_left.setImageResource(R.mipmap.kexue);
            } else {
                holderContent.img_left.setImageResource(R.mipmap.moren);
            }
        }
        return view;
    }

    class ViewHolderTitle {
        TextView tv_grade;
        TextView tv_school;
        TextView tv_score;
    }

    class ViewHolderContent {
        ImageView img_left;
        TextView tv_lesson;
        ImageView img_right;
    }
}
