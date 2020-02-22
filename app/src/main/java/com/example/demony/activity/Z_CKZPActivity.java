package com.example.demony.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.demony.AppClient;
import com.example.demony.R;
import com.example.demony.adapter.ZPXXAdapter;
import com.example.demony.bean.ZPXX;
import com.example.demony.dialog.Z_JLDialog;
import com.example.demony.net.VolleyLo;
import com.example.demony.net.Z_VolleyTo;
import com.example.demony.util.SimpData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-02-20 ：）
 */
public class Z_CKZPActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.start_image)
    ImageView startImage;
    @BindView(R.id.zhaoping_image)
    ImageView zhaopingImage;
    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.image_find)
    ImageView imageFind;
    @BindView(R.id.myList)
    ListView myList;
    private List<ZPXX> zpxxes;
    private List<String> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zpxx_layout);
        ButterKnife.bind(this);
        initView();
        setVolley();

    }

    private void setVolley() {
        Z_VolleyTo volleyTo = new Z_VolleyTo();
        volleyTo.setUrl("get_factory_recruit")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        zpxxes = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<ZPXX>>() {
                                }.getType());
                        setListView();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setListView() {
        final ZPXXAdapter adapter = new ZPXXAdapter(this, R.layout.zpxx_item2, zpxxes, list);
        myList.setAdapter(adapter);
        adapter.setData(new ZPXXAdapter.SetData() {
            @Override
            public void setdata(int position, int lx, boolean sc) {
                ZPXX zpxx = zpxxes.get(position);
                if (lx==1){
                    adapter.setIndex(position);
                }else if (lx==2){
                    list.add(zpxx.getNaem());
                }else if (lx==3){
                    Z_JLDialog dialog = new Z_JLDialog(zpxx.getNaem());
                    dialog.show(getSupportFragmentManager(), "aaa");
                }
                adapter.notifyDataSetChanged();
            }
        });

    }

    private void initView() {
        title.setText("人才市场--招聘信息");
        list = new ArrayList<>();
    }


    @OnClick({R.id.change, R.id.start_image, R.id.zhaoping_image, R.id.image_find})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.start_image:
                break;
            case R.id.zhaoping_image:
                break;
            case R.id.image_find:
                break;
        }
    }
}
