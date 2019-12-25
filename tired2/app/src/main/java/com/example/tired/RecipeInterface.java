package com.example.tired;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class RecipeInterface extends AppCompatActivity {
ImageView imageView;
TextView textView;
String title,imageURL;
RatingBar ratingBar;
Float data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_interface);


        float data2 =0;
        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);
        ratingBar = (RatingBar) findViewById(R.id.MyRating2);

        title = getIntent().getStringExtra("name");
        imageURL = getIntent().getStringExtra("image");
        data = getIntent().getFloatExtra("value",4f);
        ratingBar.setRating(data);

        textView.setText(title);
        Picasso.get().load(imageURL).into(imageView);
    }
}
