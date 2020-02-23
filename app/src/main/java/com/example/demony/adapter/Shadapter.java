package com.example.demony.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.demony.R;
import com.example.demony.bean.Sh;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Shadapter extends BaseAdapter {
    private List<Sh> msh;
    private Context context;

    public interface SetData{
        void setdata(int position,boolean xz);
    }
    public SetData data;
    public void SetData(SetData data)
    {
        this.data=data;
    }
    public Shadapter(Context context, List<Sh> msh) {
        this.context = context;
        this.msh = msh;
    }

    @Override
    public int getCount() {
        return msh.size();
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
        final Sh sh = msh.get(position);
        View view = View.inflate(context, R.layout.sh_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.bh.setText("信息编号："+sh.getBh());
        viewHolder.qymc.setText("企业名称："+sh.getName());
        viewHolder.hy.setText("行业："+sh.getHy());
        viewHolder.szd.setText("所在地："+sh.getSzd());
        viewHolder.xl.setText("学历："+sh.getXl());
        if (sh.getZt().equals("1"))
        {
            viewHolder.zt.setText("状态：审核中");
        }else if (sh.getZt().equals("2"))
        {
            viewHolder.zt.setText("状态：同意");
        }else if (sh.getZt().equals("3"))
        {
            viewHolder.zt.setText("状态：拒绝");
        }
        viewHolder.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sh.isXz())
                {
                    data.setdata(position,false);
                }else {
                    data.setdata(position,true);
                }
            }
        });
        if (sh.isXz())
        {
            viewHolder.checkbox.setChecked(true);
        }else {
            viewHolder.checkbox.setChecked(false);
        }

        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.bh)
        TextView bh;
        @BindView(R.id.qymc)
        TextView qymc;
        @BindView(R.id.xl)
        TextView xl;
        @BindView(R.id.hy)
        TextView hy;
        @BindView(R.id.szd)
        TextView szd;
        @BindView(R.id.zt)
        TextView zt;
        @BindView(R.id.checkbox)
        CheckBox checkbox;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
