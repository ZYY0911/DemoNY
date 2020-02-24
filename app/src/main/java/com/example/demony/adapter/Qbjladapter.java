package com.example.demony.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.demony.R;
import com.example.demony.bean.Rkjl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Qbjladapter extends BaseAdapter {
    private List<Rkjl> mrkjl;
    private Context context;

    public interface SetData {
        void setdata(String name, String xh, String gys, String shuliang, String danjia, String weizhi
                , String cgy, String lxr, String dfzh, String ren, String time,String path);
    }

    public SetData data;

    public void SetData(SetData data) {
        this.data = data;
    }

    public Qbjladapter(Context context, List<Rkjl> mrkjl) {
        this.context = context;
        this.mrkjl = mrkjl;
    }

    @Override
    public int getCount() {
        return mrkjl.size();
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
        final Rkjl rkjl = mrkjl.get(position);
        View view = View.inflate(context, R.layout.qbjl_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.ylmc.setText("原料名称：" + rkjl.getName());
        viewHolder.xh.setText("型号：" + rkjl.getXh());
        viewHolder.sj.setText("时间：" + rkjl.getTime());
        viewHolder.sl.setText("数量：" + rkjl.getShuliang());
        viewHolder.gys.setText("供应商：" + rkjl.getGys());
        viewHolder.rkr.setText("入库人：" + rkjl.getRen());
        viewHolder.beijing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setdata(rkjl.getName(),rkjl.getXh(),rkjl.getGys(),rkjl.getShuliang(),rkjl.getDj(),
                        rkjl.getWeizhi(),rkjl.getCaigoyuan(),rkjl.getLianxiren(),rkjl.getZhanghao()
                        ,rkjl.getRen(),rkjl.getTime(),rkjl.getPath());
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
