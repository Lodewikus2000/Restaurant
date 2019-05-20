package com.example.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {

    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        category = intent.getStringExtra("category");

        MenuRequest request = new MenuRequest(this);
        request.getMenuItems(this, category);

        setTitle(category);
    }



    @Override
    public void gotMenuItems(ArrayList<MenuItem> menuItems) {

        ListView menuList = findViewById(R.id.menuList);

        MenuAdapter adapter = new MenuAdapter(this, R.layout.menu_item, menuItems);

        menuList.setAdapter(adapter);

        menuList.setOnItemClickListener(new ListItemClickListener());

    }


    @Override
    public void gotMenuItemsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        Toast.makeText(this, "errormessage here", Toast.LENGTH_LONG).show();


    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            MenuItem clickedMenuItem = (MenuItem) parent.getItemAtPosition(position);

            Intent intent = new Intent(MenuActivity.this, ItemActivity.class);

            intent.putExtra("menuItem", clickedMenuItem);

            startActivity(intent);
        }

    }



}
