package com.example.tired.Model;

import java.util.ArrayList;

public class ItemGroup {
    private String cat_Title;
    public ArrayList<ItemData> listItem;

    public ItemGroup(){

    }

    public ItemGroup(String headerTitle, ArrayList<ItemData> listItem) {
        this.cat_Title = headerTitle;
        this.listItem = listItem;
    }

    public String getCat_Title() {
        return cat_Title;
    }

    public void setCat_Title(String cat_Title) {
        this.cat_Title = cat_Title;
    }

    public ArrayList<ItemData> getListItem() {
        return listItem;
    }

    public void setListItem(ArrayList<ItemData> listItem) {
        this.listItem = listItem;
    }
}
