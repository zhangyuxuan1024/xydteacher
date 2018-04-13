package net.iclassmate.teacherspace.ui.activity.weike;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.adapter.weike.PicShowAdapter;
import net.iclassmate.teacherspace.utils.DensityUtil;
import net.iclassmate.teacherspace.utils.ToastUtils;
import net.iclassmate.teacherspace.view.TitleBar;
import net.iclassmate.teacherspace.view.weike.MyShowGridView;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrepareActivity extends FragmentActivity implements TitleBar.TitleOnClickListener,
        View.OnClickListener, AdapterView.OnItemClickListener {
    private TitleBar titleBar;
    private ImageView imageNext;
    private List<String> listSelect;
    private PicShowAdapter mAdapter;
    private MyShowGridView gridView;
    private EditText editText;
    private boolean isFirstClick;
    private String name, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare);

        Intent intent = getIntent();
        listSelect = intent.getStringArrayListExtra("list");
        if (listSelect == null) {
            listSelect = new ArrayList<>();
        }
        initView();
    }

    private void initView() {
        titleBar = (TitleBar) findViewById(R.id.prepare_title_bar);
        titleBar.setLeftIcon(R.drawable.fragment_back);
        titleBar.setTitleClickListener(this);
        titleBar.setTitle("录制素材准备");
        editText = (EditText) findViewById(R.id.weike_video_name);
        editText.setOnClickListener(this);
        isFirstClick = true;

        imageNext = (ImageView) findViewById(R.id.weike_next);
        imageNext.setOnClickListener(this);

        gridView = (MyShowGridView) findViewById(R.id.pic_show_gridview);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        gridView.setmHeight(dm.heightPixels - DensityUtil.dip2px(this, 260));
        mAdapter = new PicShowAdapter(this, listSelect);
        gridView.setAdapter(mAdapter);
        gridView.setOnItemClickListener(this);
        mAdapter.setImgDelClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tag = (String) view.getTag();
                for (int i = 0; i < listSelect.size(); i++) {
                    String url = (String) listSelect.get(i);
                    if (tag.equals(url)) {
                        listSelect.remove(i);
                        mAdapter.notifyDataSetChanged();
                        break;
                    }
                }
            }
        });

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd");
        Date curDate = new Date(System.currentTimeMillis());
        name = "微课_" + System.currentTimeMillis();
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        time = formatter.format(curDate);
        editText.setText(name);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.weike_next:
                Intent intent = new Intent(PrepareActivity.this, WeikeRecorderActivity.class);
                intent.putStringArrayListExtra("list", (ArrayList<String>) listSelect);
                intent.putExtra("name", editText.getText().toString());
                intent.putExtra("time", time);
                startActivity(intent);
                this.finish();
                break;
            case R.id.weike_video_name:
                String name = editText.getHint().toString();
                if (isFirstClick) {
                    editText.setText("");
                    editText.setHint(R.string.weike_edittext_clear);
                    isFirstClick = false;
                } else if (name.equals("点击输入视频标题")) {
                    editText.setText("");
                    editText.setHint(R.string.weike_edittext_clear);
                }
                break;
        }
    }

    @Override
    public void leftClick() {
        toActivity();
    }

    @Override
    public void onBackPressed() {
        toActivity();
    }

    @Override
    public void rightClick() {

    }

    @Override
    public void titleClick() {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (i == 0) {
            toActivity();
        }
    }

    private void toActivity() {
        Intent intent = new Intent(PrepareActivity.this, PicSelectActivity.class);
        intent.putExtra("flag", true);
        intent.putStringArrayListExtra("list", (ArrayList<String>) listSelect);
        startActivity(intent);
        this.finish();
    }
}
