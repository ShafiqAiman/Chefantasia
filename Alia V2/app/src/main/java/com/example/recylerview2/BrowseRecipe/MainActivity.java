package com.example.recylerview2.BrowseRecipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.recylerview2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
List<Recipe> recipeList;
FirebaseDatabase db;
RecylerViewAdapter myAdapter;
    RecyclerView myrv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipeList = new ArrayList<>();
        //recipeList.add(new Recipe("Chicken Rendang",))

        myrv = (RecyclerView) findViewById(R.id.recylerview_id);
        myrv.setLayoutManager(new GridLayoutManager(this,3));

        db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference().child("Food");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot d: dataSnapshot.getChildren()){
                    Recipe p = d.getValue(Recipe.class);
                    recipeList.add(p);
                }
                 myAdapter = new RecylerViewAdapter(MainActivity.this,recipeList);
                myrv.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this,"Oops something is wrong",Toast.LENGTH_SHORT);
            }
        });



    }
}
