package net.iclassmate.teacherspace.adapter;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.bean.Student;
import net.iclassmate.teacherspace.ui.activity.GeneralTextActivity;

import java.util.List;

/**
 * Created by xydbj on 2016.3.22.
 */
public class GeneralTextAdapter extends BaseAdapter {
    private Context context;
    private List<Object> list;

    public GeneralTextAdapter(Context context, List<Object> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_general_text_item,parent,false);
            vh = new ViewHolder();
            ViewUtils.inject(vh, convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        Object object = list.get(position);
        if (object instanceof Student) {
            Student s = (Student) object;
            vh.tv.setText(s.getName());
            int id = Integer.parseInt(s.getsId());
            if (id == 201) {
                vh.iv.setImageResource(R.mipmap.yuwen);
            } else if (id == 202) {
                vh.iv.setImageResource(R.mipmap.shuxue);
            } else if (id == 203) {
                vh.iv.setImageResource(R.mipmap.yingyu);
            } else if (id == 204) {
                vh.iv.setImageResource(R.mipmap.wuli);
            } else if (id == 205) {
                vh.iv.setImageResource(R.mipmap.huaxue);
            } else if (id == 206) {
                vh.iv.setImageResource(R.mipmap.shengwu);
            } else if (id == 207) {
                vh.iv.setImageResource(R.mipmap.zhengzhi);
            } else if (id == 208) {
                vh.iv.setImageResource(R.mipmap.lishi);
            } else if (id == 209) {
                vh.iv.setImageResource(R.mipmap.dili);
            } else if (id == 210) {
                vh.iv.setImageResource(R.mipmap.wenzong);
            } else if (id == 211) {
                vh.iv.setImageResource(R.mipmap.lizong);
            } else if (id == 212) {
                vh.iv.setImageResource(R.mipmap.yinyue);
            } else if (id == 213) {
                vh.iv.setImageResource(R.mipmap.xinxi);
            } else if (id == 214) {
                vh.iv.setImageResource(R.mipmap.meishu);
            } else if (id == 216) {
                vh.iv.setImageResource(R.mipmap.tiyu);
            } else if (id == 221) {
                vh.iv.setImageResource(R.mipmap.sixiangpinde);
            } else if (id == 222) {
                vh.iv.setImageResource(R.mipmap.kexue);
            } else {
                vh.iv.setImageResource(R.mipmap.moren);
            }
        }
        return convertView;
    }

    class ViewHolder {
        @ViewInject(R.id.general_text_item_iv)
        ImageView iv;
        @ViewInject(R.id.general_text_item_tv)
        TextView tv;
    }
}
