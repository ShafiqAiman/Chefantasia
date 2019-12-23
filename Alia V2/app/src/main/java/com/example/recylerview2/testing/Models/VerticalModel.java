package com.example.recylerview2.testing.Models;
import com.example.recylerview2.Recipe;


import java.util.ArrayList;

public class VerticalModel {
    String title;
    ArrayList<Recipe> arrayList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Recipe> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Recipe> arrayList) {
        this.arrayList = arrayList;
    }
}
