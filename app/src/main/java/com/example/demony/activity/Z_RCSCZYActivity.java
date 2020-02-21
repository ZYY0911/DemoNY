package com.example.demony.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demony.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-02-20 ：）
 */
public class Z_RCSCZYActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rcsczy_layout);
        ButterKnife.bind(this);
        initView();
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Class myClass = null;
                switch (menuItem.getItemId()){
                    case R.id.first:
                        break;
                    case R.id.second:
                        break;
                    case R.id.third:
                        break;
                    case R.id.forth:
                        startActivity(new Intent(Z_RCSCZYActivity.this,Z_YPRYLBActivity.class));
                        break;
                    case R.id.five:
                        break;
                    case R.id.six:
                        break;
                }
                drawerLayout.closeDrawers();
                if (myClass!=null)startActivity(new Intent(Z_RCSCZYActivity.this,myClass));
                return true;
            }
        });

    }

    private void initView() {
        title.setText("人才市场");
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        drawerLayout.openDrawer(Gravity.START);

    }
}
