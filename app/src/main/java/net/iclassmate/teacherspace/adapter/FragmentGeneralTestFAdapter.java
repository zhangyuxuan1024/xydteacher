package net.iclassmate.teacherspace.adapter;

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
import net.iclassmate.teacherspace.bean.single.ClassNum;
import net.iclassmate.teacherspace.bean.single.ClassScore;
import net.iclassmate.teacherspace.bean.single.ClassValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xydbj on 2016.2.20.
 */
public class FragmentGeneralTestFAdapter extends BaseAdapter {
    private Context context;
    private List<Object> list;
    private boolean flag;

    public FragmentGeneralTestFAdapter(Context context){
        this.context = context;
        list = new ArrayList<>();
    }

    public void setData(List<Object> data){
        list.clear();
        if (data != null) {
            list.addAll(data);
        }
    }
    public void setFlag(boolean flag){
        this.flag = flag;
    }
    @Override
    public int getCount() {
        int ret = 0;
        if(list != null){
            ret = list.size();
            if(!flag && ret > 5){
                ret = 6;
            }else if (flag && ret > 5) {
                ret = ret + 1;
            }
        }
        return ret;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if(getItemViewType(position) == 0){
            convertView = bindView(position, convertView, parent);
        }else if(getItemViewType(position) == 1){
            convertView = bindLastView(position, convertView, parent);
        }
        setBackColor(position, convertView);
        return convertView;
    }

    private View bindLastView(int position,View view,ViewGroup viewGroup){
        ViewHolderTv vh = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_single_listview_more, viewGroup, false);
            vh = new ViewHolderTv();
            vh.iv = (ImageView) view.findViewById(R.id.fragment_single_last_tv);
            view.setTag(vh);
        } else {
            vh = (ViewHolderTv) view.getTag();
        }
        if (flag) {
            vh.iv.setImageResource(R.drawable.fragment_single_last_img_shouqi);
        } else {
            vh.iv.setImageResource(R.drawable.fragment_single_last_img_zhankai);
        }
        return view;
    }

    private View bindView(int position,View view, ViewGroup viewGroup) {
        ViewHolder vh = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_single_grade_title,viewGroup,false);
            vh = new ViewHolder();
            vh.tv1 = (TextView) view.findViewById(R.id.fragment_single_grade_tv_1);
            vh.tv2 = (TextView) view.findViewById(R.id.fragment_single_grade_tv_2);
            vh.tv3 = (TextView) view.findViewById(R.id.fragment_single_grade_tv_3);
            vh.tv4 = (TextView) view.findViewById(R.id.fragment_single_grade_tv_4);
            vh.tv5 = (TextView) view.findViewById(R.id.fragment_single_grade_tv_5);
            view.setTag(vh);
        }else{
            vh = (ViewHolder) view.getTag();
        }
        Object object = null;
        if (!flag && position == 4) {
            object = list.get(list.size() - 1);
        } else {
            object = list.get(position);
        }
        if(object instanceof ClassScore){
            ClassScore cs = (ClassScore) object;
            vh.tv1.setText(cs.getsClass());
            vh.tv2.setText(cs.getAvg());
            vh.tv3.setText(cs.getExcellentRate());
            vh.tv4.setText(cs.getPassRate());
            vh.tv5.setText(cs.getLowRate());
        }else if(object instanceof Student){
            Student s = (Student) object;
            vh.tv1.setText(s.getsClass());
            vh.tv2.setText(s.getName());
            vh.tv3.setText(s.getScore());
            vh.tv4.setText(s.getRankChange()+"");
        }else if (object instanceof ClassValue) {
            ClassValue cv = (ClassValue) object;
            vh.tv1.setText(cv.getsClass());
            vh.tv2.setText(cv.getAvg());
            vh.tv3.setText(cv.getExcellentRate());
            vh.tv4.setText(cv.getPassRate());
            vh.tv5.setText(cv.getLowRate());
        } else if (object instanceof ClassScore) {
            ClassScore cs = (ClassScore) object;
            vh.tv1.setText(cs.getsClass());
            vh.tv2.setText(cs.getAvg());
            vh.tv3.setText(cs.getExcellentRate());
            vh.tv4.setText(cs.getPassRate());
            vh.tv5.setText(cs.getLowRate());
        } else if (object instanceof ClassNum) {
            ClassNum cn = (ClassNum) object;
            vh.tv1.setText(cn.getsClass());
            vh.tv2.setText(cn.getAvg());
            vh.tv3.setText(cn.getExcellentRate());
            vh.tv4.setText(cn.getPassRate());
            vh.tv5.setText(cn.getLowRate());
        }
        setTextViewColor(position, vh, view);
        return view;
    }

    private void setTextViewColor(int position,ViewHolder vh,View view){
        if (position == 0) {
            vh.tv1.setTextColor(ContextCompat.getColor(context,R.color.app_color));
            vh.tv2.setTextColor(ContextCompat.getColor(context,R.color.app_color));
            vh.tv3.setTextColor(ContextCompat.getColor(context,R.color.app_color));
            vh.tv4.setTextColor(ContextCompat.getColor(context,R.color.app_color));
            vh.tv5.setTextColor(ContextCompat.getColor(context,R.color.app_color));
            vh.tv1.setTextSize(16);
            vh.tv2.setTextSize(16);
            vh.tv3.setTextSize(16);
            vh.tv4.setTextSize(16);
            vh.tv5.setTextSize(16);
        } else if (getCount() - 2 > position && getCount() > 5) {
            vh.tv1.setTextColor(Color.parseColor("#666666"));
            vh.tv2.setTextColor(Color.parseColor("#666666"));
            vh.tv3.setTextColor(Color.parseColor("#666666"));
            vh.tv4.setTextColor(Color.parseColor("#666666"));
            vh.tv5.setTextColor(Color.parseColor("#666666"));
            vh.tv1.setTextSize(13);
            vh.tv2.setTextSize(13);
            vh.tv3.setTextSize(13);
            vh.tv4.setTextSize(13);
            vh.tv5.setTextSize(13);
        } else if (getCount() - 1 > position && getCount() <= 5) {
            vh.tv1.setTextColor(Color.parseColor("#666666"));
            vh.tv2.setTextColor(Color.parseColor("#666666"));
            vh.tv3.setTextColor(Color.parseColor("#666666"));
            vh.tv4.setTextColor(Color.parseColor("#666666"));
            vh.tv5.setTextColor(Color.parseColor("#666666"));
            vh.tv1.setTextSize(13);
            vh.tv2.setTextSize(13);
            vh.tv3.setTextSize(13);
            vh.tv4.setTextSize(13);
            vh.tv5.setTextSize(13);
        } else {
            vh.tv1.setTextColor(Color.parseColor("#da4453"));
            vh.tv2.setTextColor(Color.parseColor("#da4453"));
            vh.tv3.setTextColor(Color.parseColor("#da4453"));
            vh.tv4.setTextColor(Color.parseColor("#da4453"));
            vh.tv5.setTextColor(Color.parseColor("#da4453"));
            vh.tv1.setTextSize(13);
            vh.tv2.setTextSize(13);
            vh.tv3.setTextSize(13);
            vh.tv4.setTextSize(13);
            vh.tv5.setTextSize(13);
        }
    }

    private void setBackColor(int position,View view){
        if(position % 2 == 0){
            view.setBackgroundColor(context.getResources().getColor(R.color.white));
        }else{
            view.setBackgroundColor(Color.parseColor("#F8F8F8"));
        }
    }

    class ViewHolder{
        TextView tv1,tv2,tv3,tv4,tv5;
    }

    class ViewHolderTv{
        ImageView iv;
    }
}
