package com.example.demony.adapter;

import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demony.R;
import com.example.demony.bean.TJyl;

import java.util.List;

public class Xqadapter extends RecyclerView.Adapter<Xqadapter.ViewHolder> {
    private List<TJyl> mtjyl;
    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView,textView1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.im);
            textView = itemView.findViewById(R.id.mc);
            textView1 = itemView.findViewById(R.id.jg);

        }
    }
    public Xqadapter(List<TJyl> mtjyl)
    {
        this.mtjyl=mtjyl;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.tjyl_item1,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        TJyl tJyl = mtjyl.get(i);
        viewHolder.imageView.setImageBitmap(BitmapFactory.decodeFile(tJyl.getImage()));
        viewHolder.textView.setText("原料名称："+tJyl.getMc());
        viewHolder.textView1.setText("价格："+tJyl.getJg());
    }

    @Override
    public int getItemCount() {
        return mtjyl.size();
    }
}
