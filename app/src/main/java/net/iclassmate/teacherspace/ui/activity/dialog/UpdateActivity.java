package net.iclassmate.teacherspace.ui.activity.dialog;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.utils.DoubleUtils;
import net.iclassmate.teacherspace.utils.LoginUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class UpdateActivity extends Activity implements OnClickListener {
    private TextView update2_cancel;
    private TextView update2_percentage;
    private TextView update2_count, update2_all;
    /* 下载中 */
    private static final int DOWNLOAD = 1;
    /* 下载结束 */
    private static final int DOWNLOAD_FINISH = 2;
    /* 下载异常 */
    private static final int EXCEPTION = 0;
    /* 非法http协议 */
    private static final int MALFORMED = -1;
    /* 服务器不可用 */
    private static final int HOSTWRONG = -2;
    /* 下载保存路径 */
    private String mSavePath;
    /* 记录进度条数量 */
    private int progress;
    /* 是否取消更新 */
    private boolean cancelUpdate = false;

    private Context mContext;
    /* 更新进度条 */
    private ProgressBar mProgress;
    private Dialog mDownloadDialog;
    private Dialog noticeDialog;
    private String mark;
    private String url;
    private String all;
    private String now;
    private DefaultHttpClient client;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {

            update2_all.setText(all + "M");
            update2_percentage.setText(progress + "%");
            update2_count.setText(now + "M");
            switch (msg.what) {
                // 正在下载
                case DOWNLOAD:
                    // 设置进度条位置
                    mProgress.setProgress(progress);
                    break;
                case DOWNLOAD_FINISH:
                    //杀死下载窗口
                    UpdateActivity.this.finish();
                    // 安装文件
                    installApk();
                    break;
                case 3:
                    if (noticeDialog != null && noticeDialog.isShowing()) {
                        noticeDialog.dismiss();
                    }
                    // showDownloadDialog();
                    break;
                case 10:
                    if (mDownloadDialog != null) {
                        mDownloadDialog.dismiss();
                    }
                    Toast.makeText(mContext, "下载出错,重新更新版本", Toast.LENGTH_SHORT).show();
                    break;
                case EXCEPTION:
                    if (mDownloadDialog != null) {
                        mDownloadDialog.dismiss();
                    }
                    if (!cancelUpdate) {
                        Toast.makeText(mContext, "服务器超时,更新版本失败!", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case MALFORMED:
                    if (mDownloadDialog != null) {
                        mDownloadDialog.dismiss();
                    }
                    Toast.makeText(mContext, "非法的HTTP协议", Toast.LENGTH_SHORT).show();
                    break;
                case HOSTWRONG:
                    if (mDownloadDialog != null) {
                        mDownloadDialog.dismiss();
                    }
                    Toast toast = Toast.makeText(mContext, "服务器不可用", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    break;
                default:
                    break;
            }
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_update);

        mContext = this;

        Intent intent = getIntent();
        url = intent.getStringExtra("versionUrl");
        Log.i("info", "版本更新的url:" + url);
        init();
        downloadApk();
    }

    public void init() {
        update2_all = (TextView) findViewById(R.id.update_all);
        update2_count = (TextView) findViewById(R.id.update_count);
        update2_percentage = (TextView) findViewById(R.id.update_percentage);
        mProgress = (ProgressBar) findViewById(R.id.update_progressbar);
        update2_cancel = (TextView) findViewById(R.id.update_cancel);
        update2_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.update_cancel:
//			client.getConnectionManager().shutdown();
                cancelUpdate = true;
                UpdateActivity.this.finish();
                break;
        }
    }

    /**
     * 下载apk文件
     */
    private synchronized void downloadApk() {
        // 启动新线程下载软件
        new downloadApkThread().start();
    }

    /**
     * 下载文件线程
     */
    private class downloadApkThread extends Thread {
        @Override
        public void run() {
            try {
                // 判断SD卡是否存在，并且是否具有读写权限
                if (Environment.getExternalStorageState().equals(
                        Environment.MEDIA_MOUNTED)) {
                    // 获得存储卡的路径
                    String sdpath = Environment.getExternalStorageDirectory() + "/";
                    mSavePath = sdpath + "net.iclassmate.teacherspace/download";
                    String ip = null;
                    if (url != null) {
                        ip = url;
                    } else {
                        mHandler.sendEmptyMessage(10);
                        return;
                    }
                    URL url = new URL(ip);
                    client = new DefaultHttpClient();
                    LoginUtils login = new LoginUtils();
                    /*client.getCredentialsProvider().setCredentials(
							new AuthScope(url.getHost(), url.getPort(),
									AuthScope.ANY_REALM, AuthPolicy.DIGEST),

							new UsernamePasswordCredentials(login
									.getLoginUser(), login.getLoginPw()));*/
                    client.getParams().setParameter(
                            CoreConnectionPNames.CONNECTION_TIMEOUT, 10 * 1000);
                    client.getParams().setParameter(
                            CoreConnectionPNames.SO_TIMEOUT, 10 * 1000);
                    HttpGet httpGet = new HttpGet(ip);
                    HttpResponse response;
                    response = client.execute(httpGet);

                    if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        HttpEntity entity = response.getEntity();

                        long length = entity.getContentLength();
                        all = DoubleUtils.doubletodouble((double) length / 1024 / 1024);
                        InputStream is = entity.getContent();

                        File file = new File(mSavePath);
                        // 判断文件目录是否存在
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        File apkFile = new File(mSavePath, "xyd_teacher.apk");
                        FileOutputStream fos = new FileOutputStream(apkFile);
                        int count = 0;
                        // 缓存
                        byte buf[] = new byte[1024];
                        // 写入到文件中
                        do {
                            int numread = is.read(buf);
                            count += numread;
                            now = DoubleUtils.doubletodouble((double) count / 1024 / 1024);
                            // 计算进度条位置
                            progress = (int) (((float) count / length) * 100);
                            // 更新进度
                            mHandler.sendEmptyMessage(DOWNLOAD);
                            if (numread <= 0) {
                                // 下载完成
                                mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
                                break;
                            }
                            // 写入文件
                            fos.write(buf, 0, numread);
                        } while (!cancelUpdate);// 点击取消就停止下载.
                        if (cancelUpdate) {// 手动取消下载
                            client.getConnectionManager().shutdown();
                        }

                        fos.close();
                        is.close();
                    } else {
                        mHandler.sendEmptyMessage(HOSTWRONG);
                    }
                }
            } catch (MalformedURLException e) {
                mHandler.sendEmptyMessage(MALFORMED);
                e.printStackTrace();
            } catch (IOException e) {
                mHandler.sendEmptyMessage(EXCEPTION);
                e.printStackTrace();
            }
            // 取消下载对话框显示
            // mDownloadDialog.dismiss();
//			Update2Activity.this.finish();
        }
    }

    ;

    /**
     * 安装APK文件
     */
    public void installApk() {
        File apkfile = new File(mSavePath, "xyd_teacher.apk");
        if (!apkfile.exists()) {
            return;
        }
        // 通过Intent安装APK文件
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
        startActivity(i);
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("UpdateActivity"); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this);          //统计时长
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("UpdateActivity"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
        MobclickAgent.onPause(this);
    }

}