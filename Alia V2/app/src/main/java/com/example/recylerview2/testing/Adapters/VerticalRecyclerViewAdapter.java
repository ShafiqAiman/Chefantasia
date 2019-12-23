package com.example.recylerview2.testing.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recylerview2.BrowseRecipe;
import com.example.recylerview2.RecylerViewAdapter;
import com.example.recylerview2.Recipe;
import com.example.recylerview2.testing.Models.VerticalModel;
import com.example.recylerview2.R;

import java.util.ArrayList;

public class VerticalRecyclerViewAdapter extends RecyclerView.Adapter<VerticalRecyclerViewAdapter.VerticalRVHolder> {

    Context context;
    ArrayList<VerticalModel> arrayList;

    public VerticalRecyclerViewAdapter(Context context, ArrayList<VerticalModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public VerticalRVHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemvertical,parent,false);
        return new VerticalRVHolder(view);
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull VerticalRVHolder holder, int position) {
        VerticalModel verticalModel = arrayList.get(position);
        String title = verticalModel.getTitle();
        ArrayList<Recipe> singleItem = verticalModel.getArrayList();

        holder.textView.setText(title);
        RecylerViewAdapter horizontalRecyclerViewAdapter = new RecylerViewAdapter(context,singleItem);

        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));

        holder.buttonmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BrowseRecipe.class);

                context.startActivity(intent);
            }
        });
        holder.recyclerView.setAdapter(horizontalRecyclerViewAdapter);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class VerticalRVHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        TextView textView;
        Button buttonmore;
        public VerticalRVHolder(@androidx.annotation.NonNull View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recylerview2);
            textView = (TextView) itemView.findViewById(R.id.txtTitle);
            buttonmore = (Button) itemView.findViewById(R.id.button);
        }
    }
}
