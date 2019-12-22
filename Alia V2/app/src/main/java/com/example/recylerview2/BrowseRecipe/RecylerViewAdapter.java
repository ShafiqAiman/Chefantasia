package com.example.recylerview2.BrowseRecipe;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recylerview2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.MyViewHolder> {

    private Context mcontext;
    private List<Recipe> mData;

    public RecylerViewAdapter(Context mcontext, List<Recipe> mData) {
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
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext,RecipeInterface.class);
                intent.putExtra("name", mData.get(position).getName());
                intent.putExtra("image", mData.get(position).getImage());
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title_recipe;
        ImageView image_recipe;
        RelativeLayout relativeLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title_recipe = (TextView) itemView.findViewById(R.id.text);
            image_recipe = (ImageView) itemView.findViewById(R.id.image);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativelayout);
        }
    }
}
