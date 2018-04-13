package net.iclassmate.teacherspace.adapter.weike;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.bean.weike.ImageState;
import net.iclassmate.teacherspace.ui.activity.weike.LookPicActivity;
import net.iclassmate.teacherspace.utils.LoadImage;
import net.iclassmate.teacherspace.utils.LoadImageSd;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xydbj on 2016/5/5.
 */
public class PicSelectAdapter extends BaseAdapter {
    private Context context;
    private List<ImageState> list;
    private int height, width;
    private LoadImageSd load;
    private View.OnClickListener imgClick;
    private View.OnClickListener imgCkeckClick;

    public void setImgClick(View.OnClickListener imgClick) {
        this.imgClick = imgClick;
    }

    public void setImgCkeckClick(View.OnClickListener imgCkeckClick) {
        this.imgCkeckClick = imgCkeckClick;
    }

    public PicSelectAdapter(Context context, List<ImageState> list) {
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
        return i;
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
            view = bindCameraView(i, view, viewGroup);
        } else if (getItemViewType(i) == 1) {
            view = bindImageView(i, view, viewGroup);
        }
        return view;
    }

    private View bindCameraView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.weike_pic_selected_gridview_camera_item, null);
        TextView tv = (TextView) view.findViewById(R.id.weike_camera_tv);

        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        height = view.getMeasuredHeight();
        width = view.getMeasuredWidth();
        return view;
    }

    private View bindImageView(final int i, View view, ViewGroup viewGroup) {
        ViewPicHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.weike_pic_selected_gridview_item, null);
            holder = new ViewPicHolder();
            holder.img_pic = (ImageView) view.findViewById(R.id.weike_selected_img_pic);
            holder.img_check = (ImageView) view.findViewById(R.id.weike_selected_img_check);
            holder.img_check_view = (ImageView) view.findViewById(R.id.weike_selected_img_check_view);
            view.setTag(holder);
        } else {
            holder = (ViewPicHolder) view.getTag();
        }
        holder.img_pic.setOnClickListener(imgClick);
        holder.img_check.setOnClickListener(imgCkeckClick);

        FrameLayout.LayoutParams frameParams = (FrameLayout.LayoutParams) holder.img_pic.getLayoutParams();
        frameParams.height = height;
        int len = list.size();

        ImageState state = list.get(len - i);
        holder.img_pic.setTag(state.path);
        holder.path = state.path;
        holder.img_check.setTag(holder);
        if (state.check) {
            holder.img_check.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_xuanzhong));
            holder.img_check_view.setVisibility(View.VISIBLE);
        } else {
            holder.img_check.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_weixuan));
            holder.img_check_view.setVisibility(View.GONE);
        }

        load.downloadImage(holder.img_pic, state.path);
        load.setmHeight(height);
        load.setmWidth(width);
        return view;
    }

    public class ViewPicHolder {
        public ImageView img_pic, img_check, img_check_view;
        public String path;
    }
}
