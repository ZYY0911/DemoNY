package com.example.demony.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demony.R;
import com.example.demony.bean.Shls;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Shlsadapter extends BaseAdapter {
    private List<Shls> mshls;
    private Context context;

    public Shlsadapter(Context context, List<Shls> mshls) {
        this.context = context;
        this.mshls = mshls;
    }

    @Override
    public int getCount() {
        return mshls.size();
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
        Shls shls = mshls.get(position);
        View view = View.inflate(context, R.layout.shls_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.bh.setText("信息编号："+shls.getBh());
        viewHolder.gw.setText("岗位："+shls.getGe());
        viewHolder.name.setText("公司名称："+shls.getName());
        viewHolder.shr.setText("审核人："+shls.getShr());
        viewHolder.shsj.setText("审核时间："+shls.getShsj());
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.bh)
        TextView bh;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.gw)
        TextView gw;
        @BindView(R.id.shr)
        TextView shr;
        @BindView(R.id.shsj)
        TextView shsj;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
