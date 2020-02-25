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
import com.example.demony.fragment.S_Fragment_jbxx;
import com.example.demony.fragment.S_Fragment_jllb;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_WDJLActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.line)
    LinearLayout line;
    @BindView(R.id.jbxx)
    TextView jbxx;
    @BindView(R.id.jllb)
    TextView jllb;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_wdjlactivity);
        ButterKnife.bind(this);
        inview();
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.line,new S_Fragment_jbxx());
        ft.commit();
    }

    private void inview() {
        title.setText("人才市场-我的简历");
    }

    @OnClick({R.id.change, R.id.jbxx, R.id.jllb})
    public void onViewClicked(View view) {
        ft = getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.jbxx:
                ft.replace(R.id.line,new S_Fragment_jbxx());
                break;
            case R.id.jllb:
                ft.replace(R.id.line,new S_Fragment_jllb());
                break;
        }
        ft.commit();
    }
}
