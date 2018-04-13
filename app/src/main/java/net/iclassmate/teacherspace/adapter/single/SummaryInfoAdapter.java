package net.iclassmate.teacherspace.adapter.single;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
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
 * Created by xydbj on 2016/2/26.
 */
public class SummaryInfoAdapter extends BaseAdapter {
    private Context context;
    private List<Object> list;
    private boolean flag;

    public SummaryInfoAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public SummaryInfoAdapter(Context context, List<Object> list) {
        this.context = context;
        this.list = list;
    }

    public void addData(List<Object> data) {
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
            if (ret <= 5) {
                return ret;
            }
            if (!flag) {
                ret = 6;
            } else {
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
        if (getCount() < 6) return ret;
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
            view = LayoutInflater.from(context).inflate(R.layout.fragment_single_summary, viewGroup, false);
            holder = new ViewHolder();
            holder.tv1 = (TextView) view.findViewById(R.id.fragment_single_su_tv_1);
            holder.tv2 = (TextView) view.findViewById(R.id.fragment_single_su_tv_2);
            holder.tv3 = (TextView) view.findViewById(R.id.fragment_single_su_tv_3);
            holder.tv4 = (TextView) view.findViewById(R.id.fragment_single_su_tv_4);
            holder.img = (ImageView) view.findViewById(R.id.fragment_single_su_img);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Object object = null;
        if (getCount() > 5 && position == getCount() - 2) {
            object = list.get(list.size() - 1);
        } else {
            object = list.get(position);
        }

        if (object instanceof Student) {
            Student s = (Student) object;
            String score = s.getScore();
            int n = 0;
            if (score != null && !score.equals("") && !score.equals("null") && position > 0 && !score.equals("--")) {
                try {
                    n = Integer.parseInt(score);
                } catch (Exception e) {

                }
            }
            if (n == 1) {
                holder.img.setVisibility(View.VISIBLE);
            } else {
                holder.img.setVisibility(View.INVISIBLE);
            }
            holder.tv1.setText(s.getsClass());
            holder.tv2.setText(s.getName());
            holder.tv3.setText(s.getScore());
            holder.tv4.setText(s.getRankChange() + "");
            setTextViewColor(position, holder, view);
            setColor(s.getRankChange(), holder.tv4);
        }
        return view;
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
        } else if (getCount() - 2 > i && getCount() > 5) {
            holder.tv1.setTextColor(Color.parseColor("#666666"));
            holder.tv2.setTextColor(Color.parseColor("#666666"));
            holder.tv3.setTextColor(Color.parseColor("#666666"));
            holder.tv4.setTextColor(Color.parseColor("#666666"));
            holder.tv1.setTextSize(13);
            holder.tv2.setTextSize(13);
            holder.tv3.setTextSize(13);
            holder.tv4.setTextSize(13);
        } else if (getCount() - 1 > i && getCount() < 6) {
            holder.tv1.setTextColor(Color.parseColor("#666666"));
            holder.tv2.setTextColor(Color.parseColor("#666666"));
            holder.tv3.setTextColor(Color.parseColor("#666666"));
            holder.tv4.setTextColor(Color.parseColor("#666666"));
            holder.tv1.setTextSize(13);
            holder.tv2.setTextSize(13);
            holder.tv3.setTextSize(13);
            holder.tv4.setTextSize(13);
        } else {
            holder.tv1.setTextColor(Color.parseColor("#da4453"));
            holder.tv2.setTextColor(Color.parseColor("#da4453"));
            holder.tv3.setTextColor(Color.parseColor("#da4453"));
            holder.tv4.setTextColor(Color.parseColor("#da4453"));
            holder.tv1.setTextSize(13);
            holder.tv2.setTextSize(13);
            holder.tv3.setTextSize(13);
            holder.tv4.setTextSize(13);
        }
    }

    private void setColor(String s, TextView tv) {
        if (s.contains("↑")) {
            if (s.contains("(") && s.contains(")")) {
                SpannableString sb = new SpannableString(s);
                int a = s.indexOf("(");
                int b = s.indexOf(")");
                sb.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.app_color)), a + 1, b, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tv.setText(sb);
            } else {
                tv.setTextColor(context.getResources().getColor(R.color.app_color));
            }
        } else if (s.contains("↓")) {
            if (s.contains("(") && s.contains(")")) {
                SpannableString sb = new SpannableString(s);
                int a = s.indexOf("(");
                int b = s.indexOf(")");
                sb.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.tv_red)), a + 1, b, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tv.setText(sb);
            } else {
                tv.setTextColor(context.getResources().getColor(R.color.tv_red));
            }
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
        ImageView img;
    }

    class ViewHolderTv {
        ImageView iv;
    }
}
