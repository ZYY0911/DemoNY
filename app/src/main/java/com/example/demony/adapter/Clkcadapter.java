package com.example.demony.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.demony.R;
import com.example.demony.bean.Clkc;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Clkcadapter extends BaseAdapter {
    private List<Clkc> mclkc;
    private Context context;

    public interface SetData {
        void setdata(int position, String name, String clxh, String jb, String cs, String lx,
                     String hbbz, String sssj, String jg, String sl, String sms, String cspz, String video, String image);
    }

    public SetData data;

    public void SetData(SetData data) {
        this.data = data;
    }

    public Clkcadapter(Context context, List<Clkc> mclkc) {
        this.context = context;
        this.mclkc=mclkc;
    }

    @Override
    public int getCount() {
        return mclkc.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Clkc clkc = mclkc.get(position);
        View view = View.inflate(context, R.layout.clkc_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.clxh.setText("车辆型号：" + clkc.getClxh());
        viewHolder.jb.setText("级别：" + clkc.getJb());
        viewHolder.cs.setText("厂商：" + clkc.getCs());
        viewHolder.lx.setText("能源类型：" + clkc.getLx());
        viewHolder.bz.setText("环保标准：" + clkc.getHbbz());
        viewHolder.sj.setText("上市时间：" + clkc.getSssj());
        viewHolder.jg.setText("价格：" + clkc.getJg());
        viewHolder.sl.setText("数量：" + clkc.getSl());
        viewHolder.beijing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setdata(position,clkc.getName(),clkc.getClxh(),clkc.getJb(),clkc.getCs(),clkc.getLx()
                        ,clkc.getHbbz(),clkc.getSssj(),clkc.getJg(),clkc.getSl(),clkc.getCms(),clkc.getCppz(),
                        clkc.getVideo(),clkc.getImage());
            }
        });
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.clxh)
        TextView clxh;
        @BindView(R.id.jb)
        TextView jb;
        @BindView(R.id.cs)
        TextView cs;
        @BindView(R.id.lx)
        TextView lx;
        @BindView(R.id.bz)
        TextView bz;
        @BindView(R.id.sj)
        TextView sj;
        @BindView(R.id.jg)
        TextView jg;
        @BindView(R.id.sl)
        TextView sl;
        @BindView(R.id.beijing)
        LinearLayout beijing;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
