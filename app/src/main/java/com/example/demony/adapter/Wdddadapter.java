package com.example.demony.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demony.R;
import com.example.demony.bean.Wddd;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Wdddadapter extends BaseAdapter {
    private List<Wddd> mwddd;
    private Context context;

    public Wdddadapter(Context context, List<Wddd> mwddd) {
        this.context = context;
        this.mwddd = mwddd;
    }

    @Override
    public int getCount() {
        return mwddd.size();
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
        Wddd wddd = mwddd.get(position);
        View view = View.inflate(context, R.layout.wddd_item, null);
        ViewHolder viewHolder  =new ViewHolder(view);
        viewHolder.name.setText("车辆名称："+wddd.getName());
        viewHolder.jg.setText("价格："+wddd.getJg());
        viewHolder.time.setText("时间："+wddd.getTime());
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.jg)
        TextView jg;
        @BindView(R.id.time)
        TextView time;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
