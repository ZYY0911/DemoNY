package com.example.demony.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.demony.AppClient;
import com.example.demony.R;
import com.example.demony.adapter.Sgxzadapter;
import com.example.demony.adapter.Zchladapter;
import com.example.demony.bean.He;
import com.example.demony.bean.Sgxz;
import com.example.demony.bean.Sgxz1;
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

public class S_CKActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.ylmc)
    TextView ylmc;
    @BindView(R.id.xh)
    TextView xh;
    @BindView(R.id.chr)
    TextView chr;
    @BindView(R.id.jsr)
    EditText jsr;
    @BindView(R.id.zchl)
    EditText zchl;
    @BindView(R.id.sgxz)
    TextView sgxz;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.zj)
    TextView zj;
    @BindView(R.id.yi)
    RadioButton yi;
    @BindView(R.id.er)
    RadioButton er;
    @BindView(R.id.san)
    RadioButton san;
    @BindView(R.id.si)
    RadioButton si;
    @BindView(R.id.chjl)
    Button chjl;
    @BindView(R.id.qd)
    Button qd;
    private String name,index="",xh1="",path="";
    private List<Sgxz> msgxz;
    private List<Sgxz1> msgxz1;
    private Sgxzadapter sgxzadapter;
    private int sun = 0,sun1=0;
    private Zchladapter zchladapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_ckactivity);
        ButterKnife.bind(this);
        inview();
        jiesho();
        huoqu1();
        jianting();
    }

    private void huoqu1() {
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("get_stock_warehousing")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            if (name.equals(jsonObject1.optString("name"))) {
                                msgxz1.add(new Sgxz1(jsonObject1.optString("name"),
                                        jsonObject1.optString("xh"),
                                        jsonObject1.optString("gys"),
                                        jsonObject1.optString("shuliang"),
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

                        }

                    }
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void jianting() {
        zchl.addTextChangedListener(new TextWatcher() {
            String temp;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                msgxz.clear();
                temp= zchl.getText().toString();

                if (!temp.equals(""))
                {
                    sun1=Integer.parseInt(temp);
                    Collections.sort(msgxz1, new Comparator<Sgxz1>() {
                        @Override
                        public int compare(Sgxz1 o1, Sgxz1 o2) {
                            try {
                                String time1 = o1.getTime();
                                String time2 = o2.getTime();
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                Date d1 = format.parse(time1);
                                Date d2 = format.parse(time2);
                                if (d1.getTime()<d2.getTime())
                                {
                                    return 1;
                                }else  if (d1.getTime()<d2.getTime())
                                {
                                    return 0;
                                }else {
                                    return -1;
                                }
                            }catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                            return 0;
                        }
                    });
                    for (int i=0;i<msgxz1.size();i++)
                    {
                        Sgxz1 sgxz1 = msgxz1.get(i);
                        int yl= Integer.parseInt(sgxz1.getYuliang());
                        msgxz.add(new Sgxz(sgxz1.getName(),sgxz1.getXh(),sgxz1.getGys(),sgxz1.getShuliang()
                                ,(yl>=Integer.parseInt(temp)?(yl-Integer.parseInt(temp)):0)+"",sgxz1.getDj(),sgxz1.getWeizhi(),sgxz1.getCaigoyuan(),sgxz1.getLianxiren(),
                                sgxz1.getZhanghao(),sgxz1.getRen(),sgxz1.getTime(),sgxz1.getPath()));
                        zj.setText("总计："+(Integer.parseInt(temp)*Integer.parseInt(sgxz1.getDj()))+"元");
                        if (yl>Integer.parseInt(temp))
                        {
                            break;
                        }
                    }
                    Log.d("11111", "onTextChanged: ----"+msgxz);
                    setadapter1();


                }else {
                    sun1=0;
                    zj.setText("总计：0元");
                    msgxz.clear();
                    setadapter1();
                }


            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void setadapter1() {
        zchladapter = new Zchladapter(this,msgxz);
        listView.setAdapter(zchladapter);
    }
    private void jiesho() {
        name = getIntent().getStringExtra("name");
        xh1 =getIntent().getStringExtra("xh");
        path = getIntent().getStringExtra("path");
        xh.setText("型号："+getIntent().getStringExtra("xh"));
        ylmc.setText("原料名称："+name);
        chr.setText("出货人："+AppClient.getName());
    }
    private void huoqu() {
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("get_stock_warehousing")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        msgxz.clear();
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            if (name.equals(jsonObject1.optString("name"))) {
                                msgxz.add(new Sgxz(jsonObject1.optString("name"),
                                        jsonObject1.optString("xh"),
                                        jsonObject1.optString("gys"),
                                        jsonObject1.optString("shuliang"),
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

                        }

                        setadapter();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }
    private void setadapter() {
        sgxzadapter = new Sgxzadapter(this, msgxz);
        listView.setAdapter(sgxzadapter);
        sgxzadapter.SetData(new Sgxzadapter.SetData() {
            @Override
            public void setdata(int position, String sl,String je,String dj) {
                if (!sl.equals("0")) {
                    sun+=Integer.parseInt(je);
                    sun1+=Integer.parseInt(sl);
                    zj.setText("总计：" + sun+"元");
                } else {
                    sun = 0;
                    sun1=0;
                    zj.setText("总计：0元");
                    sgxzadapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void inview() {
        title.setText("原料库存管理-出库");
        msgxz = new ArrayList<>();
        msgxz1 = new ArrayList<>();
    }

    @OnClick({R.id.change, R.id.sgxz, R.id.chjl, R.id.qd,R.id.yi,R.id.er,R.id.san,R.id.si})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.sgxz:
                zchl.setText("");
                huoqu();
                break;
            case R.id.chjl:
                startActivity(new Intent(S_CKActivity.this,S_CKLSActivity1.class));
                break;
            case R.id.qd:
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date(System.currentTimeMillis());
                final String t = format.format(date);
                if (jsr.getText().toString().equals("")) {
                    Toast.makeText(S_CKActivity.this, "请输入接货人", Toast.LENGTH_LONG).show();
                    return;
                }
                if (index.equals("")) {
                    Toast.makeText(S_CKActivity.this, "请选择生产线", Toast.LENGTH_LONG).show();
                    return;
                }
                if (sun1==0) {
                    Toast.makeText(S_CKActivity.this, "请输入出货数量", Toast.LENGTH_LONG).show();
                    return;
                }
                final Dialog dialog = new Dialog(S_CKActivity.this);
                dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.sgxz_dialog);
                Button qd = dialog.findViewById(R.id.qd);
                TextView tv1 = dialog.findViewById(R.id.ylmc);
                TextView tv2 = dialog.findViewById(R.id.ylxh);
                TextView tv3 = dialog.findViewById(R.id.cksl);
                TextView tv4 = dialog.findViewById(R.id.cksj);
                TextView tv5 = dialog.findViewById(R.id.ckr);
                TextView tv6 = dialog.findViewById(R.id.jsr);
                TextView tv7 = dialog.findViewById(R.id.qx);
                tv1.setText("原料名称：" + name);
                tv2.setText("原料型号：" + xh1);
                tv3.setText("出库数量：" + sun1);
                tv4.setText("出库时间：" + t);
                tv5.setText("出库人：" + AppClient.getName());
                tv6.setText("接收人：" + jsr.getText().toString());
                tv7.setText("去向：济南");

                qd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addData(name, xh1, sun + "", t, AppClient.getName(), jsr.getText().toString(), "济南", index);
                        dialog.dismiss();
                    }
                });
                WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
                layoutParams.width = 700;
                layoutParams.height = 400;
                dialog.getWindow().setAttributes(layoutParams);
                dialog.show();



                break;
            case R.id.yi:
                index = yi.getText().toString();
                break;
            case R.id.er:
                index = er.getText().toString();
                break;
            case R.id.san:
                index = san.getText().toString();
                break;
            case R.id.si:
                index = si.getText().toString();
                break;
        }
    }
    private void addData(String ylm, String xh, String sl, String time, String chr, String jsr, String qx, String scx) {
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("set_stock_shipment")
                .setJsonObject("name",ylm)
                .setJsonObject("xh",xh)
                .setJsonObject("shuliang",sl)
                .setJsonObject("time",time)
                .setJsonObject("chr",chr)
                .setJsonObject("jsr",jsr)
                .setJsonObject("qx",qx)
                .setJsonObject("path",path)
                .setJsonObject("scx",scx)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(S_CKActivity.this, "添加成功", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }
}
