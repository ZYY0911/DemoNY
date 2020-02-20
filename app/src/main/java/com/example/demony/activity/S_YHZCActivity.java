package com.example.demony.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.demony.AppClient;
import com.example.demony.MainActivity;
import com.example.demony.R;
import com.example.demony.bean.User;
import com.example.demony.net.S_VolleyTo;
import com.example.demony.net.VolleyLo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_YHZCActivity extends AppCompatActivity {
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
    @BindView(R.id.qrmm)
    EditText qrmm;
    @BindView(R.id.yx)
    EditText yx;
    @BindView(R.id.qd)
    Button qd;
    @BindView(R.id.qx)
    Button qx;
    private List<User> muser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_yhzcactivity);
        ButterKnife.bind(this);
        inview();
        huoqu();
    }
    private void huoqu() {
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("get_login")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            muser.add(new User(jsonObject1.optString("username")));
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void inview() {
        title1.setText("用户注册");
        muser = new ArrayList<>();
    }

    @OnClick({R.id.qd, R.id.qx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.qx:
                finish();
                break;
            case R.id.qd:
                String y = yhm.getText().toString();
                String m = mm.getText().toString();
                String m1 = qrmm.getText().toString();
                String y1 = yx.getText().toString();

                String pd = "(.*[A-Za-z0-9].*){8,16}";
                String yxpd = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
                String yxpd1="^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
                boolean matches = Pattern.matches(pd,m);
                boolean matches1 = Pattern.matches(yxpd,y1);
                boolean matches2 = Pattern.matches(yxpd1,y1);


                if (y.equals(""))
                {
                    Toast.makeText(S_YHZCActivity.this,"用户名不能为空",Toast.LENGTH_LONG).show();
                    return;
                }

                if (!matches)
                {
                    Toast.makeText(S_YHZCActivity.this,"密码长度在8-16之间，由数字、大写字母和小写字母构成",Toast.LENGTH_LONG).show();
                    return;
                }
                if (!m.equals(m1))
                {
                    Toast.makeText(S_YHZCActivity.this,"密码不一致",Toast.LENGTH_LONG).show();
                    return;
                }
                if (!matches1)
                {
                    Toast.makeText(S_YHZCActivity.this,"邮箱格式错误",Toast.LENGTH_LONG).show();
                    return;
                }else  if (!matches2)
                {
                    Toast.makeText(S_YHZCActivity.this,"邮箱格式错误",Toast.LENGTH_LONG).show();
                    return;
                }



                for (int i=0;i<muser.size();i++)
                {
                    User user = muser.get(i);
                    if (user.getUsername().equals(y))
                    {
                        Toast.makeText(S_YHZCActivity.this,"用户名已存在",Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                S_VolleyTo volleyTo = new S_VolleyTo();
                volleyTo.setUrl("set_login")
                        .setJsonObject("username",y)
                        .setJsonObject("password",m)
                        .setJsonObject("email",y1)
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                try {
                                    if (jsonObject.optString("RESULT").equals("S"))
                                    {
                                        Toast.makeText(S_YHZCActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                                    }
                                }catch (Exception e)
                                {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        }).start();
                break;
        }
    }
}
