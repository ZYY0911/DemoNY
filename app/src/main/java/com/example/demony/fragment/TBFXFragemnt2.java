package com.example.demony.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demony.R;
import com.example.demony.bean.Z_Jbxx;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by 张瀛煜 on 2020-02-21 ：）
 */
@SuppressLint("ValidFragment")
public class TBFXFragemnt2 extends Fragment {
    public TBFXFragemnt2(List<Z_Jbxx> jbxxes){
        this.jbxxes = jbxxes;
    }
    private BarChart barChart;
    private List<BarEntry> barEntries;
    private List<Z_Jbxx> jbxxes;
    private int a, b, c, d;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tbfx_framgent2, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        a=0;
        c=0;
        d=0;
        b=0;
        if (jbxxes==null)jbxxes = new ArrayList<>();
        else jbxxes.clear();
        for (int i = 0; i < jbxxes.size(); i++) {
            switch (jbxxes.get(i).getZy()) {
                case "计算机应用":
                    a++;
                    break;
                case "软件开发":
                    b++;
                    break;
                case "数字媒体":
                    c++;
                    break;
                case "其他":
                    d++;
                    break;

            }
        }
        if (barEntries==null)barEntries = new ArrayList<>();
        else barEntries.clear();
        barEntries.add(new BarEntry(1, a));
        barEntries.add(new BarEntry(2, b));
        barEntries.add(new BarEntry(3, c));
        barEntries.add(new BarEntry(4, d));
        List<String> xValue = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#36a9ce"));
        colors.add(Color.parseColor("#673AB7"));
        colors.add(Color.parseColor("#FF5722"));
        colors.add(Color.parseColor("#009688"));
        xValue.add("");
        xValue.add("计算机应用");
        xValue.add("软件开发");
        xValue.add("数字媒体");
        xValue.add("其他");
        xValue.add("");
        BarDataSet dataSet = new BarDataSet(barEntries, "");
        dataSet.setColors(colors);
        BarData data = new BarData(dataSet);
        data.setBarWidth(0.4f);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(5);
        xAxis.setDrawAxisLine(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xValue));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        YAxis yAxis = barChart.getAxisRight();
        yAxis.setStartAtZero(true);
        yAxis.setEnabled(false);
        YAxis yAxis1 = barChart.getAxisLeft();
        yAxis1.setStartAtZero(true);
        barChart.setData(data);
        barChart.getLegend().setEnabled(false);
        barChart.getDescription().setEnabled(false);
        barChart.invalidate();

    }

    private void initView() {
        barChart = getView().findViewById(R.id.bar_chart);
    }
}

