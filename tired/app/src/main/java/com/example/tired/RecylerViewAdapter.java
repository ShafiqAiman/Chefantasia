package com.example.tired;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tired.Model.ItemData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.MyViewHolder> {

    private Context mcontext;
    private ArrayList<ItemData> mData = new ArrayList<>();

    public RecylerViewAdapter(Context mcontext, ArrayList<ItemData> mData) {
        this.mcontext = mcontext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater= LayoutInflater.from(mcontext);
        view = mInflater.inflate(R.layout.recyler_recipe_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.title_recipe.setText(mData.get(position).getName());
        Picasso.get().load(mData.get(position).getImage()).into(holder.image_recipe);
        holder.ratingBar.setRating(mData.get(position).getRating());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext,RecipeInterface.class);
                intent.putExtra("name", mData.get(position).getName());
                intent.putExtra("image", mData.get(position).getImage());
                intent.putExtra("rating",mData.get(position).getRating());
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (mData == null) ? 0 : mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title_recipe;
        ImageView image_recipe;
        RelativeLayout relativeLayout;
        RatingBar ratingBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title_recipe = (TextView) itemView.findViewById(R.id.text);
            image_recipe = (ImageView) itemView.findViewById(R.id.image);
            ratingBar = (RatingBar) itemView.findViewById(R.id.MyRating);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativelayout2);
        }
    }
}
