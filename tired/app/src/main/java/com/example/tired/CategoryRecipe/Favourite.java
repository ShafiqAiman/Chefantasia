package com.example.tired.CategoryRecipe;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tired.Adapter.MyItemAdapter;
import com.example.tired.BrowseRecipe;
import com.example.tired.Model.ItemData;
import com.example.tired.R;
import com.example.tired.RecylerViewAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Favourite extends AppCompatActivity {
    ArrayList<ItemData> recipeList;
    FirebaseDatabase db;
    MyItemAdapter myAdapter;
    RecyclerView myrv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browserecipe);
        getSupportActionBar().setTitle("Favourite");

        recipeList = new ArrayList<>();

        myrv = (RecyclerView) findViewById(R.id.recylerview_id);
        myrv.setLayoutManager(new GridLayoutManager(this,3));

        db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("MyData"); //child("2").child("listItem")

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot d: dataSnapshot.getChildren()){

                    for(DataSnapshot e: d.child("listItem").getChildren()){
                        ItemData itemData = new ItemData();
                        itemData = e.getValue(ItemData.class);
                        if (itemData.getRating() >= 4.0)
                            recipeList.add(itemData);
                    }


                }
                myAdapter = new MyItemAdapter(Favourite.this,recipeList);
                myrv.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Favourite.this,"Oops something is wrong",Toast.LENGTH_SHORT);
            }
        });



    }
}
