package net.iclassmate.teacherspace.ui.activity.weike;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.adapter.weike.VideoListAdapter;
import net.iclassmate.teacherspace.bean.weike.ImageState;
import net.iclassmate.teacherspace.bean.weike.Video;
import net.iclassmate.teacherspace.constant.Constants;
import net.iclassmate.teacherspace.view.CenterTextView;
import net.iclassmate.teacherspace.view.TitleBar;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeiKeActivity extends FragmentActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private TextView tv_weike;
    private GridView gridView;
    private List<Video> videoList;
    private VideoListAdapter mAdapter;
    private ImageView imageBack;
    private TextView tv_select_all;
    private View view_layout;
    private CenterTextView tv_del, tv_sahre;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mAdapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_wei_ke);

        initView();
    }

    private void initView() {
        tv_weike = (TextView) findViewById(R.id.weike_recorder);
        tv_weike.setOnClickListener(this);
        gridView = (GridView) findViewById(R.id.video_list_gridview);
        imageBack = (ImageView) findViewById(R.id.weike_img_back);
        tv_select_all = (TextView) findViewById(R.id.weike_tv_select_all);
        imageBack.setOnClickListener(this);
        tv_select_all.setOnClickListener(this);
        view_layout = findViewById(R.id.weike_layout);
        view_layout.setVisibility(View.GONE);

        tv_del = (CenterTextView) findViewById(R.id.weike_tv_del);
        tv_sahre = (CenterTextView) findViewById(R.id.weike_tv_share);
        tv_del.setOnClickListener(this);
        tv_sahre.setOnClickListener(this);

        videoList = new ArrayList<>();
        mAdapter = new VideoListAdapter(this, videoList);
        gridView.setAdapter(mAdapter);
        gridView.setOnItemClickListener(this);
        mAdapter.setImageCheck(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = (int) view.getTag();
                Video video1 = videoList.get(index);
                video1.setIsCheck(!video1.isCheck());
                videoList.set(index, video1);
                mAdapter.notifyDataSetChanged();
                for (int i = 0; i < videoList.size(); i++) {
                    video1 = videoList.get(i);
                    if (video1.isCheck()) {
                        view_layout.setVisibility(View.VISIBLE);
                        break;
                    } else if (i == videoList.size() - 1) {
                        view_layout.setVisibility(View.GONE);
                    }
                }
            }
        });
        getVideo();
    }

    public void getVideo() {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "暂无外部存储", Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                String[] projection = {MediaStore.Video.Media._ID, MediaStore.Video.Media.DATA,
                        MediaStore.Video.Media.TITLE, MediaStore.Video.Media.DURATION, MediaStore.Video.Media.DATE_TAKEN};
                String orderBy = MediaStore.Video.Media._ID;
                Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                Cursor mCursor = getContentResolver().query(uri, projection, null,
                        null, orderBy);
                if (null == mCursor) {
                    return;
                }
                videoList.clear();
                while (mCursor.moveToNext()) {
                    String path = mCursor.getString(mCursor
                            .getColumnIndex(MediaStore.Images.Media.DATA));
                    String title = mCursor.getString(mCursor.getColumnIndex(MediaStore.Video.Media.TITLE));
                    String duration = mCursor.getString(mCursor.getColumnIndex(MediaStore.Video.Media.DURATION));
                    String date_taken = mCursor.getString(mCursor.getColumnIndex(MediaStore.Video.Media.DATE_TAKEN));
                    if (!path.contains("xyd")) {
                        continue;
                    }
                    Video video = new Video();
                    video.setVideoPath(path);
                    video.setVideoName(title);
                    video.setVideoLong(duration);
                    video.setVideoTime(date_taken);
                    videoList.add(video);
                }
                mCursor.close();
                mHandler.sendEmptyMessage(0x110);
            }
        }).start();
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.weike_recorder:
                Intent intent = new Intent(WeiKeActivity.this, PicSelectActivity.class);
                startActivity(intent);
                this.finish();
                break;
            case R.id.weike_img_back:
                this.finish();
                break;
            case R.id.weike_tv_select_all:
                Toast.makeText(WeiKeActivity.this, "已选中全部", Toast.LENGTH_SHORT).show();
                view_layout.setVisibility(View.VISIBLE);
                for (int i = 0; i < videoList.size(); i++) {
                    Video video = videoList.get(i);
                    video.setIsCheck(true);
                    videoList.set(i, video);
                }
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.weike_tv_del:
                for (int i = videoList.size() - 1; i >= 0; i--) {
                    Video video = videoList.get(i);
                    if (video.isCheck()) {
                        videoList.remove(i);
                        try {
                            String path = video.getVideoPath();
                            File file = new File(path);
                            file.delete();
                            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(path))));
                        } catch (Exception e) {
                            Log.i("info", "文件删除失败!");
                        }
                    }
                }
                mAdapter.notifyDataSetChanged();
                view_layout.setVisibility(View.GONE);
                Toast.makeText(WeiKeActivity.this, "已删除", Toast.LENGTH_SHORT).show();
                break;
            case R.id.weike_tv_share:

                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(WeiKeActivity.this, WeikePlayActivity.class);
        int len = videoList.size();
        Video v = videoList.get(len - i - 1);
        intent.putExtra("videopath", v.getVideoPath());
        intent.putExtra("audiopath", v.getAudioPath());
        startActivity(intent);
        finish();
    }

}
