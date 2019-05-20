package com.example.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemActivity extends AppCompatActivity {

    private MenuItem menuItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Intent intent = getIntent();
        menuItem = (MenuItem) intent.getSerializableExtra("menuItem");

        TextView nameView = findViewById(R.id.textViewName);
        TextView descriptionView = findViewById(R.id.textViewDescription);
        TextView priceView = findViewById(R.id.textViewPrice);
        ImageView imageView = findViewById(R.id.imageView);


        Float price = menuItem.getPrice();

        nameView.setText(menuItem.getName());
        descriptionView.setText(menuItem.getDescription());
        priceView.setText(menuItem.getPrice().toString() + " €");

        setTitle(menuItem.getCategory());




        new DownloadImageFromInternet(imageView)
                .execute(menuItem.getImageURL());
    }
}
