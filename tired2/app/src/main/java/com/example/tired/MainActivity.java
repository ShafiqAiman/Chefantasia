package com.example.tired;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.tired.Adapter.MyItemGroupAdapter;
import com.example.tired.Interface.IFirebaseLoaderListener;
import com.example.tired.Model.ItemData;
import com.example.tired.Model.ItemGroup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import dmax.dialog.SpotsDialog;


public class MainActivity extends AppCompatActivity implements IFirebaseLoaderListener {

    AlertDialog dialog;
    IFirebaseLoaderListener iFirebaseLoaderListener;

    RecyclerView my_recycler_view;

    DatabaseReference myData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init
        myData = FirebaseDatabase.getInstance().getReference("MyData");
        dialog = new SpotsDialog.Builder().setContext(this).build();
        iFirebaseLoaderListener = this;

        //view
        my_recycler_view = (RecyclerView) findViewById(R.id.my_recycler_view);
        my_recycler_view.setHasFixedSize(true);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(this));

        //load data
        getFirebaseData();
    }

    private void getFirebaseData() {
        dialog.show();

        myData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<ItemGroup> itemGroups = new ArrayList<>();
                ItemGroup itemGroup;
              for(int i =0; i<2;i++){
                  itemGroup = new ItemGroup();

                  switch(i){
                      case 0:
                          itemGroup.setCat_Title("Favourite");
                          break;
                      case 1:
                          itemGroup.setCat_Title("Recommended for you");
                          break;
                          default:
                  }

                  ArrayList<ItemData> trial = new ArrayList<>();

                  for(DataSnapshot d: dataSnapshot.getChildren()){

                      for(DataSnapshot e: d.child("listItem").getChildren()){
                          ItemData itemData = new ItemData();
                          itemData = e.getValue(ItemData.class);
                          if(i==0 && itemData.getRating()>=4f){
                              trial.add(itemData);
                          }else if(i==1){
                              trial.add(itemData);
                          }else{}

                      }

                  }
                  Collections.shuffle(trial);
                  itemGroup.setListItem(trial);
                  if(itemGroup.listItem.size()>10)  // limit the preview of data
                      itemGroup.listItem.subList(10,itemGroup.listItem.size()).clear();
                  itemGroups.add(itemGroup);
              }

                for(DataSnapshot groupsnapshot: dataSnapshot.getChildren()){
                   itemGroup = new ItemGroup();

                   itemGroup.setCat_Title(groupsnapshot.child("cat_Title").getValue(true).toString());
                   GenericTypeIndicator<ArrayList<ItemData>> genericTypeIndicator = new GenericTypeIndicator<ArrayList<ItemData>>(){};
                   itemGroup.setListItem(groupsnapshot.child("listItem").getValue(genericTypeIndicator));

                   Collections.shuffle(itemGroup.listItem);
                    if(itemGroup.listItem.size()>10)  // limit the preview of data
                        itemGroup.listItem.subList(10,itemGroup.listItem.size()).clear();
                    itemGroups.add(itemGroup);
                }
                iFirebaseLoaderListener.onFirebaseLoadSucecess(itemGroups);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                iFirebaseLoaderListener.onFirebaseLoaderFailed(databaseError.getMessage());
            }
        });

    }

    @Override
    public void onFirebaseLoadSucecess(List<ItemGroup> itemGroups) {
        MyItemGroupAdapter adapter = new MyItemGroupAdapter(this,itemGroups);
        my_recycler_view.setAdapter(adapter);

        dialog.dismiss();
    }

    @Override
    public void onFirebaseLoaderFailed(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
        dialog.dismiss();

    }
}
