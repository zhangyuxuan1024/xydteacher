package net.iclassmate.teacherspace.adapter.single;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.bean.single.KnowledgePoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xydbj on 2016/2/17.
 */
public class FragmentTestAdapter extends BaseAdapter{
    private Context context;
    private List<Object> list;
    private boolean flag;

    public FragmentTestAdapter(Context context, List<Object> list, boolean flag) {
        this.context = context;
        this.list = list;
        this.flag = flag;
    }

    public FragmentTestAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }
    public void addData(List<Object> data){
//        if(list!=null){
//            list.clear();
//        }
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
            if (!flag && ret > 6) {
                ret = 7;
            }
            if (flag) {
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
        if(getCount()<=6) return ret;
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
        setBackColor(i,view);
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
            view = LayoutInflater.from(context).inflate(R.layout.fragment_single_knowledget_point, viewGroup, false);
            holder = new ViewHolder();
            holder.tv1 = (TextView) view.findViewById(R.id.fragment_single_tv_1);
            holder.tv2 = (TextView) view.findViewById(R.id.fragment_single_tv_2);
            holder.tv3 = (TextView) view.findViewById(R.id.fragment_single_tv_3);
            holder.iv = (ImageView) view.findViewById(R.id.fragment_single_iv_3);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Object object = list.get(position);
        if (object instanceof KnowledgePoint) {
            KnowledgePoint kp = (KnowledgePoint) object;
            holder.tv1.setText(kp.getPoint());
            holder.tv2.setText(kp.getScore());
            if(position==0) {
                holder.tv3.setText(kp.getDegree());
                holder.tv3.setVisibility(View.VISIBLE);
                holder.iv.setVisibility(View.GONE);
            }else {
                holder.iv.setImageResource(R.mipmap.s_5);
                holder.iv.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.GONE);
                String str = kp.getDegree();
                if(str!=null && !str.equals("null")){
                    double n = Double.parseDouble(str);
                    if(n>=0.95){
                        holder.iv.setImageResource(R.mipmap.star10);
                    }else if(n>=0.85){
                        holder.iv.setImageResource(R.mipmap.star9);
                    }else if(n>=0.75){
                        holder.iv.setImageResource(R.mipmap.star8);
                    }else if(n>=0.65){
                        holder.iv.setImageResource(R.mipmap.star7);
                    }else if(n>=0.55){
                        holder.iv.setImageResource(R.mipmap.star6);
                    }else if(n>=0.45){
                        holder.iv.setImageResource(R.mipmap.star5);
                    }else if(n>=0.35){
                        holder.iv.setImageResource(R.mipmap.star4);
                    }else if(n>=0.25){
                        holder.iv.setImageResource(R.mipmap.star3);
                    }else if(n>=0.15){
                        holder.iv.setImageResource(R.mipmap.star2);
                    }else if(n>0.05){
                        holder.iv.setImageResource(R.mipmap.star1);
                    }else if(n>=0){
                        holder.iv.setImageResource(R.mipmap.star0);
                    }
                }
            }
            setTextViewColor(position, holder, view);
        }
        return view;
    }

    private void setTextViewColor(int i, ViewHolder holder, View view) {
        if (i == 0) {
            holder.tv1.setTextColor(Color.parseColor("#1698ff"));
            holder.tv2.setTextColor(Color.parseColor("#1698ff"));
            holder.tv3.setTextColor(Color.parseColor("#1698ff"));
            holder.tv1.setTextSize(16);
            holder.tv2.setTextSize(16);
            holder.tv3.setTextSize(16);
        } else {
            holder.tv1.setTextColor(Color.parseColor("#666666"));
            holder.tv2.setTextColor(Color.parseColor("#666666"));
            holder.tv3.setTextColor(Color.parseColor("#666666"));
            holder.tv1.setTextSize(13);
            holder.tv2.setTextSize(13);
            holder.tv3.setTextSize(13);
        }
    }
    private void setBackColor(int i,View view){
        if (i % 2 == 0) {
            view.setBackgroundColor(context.getResources().getColor(R.color.white));
        } else {
            view.setBackgroundColor(context.getResources().getColor(R.color.tv_single));
        }
    }

    class ViewHolder {
        TextView tv1, tv2, tv3;
        ImageView iv;
        LinearLayout linearLayout;
    }

    class ViewHolderTv {
        ImageView iv;
    }
}
