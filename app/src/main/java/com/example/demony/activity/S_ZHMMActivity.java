package com.example.demony.activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.demony.R;
import com.example.demony.net.S_VolleyTo;
import com.example.demony.net.VolleyLo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_ZHMMActivity extends AppCompatActivity {


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.title2)
    TextView title2;
    @BindView(R.id.yhm)
    EditText yhm;
    @BindView(R.id.yx)
    EditText yx;
    @BindView(R.id.zhi)
    TextView zhi;
    @BindView(R.id.qd)
    Button qd;
    @BindView(R.id.line)
    LinearLayout line;
    @BindView(R.id.line1)
    LinearLayout line1;
    private boolean is = true;
    private int index = 0, select = 31;
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            zhi.setText((--select)+"");
            if (select==0)
            {
                is=false;
                index=0;
                select=31;
                line.setBackgroundColor(Color.WHITE);
                line1.setVisibility(View.INVISIBLE);


                line.setEnabled(true);
                yhm.setEnabled(true);
                yx.setEnabled(true);
                qd.setEnabled(true);
                qd.setTextColor(Color.BLACK);
                yhm.setBackgroundResource(R.drawable.s_bk1);
                yx.setBackgroundResource(R.drawable.s_bk1);
                qd.setBackgroundResource(R.drawable.s_bk2);
            }
            return false;
        }
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_zhmmactivity);
        ButterKnife.bind(this);
        inview();
    }


    private void inview() {
        title1.setText("找回密码");
    }

    @OnClick({R.id.qd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.qd:
                final String y = yhm.getText().toString();
                final String y1 = yx.getText().toString();

                String yxpd = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
                String yxpd1 = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
                boolean matches1 = Pattern.matches(yxpd, y1);
                boolean matches2 = Pattern.matches(yxpd1, y1);


                if (y.equals("")) {
                    Toast.makeText(S_ZHMMActivity.this, "用户名不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                if (y1.equals("")) {
                    Toast.makeText(S_ZHMMActivity.this, "邮箱不能为空", Toast.LENGTH_LONG).show();
                    return;
                }


                if (!matches1) {
                    Toast.makeText(S_ZHMMActivity.this, "邮箱格式错误", Toast.LENGTH_LONG).show();
                    return;
                } else if (!matches2) {
                    Toast.makeText(S_ZHMMActivity.this, "邮箱格式错误", Toast.LENGTH_LONG).show();
                    return;
                }
                S_VolleyTo volleyTo = new S_VolleyTo();
                volleyTo.setUrl("get_login")
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                                    if (jsonObject1.optString("username").equals(y) && jsonObject1.optString("email").equals(y1)) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(S_ZHMMActivity.this);
                                        builder.setTitle("提示");
                                        builder.setMessage("找回密码为：" + jsonObject1.optString("password"));
                                        builder.setCancelable(false);
                                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                        builder.show();
                                        return;
                                    }
                                }


                                index++;
                                if (index == 3) {
                                    is = true;
                                    line1.setVisibility(View.VISIBLE);
                                    line.setEnabled(false);
                                    yhm.setEnabled(false);
                                    yx.setEnabled(false);
                                    qd.setEnabled(false);
                                    yhm.setBackgroundColor(Color.parseColor("#E9E7E7"));
                                    yx.setBackgroundColor(Color.parseColor("#E9E7E7"));
                                    qd.setBackgroundColor(Color.parseColor("#bbbbbb"));
                                    qd.setTextColor(Color.parseColor("#E9E7E7"));
                                    line.setBackgroundColor(Color.parseColor("#bbbbbb"));
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            do {
                                                try {
                                                    handler.sendEmptyMessage(0);
                                                    Thread.sleep(1000);
                                                }catch (Exception e)
                                                {
                                                    e.printStackTrace();
                                                }
                                            }while (is);
                                        }
                                    }).start();
                                }
                                Toast.makeText(S_ZHMMActivity.this, "用户名或邮箱输入错误", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        }).start();


                break;
        }
    }
}
