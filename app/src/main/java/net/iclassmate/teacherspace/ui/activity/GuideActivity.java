package net.iclassmate.teacherspace.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.umeng.analytics.MobclickAgent;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.adapter.GuidePagerAdapter;
import net.iclassmate.teacherspace.view.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by xyd on 2016/1/29.
 */
public class GuideActivity extends Activity {
    private Context mContext;
    private Button startButton;
    private ViewPager guide_viewPager;
    private GuidePagerAdapter adapter;
    private CirclePageIndicator indicator;
    private int currentIndex = 0;
    // 引导图片资源
    private static final int[] pics = {R.mipmap.yindaoye1, R.mipmap.yindaoye2,
            R.mipmap.yindaoye3,R.mipmap.yindaoye4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);

        initView();
    }

    private void initView() {
        guide_viewPager = (ViewPager) findViewById(R.id.guide_viewpager);
        startButton = (Button) findViewById(R.id.button);
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        mContext = this;
        List<View> views = new ArrayList<View>();



        initValues(views, mParams);

    }

    private void initValues(List<View> views, LinearLayout.LayoutParams mParams) {

        for (int i = 0; i < pics.length; i++) {
            ImageView image = new ImageView(this);
            image.setLayoutParams(mParams);
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image.setImageDrawable(getResources().getDrawable(pics[i]));
            views.add(image);
        }
        adapter = new GuidePagerAdapter(this, views);
        guide_viewPager.setAdapter(adapter);
        indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        indicator.setmListener(new MypageChangeListener());
        indicator.setViewPager(guide_viewPager);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(GuideActivity.this,
                        LoginActivity.class);
                startActivity(intent);
                GuideActivity.this.finish();
                overridePendingTransition(R.anim.alpha_in_anim, // Activity的切换动画，从一个activity跳转到另外一个activity时的动画。
                        R.anim.alpha_out_anim);
            }
        });
    }


    private class MypageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int position) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onPageSelected(int arg0) {
            currentIndex = arg0;
            if (currentIndex == 3) {
                startButton.setVisibility(View.VISIBLE);
                indicator.setVisibility(View.INVISIBLE);
            } else {
                startButton.setVisibility(View.GONE);
                indicator.setVisibility(View.VISIBLE);
            }

        }

    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("GuideActivity"); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this);          //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("GuideActivity"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
        MobclickAgent.onPause(this);
    }

}
