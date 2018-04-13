package net.iclassmate.teacherspace.ui.activity.weike;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.constant.Constants;
import net.iclassmate.teacherspace.utils.DensityUtil;
import net.iclassmate.teacherspace.utils.weike.MixureRecordManager;
import net.iclassmate.teacherspace.utils.weike.ScreenRecordManager;
import net.iclassmate.teacherspace.utils.weike.VideoSize;
import net.iclassmate.teacherspace.view.weike.CanvasImageView;
import net.iclassmate.teacherspace.view.weike.PagerView;
import net.iclassmate.teacherspace.view.weike.WeikeZoomView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WeikeRecorderActivity extends FragmentActivity implements View.OnClickListener {
    private ImageView img_back, img_stop, img_achive, img_pre, img_next, img_pic, img_undo, img_del, img_clean, img_red, img_blue, img_start;
    private WeikeZoomView img_weike_pic;
    private TextView tv_time;
    private CanvasImageView img_weike_canvas;
    private ImageView image_out;
    private LinearLayout pager_linear;
    private PagerView pagerView;

    private boolean isStart, isRcoder;
    private long time;
    private List<String> listSelect;
    private int cur_show_page;
    public static final int STATE_TIME = 1;

    private MixureRecordManager mixureRecordManager;
    private String recorder_video_path;
    private String recorder_audio_path;
    private String name;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case STATE_TIME:
                    long t = time / 5;
                    long a = t / 60, b = t % 60;
                    if (a > 9 && b > 9) {
                        tv_time.setText(a + ":" + b);
                    } else if (a > 9 && b <= 9) {
                        tv_time.setText(a + ":0" + b);
                    } else if (a <= 9 && b > 9) {
                        tv_time.setText("0" + a + ":" + b);
                    } else {
                        tv_time.setText("0" + a + ":0" + b);
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_weike_recorder);

        Intent intent = getIntent();
        listSelect = intent.getStringArrayListExtra("list");
        name = intent.getStringExtra("name");
        initView();
        initEvent();

        String audio_name = name;
        String video_name = name;
        mixureRecordManager = MixureRecordManager.getInstance(audio_name, video_name, "/xyd", new VideoSize(), this);
        recorder_audio_path = Environment.getExternalStorageDirectory() + "/xyd/" + audio_name + ".amr";
        recorder_video_path = Environment.getExternalStorageDirectory() + "/xyd/" + video_name + ".mp4";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == ScreenRecordManager.REQUEST_CODE) {
            //开始录制
            mixureRecordManager.startRecord(resultCode, data);
            new CalTime().start();
            Log.i("info", "开始录制");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mixureRecordManager.stopRecord();
    }

    private void toActivity() {
        Intent intent = new Intent(this, PrepareActivity.class);
        intent.putStringArrayListExtra("list", (ArrayList<String>) listSelect);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void onBackPressed() {
        toActivity();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.img_back_1:
                toActivity();
                break;
            case R.id.img_start:
                start();
                break;
            case R.id.img_stop_3:
                stop();
                break;
            case R.id.img_achieve_4:
                achieve();
                break;
            case R.id.img_pre_5:
                pre();
                break;
            case R.id.img_next_7:
                next();
                break;
            case R.id.img_undo_8:
//                img_weike_canvas.undo();
                img_weike_pic.undo();
                break;
            case R.id.img_del_9:
//                img_weike_canvas.redo();
                img_weike_pic.redo();
                break;
            case R.id.img_clear_10:
                clean();
                break;
            case R.id.img_red_11:
                redPen();
                break;
            case R.id.img_blue_12:
                bluePen();
                break;
            case R.id.pager_out:
                pager_linear.setVisibility(View.VISIBLE);
                image_out.setVisibility(View.GONE);
                break;
        }
    }

    //开始录制
    private void start() {
        isStart = true;
        isRcoder = true;
//        img_weike_pic.isStart = isStart;
//        img_weike_pic.isRecorder = isRcoder;
//        img_weike_pic.setNow_time(System.currentTimeMillis());

        mixureRecordManager.getScreenRecordPermission();
        img_start.setVisibility(View.GONE);
        Log.i("info", "获取权限");
    }

    //暂停、继续录制
    private void stop() {
        //if (!isStart) return;
        isRcoder = !isRcoder;
        if (isRcoder) {
            img_stop.setImageResource(R.mipmap.ic_zanting);
            new CalTime().start();
            Toast.makeText(WeikeRecorderActivity.this, "继续录制", Toast.LENGTH_SHORT).show();
        } else {
            img_stop.setImageResource(R.mipmap.ic_jixu_p);
            Toast.makeText(WeikeRecorderActivity.this, "暂停录制", Toast.LENGTH_SHORT).show();
        }
    }

    //录制完成
    private void achieve() {
        if (!isStart) return;
        isRcoder = false;
        isStart = false;
        mixureRecordManager.stopRecord();
        Intent intent = new Intent(this, WeikePlayActivity.class);
//        intent.putExtra("play", (Serializable) img_weike_pic.getSavePath());
//        intent.putStringArrayListExtra("list", (ArrayList<String>) listSelect);
        intent.putExtra("videopath", recorder_video_path);
        intent.putExtra("audiopath", recorder_audio_path);
        startActivity(intent);
        //save();
        this.finish();
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(recorder_video_path))));
    }

    private long last_click_time;

    //前一张图片
    private void pre() {
        if (!isStart) return;
        if (listSelect == null || listSelect.size() == 0) return;
        if (System.currentTimeMillis() - last_click_time < 2000) return;
        if (cur_show_page == 0) {
            Toast.makeText(this, "当前是第一张图片", Toast.LENGTH_SHORT).show();
        } else {
            cur_show_page--;
            loadImage(cur_show_page);
            img_weike_pic.redo();
        }
        last_click_time = System.currentTimeMillis();
    }

    //后一张图片
    private void next() {
        if (!isStart) return;
        if (listSelect == null || listSelect.size() == 0) return;
        if (System.currentTimeMillis() - last_click_time < 2000) return;
        if (cur_show_page == listSelect.size() - 1) {
            Toast.makeText(this, "当前是最后一张图片", Toast.LENGTH_SHORT).show();
        } else {
            cur_show_page++;
            loadImage(cur_show_page);
            img_weike_pic.redo();
        }
    }

    //选择橡皮檫
    private void clean() {
        img_clean.setImageResource(R.mipmap.ic_xiangpi_p);
        img_red.setImageResource(R.mipmap.ic_hongbi);
        img_blue.setImageResource(R.mipmap.ic_lanbi);

//        img_weike_pic.setmXfermode();
        img_weike_pic.setMode(WeikeZoomView.ModeEnum.XP);
    }

    //选择红笔
    private void redPen() {
        img_clean.setImageResource(R.mipmap.ic_xiangpi);
        img_red.setImageResource(R.mipmap.ic_hongbi_p);
        img_blue.setImageResource(R.mipmap.ic_lanbi);

        img_weike_pic.setmColor(Color.RED);
        img_weike_pic.setMode(WeikeZoomView.ModeEnum.TY);
    }

    //选择蓝笔
    private void bluePen() {
        img_clean.setImageResource(R.mipmap.ic_xiangpi);
        img_red.setImageResource(R.mipmap.ic_hongbi);
        img_blue.setImageResource(R.mipmap.ic_lanbi_p);

        img_weike_pic.setmColor(Color.BLUE);
        img_weike_pic.setMode(WeikeZoomView.ModeEnum.TY);
    }

    private void loadImage(int index) {
        if (listSelect != null && listSelect.size() > index) {
            Bitmap bm = BitmapFactory.decodeFile(listSelect.get(index));
            img_pic.setImageBitmap(bm);
            img_weike_pic.setImageBitmap(bm);
        }
    }

    private void initView() {
        img_back = (ImageView) findViewById(R.id.img_back_1);
        img_stop = (ImageView) findViewById(R.id.img_stop_3);
        img_achive = (ImageView) findViewById(R.id.img_achieve_4);
        img_pre = (ImageView) findViewById(R.id.img_pre_5);
        img_pic = (ImageView) findViewById(R.id.img_pic_6);
        img_next = (ImageView) findViewById(R.id.img_next_7);
        img_undo = (ImageView) findViewById(R.id.img_undo_8);
        img_del = (ImageView) findViewById(R.id.img_del_9);
        img_clean = (ImageView) findViewById(R.id.img_clear_10);
        img_red = (ImageView) findViewById(R.id.img_red_11);
        img_blue = (ImageView) findViewById(R.id.img_blue_12);
        img_start = (ImageView) findViewById(R.id.img_start);
        tv_time = (TextView) findViewById(R.id.tv_time_2);
        image_out = (ImageView) findViewById(R.id.pager_out);
        pagerView = (PagerView) findViewById(R.id.pagerView);
        pager_linear = (LinearLayout) findViewById(R.id.pager_linear);

        img_weike_pic = (WeikeZoomView) findViewById(R.id.img_weike_pic);
        img_weike_canvas = (CanvasImageView) findViewById(R.id.img_weike_canvas);

        loadImage(0);
        isStart = false;
        isRcoder = false;
//        img_weike_pic.isStart = isStart;
//        img_weike_pic.isRecorder = isRcoder;

        time = 0;
        cur_show_page = 0;
    }

    private void initEvent() {
        img_back.setOnClickListener(this);
        img_stop.setOnClickListener(this);
        img_achive.setOnClickListener(this);
        img_pre.setOnClickListener(this);
        img_next.setOnClickListener(this);
        img_undo.setOnClickListener(this);
        img_clean.setOnClickListener(this);
        img_del.setOnClickListener(this);
        img_red.setOnClickListener(this);
        img_blue.setOnClickListener(this);
        img_start.setOnClickListener(this);
        image_out.setOnClickListener(this);
    }

    class CalTime extends Thread {
        @Override
        public void run() {
            while (isStart && isRcoder) {
                try {
                    Thread.sleep(200);
                    time++;
                    Message msg = new Message();
                    msg.what = STATE_TIME;
                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
