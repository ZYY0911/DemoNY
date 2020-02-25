package com.example.demony.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.demony.AppClient;
import com.example.demony.MainActivity;
import com.example.demony.R;
import com.example.demony.net.S_VolleyTo;
import com.example.demony.net.VolleyLo;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_YHDLActivity extends AppCompatActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.title2)
    TextView title2;
    @BindView(R.id.yhm)
    EditText yhm;
    @BindView(R.id.mm)
    EditText mm;
    @BindView(R.id.jzmm)
    CheckBox jzmm;
    @BindView(R.id.zddl)
    CheckBox zddl;
    @BindView(R.id.qd)
    Button qd;
    @BindView(R.id.qx)
    Button qx;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_yhdlactivity);
        ButterKnife.bind(this);
        inview();

    }

    private void inview() {
        title.setText("用户注册");
        title1.setText("用户登录");
        title2.setText("找回密码");
        if (AppClient.getXz().equals("记住密码"))
        {
            yhm.setText(AppClient.getUserName());
            mm.setText(AppClient.getPassWord());
        }
        if (AppClient.getXz().equals("自动登录"))
        {
            startActivity(new Intent(S_YHDLActivity.this, MainActivity.class));
        }

    }

    private void huoqu(final String y, final String m) {
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("get_login")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            if (y.equals(jsonObject1.optString("username"))&&jsonObject1.optString("password").equals(m))
                            {
                                Toast.makeText(S_YHDLActivity.this,"登录成功",Toast.LENGTH_LONG).show();
                                AppClient.setPassWord(m);
                                AppClient.setUserName(y);
                                AppClient.setName(y);
                                startActivity(new Intent(S_YHDLActivity.this, S_CKZPActivity.class));
                                finish();
                                return;
                            }
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    @OnClick({R.id.title, R.id.title2, R.id.jzmm, R.id.zddl, R.id.qd, R.id.qx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title:
                startActivity(new Intent(S_YHDLActivity.this,S_YHZCActivity.class));
                break;
            case R.id.title2:
                startActivity(new Intent(S_YHDLActivity.this,S_ZHMMActivity.class));
                break;
            case R.id.jzmm:
                AppClient.setXz("记住密码");
                break;
            case R.id.zddl:
                AppClient.setXz("自动登录");

                break;
            case R.id.qd:
                String y = yhm.getText().toString();
                String m = mm.getText().toString();
                if (y.equals(""))
                {
                    Toast.makeText(S_YHDLActivity.this,"用户名不能为空",Toast.LENGTH_LONG).show();
                    return;
                }
                if (m.equals(""))
                {
                    Toast.makeText(S_YHDLActivity.this,"密码不能为空",Toast.LENGTH_LONG).show();
                    return;
                }
                huoqu(y,m);
                break;
            case R.id.qx:
                finish();
                break;
        }
    }
}
