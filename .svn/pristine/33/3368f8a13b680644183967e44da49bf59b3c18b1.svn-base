package net.iclassmate.teacherspace.ui.activity.weike;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.bean.weike.DrawPath2;
import net.iclassmate.teacherspace.bean.weike.Path2;
import net.iclassmate.teacherspace.utils.DensityUtil;
import net.iclassmate.teacherspace.utils.ToastUtils;
import net.iclassmate.teacherspace.view.weike.ZoomPicView;

import java.io.IOException;
import java.util.List;

public class WeikePlayActivity extends FragmentActivity implements View.OnClickListener {
    private ZoomPicView picView;
    private List<Path2> list;
    private List<String> listString;
    private ImageView imageStart;
    private ImageView imageShow;
    private Path mPath;
    private Paint mPaint;
    private Canvas mCanvas;
    private long start_time;
    private Bitmap baseBitmap;
    private int px = 0;
    private Handler mHandler = new Handler();
    private String video_path, audio_path;
    private VideoView videoView;
    private ImageView imge_first, image_start;
    //private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_weike_play);
        initView();

        Intent intent = getIntent();
//        list = (List<Path2>) intent.getSerializableExtra("play");
//        listString = intent.getStringArrayListExtra("list");
//        Bitmap bm = BitmapFactory.decodeFile(listString.get(0));
//        picView.setImageBitmap(bm);
//        Log.i("info", "共有线段" + list.size());

        video_path = intent.getStringExtra("videopath");
        audio_path = intent.getStringExtra("audiopath");

        Log.i("info", "视频路径=" + video_path + ",音频路径=" + audio_path);
//        Log.i("info", "图片路径=" + listString.get(0));
        videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVideoURI(Uri.parse(video_path));
        videoView.setMediaController(new android.widget.MediaController(this));
        videoView.requestFocus();

        try {
            MediaMetadataRetriever media = new MediaMetadataRetriever();
            media.setDataSource(video_path);
            Bitmap bitmap = media.getFrameAtTime(0);
            imge_first.setImageBitmap(bitmap);
        } catch (Exception e) {
            Log.i("info", "获取视频第一帧失败");
        }

//        try {
//            mediaPlayer.setDataSource(audio_path);
//            mediaPlayer.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        picView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        px = DensityUtil.dip2px(this, 54);
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                image_start.setVisibility(View.VISIBLE);
            }
        });
    }

    private long time = 0;

    @Override
    public void onBackPressed() {
        long now_time = System.currentTimeMillis();
        if (now_time - time > 2000) {
            time = now_time;
            ToastUtils.show(WeikePlayActivity.this, "再次点击返回键,系统将退出");
        } else {
            Intent intent = new Intent(this, WeiKeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void initView() {
        picView = (ZoomPicView) findViewById(R.id.play_zoom_pic_view);
        imageStart = (ImageView) findViewById(R.id.weike_play_start);
        imageStart.setOnClickListener(this);
        imageShow = (ImageView) findViewById(R.id.play_show_imageview);

        imge_first = (ImageView) findViewById(R.id.video_first);
        image_start = (ImageView) findViewById(R.id.video_start);
        image_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_start.setVisibility(View.GONE);
                imge_first.setVisibility(View.GONE);
                videoView.start();
                //mediaPlayer.start();
            }
        });
        //mediaPlayer = new MediaPlayer();
    }

    @Override
    public void onClick(View view) {
        imageStart.setVisibility(View.INVISIBLE);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        baseBitmap = Bitmap.createBitmap(screenWidth, screenHeight,
                Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(baseBitmap);
        start_time = System.currentTimeMillis();
        Toast.makeText(this, "开始播放", Toast.LENGTH_SHORT).show();
    }

    class PlayClass extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < list.size(); i++) {
                Path2 path2 = list.get(i);
                try {
                    Thread.sleep(path2.sleeptime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < path2.getDp().size(); j++) {
                    DrawPath2 dp = path2.getDp().get(j);
                    mPath = new Path();
                    mPaint = new Paint();
                    Log.i("info", "画线" + i + ",x=" + dp.startX + ",y=" + dp.startY + ",x2=" + dp.endX + ",y2=" + dp.endY);

                    mPath.moveTo(dp.startX + px, dp.startY);
                    mPath.quadTo(dp.startX + px, dp.startY, (dp.startX + dp.endX) / 2 + px, (dp.startY + dp.endY) / 2);
                    mPath.quadTo((dp.startX + dp.endX) / 2 + px, (dp.startY + dp.endY) / 2, dp.endX + px, dp.endY);

                    mPaint.setXfermode(path2.mXfermode);
                    mPaint.setColor(path2.mPaintColr);
                    mPaint.setAlpha(path2.mAlpha);
                    mPaint.setStrokeWidth(path2.mPaintWidth);
//                mPaint.setColor(Color.RED);
//                mPaint.setStrokeWidth(6f);
                    mPaint.setAntiAlias(true);
                    mPaint.setStyle(Paint.Style.STROKE);
                    mPaint.setStrokeJoin(Paint.Join.ROUND);
                    mPaint.setStrokeCap(Paint.Cap.ROUND);

                    mCanvas.drawPath(mPath, mPaint);
                    mPaint = null;
                    mPath = null;
                    try {
                        Thread.sleep(dp.stoptime - dp.starttime);
                        Log.i("info", "开始时间=" + dp.starttime + ",结束时间=" + dp.stoptime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.i("info", "显示图片");
                            imageShow.setImageBitmap(baseBitmap);
                            imageShow.invalidate();
                        }
                    });

                }
            }
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(WeikePlayActivity.this, "播放完成！", Toast.LENGTH_SHORT).show();
                    imageStart.setVisibility(View.VISIBLE);
                }
            });
        }
    }
}
