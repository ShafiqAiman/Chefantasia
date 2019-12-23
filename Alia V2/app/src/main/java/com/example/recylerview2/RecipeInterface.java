package com.example.recylerview2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recylerview2.R;
import com.squareup.picasso.Picasso;

public class RecipeInterface extends AppCompatActivity {
ImageView imageView;
TextView textView;
String title,imageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_interface);

        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);
        title = getIntent().getStringExtra("name");
        imageURL = getIntent().getStringExtra("image");

        textView.setText(title);
        Picasso.get().load(imageURL).into(imageView);
    }
}
