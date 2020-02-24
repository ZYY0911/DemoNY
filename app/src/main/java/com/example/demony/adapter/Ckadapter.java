package com.example.demony.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demony.R;
import com.example.demony.Solle;
import com.example.demony.bean.Ck;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Ckadapter extends BaseAdapter {
    private List<Ck> mck;
    private Context context;

    public interface SetData {
        void setdata(int position, String name,int lx,String xh,String path);
    }

    public SetData data;

    public void SetData(SetData data) {
        this.data = data;
    }

    public Ckadapter(Context context, List<Ck> mck) {
        this.context = context;
        this.mck = mck;
    }

    @Override
    public int getCount() {
        return mck.size();
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
        final Ck ck = mck.get(position);
        View view = View.inflate(context, R.layout.ck_item, null);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.kcl.setText("库存量：" + ck.getKcl());
        viewHolder.mc.setText("原料名称：" + ck.getName());
        viewHolder.wz.setText("位置：" + ck.getWz());
        viewHolder.xh.setText("型号：" + ck.getXh());
        viewHolder.ck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setdata(position, ck.getName(),1,ck.getXh(),ck.getPath());
            }
        });
        if (ck.isHx())
        {
            viewHolder.solle.post(new Runnable() {
                @Override
                public void run() {
                    viewHolder.solle.fullScroll(View.FOCUS_RIGHT);
                }
            });
        }
        viewHolder.solle.SetData(new Solle.SetData() {
            @Override
            public void setdata(int x) {
                if (!ck.isHx())
                {
                    if (x>20)
                    {
                        data.setdata(position, ck.getName(),2,ck.getXh(),ck.getPath());
                    }
                }
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
        @BindView(R.id.ck)
        TextView ck;
        @BindView(R.id.solle)
        Solle solle;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
