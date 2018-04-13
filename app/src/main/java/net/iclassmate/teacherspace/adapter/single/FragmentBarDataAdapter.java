package net.iclassmate.teacherspace.adapter.single;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.bean.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xydbj on 2016/3/17.
 */
public class FragmentBarDataAdapter extends BaseAdapter {
    private Context context;
    private List<Object> list;
    private boolean flag;

    public FragmentBarDataAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public FragmentBarDataAdapter(Context context, List<Object> list) {
        this.context = context;
        this.list = list;
    }

    public void addData(List<Object> data) {
        if (list != null) {
            list.clear();
        }
        if (data != null) {
            list.addAll(data);
        }
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void addData(List<Object> data, boolean flag) {
        if (list != null) {
            list.clear();
        }
        if (data != null) {
            list.addAll(data);
        }
        this.flag = flag;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (list != null) {
            ret = list.size();
            if (!flag && ret > 4) {
                ret = 5;
            } else if (flag && ret > 4) {
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
        if (getCount() < 5) return ret;
        if (getCount() - position == 1) {
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
        if (object instanceof Student) {
            Student s = (Student) object;
            holder.tv1.setText(s.getsClass());
            holder.tv2.setText(s.getName());
            holder.tv3.setText(s.getScore());
            holder.tv4.setText(s.getRankChange() + "");
            setTextViewColor(position, holder, view);
        }
        return view;
    }

    private void setTextViewColor(int i, ViewHolder holder, View view) {
        if (i == 0) {
            holder.tv1.setTextColor(ContextCompat.getColor(context,R.color.app_color));
            holder.tv2.setTextColor(ContextCompat.getColor(context,R.color.app_color));
            holder.tv3.setTextColor(ContextCompat.getColor(context,R.color.app_color));
            holder.tv4.setTextColor(ContextCompat.getColor(context,R.color.app_color));
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
            view.setBackgroundColor(Color.parseColor("#f8f8f8"));
        }
    }

    class ViewHolder {
        TextView tv1, tv2, tv3, tv4;
    }

    class ViewHolderTv {
        ImageView iv;
    }
}
