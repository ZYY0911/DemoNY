package com.example.demony.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.demony.R;
import com.example.demony.fragment.S_Fragment_kc;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_YLKCGLActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.kc)
    TextView kc;
    @BindView(R.id.yj)
    TextView yj;
    @BindView(R.id.rk)
    TextView rk;
    @BindView(R.id.ck)
    TextView ck;
    @BindView(R.id.line)
    LinearLayout line;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_ylkcglactivicty);
        ButterKnife.bind(this);
        inview();
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.line,new S_Fragment_kc());
        ft.commit();
    }

    private void inview() {
        title.setText("原料库存管理");
    }

    @OnClick({R.id.change, R.id.kc, R.id.yj, R.id.rk, R.id.ck})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.kc:
                break;
            case R.id.yj:
                break;
            case R.id.rk:
                break;
            case R.id.ck:
                break;
        }
    }
}
