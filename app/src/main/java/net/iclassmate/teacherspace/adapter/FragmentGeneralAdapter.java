package net.iclassmate.teacherspace.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.bean.Student;
import net.iclassmate.teacherspace.bean.StudentClass;
import net.iclassmate.teacherspace.bean.general.GeneralAll;
import net.iclassmate.teacherspace.bean.general.SingleExamInfos;
import net.iclassmate.teacherspace.ui.activity.GeneralTextActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by xydbj on 2016.2.17.
 */
public class FragmentGeneralAdapter extends BaseAdapter implements View.OnClickListener{
    private Context context;
    private List<Object> list;
    private boolean flag;
    private GeneralAll generalAll = new GeneralAll();

    public FragmentGeneralAdapter(Context context){
        this.context = context;
        list = new ArrayList<>();
    }

    public FragmentGeneralAdapter(Context context,GeneralAll generalAll){
        this.context = context;
        this.generalAll = generalAll;
    }

    public FragmentGeneralAdapter(Context context,List<Object> list){
        this.context = context;
        this.list = list;
    };

    public FragmentGeneralAdapter(Context context,List<Object> list,GeneralAll generalAll){
        this.context = context;
        this.list = list;
        this.generalAll = generalAll;
    };

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
        if(list != null){
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
            if (!flag && ret >= 7) {
                ret = 7;
            } else if (flag && ret >= 7) {
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

    @Override//返回多少个不同的布局
    public int getViewTypeCount() {
        return 2;
    }

    @Override//根据position返回相应的item
    public int getItemViewType(int position) {
        int ret = 0;
        if (getCount() < 7) return ret;
        if (getCount() - position == 1) {
            ret = 1;
        } else {
            ret = 0;
        }
        return ret;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(getItemViewType(position) == 0){
            convertView = bindView(position,convertView,viewGroup);
        }else if(getItemViewType(position) == 1){
            convertView = bindLastView(position,convertView,viewGroup);
        }
        setBackColor(position,convertView);
        return convertView;
    }
    private View bindLastView(int position, View convertView, ViewGroup viewGroup){
        ViewHolderTv vh = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_general_listview_more,viewGroup,false);
            vh = new ViewHolderTv();
            vh.tv = (ImageView) convertView.findViewById(R.id.fragment_general_last_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolderTv) convertView.getTag();
        }
        if(flag){
            vh.tv.setImageResource(R.drawable.fragment_single_last_img_shouqi);
        }else{
            vh.tv.setImageResource(R.drawable.fragment_single_last_img_zhankai);
        }
        return convertView;
    }
    private View bindView(int position,View convertView,ViewGroup parent){
        ViewHolder vh = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_general_listview_item,parent,false);
            vh = new ViewHolder();
            vh.lv_item1 = (TextView) convertView.findViewById(R.id.lv_item1);
            vh.lv_item2 = (TextView) convertView.findViewById(R.id.lv_item2);
            vh.lv_item3 = (TextView) convertView.findViewById(R.id.lv_item3);
            vh.lv_item4 = (TextView) convertView.findViewById(R.id.lv_item4);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        Object object = list.get(position);
        if (object instanceof StudentClass) {
            StudentClass sc = (StudentClass) object;
            vh.lv_item1.setText(sc.getsClass());
            vh.lv_item2.setText(sc.getAvgScore());
            vh.lv_item3.setText(sc.getRank());
            vh.lv_item4.setText(sc.getRankChange() + "");
            setTextViewColor(position, vh, convertView);
            setColor(sc.getRankChange(), vh.lv_item4);
        } else {
            if (object instanceof Student) {
                Student s = (Student) object;
                String className = s.getsClass();
                if (className == null || className.equals("--") || className.equals("--") || className.equals("null")) {
                    vh.lv_item1.setText("--");
                    vh.lv_item2.setText("--");
                    vh.lv_item3.setText("--");
                    vh.lv_item4.setText("--");
                } else {
                    vh.lv_item1.setText(s.getsClass());
                    vh.lv_item2.setText(s.getName());
                    vh.lv_item3.setText(s.getScore());
                    vh.lv_item4.setText(s.getRankChange() + "");
                }
                vh.lv_item2.setTag(s);
                vh.lv_item2.setOnClickListener(this);
                setTextViewColor(position, vh, convertView);
                setColor(s.getRankChange(), vh.lv_item4);

                String sId = s.getsId();
                String name = s.getName();
                if (sId != null && !sId.equals("--") && !sId.equals("--") && !sId.equals("null") && name != null && !name.equals("--") && !name.equals("--") && !name.equals("null")) {
                    vh.lv_item2.setTextColor(context.getResources().getColor(R.color.s_name));
                }
            }
        }
        return convertView;
    }

    private void setColor(String s, TextView tv) {
        if (s.contains("↑")) {
            if (s.contains("(") && s.contains(")")) {
                SpannableString sb = new SpannableString(s);
                int a = s.indexOf("(");
                int b = s.indexOf(")");
                sb.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.app_color)), a+1, b, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tv.setText(sb);
            } else {
                tv.setTextColor(context.getResources().getColor(R.color.app_color));
            }
        } else if (s.contains("↓")) {
            if (s.contains("(") && s.contains(")")) {
                SpannableString sb = new SpannableString(s);
                int a = s.indexOf("(");
                int b = s.indexOf(")");
                sb.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.tv_red)), a+1, b, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tv.setText(sb);
            } else {
                tv.setTextColor(context.getResources().getColor(R.color.tv_red));
            }
        }
    }

    private void setTextViewColor(int i,ViewHolder vh,View view){
        if (i == 0) {
            vh.lv_item1.setTextColor(ContextCompat.getColor(context,R.color.app_color));
            vh.lv_item2.setTextColor(ContextCompat.getColor(context,R.color.app_color));
            vh.lv_item3.setTextColor(ContextCompat.getColor(context,R.color.app_color));
            vh.lv_item4.setTextColor(ContextCompat.getColor(context,R.color.app_color));
            vh.lv_item1.setTextSize(16);
            vh.lv_item2.setTextSize(16);
            vh.lv_item3.setTextSize(16);
            vh.lv_item4.setTextSize(16);
        } else {
            vh.lv_item1.setTextColor(Color.parseColor("#666666"));
            vh.lv_item2.setTextColor(Color.parseColor("#666666"));
            vh.lv_item3.setTextColor(Color.parseColor("#666666"));
            vh.lv_item4.setTextColor(Color.parseColor("#666666"));
            vh.lv_item1.setTextSize(13);
            vh.lv_item2.setTextSize(13);
            vh.lv_item3.setTextSize(13);
            vh.lv_item4.setTextSize(13);
        }
    }
    //listview的背景色
    private void setBackColor(int i,View view){
        if(i % 2 == 0){
            view.setBackgroundColor(context.getResources().getColor(R.color.white));
        }else{
            view.setBackgroundColor(context.getResources().getColor(R.color.tv_single));
        }
    }

    @Override
    public void onClick(View v) {
        Object object = v.getTag();
        if (object != null) {
            if (object instanceof Student) {
                Student s = (Student) object;
                String sId = s.getsId();//学生ID
                String name = s.getName();//学生姓名
                if (sId != null && !sId.equals("--") && !sId.equals("--") && !sId.equals("null")
                        && name != null && !name.equals("--") && !name.equals("--") && !name.equals("null")) {
                    toActivity(s.getsId(), s.getName());
                }
            }
        }
    }
    public void toActivity(String id, String name) {
        List<SingleExamInfos> singleExamInfoslist = new ArrayList<SingleExamInfos>();
        int size = generalAll.getList().get(0).getTotalSituation().getSummaryInfo().getSubjectSummarieslist().get(0).getSingleExamInfoslist().size();
        for(int i=0;i<size;i++){
            SingleExamInfos singleExamInfos = new SingleExamInfos();
            //科目名称
            singleExamInfos.setCourseName(generalAll.getList().get(0).getTotalSituation().getSummaryInfo().getSubjectSummarieslist().get(0).getSingleExamInfoslist().get(i).getCourseName());
            //科目ID
            singleExamInfos.setCourseId(generalAll.getList().get(0).getTotalSituation().getSummaryInfo().getSubjectSummarieslist().get(0).getSingleExamInfoslist().get(i).getCourseId());
            //meId
            singleExamInfos.setScore(Float.parseFloat(generalAll.getList().get(0).getMeId()));
            //schoolId
            singleExamInfos.setSeFullScore(Integer.parseInt(generalAll.getList().get(0).getSchoolId()));
            singleExamInfoslist.add(singleExamInfos);
        }
        Intent intent = new Intent(context, GeneralTextActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("name", name);
        intent.putExtra("list", (Serializable) singleExamInfoslist);
        Log.i("miss","======"+singleExamInfoslist.toString());
        context.startActivity(intent);
    }

    class ViewHolder{
        TextView lv_item1,lv_item2,lv_item3,lv_item4;
    }
    class ViewHolderTv{
        ImageView tv;
    }
}
