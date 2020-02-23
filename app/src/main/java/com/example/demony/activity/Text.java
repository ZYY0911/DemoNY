package com.example.demony.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.example.demony.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2020-02-22 ：）
 */
public class Text extends AppCompatActivity {
    @BindView(R.id.webvioew)
    WebView webvioew;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        ButterKnife.bind(this);
        webvioew.loadUrl("http://192.168.2.160:3333/get_gyslb");
    }
}
