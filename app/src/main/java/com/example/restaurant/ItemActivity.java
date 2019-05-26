package com.example.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.text.NumberFormat;

public class ItemActivity extends AppCompatActivity {

    private MenuItem menuItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        // Get the item the user clicked on.
        Intent intent = getIntent();
        menuItem = (MenuItem) intent.getSerializableExtra("menuItem");


        setTitle(menuItem.getCategory());


        TextView nameView = findViewById(R.id.textViewName);
        nameView.setText(menuItem.getName());


        TextView descriptionView = findViewById(R.id.textViewDescription);
        descriptionView.setText(menuItem.getDescription());


        TextView priceView = findViewById(R.id.textViewPrice);
        priceView.setText(NumberFormat.getCurrencyInstance().format(menuItem.getPrice()));


        ImageView imageView = findViewById(R.id.imageView);
        String imageUri = menuItem.getImageURL();
        Picasso.with(this).load(imageUri).into(imageView);

    }
}
