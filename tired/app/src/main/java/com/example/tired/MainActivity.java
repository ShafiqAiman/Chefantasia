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
        myData = FirebaseDatabase.getInstance().getReference();
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


        myData.child("Foods").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                List<ItemGroup> itemGroups = new ArrayList<>();
                ItemGroup itemGroup = new ItemGroup();
                ArrayList<ItemData> itemDataArrayList = new ArrayList<>();
                ArrayList<ItemData> itemDataArrayList2 = new ArrayList<>();

                for(DataSnapshot groupsnapshot: dataSnapshot.getChildren()){
                    ItemData itemData = new ItemData();
                    itemData = groupsnapshot.getValue(ItemData.class);
                    itemDataArrayList.add(itemData);

                }

                itemGroup = new ItemGroup();
                itemGroup.setCat_Title("Favourite");

                for(ItemData trial: itemDataArrayList){
                    if (trial.getRating()>=4.f){
                        itemDataArrayList2.add(trial);
                    }
                }

                Collections.shuffle(itemDataArrayList2);
                if(itemDataArrayList2.size()>10){
                    itemDataArrayList2.subList(10,itemDataArrayList2.size()).clear();
                }
                itemGroup.setListItem(itemDataArrayList2);
                itemGroups.add(itemGroup);

                // recommended for you
                itemGroup = new ItemGroup();
                itemGroup.setCat_Title("Recommended for you");

                Collections.shuffle(itemDataArrayList);
                if(itemDataArrayList.size()>10){
                    itemDataArrayList.subList(10,itemDataArrayList.size()).clear();
                }
               itemGroup.setListItem(itemDataArrayList);
               itemGroups.add(itemGroup);

                // Western
                itemGroup = new ItemGroup();
                itemGroup.setCat_Title("Western");
                itemDataArrayList2 = new ArrayList<>();

                for(ItemData trial: itemDataArrayList){
                    if (trial.getMenuId().equals("01")){
                        itemDataArrayList2.add(trial);
                    }
                }
                Collections.shuffle(itemDataArrayList2);
                if(itemDataArrayList2.size()>10){
                    itemDataArrayList2.subList(10,itemDataArrayList2.size()).clear();
                }
                itemGroup.setListItem(itemDataArrayList2);
                itemGroups.add(itemGroup);

                //Asian
                itemGroup = new ItemGroup();
                itemGroup.setCat_Title("Asian");
                itemDataArrayList2 = new ArrayList<>();

                for(ItemData trial: itemDataArrayList){
                    if (trial.getMenuId().equals("02")){
                        itemDataArrayList2.add(trial);
                    }
                }
                Collections.shuffle(itemDataArrayList2);
                if(itemDataArrayList2.size()>10){
                    itemDataArrayList2.subList(10,itemDataArrayList2.size()).clear();
                }
                itemGroup.setListItem(itemDataArrayList2);
                itemGroups.add(itemGroup);

               /*


                //Asian

                itemGroups = new ArrayList<>();
                itemGroup = new ItemGroup();
                itemDataArrayList = new ArrayList<>();

                for(DataSnapshot groupsnapshot: dataSnapshot.getChildren()){
                    ItemData itemData = new ItemData();
                    itemData = groupsnapshot.getValue(ItemData.class);
                    if(itemData.getMenuId()=="02"){
                        itemDataArrayList.add(itemData);
                    }
                }
                itemGroup = new ItemGroup();
                itemGroup.setCat_Title("Asian");
                Collections.shuffle(itemDataArrayList);
                if(itemDataArrayList.size()>10){
                    itemDataArrayList.subList(10,itemDataArrayList.size()).clear();
                }
                itemGroup.setListItem(itemDataArrayList);
                itemGroups.add(itemGroup);*/

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
