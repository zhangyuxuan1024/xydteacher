package net.iclassmate.teacherspace.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

import net.iclassmate.teacherspace.bean.spaper.ExamQuestionPapers;

import java.util.List;

/**
 * Created by xydbj on 2016/4/12.
 */
public class LoadImage {
    private LruCache<String, Bitmap> cache;

    public LoadImage() {
        int maxSize = (int) Runtime.getRuntime().maxMemory();
        cache = new LruCache<String, Bitmap>(maxSize / 4) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    public Bitmap getBitmapForCache(String url) {
        Bitmap bitmap = null;
        bitmap = cache.get(url);
        return bitmap;
    }

    public void addBitmapToCache(String url, Bitmap value) {
        if (getBitmapForCache(url) == null) {
            cache.put(url, value);
        }
    }

    public Bitmap getImage(Context context, ImageView imageView, List<ExamQuestionPapers> listPagers, double score, int position, String url,DataCallback dataCallback) {
        Bitmap bitmap = null;
        if (getBitmapForCache(url) == null) {
            MyImageTask task = new MyImageTask(context, imageView, url, cache, listPagers, score, position,dataCallback);
            task.execute(url);
        } else {
            bitmap = getBitmapForCache(url);
        }
        return bitmap;
    }

}
