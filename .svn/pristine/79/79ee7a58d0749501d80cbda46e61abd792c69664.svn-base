package net.iclassmate.teacherspace.adapter.weike;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.utils.LoadImageSd;

import java.util.List;

/**
 * Created by xydbj on 2016/5/5.
 */
public class PicShowAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private int height, width;
    private LoadImageSd load;
    private View.OnClickListener imgDelClick;

    public void setImgDelClick(View.OnClickListener imgDelClick) {
        this.imgDelClick = imgDelClick;
    }

    public PicShowAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        load = new LoadImageSd();
    }


    @Override
    public int getCount() {
        int ret = 0;
        if (list != null) {
            ret = list.size() + 1;
        }
        return ret;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        int ret = 0;
        if (position == 0) {
            ret = 0;
        } else {
            ret = 1;
        }
        return ret;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (getItemViewType(i) == 0) {
            view = bindAddView(i, view, viewGroup);
        } else if (getItemViewType(i) == 1) {
            view = bindImageView(i, view, viewGroup);
        }
        return view;
    }

    private View bindAddView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.weike_gridview_show_item_1, null);
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        ImageView imageView = (ImageView) view.findViewById(R.id.weike_show_item_1_iv);
        height = imageView.getMeasuredHeight();
        width = imageView.getMeasuredWidth();
        return view;
    }

    private View bindImageView(final int i, View view, ViewGroup viewGroup) {
        ViewPicHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.weike_gridview_show_item_2, viewGroup, false);
            holder = new ViewPicHolder();
            holder.img_pic = (ImageView) view.findViewById(R.id.weike_gridview_show_iv);
            holder.img_check = (ImageView) view.findViewById(R.id.weike_gridview_show_iv_del);
            holder.tv = (TextView) view.findViewById(R.id.weike_gridview_show_tv);
            view.setTag(holder);
        } else {
            holder = (ViewPicHolder) view.getTag();
        }
        LinearLayout.LayoutParams frameParams = (LinearLayout.LayoutParams) holder.img_pic.getLayoutParams();
        frameParams.height = height;

        int len = list.size();
        String url = list.get(len - i);
        holder.img_pic.setTag(url);
        load.downloadImage(holder.img_pic, url);
        load.setmHeight(height);
        load.setmWidth(width);

        holder.tv.setText("第" + (len - i + 1) + "页");
        holder.img_check.setTag(url);
        holder.img_check.setOnClickListener(imgDelClick);
        return view;
    }

    public class ViewPicHolder {
        public ImageView img_pic, img_check;
        public TextView tv;
    }
}
