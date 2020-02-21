package com.example.demony.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.demony.R;
import com.example.demony.bean.Z_Jbxx;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2020-02-21 ：）
 */
public class YPRYAdapter2 extends ArrayAdapter<Z_Jbxx> {
    public YPRYAdapter2(@NonNull Context context, int resource, @NonNull List<Z_Jbxx> objects) {
        super(context, resource, objects);
    }

    private boolean is = false;

    public void setIs(boolean is) {
        this.is = is;
    }

    public boolean isIs() {
        return is;
    }

    public interface Click {
        void Click(int position, boolean is,int lx);
    }

    public void setClick(Click click) {
        this.click = click;
    }

    private Click click;


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Z_Jbxx z_jbxx = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.ypry_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.tvCsny.setText("出生日期" + z_jbxx.getCsrq());
        holder.tvGzjl.setText("工作经历:" + z_jbxx.getGzjl());
        holder.tvXl.setText("学历：" + z_jbxx.getXl());
        holder.tvXm.setText("姓名:" + z_jbxx.getName());
        Glide.with(parent.getContext()).load(z_jbxx.getPath()).into(holder.ivTx);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.Click(position,false,1);
            }
        });
        holder.cbXz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.cbXz.isChecked()) {
                    click.Click(position,true,2);
                }else {
                    click.Click(position,false,2);
                }
            }
        });holder.cbXz.setChecked(is);
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.iv_tx)
        ImageView ivTx;
        @BindView(R.id.tv_xm)
        TextView tvXm;
        @BindView(R.id.tv_xl)
        TextView tvXl;
        @BindView(R.id.tv_csny)
        TextView tvCsny;
        @BindView(R.id.tv_gzjl)
        TextView tvGzjl;
        @BindView(R.id.cb_xz)
        CheckBox cbXz;
        @BindView(R.id.layout)
        LinearLayout layout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
