package com.example.demony.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.demony.R;
import com.example.demony.bean.Jy;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Jyadapter extends BaseAdapter {
    private List<Jy> mjy;
    private Context context;

    public interface SetData {
        void setdata(int position);
    }

    public SetData data;

    public void SetData(SetData data) {
        this.data = data;
    }

    public Jyadapter(Context context, List<Jy> mjy) {
        this.context = context;
        this.mjy = mjy;
    }

    @Override
    public int getCount() {
        return mjy.size();
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
        Jy jy = mjy.get(position);
        View view = View.inflate(context, R.layout.jy_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.time.setText("时间：" + jy.getTime());
        viewHolder.dj.setText("单价：" + jy.getDj());
        viewHolder.sl.setText("数量：" + jy.getSl());
        viewHolder.zje.setText("总金额：" + jy.getZje());
        viewHolder.zh.setText("对方账号：" + jy.getZh());
        viewHolder.cgy.setText("采购员：" + jy.getCgy());
        viewHolder.lxr.setText("对方联系人：" + jy.getLxr());
        viewHolder.beijing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setdata(position);
            }
        });
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.dj)
        TextView dj;
        @BindView(R.id.sl)
        TextView sl;
        @BindView(R.id.zje)
        TextView zje;
        @BindView(R.id.zh)
        TextView zh;
        @BindView(R.id.cgy)
        TextView cgy;
        @BindView(R.id.lxr)
        TextView lxr;
        @BindView(R.id.beijing)
        LinearLayout beijing;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
