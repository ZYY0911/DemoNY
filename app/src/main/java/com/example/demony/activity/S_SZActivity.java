package com.example.demony.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.demony.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_SZActivity extends AppCompatActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.title2)
    TextView title2;
    @BindView(R.id.fb)
    LinearLayout fb;
    @BindView(R.id.sh)
    LinearLayout sh;
    @BindView(R.id.shls)
    LinearLayout shls;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_szactivity);
        ButterKnife.bind(this);
        inview();
    }

    private void inview() {
        title.setText("返回");
        title1.setText("设置");
    }

    @OnClick({R.id.title, R.id.fb, R.id.sh, R.id.shls})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title:
                finish();
                break;
            case R.id.fb:
                startActivity(new Intent(S_SZActivity.this,S_FBZPActivity.class));
                break;
            case R.id.sh:
                startActivity(new Intent(S_SZActivity.this,S_SHActivity.class));
                break;
            case R.id.shls:
                startActivity(new Intent(S_SZActivity.this,S_SHLSActivity.class));
                break;
        }
    }
}
