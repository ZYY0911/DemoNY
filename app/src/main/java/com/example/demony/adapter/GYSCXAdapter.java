package com.example.demony.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.demony.R;
import com.example.demony.activity.Z_LXGYSActivity;
import com.example.demony.bean.GYSCXBean;

import java.util.List;

/**
 * Create by 张瀛煜 on 2020-02-22 ：）
 */
public class GYSCXAdapter extends RecyclerView.Adapter<GYSCXAdapter.MyViewholder> {
    private List<GYSCXBean> gyscxBeans;
    private int lx;
    private Context context;

    public GYSCXAdapter(List<GYSCXBean> gyscxBeans, int lx) {
        this.gyscxBeans = gyscxBeans;
        this.lx = lx;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (context == null) context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.gyscx_item, viewGroup, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder myViewholder, final int i) {
        final GYSCXBean gyscxBean = gyscxBeans.get(i);
        myViewholder.itemText.setText(gyscxBean.getName());
        int image = 0;
        switch (gyscxBean.getImage()) {
            case "car1":
                image = R.mipmap.city1;
                break;
            case "car2":
                image = R.mipmap.city2;
                break;
            case "car3":
                image = R.mipmap.city3;
                break;
            case "car4":
                image = R.mipmap.city4;
                break;
            case "tie":
                image = R.mipmap.city5;
                break;
            case "suoliao":
                image = R.mipmap.city6;
                break;
            case "oil":
                image = R.mipmap.city3;
                break;
        }
        myViewholder.itemImage.setImageResource(image);
        myViewholder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Z_LXGYSActivity.class);
                switch (lx) {
                    case 1:
                        intent.putExtra("lx", 1);
                        break;
                    case 2:
                        intent.putExtra("lx", 2);
                        break;
                    case 3:
                        intent.putExtra("lx", 3);
                        break;
                    case 4:
                        intent.putExtra("lx", 4);
                        intent.putExtra("index", i);
                        break;
                }
                intent.putExtra("name", gyscxBean.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return gyscxBeans.size();
    }

    static class MyViewholder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemText;
        CardView cardView;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            itemText = itemView.findViewById(R.id.item_text);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
