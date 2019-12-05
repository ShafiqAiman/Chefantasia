package com.example.myapplication2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class RecipeActivity extends AppCompatActivity {
    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle SavedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_activity);
        
    }
}
