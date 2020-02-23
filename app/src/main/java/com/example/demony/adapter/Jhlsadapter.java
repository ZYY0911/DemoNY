package com.example.demony.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demony.R;
import com.example.demony.bean.Jhls;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Jhlsadapter extends BaseAdapter {
    private List<Jhls> mjhls;
    private Context context;

    public Jhlsadapter(Context context, List<Jhls> mjhls) {
        this.context = context;
        this.mjhls = mjhls;
    }

    @Override
    public int getCount() {
        return mjhls.size();
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
        Jhls jhls = mjhls.get(position);
        View view = View.inflate(context, R.layout.jhls_item, null);
        ViewHolder viewHolder  =new ViewHolder(view);
        viewHolder.bz.setText("备注："+jhls.getBz());
        viewHolder.cgr.setText("采购人："+jhls.getCgr());
        viewHolder.dj.setText("单价："+jhls.getDl());
        viewHolder.gys.setText("供应商："+jhls.getGys());
        viewHolder.sl.setText("数量："+jhls.getSl());
        viewHolder.ze.setText("总额："+jhls.getZe());
        viewHolder.jhsj.setText("进货时间："+jhls.getJhsj());
        viewHolder.yl.setText("余量："+jhls.getYl());
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.jhsj)
        TextView jhsj;
        @BindView(R.id.gys)
        TextView gys;
        @BindView(R.id.sl)
        TextView sl;
        @BindView(R.id.dj)
        TextView dj;
        @BindView(R.id.ze)
        TextView ze;
        @BindView(R.id.cgr)
        TextView cgr;
        @BindView(R.id.bz)
        TextView bz;
        @BindView(R.id.yl)
        TextView yl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
