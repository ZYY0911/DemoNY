package com.example.demony.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.demony.R;
import com.example.demony.bean.Chjl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Qbjchladapter extends BaseAdapter {
    private List<Chjl> nchjl;
    private Context context;

    public interface SetData {
        void setdata(String name, String xh,String shuliang
                ,  String time, String chr, String jsr, String qx, String path);
    }

    public SetData data;

    public void SetData(SetData data) {
        this.data = data;
    }

    public Qbjchladapter(Context context, List<Chjl> nchjl) {
        this.context = context;
        this.nchjl = nchjl;
    }

    @Override
    public int getCount() {
        return nchjl.size();
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
        final Chjl rkjl = nchjl.get(position);
        View view = View.inflate(context, R.layout.qbjl_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.ylmc.setText("原料名称：" + rkjl.getName());
        viewHolder.xh.setText("原料型号：" + rkjl.getXh());
        viewHolder.sj.setText("出库时间：" + rkjl.getTime());
        viewHolder.sj.setTextSize(18);
        viewHolder.sl.setText("出库数量：" + rkjl.getShuliang());
        viewHolder.gys.setText("出库人：" + rkjl.getChr());
        viewHolder.rkr.setText("接收人：" + rkjl.getJsr());
        viewHolder.beijing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setdata(rkjl.getName(),rkjl.getXh(),rkjl.getShuliang(),rkjl.getTime()
                        ,rkjl.getChr(),rkjl.getJsr(),rkjl.getQx(),rkjl.getPath());
            }
        });
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.ylmc)
        TextView ylmc;
        @BindView(R.id.xh)
        TextView xh;
        @BindView(R.id.sj)
        TextView sj;
        @BindView(R.id.sl)
        TextView sl;
        @BindView(R.id.gys)
        TextView gys;
        @BindView(R.id.rkr)
        TextView rkr;
        @BindView(R.id.beijing)
        LinearLayout beijing;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
