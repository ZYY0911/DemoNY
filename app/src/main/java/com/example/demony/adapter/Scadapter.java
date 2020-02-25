package com.example.demony.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demony.R;
import com.example.demony.bean.Sc;
import com.example.demony.bean.Zp1;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Scadapter extends BaseAdapter {
    private List<Sc> msc;
    private Context context;

    public Scadapter(Context context, List<Sc> msc) {
        this.context = context;
        this.msc = msc;
    }

    @Override
    public int getCount() {
        return msc.size();
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
        Sc  zp = msc.get(position);
        View view = View.inflate(context, R.layout.sc_item, null);
        ViewHolder viewHolder  =new ViewHolder(view);
        viewHolder.gsmc.setText("公司名称："+zp.getName());
        viewHolder.hylx.setText("行业类型："+zp.getHy());
        viewHolder.gwmc.setText("岗位名称："+zp.getGw());
        viewHolder.zyyq.setText("专业要求："+zp.getZyyq());
        viewHolder.cs.setText("所在城市："+zp.getSzd());

        viewHolder.xl.setText("学历要求："+zp.getXl());
        viewHolder.xz.setText("薪资范围："+zp.getXz());
        viewHolder.email.setText("联系邮箱："+zp.getEmail());
        viewHolder.fabsj.setText("发布时间："+zp.getFbsj());
        viewHolder.shr.setText("收藏时间："+zp.getYpsj());
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.gsmc)
        TextView gsmc;
        @BindView(R.id.hylx)
        TextView hylx;
        @BindView(R.id.gwmc)
        TextView gwmc;
        @BindView(R.id.zyyq)
        TextView zyyq;
        @BindView(R.id.cs)
        TextView cs;
        @BindView(R.id.xl)
        TextView xl;
        @BindView(R.id.xz)
        TextView xz;
        @BindView(R.id.email)
        TextView email;
        @BindView(R.id.fabsj)
        TextView fabsj;
        @BindView(R.id.shr)
        TextView shr;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
