package net.iclassmate.teacherspace.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.bean.Statistics;
import net.iclassmate.teacherspace.bean.Student;
import net.iclassmate.teacherspace.bean.StudentClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xydbj on 2016/2/2.
 */
public class FragmentSingleTestStatistricesAdapter extends BaseAdapter {
    private Context context;
    private List<Object> list;
    private boolean flag;

    public FragmentSingleTestStatistricesAdapter(Context context, List<Object> list) {
        this.context = context;
        this.list = list;
    }

    public FragmentSingleTestStatistricesAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public FragmentSingleTestStatistricesAdapter(Context context, List<Object> list, boolean flag) {
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
            if (flag) {
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
            view = LayoutInflater.from(context).inflate(R.layout.fragment_single_test_statictrices, viewGroup, false);
            holder = new ViewHolder();
            holder.tv1 = (TextView) view.findViewById(R.id.fragment_single_test_tv_1);
            holder.tv2 = (TextView) view.findViewById(R.id.fragment_single_test_tv_2);
            holder.tv3 = (TextView) view.findViewById(R.id.fragment_single_test_tv_3);
            holder.tv4 = (TextView) view.findViewById(R.id.fragment_single_test_tv_4);
            holder.tv5 = (TextView) view.findViewById(R.id.fragment_single_test_tv_5);
            holder.tv6 = (TextView) view.findViewById(R.id.fragment_single_test_tv_6);
            holder.tv7 = (TextView) view.findViewById(R.id.fragment_single_test_tv_7);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Object object = list.get(position);
        if (object instanceof Statistics) {
            Statistics s = (Statistics) object;
            holder.tv1.setText(s.getTitle());
            holder.tv2.setText(s.getCorrectRate());
            holder.tv3.setText(s.getAnswerA());
            holder.tv4.setText(s.getAnswerB());
            holder.tv5.setText(s.getAnswerC());
            holder.tv6.setText(s.getAnswerD());
            holder.tv7.setText(s.getAnswerOther());

            setTextViewColor(position, holder, view);

            if (s.getRight() != null) {
                if (s.getRight().equals("A")) {
                    holder.tv3.setTextColor(context.getResources().getColor(R.color.tv_right));
                } else if (s.getRight().equals("B")) {
                    holder.tv4.setTextColor(context.getResources().getColor(R.color.tv_right));
                } else if (s.getRight().equals("C")) {
                    holder.tv5.setTextColor(context.getResources().getColor(R.color.tv_right));
                } else if (s.getRight().equals("D")) {
                    holder.tv6.setTextColor(context.getResources().getColor(R.color.tv_right));
                }
            }
        }
        return view;
    }

    private void setTextViewColor(int i, ViewHolder holder, View view) {
        if (i == 0) {
            holder.tv1.setTextColor(Color.parseColor("#1698ff"));
            holder.tv2.setTextColor(Color.parseColor("#1698ff"));
            holder.tv3.setTextColor(Color.parseColor("#1698ff"));
            holder.tv4.setTextColor(Color.parseColor("#1698ff"));
            holder.tv5.setTextColor(Color.parseColor("#1698ff"));
            holder.tv6.setTextColor(Color.parseColor("#1698ff"));
            holder.tv7.setTextColor(Color.parseColor("#1698ff"));
            holder.tv1.setTextSize(16);
            holder.tv2.setTextSize(16);
            holder.tv3.setTextSize(16);
            holder.tv4.setTextSize(16);
            holder.tv5.setTextSize(16);
            holder.tv6.setTextSize(16);
            holder.tv7.setTextSize(16);
        } else {
            holder.tv1.setTextColor(Color.parseColor("#666666"));
            holder.tv2.setTextColor(Color.parseColor("#666666"));
            holder.tv3.setTextColor(Color.parseColor("#666666"));
            holder.tv4.setTextColor(Color.parseColor("#666666"));
            holder.tv5.setTextColor(Color.parseColor("#666666"));
            holder.tv6.setTextColor(Color.parseColor("#666666"));
            holder.tv7.setTextColor(Color.parseColor("#666666"));
            holder.tv1.setTextSize(13);
            holder.tv2.setTextSize(13);
            holder.tv3.setTextSize(13);
            holder.tv4.setTextSize(13);
            holder.tv5.setTextSize(13);
            holder.tv6.setTextSize(13);
            holder.tv7.setTextSize(13);
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
        TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7;
    }

    class ViewHolderTv {
        TextView tv;
    }
}
