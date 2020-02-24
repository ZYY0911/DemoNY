package com.example.demony.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.demony.R;
import com.example.demony.adapter.LXGYSAdapter;
import com.example.demony.bean.Z_Jxx;
import com.example.demony.bean.Z_Sp;
import com.example.demony.net.VolleyLo;
import com.example.demony.net.Z_VolleyLo;
import com.example.demony.net.Z_VolleyTo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-02-22 ：）
 */
public class Z_LXGYSActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.my_list)
    ListView myList;
    private int lx;
    private String name;
    private List<Z_Jxx> gys;
    private List<Z_Sp> sps;
    private LXGYSAdapter adapter;
    private int index;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lxgys_layout);
        ButterKnife.bind(this);
        gys = new ArrayList<>();
        title.setText("联系供应商");
        sps = new ArrayList<>();
        Z_VolleyTo volleyTo = new Z_VolleyTo();
        volleyTo.setUrl("get_gyslb")
                .setVolleyLo(new Z_VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            /**
                             *   "gysbh": "10001",
                             *             "mc": "经销商-1",
                             *             "cs": "济南",
                             *             "dd": "槐荫区",
                             *             "fr": "李四",
                             *             "lxr": "张三",
                             *             "tel": "186565554421",
                             *             "ywfw": "车门",
                             *             "image": "car1"
                             */
                            gys.add(new Z_Jxx(jsonObject1.optString("gysbh")
                                    , jsonObject1.optString("mc")
                                    , jsonObject1.optString("cs")
                                    , jsonObject1.optString("dd")
                                    , jsonObject1.optString("fr")
                                    , jsonObject1.optString("lxr")
                                    , jsonObject1.optString("tel")
                                    , jsonObject1.optString("ywfw")
                                    , jsonObject1.optString("image")));
                        }
                        Log.d("aaa", jsonObject.toString());
                        setVolley_Sp();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("aaa", volleyError.networkResponse.headers.toString() + "");

                    }
                }).start();

    }

    private void setVolley_Sp() {
        Z_VolleyTo volleyTo = new Z_VolleyTo();
        volleyTo.setUrl("get_tjyl")
                .setVolleyLo(new Z_VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        /*sps = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<Z_Sp>>() {
                                }.getType());*/
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            sps.add(new Z_Sp(jsonObject1.optString("gssbh")
                                    , jsonObject1.optString("ylmc")
                                    , jsonObject1.optString("ylbh")
                                    , jsonObject1.optString("jg")
                                    , jsonObject1.optString("path")));
                        }
                        Log.d("aaa", jsonObject.toString());
                        initView();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("aaa", volleyError.toString() + "2");

                    }
                }).start();
    }

    private void initView() {
        lx = getIntent().getIntExtra("lx", 1);
        name = getIntent().getStringExtra("name");
        switch (lx) {
            case 1:
                initDq();
                break;
            case 2:
                initFw();
                break;
            case 3:
                initYl();
                break;
            case 4:
                initJg();
                break;
            case 5:
                initMc();
                break;
            case 6:
                initLx();
                break;
        }

    }

    private void initJg() {
        index = getIntent().getIntExtra("index", 0);
        switch (index) {
            case 0:
                for (int i = sps.size() - 1; i >= 0; i--) {
                    if (!(Integer.parseInt(sps.get(i).getJg()) < 5)) {
                        sps.remove(i);
                    }
                }
                break;
            case 1:
                for (int i = sps.size() - 1; i >= 0; i--) {
                    if (!(Integer.parseInt(sps.get(i).getJg()) >= 5 && Integer.parseInt(sps.get(i).getJg()) < 10)) {
                        sps.remove(i);
                    }
                }
                break;
            case 2:
                for (int i = sps.size() - 1; i >= 0; i--) {
                    if (!(Integer.parseInt(sps.get(i).getJg()) >= 10 && Integer.parseInt(sps.get(i).getJg()) <15 )) {
                        sps.remove(i);
                    }
                }
                break;
            case 3:
                for (int i = sps.size() - 1; i >= 0; i--) {
                    if (!(Integer.parseInt(sps.get(i).getJg()) >15)) {
                        sps.remove(i);
                    }
                }
                break;
        }
       List<Z_Jxx> gyss = new ArrayList<>();
        for (int i = 0; i < sps.size(); i++) {
            for (int j = 0; j < gys.size(); j++) {
                if (sps.get(i).getGssbh().equals(gys.get(j).getGysbh())){
                    gyss.add(gys.get(j));
                }
            }
        }
        setList(gyss);
    }

    private void initLx() {
        for (int i = gys.size() - 1; i >= 0; i--) {
            Z_Jxx jxx = gys.get(i);
            if (!jxx.getLxr().equals(name)) {
                gys.remove(i);
            }
        }
        setList(gys);
    }

    private void initMc() {
        for (int i = gys.size() - 1; i >= 0; i--) {
            Z_Jxx jxx = gys.get(i);
            if (!jxx.getMc().equals(name)) {
                gys.remove(i);
            }
        }
        setList(gys);

    }

    private void initYl() {
        for (int i = sps.size() - 1; i >= 0; i--) {
            Z_Sp sp = sps.get(i);
            if (!sp.getYlmc().equals(name)) {
                sps.remove(i);
            }
        }
        List<Z_Jxx> gysss = new ArrayList<>();
        for (int i = 0; i < sps.size(); i++) {
            for (int j = 0; j < gys.size(); j++) {
                if (sps.get(i).getGssbh().equals(gys.get(j).getGysbh())) {
                    gysss.add(gys.get(j));
                }
            }
        }
        setList(gysss);
    }

    private void initFw() {
        for (int i = gys.size() - 1; i >= 0; i--) {
            Z_Jxx jxx = gys.get(i);
            if (!jxx.getYwfw().equals(name)) {
                gys.remove(i);
            }
        }
        setList(gys);
    }

    private void initDq() {
        for (int i = gys.size() - 1; i >= 0; i--) {
            Z_Jxx jxx = gys.get(i);
            if (!jxx.getCs().equals(name)) {
                gys.remove(i);
            }
        }
        setList(gys);
    }

    private void setList(List<Z_Jxx> jxxes) {
        adapter = new LXGYSAdapter(this, R.layout.lxgys_item, jxxes);
        myList.setAdapter(adapter);
        adapter.setMyclick(new LXGYSAdapter.Myclick() {
            @Override
            public void mtCccc(int index) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + gys.get(index).getTel()));
                startActivity(intent);
            }
        });
    }


    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}
