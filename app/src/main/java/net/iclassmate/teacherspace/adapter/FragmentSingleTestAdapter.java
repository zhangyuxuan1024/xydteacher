package net.iclassmate.teacherspace.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.bean.Student;
import net.iclassmate.teacherspace.bean.StudentClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xydbj on 2016/2/2.
 */
public class FragmentSingleTestAdapter extends BaseAdapter {
    private Context context;
    private List<Object> list;
    private boolean flag;

    public FragmentSingleTestAdapter(Context context, List<Object> list) {
        this.context = context;
        this.list = list;
    }

    public FragmentSingleTestAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }
    public FragmentSingleTestAdapter(Context context, List<Object> list,boolean flag) {
        this.context = context;
        this.list = list;
        this.flag = flag;
    }
    public void addData(List<Object> data) {
        list.clear();
        if (data != null) {
            list.addAll(data);
        }
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (list != null) {
            ret = list.size();
            if (ret > 6) {
                ret = 7;
            }
            if(flag){
                ret = list.size();
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
        if (position == 6 && !flag) {
            ret = 1;
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

    private View bindView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_single_title, viewGroup, false);
            holder = new ViewHolder();
            holder.tv1 = (TextView) view.findViewById(R.id.fragment_single_tv_1);
            holder.tv2 = (TextView) view.findViewById(R.id.fragment_single_tv_2);
            holder.tv3 = (TextView) view.findViewById(R.id.fragment_single_tv_3);
            holder.tv4 = (TextView) view.findViewById(R.id.fragment_single_tv_4);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Object object = list.get(position);
        if (object instanceof StudentClass) {
            StudentClass sc = (StudentClass) object;
            holder.tv1.setText(sc.getsClass());
            holder.tv2.setText(sc.getAvgScore());
            holder.tv3.setText(sc.getRank());
            holder.tv4.setText(sc.getRankChange() + "");

            setTextViewColor(position, holder, view);
        } else if (object instanceof Student) {
            Student s = (Student) object;
            holder.tv1.setText(s.getsClass());
            holder.tv2.setText(s.getName());
            holder.tv3.setText(s.getScore());
            holder.tv4.setText(s.getRankChange() + "");

            setTextViewColor(position, holder, view);
            setColor(s.getRankChange(), holder.tv4);
        }
        return view;
    }

    private void setColor(String s, TextView tv) {
        if (s.contains("(") && s.contains(")")) {
            SpannableString sb = new SpannableString(s);
            int a = s.indexOf("(");
            int b = s.indexOf(")");
            if (s.contains("难")) {
                sb.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.tv_n)), a + 1, b, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else if (s.contains("中")) {
                sb.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.tv_z)), a + 1, b, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else if (s.contains("易")) {
                sb.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.tv_y)), a + 1, b, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            tv.setText(sb);
        }
    }

    private void setTextViewColor(int i, ViewHolder holder, View view) {
        if (i == 0) {
            holder.tv1.setTextColor(Color.parseColor("#1698ff"));
            holder.tv2.setTextColor(Color.parseColor("#1698ff"));
            holder.tv3.setTextColor(Color.parseColor("#1698ff"));
            holder.tv4.setTextColor(Color.parseColor("#1698ff"));
            holder.tv1.setTextSize(16);
            holder.tv2.setTextSize(16);
            holder.tv3.setTextSize(16);
            holder.tv4.setTextSize(16);
        } else {
            holder.tv1.setTextColor(Color.parseColor("#666666"));
            holder.tv2.setTextColor(Color.parseColor("#666666"));
            holder.tv3.setTextColor(Color.parseColor("#666666"));
            holder.tv4.setTextColor(Color.parseColor("#666666"));
            holder.tv1.setTextSize(13);
            holder.tv2.setTextSize(13);
            holder.tv3.setTextSize(13);
            holder.tv4.setTextSize(13);
        }
    }

    private void setBackColor(int i, View view) {
        if (i % 2 == 0) {
            view.setBackgroundColor(context.getResources().getColor(R.color.white));
        } else {
            view.setBackgroundColor(context.getResources().getColor(R.color.tv_single));
        }
    }

    private View bindLastView(int position, View view, ViewGroup viewGroup) {
        ViewHolderTv holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_single_text_last, viewGroup, false);
            holder = new ViewHolderTv();
//            holder.tv = (TextView) view.findViewById(R.id.fragment_single_last_tv);
//            view.setTag(holder);
        } else {
            holder = (ViewHolderTv) view.getTag();
        }
        return view;
    }

    class ViewHolder {
        TextView tv1, tv2, tv3, tv4;
    }

    class ViewHolderTv {
        TextView tv;
    }
}
