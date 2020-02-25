package com.example.demony.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.demony.AppClient;
import com.example.demony.R;
import com.example.demony.adapter.Cxadapter;
import com.example.demony.adapter.Zpadapter1;
import com.example.demony.bean.Chaxun;
import com.example.demony.bean.Zp;
import com.example.demony.bean.Zp1;
import com.example.demony.net.S_VolleyTo;
import com.example.demony.net.VolleyLo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_CKZPActivity1 extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.change1)
    ImageView change1;
    @BindView(R.id.change2)
    ImageView change2;
    @BindView(R.id.shuru)
    EditText shuru;
    @BindView(R.id.qx)
    TextView qx;
    @BindView(R.id.listView)
    ListView listView;
    private String index;
    private List<Zp1> mzp;
    private boolean is=false;
    private Zpadapter1 zpadapter1;
    private Cxadapter mcxadapter;
    private AppClient mApp;
    private List<Chaxun> mchaxun;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_ckzpactivity1);
        ButterKnife.bind(this);
        inview();
        huoqu();
        shur();
        jianting();

    }

    private void shur() {
        shuru.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override

            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String search = shuru.getText().toString().trim();
                    Intent intent = new Intent(S_CKZPActivity1.this, S_CKZPActivity.class);
                    intent.putExtra("name", search);
                    intent.putExtra("is", true);
                    intent.putExtra("select","1");
                    startActivity(intent);
                    finish();
                    return true;

                }

                return false;

            }

        });
    }

    private void jianting() {
        shuru.addTextChangedListener(new TextWatcher() {
            private String temp;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp = shuru.getText().toString();
                if (!temp.equals("")) {
                    setadapter1();
                } else {
                    setadapter();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setadapter1() {
        mcxadapter = new Cxadapter(this,mchaxun );
        listView.setAdapter(mcxadapter);
        mcxadapter.SetData(new Cxadapter.SetData() {
            @Override
            public void setdata(String name) {
                Intent intent = new Intent(S_CKZPActivity1.this, S_CKZPActivity.class);
                intent.putExtra("name", shuru.getText().toString().trim());
                intent.putExtra("name1", name);
                intent.putExtra("is", true);
                intent.putExtra("select","2");
                startActivity(intent);
                finish();
            }
        });
    }

    private void setadapter() {
        zpadapter1 =new Zpadapter1(this,mzp);
        listView.setAdapter(zpadapter1);
    }

    private void huoqu() {
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("get_factory_fbzp")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            if (jsonObject1.optString("zt").equals("2")) {
                               if (is)
                               {
                                   mzp.add(new Zp1(jsonObject1.optString("bh"),
                                           jsonObject1.optString("zt"),
                                           jsonObject1.optString("naem"),
                                           jsonObject1.optString("xl"),
                                           jsonObject1.optString("hy"),
                                           jsonObject1.optString("szd"),
                                           jsonObject1.optString("gzdz"),
                                           jsonObject1.optString("tel"),
                                           jsonObject1.optString("email"),
                                           jsonObject1.optString("gw"),
                                           jsonObject1.optString("xz"),
                                           jsonObject1.optString("zyyq"),
                                           jsonObject1.optString("gzjlyq"),
                                           jsonObject1.optString("gwyq"),
                                           jsonObject1.optString("shr"),
                                           jsonObject1.optString("shsj"),
                                           jsonObject1.optString("time")));
                               }else {
                                   if (jsonObject1.optString("naem").equals(index))
                                   {
                                       mzp.add(new Zp1(jsonObject1.optString("bh"),
                                               jsonObject1.optString("zt"),
                                               jsonObject1.optString("naem"),
                                               jsonObject1.optString("xl"),
                                               jsonObject1.optString("hy"),
                                               jsonObject1.optString("szd"),
                                               jsonObject1.optString("gzdz"),
                                               jsonObject1.optString("tel"),
                                               jsonObject1.optString("email"),
                                               jsonObject1.optString("gw"),
                                               jsonObject1.optString("xz"),
                                               jsonObject1.optString("zyyq"),
                                               jsonObject1.optString("gzjlyq"),
                                               jsonObject1.optString("gwyq"),
                                               jsonObject1.optString("shr"),
                                               jsonObject1.optString("shsj"),
                                               jsonObject1.optString("time")));
                                   }
                               }
                            }
                        }
                        setadapter();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void inview() {
        title.setText("人才市场-招聘信息");
        index =getIntent().getStringExtra("name");
        change1.setVisibility(View.INVISIBLE);
        change2.setVisibility(View.INVISIBLE);
        mzp = new ArrayList<>();
        mApp = (AppClient) getApplication();
        mchaxun = mApp.getMchaxun();
    }

    @OnClick({R.id.change, R.id.qx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                startActivity(new Intent(S_CKZPActivity1.this,S_CKZPActivity.class));
                finish();
                break;
            case R.id.qx:
                mzp.clear();
                is=true;
                huoqu();
                zpadapter1.notifyDataSetChanged();
                break;
        }
    }
}
