package net.iclassmate.teacherspace.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import net.iclassmate.teacherspace.BuildConfig;
import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.constant.Constants;

/**
 *
 * Created by xyd on 2016/1/29.
 */
public class WelcomeActivity extends Activity {
    private RelativeLayout welcomeRelativeLayout;
    private Boolean isFirst;
    private ImageView welcomeImage;
    private Context mContext;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private TextView version;
    private RelativeLayout.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);

        //友盟统计禁止默认的页面统计方式
        MobclickAgent.openActivityDurationTrack(false);
        initView();
    }

    private void initView() {
        this.welcomeRelativeLayout = (RelativeLayout) this.findViewById(R.id.welcome_relativeLayout);
        this.welcomeImage = (ImageView) this.findViewById(R.id.welcome_image);
        version= (TextView) this.findViewById(R.id.version_code_welcome);
        mContext = this;

        params= (RelativeLayout.LayoutParams) version.getLayoutParams();
        String device=android.os.Build.MODEL;
        String versionName = BuildConfig.VERSION_NAME;
        version.setText("v"+versionName);

        if (device.equals("M351")){
            params.topMargin=dp2px(this,400);
            version.setLayoutParams(params);
        }

//        Bitmap bitmap = readBitMap(WelcomeActivity.this, R.mipmap.welcome);
//        welcomeImage.setBackgroundResource(R.mipmap.welcome);
        sharedPreferences = mContext.getSharedPreferences(
                Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);

        //设置透明度动画
        setAlphaAnimation();
    }

    //DP转PX
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private void setAlphaAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f, 1.0f);
        alphaAnimation.setDuration(3000);
        welcomeRelativeLayout.setAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                isFirst = sharedPreferences.getBoolean(Constants.FIRST_LOGIN,
                        true);
                if (isFirst) {
                    editor = sharedPreferences.edit();
                    editor.putBoolean(Constants.FIRST_LOGIN, false);
                    editor.commit();
                    Intent intent = new Intent(WelcomeActivity.this,
                            GuideActivity.class);
                    startActivity(intent);
                    WelcomeActivity.this.finish();
                } else {

                    Intent intent = new Intent(WelcomeActivity.this,
                            LoginActivity.class);
                    startActivity(intent);
                    WelcomeActivity.this.finish();
                }
            }
        });
    }

    //获取图片
/*    private Bitmap readBitMap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;//内存空间在系统内存不足时可以被回收
        opt.inInputShareable = true;
        // 获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }*/

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("WelcomeActivity"); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this);          //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("WelcomeActivity"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
        MobclickAgent.onPause(this);
    }
}
