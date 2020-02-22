package com.example.demony.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.demony.R;
import com.example.demony.adapter.Tjyladapter;
import com.example.demony.bean.TJyl;
import com.example.demony.bean.Update;
import com.example.demony.dialog.Tjyl_dialog;
import com.example.demony.net.S_VolleyTo;
import com.example.demony.net.VolleyLo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_TJGYSActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.change1)
    ImageView change1;
    @BindView(R.id.addImage)
    Button addImage;
    @BindView(R.id.im)
    ImageView im;
    @BindView(R.id.add)
    Button add;
    @BindView(R.id.addsp)
    Button addsp;
    @BindView(R.id.listView)
    ListView listView;
    private final int CHOOSE_PHOTO = 2;
    @BindView(R.id.bh)
    EditText bh;
    @BindView(R.id.mc)
    EditText mc;
    @BindView(R.id.cs)
    EditText cs;
    @BindView(R.id.dd)
    EditText dd;
    @BindView(R.id.fr)
    EditText fr;
    @BindView(R.id.lxr)
    EditText lxr;
    @BindView(R.id.tel)
    EditText tel;
    @BindView(R.id.ywfw)
    EditText ywfw;
    @BindView(R.id.addgys)
    Button addgys;
    private String path = "";
    private List<TJyl> mtjyl;
    private Tjyladapter tjyladapter;
    private String index = "1", bh1, name1;
    private List<Update> mupdate;
    private boolean is = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_tjgysactivity);
        ButterKnife.bind(this);
        index = getIntent().getStringExtra("index");
        bh1 = getIntent().getStringExtra("bh");
        name1 = getIntent().getStringExtra("name");
        inview();
        // huoqu1();

    }

    private void huoqu1() {

    }

    private void delecte(String b) {
        Log.d("++++++", "delecte: ----" + bh.getText().toString());
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("delete_tjyl")
                .setJsonObject("bh", bh.getText().toString())
                .setJsonObject("ylbh", b)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(S_TJGYSActivity.this, "删除成功", Toast.LENGTH_LONG).show();
                        mtjyl.clear();
                        huoqu();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setAdapter() {
        tjyladapter = new Tjyladapter(this, mtjyl);
        listView.setAdapter(tjyladapter);
        tjyladapter.SetData(new Tjyladapter.SetData() {
            @Override
            public void setdata(int lx, String b) {
                Log.d("0000000", "setdata: ----" + b);
                if (lx == 1) {
                    delecte(b);
                } else if (lx == 2) {
                    Tjyl_dialog tjyl_dialog = new Tjyl_dialog(bh.getText().toString(), b, "2");
                    tjyl_dialog.show(getSupportFragmentManager(), "");
                    tjyl_dialog.SetData(new Tjyl_dialog.SetData() {
                        @Override
                        public void setdata() {
                            mtjyl.clear();
                            huoqu();
                        }
                    });
                }
            }
        });
    }

    private void huoqu() {
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("get_tjyl")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                                if (jsonObject1.optString("gssbh").equals(bh.getText().toString())) {
                                    mtjyl.add(new TJyl(jsonObject1.optString("path")
                                            , jsonObject1.optString("ylmc")
                                            , jsonObject1.optString("jg")
                                            , jsonObject1.optString("ylbh")));
                                }
                            }
                            setAdapter();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void inview() {
        title.setText("供应商-添加供应商");
        change1.setVisibility(View.GONE);
        mupdate = new ArrayList<>();
        mtjyl = new ArrayList<>();
        path = "/storage/emulated/0/baidu/searchbox/downloads/1581068496980.jpg";
        im.setImageBitmap(BitmapFactory.decodeFile(path));
        if (index.equals("1")) {
            addsp.setEnabled(true);
            addsp.setTextColor(Color.BLACK);
            add.setEnabled(false);
            add.setTextColor(Color.GRAY);

        }
        if (index.equals("2")) {
            addsp.setEnabled(false);
            addsp.setTextColor(Color.GRAY);
            add.setEnabled(true);
            add.setTextColor(Color.BLACK);
            bh.setText(bh1);
            mc.setText(name1);
            bh.setEnabled(false);
            mc.setEnabled(false);
            huoqu();
        }
    }


    @OnClick({R.id.change, R.id.addImage, R.id.add, R.id.addsp,R.id.addgys})
    public void onViewClicked(View view) {
        final String bh1 = bh.getText().toString();
        final String mc1 = mc.getText().toString();
        final String cs1 = cs.getText().toString();
        final String dd1 = dd.getText().toString();
        final String fr1 = fr.getText().toString();
        final String lxr1 = lxr.getText().toString();
        final String tel1 = tel.getText().toString();
        final String ywfw1 = ywfw.getText().toString();

        switch (view.getId()) {
            case R.id.addgys:

                if (bh1.equals("") || mc1.equals("") || cs1.equals("") || dd1.equals("") || fr1.equals("")
                        || lxr1.equals("") || tel1.equals("") || ywfw1.equals("") || path.equals("")) {
                    Toast.makeText(S_TJGYSActivity.this, "内容不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                S_VolleyTo volleyTo = new S_VolleyTo();
                volleyTo.setUrl("set_gyslb")
                        .setJsonObject("gysbh", bh1)
                        .setJsonObject("mc", mc1)
                        .setJsonObject("cs", cs1)
                        .setJsonObject("dd", dd1)
                        .setJsonObject("fr", fr1)
                        .setJsonObject("lxr", lxr1)
                        .setJsonObject("tel", tel1)
                        .setJsonObject("ywfw", ywfw1)
                        .setJsonObject("image", path)
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                Toast.makeText(S_TJGYSActivity.this, "添加成功", Toast.LENGTH_LONG).show();
                                bh.setEnabled(false);
                                mc.setEnabled(false);
                                cs.setEnabled(false);
                                dd.setEnabled(false);
                                fr.setEnabled(false);
                                lxr.setEnabled(false);
                                tel.setEnabled(false);
                                ywfw.setEnabled(false);
                                addgys.setEnabled(false);
                                addgys.setTextColor(Color.GRAY);

                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        }).start();

                break;
            case R.id.change:
                startActivity(new Intent(S_TJGYSActivity.this, S_GYSLBActivity.class));
                finish();
                break;
            case R.id.addImage:
                break;
            case R.id.add:

                if (bh1.equals("") || mc1.equals("") || cs1.equals("") || dd1.equals("") || fr1.equals("")
                        || lxr1.equals("") || tel1.equals("") || ywfw1.equals("") || path.equals("")) {
                    Toast.makeText(S_TJGYSActivity.this, "内容不能为空", Toast.LENGTH_LONG).show();
                    return;
                }

                S_VolleyTo volleyTo1 = new S_VolleyTo();
                volleyTo1.setUrl("update_gyslb")
                        .setJsonObject("gysbh", bh1)
                        .setJsonObject("mc", mc1)
                        .setJsonObject("cs", cs1)
                        .setJsonObject("dd", dd1)
                        .setJsonObject("fr", fr1)
                        .setJsonObject("lxr", lxr1)
                        .setJsonObject("tel", tel1)
                        .setJsonObject("ywfw", ywfw1)
                        .setJsonObject("image", path)
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                Toast.makeText(S_TJGYSActivity.this, "修改成功", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        }).start();
                break;
            case R.id.addsp:
                if (bh1.equals("") || mc1.equals("") || cs1.equals("") || dd1.equals("") || fr1.equals("")
                        || lxr1.equals("") || tel1.equals("") || ywfw1.equals("") || path.equals("")) {
                    Toast.makeText(S_TJGYSActivity.this, "内容不能为空", Toast.LENGTH_LONG).show();
                    return;
                }

                S_VolleyTo volleyTo2 = new S_VolleyTo();
                volleyTo2.setUrl("get_gyslb")
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                                    if (jsonObject1.optString("gysbh").equals(bh1)&&jsonObject1.optString("mc").equals(mc1))
                                    {
                                        Tjyl_dialog tjyl_dialog = new Tjyl_dialog(bh.getText().toString(), "", "1");
                                        tjyl_dialog.show(getSupportFragmentManager(), "");
                                        tjyl_dialog.SetData(new Tjyl_dialog.SetData() {
                                            @Override
                                            public void setdata() {
                                                mtjyl.clear();
                                                huoqu();
                                            }
                                        });
                                        return;
                                    }
                                }
                                Toast.makeText(S_TJGYSActivity.this,"请先添加供应商",Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        }).start();



                break;
        }
    }
}
