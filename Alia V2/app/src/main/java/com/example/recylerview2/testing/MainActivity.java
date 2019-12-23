package com.example.recylerview2.testing;

import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recylerview2.R;
import com.example.recylerview2.testing.Adapters.VerticalRecyclerViewAdapter;
import com.example.recylerview2.Recipe;
import com.example.recylerview2.testing.Models.VerticalModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Category");
    DatabaseReference myRef2 = database.getReference("Food");

    ArrayList<Recipe> recipes;

    RecyclerView verticalRecylerView;
    VerticalRecyclerViewAdapter adapter;
    ArrayList<VerticalModel> arrayListVertical;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayListVertical = new ArrayList<>();

        verticalRecylerView = (RecyclerView)findViewById(R.id.recylerview1);
        verticalRecylerView.setHasFixedSize(true);

        verticalRecylerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        adapter= new VerticalRecyclerViewAdapter(this, arrayListVertical);

        verticalRecylerView.setAdapter(adapter);

        setData();
    }
    public void setData(){
        final ArrayList<Category> categories = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              for(DataSnapshot ds: dataSnapshot.getChildren()){
                  Category c = ds.getValue(Category.class);
                  VerticalModel mverticalModel = new VerticalModel();
                  mverticalModel.setTitle(c.getName());

                  ArrayList<Recipe> arrayList = new ArrayList<>();

                  for(int j=1; j<=5;j++){
                      Recipe horizontalModel = new Recipe();
                      horizontalModel.setName("Name: "+j);

                      arrayList.add(horizontalModel);
                  }


                  mverticalModel.setArrayList(arrayList);
                  arrayListVertical.add(mverticalModel);
              }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }


}
