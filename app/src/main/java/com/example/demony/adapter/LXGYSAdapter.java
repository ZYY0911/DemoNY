package com.example.demony.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.demony.R;
import com.example.demony.bean.Z_Jxx;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2020-02-22 ：）
 */
public class LXGYSAdapter extends ArrayAdapter<Z_Jxx> {
    private int layout;

    public interface Myclick {
        void mtCccc(int index);
    }

    private Myclick myclick;

    public void setMyclick(Myclick myclick) {
        this.myclick = myclick;
    }

    public LXGYSAdapter(@NonNull Context context, int resource, @NonNull List<Z_Jxx> objects) {
        super(context, resource, objects);
        layout = resource;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Z_Jxx gys = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        Glide.with(getContext()).load(gys.getImage()).into(holder.gysImage);
        holder.itemAddress.setText("地址:" + gys.getDd());
        holder.itemCp.setText("产品:" + gys.getYwfw());
        holder.itemName.setText("名称:" + gys.getMc());
        holder.gysCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myclick.mtCccc(position);
            }
        });
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.gys_image)
        ImageView gysImage;
        @BindView(R.id.item_name)
        TextView itemName;
        @BindView(R.id.item_address)
        TextView itemAddress;
        @BindView(R.id.item_cp)
        TextView itemCp;
        @BindView(R.id.gys_cell)
        ImageView gysCell;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
