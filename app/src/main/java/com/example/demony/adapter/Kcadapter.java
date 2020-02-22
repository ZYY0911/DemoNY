package com.example.demony.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.demony.R;
import com.example.demony.bean.Kc;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Kcadapter extends BaseAdapter {
    private List<Kc> mkc;
    private Context context;

    public interface SetData {
        void setdata(int position, String path, String name, String xh, String cshang, String cs, String kcl, String wz);
    }

    public SetData data;

    public void SetData(SetData data) {
        this.data = data;
    }

    public Kcadapter(Context context, List<Kc> mkc) {
        this.context = context;
        this.mkc = mkc;
    }

    @Override
    public int getCount() {
        return mkc.size();
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
        final Kc kc = mkc.get(position);
        View view = View.inflate(context, R.layout.kc_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mc.setText("原料名称：" + kc.getName());
        viewHolder.kcl.setText("库存量：" + kc.getKc());
        viewHolder.wz.setText("位置：" + kc.getWz());
        viewHolder.xh.setText("型号：" + kc.getXh());
        viewHolder.beijing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setdata(position,kc.getPath(),kc.getName(),kc.getXh(),kc.getCshang(),kc.getCs(),kc.getKc(),kc.getWz());
            }
        });
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.mc)
        TextView mc;
        @BindView(R.id.xh)
        TextView xh;
        @BindView(R.id.kcl)
        TextView kcl;
        @BindView(R.id.wz)
        TextView wz;
        @BindView(R.id.beijing)
        LinearLayout beijing;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
