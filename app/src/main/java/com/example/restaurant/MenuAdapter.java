package com.example.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.text.NumberFormat;
import java.util.ArrayList;



public class MenuAdapter extends ArrayAdapter<MenuItem> {

    private ArrayList<MenuItem> objects;



    public MenuAdapter(Context context, int resource, ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        this.objects = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, parent, false);
        }

        MenuItem currentMenu = objects.get(position);


        // Get the views for the image and text so we can fill them.
        TextView nameView = convertView.findViewById(R.id.textViewName);
        nameView.setText(currentMenu.getName());


        TextView descriptionView = convertView.findViewById(R.id.textViewDescription);
        descriptionView.setText(currentMenu.getDescription());


        TextView priceView = convertView.findViewById(R.id.textViewPrice);
        priceView.setText(NumberFormat.getCurrencyInstance().format(currentMenu.getPrice()));


        ImageView imageView = convertView.findViewById(R.id.imageView);
        Picasso.with(getContext()).load(currentMenu.getImageURL()).into(imageView);


        return convertView;
    }





}
