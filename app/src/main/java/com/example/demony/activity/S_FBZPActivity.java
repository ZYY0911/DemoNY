package com.example.demony.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.demony.R;
import com.example.demony.net.S_VolleyTo;
import com.example.demony.net.VolleyLo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_FBZPActivity extends AppCompatActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.title2)
    TextView title2;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.szd)
    EditText szd;
    @BindView(R.id.gsdz)
    EditText gsdz;
    @BindView(R.id.tel)
    EditText tel;
    @BindView(R.id.yx)
    EditText yx;
    @BindView(R.id.gw)
    EditText gw;
    @BindView(R.id.xz)
    EditText xz;
    @BindView(R.id.hy)
    EditText hy;
    @BindView(R.id.zyyq)
    EditText zyyq;
    @BindView(R.id.gzjl)
    EditText gzjl;
    @BindView(R.id.gwyq)
    EditText gwyq;
    @BindView(R.id.fb)
    Button fb;
    @BindView(R.id.name)
    EditText name;
    private String index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_fbzpxxactivity);
        ButterKnife.bind(this);
        inview();

    }

    private void bianhao() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(System.currentTimeMillis());
        final String t = format.format(date);
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("get_factory_fbzp")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            String b = jsonObject1.optString("bh");
                            Log.d("0000", "onResponse: ---"+b);
                            if (b.substring(0, 8).equals(t)) {
                                index = t + (Integer.parseInt(b.substring(8)) + 1);
                            } else {
                                index = t + 1;
                            }
                            Log.d("0000", "onResponse: ---"+index);
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void huoqu() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String t = format.format(date);
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("set_factory_fbzp")
                .setJsonObject("bh", index)
                .setJsonObject("zt", "1")
                .setJsonObject("name", name.getText().toString())
                .setJsonObject("xl", spinner.getSelectedItem().toString())
                .setJsonObject("hy", hy.getText().toString())
                .setJsonObject("szd", szd.getText().toString())
                .setJsonObject("gzdz", gsdz.getText().toString())
                .setJsonObject("tel", tel.getText().toString())
                .setJsonObject("email", yx.getText().toString())
                .setJsonObject("gw", gw.getText().toString())
                .setJsonObject("xz", xz.getText().toString())
                .setJsonObject("zyyq", zyyq.getText().toString())
                .setJsonObject("gzjlyq", gzjl.getText().toString())
                .setJsonObject("shr", "无")
                .setJsonObject("shsj","无")
                .setJsonObject("gwyq", gwyq.getText().toString())
                .setJsonObject("time", t)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {

                            if (jsonObject.getString("RESULT").equals("S")) {
                                Toast.makeText(S_FBZPActivity.this, "发布成功", Toast.LENGTH_LONG).show();
                            }
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
        title1.setText("发布招聘信息");
        title.setText("返回");
    }


    @OnClick({R.id.title, R.id.fb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title:
                finish();
                break;
            case R.id.fb:
                bianhao();
                huoqu();
                break;
        }
    }
}
