package com.example.demony.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.demony.R;
import com.example.demony.bean.Z_Jbxx;
import com.example.demony.bean.Z_Jxx;
import com.example.demony.bean.Z_Rk;
import com.example.demony.bean.Z_Sp;
import com.example.demony.net.VolleyLo;
import com.example.demony.net.Z_VolleyTo;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;
import org.litepal.LitePal;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-02-22 ：）
 */
public class Z_GYSTJActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.bar_chart_left_top)
    BarChart barChartLeftTop;
    @BindView(R.id.pie_chart_right_top)
    PieChart pieChartRightTop;
    @BindView(R.id.pie_chart_left_bottom)
    PieChart pieChartLeftBottom;
    @BindView(R.id.bar_chart_right_bottom)
    BarChart barChartRightBottom;
    private List<Z_Jxx> jxxes;
    private List<Z_Sp> sps;
    private List<Z_Rk> z_rks;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gystj_layout);
        ButterKnife.bind(this);
        setVolley_LeftTop();
    }



    private void initRightBottom() {
        List<Integer> colors = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < sps.size(); i++) {
            Z_Sp gscp = sps.get(i);
            int money = (map.get(gscp.getGssbh()) == null) ? 0 : map.get(gscp.getGssbh());
            for (int j = 0; j < z_rks.size(); j++) {
                money += (Integer.parseInt(z_rks.get(j).getSl()) * Integer.parseInt(z_rks.get(j).getDj()));
            }
            map.put(getGysBh(gscp.getGssbh()), money);
            colors.add(getColor());
            strings.add(getGysBh(gscp.getGssbh()));
        }
        List<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
            barEntries.add(new BarEntry(i, map.get(strings.get(i))));
        }
        BarDataSet dataSet = new BarDataSet(barEntries, "");
        dataSet.setColors(colors);
        dataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                DecimalFormat format = new DecimalFormat("0.0");
                return format.format(value) + "元";
            }
        });
        BarData data = new BarData(dataSet);
        data.setBarWidth(0.3f);
        barChartRightBottom.setData(data);
        XAxis xAxis = barChartRightBottom.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(strings));
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(false);
        YAxis yAxis = barChartRightBottom.getAxisRight();
        yAxis.setStartAtZero(true);
        yAxis.setEnabled(false);
        YAxis yAxis1 = barChartRightBottom.getAxisLeft();
        yAxis1.setStartAtZero(true);
        barChartRightBottom.getLegend().setEnabled(false);
        barChartRightBottom.getDescription().setText("所有供应商交易总金额统计图");
        barChartRightBottom.getDescription().setTextSize(15);
        barChartRightBottom.setDoubleTapToZoomEnabled(false);
        barChartRightBottom.invalidate();
    }

    private void setVolley_LeftBottom() {
        Z_VolleyTo volleyTo = new Z_VolleyTo();
        volleyTo.setUrl("get_supplier_transaction")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        z_rks = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<Z_Rk>>() {
                                }.getType());
                        initLeftBottom();
                        initRightBottom();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private String getGysBh(String gys) {
        switch (gys) {
            case "10001":
                return "公司A";
            case "10002":
                return "公司B";
            case "10003":
                return "公司C";
            case "10004":
                return "公司D";
            case "10005":
                return "公司E";
            case "10006":
                return "公司F";
            default :
                return "";
        }
    }

    private void initLeftBottom() {
        List<Integer> colors = new ArrayList<>();
        int a = 0, b = 0;
        for (int i = sps.size() - 1; i >= 0; i--) {
            Z_Sp gscp = sps.get(i);
            List<Z_Rk> rks = new ArrayList<>();
            for (int j = 0; j < z_rks.size(); j++) {
                if (gscp.getGssbh().equals(getGysBh(z_rks.get(j).getCsm()))) {
                    rks.add(z_rks.get(j));
                }
            }
            if (rks.size() == 0) {
                a++;
            } else {
                b++;
            }
            colors.add(getColor());
        }
        List<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(a, "没有业务供应商"));
        pieEntries.add(new PieEntry(b, "有业务供应商"));
        PieDataSet dataSet = new PieDataSet(pieEntries, "");
        dataSet.setValueFormatter(new PercentFormatter());
        dataSet.setColors(colors);
        dataSet.setSliceSpace(3f);
        PieData data = new PieData(dataSet);
        pieChartLeftBottom.setData(data);
        pieChartLeftBottom.setDrawHoleEnabled(false);
        pieChartLeftBottom.setRotationEnabled(false);
        pieChartLeftBottom.getDescription().setText("有无业务供应商统计图");
        pieChartLeftBottom.getDescription().setTextSize(15);
        pieChartLeftBottom.setTouchEnabled(false);
        pieChartLeftBottom.setUsePercentValues(true);
        pieChartLeftBottom.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        pieChartLeftBottom.invalidate();
    }

    private void setVolley_RightTop() {
        Z_VolleyTo volleyTo = new Z_VolleyTo();
        volleyTo.setUrl("get_tjyl")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        sps = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<Z_Sp>>() {
                                }.getType());
                        initRightTop(sps);
                        setVolley_LeftBottom();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    private void initRightTop(List<Z_Sp> sps) {
        Map<String, Integer> map = new HashMap<>();
        List<String> strings = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();
        for (int i = 0; i < sps.size(); i++) {
            List<Z_Jxx> list = new ArrayList<>();
            for (int j = 0; j < jxxes.size(); j++) {
                if (sps.get(i).getGssbh().equals(jxxes.get(j).getGysbh())) {
                    list.add(jxxes.get(j));
                }
            }
            if (jxxes.size() != 0) {
                String name = jxxes.get(0).getMc();
                Integer count = map.get(name);
                map.put(name, (count == null) ? 1 : count + 1);
                strings.add(name);
                for (int k = 0; k < strings.size(); k++) {
                    for (int j = strings.size() - 1; j > k; j--) {
                        if (strings.get(k).equals(strings.get(j))) {
                            strings.remove(j);
                        }
                    }
                }
                colors.add(getColor());
            }
        }
        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++) {
            pieEntries.add(new PieEntry((float) map.get(strings.get(i)) / (float) sps.size(), strings.get(i)));
        }
        PieDataSet dataSet = new PieDataSet(pieEntries, "");
        dataSet.setValueFormatter(new PercentFormatter());
        dataSet.setColors(colors);
        dataSet.setSliceSpace(3f);
        PieData data = new PieData(dataSet);
        pieChartRightTop.setData(data);
        pieChartRightTop.setDrawHoleEnabled(false);
        pieChartRightTop.setRotationEnabled(false);
        pieChartRightTop.getDescription().setText("不同产品供应商数量统计图");
        pieChartRightTop.getDescription().setTextSize(15);
        pieChartRightTop.setTouchEnabled(false);
        pieChartRightTop.setUsePercentValues(true);
        pieChartRightTop.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        pieChartRightTop.invalidate();
    }

    private void setVolley_LeftTop() {
        Z_VolleyTo volleyTo = new Z_VolleyTo();
        volleyTo.setUrl("get_gyslb")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        jxxes = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<Z_Jxx>>() {
                                }.getType());
                        initLeftTop(jxxes);
                        setVolley_RightTop();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void initLeftTop(List<Z_Jxx> jxxes) {
        Map<String, Integer> map = new HashMap<>();
        List<String> strings = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();
        for (int i = 0; i < jxxes.size(); i++) {
            String city = jxxes.get(i).getCs();
            Integer count = map.get(city);
            Log.i("aaa", "initLeftTop: bb" + city);
            map.put(city, (count == null) ? 1 : count + 1);
            strings.add(city);
            for (int k = 0; k < strings.size(); k++) {
                for (int j = strings.size() - 1; j > k; j--) {
                    if (strings.get(k).equals(strings.get(j))) {
                        strings.remove(j);
                    }
                }
            }
            colors.add(getColor());
        }
        List<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
            barEntries.add(new BarEntry(i, (float) map.get(strings.get(i)) / (float) jxxes.size()));
        }
        BarDataSet dataSet = new BarDataSet(barEntries, "");
        dataSet.setColors(colors);
        dataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                DecimalFormat format = new DecimalFormat("0.0");
                return format.format(value * 100) + "%";
            }
        });
        BarData data = new BarData(dataSet);
        data.setBarWidth(0.2f);
        barChartLeftTop.setData(data);
        XAxis xAxis = barChartLeftTop.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(strings));
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(false);
        YAxis yAxis = barChartLeftTop.getAxisRight();
        yAxis.setStartAtZero(true);
        yAxis.setEnabled(false);
        YAxis yAxis1 = barChartLeftTop.getAxisLeft();
        yAxis1.setStartAtZero(true);
        barChartLeftTop.getLegend().setEnabled(false);
        barChartLeftTop.getDescription().setText("所在城市百分比统计图");
        barChartLeftTop.getDescription().setTextSize(15);
        barChartLeftTop.setDoubleTapToZoomEnabled(false);
        barChartLeftTop.invalidate();
    }

    private int getColor() {
        Random random = new Random();
        int ranColor = 0xff000000 | random.nextInt(0x00ffffff);
        return ranColor;
    }

    @OnClick({R.id.change, R.id.bar_chart_left_top, R.id.pie_chart_right_top, R.id.pie_chart_left_bottom, R.id.bar_chart_right_bottom})
    public void onViewClicked(View view) {
        Intent intent = new Intent(this,Z_TJXQActivity.class);
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.bar_chart_left_top:
                intent.putExtra("lx",1);
                startActivity(intent);
                break;
            case R.id.pie_chart_right_top:
                intent.putExtra("lx",2);
                startActivity(intent);
                break;
            case R.id.pie_chart_left_bottom:
                intent.putExtra("lx",3);
                startActivity(intent);
                break;
            case R.id.bar_chart_right_bottom:
                intent.putExtra("lx",4);
                startActivity(intent);
                break;
        }
    }
}
