package com.example.demony.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demony.R;
import com.example.demony.bean.Xslb;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Xscpadapter extends BaseAdapter {
    private List<Xslb> mxslb;
    private Context context;

    public Xscpadapter(Context context, List<Xslb> mxslb) {
        this.context = context;
        this.mxslb = mxslb;
    }

    @Override
    public int getCount() {
        return mxslb.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Xslb xslb = mxslb.get(position);
        View view = View.inflate(context, R.layout.xscp_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.cjh.setText("车间号："+xslb.getCjh());
        viewHolder.scxh.setText("生产线号："+xslb.getScxh());
        viewHolder.clmc.setText("车辆名称："+xslb.getName());
        viewHolder.clxh.setText("车辆型号："+xslb.getXh());
        viewHolder.sl.setText("数量："+xslb.getSl());
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.cjh)
        TextView cjh;
        @BindView(R.id.scxh)
        TextView scxh;
        @BindView(R.id.clmc)
        TextView clmc;
        @BindView(R.id.clxh)
        TextView clxh;
        @BindView(R.id.sl)
        TextView sl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
