package com.example.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {


    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        CategoriesRequest request = new CategoriesRequest(this);
        request.getCategories(this);

    }


    @Override
    public void gotCategories(ArrayList<String> categories) {

        ListView categoriesList = findViewById(R.id.categoriesList);

        adapter = new ArrayAdapter<>(this, R.layout.list_item, categories);

        categoriesList.setAdapter(adapter);

        categoriesList.setOnItemClickListener(new ListItemClickListener());

    }


    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, "Could not find categories", Toast.LENGTH_LONG).show();
    }


    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String clickedCategory = (String) parent.getItemAtPosition(position);

            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);

            intent.putExtra("category", clickedCategory);

            startActivity(intent);
        }
    }
}
