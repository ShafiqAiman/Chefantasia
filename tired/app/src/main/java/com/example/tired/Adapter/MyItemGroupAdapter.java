package com.example.tired.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tired.BrowseRecipe;
import com.example.tired.CategoryRecipe.Asian;
import com.example.tired.CategoryRecipe.Favourite;
import com.example.tired.CategoryRecipe.Recommeded;
import com.example.tired.CategoryRecipe.Western;
import com.example.tired.Model.ItemData;
import com.example.tired.Model.ItemGroup;
import com.example.tired.R;

import java.util.List;

public class MyItemGroupAdapter extends RecyclerView.Adapter<MyItemGroupAdapter.MyViewHolder> {

    private Context context;
    private List<ItemGroup> dataList;

    public MyItemGroupAdapter(Context context, List<ItemGroup> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.layout_group,parent,false);
        return  new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.item_title.setText(dataList.get(position).getCat_Title());
        List<ItemData> itemData = dataList.get(position).getListItem();

        MyItemAdapter itemListAdapter = new MyItemAdapter(context,itemData);
        holder.recyclerView_item_list.setHasFixedSize(true);
        holder.recyclerView_item_list.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        holder.recyclerView_item_list.setAdapter(itemListAdapter);

        holder.recyclerView_item_list.setNestedScrollingEnabled(false);


    }

    @Override
    public int getItemCount() {
        return (dataList != null ? dataList.size() : 0);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item_title;
        RecyclerView recyclerView_item_list;
        Button btn_more;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            context=itemView.getContext();

            item_title= (TextView) itemView.findViewById(R.id.itemTitle);
            btn_more = (Button) itemView.findViewById(R.id.btnMore);
            recyclerView_item_list = (RecyclerView) itemView.findViewById(R.id.my_recycler_view_list);

            btn_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent intent;
                    switch (getAdapterPosition()){
                        case 0:
                            intent= new Intent(context, Favourite.class);
                            break;
                        case 1:
                            intent= new Intent(context, Recommeded.class);
                            break;
                        case 2:
                            intent = new Intent(context,Western.class);
                            break;
                        case 3:
                            intent = new Intent(context, Asian.class);
                            break;
                            default:
                                intent = new Intent(context,BrowseRecipe.class);

                    }
                    context.startActivity(intent);
                }
            });
        }
    }
}
