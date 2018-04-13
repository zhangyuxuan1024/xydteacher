package net.iclassmate.teacherspace.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.bean.Student;
import net.iclassmate.teacherspace.bean.single.ClassNum;
import net.iclassmate.teacherspace.bean.single.ClassScore;
import net.iclassmate.teacherspace.bean.single.ClassValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xydbj on 2016/2/2.
 */
public class FragmentSingleTestFAdapter extends BaseAdapter {
    private Context context;
    private List<Object> list;
    private boolean flag;

    public FragmentSingleTestFAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setData(List<Object> data) {
        list.clear();
        if (data != null) {
            list.addAll(data);
        }
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (list != null) {
            ret = list.size();
            if (!flag && ret > 5) {
                ret = 6;
            } else if (flag && ret > 5) {
                ret = ret + 1;
            }
        }
        return ret;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        int ret = 0;
        if (getCount() < 6) {
            ret = 0;
        } else if (getCount() - position == 1) {
            ret = 1;
        } else {
            ret = 0;
        }
        return ret;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (getItemViewType(i) == 0) {
            view = bindView(i, view, viewGroup);
        } else if (getItemViewType(i) == 1) {
            view = bindLastView(i, view, viewGroup);
        }
        setBackColor(i, view);
        return view;
    }

    private View bindLastView(int position, View view, ViewGroup viewGroup) {
        ViewHolderTv holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_single_listview_more, viewGroup, false);
            holder = new ViewHolderTv();
            holder.iv = (ImageView) view.findViewById(R.id.fragment_single_last_tv);
            view.setTag(holder);
        } else {
            holder = (ViewHolderTv) view.getTag();
        }
        if (flag) {
            holder.iv.setImageResource(R.drawable.fragment_single_last_img_shouqi);
        } else {
            holder.iv.setImageResource(R.drawable.fragment_single_last_img_zhankai);
        }
        return view;
    }

    private View bindView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_single_grade_title, viewGroup, false);
            holder = new ViewHolder();
            holder.tv1 = (TextView) view.findViewById(R.id.fragment_single_grade_tv_1);
            holder.tv2 = (TextView) view.findViewById(R.id.fragment_single_grade_tv_2);
            holder.tv3 = (TextView) view.findViewById(R.id.fragment_single_grade_tv_3);
            holder.tv4 = (TextView) view.findViewById(R.id.fragment_single_grade_tv_4);
            holder.tv5 = (TextView) view.findViewById(R.id.fragment_single_grade_tv_5);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Object object = null;
        if (!flag && position == 4) {
            object = list.get(list.size() - 1);
        } else {
            object = list.get(position);
        }
        if (object instanceof ClassScore) {
            ClassScore cs = (ClassScore) object;
            holder.tv1.setText(cs.getsClass());
            holder.tv2.setText(cs.getAvg());
            holder.tv3.setText(cs.getExcellentRate());
            holder.tv4.setText(cs.getPassRate());
            holder.tv5.setText(cs.getLowRate());

        } else if (object instanceof Student) {
            Student s = (Student) object;
            holder.tv1.setText(s.getsClass());
            holder.tv2.setText(s.getName());
            holder.tv3.setText(s.getScore());
            holder.tv4.setText(s.getRankChange() + "");
        } else if (object instanceof ClassValue) {
            ClassValue cv = (ClassValue) object;
            holder.tv1.setText(cv.getsClass());
            holder.tv2.setText(cv.getAvg());
            holder.tv3.setText(cv.getExcellentRate());
            holder.tv4.setText(cv.getPassRate());
            holder.tv5.setText(cv.getLowRate());
        } else if (object instanceof ClassScore) {
            ClassScore cs = (ClassScore) object;
            holder.tv1.setText(cs.getsClass());
            holder.tv2.setText(cs.getAvg());
            holder.tv3.setText(cs.getExcellentRate());
            holder.tv4.setText(cs.getPassRate());
            holder.tv5.setText(cs.getLowRate());
        } else if (object instanceof ClassNum) {
            ClassNum cn = (ClassNum) object;
            holder.tv1.setText(cn.getsClass());
            holder.tv2.setText(cn.getAvg());
            holder.tv3.setText(cn.getExcellentRate());
            holder.tv4.setText(cn.getPassRate());
            holder.tv5.setText(cn.getLowRate());
        }

        setTextViewColor(position, holder, view);
        return view;
    }

    private void setTextViewColor(int i, ViewHolder holder, View view) {
        if (i == 0) {
            holder.tv1.setTextColor(Color.parseColor("#1698ff"));
            holder.tv2.setTextColor(Color.parseColor("#1698ff"));
            holder.tv3.setTextColor(Color.parseColor("#1698ff"));
            holder.tv4.setTextColor(Color.parseColor("#1698ff"));
            holder.tv5.setTextColor(Color.parseColor("#1698ff"));
            holder.tv1.setTextSize(16);
            holder.tv2.setTextSize(16);
            holder.tv3.setTextSize(16);
            holder.tv4.setTextSize(16);
            holder.tv5.setTextSize(16);
        } else if (getCount() - 2 > i && getCount() > 5) {
            holder.tv1.setTextColor(Color.parseColor("#666666"));
            holder.tv2.setTextColor(Color.parseColor("#666666"));
            holder.tv3.setTextColor(Color.parseColor("#666666"));
            holder.tv4.setTextColor(Color.parseColor("#666666"));
            holder.tv5.setTextColor(Color.parseColor("#666666"));
            holder.tv1.setTextSize(13);
            holder.tv2.setTextSize(13);
            holder.tv3.setTextSize(13);
            holder.tv4.setTextSize(13);
            holder.tv5.setTextSize(13);
        } else if (getCount() - 1 > i && getCount() <= 5) {
            holder.tv1.setTextColor(Color.parseColor("#666666"));
            holder.tv2.setTextColor(Color.parseColor("#666666"));
            holder.tv3.setTextColor(Color.parseColor("#666666"));
            holder.tv4.setTextColor(Color.parseColor("#666666"));
            holder.tv5.setTextColor(Color.parseColor("#666666"));
            holder.tv1.setTextSize(13);
            holder.tv2.setTextSize(13);
            holder.tv3.setTextSize(13);
            holder.tv4.setTextSize(13);
            holder.tv5.setTextSize(13);
        } else {
            holder.tv1.setTextColor(Color.parseColor("#da4453"));
            holder.tv2.setTextColor(Color.parseColor("#da4453"));
            holder.tv3.setTextColor(Color.parseColor("#da4453"));
            holder.tv4.setTextColor(Color.parseColor("#da4453"));
            holder.tv5.setTextColor(Color.parseColor("#da4453"));
            holder.tv1.setTextSize(13);
            holder.tv2.setTextSize(13);
            holder.tv3.setTextSize(13);
            holder.tv4.setTextSize(13);
            holder.tv5.setTextSize(13);
        }
    }

    private void setBackColor(int i, View view) {
        if (i % 2 == 0) {
            view.setBackgroundColor(context.getResources().getColor(R.color.white));
        } else {
            view.setBackgroundColor(Color.parseColor("#f8f8f8"));
        }
    }

    class ViewHolder {
        TextView tv1, tv2, tv3, tv4, tv5;
    }

    class ViewHolderTv {
        ImageView iv;
    }
}
