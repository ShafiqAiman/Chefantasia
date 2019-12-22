package com.example.recylerview2.BrowseRecipe;

public class Recipe {
    private String Name, Image, MenuID, Ingredient;
    private float Rating;

    public Recipe() {

    }

    public Recipe(String Name, String Image, String MenuID, String Ingredient, float Rating) {
        this.Name = Name;
        this.Image = Image;
        this.MenuID = MenuID;
        this.Ingredient = Ingredient;
        this.Rating = Rating;
    }

    public String getName() {
        return Name;
    }

    public String getImage() {
        return Image;
    }

    public String getMenuID() {
        return MenuID;
    }

    public String getIngredient() {
        return Ingredient;
    }

    public float getRating() {
        return Rating;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setImage(String image) {
        Image = image;
    }

    public void setMenuID(String menuID) {
        MenuID = menuID;
    }

    public void setIngredient(String ingredient) {
        Ingredient = ingredient;
    }

    public void setRating(float rating) {
        Rating = rating;
    }
}