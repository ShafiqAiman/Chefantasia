package com.example.recylerview2;

public class Recipe {
    private String Name, Image, MenuID, Ingredient, Price, Description;
    private float Rating;

    public Recipe() {

    }

    public Recipe(String Name, String Image, String Ingredient, float Rating, String Price, String MenuID, String Description) {
        this.Name = Name;
        this.Image = Image;
        this.Price = Price;
        this.MenuID = MenuID;
        this.Ingredient = Ingredient;
        this.Description = Description;
        this.Rating = Rating;
    }

    public String getName() {
        return Name;
    }

    public String getImage() {
        return Image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getMenuID() {
        return MenuID;
    }

    public void setMenuID(String menuID) {
        MenuID = menuID;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
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

    public void setIngredient(String ingredient) {
        Ingredient = ingredient;
    }

    public void setRating(float rating) {
        Rating = rating;
    }
}