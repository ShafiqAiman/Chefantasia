package com.example.tired;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tired.Model.ItemData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BrowseRecipe extends AppCompatActivity {
ArrayList<ItemData> recipeList;
FirebaseDatabase db;
RecylerViewAdapter myAdapter;
    RecyclerView myrv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browserecipe);

        recipeList = new ArrayList<>();

        myrv = (RecyclerView) findViewById(R.id.recylerview_id);
        myrv.setLayoutManager(new GridLayoutManager(this,3));

        db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference().child("MyData");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot d: dataSnapshot.getChildren()){
                    ItemData itemData = new ItemData();
                    itemData = d.child("listItem").getValue(ItemData.class);
                    recipeList.add(itemData);
                }
                 myAdapter = new RecylerViewAdapter(BrowseRecipe.this,recipeList);
                myrv.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(BrowseRecipe.this,"Oops something is wrong",Toast.LENGTH_SHORT);
            }
        });



    }
}
