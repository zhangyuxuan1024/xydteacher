package net.iclassmate.teacherspace.utils.weike;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Basti031 on 2015/11/23.
 */
public class MixureRecordManager {
    private AudioManager audioManager;
    private ScreenRecordManager screenRecordManager;

    private static MixureRecordManager mInstance;

    private MixureRecordManager(String audioName, String videoName, String dir, VideoSize size, Context context) {
        audioManager = AudioManager.getInstance(audioName, dir);
        screenRecordManager = ScreenRecordManager.getInstance(size, dir, videoName, context);
    }

    public static MixureRecordManager getInstance(String audioName, String videoName, String dir, VideoSize size, Context context) {
//        if (mInstance == null) {
//            synchronized (MixureRecordManager.class) {
//                if (mInstance == null) {
        mInstance = new MixureRecordManager(audioName, videoName, dir, size, context);
//                }
//            }
//        }
        return mInstance;
    }

    //录屏获取权限

    public void getScreenRecordPermission() {
        screenRecordManager.getPermission();
    }

    public void startRecord(int resultcode, Intent data) {
        audioManager.prepareAudio();
        screenRecordManager.startRecord(resultcode, data);
    }

    public void stopRecord() {
        if (audioManager != null) {
            audioManager.release();
            audioManager = null;
        }
        if (screenRecordManager != null) {
            screenRecordManager.stopRecord();
            screenRecordManager = null;
        }
    }
}
