package com.example.tired.CategoryRecipe;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tired.Adapter.MyItemAdapter;
import com.example.tired.Model.ItemData;
import com.example.tired.R;
import com.example.tired.RecylerViewAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class Recommeded extends AppCompatActivity {
    ArrayList<ItemData> recipeList;
    FirebaseDatabase db;
    RecylerViewAdapter myAdapter;
    RecyclerView myrv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browserecipe);
        getSupportActionBar().setTitle("Recommended");

        recipeList = new ArrayList<>();

        myrv = (RecyclerView) findViewById(R.id.recylerview_id);
        myrv.setLayoutManager(new GridLayoutManager(this,3));

        db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("Foods");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<ItemData> itemDataArrayList = new ArrayList<>();

                for(DataSnapshot groupsnapshot: dataSnapshot.getChildren()){
                    ItemData itemData = new ItemData();
                    itemData = groupsnapshot.getValue(ItemData.class);

                        itemDataArrayList.add(itemData);

                }
                Collections.shuffle(itemDataArrayList);
                myAdapter = new RecylerViewAdapter(Recommeded.this,itemDataArrayList);
                myrv.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Recommeded.this,"Oops something is wrong",Toast.LENGTH_SHORT);
            }
        });



    }
}
