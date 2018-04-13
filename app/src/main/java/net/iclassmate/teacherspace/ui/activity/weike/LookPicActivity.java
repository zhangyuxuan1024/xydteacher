package net.iclassmate.teacherspace.ui.activity.weike;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.constant.Constants;
import net.iclassmate.teacherspace.view.TitleBar;
import net.iclassmate.teacherspace.view.ZoomImageView;
import net.iclassmate.teacherspace.view.weike.ZoomPicView;

public class LookPicActivity extends FragmentActivity implements View.OnClickListener {
    private ZoomPicView zoom;
    private ImageView imageView;
    private String url;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_look_pic);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        initView();
    }

    private void initView() {
        zoom = (ZoomPicView) findViewById(R.id.weike_zoom_pic);
        Bitmap bitmap = BitmapFactory.decodeFile(url);
        zoom.setImageBitmap(bitmap);

        imageView = (ImageView) findViewById(R.id.weike_img_left);
        imageView.setOnClickListener(this);

        tv = (TextView) findViewById(R.id.weike_tv_select);
        tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.weike_img_left:
                this.finish();
                break;
            case R.id.weike_tv_select:
                Toast.makeText(LookPicActivity.this, "已选择", Toast.LENGTH_SHORT).show();
                SharedPreferences preferences = getSharedPreferences(Constants.SHARED_WEIKE, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("pic_isckeck", true);
                editor.commit();
                this.finish();
                break;
        }
    }
}
