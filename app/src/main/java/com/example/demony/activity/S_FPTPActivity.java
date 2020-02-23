package com.example.demony.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demony.Image;
import com.example.demony.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_FPTPActivity extends AppCompatActivity {
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.fanhui)
    Button fanhui;
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    private String path;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_fptpactivity);
        ButterKnife.bind(this);
        title.setText("发票图片");
        path = getIntent().getStringExtra("path");
        image.setImageBitmap(BitmapFactory.decodeFile(path));
        image.setOnTouchListener(new Image(image));
    }

    @OnClick(R.id.fanhui)
    public void onFanhuiClicked() {
        finish();
    }
}
