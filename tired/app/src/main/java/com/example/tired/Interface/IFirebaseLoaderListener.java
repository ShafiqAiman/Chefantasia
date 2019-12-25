package com.example.tired.Interface;

import com.example.tired.Model.ItemGroup;

import java.util.List;

public interface IFirebaseLoaderListener {

    void onFirebaseLoadSucecess(List<ItemGroup> itemGroups);
    void onFirebaseLoaderFailed(String message);
}
