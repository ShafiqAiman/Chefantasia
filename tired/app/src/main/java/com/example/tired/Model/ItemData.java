package com.example.tired.Model;

public class ItemData {
    private String Name, Image, Ingredient, MenuId;
    private float Rating;

    public ItemData(String name, String image, String ingredient, String menuId, float rating) {
        Name = name;
        Image = image;
        Ingredient = ingredient;
        MenuId = menuId;
        Rating = rating;
    }

    public ItemData() {
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

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }

    public float getRating() {
        return Rating;
    }

    public void setRating(float rating) {
        Rating = rating;
    }
}