package com.example.demony.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demony.R;
import com.example.demony.bean.Jllb;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Jllbadapter extends BaseAdapter {
    private List<Jllb> mjllb;
    private Context context;

    public Jllbadapter(Context context, List<Jllb> mjllb) {
        this.context = context;
        this.mjllb = mjllb;
    }

    @Override
    public int getCount() {
        return mjllb.size();
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
        Jllb jllb = mjllb.get(position);
        View view = View.inflate(context, R.layout.jllb_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mc.setText("简历名称："+jllb.getMc());
        viewHolder.bz.setText("备注："+jllb.getBz());
        viewHolder.time.setText("上传时间："+jllb.getTime());
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.mc)
        TextView mc;
        @BindView(R.id.bz)
        TextView bz;
        @BindView(R.id.time)
        TextView time;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
