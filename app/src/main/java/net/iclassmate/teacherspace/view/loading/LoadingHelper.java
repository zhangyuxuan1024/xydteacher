package net.iclassmate.teacherspace.view.loading;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.iclassmate.teacherspace.R;

import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;


/**
 * Created by xyd on 2016/2/24.
 * 加载动画帮助类
 */
public class LoadingHelper {
    RelativeLayout promptLayout;
//    GifView loadingGif;
    GifImageView loadingGif;
    ImageView promptRefresh;
    TextView promptText;

    // 数据为空时加载的view
    RelativeLayout emptyLayout;
    TextView emptyText;
    ImageView emptyRetryBtn;

    enum State {
        Loading, EMPTY, Error, Hide,
    }

    State cuState;
    LoadingListener mlistener;

    public void SetListener(LoadingListener listener) {
        mlistener = listener;
    }

    public LoadingHelper(View rootView, View emptyView) {
        promptLayout = (RelativeLayout) rootView.findViewById(R.id.loading_prompt_relative);
        loadingGif = (GifImageView) rootView.findViewById(R.id.loading_gif);
//        loadingGif.setGifImageType(GifView.GifImageType.WAIT_FINISH);
//        loadingGif.setGifImage(R.drawable.loading);
        loadingGif.setImageResource(R.drawable.loading);
        promptRefresh = (ImageView) rootView.findViewById(R.id.prompt_refresh);
        promptText = (TextView) rootView.findViewById(R.id.loading_prompt_text);

        promptRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cuState == State.EMPTY) {
                    if (mlistener != null) {
                        mlistener.OnRetryClick();
                        return;
                    }
                }
                if (cuState != State.Error) {
                    return;
                }

                if (mlistener != null) {
                    // 重试按钮
                    mlistener.OnRetryClick();
                }

            }
        });

        emptyLayout = (RelativeLayout) emptyView.findViewById(R.id.loading_empty_prompt_linear);
        emptyText = (TextView) emptyView.findViewById(R.id.loading_prompt_empty_text);
        emptyRetryBtn = (ImageView) emptyView.findViewById(R.id.prompt_empty_refresh);

        emptyRetryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cuState == State.EMPTY) {
                    if (mlistener != null) {
                        mlistener.OnRetryClick();
                    }
                }
            }
        });

        HideLoading(4);

    }

    public void ShowLoading() {
        cuState = State.Loading;
        emptyLayout.setVisibility(View.GONE);
        promptLayout.setVisibility(View.VISIBLE);
        loadingGif.setVisibility(View.VISIBLE);
        promptRefresh.setVisibility(View.INVISIBLE);
        promptText.setVisibility(View.INVISIBLE);
        final GifDrawable drawable = (GifDrawable) loadingGif.getDrawable();
        Log.i("lxw",""+drawable.isRunning());
        drawable.addAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationCompleted(int loopNumber) {
                Log.i("lxw",""+drawable.isRunning());
                drawable.reset();
            }
        });
    }

    public void ShowError(String info) {
        cuState = State.Error;
        promptRefresh.setVisibility(View.VISIBLE);
        promptLayout.setVisibility(View.VISIBLE);
        loadingGif.setVisibility(View.INVISIBLE);
        promptText.setVisibility(View.VISIBLE);
        promptText.setText(info);
    }

    public void ShowEmptyData() {
        cuState = State.EMPTY;
        emptyLayout.setVisibility(View.VISIBLE);
        emptyText.setText(R.string.loading_show_empty);
    }

    /**
     * 4为INVISIBLE,8为GONE
     *
     * @param hide
     */
    public void HideLoading(int hide) {
        cuState = State.Hide;
        promptLayout.setVisibility(hide);
    }
}
