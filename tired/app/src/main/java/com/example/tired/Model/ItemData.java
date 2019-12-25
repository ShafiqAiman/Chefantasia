package com.example.tired.Model;

public class ItemData {
    private String Name, Image, Ingredient, MenuID;
    private float Rating;

    public ItemData(String name, String image, String ingredient, String MenuID, float rating) {
        Name = name;
        Image = image;
        Ingredient = ingredient;
        Rating = rating;
        this.MenuID = MenuID;
    }

    public String getMenuID() {
        return MenuID;
    }

    public void setMenuID(String menuID) {
        MenuID = menuID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getIngredient() {
        return Ingredient;
    }

    public void setIngredient(String ingredient) {
        Ingredient = ingredient;
    }

    public float getRating() {
        return Rating;
    }

    public void setRating(float rating) {
        Rating = rating;
    }

    public ItemData(){

    }


}
