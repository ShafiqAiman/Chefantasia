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

public class Favourite extends AppCompatActivity {
    FirebaseDatabase db;
    RecylerViewAdapter myAdapter;
    RecyclerView myrv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browserecipe);
        getSupportActionBar().setTitle("Favourite");

        myrv = (RecyclerView) findViewById(R.id.recylerview_id);
        myrv.setLayoutManager(new GridLayoutManager(this,3));

        db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("Foods"); //child("2").child("listItem")

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<ItemData> itemDataArrayList = new ArrayList<>();

                for(DataSnapshot groupsnapshot: dataSnapshot.getChildren()){
                    ItemData itemData = new ItemData();
                    itemData = groupsnapshot.getValue(ItemData.class);
                    if(itemData.getRating() >= 4f){
                        itemDataArrayList.add(itemData);
                    }

                }
                Collections.shuffle(itemDataArrayList);
                myAdapter = new RecylerViewAdapter(Favourite.this,itemDataArrayList);
                myrv.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Favourite.this,"Oops something is wrong",Toast.LENGTH_SHORT);
            }
        });



    }
}
