package net.iclassmate.teacherspace.application;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

/**
 *
 * Created by xyd on 2016/1/26.
 */
public class MyApplication extends Application {
    // 获取到主线程的上下文
    private static MyApplication mContext;
    // 获取到主线程的hander;
    private static Handler mMainThreadHander;
    // 获取到主线程的looper
    private static Looper mMainThreadLooper;
    // 获取到主线程
    private static Thread mMainThead;
    // 获取到主线程的id
    private static int mMainTheadId;

    //版本信息类
    private double versionCode;
    private double versionSize;
    private String versionMark;
    private String versionUrl;
    private String versionName;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
        mMainThreadHander = new Handler();
        mMainThreadLooper = getMainLooper();
        mMainThead = Thread.currentThread();
        mMainTheadId = android.os.Process.myTid();
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public double getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(double versionCode) {
        this.versionCode = versionCode;
    }

    public double getVersionSize() {
        return versionSize;
    }

    public void setVersionSize(double versionSize) {
        this.versionSize = versionSize;
    }

    public String getVersionMark() {
        return versionMark;
    }

    public void setVersionMark(String versionMark) {
        this.versionMark = versionMark;
    }

    public String getVersionUrl() {
        return versionUrl;
    }

    public void setVersionUrl(String versionUrl) {
        this.versionUrl = versionUrl;
    }

    public static MyApplication getApplication() {
        return mContext;
    }

    public static Handler getMainThreadHandler() {
        return mMainThreadHander;
    }

    public static Looper getMainThreadLooper() {
        return mMainThreadLooper;
    }

    public static Thread getMainThread() {
        return mMainThead;
    }

    public static int getMainThreadId() {
        return mMainTheadId;
    }
}
