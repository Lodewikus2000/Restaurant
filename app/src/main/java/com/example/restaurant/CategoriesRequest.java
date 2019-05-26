package com.example.restaurant;


import android.content.Context;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    private Callback currentActivity;


    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }


    public CategoriesRequest(Context aContext) {
        context = aContext;
    }


    @Override
    public void onErrorResponse(VolleyError error) {

        String errorMessage = error.getMessage();
        currentActivity.gotCategoriesError(errorMessage);

    }


    @Override
    public void onResponse(JSONObject response) {

        try {

            // New empty ArrayList of strings that will be filled with categories.
            ArrayList<String> categories = new ArrayList<String>();


            // Get the JSONArray from the response.
            JSONArray categoriesArray = response.getJSONArray("categories");


            for (int i = 0; i < categoriesArray.length(); i++) {

                String category = categoriesArray.getString(i);

                categories.add(category);
            }

            currentActivity.gotCategories(categories);

        } catch (JSONException exception) {
            exception.printStackTrace();
        }
    }


    public void getCategories(Callback activity) {

        currentActivity= activity;

        RequestQueue queue = Volley.newRequestQueue(context);

        // The API delivers the data as a Json.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/categories", null, this, this);
        queue.add(jsonObjectRequest);

    }
}
