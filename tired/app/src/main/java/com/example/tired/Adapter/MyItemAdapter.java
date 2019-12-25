package com.example.tired.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tired.Interface.IItemClickListener;
import com.example.tired.Model.ItemData;
import com.example.tired.R;
import com.example.tired.RecipeInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyItemAdapter extends RecyclerView.Adapter<MyItemAdapter.MyViewHolder> {

    private Context context;
    private List<ItemData> itemDataList;

    public MyItemAdapter(Context context, List<ItemData> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.txt_item_title.setText(itemDataList.get(position).getName());
        holder.ratingBar.setRating(itemDataList.get(position).getRating());
        Picasso.get().load(itemDataList.get(position).getImage()).into(holder.img_item);


        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Connect this to recipe interface
                Intent intent = new Intent(context, RecipeInterface.class);
                intent.putExtra("name", itemDataList.get(position).getName());
                intent.putExtra("image", itemDataList.get(position).getImage());
                intent.putExtra("value",itemDataList.get(position).getRating());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (itemDataList != null ? itemDataList.size() : 0);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txt_item_title;
        ImageView img_item;
        RatingBar ratingBar;
        RelativeLayout relativeLayout;

        IItemClickListener iItemClickListener;

        public void setiItemClickListener (IItemClickListener iItemClickListener) {
            this.iItemClickListener = iItemClickListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_item_title = (TextView) itemView.findViewById(R.id.tvTitle);
            img_item = (ImageView) itemView.findViewById(R.id.itemImage);
            ratingBar = (RatingBar) itemView.findViewById(R.id.MyRating);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativelayout);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
           iItemClickListener.onItemClickListener(v,getAdapterPosition());
        }
    }
}
