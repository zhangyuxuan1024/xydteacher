package net.iclassmate.teacherspace.adapter.weike;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.bean.exam.Exam;
import net.iclassmate.teacherspace.bean.weike.Video;
import net.iclassmate.teacherspace.view.CenterTextView;

import java.util.List;

/**
 * Created by xydbj on 2016/5/13.
 */
public class VideoListAdapter extends BaseAdapter {
    private Context context;
    private List<Video> list;
    private View.OnClickListener ImageCheck;
    private Handler mHandler = new Handler();
    private LruCache<String, Bitmap> lruCache;

    public void setImageCheck(View.OnClickListener imageCheck) {
        ImageCheck = imageCheck;
    }

    public VideoListAdapter(Context context, List<Video> list) {
        this.context = context;
        this.list = list;
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int mCacheSize = maxMemory / 4;
        lruCache = new LruCache<String, Bitmap>(mCacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (list != null) {
            ret = list.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.weike_gridview_show_video, null);
            holder = new ViewHolder();
            holder.imageVideo = (ImageView) view.findViewById(R.id.video_image);
            holder.imageCheckView = (ImageView) view.findViewById(R.id.video_image_check_view);
            holder.imageCheck = (ImageView) view.findViewById(R.id.video_image_check);
            holder.imagePlay = (ImageView) view.findViewById(R.id.video_image_play);
            holder.tvName = (TextView) view.findViewById(R.id.video_tv_name);
            holder.tvTime = (TextView) view.findViewById(R.id.video_tv_time_day);
            holder.tvLong = (TextView) view.findViewById(R.id.video_tv_time_long);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        int len = list.size();
        final Video video = list.get(len - i - 1);
        holder.tvName.setText(video.getVideoName());
        holder.tvTime.setText(video.getVideoTime());
        holder.tvLong.setText(video.getVideoLong());
        holder.imageVideo.setTag(video.getAudioPath());
        if (video.isCheck()) {
            holder.imagePlay.setVisibility(View.GONE);
            holder.imageCheckView.setVisibility(View.VISIBLE);
            holder.imageCheck.setImageResource(R.mipmap.ic_xuanzhong);
        } else {
            holder.imagePlay.setVisibility(View.VISIBLE);
            holder.imageCheckView.setVisibility(View.GONE);
            holder.imageCheck.setImageResource(R.mipmap.ic_weixuan);
        }
        holder.imageCheck.setOnClickListener(ImageCheck);
        holder.imageCheck.setTag(len - i - 1);

        final ViewHolder finalHolder = holder;
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Bitmap bitmap = getBitmapForCache(video.getVideoPath());
                    if (bitmap != null) {
                        finalHolder.imageVideo.setImageBitmap(bitmap);
                    } else {
                        MediaMetadataRetriever media = new MediaMetadataRetriever();
                        media.setDataSource(video.getVideoPath());
                        bitmap = media.getFrameAtTime(0);
                        putBitmapToCache(video.getVideoPath(), bitmap);
                    }
                } catch (Exception e) {

                }
            }
        });
        return view;
    }

    private Bitmap getBitmapForCache(String key) {
        Bitmap bitmap = lruCache.get(key);
        return bitmap;
    }

    private void putBitmapToCache(String key, Bitmap bitmap) {
        if (lruCache.get(key) == null) {
            lruCache.put(key, bitmap);
        }
    }

    class ViewHolder {
        ImageView imageVideo, imageCheckView, imageCheck, imagePlay;
        TextView tvName, tvTime, tvLong;
    }
}
