package com.example.myapplication2;

import android.content.Context;
import android.media.Rating;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public final class MyAdapter extends BaseAdapter {
    public final List<Item> mItems = new ArrayList<Item>();
    public final LayoutInflater mInflater;


   public MyAdapter(Context context) {
        mInflater = LayoutInflater.from(context);

        mItems.add(new Item("Red",       R.drawable.image1));
        mItems.add(new Item("Magenta",   R.drawable.image2));
        mItems.add(new Item("Dark Gray", R.drawable.image3));
        mItems.add(new Item("Gray",      R.drawable.image4));
        mItems.add(new Item("Green",     R.drawable.image5));
        mItems.add(new Item("Cyan",      R.drawable.image6));
        mItems.add(new Item("Ayam Goreng",      R.drawable.image7));
        mItems.add(new Item("Racun Serangga",      R.drawable.image8));
        mItems.add(new Item("Laptop",      R.drawable.image9));
        mItems.add(new Item("Badminton",      R.drawable.image10));
        mItems.add(new Item("Wow",      R.drawable.image11));
        mItems.add(new Item("Blue",      R.drawable.image12));
        mItems.add(new Item("China",      R.drawable.image13));
        mItems.add(new Item("Korea",      R.drawable.image14));
        mItems.add(new Item("Japan",      R.drawable.image15));
    }


    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Item getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mItems.get(i).drawableId;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView picture;
        TextView name;
        RatingBar ratingbar;

        if (v == null) {
            v = mInflater.inflate(R.layout.grid_item, viewGroup, false);

            v.setTag(R.id.squareImageView, v.findViewById(R.id.squareImageView));
            v.setTag(R.id.text, v.findViewById(R.id.text));
            v.setTag(R.id.rating, v.findViewById(R.id.rating));
        }

        picture = (ImageView) v.getTag(R.id.squareImageView);
        name = (TextView) v.getTag(R.id.text);
        ratingbar = (RatingBar) v.getTag(R.id.rating);



       Item item = getItem(i);

        picture.setImageResource(item.drawableId);
        name.setText(item.name);
        ratingbar.setRating((item.drawableId));

        return v;
    }

    private static class Item {
        public final String name;
        public final int drawableId;

        Item(String name, int drawableId) {
            this.name = name;
            this.drawableId = drawableId;
        }
    }
}