package com.example.demony.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.demony.AppClient;
import com.example.demony.R;
import com.example.demony.bean.Gwc;
import com.example.demony.net.S_VolleyTo;
import com.example.demony.net.VolleyLo;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_ZFJMActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.bt)
    TextView bt;
    @BindView(R.id.im)
    ImageView im;
    private String name="",xh="",jg="",sl="",jg1="";
    public String url,url1;
    private AppClient mApp;
    private List<Gwc> mgwc;
    private boolean is=true;
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Random random  =new Random();
            url1=url;
            url1+=url1+random.nextInt(10);
            Creat(url1);
            return false;
        }
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_zfjmactivity);
        ButterKnife.bind(this);
        name = getIntent().getStringExtra("name");
        xh =getIntent().getStringExtra("xh");
        sl =getIntent().getStringExtra("sl");
        jg=getIntent().getStringExtra("jg");
        jg1=getIntent().getStringExtra("jg1");
        url="车辆名称："+name+"  付款金额："+jg+"元";
        inview();
        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    try {
                        handler.sendEmptyMessage(0);
                        Thread.sleep(5000);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }while (is);
            }
        }).start();
        setdianji();


    }

    private void inview() {
        title.setText("支付界面");
        mApp = (AppClient) getApplication();
        mgwc = mApp.getMgwc();
    }

    private void addData(String name,String jine,String time) {
        S_VolleyTo volleyTo  =new S_VolleyTo();
        volleyTo.setUrl("set_order")
                .setJsonObject("name",name)
                .setJsonObject("jine",jine)
                .setJsonObject("time",time)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void update(final String n, String x, String s) {
        S_VolleyTo volleyTo  =new S_VolleyTo();
        volleyTo.setUrl("update_vehicle")
                .setJsonObject("name",n)
                .setJsonObject("clxh",x)
                .setJsonObject("sl",s)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(S_ZFJMActivity.this,"支付成功",Toast.LENGTH_LONG).show();
                        for (int i=mgwc.size();i>0;i--)
                        {
                            Gwc gwc = mgwc.get(i-1);
                            if (gwc.getName().equals(n))
                            {
                                mgwc.remove(i-1);
                            }
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setdianji() {
        im.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(System.currentTimeMillis());
                bt.setText(url);
                String[] n = name.split(",");
                String[] x = xh.split(",");
                String[] s=sl.split(",");
                String[] j = jg1.split(",");
                addData(name,jg,format.format(date));
                for (int i=0;i<n.length;i++)
                {
                    update(n[i],x[i],s[i]);
                    addData(n[i],j[i],format.format(date));
                }
                return true;
            }
        });
    }

    private void Creat(String url) {
        Hashtable<EncodeHintType,String> hashtable = new Hashtable<>();
        hashtable.put(EncodeHintType.CHARACTER_SET,"UTF-8");
        try {
            BitMatrix bitMatrix  =new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE,300,300,hashtable);
            int[] arr=new int[300*300];
            for (int i=0;i<300;i++)
            {
                for (int j=0;j<300;j++)
                {
                    if (bitMatrix.get(i,j))
                    {
                        arr[j*300+i]=0xff000000;
                    }else {
                        arr[j*300+i]=0xffffffff;
                    }
                }
            }
            Bitmap bitmap  =Bitmap.createBitmap(300,300,Bitmap.Config.ARGB_8888);
            bitmap.setPixels(arr,0,300,0,0,300,300);
            im.setImageBitmap(bitmap);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        startActivity(new Intent(S_ZFJMActivity.this,S_GWCActivity.class));
        finish();
    }
}
