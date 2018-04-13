package net.iclassmate.teacherspace.adapter;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.bean.notice.Notice_Info;
import net.iclassmate.teacherspace.bean.notice.Notice_exam_info;
import net.iclassmate.teacherspace.utils.StringUtils;
import net.iclassmate.teacherspace.utils.SubjectIconUtil;

import java.util.List;

/**
 *
 * Created by xyd on 2016/1/29.
 */
public class MyNoticeAdapter extends BaseAdapter {
    private Context mContext;
    private ViewHolder viewHolder;
    private Notice_Info notice_info;

    public MyNoticeAdapter(Context context,Notice_Info notice_info) {
        this.mContext = context;
        this.notice_info=notice_info;
    }

    @Override
    public int getCount() {
        return notice_info.getTotalCount();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_notice, null);
            viewHolder.info_img = (ImageView) convertView.findViewById(R.id.info_img);
            viewHolder.tv_info = (TextView) convertView.findViewById(R.id.tv_info);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            viewHolder.unRead_img= (ImageView) convertView.findViewById(R.id.unread_img);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (notice_info!=null){
            List<Notice_exam_info> notice_exam_infos=notice_info.getNotice_exam_infoList();
            if (notice_exam_infos.size()>0){
                int type=notice_exam_infos.get(position).getMsgType();
                setReadIcon(notice_exam_infos.get(position).getIsReaded());
                String info=notice_exam_infos.get(position).getInfo();
                String[] time=notice_exam_infos.get(position).getIndbtime().split(" ");
                SpannableStringBuilder buleText=StringUtils.getSpannableStringBuilder(info);
                if (type==1){
                    viewHolder.tv_info.setText(buleText);
                    viewHolder.info_img.setImageResource(R.mipmap.tongzhi);
                    viewHolder.tv_time.setText(time[0]);
                    viewHolder.tv_title.setText("阅卷通知");
                }else if (type==2){
                    viewHolder.tv_info.setText(buleText);
                    viewHolder.info_img.setImageResource(SubjectIconUtil.getIconById(notice_exam_infos.get(position).getCourseId()));
                    viewHolder.tv_time.setText(time[0]);
                    viewHolder.tv_title.setText("成绩查询");
                }

            }

        }

        return convertView;
    }

    private void setReadIcon(int isReaded) {
        if (isReaded == 0) {
            viewHolder.unRead_img.setVisibility(View.VISIBLE);
        } else {
            viewHolder.unRead_img.setVisibility(View.GONE);
        }
    }

    private class ViewHolder {
        private TextView tv_time, tv_info, tv_title;
        private ImageView info_img,unRead_img;
    }
}
