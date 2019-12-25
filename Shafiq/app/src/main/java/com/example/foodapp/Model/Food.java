package com.example.foodapp.Model;

public class Food {
    private String Name, Image, Price, Description, MenuId, Instructions;

    public Food() {
    }

    public Food(String name, String image, String price, String description, String menuId, String instructions) {
        Name = name;
        Image = image;
        Price = price;
        Description = description;
        MenuId = menuId;
        Instructions = instructions;
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

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }

    public String getInstructions() {
        return Instructions;
    }

    public void setInstructions(String instructions) {
        Instructions = instructions;
    }
}
