package com.example.restaurant;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;



public class MenuAdapter extends ArrayAdapter<MenuItem> {

    private int resource;
    private ArrayList<MenuItem> menuItems;

    // constructor
    public MenuAdapter(Context context, int resource, ArrayList<MenuItem> objects) {
        super(context, resource, objects);

        this.menuItems = objects;
        this.resource = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, parent, false);
        }

        MenuItem currentMenu = menuItems.get(position);

        String name = currentMenu.getName();
        String description = currentMenu.getDescription();
        String imageURL = currentMenu.getImageURL();
        Float price =  currentMenu.getPrice();




        // Get the views for the image and text so we can fill them.
        TextView nameView = convertView.findViewById(R.id.textViewName);
        TextView descriptionView = convertView.findViewById(R.id.textViewDescription);
        TextView priceView = convertView.findViewById(R.id.textViewPrice);
        ImageView imageView = convertView.findViewById(R.id.imageView);

        nameView.setText(name);
        descriptionView.setText(description);
        priceView.setText(price.toString() + " €");





        new DownloadImageFromInternet(imageView)
                .execute(imageURL);

        return convertView;
    }





}
