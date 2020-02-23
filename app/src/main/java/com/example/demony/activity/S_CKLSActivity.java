package com.example.demony.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.demony.R;
import com.example.demony.adapter.Arkrxadapter;
import com.example.demony.adapter.Aylcxadapter;
import com.example.demony.adapter.Fladapter;
import com.example.demony.adapter.Qbjladapter;
import com.example.demony.bean.Rkjl;
import com.example.demony.bean.Rkjl1;
import com.example.demony.dialog.Rl_Dialog;
import com.example.demony.net.S_VolleyTo;
import com.example.demony.net.VolleyLo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_CKLSActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.start1)
    TextView start1;
    @BindView(R.id.end1)
    TextView end1;
    @BindView(R.id.qk)
    Button qk;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.listVie1)
    ListView listVie1;
    @BindView(R.id.ex)
    ExpandableListView ex;
    private List<String> list;
    private List<Rkjl> mrkjl;
    private List<Rkjl1> mrkjl1;
    private Fladapter mfladapter;
    private Qbjladapter mqbjladapter;
    private String select="全部记录";
    private List<String> fu;
    private Map<String, List<String>> zi;
    private Aylcxadapter aylcxadapter;
    private Arkrxadapter arkrxadapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_cklsactivity);
        ButterKnife.bind(this);
        inview();
        addData();
        huoqu();
    }
    private void huoqu() {
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("get_stock_warehousing")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            mrkjl1.add(new Rkjl1(jsonObject1.optString("name"),
                                    jsonObject1.optString("xh"),
                                    jsonObject1.optString("gys"),
                                    jsonObject1.optString("shuliang"),
                                    jsonObject1.optString("dj"),
                                    jsonObject1.optString("weizhi"),
                                    jsonObject1.optString("caigoyuan"),
                                    jsonObject1.optString("lianxiren"),
                                    jsonObject1.optString("zhanghao"),
                                    jsonObject1.optString("ren"),
                                    jsonObject1.optString("time"),
                                    jsonObject1.optString("path")));

                            mrkjl.add(new Rkjl(jsonObject1.optString("name"),
                                    jsonObject1.optString("xh"),
                                    jsonObject1.optString("gys"),
                                    jsonObject1.optString("shuliang"),
                                    jsonObject1.optString("dj"),
                                    jsonObject1.optString("weizhi"),
                                    jsonObject1.optString("caigoyuan"),
                                    jsonObject1.optString("lianxiren"),
                                    jsonObject1.optString("zhanghao"),
                                    jsonObject1.optString("ren"),
                                    jsonObject1.optString("time"),
                                    jsonObject1.optString("path")));
                        }
                        setadapter();
                        setadapter1();
                    }
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }
    private void setadapter() {
        mfladapter = new Fladapter(this, list);
        listView.setAdapter(mfladapter);
        mfladapter.SetData(new Fladapter.SetData() {
            @Override
            public void setdata(String name) {
                select=name;
                mrkjl.clear();
                setTimeData();
                huoqu1(name);
            }
        });
    }
    private void huoqu1(String name) {
        Log.d("0000000", "huoqu1: -----"+name);
        fu = new ArrayList<>();
        zi = new HashMap<>();
        if (name.equals("全部记录")) {
            ex.setVisibility(View.GONE);
            listVie1.setVisibility(View.VISIBLE);
            mqbjladapter.notifyDataSetChanged();
            return;
        }
        if (name.equals("按原料查询")) {
            ex.setVisibility(View.VISIBLE);
            listVie1.setVisibility(View.GONE);
            for (int i = 0; i < mrkjl.size(); i++) {
                Rkjl rkjl = mrkjl.get(i);
                for (int y = fu.size(); y > 0; y--) {
                    if (fu.get(y - 1).equals(rkjl.getName())) {
                        fu.remove(y - 1);
                    }
                }
                fu.add(rkjl.getName());
            }

            for (int y = 0; y < fu.size(); y++) {
                List<String> zz = new ArrayList<>();
                for (int x = 0; x < mrkjl.size(); x++) {
                    Rkjl rkjl = mrkjl.get(x);
                    if (rkjl.getName().equals(fu.get(y))) {
                        zz.add(rkjl.getXh() + "=" + rkjl.getTime() + "=" + rkjl.getShuliang() + "=" + rkjl.getGys()
                                + "=" + rkjl.getRen());
                    }
                }
                zi.put(fu.get(y), zz);
            }
            setadapter2();
            return;
        }
        if (name.equals("按入库人查询")) {
            ex.setVisibility(View.VISIBLE);
            listVie1.setVisibility(View.GONE);
            for (int i = 0; i < mrkjl.size(); i++) {
                Rkjl rkjl = mrkjl.get(i);
                for (int y = fu.size(); y > 0; y--) {
                    if (fu.get(y - 1).equals(rkjl.getRen())) {
                        fu.remove(y - 1);
                    }
                }
                fu.add(rkjl.getRen());
            }

            for (int y = 0; y < fu.size(); y++) {
                List<String> zz = new ArrayList<>();
                for (int x = 0; x < mrkjl.size(); x++) {
                    Rkjl rkjl = mrkjl.get(x);
                    if (rkjl.getRen().equals(fu.get(y))) {
                        zz.add(rkjl.getName() + "=" + rkjl.getXh() + "=" + rkjl.getTime() + "=" + rkjl.getShuliang()
                                + "=" + rkjl.getGys());
                    }
                }
                zi.put(fu.get(y), zz);
            }
            setadapter3();
            return;
        }
        if (name.equals("按供应商查询")) {
            ex.setVisibility(View.VISIBLE);
            listVie1.setVisibility(View.GONE);
            for (int i = 0; i < mrkjl.size(); i++) {
                Rkjl rkjl = mrkjl.get(i);
                for (int y = fu.size(); y > 0; y--) {
                    if (fu.get(y - 1).equals(rkjl.getGys())) {
                        fu.remove(y - 1);
                    }
                }
                fu.add(rkjl.getGys());
            }

            for (int y = 0; y < fu.size(); y++) {
                List<String> zz = new ArrayList<>();
                for (int x = 0; x < mrkjl.size(); x++) {
                    Rkjl rkjl = mrkjl.get(x);
                    if (rkjl.getGys().equals(fu.get(y))) {
                        zz.add(rkjl.getName() + "=" + rkjl.getXh() + "=" + rkjl.getTime() + "=" + rkjl.getShuliang()
                                + "=" + rkjl.getGys());
                    }
                }
                zi.put(fu.get(y), zz);
            }
            setadapter3();
            return;
        }
    }
    private void setadapter3() {
        arkrxadapter = new Arkrxadapter(fu, zi);
        ex.setAdapter(arkrxadapter);
        arkrxadapter.SetData(new Arkrxadapter.SetData() {
            @Override
            public void setdata(String xh, String sj, String sl, String gys, String ylmc) {
                Intent intent = new Intent(S_CKLSActivity.this,S_XSXQActivity.class);
                intent.putExtra("xh",xh);
                intent.putExtra("gys",gys);
                intent.putExtra("shuliang",sl);
                intent.putExtra("ylmc",ylmc);
                intent.putExtra("time",sj);
                intent.putExtra("index","3");
                startActivity(intent);
            }
        });
    }

    private void setadapter2() {
        aylcxadapter = new Aylcxadapter(fu, zi);
        ex.setAdapter(aylcxadapter);
        aylcxadapter.SetData(new Aylcxadapter.SetData() {
            @Override
            public void setdata(String xh, String sj, String sl, String gys, String ren) {
                Intent intent = new Intent(S_CKLSActivity.this,S_XSXQActivity.class);
                intent.putExtra("xh",xh);
                intent.putExtra("gys",gys);
                intent.putExtra("shuliang",sl);
                intent.putExtra("ren",ren);
                intent.putExtra("time",sj);
                intent.putExtra("index","2");
                startActivity(intent);
            }
        });
    }

    private void setadapter1() {
        mqbjladapter = new Qbjladapter(this, mrkjl);
        listVie1.setAdapter(mqbjladapter);
        mqbjladapter.SetData(new Qbjladapter.SetData() {
            @Override
            public void setdata(String name, String xh, String gys, String shuliang, String danjia,
                                String weizhi, String cgy, String lxr, String dfzh, String ren, String time, String path) {
                Intent intent = new Intent(S_CKLSActivity.this,S_XSXQActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("xh",xh);
                intent.putExtra("gys",gys);
                intent.putExtra("shuliang",shuliang);
                intent.putExtra("danjia",danjia);
                intent.putExtra("weizhi",weizhi);
                intent.putExtra("cgy",cgy);
                intent.putExtra("lxr",lxr);
                intent.putExtra("dfzh",dfzh);
                intent.putExtra("ren",ren);
                intent.putExtra("time",time);
                intent.putExtra("path",path);
                intent.putExtra("index","1");
                startActivity(intent);
            }
        });
    }

    private void addData() {
        list.add("全部记录");
        list.add("按原料查询");
        list.add("按入库人查询");
        list.add("按供应商查询");
    }

    private void inview() {
        title.setText("原料库存管理-入库查询");
        list = new ArrayList<>();
        mrkjl = new ArrayList<>();
        mrkjl1 = new ArrayList<>();
        fu = new ArrayList<>();
        zi = new HashMap<>();
    }

    @OnClick({R.id.change, R.id.start1, R.id.end1, R.id.qk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.start1:
                Rl_Dialog dialog = new Rl_Dialog();
                dialog.show(getSupportFragmentManager(), "aaa");
                dialog.setGetsele(new Rl_Dialog.GETSELE() {
                    @Override
                    public void getSlelct(String start, String end) {
                        if (end.equals("")) {
                            start1.setText(start);
                            end1.setText(start);
                        } else {
                            start1.setText(start);
                            end1.setText(end);
                        }
                        setTimeData();
                    }
                });
                break;
            case R.id.end1:
                Rl_Dialog dialog1 = new Rl_Dialog();
                dialog1.show(getSupportFragmentManager(), "aaa");
                dialog1.setGetsele(new Rl_Dialog.GETSELE() {
                    @Override
                    public void getSlelct(String start, String end) {
                        if (end.equals("")) {
                            start1.setText(start);
                            end1.setText(start);
                        } else {
                            start1.setText(start);
                            end1.setText(end);
                        }
                        setTimeData();
                    }
                });
                break;
            case R.id.qk:
                start1.setText("");
                end1.setText("");
                setTimeData();
                break;
        }
    }
    private void setTimeData() {
        mrkjl.clear();
        if (!start1.getText().toString().equals("")&&!end1.getText().toString().equals(""))
        {
            for (int i=0;i<mrkjl1.size();i++)
            {
                Rkjl1 rkjl1 = mrkjl1.get(i);
                SimpleDateFormat format  =new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date d1 = format.parse(start1.getText().toString());
                    Date d2 = format.parse(end1.getText().toString());
                    Date hq= format.parse(rkjl1.getTime());
                    if (hq.getTime()<=d2.getTime()&&hq.getTime()>=d1.getTime())
                    {
                        mrkjl.add(new Rkjl(rkjl1.getName(),rkjl1.getXh()
                                ,rkjl1.getGys(),rkjl1.getShuliang(),rkjl1.getDj(),rkjl1.getWeizhi(),
                                rkjl1.getCaigoyuan(),rkjl1.getLianxiren(),rkjl1.getZhanghao(),
                                rkjl1.getRen(),rkjl1.getTime(),rkjl1.getPath()));
                    }
                    mqbjladapter.notifyDataSetChanged();
                    huoqu1(select);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }else {
            for (int i=0;i<mrkjl1.size();i++)
            {
                Rkjl1 rkjl1 = mrkjl1.get(i);
                mrkjl.add(new Rkjl(rkjl1.getName(),rkjl1.getXh()
                        ,rkjl1.getGys(),rkjl1.getShuliang(),rkjl1.getDj(),rkjl1.getWeizhi(),
                        rkjl1.getCaigoyuan(),rkjl1.getLianxiren(),rkjl1.getZhanghao(),
                        rkjl1.getRen(),rkjl1.getTime(),rkjl1.getPath()));
                mqbjladapter.notifyDataSetChanged();
                huoqu1(select);
            }
        }
    }
}
