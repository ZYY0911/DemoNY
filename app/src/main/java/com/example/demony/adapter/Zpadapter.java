package com.example.demony.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demony.R;
import com.example.demony.Solle;
import com.example.demony.bean.Zp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Zpadapter extends BaseAdapter {
    private List<Zp> mzp;
    private Context context;

    public interface SetData {
        void setdata(int posotion, boolean sc, int lx, String bh, String name,String hylx,String gw,String zy
        ,String cs,String xl,String xz,String yx,String fbsj);
    }

    public SetData data;

    public void SetData(SetData data) {
        this.data = data;
    }

    public Zpadapter(Context context, List<Zp> mzp) {
        this.context = context;
        this.mzp = mzp;
    }

    @Override
    public int getCount() {
        return mzp.size();
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
        final Zp zp = mzp.get(position);
        View view = View.inflate(context, R.layout.zp_item, null);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.gsmc.setText("公司名称：" + zp.getName());
        viewHolder.hylx.setText("行业类型：" + zp.getHy());
        viewHolder.gwmc.setText("岗位名称：" + zp.getGw());
        viewHolder.zyyq.setText("专业要求：" + zp.getZyyq());
        viewHolder.cs.setText("所在城市：" + zp.getSzd());

        viewHolder.xl.setText("学历要求：" + zp.getXl());
        viewHolder.xz.setText("薪资范围：" + zp.getXz());
        viewHolder.email.setText("联系邮箱：" + zp.getEmail());
        viewHolder.fabsj.setText("发布时间：" + zp.getTime());
        viewHolder.shr.setText("审核人：" + zp.getShr());
        if (zp.isHx()) {
            viewHolder.solle.post(new Runnable() {
                @Override
                public void run() {
                    viewHolder.solle.fullScroll(View.FOCUS_RIGHT);
                }
            });
        }
        if (zp.isSc())
        {
            viewHolder.sc.setText("已收藏");
        }else {
            viewHolder.sc.setText("收藏");
        }
        viewHolder.solle.SetData(new Solle.SetData() {
            @Override
            public void setdata(int x) {
                if (!zp.isHx()) {
                    if (x > 20) {
                        data.setdata(position, true, 1, zp.getBh(), zp.getName()
                        ,zp.getHy(),zp.getGw(),zp.getZyyq(),zp.getSzd(),zp.getXl(),zp.getXz(),zp.getEmail(),zp.getTime());
                    }
                }
            }
        });
        viewHolder.sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zp.isSc())
                {
                    data.setdata(position, false, 2, zp.getBh(), zp.getName()
                            ,zp.getHy(),zp.getGw(),zp.getZyyq(),zp.getSzd(),zp.getXl(),zp.getXz(),zp.getEmail(),zp.getTime());
                }else {
                    data.setdata(position, true, 2, zp.getBh(), zp.getName()
                            ,zp.getHy(),zp.getGw(),zp.getZyyq(),zp.getSzd(),zp.getXl(),zp.getXz(),zp.getEmail(),zp.getTime());
                }
            }
        });
        viewHolder.yp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setdata(position, true, 3, zp.getBh(), zp.getName()
                        ,zp.getHy(),zp.getGw(),zp.getZyyq(),zp.getSzd(),zp.getXl(),zp.getXz(),zp.getEmail(),zp.getTime());
            }
        });
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.gsmc)
        TextView gsmc;
        @BindView(R.id.hylx)
        TextView hylx;
        @BindView(R.id.gwmc)
        TextView gwmc;
        @BindView(R.id.zyyq)
        TextView zyyq;
        @BindView(R.id.cs)
        TextView cs;
        @BindView(R.id.xl)
        TextView xl;
        @BindView(R.id.xz)
        TextView xz;
        @BindView(R.id.email)
        TextView email;
        @BindView(R.id.fabsj)
        TextView fabsj;
        @BindView(R.id.shr)
        TextView shr;
        @BindView(R.id.sc)
        TextView sc;
        @BindView(R.id.yp)
        TextView yp;
        @BindView(R.id.solle)
        Solle solle;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
